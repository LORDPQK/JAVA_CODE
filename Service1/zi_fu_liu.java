package Service1;

import java.io.FileReader;

public class zi_fu_liu {
    public static void main(String[] args)throws Exception {
        FileReader fr=new FileReader("hallo.txt");

        //单个字节读取
        /*int data;
        while((data= fr.read())!=-1)
        {
            System.out.print((char)data);
        }

         */

        //创建缓冲区读取
        char[] buf =new char[1];
        int count=0;
        while((count= fr.read(buf))!=-1){
            System.out.println(new String(buf,0,count));
        }
        fr.close();
    }
}
