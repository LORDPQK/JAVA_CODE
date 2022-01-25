package tree;
public class BinarytreeTest {
    public static void main(String[] args) {
        BinaryTree<Integer, String> tree = new BinaryTree<>();

        //测试插入
        tree.put(1,"张三1");
        tree.put(2,"张三2");
        tree.put(3,"张三3");

        System.out.println(tree.size());
       // System.out.println(tree.get(2));


        tree.delete(1);
        System.out.println(tree.size());
        System.out.println(tree.get(1));


    }
}
