package tree;

import linear.Queue;

import java.util.LinkedList;

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



   //前序遍历获得树中所有的键
   public Queue<Key> preErgodic(){
       Queue<Key> keys = new Queue<>();
       preErgodic(root,keys);
       return keys;
   }



   //获取指定树x的所有键，并放到keys队列中
    private void preErgodic(Node x,Queue<Key> keys){
        if(x==null) return;

        //把x节点的key放到keys中
        keys.enqueue(x.key);

        //递归遍历左子树
        if(x.left!=null){
            preErgodic(x.left,keys);
        }

        //递归遍历右子树
        if(x.right!=null){
            preErgodic(x.right,keys);
        }
    }

    //中序遍历获得树中所有的键
    public Queue<Key> midErgodic(){
        Queue<Key> keys = new Queue<>();
        midErgodic(root,keys);
        return keys;
    }

    //获取指定树x的所有键，并放到keys队列中
    private void midErgodic(Node x,Queue<Key> keys){
        if(x==null) return ;

        //递归遍历左子树
        if(x.left!=null){
            midErgodic(x.left,keys);
        }

        //把x节点的key放到keys中
        keys.enqueue(x.key);

        //递归遍历右子树
        if(x.right!=null){
            midErgodic(x.right,keys);
        }
    }

    //层序遍历获取树中的所有键
    public Queue<Key> layerErgodic(){
        //定义两个队列，分别存储树中的键和树中节点
        Queue<Key> keys = new Queue<>();
        Queue<Node> nodes = new Queue<>();

        //默认，往队列中放入根节点
        nodes.enqueue(root);

        while(!nodes.isEmpty()){
            //从队列中弹出一个节点，把key放到keys中
            Node dequeue = nodes.dequeue();
            keys.enqueue(dequeue.key);
            //判断当前节点是否有左子节点，有的话放入nodes队列中
            if(dequeue.left!=null){
                nodes.enqueue(dequeue.left);
            }
            //判断当前节点是否有右子节点，有的话放入nodes队列中
            if(dequeue.right!=null){
                nodes.enqueue(dequeue.right);
            }
        }

        return keys;
    }


    //获取树的深度
    public int maxDeep(){
        return -1;
    }

    //获取指定树的深度
    private int maxDeep(Node node){
        if(node == null){
            return 0;
        }

        int max = 0;
        int maxL= 0;
        int maxR= 0;

        //计算node节点左子树的最大深度,如果左子树不为空，计算左子树的深度，递归出口就是当进行到叶子节点，左右子树都不存在时，maxL = maxR =0 ;
        if(node.left!=null){
            maxL = maxDeep(node.left);
        }
        //计算node节点右子树的最大深度
        if(node.right!=null){
            maxR =maxDeep(node.right);
        }

        //比较左子树最大深度和右子树最大深度，取较大值加1即可，（如果相等就随便去一个值+1）
        max = maxL>maxR?maxL+1:maxR+1;

        return max;

    }


}
