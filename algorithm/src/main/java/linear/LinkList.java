package linear;

//单向链表
public class LinkList <T>{
    //记录头结点
    private Node head;
    //记录链表的长度
    private int N;

    //节点类
    private class Node{
        //数据域
        T item;
        //指针域，指向下一个节点
        Node next;

        public Node(T item,Node next){
            this.item = item;
            this.next = next;
        }
    }

    public LinkList(){
        //初始化头节点
        this.head = new Node(null,null);
        //初始化元素个数
        this.N = 0 ;
    }

    //清空链表
    public void clear(){
        this.head.next =null;
    }

    //返回链表长度
    public int length(){
        return N;
    }

    //判读链表是否为空
    public boolean isEmpty(){
        return N==0;
    }

    //获取指定位置i出的元素
    public T get(int i){
        Node node  = head.next;
        for(int j=0;j<i;j++){
            node = node.next;
        }
        return node.item;
    }

    //向链表中添加元素t
    public void insert(T t){
        //找到当前的最后一个节点
        Node n =head;
        while (n.next!=null){
            n=n.next;
        }

        //创建新节点，保存元素t
        Node newNode = new Node(t,null);
        //让最后一个节点指向新节点
        n.next =  newNode;
        //元素的个数加1
        N++;
    }

    //向指定位置i处，添加元素t
    public void insert(int i , T t){
        //找到i位置的前一个节点
        Node pre =head;
        for(int index = 0; index<=i-1;i++){
            pre = pre.next;
        }
        //找到i位置的节点
        Node curr = pre.next;
        //创建新节点，并且新节点需要指向原来i位置的节点
        Node newNode  = new Node(t,curr);
        //原来i位置的前一个节点指向新节点即可
        pre.next=newNode;
        //元素的个数加1
        N++;
    }

    //删除指定位置处的节点，并返回删除的元素
    public T remove(int i){
        //找到i位置的前一个节点
        Node pre = head;
        for(int index =0; index<=i-1;index++){
            pre=pre.next;
        }
        //要找到i位置的节点
        Node curr = pre.next;
        //找到i位置的下一个节点
        Node nextNode = curr.next;
        //前一个节点指向下一个节点
        pre.next=nextNode;
        //元素个数-1
        N--;
        return curr.item;
    }

    //查找元素t在链表中第一次出现的位置
    public int indexOf(T t){
        Node n =head;
        for (int i=0;n.next!=null;i++){
            n=n.next;
            if(n.item.equals(t)){
                return i;
            }
        }
        return -1;
    }

    //反转链表
    public void reverse(){
        if(isEmpty()){
            return;
        }
        reverse(head.next);
    }

    //反转指定节点，并把反转后的节点返回
    public Node reverse(Node curr){
        if(curr.next == null){
            head.next = curr;
            return curr;
        }

        //递归反转当前节点的下一个结点；返回值就是链表反转后，当前结点的上一个结点
        Node pre = reverse(curr);
        //让返回的结点的next指向curr
        pre.next = curr;
        //把当前结点的下一个结点变为null
        curr.next = null;
        return curr;
    }



}

