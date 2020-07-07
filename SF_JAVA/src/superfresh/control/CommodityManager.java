package superfresh.control;

//添加商品


import java.sql.Connection;
import java.sql.SQLException;

import superfresh.util.BaseException;
import superfresh.util.BusinessException;
import superfresh.util.DBUtil;
import superfresh.util.DbException;

public class CommodityManager {
    
	public void AddCommodity(String name, double price, double vipprice,
    		int com_count, String guige, String juti) throws BaseException {
    	if(name==null||"".equals(name)||price<0||vipprice<0||com_count<0) {
    		throw new BusinessException("输入数据非法！");
    	}
    	Connection conn=null;
    	int flag=0;
    	try {
    		conn=DBUtil.getConnection();
    		String sql="select * from commodity";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) {
				//rs.close();
				pst.close();
				sql="insert into commodity values(1,?,?,?,?,?,?)";
				pst=conn.prepareStatement(sql);
				pst.setString(1, name);
				pst.setDouble(2,price);
				pst.setDouble(3,vipprice);
				pst.setInt(4,com_count);
				pst.setString(5,guige);
				pst.setString(6,juti);
				pst.execute();
				flag=1;
			}
			rs.close();
			pst.close();
    		if(flag==0) {
    		sql="select * from commodity where com_name = ?";
			pst=conn.prepareStatement(sql);
			pst.setString(1,name);
			rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("已存在该商品!");
			rs.close();
			pst.close();
    		
			sql="insert into commodity(com_name,com_price,com_vippri,"
					+ "com_count,com_guige,com_juti) values(?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setDouble(2,price);
			pst.setDouble(3,vipprice);
			pst.setInt(4,com_count);
			pst.setString(5,guige);
			pst.setString(6,juti);
			pst.execute();
			pst.close();
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
    
    
    public void DeleteCommodity(String name) throws BaseException{
    	if(name==null||"".equals(name))  throw new BusinessException("商品名不能为空!");
    	Connection conn=null;
    	try {
    		conn=DBUtil.getConnection();
    		String sql="select * from commodity where com_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,name);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("请输入正确的商品名！ ");
			rs.close();
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
