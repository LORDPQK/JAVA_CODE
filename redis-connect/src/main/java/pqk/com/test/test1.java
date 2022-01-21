package pqk.com.test;

import org.junit.Test;
import redis.clients.jedis.Jedis;

public class test1 {
    @Test
    public void test(){
        // 1.连接Redis
        Jedis jedis = new Jedis("192.168.2.19",6379);
        // 2.操作Redis
        jedis.set("name1","松吹君");
        // 3.关闭Redis
        jedis.close();
    }
}
