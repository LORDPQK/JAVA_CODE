package servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import utils.DownLoadUtils;
import utils.UploadUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "FileListController", value = "/FileList")
public class FileListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //解决编码
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        //获取文件列表
        HashMap<String,String> filemap = new HashMap<>();
        String basePath = request.getServletContext().getRealPath("/WEB-INF/upload");
        DownLoadUtils.getFileList(new File(basePath),filemap);

        //转发
        request.setAttribute("map",filemap);
        request.getRequestDispatcher("/fileList.jsp").forward(request,response);
    }
}
