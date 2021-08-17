package controller;

import com.google.gson.Gson;
import entity.Type;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.TypeService;
import service.impl.TypeServiceImpl;

import java.sql.SQLException;
import java.util.List;

@WebServlet(value = "/type")
public class TypeController extends BaseServlet{

    public static String findAll(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        TypeService typeService = new TypeServiceImpl();
        List<Type> types = typeService.findAll();

        Gson gson = new Gson();
        String json = gson.toJson(types);
        return json;
    }
}
