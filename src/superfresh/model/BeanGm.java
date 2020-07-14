package superfresh.model;

public class BeanGm {
    private int gm_id;
    private String gm_name;
    private String gm_pwd;
    
    public static final String[] tableTitles={"管理员编号","管理员姓名","管理员密码"};
	public int getGm_id() {
		return gm_id;
	}
	public void setGm_id(int gm_id) {
		this.gm_id = gm_id;
	}
	public String getGm_name() {
		return gm_name;
	}
	public void setGm_name(String gm_name) {
		this.gm_name = gm_name;
	}
	public String getGm_pwd() {
		return gm_pwd;
	}
	public void setGm_pwd(String gm_pwd) {
		this.gm_pwd = gm_pwd;
	}
	
	public String getCell(int col){
		if(col==0) return String.valueOf(gm_id);
		else if(col==1) return gm_name;
		else if(col==2) return gm_pwd;
		else return "";
	}
}
