package superfresh.control;

//添加（注册），登陆，修改密码 
//少删除
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import superfresh.model.BeanGm;
import superfresh.model.BeanUsers;
import superfresh.util.BaseException;
import superfresh.util.BusinessException;
import superfresh.util.DBUtil;
import superfresh.util.DbException;

public class UsersManager {
	public static BeanUsers currentUser=null;
	
	public void AddUsers(String name, String sex, String pwd, String tel, String mail, 
			String city, String vip, String end_day) throws BaseException {
		int flag=0;
		Connection conn=null;
		if("".equals(name)||name==null||"".equals(pwd)||pwd==null||"".equals(tel)||tel==null
				||"".equals(mail)||mail==null||"".equals(city)||city==null) {
			throw new BusinessException("任意输入不能为空！");
		}if(!("男".equals(sex)||"女".equals(sex)))  throw new BusinessException("输入正确的性别！");
		if(!("No".equals(vip)||"Yes".equals(vip)))  throw new BusinessException("输入正确的vip选项！");
		try {
			conn=DBUtil.getConnection();
			String sql="select * from users";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) {
				//rs.close();
				pst.close();
				sql="insert into users values(1,?,?,?,?,?,?,?,?,?)";
				pst=conn.prepareStatement(sql);
				pst.setString(1, name);
				pst.setString(2,sex);
				pst.setString(3,pwd);
				pst.setString(4,tel);
				pst.setString(5,mail);
				pst.setString(6,city);
				pst.setDate(7, new java.sql.Date(System.currentTimeMillis()));
				pst.setString(8, vip);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date d1 = new Date();
					d1 = sdf.parse(end_day);
					pst.setDate(9, new java.sql.Date(d1.getTime()));
				} catch (ParseException e) {
					e.printStackTrace();
				} 

				pst.execute();
				flag=1;
			}
			rs.close();
			pst.close();
			if(flag==0) {
			sql="select * from users where user_name = ?";
			pst=conn.prepareStatement(sql);
			pst.setString(1,name);
			rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("已存在该用户!");
			rs.close();
			pst.close();
			
			sql="insert into users(user_name,user_sex,user_pwd,user_tel,user_mail,"
					+ "user_city,user_reg_day,user_vip,user_vip_end) values(?,?,?,?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2,sex);
			pst.setString(3,pwd);
			pst.setString(4,tel);
			pst.setString(5,mail);
			pst.setString(6,city);
			pst.setDate(7, new java.sql.Date(System.currentTimeMillis()));
			pst.setString(8, vip);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date d1 = new Date();
				d1 = sdf.parse(end_day);
				pst.setDate(9, new java.sql.Date(d1.getTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			} 
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
	
	
	public BeanUsers login(String name, String pwd) throws BaseException{
		Connection conn=null;
		BeanUsers g = new BeanUsers();
		try {
			conn=DBUtil.getConnection();
			String sql="select user_pwd,user_id from users where user_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,name);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("请输入正确的用户名");
			if(!rs.getString(1).equals(pwd))  throw new BusinessException("密码错误！");
			g.setUser_id(rs.getInt(2));
			g.setUser_name(name);
			g.setUser_pwd(pwd);
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
			String sql="select user_pwd from users where user_name=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,name);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("请输入正确的用户名！");
			if(!oldPwd.equals(rs.getString(1))) throw new BusinessException("原密码错误！");
			if(!newPwd.equals(newPwd2))  throw new BusinessException("两次输入的密码请一致！");
			rs.close();
			pst.close();
			sql="update users set user_pwd=? where user_name=?";
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
	
	
	public void DeleteUser(BeanUsers bb) throws BaseException{
		int user_id = bb.getUser_id();
		Connection conn=null;
		if(GmManager.currentGm==null)  throw new BusinessException("需要管理员权限！");
		try {
			conn=DBUtil.getConnection();
			/*String sql="SELECT * from address a, coupon c, evaluate e, orders o, users u\r\n" + 
					"where a.user_id=u.user_id and c.user_id=u.user_id and e.user_id=u.user_id and o.user_id=u.user_id and user_id = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,user_id);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next())  {
				pst.close();
				rs.close();
				throw new BusinessException("有相关记录，不能删除！！");
			}
			pst.close();
			rs.close();*/
			String sql="delete from users where user_id = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, user_id);
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
	
	
	
	public List<BeanUsers> loadAll() throws BaseException {
		List<BeanUsers> result=new ArrayList<BeanUsers>();
		if(GmManager.currentGm==null)  throw new BusinessException("需要管理员权限！");
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from users";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanUsers p=new BeanUsers();
				p.setUser_id(rs.getInt(1));
				p.setUser_name(rs.getString(2));
				p.setUser_sex(rs.getString(3));
				p.setUser_pwd(rs.getString(4));
				p.setUser_tel(rs.getString(5));
				p.setUser_mail(rs.getString(6));
				p.setUser_city(rs.getString(7));
				p.setReg_day(rs.getDate(8));
				p.setUser_vip(rs.getString(9));
				p.setUser_vip_end(rs.getString(10));
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
	
	
	
	
	public static void main(String[] args) {
		UsersManager comm = new UsersManager();
		try {
			comm.changePwd("skd111", "skd2", "s2", "s2");
		} catch (BaseException e) {
			e.printStackTrace();
		}
	}
}
