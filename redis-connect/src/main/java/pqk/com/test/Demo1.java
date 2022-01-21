package pqk.com.test;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.util.SerializationUtils;
import pqk.com.entity.User;
import redis.clients.jedis.Jedis;

import java.io.PrintStream;
import java.util.Date;

public class Demo1 {
    //存储对象 - 以byte[]形式存储在Redis中
    @Test
    public void setByteArray(){
        //1. 连接Redis服务
        Jedis jedis = new Jedis("192.168.2.19",6379);

        //2.1准备Key(String)-value(User)
        String key ="user";
        User value = new User(1,"张三",new Date());
        //2.2将key value转换为byte[]
        byte[] bytekey = SerializationUtils.serialize(key);
        byte[] bytevalue = SerializationUtils.serialize(value);

        //2.3将其存入Redis
        jedis.set(bytekey,bytevalue);

        //3释放资源
        jedis.close();
    }

    //获取对象 - 以byte[]形式存储在Redis中
    @Test
    public void getByteArray(){
        //1. 连接Redis服务
        Jedis jedis = new Jedis("192.168.2.19",6379);

        //2.1准备Key
        String key ="user";

        //2.2将key转换为byte[]
        byte[] bytekey = SerializationUtils.serialize(key);


        //2.3通过jedis去redis中获取
        byte[] value = jedis.get(bytekey);

        //2.4将value反序列化
        User user = (User) SerializationUtils.deserialize(value);

        //2.5输出
        System.out.println(user);

        //3释放资源
        jedis.close();
    }

    //存储对象 - 以String形式存储在Redis中
    @Test
    public void setString(){
        //1. 连接Redis服务
        Jedis jedis = new Jedis("192.168.2.19",6379);

        //2.1准备Key(String)-value(User)
        String key ="Stringuser";
        User value = new User(2,"李四",new Date());

        //2.2使用fastJSON将value转化为json字符串
        String stringValue = JSON.toJSONString(value);

        //2.3将其存入Redis
        jedis.set(key,stringValue);

        //3释放资源
        jedis.close();
    }

    //获取对象 - 以String形式
    @Test
    public void getString(){
        //1. 连接Redis服务
        Jedis jedis = new Jedis("192.168.2.19",6379);

        //2.1准备Key(String)-value(User)
        String key ="Stringuser";

        //2.2去Redis中查询value
        String value = jedis.get(key);

        //2.3将value反序列化为一个String
        User user = JSON.parseObject(value, User.class);
        System.out.println(user);
        //3释放资源
        jedis.close();
    }
}
