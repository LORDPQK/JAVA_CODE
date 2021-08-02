package Service1;

import java.io.FileWriter;
import java.io.IOException;

public class filewriter {
    public static void main(String[] args) throws IOException {
        FileWriter fw=new FileWriter("Write.txt");
        for(int i=0;i<10;i++)
        {
            fw.write("你所荒废的今日是他人渴求的明日\r\n");
            fw.flush();
        }
        fw.close();
        System.out.println("执行完毕");

    }
}
