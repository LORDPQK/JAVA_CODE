package servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import utils.UploadUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "MoreUploadController", value = "/moreUpload")
@MultipartConfig(maxRequestSize = 1024*1024*100,maxFileSize = 1024*1024*200)
public class MoreUploadController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String basePath = request.getServletContext().getRealPath("/WEB-INF/upload");
        File file = new File(basePath);
        if(!file.exists()){
            file.mkdirs();
        }

        //提交表单得所有数据
        Collection<Part> parts =request.getParts();

        for(Part part : parts){
            String filename = part.getSubmittedFileName();
            if(filename!=null){
                //是一个文件
                String newName = UploadUtils.NewFileName(filename);
                String newPath = UploadUtils.NewFilePath(basePath,filename);
                part.write(newPath+"\\"+newName);
            }else {
                String username = request.getParameter(part.getName());
                System.out.println(username);
            }
        }
    }
}
