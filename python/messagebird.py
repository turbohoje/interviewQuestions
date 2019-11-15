#!/usr/local/bin/python3

"""

A = 1
B = 2*A + A
C = 3*B + B
...

The weight of any string made up of these characters is the summation of weights of each character.
Given a total string weight, determine shortest string of that given weight.  If there is more than one solution,
return the lexicographically smallest of them.  For example, given weight = 25, and the weights of the first few chars
of the alphabet are A=1, B=3, C=12, D=60 it is certain that no letter larger than C is required.  Some of the sstrings with a total
weight equal to teh target are ABBBC, ACC and AAAAAAABBBBBB.  The shortest of these is ACC.  While any permutation of these
characters wil have the same weight, this is the lexicographically smallest of them.
"""

chars = ['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z']
#popuate wights
weights = [1]
for x in range(1, 26):
    weights.append( (weights[x-1] * (x+1)) + weights[x-1] )

def get_fact(weight):
    currWeight = weight
    rtot = ''

    while(currWeight > 0):
        for w in range(len(weights)-1, -1, -1):
            if(weights[w] <= currWeight):
                currWeight = currWeight - weights[w]  #this could be optimized with some modulo and not seeking all the way from len(weights)
                rtot += chars[w]

                break

    return rtot[::-1]

print(get_fact(11))


