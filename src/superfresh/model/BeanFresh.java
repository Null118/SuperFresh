package superfresh.model;

public class BeanFresh {
	public static final String[] tableTitles={"生鲜编号","生鲜名字","生鲜详情"};
    private int fre_id;
    private String fre_name;
    private String fre_what;
	public int getFre_id() {
		return fre_id;
	}
	public void setFre_id(int fre_id) {
		this.fre_id = fre_id;
	}
	public String getFre_name() {
		return fre_name;
	}
	public void setFre_name(String fre_name) {
		this.fre_name = fre_name;
	}
	public String getFre_what() {
		return fre_what;
	}
	public void setFre_what(String fre_what) {
		this.fre_what = fre_what;
	}
	public String getCell(int col){
		if(col==0) return String.valueOf(fre_id);
		else if(col==1) return fre_name;
		else if(col==2) return fre_what;
		else return "";
	}
}
