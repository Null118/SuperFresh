package superfresh.model;

public class BeanAddress {
	public static final String[] tableTitles={"地址编号","用户编号","省","市","区","具体","姓名","电话"};
	
    private int add_id;
    private int user_id;
    private String add_sheng;
    private String add_shi;
    private String add_qu;
    private String add_juti;
    private String add_name;
    private String add_tel;
    
    
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
	public String getAdd_sheng() {
		return add_sheng;
	}
	public void setAdd_sheng(String add_sheng) {
		this.add_sheng = add_sheng;
	}
	public String getAdd_shi() {
		return add_shi;
	}
	public void setAdd_shi(String add_shi) {
		this.add_shi = add_shi;
	}
	public String getAdd_qu() {
		return add_qu;
	}
	public void setAdd_qu(String add_qu) {
		this.add_qu = add_qu;
	}
	public String getAdd_juti() {
		return add_juti;
	}
	public void setAdd_juti(String add_juti) {
		this.add_juti = add_juti;
	}
	public String getAdd_name() {
		return add_name;
	}
	public void setAdd_name(String add_name) {
		this.add_name = add_name;
	}
	public String getAdd_tel() {
		return add_tel;
	}
	public void setAdd_tel(String add_tel) {
		this.add_tel = add_tel;
	}
    
	public String getCell(int col){
		if(col==0) return String.valueOf(add_id);
		else if(col==1) return String.valueOf(user_id);
		else if(col==2) return add_sheng;
		else if(col==3) return add_shi;
		else if(col==4) return add_qu;
		else if(col==5) return add_juti;
		else if(col==6) return add_name;
		else if(col==7) return add_tel;
		else return "";
	}
    
}
