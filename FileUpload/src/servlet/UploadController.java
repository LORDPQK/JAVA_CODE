package servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import utils.UploadUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UploadController", value = "/upload")
@MultipartConfig(maxFileSize = 1024*1024*100,maxRequestSize = 1024*1024*200)
public class UploadController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        //获得请求的数据
        String username = request.getParameter("username");
        Part part = request.getPart("file1");

        //获取上传的文件的路径 真实路径
        String uploadPath = request.getServletContext().getRealPath("WEB-INF/upload");
        File file = new File(uploadPath);
        if(!file.exists()){
            file.mkdir();
        }

        //文件上传
        //生成唯一文件名
        String oldName = part.getSubmittedFileName();

        List<String> nameList = new ArrayList<>();
        nameList.add(".jpg");
        nameList.add(".png");
        nameList.add(".jpeg");
        String extName = oldName.substring(oldName.lastIndexOf("."));
        if(!nameList.contains(extName)){
            System.out.println("该格式不支持上传");
            return;
        }

        String newName = UploadUtils.NewFileName(oldName);
        //生成二级，三级目录 实现散列存储
        String newPath = UploadUtils.NewFilePath(uploadPath,oldName);
        part.write(newPath+"\\"+newName);

        //打印上传结果
        response.getWriter().println(oldName+"上传成功");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }


}
