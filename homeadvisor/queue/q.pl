#!/usr/bin/perl
#use Data::Dumper;

use strict;



#determine what is in the cache from a state machine
sub cacheContents {
    my $clptr = shift;
    # logs are "not necessarily" in order, sort by [0] in place bec i later depend on order.
    @$clptr = sort { $a->[0] <=> $b->[0] } @$clptr;

    my %mem;

    #incr/decr function for possible later expansion or mods
    sub decr{ # value
        my $val = shift;
        $val -= 1;
        if( $val < 0 ){ return 0; }
        return $val;
    }
    sub incr{ #array, returns a hash of all values to enc
        my $addrs = shift;
        my %vals;
#        print "incr vals: ";
        foreach(@$addrs){
            $vals{$_} ++;
#            print $_;
        }
#        print "\n";
        foreach(keys(%vals)){
            #if($vals{$_} > 1){
                $vals{$_} = $vals{$_} * 2;
            #}

        }
        return %vals;
    }

    #decrement appropriately, addr is an array of who not to decrement
    sub clock_cycle{
        my $addrs =  shift;
        my $size = scalar @$addrs;

        my %incr = incr($addrs);
        foreach(keys(%incr)){
            $mem{$_} += $incr{$_};
        }
        @$addrs = (); #empty array

        foreach my $i (sort keys(%mem)){
            if($size > 0 && grep {$_ ne $i} keys(%incr)){
                $mem{$i} = decr($mem{$i});
            }
            elsif($size==0){
                $mem{$i} = decr($mem{$i});
            }
        }
    }

    #build the state
    my $last_timestamp = 0;
    my @accesses = ();
    foreach(@$clptr){
        my ($timestamp, $item_id) = ($_->[0], $_->[1]);
#        print "DATA ROW Time: $timestamp, Item: $item_id - $#accesses\n";

        if($last_timestamp == $timestamp){
            push(@accesses, $item_id);
            next;
        }

        while($last_timestamp < $timestamp){
#            print "\tcatchup: LT: $last_timestamp  T: $timestamp \n";
            clock_cycle(\@accesses);
            $last_timestamp += 1;
        }

        push(@accesses, $item_id);
    }
    clock_cycle(\@accesses);

    #return formatting
    my @retval = ();
    foreach my $i (sort keys(%mem)){
        if($mem{$i} >= 4){
            push(@retval, $i);
        }
    }
    return @retval;
}


############### their code ###

my $callLogs_rows = ltrim(rtrim(my $callLogs_rows_temp = <DATA>));
my $callLogs_columns = ltrim(rtrim(my $callLogs_columns_temp = <DATA>));
my @callLogs = ();
for (1..$callLogs_rows) {
    my $callLogs_item = rtrim(my $callLogs_item_temp = <DATA>);
    my @callLogs_item = split /\s+/, $callLogs_item;
    push @callLogs, \@callLogs_item;
}

my @result = cacheContents \@callLogs;
print  join "\n", @result;
print  "\n";
sub ltrim {
    my $str = shift;
    $str =~ s/^\s+//;
    return $str;
}
sub rtrim {
    my $str = shift;
    $str =~ s/\s+$//;
    return $str;
}

__DATA__
6
2
1 1
2 1
2 1
4 2
6 2
5 2
