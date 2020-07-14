package superfresh.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import superfresh.model.BeanAddress;
import superfresh.util.BaseException;
import superfresh.util.BusinessException;
import superfresh.util.DBUtil;
import superfresh.util.DbException;

public class AddressManager {
    public static BeanAddress curAddress = null;
    
	public void AddAddress(String sheng, String shi, String qu, 
			String juti, String name, String tel) throws BaseException{
		
	    if(GmManager.currentGm!=null)  throw new BusinessException("只有用户可以进行此操作！");
    	if("".equals(sheng)||sheng==null||"".equals(shi)||shi==null||
    			"".equals(qu)||qu==null||"".equals(juti)||juti==null||
    			"".equals(name)||name==null||"".equals(tel)||tel==null)
    		throw new BusinessException("任意输入不能为空！");
    	Connection conn=null;
    	try {
    		conn=DBUtil.getConnection();
    		String sql="insert into address(user_id,add_sheng,add_shi,add_qu,"
    				+ "add_juti,add_name,add_tel) values(?,?,?,?,?,?,?)";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
    		//pst.setInt(1, 1);
    		pst.setInt(1, UsersManager.currentUser.getUser_id());
    		pst.setString(2,sheng);
    		pst.setString(3,shi);
    		pst.setString(4,qu);
    		pst.setString(5,juti);
    		pst.setString(6,name);
    		pst.setString(7,tel);
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
	
	
	public void DeleteAdd(BeanAddress add) throws BaseException{
		int add_id = add.getAdd_id();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
    		String sql="select * from orders where add_id = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, add_id);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next())  {
				rs.close();
				pst.close();
				throw new BusinessException("有相关订单，不能删除！");
			}
			rs.close();
			pst.close();
			sql="delete from address where add_id = ?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, add_id);
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
	
	
	
	public List<BeanAddress> loadAll() throws BaseException {
		List<BeanAddress> result=new ArrayList<BeanAddress>();
		Connection conn=null;
		if(GmManager.currentGm==null) {
		try {
			conn=DBUtil.getConnection();
			String sql="select * from address where user_id = ? order by add_id";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,UsersManager.currentUser.getUser_id());
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanAddress p=new BeanAddress();
				p.setAdd_id(rs.getInt(1));
				p.setUser_id(rs.getInt(2));
				p.setAdd_sheng(rs.getString(3));
				p.setAdd_shi(rs.getString(4));
				p.setAdd_qu(rs.getString(5));
				p.setAdd_juti(rs.getString(6));
				p.setAdd_name(rs.getString(7));
				p.setAdd_tel(rs.getString(8));
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
				String sql="select * from address";
				java.sql.PreparedStatement pst=conn.prepareStatement(sql);
				java.sql.ResultSet rs=pst.executeQuery();
				while(rs.next()) {
					BeanAddress p=new BeanAddress();
					p.setAdd_id(rs.getInt(1));
					p.setUser_id(rs.getInt(2));
					p.setAdd_sheng(rs.getString(3));
					p.setAdd_shi(rs.getString(4));
					p.setAdd_qu(rs.getString(5));
					p.setAdd_juti(rs.getString(6));
					p.setAdd_name(rs.getString(7));
					p.setAdd_tel(rs.getString(8));
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
	
	
	
	
	public void ChaAdd(BeanAddress add, String sheng, String shi, String qu, 
    		String juti, String name, String tel) throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql;
			java.sql.PreparedStatement pst;
			java.sql.ResultSet rs;
		    if(!"".equals(sheng)) {
		    	sql="update address set add_sheng = ? where add_id = ?";
		    	pst=conn.prepareStatement(sql);
		    	pst.setString(1, sheng);
		    	pst.setInt(2, add.getAdd_id());
		    	pst.execute();
		        }
		    if(!"".equals(shi)) {
		    	sql="update address set add_shi = ? where add_id = ?";
		    	pst=conn.prepareStatement(sql);
		    	pst.setString(1, shi);
		    	pst.setInt(2, add.getAdd_id());
		    	pst.execute();
		    }
		    if(!"".equals(qu)) {
		    	sql="update address set add_qu = ? where add_id = ?";
		    	pst=conn.prepareStatement(sql);
		    	pst.setString(1, qu);
		    	pst.setInt(2, add.getAdd_id());
		    	pst.execute();
		    }
		    if(!"".equals(juti)) {
		    	sql="update address set add_juti = ? where add_id = ?";
		    	pst=conn.prepareStatement(sql);
		    	pst.setString(1, juti);
		    	pst.setInt(2, add.getAdd_id());
		    	pst.execute();
		    }
		    if(!"".equals(name)) {
		    	sql="update address set add_name = ? where add_id = ?";
		    	pst=conn.prepareStatement(sql);
		    	pst.setString(1, name);
		    	pst.setInt(2, add.getAdd_id());
		    	pst.execute();
		    }
		    if(!"".equals(tel)) {
		    	sql="update address set add_tel = ? where add_id = ?";
		    	pst=conn.prepareStatement(sql);
		    	pst.setString(1, tel);
		    	pst.setInt(2, add.getAdd_id());
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
		AddressManager ad = new AddressManager();
		try {
			ad.AddAddress("11", "22", "2", "2", "232", "1111");
		} catch (BaseException e) {
			e.printStackTrace();
		}
	}
	
}
