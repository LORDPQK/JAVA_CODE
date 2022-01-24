package linear;

import java.util.Iterator;

//栈是一种逻辑结构，要用物理结构将其实现，物理结构（顺序存储，链式存储）
public class Stack <T> implements Iterable<T>{
    //记录首节点
    private Node head;
    //栈中元素个数
    private int N;



    private class Node{
        public T item;
        public Node next;

        public Node(T item , Node next){
            this.item = item;
            this.next = next;
        }
    }


    public Stack(){
        this.head = new Node(null,null);
        this.N = 0;
    }

    //判断栈中元素个数是否为0
    public boolean isEmpty(){
        return N==0;
    }

    //获取栈中元素的个数
    public int size(){
        return  N;

    }

    //把t元素压入栈
    public void push(T t){
        //找到首节点指向的第一个节点
        Node oldfirst = head.next;
        //创建新节点
        Node newnode =new Node(t,null);
        //让首节点指向新节点
        head.next = newnode;
        //让新节点指向原来的第一个节点
        newnode.next=oldfirst;
        //元素个数+1
        N++;

    }

    //把t元素弹出栈
    public T pop(){
       Node oldFirst = head.next;
       if(oldFirst==null){
           return null;
       }
       head.next = oldFirst.next;
       N--;
       return oldFirst.item;
    }


    public Iterator<T> iterator() {
        return new SIterator();
    }

    private  class SIterator implements Iterator{

        private Node n;

        public SIterator(){
            this.n = head;
        }

        public boolean hasNext() {
            return n.next!=null;
        }

        public Object next() {
            n =n.next;
            return n.item;
        }

        public void remove() {

        }
    }

}
