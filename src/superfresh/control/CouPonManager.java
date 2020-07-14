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
import superfresh.util.BaseException;
import superfresh.util.BusinessException;
import superfresh.util.DBUtil;
import superfresh.util.DbException;

public class CouPonManager {
	public static BeanCoupon curCoupon = null;
	
	public List<BeanCoupon> loadAll() throws BaseException {
		List<BeanCoupon> result=new ArrayList<BeanCoupon>();
		Connection conn=null;
		if(GmManager.currentGm==null) {
		try {
			conn=DBUtil.getConnection();
			String sql="select * from coupon where user_id = ? order by cou_id";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,UsersManager.currentUser.getUser_id());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanCoupon p = new BeanCoupon();
				p.setCou_id(rs.getInt(1));
				p.setUser_id(rs.getInt(2));
				p.setCou_what(rs.getString(3));
				p.setCou_suit_jine(rs.getDouble(4));
				p.setCou_dis_jine(rs.getDouble(5));
				p.setCou_start_day(rs.getDate(6));
				p.setCou_end_day(rs.getDate(7));
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
		else {
			try {
			conn=DBUtil.getConnection();
			String sql="select * from coupon";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanCoupon p = new BeanCoupon();
				p.setCou_id(rs.getInt(1));
				p.setUser_id(rs.getInt(2));
				p.setCou_what(rs.getString(3));
				p.setCou_suit_jine(rs.getDouble(4));
				p.setCou_dis_jine(rs.getDouble(5));
				p.setCou_start_day(rs.getDate(6));
				p.setCou_end_day(rs.getDate(7));
				result.add(p);}
			}
		catch (SQLException e) {
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
	}
	

	
	public void AddCou(int user_id,String what, double suit_jine, double dis_jine, 
    		String start_day, String end_day, int cishu) throws BaseException{
		if(GmManager.currentGm==null)  throw new BusinessException("需要管理员权限！");
    	if("".equals(start_day)||start_day==null||"".equals(end_day)||end_day==null)
    		throw new BusinessException("日期不能为空！");
    	Connection conn=null;
    	try {
    		conn=DBUtil.getConnection();
    		String sql="select * from users where user_id = ?";
    		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
    		pst.setInt(1, user_id);
    		java.sql.ResultSet rs = pst.executeQuery();
    		if(!rs.next()) {
    			rs.close();
    			pst.close();
    			throw new BusinessException("请输入正确的用户编号！");
    		}
    		rs.close();
			pst.close();
    		sql="insert into coupon(user_id,cou_what,cou_suit_jine,cou_dis_jine,cou_start_day,cou_end_day, cou_cishu) values(?,?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
    		pst.setInt(1, user_id);
    		pst.setString(2,what);
    		pst.setDouble(3,suit_jine);
    		pst.setDouble(4,dis_jine);
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		Date d1 = new Date();
			try {
				d1 = sdf.parse(start_day);
				pst.setDate(5, new java.sql.Date(d1.getTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Date d2 = new Date();
			try {
				d1 = sdf.parse(end_day);
				pst.setDate(6, new java.sql.Date(d1.getTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			pst.setInt(7,cishu);
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
	
	public void DeleteCou(BeanCoupon cou) throws BaseException{
		int cou_id = cou.getCou_id();
		Connection conn=null;
		if(GmManager.currentGm==null)  throw new BusinessException("需要管理员权限！");
		try {
			conn=DBUtil.getConnection();
			String sql="select * from orders where cou_id = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
	    	pst.setInt(1, cou_id);
	    	java.sql.ResultSet rs = pst.executeQuery();
	    	if(rs.next())  throw new BusinessException("在订单记录中出现，不能修改！");
	    	rs.close();
	    	pst.close();
	    	
			sql="delete from coupon where cou_id = ?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, cou_id);
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
	
	
	public void ChaCou(BeanCoupon cc, int user_id,String what, double suit_jine, double dis_jine, 
    		String start_day, String end_day) throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql;
			java.sql.PreparedStatement pst;
			java.sql.ResultSet rs;
			int cou_id = cc.getCou_id();
			
			sql="select * from orders where cou_id = ?";
			pst=conn.prepareStatement(sql);
	    	pst.setInt(1, cou_id);
	    	rs = pst.executeQuery();
	    	if(rs.next())  throw new BusinessException("在订单记录中出现，不能修改！");
	    	rs.close();
	    	pst.close();
	    	
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(user_id!=-1) {
				sql="select * from users where user_id = ?";
				pst=conn.prepareStatement(sql);
		    	pst.setInt(1, user_id);
				rs=pst.executeQuery();
				if(!rs.next())  throw new BusinessException("请输入正确的用户编号！");
				
		    	sql="update coupon set user_id = ? where cou_id = ?";
		    	pst=conn.prepareStatement(sql);
		    	pst.setInt(1, user_id);
		    	pst.setInt(2, cc.getCou_id());
		    	pst.execute();
		        }
			if(!"".equals(what)) {
		    	sql="update coupon set cou_what = ? where cou_id = ?";
		    	pst=conn.prepareStatement(sql);
		    	pst.setString(1, what);
		    	pst.setInt(2, cc.getCou_id());
		    	pst.execute();
		        }
			if(suit_jine!=-1) {
				if(suit_jine<=0)  throw new BusinessException("请输入正确的适用金额！");
		    	sql="update coupon set cou_suit_jine = ? where cou_id = ?";
		    	pst=conn.prepareStatement(sql);
		    	pst.setDouble(1, suit_jine);
		    	pst.setInt(2, cc.getCou_id());
		    	pst.execute();
		        }
			if(dis_jine!=-1) {
				if(dis_jine<=0||dis_jine>suit_jine)  throw new BusinessException("请输入正确的减免金额！");
		    	sql="update coupon set cou_dis_jine = ? where cou_id = ?";
		    	pst=conn.prepareStatement(sql);
		    	pst.setDouble(1, dis_jine);
		    	pst.setInt(2, cc.getCou_id());
		    	pst.execute();
		        }
			if(start_day!=null) {
				Date d1 = new Date();
				try {
	    			d1 = sdf.parse(start_day);
				} catch (ParseException e) {
					e.printStackTrace();
				}
		    	sql="update coupon set cou_start_day = ? where cou_id = ?";
		    	pst=conn.prepareStatement(sql);
		    	pst.setDate(1, new java.sql.Date(d1.getTime()));
		    	pst.setInt(2, cc.getCou_id());
		    	pst.execute();
		        }
			if(end_day!=null) {
				Date d2 = new Date();
				try {
	    			d2 = sdf.parse(end_day);
				} catch (ParseException e) {
					e.printStackTrace();
				}
		    	sql="update coupon set cou_end_day = ? where cou_id = ?";
		    	pst=conn.prepareStatement(sql);
		    	pst.setDate(1, new java.sql.Date(d2.getTime()));
		    	pst.setInt(2, cc.getCou_id());
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
	
	
	
}
