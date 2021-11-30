package ocm.pqn.comtroller.web;

import ocm.pqn.comtroller.entity.User;
import ocm.pqn.comtroller.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.ws.Action;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @GetMapping("/users")
    public String queryUsers(Model model){
        System.out.println("queryUser");
        List<User> users = userService.queryUsers();
        model.addAttribute("users",users);
        return "user";
    }

    @RequestMapping("/users/{id}")
    public String queryOne(@PathVariable Integer id){
        System.out.println("query user id:"+id);
        return "index";
    }

    @PostMapping("/users")
    public String updateUser(User user){
        System.out.println("update User"+user);
        return "index";
    }
}
