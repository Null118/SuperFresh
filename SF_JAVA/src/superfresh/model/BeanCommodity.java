package superfresh.model;

public class BeanCommodity {
	private int com_id;
	private String com_name;
	private double com_private;
	private double com_vippri;
	private int com_count;
	private String com_guige;
	private String com_juti;
	
	
	public double getCom_private() {
		return com_private;
	}
	public double getCom_vippri() {
		return com_vippri;
	}
	public void setCom_private(double com_private) {
		this.com_private = com_private;
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
	
}
