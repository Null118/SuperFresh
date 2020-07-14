package superfresh.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import superfresh.model.BeanEvaluate;
import superfresh.model.BeanRecommend;
import superfresh.util.BaseException;
import superfresh.util.BusinessException;
import superfresh.util.DBUtil;
import superfresh.util.DbException;

public class EvaluateManager {
	
	public static int getSumPingjia(int user_id)throws BaseException{
		Connection conn=null;
		int result = 0;
		try {
			conn=DBUtil.getConnection();
			String sql="select count(*) from evaluate group by user_id having user_id =?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, user_id);
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
	
	
	public List<BeanEvaluate> loadAll() throws BaseException {
		List<BeanEvaluate> result=new ArrayList<BeanEvaluate>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from evaluate";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanEvaluate p=new BeanEvaluate();
				p.setCom_id(rs.getInt(1));
				p.setUser_id(rs.getInt(2));
				p.setEva_what(rs.getString(3));
				p.setEva_day(rs.getDate(4));
				p.setEva_star(rs.getInt(5));
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
	public void DeleteEva(BeanEvaluate ee) throws BaseException{
		if(GmManager.currentGm==null)  throw new BusinessException("需要管理员权限！");
		int user_id = ee.getUser_id();
		int com_id = ee.getCom_id();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="delete from evaluate where user_id = ? and com_id = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, user_id);
			pst.setInt(2, com_id);
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
	
	public void AddEva(int com_id, String what, String day, int star) throws BaseException{
    	Connection conn=null;
    	if(GmManager.currentGm!=null)  throw new BusinessException("只有用户可以评论！");
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
			sql="select * from evaluate where com_id = ? and user_id = ?";
			pst=conn.prepareStatement(sql);
    		pst.setInt(2, UsersManager.currentUser.getUser_id());
    		pst.setInt(1, com_id);
    		rs = pst.executeQuery();
    		if(rs.next()) {
    			rs.close();
    			pst.close();
    			throw new BusinessException("已存在该评价！");
    		}
    		sql="insert into evaluate(com_id, user_id, eva_what, eva_day, eva_star) values(?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
    		pst.setInt(1, com_id);
    		pst.setInt(2, UsersManager.currentUser.getUser_id());
    		pst.setString(3, what);
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		Date d1 = new Date();
			try {
				d1 = sdf.parse(day);
				pst.setDate(4, new java.sql.Date(d1.getTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			pst.setInt(5, star);
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
	
	
}
