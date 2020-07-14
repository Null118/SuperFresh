package superfresh.control;

//登陆，添加，删除（有管理员采购数据无法删除），修改密码 COMPLETE

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import superfresh.model.BeanGm;
import superfresh.util.BaseException;
import superfresh.util.BusinessException;
import superfresh.util.DBUtil;
import superfresh.util.DbException;

public class GmManager {
	public static BeanGm currentGm=null;

	public void AddGm(String name, String pwd) throws BaseException {
		int flag=0;
		Connection conn=null;
		if("".equals(name)||name==null) {
			throw new BusinessException("管理员名不能为空！");
		}
		if("".equals(pwd)||pwd==null) {
			throw new BusinessException("密码不能为空！");
		}
		try {
			conn=DBUtil.getConnection();
			String sql="select * from gm";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) {
				//rs.close();
				pst.close();
				sql="insert into gm values(1,?,?)";
				pst=conn.prepareStatement(sql);
				pst.setString(1, name);
				pst.setString(2,pwd);
				pst.execute();
				flag=1;
			}
			rs.close();
			pst.close();
			if(flag==0) {
			sql="select * from gm where gm_name = ?";
			pst=conn.prepareStatement(sql);
			pst.setString(1,name);
			rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("已存在该管理员！!");
			rs.close();
			pst.close();
			
			sql="insert into gm(gm_name,gm_pwd) values(?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2,pwd);
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
	
	
    public List<BeanGm> loadAll() throws BaseException {
		List<BeanGm> result=new ArrayList<BeanGm>();
		Connection conn=null;
		if(GmManager.currentGm==null)  throw new BusinessException("需要管理员权限！");
		try {
			conn=DBUtil.getConnection();
			String sql="select * from gm ";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanGm p=new BeanGm();
				p.setGm_id(rs.getInt(1));
				p.setGm_name(rs.getString(2));
				p.setGm_pwd(rs.getString(3));
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
	
	
	public BeanGm login(String name, String pwd) throws BaseException{
		Connection conn=null;
		BeanGm g = new BeanGm();
		try {
			conn=DBUtil.getConnection();
			String sql="select gm_pwd,gm_id from gm where gm_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,name);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("请输入正确的管理员名");
			if(!rs.getString(1).equals(pwd))  throw new BusinessException("密码错误！");
			g.setGm_id(rs.getInt(2));
			g.setGm_name(name);
			g.setGm_pwd(pwd);
			rs.close();
			pst.close();
			return g;
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
	
	
	
	public void changePwd(String name, String oldPwd, String newPwd,
			String newPwd2) throws BaseException {
		if(oldPwd==null) throw new BusinessException("原密码不能为空！");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select gm_pwd from gm where gm_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,name);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("请输入正确的管理员名！");
			if(!oldPwd.equals(rs.getString(1))) throw new BusinessException("原密码错误！");
			if(!newPwd.equals(newPwd2))  throw new BusinessException("两次输入的密码请一致！");
			rs.close();
			pst.close();
			sql="update gm set gm_pwd=? where gm_name=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, newPwd);
			pst.setString(2, name);
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
	}
	
	
	
	public void DeleteGm(BeanGm g) throws BaseException {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql = "select * from gmord where gm_id = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,g.getGm_id());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("存在购买记录，无法删除！");
			rs.close();
			pst.close();
			sql = "delete from gm where gm_id = ?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,g.getGm_id());
			pst.execute();
			pst.close();
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	public static void main(String[] args) {
		GmManager gmm = new GmManager();
		try {
			gmm.AddGm("lcyyy", "123");;
		} catch (BaseException e) {
			e.printStackTrace();
		}
	}
}
