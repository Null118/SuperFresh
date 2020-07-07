package superfresh.model;

import java.util.Date;

public class BeanManzinfo {
	private int mz_id;
	private String mz_what;
	private int mz_count;
	private float mz_discount;
	private Date mz_start_day;
	private Date mz_end_day;
	
	
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
	public float getMz_discount() {
		return mz_discount;
	}
	public void setMz_discount(float mz_discount) {
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
	
}
