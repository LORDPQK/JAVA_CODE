package Service1;

import DEOM1.student;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/*
使用ObjectInputStream实现反序列化（读取构成对象）
* */
public class service1_2 {
    public static void main(String[] args)throws Exception{
        //1创建对象流
        FileInputStream fis =new FileInputStream("stu.bin");
        ObjectInputStream ois=new ObjectInputStream(fis);
        //读取文件(反序列化)
//        student s=(student)ois.readObject();
//        student s2=(student)ois.readObject();
        ArrayList<student> list=(ArrayList<student>)ois.readObject();
        //close the file
        ois.close();
        System.out.println("反序列化完成");
//        System.out.println(s.toString());
//        System.out.println(s2.toString());
        System.out.println(list.toString());
    }
}











