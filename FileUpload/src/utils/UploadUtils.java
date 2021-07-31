package utils;

import java.io.File;
import java.util.UUID;

public class UploadUtils {
    //使用UUID生成唯一标识，拼接上图片的名称得到唯一文件名
    public static String NewFileName(String filename){
        return UUID.randomUUID().toString().replaceAll("-","")+"_"+filename;
    }

    //散列存储
    public static String NewFilePath(String basePath,String filename){
        int hashcode = filename.hashCode();
        int path1 = hashcode & 15;
        int path2 = (hashcode>>4) & 15;
        String newPath = basePath+"\\"+path1+"\\"+path2;
        File file = new File(newPath);
        if(!file.exists()){
            file.mkdirs();
        }
        return newPath;//返回新路径
    }
}
