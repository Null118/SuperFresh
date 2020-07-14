package superfresh.model;

import java.sql.Timestamp;
import java.text.DecimalFormat;

public class BeanOrders {
	public static final String[] tableTitles={"订单编号","优惠券编号","地址编号","用户编号","总金额","实付金额","到达时间 ","订单状态"};
    private int ord_id;
    private int cou_id;
    private int add_id;
    private int user_id;
    private double ord_init_jine;
    private double ord_sum_jine;
    private Timestamp ord_ttl;
    private String ord_situ;
    public static int ord_cnt;
	public static int getOrd_cnt() {
	return ord_cnt;
}
public void setOrd_cnt(int ord_cnt) {
	this.ord_cnt = ord_cnt;
}
	public int getOrd_id() {
		return ord_id;
	}
	public void setOrd_id(int ord_id) {
		this.ord_id = ord_id;
	}
	public int getCou_id() {
		return cou_id;
	}
	public void setCou_id(int cou_id) {
		this.cou_id = cou_id;
	}
	public int getAdd_id() {
		return add_id;
	}
	public void setAdd_id(int add_id) {
		this.add_id = add_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public double getOrd_init_jine() {
		return ord_init_jine;
	}
	public void setOrd_init_jine(double ord_init_jine) {
		this.ord_init_jine = ord_init_jine;
	}
	public double getOrd_sum_jine() {
		return ord_sum_jine;
	}
	public void setOrd_sum_jine(double ord_sum_jine) {
		this.ord_sum_jine = ord_sum_jine;
	}
	public Timestamp getOrd_ttl() {
		return ord_ttl;
	}
	public void setOrd_ttl(Timestamp ord_ttl) {
		this.ord_ttl = ord_ttl;
	}
	public String getOrd_situ() {
		return ord_situ;
	}
	public void setOrd_situ(String ord_situ) {
		this.ord_situ = ord_situ;
	}
	public String getCell(int col){
		DecimalFormat df = new DecimalFormat("##0.00");
		if(col==0) return String.valueOf(ord_id);
		else if(col==1) return String.valueOf(cou_id);
		else if(col==2) return String.valueOf(add_id);
		else if(col==3) return String.valueOf(user_id);
		else if(col==4) return String.valueOf(new DecimalFormat("0.00").format(ord_init_jine));
		else if(col==5) return String.valueOf(new DecimalFormat("0.00").format(ord_sum_jine));
		else if(col==6) return String.valueOf(ord_ttl);
		else if(col==7) return ord_situ;
		else return "";
	}
}
