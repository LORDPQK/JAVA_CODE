package controller;

import cn.dsna.util.images.ValidateCode;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/code")
public class CodeController extends BaseServlet{
    public static void createCode(HttpServletRequest request , HttpServletResponse response) throws IOException {

        //生成验证码对象
        ValidateCode validateCode = new ValidateCode(100,35,5,20);

        //将对象放入session对象中
        String code = validateCode.getCode();
        request.getSession().setAttribute("code",code);

        //向页面写回验证码
        ServletOutputStream outputStream = response.getOutputStream();
        validateCode.write(outputStream);
    }

}
