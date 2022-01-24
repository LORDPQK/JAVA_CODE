package linear;

public class SymbolTable<Key,Value>{
    //记录首节点
    private Node head;
    //记录符号表中元素的个数
    private int N;

    private class Node{
        public Key key;
        public Value value;
        public Node next;


        public Node(Key key,Value value,Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public SymbolTable(){
        this.head = new Node(null,null,null);
        this.N = 0;
    }

    //获取符号表中键值对的个数
    public int size(){
        return N;
    }

    public void put(Key key,Value value){
        //符号表中已经存在了键为key的键值对，那么只要找到该节点，替换值为value即可
        Node n =head;
        while (n.next!=null){
            n = n.next;
            if(n.key.equals(key)){
                n.value =value;
                return;
            }
        }

        //不存在，创建新节点，头插法即可
        Node newNode = new Node(key,value,null);
        Node oldFirst = head.next;
        newNode.next = oldFirst;
        head.next = newNode;

        N++;
    }

    public void delete(Key key){
        //找到键为key的节点，把他从链表中删除
        Node n =head;
        while(n.next!=null){
            if(n.next.key.equals(key)){
                n.next = n.next.next;
                N--;
                return;
            }
        }
    }

    public Value get(Key key){
        Node n =head;
        while (n.next!=null){
            n = n.next;
            if(n.key.equals(key)){
                return n.value;
            }
        }
       return null;
    }
}
