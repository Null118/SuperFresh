package superfresh.model;

import java.util.Date;

public class BeanCoupon {
    private int cou_id;
    private String cou_what;
    private float cou_suit_jine;
    private float cou_dis_jine;
    private Date cou_start_day;
    private Date cou_end_day;
    
    
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
	public float getCou_suit_jine() {
		return cou_suit_jine;
	}
	public void setCou_suit_jine(float cou_suit_jine) {
		this.cou_suit_jine = cou_suit_jine;
	}
	public float getCou_dis_jine() {
		return cou_dis_jine;
	}
	public void setCou_dis_jine(float cou_dis_jine) {
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
    
}
