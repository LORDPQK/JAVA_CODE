package entity;

import java.io.Serializable;

/*
    对应数据库的用户表

    u_id                 int not null auto_increment comment '用户实体',
    u_name               varchar(20) not null comment '用户账号',
    u_password           varchar(64) not null comment '用户密码',
    u_email              vachar(50) not null comment '用户邮箱',
    u_sex                varchar(4) comment '用户性别',
    u_status             int comment '用户的激活',
    u_code               varchar(64) comment '邮件激活',
    u_role               int comment '用户0管理员1',

 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private int uid;//用户主键
    private String username;//用户名
    private String upassword;//用户密码
    private String email;//用户邮箱对应数据库 u_email
    private String usex;//用户性别
    private String ustatus;//用户激活状态
    private String code;//邮件激活
    private int urole;//用户代码：0 管理员代码：1

    public String getUsername() {
        return username;
    }

    public String getCode() {
        return code;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUid() {
        return uid;
    }

    public String getUpassword() {
        return upassword;
    }

    public String getUsex() {
        return usex;
    }

    public String getUstatus() {
        return ustatus;
    }
    public void setUstatus(String ustatus) {
        this.ustatus = ustatus;
    }
    public int getUrole() {
        return urole;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }
    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }
    public void setUsex(String usex) {
        this.usex = usex;
    }
    public void setUrole(int urole) {
        this.urole = urole;
    }
    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", upassword='" + upassword + '\'' +
                ", email='" + email + '\'' +
                ", usex='" + usex + '\'' +
                ", ustatus=" + ustatus +
                ", code='" + code + '\'' +
                ", urole=" + urole +
                '}';
    }
}
