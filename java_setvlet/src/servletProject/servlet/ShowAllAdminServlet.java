package servletProject.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servletProject.entity.Admin;
import servletProject.service.AdminService;
import servletProject.service.AdminServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(value = "/showall")
public class ShowAllAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        AdminService adminService =new AdminServiceImpl();
        List<Admin> adminList =adminService.showAllAdmin();

        PrintWriter printWriter =resp.getWriter();
        if(adminList!=null){
            printWriter.println("<html>");
            printWriter.println("<head>");
            printWriter.println("<meta charset=’UTF-8‘");
            printWriter.println("<title>显示所有</title>");
            printWriter.println("<body>");
            printWriter.println("<table>");
            printWriter.println("   <tr>");
            printWriter.println("       <td>");
            printWriter.println("username");
            printWriter.println("       </td>");
            printWriter.println("       <td>");
            printWriter.println("password");
            printWriter.println("       </td>");
            printWriter.println("       <td>");
            printWriter.println("phone");
            printWriter.println("       </td>");
            printWriter.println("       <td>");
            printWriter.println("address");
            printWriter.println("       </td>");
            for(Admin admin :adminList){
                printWriter.println("       <td>");
                printWriter.println(admin.getUsername());
                printWriter.println("       </td>");
                printWriter.println("       <td>");
                printWriter.println(admin.getPassword());
                printWriter.println("       </td>");
                printWriter.println("       <td>");
                printWriter.println(admin.getPhone());
                printWriter.println("       </td>");
                printWriter.println("       <td>");
                printWriter.println(admin.getAddress());
                printWriter.println("       </td>");
            }
            printWriter.println("   </tr>");
            printWriter.println("</table>");
            printWriter.println("</body>");
            printWriter.println(("</head>"));
            printWriter.println("</html>");
        }else {
            printWriter.println("<html>");
            printWriter.println("<head>");
            printWriter.println("<meta charset=’UTF-8‘");
            printWriter.println("<title>显示所有</title>");
            printWriter.println("<body>");
            printWriter.println("当前没有用户");
            printWriter.println("</body>");
            printWriter.println(("</head>"));
            printWriter.println("</html>");
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
