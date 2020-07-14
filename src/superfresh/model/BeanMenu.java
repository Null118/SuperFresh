package superfresh.model;

public class BeanMenu {
    private int men_id;
    private int fre_id;
    private String men_step;
    private String men_name;
    private String men_pic;
    public static final String[] tableTitles={"菜谱编号","菜谱名称","生鲜用料","制作步骤"};
    
	public int getMen_id() {
		return men_id;
	}
	public void setMen_id(int men_id) {
		this.men_id = men_id;
	}
	
	public int getFre_id() {
		return fre_id;
	}
	public void setFre_id(int fre_id) {
		this.fre_id = fre_id;
	}
	public String getMen_step() {
		return men_step;
	}
	public void setMen_step(String men_step) {
		this.men_step = men_step;
	}
	public String getMen_name() {
		return men_name;
	}
	public void setMen_name(String men_name) {
		this.men_name = men_name;
	}
	public String getMen_pic() {
		return men_pic;
	}
	public void setMen_pic(String men_pic) {
		this.men_pic = men_pic;
	}
	public String getCell(int col){
		if(col==0) return String.valueOf(men_id);
		else if(col==1) return men_name;
		else if(col==2) return String.valueOf(fre_id);
		else if(col==3) return men_step;
		else return "";
	}
}
