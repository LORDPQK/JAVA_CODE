package ocm.pqn.comtroller.entity;

import java.util.Date;
import java.util.List;

public class User {
   private Integer id;
   private String username;
   private String password;
   private Boolean gender;
   private Date regist_time;

    public User(Integer id, String username, String password, Boolean gender, Date registerTime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.regist_time = registerTime;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Date getRegisterTime() {
        return regist_time;
    }

    public void setRegisterTime(Date registerTime) {
        this.regist_time = registerTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                ", register_time=" + regist_time +
                '}';
    }
}
