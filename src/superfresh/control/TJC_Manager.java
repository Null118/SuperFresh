package superfresh.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import superfresh.model.BeanTJC;
import superfresh.model.BeanTJU;
import superfresh.util.BaseException;
import superfresh.util.DBUtil;
import superfresh.util.DbException;

public class TJC_Manager {
	
	public static int getSumKC(int com_id)throws BaseException{
		Connection conn=null;
		int result = 0;
		try {
			conn=DBUtil.getConnection();
			String sql="select sum(com_count) from commodity group by com_id having com_id =?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, com_id);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			return result;
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
	
	
	public static int getSumXL(int com_id)throws BaseException{
		Connection conn=null;
		int result = 0;
		try {
			conn=DBUtil.getConnection();
			String sql="select sum(buy_count) from buy group by com_id having com_id =?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, com_id);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			return result;
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
	
	
	public static int getmz_id(int com_id)throws BaseException{
		Connection conn=null;
		int result = 0;
		try {
			conn=DBUtil.getConnection();
			String sql="select mz_id from manzinfo where com_id =?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, com_id);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			return result;
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
	
	
	
	public static int getcx_id(int com_id)throws BaseException{
		Connection conn=null;
		int result = 0;
		try {
			conn=DBUtil.getConnection();
			String sql="select cx_id from xianscx where com_id =?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, com_id);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			return result;
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
	
	
	public static int getPingjia(int com_id)throws BaseException{
		Connection conn=null;
		int result = 0;
		try {
			conn=DBUtil.getConnection();
			String sql="select count(*) from evaluate group by com_id having com_id =?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, com_id);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
			return result;
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
	
	
	
	public List<BeanTJC> loadAll() throws BaseException {
		List<BeanTJC> result=new ArrayList<BeanTJC>();
		List<Integer> user=new ArrayList<Integer>();
		Connection conn=null;
		int i = 0;
		try {
			conn=DBUtil.getConnection();
			String sql="select com_id from commodity group by com_id";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				user.add(rs.getInt(1));
			}
			for(i=0;i<user.size();i++) {
				BeanTJC p = new BeanTJC();
				p.setCom_id(user.get(i).intValue());
				p.setKc(TJC_Manager.getSumKC(user.get(i).intValue()));
				p.setXl(TJC_Manager.getSumXL(user.get(i).intValue()));
				p.setMz_id(TJC_Manager.getmz_id(user.get(i).intValue()));
				p.setCx_id(TJC_Manager.getcx_id(user.get(i).intValue()));
				p.setPj(TJC_Manager.getPingjia(user.get(i).intValue()));
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
}
