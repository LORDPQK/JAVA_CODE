package entity;

import java.io.Serializable;

/*
    对应数据库的类别表
    create table type
(
   t_id                 int not null auto_increment comment '类别的主键id',
   t_name               varchar(20) not null comment '类别的名称',
   t_info               varchar(200) not null comment '类别的描述',
   primary key (t_id)
);
 */
public class Type implements Serializable {
    private static final long serialVersionUID = 1L;

    private int tid;//类别主键
    private String tname;//类别名称
    private String tinfo;//类别描述

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTinfo() {
        return tinfo;
    }

    public void setTinfo(String tinfo) {
        this.tinfo = tinfo;
    }

    @Override
    public String toString() {
        return "Type{" +
                "tid=" + tid +
                ", tname='" + tname + '\'' +
                ", tinfo='" + tinfo + '\'' +
                '}';
    }
}
