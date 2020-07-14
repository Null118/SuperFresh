package superfresh.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import superfresh.model.BeanOrders;
import superfresh.model.BeanTJU;
import superfresh.util.BaseException;
import superfresh.util.DBUtil;
import superfresh.util.DbException;

public class SumManager {
	public List<BeanTJU> loadAll() throws BaseException {
		List<BeanTJU> result=new ArrayList<BeanTJU>();
		List<Integer> user=new ArrayList<Integer>();
		Connection conn=null;
		int i = 0;
		try {
			conn=DBUtil.getConnection();
			String sql="select user_id from users group by user_id";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				user.add(rs.getInt(1));
			}
			for(i=0;i<user.size();i++) {
				BeanTJU p = new BeanTJU();
				p.setUser_id(user.get(i).intValue());
				p.setSum_ord(OrdersManager.getSumOrd(user.get(i).intValue()));
				p.setSum_jine(OrdersManager.getSumJine(user.get(i).intValue()));
				p.setSum_jine(OrdersManager.getSumYouhui(user.get(i).intValue()));
				p.setSum_pingjia(EvaluateManager.getSumPingjia(user.get(i).intValue()));
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
