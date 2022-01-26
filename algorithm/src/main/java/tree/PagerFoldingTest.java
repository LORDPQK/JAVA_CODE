package tree;

import linear.Queue;

public class PagerFoldingTest {
    public static void main(String[] args) {
        //模拟折纸过程，产生树
        Node tree = createTree(2);
        //打印树
        printTree(tree);

    }


    //模拟通过N次对折产生树
    public static Node<String> createTree(int N){
        //定义根节点
        Node<String> root = null;

        for (int i = 0; i <N ; i++) {
            //如果时第一次对折
            if(i==0){
                root = new Node<>("down",null,null);
                continue;
            }

            //定义一个辅助数组，通过层序遍历的思想，先找到叶子节点，在叶子节点下面添加左右子节点,因为一次对折，树多出一层
            Queue<Node> queue = new Queue<>();
            queue.enqueue(root);

            while(!queue.isEmpty()){
                //从队列中弹出一个节点
                Node a =queue.dequeue();
                //如果有左子节点，将其放入队列中
                if(a.left!=null){
                    queue.enqueue(a.left);
                }
                //如果有右子节点，将其放入队列中
                if(a.right!=null){
                    queue.enqueue(a.right);
                }
                //如果同时没有左子节点和右子节点，证明该节点是叶子节点，只需要给该叶子节点添加左右子节点即可
                if(a.right==null && a.left==null){
                    a.left = new Node<String>("down",null,null);
                    a.right = new Node("up",null,null);
                }
            }
        }

        return root;


    }


    public static  void printTree(Node root){
        if(root == null){
            return ;
        }

        //先访问左子树
        if(root.left!=null){
            printTree(root.left);
        }
        //打印节点key值
        System.out.println(root.item);
        //访问右子树
        if(root.right!=null){
            printTree(root.right);
        }
    }

    //节点类
    public static class Node<T>{
        public T item;
        public Node left;
        public Node right;

        public Node(T item ,Node left,Node right){
            this.item = item;
            this.left = left;
            this.right = right;
        }
    }

}
