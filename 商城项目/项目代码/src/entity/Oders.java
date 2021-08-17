package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/*
    对应数据库的订单表
    create table orders
(
   o_id                 varchar(64) not null auto_increment comment '订单编号是字符串',
   a_id                 int comment '地址实体的唯一主键',
   u_id                 int comment '用户实体',
   o_count              decimal(12,2) comment '订单的总金额',
   o_time               datetime comment '订单的详细时间',
   o_state              int comment 1付款待发货2发货待收货3收货待评价4订单完成5退货中
   o_detail             varchar(200) comment '订单收货地址',
   primary key (o_id)
);
 */
public class Oders implements Serializable {
    public static final long serialVersionUID = 1L;

    private String oid;//订单主键
    private int aid;//地址主键
    private Address address;
    private int uid;//用户主键
    private BigDecimal ocount;//订单总金额
    private Date otime;//订单的详细时间
    private int ostate;//1付款待发货2发货待收货3收货待评价4订单完成5退货中
    private String odetail;//订单收货地址
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public BigDecimal getOcount() {
        return ocount;
    }

    public void setOcount(BigDecimal ocount) {
        this.ocount = ocount;
    }

    public Date getOtime() {
        return otime;
    }

    public void setOtime(Date otime) {
        this.otime = otime;
    }

    public int getOstate() {
        return ostate;
    }

    public void setOstate(int ostate) {
        this.ostate = ostate;
    }

    public String getOdetail() {
        return odetail;
    }

    public void setOdetail(String odetail) {
        this.odetail = odetail;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Oders{" +
                "oid='" + oid + '\'' +
                ", aid=" + aid +
                ", uid=" + uid +
                ", ocount=" + ocount +
                ", otime=" + otime +
                ", ostate=" + ostate +
                ", odetail='" + odetail + '\'' +
                '}';
    }
}
