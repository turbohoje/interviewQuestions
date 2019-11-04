#!/usr/local/bin/python3



class Node:
    left = None
    right = None
    value = None

    def __init__(self):
        left = None
        right = None
        value = "";

    def getMaxDepth(self):
        if( self.left is not None ):
            return self.left.getMaxDepth()+1
        else:
            return 1

    def setVal(self, v):
        self.value = v

    def printree(self):
        # get depth of tree
        #print(self.getMaxDepth())

        print("      %s" % (self.value))
        print("    /    \  " )
        print("  %s       %s  " % (self.left.value if self.left is not None else 'none' , self.right.value if self.right is not None else 'none') )






tree = Node()

tree.setVal("d")

tree.left = Node()
tree.right = Node()
tree.left.value="a"
tree.right.value="b"
tree.printree()


#unifinished, short answer left =  reverse(right) right = reverse(left) however this leaves things unbalanced.

"""
        U
       /         \
      w             n
     /  \          /
   x      y       z
  /  \   /  \    / 
 a   b   c   d   e

"""
