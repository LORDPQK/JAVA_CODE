package tree;

//二叉查找树的实现及API
public class BinaryTree<Key extends Comparable<Key>,Value>{
    //记录根节点
    private Node root;
    //记录树中元素的个数
    private int N;

    private class Node{
        //存储键
        private Key key;
        //存储值
        private Value value;
        //记录左子节点
        private Node left;
        //记录右子节点
        private Node right;

        public Node(Key key,Value value,Node left, Node right){
            this.key = key;
            this.value = value;
            this.left =left;
            this.right =right;
        }

    }
    //获取树中元素个数
    public int size(){
        return N;
    }

    //向树中添加元素key-value
    //如果当前树中没有任何一个节点，则直接把新节点当作根节点使用
    //如果当前树不为空
    //如果新节点的key小于当前节点的key，则继续寻找当前节点的左子节点，否则继续寻找当前节点的右子节点；  如果等于，则替换当前节点的value值
    public void put(Key key ,Value value){
        root = put(root,key,value);
    }

    //向指定的树x中添加key-value,并返回添加元素后的新树(最上层返回的就是插入后的root结点)
    public Node put (Node x,Key key, Value value){
        //如果传入的x子树为空
        if(x==null){
            N++;
            return new Node(key,value,null,null);
        }

        //如果x子树不为空
        int cmp = key.compareTo(x.key);
        if(cmp>0){
            //如果key的值大于x结点的键，则继续寻找x的右子树
            x.right = put(x.right,key,value);
        }else if(cmp<0){
            //如果key的值小于x结点的键，则继续寻找x的左子树
            x.left = put(x.left,key,value);
        }else{
            x.value =value;
        }
        return x;
    }

    //查询树中指定key的value
    public Value get(Key key){
        Value value = get(root, key);
        return value;
    }

    //从指定的树x中，查找key的对应值
    public Value get(Node x ,Key key){
        if(x==null){
            return null;
        }

        //如果x子树不为空
        int cmp = key.compareTo(x.key);
        if(cmp>0){
            //如果key的值大于x结点的键，则继续寻找x的右子树
            return get(x.right,key);
        }else if(cmp<0){
            //如果key的值小于x结点的键，则继续寻找x的左子树
            return get(x.left,key);
        }else{
            //找到了键为key的结点，返回该结点
            return  x.value;
        }

    }

    //删除树中的key对应的value
    public void delete(Key key){
        root = delete(root,key);

    }

    //删除指定树x中的key对应的value,并返回删除后的新树
    public Node delete(Node x,Key key){
        if(x==null){
            return null;
        }

        //如果x子树不为空
        int cmp = key.compareTo(x.key);
        if(cmp>0){
            //如果key的值大于x结点的键，则继续寻找x的右子树
            x.right = delete(x.right,key);
        }else if(cmp<0){
            //如果key的值小于x结点的键，则继续寻找x的左子树
            x.left = delete(x.left,key);
        }else{
            //找到了键为key的结点，执行真正的删除操作
            //元素个数-1
            N--;
            //1 找到右子树中最小的结点
            if(x.right== null){
                //如果右子树为空，直接将当前结点的结点返回，作为父结点的新左子树
                return x.left;
            }
            if(x.left == null){
                return x.right;

            }


            //如果左右子树都存在,找到右子树中最小的结点
            Node minNode = x.right;

            while(minNode.left!=null){
                minNode = minNode.left;
            }

            //删除右子树中最小的结点
            Node n =x.right;
            while(n.left!=null){
                if(n.left.left==null){
                    n.left = null;
                }else{
                    n = n.left;
                }
            }

            //让x结点的左子树成为minNode的左子树
            minNode.left = x.left;
            //让x结点的右子树成为minNode的右子树
            minNode.right =x.right;
            //让x结点的父节点指向minNode 只需让指向x的指针指向minNode
            x = minNode;


        }

        return x;
    }

    //查找二叉树中最小的键
    public Key min(){
        return min(root).key;
    }

    //找出指定树x中最小的键所在的结点
    private Node min(Node node) {
        if (node.left != null) {
            node = node.left;
        }
        return node;
    }

    //查找二叉树中最大的键
    public Key max(){
        return max(root).key;
    }

    //找出指定树x中最大键所在的结点
    public Node max(Node node){
        if(node.right!=null){
            node = node.right;
        }
        return node;
    }
}
