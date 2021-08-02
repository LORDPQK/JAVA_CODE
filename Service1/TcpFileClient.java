package Service1;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TcpFileClient {
    public static void main(String[] args) throws Exception{
        //创建Socket
        Socket socket= new Socket("192.168.23.1",9999);
        //获取输出流
        OutputStream os=socket.getOutputStream();
        //边读取边发送
        FileInputStream fis=new FileInputStream("1.jpg");
        byte[] buf=new byte[1024*4];
        int count =0;
        while(((count=fis.read(buf))!=-1))
        {
            os.write(buf,0,count);
        }
        //关闭
        fis.close();
        os.close();
        socket.close();
        System.out.println("发送完毕");
    }
}
