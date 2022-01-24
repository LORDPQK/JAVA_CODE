import linear.Queue;

public class Queuetest {
    public static void main(String[] args) {
        //创建队列对象
        Queue<String> queue = new Queue<String>();

        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");
        queue.enqueue("d");


        for (String s:queue) {
            System.out.println(s);
        }


        System.out.println("-------------------------------");

        String re = queue.dequeue();
        System.out.println("出队列的元素是："+re);

        System.out.println("剩余的元素个数是："+ queue.size());
    }
}
