package superfresh.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BeanEvaluate {
    private int com_id;
    private int user_id;
    private String eva_what;
    private Date eva_day;
    private int eva_star;
    private String eva_pic;
    public static final String[] tableTitles={"商品编号","用户编号","评价","评价日期","评价星级"};
    
	public int getCom_id() {
		return com_id;
	}
	public void setCom_id(int com_id) {
		this.com_id = com_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getEva_what() {
		return eva_what;
	}
	public void setEva_what(String cou_what) {
		this.eva_what = cou_what;
	}
	public Date getEva_day() {
		return eva_day;
	}
	public void setEva_day(Date eva_day) {
		this.eva_day = eva_day;
	}
	public int getEva_star() {
		return eva_star;
	}
	public void setEva_star(int eva_star) {
		this.eva_star = eva_star;
	}
	public String getEva_pic() {
		return eva_pic;
	}
	public void setEva_pic(String eva_pic) {
		this.eva_pic = eva_pic;
	}
	public String getCell(int col){
		if(col==0) return String.valueOf(com_id);
		else if(col==1) return String.valueOf(user_id);
		else if(col==2) return eva_what;
		else if(col==3) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
			return format.format(eva_day); 
		}
		else if(col==4) return String.valueOf(eva_star);
		else return "";
	}
}
