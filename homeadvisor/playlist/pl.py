#!/usr/local/bin/python3
#!/bin/python3

import math
import os
import random
import re
import sys

#
# Complete the 'playlist' function below.
#
# The function is expected to return an INTEGER.
# The function accepts following parameters:
#  1. STRING_ARRAY songs
#  2. INTEGER k
#  3. STRING q
#

def playlist(songs, idx, songname):
    try:
        if(songs[idx] == songname):
            return 0

        def wrap(i):
            while(i >= len(songs)):
                 i -= len(songs)
            while(i < 0):
                i += len(songs)
            return i;

        #due to the possibility of multiple entries of a songs in a list, gotta brute it. at least we are O(n/2)
        for count in range(1, len(songs)//2 + 1):
            if(songs[wrap(idx + count)] == songname):
                return count
            if(songs[wrap(idx - count)] == songname):
                return -count #abs this to get only quantity
    except:
        print("playlst input error: " + str(idx) + " Song: " + songname);



if ( __name__ == '__main__'):
    songs = ['money', 'wishyouwerehere', 'dude', 'welcometothemachine', 'time', 'dude']
    print("ANSER " + str(playlist(songs, 5, 'dude')))

### FIX THIS ANT THE INTERPRETER LINE BEFORE TURNING IN
if __name__ == 'COMMENT__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    songs_count = int(input().strip())

    songs = []

    for _ in range(songs_count):
        songs_item = input()
        songs.append(songs_item)

    k = int(input().strip())

    q = input()

    result = playlist(songs, k, q)

    fptr.write(str(result) + '\n')

    fptr.close()
