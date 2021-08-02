package Service1;

import java.util.ArrayList;
import java.util.UUID;

public class Stream_use {
    public static void main(String[] args) {
        //串行流和并行流的区别
        ArrayList<String>list = new ArrayList<>();
        for(int i=0;i<500000;i++){
            list.add(UUID.randomUUID().toString());//UUID.randomUUID生成随机字符串

        }
        //
        long Starttime=System.currentTimeMillis();
      //  long count=list.parallelStream().sorted().count();//并行
        //串行
        long count=list.stream().sorted().count();
        System.out.println(count);
        long endtime=System.currentTimeMillis();
        System.out.println("运行时间为"+(endtime-Starttime));
    }
}
