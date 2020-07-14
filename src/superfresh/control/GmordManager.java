package superfresh.control;

//添加，删除 COMPLETE

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import superfresh.model.BeanGm;
import superfresh.model.BeanGmord;
import superfresh.util.BaseException;
import superfresh.util.BusinessException;
import superfresh.util.DBUtil;
import superfresh.util.DbException;

public class GmordManager {
    public void AddGmord(int com_id,int gmord_cnt) throws BaseException {
    	Connection conn=null;
    	//int flag=0;
    	if(com_id<0||gmord_cnt<0)  throw new BusinessException("请输入合法数据！");
    	try {
    		conn=DBUtil.getConnection();
    		String sql="select com_count from commodity where com_id = ?";
    		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,com_id);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("请输入正确的商品编号!");
    		if(rs.getInt(1)<gmord_cnt)  throw new BusinessException("购买量大于库存!");
    		pst.close();
    		rs.close();
    		
    		sql="insert into gmord(gm_id,com_id,gmord_cnt,gmord_situ) values (?,?,?,'在库')";
    		pst=conn.prepareStatement(sql);
    		pst.setInt(1, 1);//pst.setInt(1,currentUser.getGm_id());
    		pst.setInt(2,com_id);
    		pst.setInt(3, gmord_cnt);
    		pst.execute();
    		pst.close();
    	    
    		sql="update commodity set com_count=com_count- ? where com_id = ?";
    		pst=conn.prepareStatement(sql);
    		pst.setInt(1, gmord_cnt);
    		pst.setInt(2, com_id);
    		pst.execute();
    		pst.close();
			//}
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
    
    
    public List<BeanGmord> loadAll() throws BaseException {
		List<BeanGmord> result=new ArrayList<BeanGmord>();
		Connection conn=null;
		if(GmManager.currentGm==null)  throw new BusinessException("需要管理员权限！");
		try {
			conn=DBUtil.getConnection();
			String sql="select * from gmord ";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanGmord p=new BeanGmord();
				p.setGmord_id(rs.getInt(1));
				p.setGm_id(rs.getInt(2));
				p.setCom_id(rs.getInt(3));
				p.setGmord_cnt(rs.getInt(4));
				p.setGmord_situ(rs.getString(5));
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
    
    
    public void DeleteGmord (BeanGmord b) throws BaseException{
    	Connection conn=null;
    	//int com_id = 0;
    	//if("".equals(b.get)||name==null)  throw new BusinessException("请输入正确的商品名!");
    	try {
    		conn=DBUtil.getConnection();
    		/*String sql="select c.com_id from gmord g,commodity c where com_name = ? and g.gm_id = ?";
    		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
    		pst.setString(1, name);
    		pst.setInt(2, 1);//pst.setInt(2,currentUser.getGm_id());
    		java.sql.ResultSet rs=pst.executeQuery();
    		if(!rs.next())  {
    			pst.close();
        		rs.close();
    			throw new BusinessException("没有购买记录!");
    		}
    		com_id = rs.getInt(1);
    		pst.close();
    		rs.close();*/
    		
    		String sql="delete from gmord where gmord_id  = ?";
    		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
    		pst.setInt(1, b.getGmord_id()); 
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
