package superfresh.model;

public class BeanCommodity {
	private int com_id;
	private String com_name;
	private double com_price;
	private double com_vippri;
	private int com_count;
	private String com_guige;
	private String com_juti;
	public static final String[] tableTitles={"商品编号","商品名称","商品价格","vip价格","商品库存","商品规格","具体信息"};
	
	public double getCom_price() {
		return com_price;
	}
	public double getCom_vippri() {
		return com_vippri;
	}
	public void setCom_price(double com_private) {
		this.com_price = com_private;
	}
	public void setCom_vippri(double com_vippri) {
		this.com_vippri = com_vippri;
	}
	public int getCom_id() {
		return com_id;
	}
	public void setCom_id(int com_id) {
		this.com_id = com_id;
	}
	public String getCom_name() {
		return com_name;
	}
	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}
	
	public int getCom_count() {
		return com_count;
	}
	public void setCom_count(int com_count) {
		this.com_count = com_count;
	}
	public String getCom_guige() {
		return com_guige;
	}
	public void setCom_guige(String com_guige) {
		this.com_guige = com_guige;
	}
	public String getCom_juti() {
		return com_juti;
	}
	public void setCom_juti(String com_juti) {
		this.com_juti = com_juti;
	}
	public String getCell(int col){
		if(col==0) return String.valueOf(com_id);
		else if(col==1) return com_name;
		else if(col==2) return String.valueOf(com_price);
		else if(col==3) return String.valueOf(com_vippri);
		else if(col==4) return String.valueOf(com_count);
		else if(col==5) return com_guige;
		else if(col==6) return com_juti;
		else return "";
	}
}
