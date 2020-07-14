package superfresh.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import superfresh.model.BeanAddress;
import superfresh.model.BeanFresh;
import superfresh.model.BeanGm;
import superfresh.util.BaseException;
import superfresh.util.BusinessException;
import superfresh.util.DBUtil;
import superfresh.util.DbException;

public class FreshManager {
	public static BeanFresh currentFresh = null;
	
	public List<BeanFresh> loadAll() throws BaseException {
		List<BeanFresh> result=new ArrayList<BeanFresh>();
		Connection conn=null;
		//if(GmManager.currentGm==null)  throw new BusinessException("需要管理员权限！");
		try {
			conn=DBUtil.getConnection();
			String sql="select * from fresh";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanFresh p=new BeanFresh();
				p.setFre_id(rs.getInt(1));
				p.setFre_name(rs.getString(2));
				p.setFre_what(rs.getString(3));
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
	
	
	public void AddFresh(String name, String what) throws BaseException{
    	if("".equals(name)||name==null||"".equals(what)||what==null)
    		throw new BusinessException("任意输入不能为空！");
    	Connection conn=null;
    	try {
    		conn=DBUtil.getConnection();
    		String sql="insert into fresh(fre_name,fre_what) values(?,?)";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
    		pst.setString(1,name);
    		pst.setString(2,what);
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
	
	
	public void ChaFre(BeanFresh ff, String name, String what) throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql;
			java.sql.PreparedStatement pst;
			java.sql.ResultSet rs;
			int fre_id = ff.getFre_id();
			if(!"".equals(name)) {
				sql="update fresh set fre_name = ? where fre_id = ?";
				pst=conn.prepareStatement(sql);
		    	pst.setString(1, name);
		    	pst.setInt(2, fre_id);
		    	pst.execute();
			}
			if(!"".equals(name)) {
				sql="update fresh set fre_what = ? where fre_id = ?";
				pst=conn.prepareStatement(sql);
		    	pst.setString(1, what);
		    	pst.setInt(2, fre_id);
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
	
	
	
	public void DeleteFresh(BeanFresh fre) throws BaseException{
		int fre_id = fre.getFre_id();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="delete from fresh where fre_id = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, fre_id);
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
