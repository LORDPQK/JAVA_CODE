package entity;
/*对应数据库的订单项
create table item
(
   i_id                 int not null auto_increment comment '订单项的唯一标识',
   p_id                 int comment '商品的唯一主键',
   o_id                 varchar(64) comment '订单编号是字符串',
   i_count              decimal(12,2) comment '订单项的小计',
   i_num                int comment '订单项的数量',
   primary key (i_id)
);
 */

import java.io.Serializable;
import java.math.BigDecimal;

public class Item implements Serializable {
    private static final long serialVersionUID = 1L;
    private int iid;//订单项主键
    private int pid;//商品主键
    private String oid;//订单主键
    private BigDecimal icount;//订单项小计
    private int inum;//订单项的数量
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) { this.oid = oid; }

    public BigDecimal getIcount() {
        return icount;
    }

    public void setIcount(BigDecimal icount) {
        this.icount = icount;
    }

    public int getInum() {
        return inum;
    }

    public void setInum(int inum) {
        this.inum = inum;
    }

    @Override
    public String toString() {
        return "Item{" +
                "iid=" + iid +
                ", pid=" + pid +
                ", oid=" + oid +
                ", icount=" + icount +
                ", inum=" + inum +
                '}';
    }
}
