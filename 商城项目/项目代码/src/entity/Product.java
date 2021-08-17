package entity;
/*
    对应数据库的商品表
    create table product
(
   p_id                 int not null auto_increment comment '商品的唯一主键',
   t_id                 int comment '类别的主键id',
   p_name               varchar(50) comment '商品的名称',
   p_time               date comment '商品的上市时间',
   p_image              varchar(100) comment '商品图片路径',
   p_price              decimal(12,2) comment '商品的价格',
   p_state              int comment '商品的热门指数',
   p_info               varchar(200) comment '商品的描述',
   primary key (p_id)
);
 */
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Product implements Serializable {
    private static final long serialVersionUID = 1l;
    private int pid;//商品实体主键
    private int tid;//类别实体主键
    private String pname;//商品名
    private Date ptime;//商品上架时间 database date ---> java.util.Data
    private String pimage;//商品图路径
    private BigDecimal pprice;//商品价格
    private int pstate;//商品的热门指数
    private String pinfo;//商品的描述

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Date getPtime() {
        return ptime;
    }

    public void setPtime(Date ptime) {
        this.ptime = ptime;
    }

    public String getPimage() {
        return pimage;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage;
    }

    public BigDecimal getPprice() {
        return pprice;
    }

    public void setPprice(BigDecimal pprice) {
        this.pprice = pprice;
    }

    public int getPstate() {
        return pstate;
    }

    public void setPstate(int pstate) {
        this.pstate = pstate;
    }

    public String getPinfo() {
        return pinfo;
    }

    public void setPinfo(String pinfo) {
        this.pinfo = pinfo;
    }

    @Override
    public String toString() {
        return "Product{" +
                "pid=" + pid +
                ", tid=" + tid +
                ", pname='" + pname + '\'' +
                ", ptime=" + ptime +
                ", pimage='" + pimage + '\'' +
                ", pprice=" + pprice +
                ", pstate=" + pstate +
                ", pinfo='" + pinfo + '\'' +
                '}';
    }
}
