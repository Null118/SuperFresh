package superfresh.model;

import java.sql.Timestamp;

public class BeanOrders {
    private int ord_id;
    private int cou_id;
    private int add_id;
    private int user_id;
    private float ord_init_jine;
    private float ord_sum_jine;
    private Timestamp ord_ttl;
    private String ord_situ;
    
    
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
	public float getOrd_init_jine() {
		return ord_init_jine;
	}
	public void setOrd_init_jine(float ord_init_jine) {
		this.ord_init_jine = ord_init_jine;
	}
	public float getOrd_sum_jine() {
		return ord_sum_jine;
	}
	public void setOrd_sum_jine(float ord_sum_jine) {
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
    
}
