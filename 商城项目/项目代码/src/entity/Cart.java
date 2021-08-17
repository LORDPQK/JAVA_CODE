package entity;

/*
数据库对应的购物车表
 */
import java.io.Serializable;
import java.math.BigDecimal;

public class Cart implements Serializable {
    private static final long serialVersionUID = 1L;

    private int cid;//购物车主键
    private int uid;//用户实体主键
    private int pid;//商品主键
    private Product product;
    private int cnum = 0;//购物车商品数
    private BigDecimal ccount;//购物车小计

    public int getCid() {
        return cid;
    }

    public int getUid() {
        return uid;
    }

    public int getPid() {
        return pid;
    }

    public int getCnum() {
        return cnum;
    }

    public BigDecimal getCcount() {
        BigDecimal pprice = product.getPprice();
        BigDecimal bigDecimal = new BigDecimal(cnum);
        return pprice.multiply(bigDecimal);
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setCnum(int cnum) {
        this.cnum = cnum;
    }

    public void setCcount(BigDecimal ccount) {
        this.ccount = ccount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) { this.product = product; }

    @Override
    public String toString() {
        return "Cart{" +
                "cid=" + cid +
                ", uid=" + uid +
                ", pid=" + pid +
                ", cnum=" + cnum +
                ", ccount=" + ccount +
                '}';
    }
}
