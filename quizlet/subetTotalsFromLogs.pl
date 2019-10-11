#!/usr/bin/perl

# find all the totals for ips in the given range
# open logs to find the traffic. 3832

open(FILE, "nginx_logs.txt");

my @DATA = <FILE>;
close(FILE);

@masks = (
"192.0.0.0/8",
"93.180.0.0/16",
"80.91.0.0/16",
"192.235.0.0/16",
"0.0.0.0/0",
"128.0.0.0/1",
"128.0.0.0/3"
);

%totals;

foreach(@DATA){
    my ($a,$b,$c,$d, $mess) = m/^(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})(\s.*)/;

    my $ip = 0;
    $ip += (int($a) << 24);
    $ip += (int($b) << 16);
    $ip += (int($c) << 8);
    $ip += int($d);

    foreach $msk (@masks){
        my $mask = 0;
        my ($ma, $mb, $mc, $md, $bits) = ($msk =~ m/^(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})\/(\d+)/);

        $mask += (int($ma) << 24);
        $mask += (int($mb) << 16);
        $mask += (int($mc) << 8);
        $mask +=  int($md);

        my $ok = 1;
        for($i = $bits; $ok && $i > 0; $i--){
            my $test = 1 << (31-$i);

            if(($test & $ip) != ($test & $mask)){
                $ok = 0;
            }
        }
        if($ok){
            $totals{$msk} = int($totals{$msk}) + 1;
         }
    }
}

foreach(keys(%totals)){
    print "$_ : $totals{$_}\n";
}
