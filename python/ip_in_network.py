#!/usr/local/bin/python3
import re

# write a function that checks to see if an IP address is in a provided network
# in this example see if an IP is local

my_ip = "207.224.48.249"

local_networks = [
    "10.0.0.0/8",
    "172.16.0.0/12",
    "192.168.0.0/16",
#    "207.224.48.248/24",
]

def check_in_networks(ip, networks):
    #chunk it up, make a big number for masking
    (a,b,c,d) = tuple(map(int, re.match(r'^(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})$', ip).groups()))
    test_ip_number = (a << 24) + (b<<16) + (c<<8) + (d)

    for net in networks:
        (na,nb,nc,nd,sn) = tuple(map(int, re.match(r'^(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})\/(\d+)$', net).groups()))
        network_ip_number = (na << 24) + (nb<<16) + (nc<<8) + (nd)

        # start at msb and work backward until i'm out of bits i care about
        net_mask_sum = 0;
        ip_mask_sum  = 0;

        for bit in range(31,31-sn,-1):
            my_bit = ( 1 << (bit))
            net_mask_sum += my_bit & network_ip_number
            ip_mask_sum  += my_bit & test_ip_number

        if net_mask_sum == ip_mask_sum:
            return True
    return False


t = check_in_networks(my_ip, local_networks)
print("%s\nIn: %r\nFrom: %s" % (my_ip, t, str(local_networks)))
