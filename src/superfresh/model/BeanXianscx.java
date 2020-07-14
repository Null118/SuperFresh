package superfresh.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BeanXianscx {
    private int cx_id;
    private int com_id;
    private double cx_price;
    private int cx_count;
    private Date cx_start_day;
    private Date cx_end_day;
    public static final String[] tableTitles={"促销编号","商品编号","促销价格","促销件数","起始日期","结束日期"};
    
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
	public double getCx_price() {
		return cx_price;
	}
	public void setCx_price(double cx_price) {
		this.cx_price = cx_price;
	}
	public int getCx_count() {
		return cx_count;
	}
	public void setCx_count(int cx_count) {
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
	public String getCell(int col){
		if(col==0) return String.valueOf(cx_id);
		else if(col==1) return String.valueOf(com_id);
		else if(col==2) return String.valueOf(cx_price);
		else if(col==3) return String.valueOf(cx_count);
		else if(col==4) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
			return format.format(cx_start_day); 
		}
		else if(col==5) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
			return format.format(cx_end_day); 
		}
		else return "";
	}
}
