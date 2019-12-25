public class BNode {
    private int val;
    private BNode left,right;
    public BNode(int v){
        val=v;
        left=null;
        right=null;


    }

    public int getVal() {
        return val;
    }

    public BNode getLeft() {
        return left;
    }

    public BNode getRight() {
        return right;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public void setLeft(BNode left) {
        this.left = left;
    }

    public void setRight(BNode right) {
        this.right = right;
    }
}
