package Service1;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpFileService {
    public static void main(String[] args) throws Exception{
        //创建ServiceSocket
        ServerSocket listener =new ServerSocket(9999);
        //侦听，接收客户端请求
        System.out.println("服务器已启动。。。。");
        Socket socket=listener.accept();
        //获取输入流
        InputStream is=socket.getInputStream();
        //边读取边保存
        FileOutputStream fos = new FileOutputStream("2.jpg");
        byte []buf=new byte[1024*4];
        int count=0;
        while ((count=is.read(buf))!=-1)
        {
            fos.write(buf,0,count);
        }
        //关闭
        fos.close();
        is.close();
        socket.close();
        listener.close();
        System.out.println("接收完毕！ ");
    }
}
