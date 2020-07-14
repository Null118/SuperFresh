package superfresh.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import superfresh.model.BeanCoupon;
import superfresh.model.BeanGm;
import superfresh.model.BeanMenu;
import superfresh.util.BaseException;
import superfresh.util.BusinessException;
import superfresh.util.DBUtil;
import superfresh.util.DbException;

public class MenuManager {
	public static BeanMenu currentMenu = null;
	
	public List<BeanMenu> loadAll() throws BaseException {
		List<BeanMenu> result=new ArrayList<BeanMenu>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from menu";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanMenu p=new BeanMenu();
				p.setMen_id(rs.getInt(1));
				p.setMen_name(rs.getString(2));
				p.setFre_id(rs.getInt(3));
				p.setMen_step(rs.getString(4));
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

	
	public void AddMenu(String name, int fre_id, String step) throws BaseException{
    	if("".equals(name)||name==null||"".equals(step)||step==null)
    		throw new BusinessException("步骤或名称不能为空！");
    	Connection conn=null;
    	try {
    		conn=DBUtil.getConnection();
    		String sql="select * from fresh where fre_id = ?";
    		java.sql.PreparedStatement pst=conn.prepareStatement(sql);
    		pst.setInt(1, fre_id);
    		java.sql.ResultSet rs = pst.executeQuery();
    		if(!rs.next()) {
    			rs.close();
    			pst.close();
    			throw new BusinessException("请输入正确的生鲜编号！");
    		}
    		rs.close();
			pst.close();
    		sql="insert into menu(men_name, fre_id, men_step) values(?,?,?)";
			pst=conn.prepareStatement(sql);
    		pst.setString(1, name);
    		pst.setInt(2,fre_id);
    		pst.setString(3,step);
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
	
	public void ChaMenu(BeanMenu mm, String name, int fre_id, String step) throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql;
			java.sql.PreparedStatement pst;
			java.sql.ResultSet rs;
			int men_id = mm.getMen_id();
			if(!"".equals(name)) {
				sql="update menu set men_name = ? where men_id = ?";
				pst=conn.prepareStatement(sql);
		    	pst.setString(1, name);
		    	pst.setInt(2, men_id);
		    	pst.execute();
			}
			if(fre_id!=-1) {
				sql="update menu set fre_id = ? where men_id = ?";
				pst=conn.prepareStatement(sql);
		    	pst.setInt(1, fre_id);
		    	pst.setInt(2, men_id);
		    	pst.execute();
			}
			if(!"".equals(step)) {
				sql="update menu set men_step = ? where men_id = ?";
				pst=conn.prepareStatement(sql);
		    	pst.setString(1, step);
		    	pst.setInt(2, men_id);
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
	
	
	public void DeleteMenu(BeanMenu men) throws BaseException{
		int men_id = men.getMen_id();
		Connection conn=null;
		if(GmManager.currentGm==null)  throw new BusinessException("需要管理员权限！");
		try {
			conn=DBUtil.getConnection();
			String sql="select * from recommend where men_id = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, men_id);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next())  {
				pst.close();
				rs.close();
				throw new BusinessException("存在推荐，无法删除！");
			}
			pst.close();
			rs.close();
			sql="delete from menu where men_id = ?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, men_id);
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
	
	
	public static void main(String[] args) {
		MenuManager xx = new MenuManager();
		try {
			xx.AddMenu("dby", 2, "qaq");;
		} catch (BaseException e) {
			e.printStackTrace();
		}
	}
}
