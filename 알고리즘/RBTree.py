class Node:
    def __init__(self, value):
        self.value = value
        self.parent = None
        self.left = None
        self.right = None
        self.color = True #True = red, False = black
        
    #child node become black if it's empty node.

    def makeRed(self):
        self.color = True
        
    def makeBlack(self):
        self.color = False
                
    def findG(self):#GrandNode
        return self.parent.parent
    
    def findP(self):#ParNode
        return self.parent
    
    def findU(self):#UncleNode
        gNode = self.findG()
        if gNode.left == self.parent:
            return gNode.right
        else:
            return gNode.left
        
    
class Tree:
    def __init__(self):
        self.root = None;
    
    def search(self,target): #find nearest node.
        now = self.root
        prev = None
        while(now):
            prev = now
            if now.value == target:
                return now
            elif target < now.value:
                now = now.left
            elif target > now.value:
                now = now.right
        return prev
    
    def inspectColor(self,node):
        if not node.findP():
            return
        if not node.findG():
            return
        if not node.findP().color == node.color == True:
            return
        #from here, only Red-Red case remains
        uncle = node.findU()
        if not uncle:
            self.reStructure(node)
        else:
            if not uncle.color:
                #Black 
                self.reStructure(node)
            else:
                #Red
                self.reColor(node)
        
    def insert(self,value):
        node = Node(value)
        if not self.root:
            self.root = node
            node.makeBlack()
            return
        par = self.search(value)
        parval = par.value
        node.parent = par
        if value > parval:
            par.right = node
        else:
            par.left = node
        self.inspectColor(node)
    
    def reStructure(self,node):
        p = node.findP()
        g = node.findG()
        gg = g.parent
        nodeList = [node,p,g]
    
        #prepare reStructre 
        if g.left == p:
            g.left = None
        else:        
            g.right = None
            
        if p.left == node:
            p.left = None
        else:
            p.right = None
            
        #sort
        for i in range(2):
            for x in range(2-i):
                if nodeList[x].value > nodeList[x+1].value:
                    tmp = nodeList[x]
                    nodeList[x] = nodeList[x+1]
                    nodeList[x+1] = tmp
        
        #restructure
        nodeList[0].parent = nodeList[1]
        nodeList[2].parent = nodeList[1]
        
        nodeList[1].left = nodeList[0]    
        nodeList[1].right = nodeList[2]
        
        if gg:
            if gg.right == g:
                gg.right = nodeList[1]
            else:
                gg.left = nodeList[1]
        else:
            self.root = nodeList[1]
        
        nodeList[1].parent = gg
        
        nodeList[0].color = True
        nodeList[1].color = False
        nodeList[2].color = True
    
    def reColor(self,node):
        #if grandparent node is not root,
        #dobule Red situation can happen again
        p = node.findP()
        g = node.findG()
        u = node.findU()
        
        p.makeBlack()
        u.makeBlack()
        if g != self.root:
            g.makeRed()
            #double red could happen in grandparent node
            self.inspectColor(g)
        
#Test Code
#not check if data overlapped
rbt = Tree()

values =  [8, 7, 9, 3, 6, 4, 5, 12]

for x in values :
    rbt.insert(x)

def test(node):
    if not node.left  == None : test(node.left)
    print('node : ', node.value, ' parent : ', end = '')
    
    if node.parent != None:
        print(node.parent.value, end = '')
    else:
        print('X', end = '')    
        
    print(' Color: ', end = '')
    
    if node.color:
        print("Red", end = "\n")
    else:
        print("Black", end = "\n")
    if not node.right == None : test(node.right)
    
test(rbt.root)