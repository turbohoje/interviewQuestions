#!/usr/bin/perl
#use Data::Dumper;

use strict;



#determine what is in the cache from a state machine
sub cacheContents {
    my $clptr = shift;
    # logs are "not necessarily" in order, sort by [0] in place bec i later depend on order.
    @$clptr = sort { $a->[0] <=> $b->[0] } @$clptr;

    my %mem;
    my %cache;

    my $timeVal = 0;
    sub p_state{
        print "STATE<$timeVal>: ";
        foreach my $i (sort(keys(%mem))){
            print "[$i]($mem{$i}), " if ($mem{$i});
        }
        print "\n";
    }

    #incr/decr function for possible later expansion or mods
    sub decr{ # expects only a value
        my $val = shift;
        $val -= 1;
        if( $val < 0 ){ return 0; }
        return $val;
    }
    sub incr{ #expects array, returns a hash of all values to enc
        my $addrs = shift;
        my %vals;
        foreach(@$addrs){
            $vals{$_} ++;
        }
        foreach(keys(%vals)){
            $vals{$_} = $vals{$_} * 2;
#            print "i$vals{$_} ";
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
            #add to cache on increment
            if($mem{$_} > 5){
                $cache{$_} = 1;
            }
        }
        @$addrs = (); #empty array


        foreach my $i (sort keys(%mem)){
            if(!grep {$_ eq $i} keys(%incr)){
#                print "d$i ";
                $mem{$i} = decr($mem{$i});

                #remove from cache
                if($mem{$i} <= 3){
                    $cache{$i} = 0;
                }
            }

        }
        #p_state();
    }

    #build the state
    my $last_timestamp = 0;
    my @accesses = ();
    foreach(@$clptr){
        my ($timestamp, $item_id) = ($_->[0], $_->[1]);
        #######
        $timeVal ++;
#        print "DATA ROW Time: $timestamp, Item: $item_id - $#accesses\n";

        if($last_timestamp == $timestamp){
            push(@accesses, $item_id);
            next;
        }

        while($last_timestamp < $timestamp){ #time wo access
#            print "catchup: LT: $last_timestamp  T: $timestamp \n";
            clock_cycle(\@accesses);
            $last_timestamp += 1;
        }

        push(@accesses, $item_id);
    }
#    print "last\n";
    clock_cycle(\@accesses);

    #return formatting items in cache
    my @retval = ();
    foreach my $i (sort keys(%cache)){
        if($cache{$i} > 0){
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
5 2
6 2
