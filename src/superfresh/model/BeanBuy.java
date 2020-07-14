package superfresh.model;

import java.text.DecimalFormat;

public class BeanBuy {
	public static final String[] tableTitles={"购买编号","商品编号","购买数量","商品状态","购买批次","购买总金额","优惠总金额"};
    private int buy_id;
    private int com_id;
    private int buy_count;
    private String buy_suit;
    private double buy_sum;
    private double buy_discsum;
    public double getBuy_sum() {
		return buy_sum;
	}
	public void setBuy_sum(double buy_sum) {
		this.buy_sum = buy_sum;
	}
	public double getBuy_discsum() {
		return buy_discsum;
	}
	public void setBuy_discsum(double buy_discsum) {
		this.buy_discsum = buy_discsum;
	}
	public int getMz_id() {
		return mz_id;
	}
	public void setMz_id(int mz_id) {
		this.mz_id = mz_id;
	}
	public int getCx_id() {
		return cx_id;
	}
	public void setCx_id(int cx_id) {
		this.cx_id = cx_id;
	}
	private int mz_id;
    private int cx_id;
    private int flag;
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getBuy_id() {
		return buy_id;
	}
	public void setBuy_id(int buy_id) {
		this.buy_id = buy_id;
	}
	public int getCom_id() {
		return com_id;
	}
	public void setCom_id(int com_id) {
		this.com_id = com_id;
	}
	public int getBuy_count() {
		return buy_count;
	}
	public void setBuy_count(int buy_count) {
		this.buy_count = buy_count;
	}
	public String getBuy_suit() {
		return buy_suit;
	}
	public void setBuy_suit(String buy_suit) {
		this.buy_suit = buy_suit;
	}
	public String getCell(int col){
		if(col==0) return String.valueOf(buy_id);
		else if(col==1) return String.valueOf(com_id);
		else if(col==2) return String.valueOf(buy_count);
		else if(col==3) return buy_suit;
		else if(col==4) return String.valueOf(flag);
		else if(col==5) return String.valueOf(new DecimalFormat("0.00").format(buy_sum));
		else if(col==6) return String.valueOf(new DecimalFormat("0.00").format(buy_discsum));
		else return "";
	}
}
