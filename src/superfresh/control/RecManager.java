package superfresh.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import superfresh.model.BeanXianscx;
import superfresh.util.BaseException;
import superfresh.util.BusinessException;
import superfresh.util.DBUtil;
import superfresh.util.DbException;
import superfresh.model.BeanRecommend;

public class RecManager {
	
	public List<BeanRecommend> loadAll() throws BaseException {
		List<BeanRecommend> result=new ArrayList<BeanRecommend>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from recommend";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanRecommend p=new BeanRecommend();
				p.setMen_id(rs.getInt(1));
				p.setCom_id(rs.getInt(2));
				p.setRec_how(rs.getString(3));
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
	
	
	public void DeleteRec(BeanRecommend cx) throws BaseException{
		if(GmManager.currentGm==null)  throw new BusinessException("需要管理员权限！");
		int men_id = cx.getMen_id();
		int com_id = cx.getCom_id();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="delete from recommend where men_id = ? and com_id = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, men_id);
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
	
	public void AddRec(int men_id, int com_id, String how) throws BaseException{
    	Connection conn=null;
    	try {
    		conn=DBUtil.getConnection();
    		String sql="select * from menu where men_id = ?";
    		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
    		pst.setInt(1, men_id);
    		java.sql.ResultSet rs = pst.executeQuery();
    		if(!rs.next()) {
    			rs.close();
    			pst.close();
    			throw new BusinessException("请输入正确的菜谱编号！");
    		}
    		rs.close();
			pst.close();
			sql="select * from commodity where com_id = ?";
    		pst=conn.prepareStatement(sql);
    		pst.setInt(1, com_id);
    		rs = pst.executeQuery();
    		if(!rs.next()) {
    			rs.close();
    			pst.close();
    			throw new BusinessException("请输入正确的商品编号！");
    		}
    		rs.close();
			pst.close();
			sql="select * from recommend where com_id = ? and men_id = ?";
			pst=conn.prepareStatement(sql);
    		pst.setInt(2, men_id);
    		pst.setInt(1, com_id);
    		rs = pst.executeQuery();
    		if(rs.next()) {
    			rs.close();
    			pst.close();
    			throw new BusinessException("已存在该商品菜谱推荐！");
    		}
    		sql="insert into recommend(men_id, com_id, rec_how) values(?,?,?)";
			pst=conn.prepareStatement(sql);
    		pst.setInt(1, men_id);
    		pst.setInt(2, com_id);
    		pst.setString(3, how);
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
