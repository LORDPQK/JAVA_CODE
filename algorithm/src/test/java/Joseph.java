//约瑟夫环问题
public class Joseph {
    public static void main(String[] args) {

        // 1 构造循环链表，包含41个结点，分别存储1-41之间的数据
        //记录首结点
        Node<Integer> first = null;
        //记录前一个结点
        Node<Integer> pre = null;

        for(int i =1;i<=41;i++){
            //如果是第一个结点
            if(i==1){
                first = new Node<Integer>(i,null);
                pre = first;
                continue;
            }

            //如果不是第一个结点
            Node<Integer> node = new Node<Integer>(i,null);
            pre.next = node;
            pre = node;

            //如果是最后一个结点，那么需要让最后一个结点的下一个结点变为first,变为循环链表
            if(i==41){
                pre.next=first;
            }

        }

        //2 实现一个计数器，模拟报数
        int count = 0;

        //3 遍历循环链表
        //记录每次遍历拿到的结点，默认从首结点开始
        Node<Integer> n = first;
        //记录当前结点的上一个结点
        Node<Integer> before = null;
        while (n!=n.next){
            //模拟报数
            count++;
            //判断当前报数是不是3
            if(count ==3){
                //如果是3，则把当前结点删除，打印当前结点，重置count =0;让指向当前结点的指针n后移
                before.next = n.next;
                System.out.println(n.item);
                count = 0;
                n = n.next;
            }else {
                //如果不是3,让before指向当前结点，让指向当前结点的指针n后移
                before = n;
                n = n.next;
            }
        }
        System.out.println(n);
    }

    //节点类
    private static class Node<T>{
        //数据域
        T item;
        //指针域，指向下一个节点
        Node next;

        public Node(T item, Node next){
            this.item = item;
            this.next = next;
        }
    }
}
