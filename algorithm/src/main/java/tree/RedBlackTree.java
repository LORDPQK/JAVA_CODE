package tree;

public class RedBlackTree <Key extends Comparable<Key>,Value >{

    //记录根节点
    private Node root;
    //记录树中元素的个数
    private int N;
    //红色连接标识
    private static final boolean RED = true;
    //黑色连接标识
    private static final boolean BlACK = false;

    public class Node{
        //存储键
        public Key key;
        //存储值
        public Value value;
        //记录左子节点
        public Node left;
        //记录右子节点
        public Node right;
        //由父节点指向它的连接的颜色
        public boolean color;

        public Node(Key key, Value value, Node left, Node right, boolean color) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.color = color;
        }
    }


    //判断当前父节点指向它的连接是否为红色
    private boolean isRed(Node x){

        if(x==null){
            return false;
        }

        return x.color == RED;
    }

    //左旋
    private Node rotateLeft(Node h){
        Node x =h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    //右旋
    private Node rotateRight(Node h){
        Node x = h.right;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color =RED;
        return x;
    }

    //颜色反转，相当于拆分4-节点
    private void filpColors(Node h){
        h.left.color = BlACK;
        h.left.color =RED;
        h.color =RED;
    }

    //在整个树上完成插入操作
    public void put(Key key,Value value){
        root = put(root, key, value);
        root.color =BlACK;
    }

    //在指定树中完成插入操作，并返回添加节点之后的树
    public Node put(Node h, Key key, Value value){
        //如果根节点为空
        if(h==null){
            N++;
            return new Node(key,value,null,null,RED);
        }

        //找到插入的位置
        int cmp = key.compareTo(h.key);

        if(cmp<0){
            //如果比当前元素小，查找左子树
            h.left = put(h.left,key,value);
        }else if(cmp >0){
            //如果比当前元素大，查找右子树
            h.right = put(h.right,key,value);
        }else{
            h.value = value;
        }


        //左旋,如果当前节点的左子节点为黑色，右子节点为红色，需要左旋

        if(isRed(h.right) && !isRed(h.left)){
           h = rotateLeft(h);
        }
        //右旋，如果当前节点的左子节点以及左子节点的左子节点都为红色，需要右旋
        if(isRed(h.left) && isRed(h.left.left)){
           h = rotateRight(h);
        }
        //颜色反转，如果当前节点的左子节点，右子节点都为红色，需要颜色反转
        if(isRed(h.left) && isRed(h.left)){
            filpColors(h);
        }


        return h;
    }

    //根据key从树中找出对应的值
    public Value get(Key key){
        Value value = get(root, key);
        return value;
    }

    //从指定的树中找到key对应的值  查找的算法与二叉查找树一模一样
    private Value get(Node x ,Key key){
        if(x == null){
            return null;
        }

        int cmp = key.compareTo(x.key);
        if(cmp>0){
            return get(x.right,key);
        }else if(cmp<0){
            return get(x.left,key);
        }else{
            return x.value;
        }

    }

    //获取树中元素的个数
    public int size(){
        return N;
    }



}
