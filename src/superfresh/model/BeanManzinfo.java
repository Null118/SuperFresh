package superfresh.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BeanManzinfo {
	private int mz_id;
	private int com_id;
	private String mz_what;
	private int mz_count;
	private double mz_discount;
	private Date mz_start_day;
	private Date mz_end_day;
	public static final String[] tableTitles={"满折编号","商品编号","内容说明","满折件数","优惠折扣","开始日期","结束日期"};
    
	
	public int getCom_id() {
		return com_id;
	}
	public void setCom_id(int com_id) {
		this.com_id = com_id;
	}
	public int getMz_id() {
		return mz_id;
	}
	public void setMz_id(int mz_id) {
		this.mz_id = mz_id;
	}
	public String getMz_what() {
		return mz_what;
	}
	public void setMz_what(String mz_what) {
		this.mz_what = mz_what;
	}
	public int getMz_count() {
		return mz_count;
	}
	public void setMz_count(int mz_count) {
		this.mz_count = mz_count;
	}
	public double getMz_discount() {
		return mz_discount;
	}
	public void setMz_discount(double mz_discount) {
		this.mz_discount = mz_discount;
	}
	public Date getMz_start_day() {
		return mz_start_day;
	}
	public void setMz_start_day(Date mz_start_day) {
		this.mz_start_day = mz_start_day;
	}
	public Date getMz_end_day() {
		return mz_end_day;
	}
	public void setMz_end_day(Date mz_end_day) {
		this.mz_end_day = mz_end_day;
	}
	public String getCell(int col){
		if(col==0) return String.valueOf(mz_id);
		if(col==1) return String.valueOf(com_id);
		if(col==2) return mz_what;
		else if(col==3) return String.valueOf(mz_count);
		else if(col==4) return String.valueOf(mz_discount);
		else if(col==5) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
			return format.format(mz_start_day); 
		}
		else if(col==6) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
			return format.format(mz_end_day);
		}
		else return "";
	}
}
