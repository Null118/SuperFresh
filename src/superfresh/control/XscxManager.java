package superfresh.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import superfresh.model.BeanAddress;
import superfresh.model.BeanCoupon;
import superfresh.model.BeanXianscx;
import superfresh.util.BaseException;
import superfresh.util.BusinessException;
import superfresh.util.DBUtil;
import superfresh.util.DbException;

public class XscxManager {
	public static BeanXianscx curCx = null;
	
	public List<BeanXianscx> loadAll() throws BaseException {
		List<BeanXianscx> result=new ArrayList<BeanXianscx>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from xianscx";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanXianscx p=new BeanXianscx();
				p.setCx_id(rs.getInt(1));
				p.setCom_id(rs.getInt(2));
				p.setCx_price(rs.getDouble(3));
				p.setCx_count(rs.getInt(4));
				p.setCx_start_day(rs.getDate(5));
				p.setCx_end_day(rs.getDate(6));
				result.add(p);
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
	
	public void AddXscx(int com_id,double price, int count, String start_day, String end_day) throws BaseException{
		if(GmManager.currentGm==null)  throw new BusinessException("需要管理员权限！");
    	if("".equals(start_day)||start_day==null||"".equals(end_day)||end_day==null)
    		throw new BusinessException("日期不能为空！");
    	if(price<0)  throw new BusinessException("价格不能小于零！");
    	if(count<0)  throw new BusinessException("件数不能小于零！");
    	Connection conn=null;
    	try {
    		conn=DBUtil.getConnection();
    		String sql="select * from commodity where com_id = ?";
    		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
    		pst.setInt(1, com_id);
    		java.sql.ResultSet rs = pst.executeQuery();
    		if(!rs.next()) {
    			rs.close();
    			pst.close();
    			throw new BusinessException("请输入正确的商品编号！");
    		}
    		rs.close();
			pst.close();
    		sql="insert into xianscx(com_id,cx_price,cx_count,cx_start_day,cx_end_day) values(?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
    		pst.setInt(1, com_id);
    		pst.setDouble(2,price);
    		pst.setInt(3,count);
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		Date d1 = new Date();
			try {
				d1 = sdf.parse(start_day);
				pst.setDate(4, new java.sql.Date(d1.getTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Date d2 = new Date();
			try {
				d1 = sdf.parse(end_day);
				pst.setDate(5, new java.sql.Date(d1.getTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
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
	
	
	public void DeleteXscx(BeanXianscx cx) throws BaseException{
		if(GmManager.currentGm==null)  throw new BusinessException("需要管理员权限！");
		int cx_id = cx.getCx_id();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from buy where cx_id = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, cx_id);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next())  {
				pst.close();
				rs.close();
				throw new BusinessException("存在购买记录！");
			}
			pst.close();
			rs.close();
			sql="delete from xianscx where cx_id = ?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, cx_id);
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
	
	
	public void ChaCx(BeanXianscx xx, int com_id,double price, int count, String start_day, String end_day) throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql;
			java.sql.PreparedStatement pst;
			java.sql.ResultSet rs;
			int cx_id = xx.getCx_id();
			
			sql="select * from buy where cx_id = ?";
			pst=conn.prepareStatement(sql);
	    	pst.setInt(1, cx_id);
	    	rs = pst.executeQuery();
	    	if(rs.next())  throw new BusinessException("在购买记录中出现，不能修改！");
	    	rs.close();
	    	pst.close();
	    	
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    		if(com_id!=-1) {
				sql="select * from commodity where com_id = ?";
				pst=conn.prepareStatement(sql);
		    	pst.setInt(1, com_id);
				rs=pst.executeQuery();
				if(!rs.next())  throw new BusinessException("请输入正确的商品编号！");
				
		    	sql="update xianscx set com_id = ? where cx_id = ?";
		    	pst=conn.prepareStatement(sql);
		    	pst.setInt(1, com_id);
		    	pst.setInt(2, cx_id);
		    	pst.execute();
		        }
	    		if(price!=-1) {
	    			if(price<0)  throw new BusinessException("请输出正确的价格！");
	    			sql="update xianscx set cx_price = ? where cx_id = ?";
	    			pst=conn.prepareStatement(sql);
			    	pst.setDouble(1, price);
			    	pst.setInt(2, cx_id);
			    	pst.execute();
	    		}
	    		if(count!=-1) {
	    			if(count<0)  throw new BusinessException("请输出正确的促销件数！");
	    			sql="update xianscx set cx_count = ? where cx_id = ?";
	    			pst=conn.prepareStatement(sql);
			    	pst.setInt(1, count);
			    	pst.setInt(2, cx_id);
			    	pst.execute();
	    		}
	    		if(start_day!=null) {
					Date d1 = new Date();
					try {
		    			d1 = sdf.parse(start_day);
					} catch (ParseException e) {
						e.printStackTrace();
					}
			    	sql="update xianscx set cx_start_day = ? where cx_id = ?";
			    	pst=conn.prepareStatement(sql);
			    	pst.setDate(1, new java.sql.Date(d1.getTime()));
			    	pst.setInt(2, cx_id);
			    	pst.execute();
			        }
				if(end_day!=null) {
					Date d2 = new Date();
					try {
		    			d2 = sdf.parse(end_day);
					} catch (ParseException e) {
						e.printStackTrace();
					}
			    	sql="update xianscx set cx_end_day = ? where cx_id = ?";
			    	pst=conn.prepareStatement(sql);
			    	pst.setDate(1, new java.sql.Date(d2.getTime()));
			    	pst.setInt(2, cx_id);
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
	
	
	
	public static void main(String[] args) {
		XscxManager xx = new XscxManager();
		try {
			xx.AddXscx(1, 50, 5, "2020-05-17", "2020-05-20");
		} catch (BaseException e) {
			e.printStackTrace();
		}
	}
}
