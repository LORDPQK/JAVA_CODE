package Service1;
import DEOM1.student;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class service {
public static void main(String[] args)throws Exception{
    //1创建对象流
    FileOutputStream fos=new FileOutputStream("stu.bin");
    ObjectOutputStream oos=new ObjectOutputStream(fos);
    //2序列化
    student zhangsan=new student("张三",20);
    student lisi=new student("丽四",20);
    ArrayList<student> list = new ArrayList<>();
    list.add(zhangsan);
    list.add(lisi);
//    oos.writeObject(zhangsan);
//   oos.writeObject(lisi);
    oos.writeObject(list);
    //3关闭
    oos.close();
    System.out.println("序列化完毕");
}

}
