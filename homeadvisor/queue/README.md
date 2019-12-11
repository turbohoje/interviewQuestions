#  Priority Caching
A caching system uses 'priority' to decide what memory items are moved to the cache. All items start in the main memory with a priority of 0. Priority of all items decrease by 1 per second, but the priority of an item is bumped up by 2 instead of being decremented at that second whenever it is accessed. The minimum priority is 0.

When the priority of an item exceeds 5, it is moved to the cache.
When the priority of an item in cache becomes less than or equal to 3 it is moved back to the main memory.
If an item is accessed more than once in the same second, it will be incremented by 2 x (number of times it is accessed at that second). 
 

 The logs of all calls to access memory items will be provided in the format given below, not necessarily in any sorted order.

<timestamp> <item_id>
Return the item IDs of all items in the cache in ascending order, once the final log entry is made. If there is no item in the cache, return the array [-1].

 

For example, the logs are callLogs = [[1, 1], [2, 1], [2, 1], [4, 2], [5, 2], [6, 2]].  Both of the items start at priority 0. In the table below, the number of times an item is accessed at a time is shown in the "access" column. An item is in the cache at the times it has an asterisk. At the end, only item 2 is in the cache. Note that at second 2, item 1 was accessed twice, so its priority increased 2 * times accessed that second = 4.

 

Time    Item 1           Item 2
        access priority  access priority
0                0                0
1         1      2                0
2         2      6*               0
3                5*               0
4                4*        1      2
5                3         1      4
6                2         1      6*
 

Function Description

 

Complete the cacheContents function in the editor below. The function must return an integer array denoting the items present in the cache.

 

cacheContents has the following parameter(s):

    callLogs: A 2D integer array, that denotes the logs of the calls made to memory. 

 

Constraints

1 ≤ n ≤ 105
1 ≤ callLogsij ≤ 103
 

Input Format For Custom Testing
The first line contains an integer, n, the number of log entries.

The next line contains an integer, 2, the number of parameters to describe a log entry. 

Each line i of the n subsequent lines (where 0 ≤ i < n) contains the log entry in the form described above. 

Sample Case 0
Sample Input For Custom Testing

6
2
1 1
2 1
3 1
4 2
5 2
6 2
Sample Output

2
Explanation

3 calls are made for item 1, and by time 3, since the priority is 6, it is moved to the cache. Now its priority decays by 1 every second till time 6 at which it is moved back to the main memory.
Similarly, 3 calls are made for item 2, and at time 6 it will be moved to the cache.
Sample Case 1
Python 3
Autocomplete Ready


1101112131415161718192021
#!/bin/python3

import math
import os
import random
import re
import sys



#
# Complete the 'cacheContents' function below.
#
# The function is expected to return an INTEGER_ARRAY.
# The function accepts 2D_INTEGER_ARRAY callLogs as parameter.
#

def cacheContents(callLogs):

Line: 10 Col: 1
Test Results
Custom Input

Run


Submit Code
