package Service1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class dtf_sue {
    public static void main(String[] args) {
        //创建DateTimeFormater
        DateTimeFormatter dtf= DateTimeFormatter.ofPattern("YYYY/MM/dd HH:MM:SS");
        //(1)把时间格式化为字符串
        String format =dtf.format(LocalDateTime.now());
        System.out.println(format);
        //(2)把字符串解析为时间
        LocalDateTime localDateTime =LocalDateTime.parse("2020/5/24 10:20:35",dtf);
        System.out.println(localDateTime);
    }
}
