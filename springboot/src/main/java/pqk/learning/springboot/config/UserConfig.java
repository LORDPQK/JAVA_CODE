package pqk.learning.springboot.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pqk.learning.springboot.entity.User;

@Configuration  //相当于<beans/>
public class UserConfig {

    @Bean    //构建一个实例放到spring容器中
    //@Bean(name = "user1")  //指定<bean>的id
    public User user(){
        User user = new User();
        user.setId(1);
        user.setName("迪卢克");
        return user;
    }


}
