package Service1;

import java.io.*;
import java.util.Properties;
import java.util.Set;

public class Properties_use {
    public static void main(String[] args) throws IOException {
        //1.创建集合
        Properties properties = new Properties();
        //2.添加数据
        properties.setProperty("username","zhangsan");
        properties.setProperty("age","20");
        System.out.println(properties.toString());
        //3.遍历
        //3.1   keySet
        //3.2   entrySet
        //3.3   stringPropertyNames()
        Set<String> pronames = properties.stringPropertyNames();
        for (String pro:pronames) {
            System.out.println(pro+"===="+properties.getProperty(pro));
        }
        //和流有关的方法

        // 1、 list  将属性列表输出到指定的输出流。
      /*
        PrintWriter pw = new PrintWriter("prpperties.txt");
        properties.list(pw);
        pw.close();
       */


        // 2、 store  以适合使用 load(InputStream) 方法加载到 Properties 表中的格式，将此 Properties 表中的属性列表（键和元素对）写入输出流。
       /*
        FileOutputStream fos = new FileOutputStream("out.properties");
        properties.store(fos,"注释");
        fos.close();
        */

        //3、  lord 加载
        Properties properties2 = new Properties();
        FileInputStream fis =new FileInputStream("out.properties");
        properties2.load(fis);
        fis.close();
        System.out.println(properties2.toString());
    }
}
