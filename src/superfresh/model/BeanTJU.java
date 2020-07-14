package superfresh.model;

import java.text.DecimalFormat;

public class BeanTJU {
	public static final String[] tableTitles={"用户编号","总购买单数","购买总金额","获得总优惠","总评价条数"};
	private int user_id;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	private int sum_ord;
	private double sum_jine;
	private double sum_youhui;
	private int sum_cnt;
	private int sum_pingjia;
	public int getSum_ord() {
		return sum_ord;
	}
	public void setSum_ord(int sum_ord) {
		this.sum_ord = sum_ord;
	}
	public double getSum_jine() {
		return sum_jine;
	}
	public void setSum_jine(double sum_jine) {
		this.sum_jine = sum_jine;
	}
	public double getSum_youhui() {
		return sum_youhui;
	}
	public void setSum_youhui(double sum_youhui) {
		this.sum_youhui = sum_youhui;
	}
	public int getSum_cnt() {
		return sum_cnt;
	}
	public void setSum_cnt(int sum_cnt) {
		this.sum_cnt = sum_cnt;
	}
	public int getSum_pingjia() {
		return sum_pingjia;
	}
	public void setSum_pingjia(int sum_pingjia) {
		this.sum_pingjia = sum_pingjia;
	}
	
	public String getCell(int col){
		if(col==0) return String.valueOf(user_id);
		else if(col==1) return String.valueOf(sum_ord);
		else if(col==2) return String.valueOf(sum_jine);
		else if(col==3) return String.valueOf(sum_youhui);
		else if(col==4) return String.valueOf(sum_pingjia);
		else return "";
	}
}
