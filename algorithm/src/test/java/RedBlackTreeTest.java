import tree.RedBlackTree;

public class RedBlackTreeTest {
    public static void main(String[] args) {

        RedBlackTree<String, String> stringStringRedBlackTree = new RedBlackTree<>();

        stringStringRedBlackTree.put("1","1");
        stringStringRedBlackTree.put("2","2");
        stringStringRedBlackTree.put("3","3");
        stringStringRedBlackTree.put("4","4");
        stringStringRedBlackTree.put("5","5");
        stringStringRedBlackTree.put("6","6");
        stringStringRedBlackTree.put("7","7");
        stringStringRedBlackTree.put("8","8");

        int size = stringStringRedBlackTree.size();
        System.out.println("tree size is : "+size);

        String s = stringStringRedBlackTree.get("1");
        System.out.println(s);
        String s1 = stringStringRedBlackTree.get("2");
        System.out.println(s1);
        String s2 = stringStringRedBlackTree.get("3");
        System.out.println(s2);




    }
}
