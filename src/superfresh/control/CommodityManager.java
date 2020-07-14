package superfresh.control;

//添加商品


import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import superfresh.model.BeanCommodity;
import superfresh.model.BeanCoupon;
import superfresh.model.BeanGm;
import superfresh.util.BaseException;
import superfresh.util.BusinessException;
import superfresh.util.DBUtil;
import superfresh.util.DbException;

public class CommodityManager {
    public static BeanCommodity currentCommo = null;
	
	public void AddCommodity(String name, double price, double vipprice,
    		int com_count, String guige, String juti) throws BaseException {
    	if(name==null||"".equals(name)||price<0||vipprice<0||com_count<0) {
    		throw new BusinessException("输入数据非法！");
    	}
    	Connection conn=null;
    	if(GmManager.currentGm==null)  throw new BusinessException("需要管理员权限！");
    	try {
    		conn=DBUtil.getConnection();
    		/*String sql="select * from commodity where com_name = ?";
    		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,name);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("已存在该商品!");
			rs.close();
			pst.close();*/
    		
    		String sql="insert into commodity(com_name,com_price,com_vippri,"
					+ "com_count,com_guige,com_juti) values(?,?,?,?,?,?)";
    		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setDouble(2,price);
			pst.setDouble(3,vipprice);
			pst.setInt(4,com_count);
			pst.setString(5,guige);
			pst.setString(6,juti);
			pst.execute();
			pst.close();
			
			
    	}catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
    }
    
    
	public void ChaCommo(BeanCommodity cc, String name, double price, double vipprice,
    		int count, String guige, String juti) throws BaseException {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql;
			java.sql.PreparedStatement pst;
			java.sql.ResultSet rs;
			int com_id = cc.getCom_id();
			/*sql="SELECT * from buy b, commodity c, evaluate e, gmord g, manzinfo m, recommend r, xianscx x\r\n" + 
					"where c.com_id=b.com_id or c.com_id=e.com_id or c.com_id=g.com_id or c.com_id=m.com_id "
					+ "or c.com_id=r.com_id or c.com_id=x.com_id";
			pst=conn.prepareStatement(sql);
	    	pst.setInt(1, cou_id);
	    	rs = pst.executeQuery();
	    	if(rs.next())  throw new BusinessException("在订单记录中出现，不能修改！");
	    	rs.close();
	    	pst.close();*/
	    	
	    	if(!"".equals(name)) {
	    		sql="update commodity set com_name = ? where com_id = ?";
	    		pst=conn.prepareStatement(sql);
		    	pst.setString(1, name);
		    	pst.setInt(2, com_id);
		    	pst.execute();
	    	}
	    	if(price!=-1) {
	    		if(price<=0)  throw new BusinessException("情输入正确的价格！");
	    		sql="update commodity set com_price = ? where com_id = ?";
	    		pst=conn.prepareStatement(sql);
		    	pst.setDouble(1, price);
		    	pst.setInt(2, com_id);
		    	pst.execute();
	    	}
	    	if(vipprice!=-1) {
	    		if(vipprice<=0)  throw new BusinessException("情输入会员正确的价格！");
	    		sql="update commodity set com_vippri = ? where com_id = ?";
	    		pst=conn.prepareStatement(sql);
		    	pst.setDouble(1, vipprice);
		    	pst.setInt(2, com_id);
		    	pst.execute();
	    	}
	    	if(count!=-1) {
	    		if(count<=0)  throw new BusinessException("情输入正确的商品库存！");
	    		sql="update commodity set com_count = ? where com_id = ?";
	    		pst=conn.prepareStatement(sql);
		    	pst.setInt(1, count);
		    	pst.setInt(2, com_id);
		    	pst.execute();
	    	}
	    	if(!"".equals(guige)) {
	    		sql="update commodity set com_guige = ? where com_id = ?";
	    		pst=conn.prepareStatement(sql);
		    	pst.setString(1, guige);
		    	pst.setInt(2, com_id);
		    	pst.execute();
	    	}
	    	if(!"".equals(juti)) {
	    		sql="update commodity set com_juti = ? where com_id = ?";
	    		pst=conn.prepareStatement(sql);
		    	pst.setString(1, juti);
		    	pst.setInt(2, com_id);
		    	pst.execute();
	    	}
	    	
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	
	
	public List<BeanCommodity> loadAll() throws BaseException {
		List<BeanCommodity> result=new ArrayList<BeanCommodity>();
		Connection conn=null;
		//if(GmManager.currentGm==null)  throw new BusinessException("需要管理员权限！");
		try {
			conn=DBUtil.getConnection();
			String sql="select * from commodity";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanCommodity c = new BeanCommodity();
				c.setCom_id(rs.getInt(1));
				c.setCom_name(rs.getString(2));
				c.setCom_price(rs.getDouble(3));
				c.setCom_vippri(rs.getDouble(4));
				c.setCom_count(rs.getInt(5));
				c.setCom_guige(rs.getString(6));
				c.setCom_juti(rs.getString(7));
				result.add(c);
			}
		
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return result;
	}
    
	
	public void DeleteCommo(BeanCommodity cc) throws BaseException{
		int com_id = cc.getCom_id();
		Connection conn=null;
		if(GmManager.currentGm==null)  throw new BusinessException("需要管理员权限！");
		try {
			conn=DBUtil.getConnection();
			String sql="delete from commodity where com_id = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, com_id);
			pst.execute();
			pst.close();
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	
	
    
    public static void main(String[] args) {
		CommodityManager comm = new CommodityManager();
		try {
			comm.AddCommodity("teaaaa", 100, 98.7, 5, "", "");
		} catch (BaseException e) {
			e.printStackTrace();
		}
	}
}
