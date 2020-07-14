package superfresh.model;

public class BeanTJC {
	public static final String[] tableTitles={"商品编号","销量","库存","满折活动编号","促销活动编号","总评价条数"};
	private int xl;
	private int com_id;
	public int getCom_id() {
		return com_id;
	}
	public void setCom_id(int com_id) {
		this.com_id = com_id;
	}
	private int kc;
	private int mz_id;
	private int cx_id;
	private int pj;
	public int getXl() {
		return xl;
	}
	public void setXl(int xl) {
		this.xl = xl;
	}
	public int getKc() {
		return kc;
	}
	public void setKc(int kc) {
		this.kc = kc;
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
	public int getPj() {
		return pj;
	}
	public void setPj(int pj) {
		this.pj = pj;
	}
	public String getCell(int col){
		if(col==0) return String.valueOf(com_id);
		else if(col==1) return String.valueOf(xl);
		else if(col==2) return String.valueOf(kc);
		else if(col==3) return String.valueOf(mz_id);
		else if(col==4) return String.valueOf(cx_id);
		else if(col==5) return String.valueOf(pj);
		else return "";
	}
}
