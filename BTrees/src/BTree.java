/*BTree.java
* Nithin Muthukumar
* ICS4U
* A class which holds a reference to a BNode to create a binary tree*/
public class BTree {
    //the initial top node
    private BNode root;
    //constants for display type
    public static final int IN = 1;
    public static final int PRE = 2;
    public static final int POST = 3;

    public BTree() {
        root = null;
    }
    //add a new node
    public void add(int v) {
        //if there is no root set the new node as root
        if (root == null) {
            root = new BNode(v);
        } else {
            //add the newly created BNode to the tree
            add(new BNode(v), root);
        }
    }


    private void add(BNode newNode, BNode branch) {
        //checks if node should be to the left of branch or right of branch which
        // depends on whether it is bigger or smaller
        if (newNode.getVal() > branch.getVal()) {
            //if the child is null than the node can take that spot
            if (branch.getRight() == null) {
                branch.setRight(newNode);
            } else {
                add(newNode, branch.getRight());
            }
        } else if (newNode.getVal() < branch.getVal()) {
            if (branch.getLeft() == null) {
                branch.setLeft(newNode);
            } else {
                add(newNode, branch.getLeft());
            }
        }
    }
    //adds trees
    public void add(BTree tree){
        addTreeNodes(tree.root);
    }
    private void addTreeNodes(BNode branch){
        //if there is a node add it to the tree
        //we cant just attach the root to the tree because first of all
        // it has to be a clone and not the same object
        //and it would make the tree inefficient because the tree could be extremely unbalanced
        if(branch!=null){
           add(branch.getVal());
           addTreeNodes(branch.getRight());
           addTreeNodes(branch.getLeft());
        }



    }
    public void sprout(){
        if(root!=null)
            sprout(0,root);
    }
    public void sprout(int parent,BNode node){
        if(node.getRight()==null&&node.getLeft()==null){
            add((parent+node.getVal())/2);
        }else{
            if(node.getLeft()!=null)
                sprout(node.getVal(),node.getLeft());
            if(node.getRight()!=null)
                sprout(node.getVal(),node.getRight());
        }
    }
    
    //starts at show because the node the first tow has depth show
    public int depth(int n) {
        return depth(n, root, 1);

    }

    private int depth(int n, BNode node, int level) {
        //if the node doesnt exist return -1
        if (node == null) {
            return -1;
        }
        //if its the node return the depth otherwise recurse through and add to level
        if (n == node.getVal()) {
            return level;
        }
        if (n < node.getVal()) {
            return depth(n, node.getLeft(), level + 1);

        }
        return depth(n, node.getRight(), level + 1);

    }

    public int countLeaves() {
        return countLeaves(root, 0);
    }

    private int countLeaves(BNode node, int leaves) {
        //if the root itself is null or the child is n
        if(node==null){
            return leaves;
        }
        //if both are null it is a leaf so add 1
        if (node.getLeft() == null && node.getRight() == null) {
            return leaves + 1;
        }
        //add the number of leaves from the left and right side and sends them up the call stack
        return countLeaves(node.getLeft(), leaves) + countLeaves(node.getRight(), leaves);

    }

    public int height() {
        return height(root, 0);
    }

    private int height(BNode node, int height) {
        if (node == null) {
            return height;
        }
        //the height will be to the lowest level so we take max
        //add to height as we traverse downwards
        return Math.max(height(node.getLeft(), height + 1), height(node.getRight(), height + 1));
    }

    @Override
    public String toString() {
        //replaces extra  in the string if there is show
        return ("[" + inOrder(root) + "]").replace(", ]", "]");

    }


    public void display(int type) {
        String s="";
        switch (type) {
            case IN:
                //inorder is already in toString
                s=toString();

            case PRE:

                s=("["+preOrder(root)+"]").replace(", ]","]");
            case POST:

                s=("["+postOrder(root)+"]").replace("[, ","[");
        }
        //s can never equal "" because it will always have [] unless the number is not 1 2 or 3
        if(!s.equals(""))
            System.out.println(s);


    }
    //the trees toString method will be called
    public void display(){
        System.out.println(this);
    }
    //all three of the following methods are the same except the order of values
    private String inOrder(BNode branch) {

        if (branch == null)
            return "";
        //joins them with commas so that the look proper
        return inOrder(branch.getLeft()) + branch.getVal() + ", " + inOrder(branch.getRight());
    }

    private String preOrder(BNode branch) {
        if (branch == null)
            return "";
        return branch.getVal() + ", " + preOrder(branch.getLeft()) + preOrder(branch.getRight());
    }

    private String postOrder(BNode branch) {
        if (branch == null)
            return "";

        return postOrder(branch.getLeft()) + postOrder(branch.getRight()) + ", " + branch.getVal();



    }
    //function that is the exact same as height but finds the minimum instead
    //used in isBalanced
    private int minHeight(BNode node,int h){
        if(node==null)return h;
        return Math.min(minHeight(node.getLeft(),h+1),minHeight(node.getRight(),h+1));

    }
    public boolean isBalanced(){
        return height()-minHeight(root,0)<2;
    }

    public boolean isAncestor(int a,int n){
        //finds the node
        BNode ancestor=search(root,a);
        //if the node exists find the ancestor and check if the node appears
        // below it with search but from below the node
        if(ancestor!=null)
            if(search(ancestor.getRight(),n)!=null||search(ancestor.getLeft(),n)!=null)
                return true;

        return false;

    }
    //finds a node with the value n

    private BNode search(BNode node,int n){
        //return null if node is not found or returns node if it is found
        if(node==null||node.getVal()==n)
            return node;
        //traverses tree based on if it is less than or greater than
        if(n<node.getVal())
            return search(node.getLeft(),n);
        return search(node.getRight(),n);



    }
    //finds the parent of node
    private BNode findParent(BNode node,BNode parNode){
        //if the node is null(which only happens if the value is the root then it returns null)
        if(parNode==null||(parNode.getRight()==null&&parNode.getRight()==null))
            return null;
        //if the node is right after than this is the parent
        if(parNode.getLeft()==node||parNode.getRight()==node)
            return parNode;
        //traverse through recursively
        if(node.getVal()<parNode.getVal())
            return findParent(node,parNode.getLeft());

        return findParent(node,parNode.getRight());


    }

    //java does not error when removing something that is not in the arraylist
    //so that is followed here as well
    public void delete(int n){
        //finds node that is to be deleted
        BNode delNode=search(root,n);
        if(delNode!=null) {
            //finds parent
            //if there is no parent we know its the root
            BNode parent=findParent(delNode,root);
            if(parent!=null) {
                //if there is a parent check which side its on and remove the reference
                if (parent.getRight() == delNode) {
                    parent.setRight(null);
                } else if (parent.getLeft() == delNode) {
                    parent.setLeft(null);
                }
                //using the reference of the deleted node that we have if it has branches, add that node to the tree
                //the node will carry all its children with it
                //no need to balance because since the tree was already like this it will end up in relatively the same spot
                if (delNode.getLeft() != null) {
                    add(delNode.getLeft(), root);
                }
                if (delNode.getRight() != null) {
                    add(delNode.getRight(), root);
                }
            }else{
                //root becomes the right child which could be null

                BNode left=delNode.getLeft();
                root=delNode.getRight();
                //if the left child is not null and the right child is not null the we add the left subtree
                //otherwise left becomes the head
                if(left!=null){
                    if(root!=null) {
                        add(left, root);
                    }else{
                        root=left;
                    }
                }
            }
        }
    }


}
