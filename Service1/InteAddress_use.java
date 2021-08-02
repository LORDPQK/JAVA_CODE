package Service1;

import java.net.InetAddress;

public class InteAddress_use  {
    public static void main(String[] args) throws Exception{
        //创建本机IP地址对象
        //1.1getLocalHost方法
        InetAddress ia1 = InetAddress.getLocalHost();
        System.out.println("ip地址"+ia1.getHostAddress()+"主机名"+ia1.getHostName());

        //1.2 getByName("ip地址")
        InetAddress ia2 = InetAddress.getByName("192.168.23.1");
        System.out.println("ip地址"+ia2.getHostAddress()+"主机名"+ia2.getHostName());

        //1.3 getByName("127.0.0.1")
        InetAddress ia3 = InetAddress.getByName("127.0.0.1");
        System.out.println("ip地址"+ia3.getHostAddress()+"主机名"+ia3.getHostName());

        //1.4 getByName("localhost")
        InetAddress ia4 = InetAddress.getByName("localhost");
        System.out.println("ip地址"+ia4.getHostAddress()+"主机名"+ia4.getHostName());

        //2创建局域网IP地址对象  "192.168.23.3"随便打的不存在
        InetAddress ia5 = InetAddress.getByName("192.168.23.3");
       // System.out.println("ip地址"+ia5.getHostAddress()+"主机名"+ia5.getHostName());
       // System.out.println("2秒内是否可达"+ia5.isReachable(2000));

        //创建外网ip地址对象
        InetAddress ia6=InetAddress.getByName("www.bilibili.com");
        System.out.println("ip地址"+ia6.getHostAddress()+"主机名"+ia6.getHostName());
        System.out.println("2秒内是否可达"+ia6.isReachable(2000));

        //获取所有的ip地址
        InetAddress[] ias = InetAddress.getAllByName("www.bilibili.com");
        for (InetAddress inetaddress:ias) {
            System.out.println(inetaddress);
        }

    }
}
