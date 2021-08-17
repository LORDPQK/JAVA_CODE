package filter;

import entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.UserService;
import service.impl.UserServiceImpl;
import util.Base64Utils;
import util.Constants;

import javax.annotation.processing.Filer;
import java.io.IOException;

@WebFilter(value = "/login.jsp")  //默认情况下过滤器只过滤普通请求和重定向
public class AutoFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException { ServletRequest servletRequest1 = servletRequest;

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        Cookie[] cookies = request.getCookies();

        if(cookies!=null){
            //本地存储了cookies
            String content = null; //接收储存账号密码的数据

            for (Cookie c:cookies) {
                //如果找到自动登录的cookies的名字就读取储存的账号的用户名密码
                if(c.getName().equals(Constants.AUTO_NAME)){
                    content = c.getValue();
                }
            }
            if(content!=null){
                //读取到了存储密码和用户名的cookies
                content = Base64Utils.decode(content);
                String[] split = content.split(Constants.FLAG);

                String username = split[0];
                String password = split[1];

                UserService userService = new UserServiceImpl();
                try {
                    User user = userService.login(username, password);
                    if(user!=null){
                        //可以自动登录
                        HttpSession session = request.getSession();
                        session.setAttribute("loginUser",user);
                        HttpServletResponse response = (HttpServletResponse) servletResponse;
                        response.sendRedirect(request.getContextPath()+"/index.jsp");

                    }else{
                        //不能自动登录
                        filterChain.doFilter(servletRequest,servletResponse);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                //没有读取到
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }else{
            //本地没有cookies放行即可
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }
}
