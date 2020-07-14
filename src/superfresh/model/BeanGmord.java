package superfresh.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class BeanGmord {
    private int gmord_id;
    private int gm_id;
    private int com_id;
    private int gmord_cnt;
    private String gmord_situ;
    public static final String[] tableTitles={"管理员订单编号","管理员编号","商品编号","购买件数","订单状态"};
	public int getGmord_id() {
		return gmord_id;
	}
	public void setGmord_id(int gmord_id) {
		this.gmord_id = gmord_id;
	}
	public int getGm_id() {
		return gm_id;
	}
	public void setGm_id(int gm_id) {
		this.gm_id = gm_id;
	}
	public int getCom_id() {
		return com_id;
	}
	public void setCom_id(int com_id) {
		this.com_id = com_id;
	}
	public int getGmord_cnt() {
		return gmord_cnt;
	}
	public void setGmord_cnt(int gmord_cnt) {
		this.gmord_cnt = gmord_cnt;
	}
	public String getGmord_situ() {
		return gmord_situ;
	}
	public void setGmord_situ(String gmord_situ) {
		this.gmord_situ = gmord_situ;
	}
	public String getCell(int col){
		if(col==0) return String.valueOf(gmord_id);
		else if(col==1) return String.valueOf(gm_id);
		else if(col==2) return String.valueOf(com_id);
		else if(col==3) return String.valueOf(gmord_cnt);
		else if(col==4) return gmord_situ;
		else return "";
	}
}
