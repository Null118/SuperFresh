package superfresh.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import superfresh.model.BeanBuy;
import superfresh.model.BeanManzinfo;
import superfresh.model.BeanRecommend;
import superfresh.util.BaseException;
import superfresh.util.BusinessException;
import superfresh.util.DBUtil;
import superfresh.util.DbException;

public class BuyManager {
	
	
	public List<BeanBuy> loadAll() throws BaseException {
		List<BeanBuy> result=new ArrayList<BeanBuy>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from buy";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanBuy p = new BeanBuy();
				p.setBuy_id(rs.getInt(1));
				p.setCom_id(rs.getInt(2));
				//p.setMz_id(rs.getInt(3));
				//p.setCx_id(rs.getInt(4));
				p.setBuy_count(rs.getInt(5));
				p.setBuy_suit(rs.getString(6));
				p.setFlag(rs.getInt(7));
				p.setBuy_sum(rs.getDouble(8));
				p.setBuy_discsum(rs.getDouble(9));
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
	public void AddBuy(int com_id, int mz_id, int cx_id, int count, String situ, int flag) throws BaseException{
		if(count<=0)  throw new BusinessException("商品件数必须大于零！");
		if(flag<=0)  throw new BusinessException("购买批次必须大于零！");
		Connection conn=null;
		double discsum = 0;
		int isVip = 0;
		int com_cnt;
		double price = 0;
		double sum = 0;
		double vp,np,hm=0;
		try {
			conn=DBUtil.getConnection();
			String sql="select user_vip from users where user_id = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
    		pst.setInt(1, UsersManager.currentUser.getUser_id());
    		java.sql.ResultSet rs = pst.executeQuery();
    		if(rs.next()) {
    			if("是".equals(rs.getString(1)))  
    				isVip = 1;
    		}
    		rs.close();
			pst.close();
			sql="select com_price, com_vippri, com_count from commodity where com_id = ?";
    		pst=conn.prepareStatement(sql);
    		pst.setInt(1, com_id);
    		rs = pst.executeQuery();
    		if(!rs.next()) {
    			rs.close();
    			pst.close();
    			throw new BusinessException("请输入正确的商品编号！");
    		}else {
    			if(count>rs.getInt(3))  {
    				rs.close();
        			pst.close();
    				throw new BusinessException("购买件数大于库存！");
    			}
    			com_cnt = rs.getInt(3);
    			if(isVip==0)
    			price = np =rs.getDouble(1);
    			else {
    				price = vp = rs.getDouble(2);
    				hm = discsum+=((rs.getDouble(1)-rs.getDouble(2))*count);
    			}
    		}
    		sum = count*price;
    		rs.close();
			pst.close();
			if(cx_id != -1&&mz_id!=-1) {
			sql="select cx_start_day, cx_end_day, cx_price, cx_count from xianscx where cx_id = ? and com_id = ?";
			pst=conn.prepareStatement(sql);
    		pst.setInt(1, cx_id);
    		pst.setInt(2, com_id);
    		rs = pst.executeQuery();
    		if(!rs.next()) {
    			rs.close();
    			pst.close();
    			throw new BusinessException("请输入正确的促销编号！");
    		}else {
    			Date d1 = rs.getDate(1);
    			Date d2 = rs.getDate(2);
    			Date nowTime = new Date();
    			boolean effectiveDate = isEffectiveDate(nowTime,d1,d2);
    			if(!effectiveDate)  {
    				rs.close();
        			pst.close();
    				throw new BusinessException("不在规定使用时间内！");
    			}else {
    				int cx_count = rs.getInt(4);
    				double cx_price = rs.getDouble(3);
    				if(cx_count<count) {
    					rs.close();
            			pst.close();
            			sql="update xianscx set cx_count = 0 where cx_id = ?";
            			pst=conn.prepareStatement(sql);
                		pst.setInt(1, cx_id);
                		pst.execute();
                		rs.close();
            			pst.close();
            			sum = cx_count*cx_price+(count-cx_count)*price;
            			System.out.println(sum+"   1");
                		discsum+=(price-cx_price)*cx_count;
                		System.out.println(discsum+"   1");
    				}else {
    					rs.close();
            			pst.close();
            			sql="update xianscx set cx_count = ? where cx_id = ?";
            			pst=conn.prepareStatement(sql);
                		pst.setInt(1, cx_count-count);
                		pst.setInt(2, cx_id);
                		pst.execute();
                		rs.close();
            			pst.close();
            			sum = cx_price*count;
                		discsum+=(price-cx_price)*count;
    				}
    			}
    		}
    		sql="select mz_start_day, mz_end_day, mz_count, mz_discount from manzinfo where mz_id = ? and com_id =?";
    		pst=conn.prepareStatement(sql);
    		pst.setInt(1, mz_id);
    		pst.setInt(2, com_id);
    		rs = pst.executeQuery();
    		if(!rs.next()) {
    			rs.close();
    			pst.close();
    			throw new BusinessException("请输入正确的满折编号！");
    		}else {
    			Date d1 = rs.getDate(1);
    			Date d2 = rs.getDate(2);
    			Date nowTime = new Date();
    			boolean effectiveDate = isEffectiveDate(nowTime,d1,d2);
    			if(!effectiveDate)  {
    				rs.close();
        			pst.close();
    				throw new BusinessException("不在规定使用时间内！");
    			}else {
    				if(count>=rs.getInt(3)) {
    					discsum+=((1-rs.getDouble(4))*sum);
    					rs.close();
    	    			pst.close();
    				}
    			}
    		}
    		sql="update commodity set com_count = ? where com_id = ?";
			pst=conn.prepareStatement(sql);
    		pst.setInt(1, com_cnt-count);
    		pst.setInt(2, com_id);
    		pst.execute();
			pst.close();
			
    		sql="insert into buy(com_id,mz_id,cx_id,buy_count,buy_situ,flag,buy_sum,buy_discsum) values(?,?,?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
    		pst.setInt(1, com_id);
    		pst.setInt(2, mz_id);
    		pst.setInt(3, cx_id);
    		pst.setInt(4, count);
    		pst.setString(5, situ);
    		pst.setInt(6, flag);
    		if(isVip==1) {
    			sum = count*price;
    			sum+=hm;
    		    pst.setDouble(7, sum-discsum);
    		    }else
    		    	{
    		    	sum = count*price;
    		    	pst.setDouble(7, sum-discsum);
    		    	}
    		pst.setDouble(8, discsum);
    		pst.execute();
			pst.close();
			}else if(mz_id==-1&&cx_id!=-1) {
				sql="select cx_start_day, cx_end_day, cx_price, cx_count from xianscx where cx_id = ? and com_id = ?";
				pst=conn.prepareStatement(sql);
	    		pst.setInt(1, cx_id);
	    		pst.setInt(2, com_id);
	    		rs = pst.executeQuery();
	    		if(!rs.next()) {
	    			rs.close();
	    			pst.close();
	    			throw new BusinessException("请输入正确的促销编号！");
	    		}else {
	    			Date d1 = rs.getDate(1);
	    			Date d2 = rs.getDate(2);
	    			Date nowTime = new Date();
	    			boolean effectiveDate = isEffectiveDate(nowTime,d1,d2);
	    			if(!effectiveDate)  {
	    				rs.close();
	        			pst.close();
	    				throw new BusinessException("不在规定使用时间内！");
	    			}else {
	    				int cx_count = rs.getInt(4);
	    				double cx_price = rs.getDouble(3);
	    				if(cx_count<count) {
	    					rs.close();
	            			pst.close();
	            			sql="update xianscx set cx_count = 0 where cx_id = ?";
	            			pst=conn.prepareStatement(sql);
	                		pst.setInt(1, cx_id);
	                		pst.execute();
	                		rs.close();
	            			pst.close();
	                		discsum+=(double)(price-cx_price)*cx_count;
	    				}else {
	    					rs.close();
	            			pst.close();
	            			sql="update xianscx set cx_count = ? where cx_id = ?";
	            			pst=conn.prepareStatement(sql);
	                		pst.setInt(1, cx_count-count);
	                		pst.setInt(2, cx_id);
	                		pst.execute();
	                		rs.close();
	            			pst.close();
	                		discsum+=(double)(price-cx_price)*count;
	    				}
	    			}
	    		}
	    		sql="update commodity set com_count = ? where com_id = ?";
				pst=conn.prepareStatement(sql);
	    		pst.setInt(1, com_cnt-count);
	    		pst.setInt(2, com_id);
	    		pst.execute();
				pst.close();
				
	    		sql="insert into buy(com_id,cx_id,buy_count,buy_situ,flag,buy_sum,buy_discsum) values(?,?,?,?,?,?,?)";
				pst=conn.prepareStatement(sql);
	    		pst.setInt(1, com_id);
	    		pst.setInt(2, cx_id);
	    		pst.setInt(3, count);
	    		pst.setString(4, situ);
	    		pst.setInt(5, flag);
	    		if(isVip==1) {
	    			sum = count*price;
	    			sum+=hm;
	    		    pst.setDouble(6, sum-discsum);
	    		    }else {
	    		    	sum = count*price;
	    		    	pst.setDouble(6, sum-discsum);
	    		    }
	    		pst.setDouble(7, discsum);
	    		pst.execute();
				pst.close();
			}else if(mz_id!=-1&&cx_id==-1) {
				sql="select mz_start_day, mz_end_day, mz_count, mz_discount from manzinfo where mz_id = ? and com_id =?";
	    		pst=conn.prepareStatement(sql);
	    		pst.setInt(1, mz_id);
	    		pst.setInt(2, com_id);
	    		rs = pst.executeQuery();
	    		if(!rs.next()) {
	    			rs.close();
	    			pst.close();
	    			throw new BusinessException("请输入正确的满折编号！");
	    		}else {
	    			Date d1 = rs.getDate(1);
	    			Date d2 = rs.getDate(2);
	    			Date nowTime = new Date();
	    			boolean effectiveDate = isEffectiveDate(nowTime,d1,d2);
	    			if(!effectiveDate)  {
	    				rs.close();
	        			pst.close();
	    				throw new BusinessException("不在规定使用时间内！");
	    			}else {
	    				if(count>=rs.getInt(3)) {
	    					discsum+=((1-rs.getDouble(4))*sum);
	    					rs.close();
	    	    			pst.close();
	    				}
	    			}
	    		}
	    		sql="update commodity set com_count = ? where com_id = ?";
				pst=conn.prepareStatement(sql);
	    		pst.setInt(1, com_cnt-count);
	    		pst.setInt(2, com_id);
	    		pst.execute();
				pst.close();
				
	    		sql="insert into buy(com_id,mz_id,buy_count,buy_situ,flag,buy_sum,buy_discsum) values(?,?,?,?,?,?,?)";
				pst=conn.prepareStatement(sql);
	    		pst.setInt(1, com_id);
	    		pst.setInt(2, mz_id);
	    		pst.setInt(3, count);
	    		pst.setString(4, situ);
	    		pst.setInt(5, flag);
	    		if(isVip==1) {
	    			sum = count*price;
	    			sum+=hm;
	    		    pst.setDouble(6, sum-discsum);
	    		    }else {
	    		    	sum = count*price;
	    		    	pst.setDouble(6, sum-discsum);
	    		    }
	    		pst.setDouble(7, discsum);
	    		pst.execute();
				pst.close();
			}else if(mz_id==-1&&cx_id==-1) {
				sql="update commodity set com_count = ? where com_id = ?";
				pst=conn.prepareStatement(sql);
	    		pst.setInt(1, com_cnt-count);
	    		pst.setInt(2, com_id);
	    		pst.execute();
				pst.close();
				
				sql="insert into buy(com_id,buy_count,buy_situ,flag,buy_sum,buy_discsum) values(?,?,?,?,?,?)";
				pst=conn.prepareStatement(sql);
	    		pst.setInt(1, com_id);
	    		pst.setInt(2, count);
	    		pst.setString(3, situ);
	    		pst.setInt(4, flag);
	    		if(isVip==1) {
	    			sum = count*price;
	    			sum+=hm;
	    		    pst.setDouble(5, sum-discsum);
	    		    }else {
	    		    	sum = count*price;
	    		    	pst.setDouble(5, sum-discsum);
	    		    }
	    		pst.setDouble(6, discsum);
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
	
	public void DeleteBuy(BeanBuy bb) throws BaseException{
		if(GmManager.currentGm==null)  throw new BusinessException("需要管理员权限！");
		int buy_id = bb.getBuy_id();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="delete from buy where buy_id = ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, buy_id);
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
