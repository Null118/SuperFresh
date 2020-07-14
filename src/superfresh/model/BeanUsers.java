package superfresh.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BeanUsers {
    private int user_id;
    private String user_name;
    private String user_sex;
    private String user_pwd;
    private String user_tel;
    private String user_mail;
    private String user_city;
    private Date reg_day;
    private String user_vip;
    private String user_vip_end;
    public static final String[] tableTitles={"用户编号","用户姓名","用户性别","用户密码","用户电话","用户邮箱","用户城市","注册时间","VIP","VIP截至时间"};
    
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_sex() {
		return user_sex;
	}
	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	public String getUser_tel() {
		return user_tel;
	}
	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}
	public String getUser_mail() {
		return user_mail;
	}
	public void setUser_mail(String user_mail) {
		this.user_mail = user_mail;
	}
	public String getUser_city() {
		return user_city;
	}
	public void setUser_city(String user_city) {
		this.user_city = user_city;
	}
	public Date getReg_day() {
		return reg_day;
	}
	public void setReg_day(Date reg_day) {
		this.reg_day = reg_day;
	}
	public String getUser_vip() {
		return user_vip;
	}
	public void setUser_vip(String user_vip) {
		this.user_vip = user_vip;
	}
	public String getUser_vip_end() {
		return user_vip_end;
	}
	public void setUser_vip_end(String user_vip_end) {
		this.user_vip_end = user_vip_end;
	}
    
	public String getCell(int col){
		if(col==0) return String.valueOf(user_id);
		else if(col==1) return user_name;
		else if(col==2) return user_sex;
		else if(col==3) return user_pwd;
		else if(col==4) return user_tel;
		else if(col==5) return user_mail;
		else if(col==6) return user_city;
		else if(col==7) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
			return format.format(reg_day); 
		}
		else if(col==8) return user_vip;
		else if(col==9) return user_vip_end;
		else return "";
	}
}
