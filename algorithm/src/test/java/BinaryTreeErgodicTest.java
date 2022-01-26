import linear.Queue;
import tree.BinaryTree;

public class BinaryTreeErgodicTest {
    public static void main(String[] args) {
        BinaryTree<String, String> tree = new BinaryTree<>();

        tree.put("E", "5");
        tree.put("B", "2");
        tree.put("G", "7");
        tree.put("A", "1");
        tree.put("D", "4");
        tree.put("F", "6");
        tree.put("H", "8");
        tree.put("C", "3");

        Queue<String> keys = tree.layerErgodic();
        for (String key : keys) {
            String value = tree.get(key);
            System.out.println(key + "-------" + value);
        }

    }
}
