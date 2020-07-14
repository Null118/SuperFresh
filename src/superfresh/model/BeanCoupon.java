package superfresh.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BeanCoupon {
    private int cou_id;
    private int user_id;
    public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	private String cou_what;
    private double cou_suit_jine;
    private double cou_dis_jine;
    private Date cou_start_day;
    private Date cou_end_day;
    private int cou_cishu;
    
    public int getCou_cishu() {
		return cou_cishu;
	}
	public void setCou_cishu(int cou_cishu) {
		this.cou_cishu = cou_cishu;
	}
	public static final String[] tableTitles={"优惠券编号","用户编号","优惠券说明","适用金额","优惠金额","开始日期","结束日期","剩余次数"};
    
	public int getCou_id() {
		return cou_id;
	}
	public void setCou_id(int cou_id) {
		this.cou_id = cou_id;
	}
	public String getCou_what() {
		return cou_what;
	}
	public void setCou_what(String cou_what) {
		this.cou_what = cou_what;
	}
	public double getCou_suit_jine() {
		return cou_suit_jine;
	}
	public void setCou_suit_jine(double cou_suit_jine) {
		this.cou_suit_jine = cou_suit_jine;
	}
	public double getCou_dis_jine() {
		return cou_dis_jine;
	}
	public void setCou_dis_jine(double cou_dis_jine) {
		this.cou_dis_jine = cou_dis_jine;
	}
	public Date getCou_start_day() {
		return cou_start_day;
	}
	public void setCou_start_day(Date cou_start_day) {
		this.cou_start_day = cou_start_day;
	}
	public Date getCou_end_day() {
		return cou_end_day;
	}
	public void setCou_end_day(Date cou_end_day) {
		this.cou_end_day = cou_end_day;
	}
	public String getCell(int col){
		if(col==0) return String.valueOf(cou_id);
		if(col==1) return String.valueOf(user_id);
		else if(col==2) return cou_what;
		else if(col==3) return String.valueOf(cou_suit_jine);
		else if(col==4) return String.valueOf(cou_dis_jine);
		else if(col==5) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
			return format.format(cou_start_day); 
		}
		else if(col==6) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
			return format.format(cou_end_day);
		}
		else if(col==7) {
			return String.valueOf(cou_cishu);
		}
		else return "";
	}
}
