package superfresh.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import superfresh.model.BeanCoupon;
import superfresh.model.BeanManzinfo;
import superfresh.util.BaseException;
import superfresh.util.BusinessException;
import superfresh.util.DBUtil;
import superfresh.util.DbException;

public class MzinfoManager {
	public static BeanManzinfo curManzinfo = null;

	public List<BeanManzinfo> loadAll() throws BaseException {
		List<BeanManzinfo> result=new ArrayList<BeanManzinfo>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from manzinfo";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanManzinfo p = new BeanManzinfo();
				p.setMz_id(rs.getInt(1));
				p.setCom_id(rs.getInt(2));
				p.setMz_what(rs.getString(3));
				p.setMz_count(rs.getInt(4));
				p.setMz_discount(rs.getDouble(5));
				p.setMz_start_day(rs.getDate(6));
				p.setMz_end_day(rs.getDate(7));
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
		
		
	public void AddMzinfo(int com_id,String what, int count, double discount, 
    		String start_day, String end_day) throws BaseException{
		if(GmManager.currentGm==null)  throw new BusinessException("需要管理员权限！");
    	if("".equals(start_day)||start_day==null||"".equals(end_day)||end_day==null)
    		throw new BusinessException("日期不能为空！");
    	if(discount<0||discount>1)  throw new BusinessException("请输入合法的折数！");
    	if(count<0)  throw new BusinessException("请输入合法的件数！");
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
    		sql="insert into manzinfo(com_id,mz_what,mz_count,mz_discount,mz_start_day,mz_end_day) values(?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, com_id);
    		pst.setString(2, what);
    		pst.setInt(3, count);
    		pst.setDouble(4, discount);
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		Date d1 = new Date();
			try {
				d1 = sdf.parse(start_day);
				pst.setDate(5, new java.sql.Date(d1.getTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			try {
				d1 = sdf.parse(end_day);
				pst.setDate(6, new java.sql.Date(d1.getTime()));
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
	public void DeleteMzinfo(BeanManzinfo mm) throws BaseException{
		int mz_id = mm.getMz_id();
		Connection conn=null;
		if(GmManager.currentGm==null)  throw new BusinessException("需要管理员权限！");
		try {
			conn=DBUtil.getConnection();
			String sql="select * from buy where mz_id = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, mz_id);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next())  {
				pst.close();
				rs.close();
				throw new BusinessException("存在使用记录 ，无法删除！");
			}
			pst.close();
			rs.close();
			sql="delete from manzinfo where mz_id = ?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, mz_id);
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


	public void ChaMzinfo(BeanManzinfo bb, int com_id,String what, int count, double discount, 
    		String start_day, String end_day) throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql;
			java.sql.PreparedStatement pst;
			java.sql.ResultSet rs;
			int mz_id = bb.getMz_id();
			
			sql="select * from buy where mz_id = ?";
			pst=conn.prepareStatement(sql);
	    	pst.setInt(1, mz_id);
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
				
		    	sql="update manzinfo set com_id = ? where mz_id = ?";
		    	pst=conn.prepareStatement(sql);
		    	pst.setInt(1, com_id);
		    	pst.setInt(2, mz_id);
		    	pst.execute();
		        }
	    	if(!"".equals(what)) {
		    	sql="update manzinfo set mz_what = ? where mz_id = ?";
		    	pst=conn.prepareStatement(sql);
		    	pst.setString(1, what);
		    	pst.setInt(2, mz_id);
		    	pst.execute();
		        }
	    	if(count!=-1) {
				if(count<0)  throw new BusinessException("请输入正确的满折件数！");
		    	sql="update manzinfo set mz_count = ? where mz_id = ?";
		    	pst=conn.prepareStatement(sql);
		    	pst.setInt(1, count);
		    	pst.setInt(2, mz_id);
		    	pst.execute();
		        }
	    	if(discount!=-1) {
				if(discount<0||discount>1)  throw new BusinessException("请输入正确的折扣！");
		    	sql="update manzinfo set mz_discount = ? where mz_id = ?";
		    	pst=conn.prepareStatement(sql);
		    	pst.setDouble(1, discount);
		    	pst.setInt(2, mz_id);
		    	pst.execute();
		        }
	    	if(start_day!=null) {
				Date d1 = new Date();
				try {
	    			d1 = sdf.parse(start_day);
				} catch (ParseException e) {
					e.printStackTrace();
				}
		    	sql="update manzinfo set mz_start_day = ? where mz_id = ?";
		    	pst=conn.prepareStatement(sql);
		    	pst.setDate(1, new java.sql.Date(d1.getTime()));
		    	pst.setInt(2, mz_id);
		    	pst.execute();
		        }
			if(end_day!=null) {
				Date d2 = new Date();
				try {
	    			d2 = sdf.parse(end_day);
				} catch (ParseException e) {
					e.printStackTrace();
				}
		    	sql="update manzinfo set mz_end_day = ? where mz_id = ?";
		    	pst=conn.prepareStatement(sql);
		    	pst.setDate(1, new java.sql.Date(d2.getTime()));
		    	pst.setInt(2, mz_id);
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

