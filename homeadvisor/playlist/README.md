# Playlist
You have just made a playlist on your MP3 player with all of your songs. Some of them may be listed more than once. Your buttons are limited and you can only go down ↓ or up ↑ in the list one song at a time. The list is circular, so when you are at the end of the list, ↓ takes you to the beginning and vice versa.

 

Given the index of the song that is currently playing, determine the minimum number of button presses you will need to reach the requested next song. For example, your playlist includes [money, wishyouwerehere, welcometothemachine, time]. Currently time is playing, index 3, and you want to play money next, index 0. You can either go down 1 or up 3 to reach the song.

 

Function Description 

Complete the function playlist in the editor below. The function must return the integer value of the least button presses to get to the requested song.

 

playlist has the following parameter(s):

    songs[songs[0],...songs[n-1]]:  an array of song name strings in order of the playlist

    k: the index of the song currently playing

    q: the name of the song you want to play next

 

Constraints

1 ≤ n ≤ 100
0 ≤ k ≤ n−1
1 ≤ |s[i]|, |q| ≤ 100
Song q is in the playlist.
 

Input Format for Custom Testing
Input from stdin will be processed as follows and passed to the function.

 

The first line contains an integer n, the size of the array songs.

Each of the next n lines contains a string songs[i] where 0 ≤ i < n.

The next line contains the integer k.

The next line contains the string q.

Sample Case 0
Sample Input 0

4
wheniseeyouagain
borntorun
nothingelsematters
cecelia
1
cecelia
 

Sample Output 0

2
 

Explanation 0

You are listening to song s[k] = s[1] = "borntorun". By pressing the ↓ or the ↑ button 2 times, you can reach q = s[3] = "cecelia".

 

Sample Case 1
Python 3
Autocomplete Ready


131415161718192021222324252627282930313233343536373839401211110
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
…    result = playlist(songs, k, q)

    fptr.write(str(result) + '\n')

    fptr.close()

Line: 44 Col: 1
Test Results
Custom Input

Run


Submit Code
