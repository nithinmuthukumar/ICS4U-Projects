import java.util.ArrayList;

class BTest{
    public static void main(String[] args){
        test3();
        test4();
        test5();
    }
    static void test3() {
        BTree balanced = new BTree();
        balanced.add(50);
        balanced.add(17);
        balanced.add(72);
        balanced.add(12);
        balanced.add(23);
        balanced.add(54);
        balanced.add(76);
        balanced.add(67);
        balanced.add(9);
        balanced.add(14);
        balanced.add(19);
        balanced.delete(50);
        System.out.println(balanced);
    }

    static void test4() {
        BTree tester = new BTree();
        for (int i = 10; i > 0; i--) {
            tester.add(i);
        }
        tester.delete(10);
        tester.display(BTree.IN);
        BTree tester2 = new BTree();
        for (int i = 0; i < 10; i++) {
            tester2.add(i);
        }
        tester2.delete(0);
        tester2.display(BTree.IN);
    }
    static void test5(){
        BTree tester = new BTree();
        System.out.println(tester.isBalanced());
    }
}
