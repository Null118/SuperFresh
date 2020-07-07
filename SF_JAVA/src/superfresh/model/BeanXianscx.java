package superfresh.model;

import java.util.Date;

public class BeanXianscx {
    private int cx_id;
    private int com_id;
    private float cx_price;
    private float cx_count;
    private Date cx_start_day;
    private Date cx_end_day;
    
    
	public int getCx_id() {
		return cx_id;
	}
	public void setCx_id(int cx_id) {
		this.cx_id = cx_id;
	}
	public int getCom_id() {
		return com_id;
	}
	public void setCom_id(int com_id) {
		this.com_id = com_id;
	}
	public float getCx_price() {
		return cx_price;
	}
	public void setCx_price(float cx_price) {
		this.cx_price = cx_price;
	}
	public float getCx_count() {
		return cx_count;
	}
	public void setCx_count(float cx_count) {
		this.cx_count = cx_count;
	}
	public Date getCx_start_day() {
		return cx_start_day;
	}
	public void setCx_start_day(Date cx_start_day) {
		this.cx_start_day = cx_start_day;
	}
	public Date getCx_end_day() {
		return cx_end_day;
	}
	public void setCx_end_day(Date cx_end_day) {
		this.cx_end_day = cx_end_day;
	}
    
}
