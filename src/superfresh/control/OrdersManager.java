package superfresh.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import superfresh.model.BeanBuy;
import superfresh.model.BeanOrders;
import superfresh.util.BaseException;
import superfresh.util.BusinessException;
import superfresh.util.DBUtil;
import superfresh.util.DbException;

public class OrdersManager {

	public static int getSumOrd(int user_id)throws BaseException{
		Connection conn=null;
		int result = 0;
		try {
			conn=DBUtil.getConnection();
			String sql="select count(ord_id) from orders group by user_id having user_id =?";
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
	
	
	public static double getSumJine(int user_id)throws BaseException{
		Connection conn=null;
		double result = 0;
		try {
			conn=DBUtil.getConnection();
			String sql="select sum(ord_sum_jine) from orders group by user_id having user_id =?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, user_id);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				result = rs.getDouble(1);
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
	
	
	
	public static double getSumYouhui(int user_id)throws BaseException{
		Connection conn=null;
		double result = 0;
		try {
			conn=DBUtil.getConnection();
			String sql="select sum(ord_init_jine-ord_sum_jine) from orders group by user_id having user_id =?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, user_id);
			java.sql.ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				result = rs.getDouble(1);
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
	
	
	
	
	
	public void AddOrd (int flag, int cou_id, int add_id, int user_id, String ord_ttl, String ord_situ) throws BaseException{
		double sum, discsum;
		Date sd,ed;
		double suit_jine, dis_jine;
		Connection conn=null;
		if(GmManager.currentGm!=null)  throw new BusinessException("只有用户可以下单购买！");
		if(flag<=0)  throw new BusinessException("该批商品已经结算过！");
		try {
			conn=DBUtil.getConnection();
			String sql="select buy_sum, buy_discsum from buy where flag = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
    		pst.setInt(1, flag);
    		java.sql.ResultSet rs = pst.executeQuery();
    		if(!rs.next()) {
    			rs.close();
    			pst.close();
    			throw new BusinessException("该批次没有需要结算的商品！");
    		}else {
    			sum = rs.getDouble(1);
    			discsum = rs.getDouble(2);
    			rs.close();
    			pst.close();
    		}
    		if(cou_id!=-1) {
    		sql="select cou_start_day, cou_end_day,cou_suit_jine, cou_dis_jine, cou_cishu from coupon where cou_id = ? and user_id = ?";
    		pst=conn.prepareStatement(sql);
    		pst.setInt(1, cou_id);
    		pst.setInt(2, user_id);
    		rs = pst.executeQuery();
    		if(!rs.next()) {
    			rs.close();
    			pst.close();
    			throw new BusinessException("请输入正确的优惠券编号！");
    		}else {
    			if(rs.getInt(5)<=0) {
    				rs.close();
        			pst.close();
    				throw new BusinessException("优惠券已无使用次数！");
    			}
    			sd = rs.getDate(1);
    			ed = rs.getDate(2);
    			Date nowTime = new Date();
    			boolean effectiveDate = isEffectiveDate(nowTime,sd,ed);
    			if(!effectiveDate)  {
    				rs.close();
        			pst.close();
    				throw new BusinessException("不在规定使用时间内！");
    				}else {
    					suit_jine = rs.getDouble(3);
    					dis_jine = rs.getDouble(4);
    					rs.close();
    			        pst.close();
    			}
    		}
    		sql="select * from address where add_id = ? and user_id = ?";
    		pst=conn.prepareStatement(sql);
    		pst.setInt(1, add_id);
    		pst.setInt(2, user_id);
    		rs = pst.executeQuery();
    		if(!rs.next()) {
    			rs.close();
    			pst.close();
    			throw new BusinessException("请输入正确的地址编号！");
    		}
    		rs.close();
			pst.close();
			
			sql="select sum(buy_sum), sum(buy_discsum) from buy group by flag having flag = ?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, flag);
			rs = pst.executeQuery();
			if(rs.next()) {
			sum = rs.getDouble(1);
			discsum = rs.getDouble(2);}
    		rs.close();
			pst.close();
			if(sum+discsum>=suit_jine) {
				sql="update coupon set cou_cishu = cou_cishu-1 where cou_id = ?";
				pst=conn.prepareStatement(sql);
				pst.setInt(1, cou_id);
				pst.execute();
				pst.close();
			}
			sql="insert into orders(cou_id,add_id,user_id,ord_init_jine,"
					+ "ord_sum_jine,ord_ttl,ord_situ) values(?,?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, cou_id);
			pst.setInt(2, add_id);
			pst.setInt(3, user_id);
			pst.setDouble(4, sum+discsum);
			if(sum+discsum>=suit_jine) {
				pst.setDouble(5, sum-dis_jine);
			}else
			pst.setDouble(5, sum);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d1 = new Date();
			try {
				d1 = sdf.parse(ord_ttl);
				pst.setTimestamp(6, new java.sql.Timestamp(d1.getTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
    		pst.setString(7, ord_situ);
    		pst.execute();
    		pst.close();
    		sql="update buy set flag = -flag where flag = ?";
    		pst=conn.prepareStatement(sql);
			pst.setInt(1, flag);
			pst.execute();
    		pst.close();
    		
    		}else {
    			sql="select * from address where add_id = ? and user_id = ?";
        		pst=conn.prepareStatement(sql);
        		pst.setInt(1, add_id);
        		pst.setInt(2, user_id);
        		rs = pst.executeQuery();
        		if(!rs.next()) {
        			rs.close();
        			pst.close();
        			throw new BusinessException("请输入正确的地址编号！");
        		}
        		rs.close();
    			pst.close();
    			
    			sql="select sum(buy_sum), sum(buy_discsum) from buy group by flag having flag = ?";
    			pst=conn.prepareStatement(sql);
    			pst.setInt(1, flag);
    			rs = pst.executeQuery();
    			if(rs.next()) {
    			sum = rs.getDouble(1);
    			discsum = rs.getDouble(2);}
        		rs.close();
    			pst.close();
    			sql="insert into orders(add_id,user_id,ord_init_jine,"
    					+ "ord_sum_jine,ord_ttl,ord_situ) values(?,?,?,?,?,?)";
    			pst=conn.prepareStatement(sql);
    			pst.setInt(1, add_id);
    			pst.setInt(2, user_id);
    			pst.setDouble(3, sum+discsum);
    			pst.setDouble(4, sum);
    			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    			Date d1 = new Date();
    			try {
    				d1 = sdf.parse(ord_ttl);
    				pst.setTimestamp(5, new java.sql.Timestamp(d1.getTime()));
    			} catch (ParseException e) {
    				e.printStackTrace();
    			}
        		pst.setString(6, ord_situ);
        		pst.execute();
        		pst.close();	
        		sql="update buy set flag = -flag where flag = ?";
        		pst=conn.prepareStatement(sql);
    			pst.setInt(1, flag);
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
	
	
	
	public void DeleteOrd(BeanOrders oo) throws BaseException{
		if(GmManager.currentGm==null)  throw new BusinessException("需要管理员权限！");
		int ord_id = oo.getOrd_id();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="delete from orders where ord_id = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, ord_id);
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
	
	
	
	public List<BeanOrders> loadAll() throws BaseException {
		List<BeanOrders> result=new ArrayList<BeanOrders>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from orders";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanOrders p = new BeanOrders();
				p.setOrd_id(rs.getInt(1));
				p.setCou_id(rs.getInt(2));
				p.setAdd_id(rs.getInt(3));
				p.setUser_id(rs.getInt(4));
				p.setOrd_init_jine(rs.getDouble(5));;
				p.setOrd_sum_jine(rs.getDouble(6));;
				p.setOrd_ttl(rs.getTimestamp(7));
				p.setOrd_situ(rs.getString(8));
				result.add(p);}
			}
		catch (SQLException e) {
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
	
	
	
	public static void main(String [] args) {
		OrdersManager oo = new OrdersManager();
		try {
			oo.AddOrd(777, 4, 10, 1, "2020-07-11 12:30:30", "");
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }
}
