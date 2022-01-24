package linear;

import java.util.Iterator;

public class Queue <T> implements Iterable<T>{

    private Node head;
    private int N;
    private Node last;



    private class Node{
        public T item;
        public Node next;

        public  Node(T item , Node next){
            this.item = item;
            this.next = next;
        }
    }

    public  Queue(){
        this.head = new Node(null,null);
        this.last = null;
        this.N = 0;

    }

    //判断队列是否为空
    public boolean isEmpty(){
        return N==0;
    }

    //返回队列中元素个数
    public int size(){
        return  N;
    }

    //向队列中插入元素t
    public void enqueue(T t){
        //当前尾节点为null
        if(last == null){
            last = new Node(t,null);
            head.next = last;
        }else{
            //尾节点不为null
            Node oldlast = last;
            last = new Node(t,null);
            oldlast.next=last;
        }

        N++;
    }

    //队列中弹出元素
    public T dequeue(){

        if(isEmpty()){
            return null;
        }

        Node out = head.next;
        head.next = head.next.next;
        N--;

        if(isEmpty()){
            last=null;
        }

        return out.item;
    }


    @Override
    public Iterator<T> iterator() {
        return new QIterator();
    }

    public class  QIterator implements Iterator{

        private Node n;

        public QIterator(){
            this.n = head;
        }

        @Override
        public boolean hasNext() {
            return n.next!=null;
        }

        @Override
        public Object next() {
            n =n.next;
            return n.item;
        }
    }



}
