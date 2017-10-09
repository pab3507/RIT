from rit_lib import *

def createBST():
    return BinaryTree(None)

class BinaryTree(struct):
    _slots = (BSTNode, 'root')

class BSTNode(struct):
    _slots = ((('BSTNode',NoneType),'left'), (object, 'value'),(('BSTNode', NoneType), 'right'))

    def add(self, value):
        if self.root == None:
            self.root = BSTNode(None, value, None)
        else:
            current = self.root
            while True:
                if value < current.value and current.left != None:
                    current = current.left
                elif value < current.value:
                    current.left = BSTNode(None, value, None)
                    break
                elif value >= current.value and current.right != None:
                    current = current.right
                else:
                    current.right = BSTNode(None,value, None)
                    break
    def search(self):
        pass


def main():
    myTree = createBST()
    myTree.add(7)
    print(myTree)