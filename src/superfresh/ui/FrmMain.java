package superfresh.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import superfresh.control.AddressManager;
import superfresh.control.BuyManager;
import superfresh.control.CommodityManager;
import superfresh.control.CouPonManager;
import superfresh.control.EvaluateManager;
import superfresh.control.FreshManager;
import superfresh.control.GmManager;
import superfresh.control.GmordManager;
import superfresh.control.MenuManager;
import superfresh.control.MzinfoManager;
import superfresh.control.OrdersManager;
import superfresh.control.RecManager;
import superfresh.control.SumManager;
import superfresh.control.TJC_Manager;
import superfresh.control.UsersManager;
import superfresh.control.XscxManager;
import superfresh.model.BeanAddress;
import superfresh.model.BeanBuy;
import superfresh.model.BeanCommodity;
import superfresh.model.BeanCoupon;
import superfresh.model.BeanEvaluate;
import superfresh.model.BeanFresh;
import superfresh.model.BeanGm;
import superfresh.model.BeanGmord;
import superfresh.model.BeanManzinfo;
import superfresh.model.BeanMenu;
import superfresh.model.BeanOrders;
import superfresh.model.BeanRecommend;
import superfresh.model.BeanTJC;
import superfresh.model.BeanTJU;
import superfresh.model.BeanUsers;
import superfresh.model.BeanXianscx;
import superfresh.util.BaseException;
import superfresh.util.BusinessException;
import superfresh.ui.FrmAddAdd;;


public class FrmMain extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JMenuBar menubar=new JMenuBar(); ;
    private JMenu menu_users=new JMenu("用户");
    private JMenu menu_youhui=new JMenu("优惠");
    private JMenu menu_jilu=new JMenu("消费记录");
    private JMenu menu_commo=new JMenu("商品");
    private JMenu menu_add=new JMenu("地址 ");
    private JMenu menu_fresh=new JMenu("生鲜 ");
    private JMenu menu_menu=new JMenu("菜谱 ");
    private JMenu menu_tongji=new JMenu("统计 ");
	
    private JMenuItem  menuItem_AddAdd=new JMenuItem("添加地址");
    private JMenuItem  menuItem_DeleteAdd=new JMenuItem("删除地址");
    private JMenuItem  menuItem_LookAdd=new JMenuItem("查看地址");
    private JMenuItem  menuItem_Chaowd_user=new JMenuItem("修改密码");
    private JMenuItem  menuItem_AddGm=new JMenuItem("添加管理员");
    private JMenuItem  menuItem_DeleteGm=new JMenuItem("删除管理员");
    private JMenuItem  menuItem_LookGm=new JMenuItem("查看管理员");
    private JMenuItem  menuItem_LookUsers=new JMenuItem("查看用户");
    private JMenuItem  menuItem_LookGmord=new JMenuItem("查看管理员订单");
    private JMenuItem  menuItem_DeleteGmord=new JMenuItem("删除管理员订单");
    private JMenuItem  menuItem_LookCommo=new JMenuItem("查看商品");
    private JMenuItem  menuItem_AddCommo=new JMenuItem("添加商品");
    private JMenuItem  menuItem_BuyCommo=new JMenuItem("购买");
    private JMenuItem  menuItem_LookFresh=new JMenuItem("查看生鲜信息");
    private JMenuItem  menuItem_AddFresh=new JMenuItem("添加生鲜");
    private JMenuItem  menuItem_DeleteFresh=new JMenuItem("删除生鲜");
    private JMenuItem  menuItem_LookCou=new JMenuItem("查看优惠券");
    private JMenuItem  menuItem_AddCou=new JMenuItem("添加优惠券");
    private JMenuItem  menuItem_DeleteCou=new JMenuItem("删除优惠券");
    private JMenuItem  menuItem_LookXscx=new JMenuItem("查看限时促销");
    private JMenuItem  menuItem_AddXscx=new JMenuItem("添加限时促销");
    private JMenuItem  menuItem_DeleteXscx=new JMenuItem("删除限时促销");
    private JMenuItem  menuItem_LookMenu=new JMenuItem("查看菜谱");
    private JMenuItem  menuItem_AddMenu=new JMenuItem("添加菜谱");
    private JMenuItem  menuItem_DeleteMenu=new JMenuItem("删除菜谱");
    private JMenuItem  menuItem_LookRec=new JMenuItem("查看推荐菜谱");
    private JMenuItem  menuItem_DeleteRec=new JMenuItem("删除菜谱推荐");
    private JMenuItem  menuItem_AddRec=new JMenuItem("推荐菜谱");
    private JMenuItem  menuItem_LookEva=new JMenuItem("查看评价");
    private JMenuItem  menuItem_DeleteEva=new JMenuItem("删除评价");
    private JMenuItem  menuItem_AddEva=new JMenuItem("添加评价");
    private JMenuItem  menuItem_LookMzinfo=new JMenuItem("查看满折信息");
    private JMenuItem  menuItem_AddMzinfo=new JMenuItem("添加满折信息");
    private JMenuItem  menuItem_DeleteMzinfo=new JMenuItem("删除满折信息");
    private JMenuItem  menuItem_LookBuy=new JMenuItem("查看购买详情");
    private JMenuItem  menuItem_DeleteBuy=new JMenuItem("删除购买");
    private JMenuItem  menuItem_jiesuan=new JMenuItem("结算下单");
    private JMenuItem  menuItem_LookOrd=new JMenuItem("查看用户订单");
    private JMenuItem  menuItem_DeleteOrd=new JMenuItem("删除用户订单");
    private JMenuItem  menuItem_LookUserB=new JMenuItem("统计用户购买明细");
    private JMenuItem  menuItem_LookTJC=new JMenuItem("统计商品信息明细");
    private JMenuItem  menuItem_ChaAdd=new JMenuItem("修改地址");
    private JMenuItem  menuItem_DeleteUser=new JMenuItem("删除用户");
    private JMenuItem  menuItem_ChaCou=new JMenuItem("修改优惠券");
    private JMenuItem  menuItem_ChaMz=new JMenuItem("修改满折活动");
    private JMenuItem  menuItem_ChaCx=new JMenuItem("修改促销活动");
    private JMenuItem  menuItem_ChaCommo=new JMenuItem("修改商品信息");
    private JMenuItem  menuItem_DeleteCommo=new JMenuItem("删除商品");
    private JMenuItem  menuItem_ChaFre=new JMenuItem("修改生鲜信息");
    private JMenuItem  menuItem_ChaMenu=new JMenuItem("修改菜谱信息");
    
	private FrmLogin dlgLogin=null;
	private JPanel statusBar = new JPanel();
	
	
	
	private Object tblAddTitle[]=BeanAddress.tableTitles;
	private Object tblAddData[][];
	DefaultTableModel tabAddModel=new DefaultTableModel();
	private JTable dataTableAdd=new JTable(tabAddModel);
	private BeanAddress curAdd=null;
	List<BeanAddress> allAdd=null;
	private void reloadAddTable(){
		
		try {
			AddressManager a = new AddressManager();
			allAdd=a.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblAddData =  new Object[allAdd.size()][BeanAddress.tableTitles.length];
		for(int i=0;i<allAdd.size();i++){
			for(int j=0;j<BeanAddress.tableTitles.length;j++)
				tblAddData[i][j]=allAdd.get(i).getCell(j);
		}
		tabAddModel.setDataVector(tblAddData,tblAddTitle);
		this.dataTableAdd.validate();
		this.dataTableAdd.repaint();
	}
	
	/*private void getAddIndex(int Idx){
		if(Idx<0) return;
		curAdd=allAdd.get(Idx);
	}*/
	
	
	private Object tblGmTitle[]=BeanGm.tableTitles;
	private Object tblGmData[][];
	private BeanGm curGm=null;
	List<BeanGm> allGm=null;
    private void reloadGmTable(){
		
		try {
			GmManager a = new GmManager();
			allGm=a.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblGmData =  new Object[allGm.size()][BeanGm.tableTitles.length];
		for(int i=0;i<allGm.size();i++){
			for(int j=0;j<BeanGm.tableTitles.length;j++)
				tblGmData[i][j]=allGm.get(i).getCell(j);
		}
		tabAddModel.setDataVector(tblGmData,tblGmTitle);
		this.dataTableAdd.validate();
		this.dataTableAdd.repaint();
	}
	
	/*private void getGmIndex(int Idx){
		if(Idx<0) return;
		curGm=allGm.get(Idx);
	}*/
	
	
	private Object tblUserTitle[]=BeanUsers.tableTitles;
	private Object tblUserData[][];
	private BeanUsers curUser=null;
	List<BeanUsers> allUser=null;
    private void reloadUserTable(){
		
		try {
			UsersManager a = new UsersManager();
			allUser=a.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblUserData =  new Object[allUser.size()][BeanUsers.tableTitles.length];
		for(int i=0;i<allUser.size();i++){
			for(int j=0;j<BeanUsers.tableTitles.length;j++)
				tblUserData[i][j]=allUser.get(i).getCell(j);
		}
		tabAddModel.setDataVector(tblUserData,tblUserTitle);
		this.dataTableAdd.validate();
		this.dataTableAdd.repaint();
	}
	
	/*private void getUserIndex(int Idx){
		if(Idx<0) return;
		curUser=allUser.get(Idx);
	}*/
	
	
	private Object tblGmoTitle[]=BeanGmord.tableTitles;
	private Object tblGmoData[][];
	private BeanGmord curGmo=null;
	List<BeanGmord> allGmo=null;
    private void reloadGmordTable(){
		
		try {
			GmordManager a = new GmordManager();
			allGmo=a.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblGmoData =  new Object[allGmo.size()][BeanGmord.tableTitles.length];
		for(int i=0;i<allGmo.size();i++){
			for(int j=0;j<BeanGmord.tableTitles.length;j++)
				tblGmoData[i][j]=allGmo.get(i).getCell(j);
		}
		tabAddModel.setDataVector(tblGmoData,tblGmoTitle);
		this.dataTableAdd.validate();
		this.dataTableAdd.repaint();
	}
	
	/*private void getGmoIndex(int Idx){
		if(Idx<0) return;
		curGmo=allGmo.get(Idx);
	}*/
	
	
	
	private Object tblCommoTitle[]=BeanCommodity.tableTitles;
	private Object tblCommoData[][];
	private BeanCommodity curCommo=null;
	List<BeanCommodity> allCommo=null;
    private void reloadCommoTable(){
		
		try {
			CommodityManager a = new CommodityManager();
			allCommo=a.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblCommoData =  new Object[allCommo.size()][BeanCommodity.tableTitles.length];
		for(int i=0;i<allCommo.size();i++){
			for(int j=0;j<BeanCommodity.tableTitles.length;j++)
				tblCommoData[i][j]=allCommo.get(i).getCell(j);
		}
		tabAddModel.setDataVector(tblCommoData,tblCommoTitle);
		this.dataTableAdd.validate();
		this.dataTableAdd.repaint();
	}
	
	/*private void getCommoIndex(int Idx){
		if(Idx<0) return;
		curCommo=allCommo.get(Idx);
	}*/
	
	
	
	private Object tblFreshTitle[]=BeanFresh.tableTitles;
	private Object tblFreshData[][];
	private BeanFresh curFresh=null;
	List<BeanFresh> allFresh=null;
    private void reloadFreshTable(){
		
		try {
			FreshManager a = new FreshManager();
			allFresh=a.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblFreshData =  new Object[allFresh.size()][BeanFresh.tableTitles.length];
		for(int i=0;i<allFresh.size();i++){
			for(int j=0;j<BeanFresh.tableTitles.length;j++)
				tblFreshData[i][j]=allFresh.get(i).getCell(j);
		}
		tabAddModel.setDataVector(tblFreshData,tblFreshTitle);
		this.dataTableAdd.validate();
		this.dataTableAdd.repaint();
	}
	
	/*private void getFreshIndex(int Idx){
		if(Idx<0) return;
		curFresh=allFresh.get(Idx);
	}*/
	
	
	
	private Object tblCouTitle[]=BeanCoupon.tableTitles;
	private Object tblCouData[][];
	private BeanCoupon curCou=null;
	List<BeanCoupon> allCou=null;
    private void reloadCouTable(){
		
		try {
			CouPonManager a = new CouPonManager();
			allCou=a.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblCouData =  new Object[allCou.size()][BeanCoupon.tableTitles.length];
		for(int i=0;i<allCou.size();i++){
			for(int j=0;j<BeanCoupon.tableTitles.length;j++)
				tblCouData[i][j]=allCou.get(i).getCell(j);
		}
		tabAddModel.setDataVector(tblCouData,tblCouTitle);
		this.dataTableAdd.validate();
		this.dataTableAdd.repaint();
	}
	
	/*private void getCouIndex(int Idx){
		if(Idx<0) return;
		curCou=allCou.get(Idx);
	}*/
	
	
	private Object tblXscxTitle[]=BeanXianscx.tableTitles;
	private Object tblXscxData[][];
	private BeanXianscx curXscx=null;
	List<BeanXianscx> allXscx=null;
    private void reloadXscxTable(){
		
		try {
			XscxManager a = new XscxManager();
			allXscx=a.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblXscxData =  new Object[allXscx.size()][BeanXianscx.tableTitles.length];
		for(int i=0;i<allXscx.size();i++){
			for(int j=0;j<BeanXianscx.tableTitles.length;j++)
				tblXscxData[i][j]=allXscx.get(i).getCell(j);
		}
		tabAddModel.setDataVector(tblXscxData,tblXscxTitle);
		this.dataTableAdd.validate();
		this.dataTableAdd.repaint();
	}
	
	/*private void getXscxIndex(int Idx){
		if(Idx<0) return;
		curXscx=allXscx.get(Idx);
	}*/
	
	
	
	private Object tblMenuTitle[]=BeanMenu.tableTitles;
	private Object tblMenuData[][];
	private BeanMenu curMenu=null;
	List<BeanMenu> allMenu=null;
    private void reloadMenuTable(){
		
		try {
			MenuManager a = new MenuManager();
			allMenu=a.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblMenuData =  new Object[allMenu.size()][BeanMenu.tableTitles.length];
		for(int i=0;i<allMenu.size();i++){
			for(int j=0;j<BeanMenu.tableTitles.length;j++)
				tblMenuData[i][j]=allMenu.get(i).getCell(j);
		}
		tabAddModel.setDataVector(tblMenuData,tblMenuTitle);
		this.dataTableAdd.validate();
		this.dataTableAdd.repaint();
	}
	
	/*private void getMenuIndex(int Idx){
		if(Idx<0) return;
		curMenu=allMenu.get(Idx);
	}*/
	
	
	private Object tblRecTitle[]=BeanRecommend.tableTitles;
	private Object tblRecData[][];
	private BeanRecommend curRec=null;
	List<BeanRecommend> allRec=null;
    private void reloadRecTable(){
		
		try {
			RecManager a = new RecManager();
			allRec=a.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblRecData =  new Object[allRec.size()][BeanRecommend.tableTitles.length];
		for(int i=0;i<allRec.size();i++){
			for(int j=0;j<BeanRecommend.tableTitles.length;j++)
				tblRecData[i][j]=allRec.get(i).getCell(j);
		}
		tabAddModel.setDataVector(tblRecData,tblRecTitle);
		this.dataTableAdd.validate();
		this.dataTableAdd.repaint();
	}
	
	/*private void getRecIndex(int Idx){
		if(Idx<0) return;
		curRec=allRec.get(Idx);
	}*/
	
	
	
	private Object tblEvaTitle[]=BeanEvaluate.tableTitles;
	private Object tblEvaData[][];
	private BeanEvaluate curEva=null;
	List<BeanEvaluate> allEva=null;
    private void reloadEvaTable(){
		
		try {
			EvaluateManager a = new EvaluateManager();
			allEva=a.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblEvaData =  new Object[allEva.size()][BeanEvaluate.tableTitles.length];
		for(int i=0;i<allEva.size();i++){
			for(int j=0;j<BeanEvaluate.tableTitles.length;j++)
				tblEvaData[i][j]=allEva.get(i).getCell(j);
		}
		tabAddModel.setDataVector(tblEvaData,tblEvaTitle);
		this.dataTableAdd.validate();
		this.dataTableAdd.repaint();
	}
	
	/*private void getEvaIndex(int Idx){
		if(Idx<0) return;
		curEva=allEva.get(Idx);
	}*/
	
	
	
	private Object tblMzinfoTitle[]=BeanManzinfo.tableTitles;
	private Object tblMzinfoData[][];
	private BeanManzinfo curMzinfo=null;
	List<BeanManzinfo> allMzinfo=null;
    private void reloadMzinfoTable(){
		
		try {
			MzinfoManager a = new MzinfoManager();
			allMzinfo=a.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblMzinfoData =  new Object[allMzinfo.size()][BeanManzinfo.tableTitles.length];
		for(int i=0;i<allMzinfo.size();i++){
			for(int j=0;j<BeanManzinfo.tableTitles.length;j++)
				tblMzinfoData[i][j]=allMzinfo.get(i).getCell(j);
		}
		tabAddModel.setDataVector(tblMzinfoData,tblMzinfoTitle);
		this.dataTableAdd.validate();
		this.dataTableAdd.repaint();
	}
	
	/*private void getMzinfoIndex(int Idx){
		if(Idx<0) return;
		curMzinfo=allMzinfo.get(Idx);
	}*/
	
	
	
	private Object tblBuyTitle[]=BeanBuy.tableTitles;
	private Object tblBuyData[][];
	private BeanBuy curBuy=null;
	List<BeanBuy> allBuy=null;
    private void reloadBuyTable(){
		
		try {
			BuyManager a = new BuyManager();
			allBuy=a.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblBuyData =  new Object[allBuy.size()][BeanBuy.tableTitles.length];
		for(int i=0;i<allBuy.size();i++){
			for(int j=0;j<BeanBuy.tableTitles.length;j++)
				tblBuyData[i][j]=allBuy.get(i).getCell(j);
		}
		tabAddModel.setDataVector(tblBuyData,tblBuyTitle);
		this.dataTableAdd.validate();
		this.dataTableAdd.repaint();
	}
	
	/*private void getBuyIndex(int Idx){
		if(Idx<0) return;
		curBuy=allBuy.get(Idx);
	}*/
	
	
	
	private Object tblOrdTitle[]=BeanOrders.tableTitles;
	private Object tblOrdData[][];
	private BeanOrders curOrd=null;
	List<BeanOrders> allOrd=null;
    private void reloadOrdTable(){
		
		try {
			OrdersManager a = new OrdersManager();
			allOrd=a.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblOrdData =  new Object[allOrd.size()][BeanOrders.tableTitles.length];
		for(int i=0;i<allOrd.size();i++){
			for(int j=0;j<BeanOrders.tableTitles.length;j++)
				tblOrdData[i][j]=allOrd.get(i).getCell(j);
		}
		tabAddModel.setDataVector(tblOrdData,tblOrdTitle);
		this.dataTableAdd.validate();
		this.dataTableAdd.repaint();
	}
	
	/*private void getOrdIndex(int Idx){
		if(Idx<0) return;
		curOrd=allOrd.get(Idx);
	}*/
	
	
	private BeanTJU curSum=null;
	List<BeanTJU> allSum=null;
	private Object tblTJUTitle[]=BeanTJU.tableTitles;
	private Object tblTJUData[][];
    private void reloadTJUTable() throws BaseException{
		try {
			SumManager a = new SumManager();
			allSum=a.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblTJUData =  new Object[allSum.size()][BeanOrders.tableTitles.length];
		for(int i=0;i<allSum.size();i++){
			for(int j=0;j<BeanTJU.tableTitles.length;j++)
				tblTJUData[i][j]=allSum.get(i).getCell(j);
		}
		tabAddModel.setDataVector(tblTJUData,tblTJUTitle);
		this.dataTableAdd.validate();
		this.dataTableAdd.repaint();
	}
	
	
    private BeanTJC curTJC=null;
	List<BeanTJC> allTJC=null;
	private Object tblTJCTitle[]=BeanTJC.tableTitles;
	private Object tblTJCData[][];
    private void reloadTJCTable() throws BaseException{
		try {
			TJC_Manager a = new TJC_Manager();
			allTJC=a.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblTJCData =  new Object[allTJC.size()][BeanTJC.tableTitles.length];
		for(int i=0;i<allTJC.size();i++){
			for(int j=0;j<BeanTJC.tableTitles.length;j++)
				tblTJCData[i][j]=allTJC.get(i).getCell(j);
		}
		tabAddModel.setDataVector(tblTJCData,tblTJCTitle);
		this.dataTableAdd.validate();
		this.dataTableAdd.repaint();
	}
    
    
    
	public FrmMain(){
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("SSSSSuperFresh");
		dlgLogin=new FrmLogin(this,"登陆",true);
		dlgLogin.setVisible(true);
		 //菜单
	    this.menu_add.add(this.menuItem_AddAdd); this.menuItem_AddAdd.addActionListener(this);
	    this.menu_add.add(this.menuItem_DeleteAdd); this.menuItem_DeleteAdd.addActionListener(this);
	    this.menu_add.add(this.menuItem_LookAdd); this.menuItem_LookAdd.addActionListener(this);
	    this.menu_users.add(this.menuItem_LookGm); this.menuItem_LookGm.addActionListener(this); 
	    this.menu_users.add(this.menuItem_Chaowd_user); this.menuItem_Chaowd_user.addActionListener(this); 
	    this.menu_users.add(this.menuItem_AddGm); this.menuItem_AddGm.addActionListener(this); 
	    this.menu_users.add(this.menuItem_DeleteGm); this.menuItem_DeleteGm.addActionListener(this); 
	    this.menu_users.add(this.menuItem_LookUsers); this.menuItem_LookUsers.addActionListener(this); 
	    this.menu_jilu.add(this.menuItem_LookGmord); this.menuItem_LookGmord.addActionListener(this); 
	    this.menu_jilu.add(this.menuItem_DeleteGmord); this.menuItem_DeleteGmord.addActionListener(this);
	    this.menu_commo.add(this.menuItem_LookCommo); this.menuItem_LookCommo.addActionListener(this);
	    this.menu_commo.add(this.menuItem_AddCommo); this.menuItem_AddCommo.addActionListener(this);
	    this.menu_commo.add(this.menuItem_BuyCommo); this.menuItem_BuyCommo.addActionListener(this);
	    this.menu_fresh.add(this.menuItem_LookFresh); this.menuItem_LookFresh.addActionListener(this);
	    this.menu_fresh.add(this.menuItem_AddFresh); this.menuItem_AddFresh.addActionListener(this);
	    this.menu_fresh.add(this.menuItem_DeleteFresh); this.menuItem_DeleteFresh.addActionListener(this);
	    this.menu_youhui.add(this.menuItem_LookCou); this.menuItem_LookCou.addActionListener(this);
	    this.menu_youhui.add(this.menuItem_AddCou); this.menuItem_AddCou.addActionListener(this);
	    this.menu_youhui.add(this.menuItem_DeleteCou); this.menuItem_DeleteCou.addActionListener(this);
	    this.menu_youhui.add(this.menuItem_LookXscx); this.menuItem_LookXscx.addActionListener(this);
	    this.menu_youhui.add(this.menuItem_AddXscx); this.menuItem_AddXscx.addActionListener(this);
	    this.menu_youhui.add(this.menuItem_DeleteXscx); this.menuItem_DeleteXscx.addActionListener(this);
	    this.menu_menu.add(this.menuItem_LookMenu); this.menuItem_LookMenu.addActionListener(this);
	    this.menu_menu.add(this.menuItem_AddMenu); this.menuItem_AddMenu.addActionListener(this);
	    this.menu_menu.add(this.menuItem_DeleteMenu); this.menuItem_DeleteMenu.addActionListener(this);
	    this.menu_menu.add(this.menuItem_LookRec); this.menuItem_LookRec.addActionListener(this);
	    this.menu_menu.add(this.menuItem_DeleteRec); this.menuItem_DeleteRec.addActionListener(this);
	    this.menu_menu.add(this.menuItem_AddRec); this.menuItem_AddRec.addActionListener(this);
	    this.menu_commo.add(this.menuItem_LookEva); this.menuItem_LookEva.addActionListener(this);
	    this.menu_commo.add(this.menuItem_DeleteEva); this.menuItem_DeleteEva.addActionListener(this);
	    this.menu_commo.add(this.menuItem_AddEva); this.menuItem_AddEva.addActionListener(this);
	    this.menu_youhui.add(this.menuItem_LookMzinfo); this.menuItem_LookMzinfo.addActionListener(this);
	    this.menu_youhui.add(this.menuItem_AddMzinfo); this.menuItem_AddMzinfo.addActionListener(this);
	    this.menu_youhui.add(this.menuItem_DeleteMzinfo); this.menuItem_DeleteMzinfo.addActionListener(this);
	    this.menu_commo.add(this.menuItem_LookBuy); this.menuItem_LookBuy.addActionListener(this);
	    this.menu_commo.add(this.menuItem_DeleteBuy); this.menuItem_DeleteBuy.addActionListener(this);
	    this.menu_commo.add(this.menuItem_jiesuan); this.menuItem_jiesuan.addActionListener(this);
	    this.menu_jilu.add(this.menuItem_LookOrd); this.menuItem_LookOrd.addActionListener(this);
	    this.menu_jilu.add(this.menuItem_DeleteOrd); this.menuItem_DeleteOrd.addActionListener(this);
	    this.menu_tongji.add(this.menuItem_LookUserB); this.menuItem_LookUserB.addActionListener(this);
	    this.menu_tongji.add(this.menuItem_LookTJC); this.menuItem_LookTJC.addActionListener(this);
	    this.menu_add.add(this.menuItem_ChaAdd); this.menuItem_ChaAdd.addActionListener(this);
	    this.menu_users.add(this.menuItem_DeleteUser); this.menuItem_DeleteUser.addActionListener(this);
	    this.menu_youhui.add(this.menuItem_ChaCou); this.menuItem_ChaCou.addActionListener(this);
	    this.menu_youhui.add(this.menuItem_ChaMz); this.menuItem_ChaMz.addActionListener(this);
	    this.menu_youhui.add(this.menuItem_ChaCx); this.menuItem_ChaCx.addActionListener(this);
	    this.menu_commo.add(this.menuItem_ChaCommo); this.menuItem_ChaCommo.addActionListener(this);
	    this.menu_commo.add(this.menuItem_DeleteCommo); this.menuItem_DeleteCommo.addActionListener(this);
	    this.menu_fresh.add(this.menuItem_ChaFre); this.menuItem_ChaFre.addActionListener(this);
	    this.menu_menu.add(this.menuItem_ChaMenu); this.menuItem_ChaMenu.addActionListener(this);
	    
	    menubar.add(menu_users);
	    menubar.add(menu_youhui);
	    menubar.add(menu_jilu);
	    menubar.add(menu_commo);
	    menubar.add(menu_add);
	    menubar.add(menu_fresh);
	    menubar.add(menu_menu);
	    menubar.add(menu_tongji);
	    this.setJMenuBar(menubar);
	    
	    this.getContentPane().add(new JScrollPane(this.dataTableAdd), BorderLayout.WEST);
	    this.getContentPane().add(new JScrollPane(this.dataTableAdd), BorderLayout.CENTER);
	    
	    //状态栏
	    statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
	    JLabel label=new JLabel("Hello Baby!");
	    statusBar.add(label);
	    this.getContentPane().add(statusBar,BorderLayout.SOUTH);
	    this.addWindowListener(new WindowAdapter(){   
	    	public void windowClosing(WindowEvent e){ 
	    		System.exit(0);
             }
        });
	    this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		
		 if(e.getSource()==this.menuItem_DeleteAdd){
			 int i=FrmMain.this.dataTableAdd.getSelectedRow();
			 if(i<0) {
					JOptionPane.showMessageDialog(null, "请选择地址", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			 curAdd=allAdd.get(i);
				try {
					AddressManager p=new AddressManager();
					p.DeleteAdd(this.curAdd);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_LookAdd){
			 this.reloadAddTable();
			 this.dataTableAdd.addMouseListener(new MouseAdapter (){
					public void mouseClicked(MouseEvent e) {
						int i=FrmMain.this.dataTableAdd.getSelectedRow();
						if(i<0) {
							return;
						}
						//FrmMain.this.getAddIndex(i);
					}
			    	
			    }); 
		 }
		 
		 
		 else if (e.getSource()==this.menuItem_AddAdd) {
			 FrmAddAdd dlg=new FrmAddAdd(this,"添加地址",true);
				dlg.setVisible(true);
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_LookGm){
			 this.reloadGmTable();
			 this.dataTableAdd.addMouseListener(new MouseAdapter (){
					@Override
					public void mouseClicked(MouseEvent e) {
						int i=FrmMain.this.dataTableAdd.getSelectedRow();
						if(i<0) {
							return;
						}
						//FrmMain.this.getGmIndex(i);
					}
			    	
			    });
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_DeleteGm) {
			 int i=FrmMain.this.dataTableAdd.getSelectedRow();
			 if(i<0) {
					JOptionPane.showMessageDialog(null, "请选择管理员", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			 curGm=allGm.get(i);
			 GmManager p=new GmManager();
				try {
					p.DeleteGm(this.curGm);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_AddGm) {
			 FrmAddGm dlg = new FrmAddGm(this,"添加计划",true);
			 dlg.setVisible(true);
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_Chaowd_user) {
			 FrmChaPwd dlg = new FrmChaPwd(this,"修改密码",true);
			 dlg.setVisible(true);
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_LookUsers) {
			 this.dataTableAdd.addMouseListener(new MouseAdapter (){
					@Override
					public void mouseClicked(MouseEvent e) {
						int i=FrmMain.this.dataTableAdd.getSelectedRow();
						if(i<0) {
							return;
						}
						//FrmMain.this.getUserIndex(i);
					}
			    });
			 this.reloadUserTable();
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_LookGmord) {
			 this.reloadGmordTable();
			 this.dataTableAdd.addMouseListener(new MouseAdapter (){
					@Override
					public void mouseClicked(MouseEvent e) {
						int i=FrmMain.this.dataTableAdd.getSelectedRow();
						if(i<0) {
							return;
						}
						//FrmMain.this.getGmoIndex(i);
					}
			    });
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_DeleteGmord) {
			 int i=FrmMain.this.dataTableAdd.getSelectedRow();
			 if(i<0) {
					JOptionPane.showMessageDialog(null, "请选择管理员订单", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			 curGmo=allGmo.get(i);
			 GmordManager p=new GmordManager();
				try {
					p.DeleteGmord(this.curGmo);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_LookCommo) {
			 this.reloadCommoTable();
			 this.dataTableAdd.addMouseListener(new MouseAdapter (){
					@Override
					public void mouseClicked(MouseEvent e) {
						int i=FrmMain.this.dataTableAdd.getSelectedRow();
						if(i<0) {
							return;
						}
						//FrmMain.this.getCommoIndex(i);
					}
			    });
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_AddCommo) {
			 FrmAddCommo dlg1 = new FrmAddCommo(this,"添加商品",true);
			 dlg1.setVisible(true);
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_BuyCommo) {
			 int i=FrmMain.this.dataTableAdd.getSelectedRow();
			 if(i<0) {
					JOptionPane.showMessageDialog(null, "请选择商品", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			 curCommo=allCommo.get(i);
			 CommodityManager.currentCommo = curCommo;
			 if(GmManager.currentGm!=null) {
			     FrmBuyCommo dlg1 = new FrmBuyCommo(this,"购买商品",true);
			     dlg1.setVisible(true);
			 }else {
				 FrmBuyU dlg1 = new FrmBuyU(this,"购买商品",true);
			     dlg1.setVisible(true);
			 }
				}
		 
		 
		 else if(e.getSource()==this.menuItem_LookFresh) {
			 this.reloadFreshTable();
			 this.dataTableAdd.addMouseListener(new MouseAdapter (){
					@Override
					public void mouseClicked(MouseEvent e) {
						int i=FrmMain.this.dataTableAdd.getSelectedRow();
						if(i<0) {
							return;
						}
						//FrmMain.this.getFreshIndex(i);
					}
			    });
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_AddFresh) {
			 FrmAddFresh dlg1 = new FrmAddFresh(this,"添加生鲜",true);
			 dlg1.setVisible(true);
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_DeleteFresh) {
			 int i=FrmMain.this.dataTableAdd.getSelectedRow();
			 if(i<0) {
					JOptionPane.showMessageDialog(null, "请选择生鲜", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			 curFresh=allFresh.get(i);
			 FreshManager p=new FreshManager();
				try {
					p.DeleteFresh(this.curFresh);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_LookCou) {
			 this.reloadCouTable();
			 this.dataTableAdd.addMouseListener(new MouseAdapter (){
					@Override
					public void mouseClicked(MouseEvent e) {
						int i=FrmMain.this.dataTableAdd.getSelectedRow();
						if(i<0) {
							return;
						}
						//FrmMain.this.getCouIndex(i);
					}
			    });
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_AddCou) {
			 FrmAddCou dlg = new FrmAddCou(this,"添加优惠券",true);
			 dlg.setVisible(true);
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_DeleteCou) {
			 int i=FrmMain.this.dataTableAdd.getSelectedRow();
			 if(i<0) {
					JOptionPane.showMessageDialog(null, "请选择优惠券", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			 curCou=allCou.get(i);
			 CouPonManager p=new CouPonManager();
				try {
					p.DeleteCou(this.curCou);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_LookXscx) {
			 this.reloadXscxTable();
			 this.dataTableAdd.addMouseListener(new MouseAdapter (){
					@Override
					public void mouseClicked(MouseEvent e) {
						int i=FrmMain.this.dataTableAdd.getSelectedRow();
						if(i<0) {
							return;
						}
						//FrmMain.this.getXscxIndex(i);
					}
			    });
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_AddXscx) {
			 FrmAddXscx dlg = new FrmAddXscx(this,"添加促销",true);
			 dlg.setVisible(true);
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_DeleteXscx) {
			 int i=FrmMain.this.dataTableAdd.getSelectedRow();
			 if(i<0) {
					JOptionPane.showMessageDialog(null, "请选择促销", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			 curXscx=allXscx.get(i);
			 XscxManager p=new XscxManager();
				try {
					p.DeleteXscx(curXscx);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_LookMenu) {
			 this.reloadMenuTable();
			 this.dataTableAdd.addMouseListener(new MouseAdapter (){
					@Override
					public void mouseClicked(MouseEvent e) {
						int i=FrmMain.this.dataTableAdd.getSelectedRow();
						if(i<0) {
							return;
						}
						//FrmMain.this.getMenuIndex(i);
					}
			    });
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_AddMenu) {
			 FrmAddMenu dlg = new FrmAddMenu(this,"添加菜谱",true);
			 dlg.setVisible(true);
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_DeleteMenu) {
			 int i=FrmMain.this.dataTableAdd.getSelectedRow();
			 if(i<0) {
					JOptionPane.showMessageDialog(null, "请选择菜谱", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			 curMenu=allMenu.get(i);
			 MenuManager p=new MenuManager();
				try {
					p.DeleteMenu(curMenu);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_LookRec) {
			 this.reloadRecTable();
			 this.dataTableAdd.addMouseListener(new MouseAdapter (){
					@Override
					public void mouseClicked(MouseEvent e) {
						int i=FrmMain.this.dataTableAdd.getSelectedRow();
						if(i<0) {
							return;
						}
						//FrmMain.this.getRecIndex(i);
					}
			    });
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_DeleteRec) {
			 int i=FrmMain.this.dataTableAdd.getSelectedRow();
			 if(i<0) {
					JOptionPane.showMessageDialog(null, "请选择推荐菜谱", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			 curRec=allRec.get(i);
			 RecManager p=new RecManager();
				try {
					p.DeleteRec(curRec);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_AddRec) {
			 FrmAddRec dlg = new FrmAddRec(this,"添加推荐",true);
			 dlg.setVisible(true);
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_LookEva) {
			 this.reloadEvaTable();
			 this.dataTableAdd.addMouseListener(new MouseAdapter (){
					@Override
					public void mouseClicked(MouseEvent e) {
						int i=FrmMain.this.dataTableAdd.getSelectedRow();
						if(i<0) {
							return;
						}
						//FrmMain.this.getEvaIndex(i);
					}
			    });
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_DeleteEva) {
			 int i=FrmMain.this.dataTableAdd.getSelectedRow();
			 if(i<0) {
					JOptionPane.showMessageDialog(null, "请选择评价", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			 curEva=allEva.get(i);
			 EvaluateManager p=new EvaluateManager();
				try {
					p.DeleteEva(curEva);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_AddEva) {
			 FrmAddEva dlg = new FrmAddEva(this,"添加评价",true);
			 dlg.setVisible(true);
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_LookMzinfo) {
			 this.reloadMzinfoTable();
			 this.dataTableAdd.addMouseListener(new MouseAdapter (){
					@Override
					public void mouseClicked(MouseEvent e) {
						int i=FrmMain.this.dataTableAdd.getSelectedRow();
						if(i<0) {
							return;
						}
						//FrmMain.this.getMzinfoIndex(i);
					}
			    });
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_AddMzinfo) {
			 FrmAddmMzinfo dlg = new FrmAddmMzinfo(this,"添加评价",true);
			 dlg.setVisible(true);
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_DeleteMzinfo) {
			 int i=FrmMain.this.dataTableAdd.getSelectedRow();
			 if(i<0) {
					JOptionPane.showMessageDialog(null, "请选择满折活动！", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			 curMzinfo=allMzinfo.get(i);
			 MzinfoManager p=new MzinfoManager();
				try {
					p.DeleteMzinfo(curMzinfo);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_LookBuy) {
			 this.reloadBuyTable();
			 this.dataTableAdd.addMouseListener(new MouseAdapter (){
					@Override
					public void mouseClicked(MouseEvent e) {
						int i=FrmMain.this.dataTableAdd.getSelectedRow();
						if(i<0) {
							return;
						}
						//FrmMain.this.getBuyIndex(i);
					}
			    });
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_DeleteBuy) {
			 int i=FrmMain.this.dataTableAdd.getSelectedRow();
			 if(i<0) {
					JOptionPane.showMessageDialog(null, "请选择购买记录！", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			 curBuy=allBuy.get(i);
			 BuyManager p=new BuyManager();
				try {
					p.DeleteBuy(curBuy);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_jiesuan) {
			 FrmAddOrd dlg = new FrmAddOrd(this,"结算订单",true);
			 dlg.setVisible(true);
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_LookOrd) {
			 this.reloadOrdTable();
			 this.dataTableAdd.addMouseListener(new MouseAdapter (){
					@Override
					public void mouseClicked(MouseEvent e) {
						int i=FrmMain.this.dataTableAdd.getSelectedRow();
						if(i<0) {
							return;
						}
						//FrmMain.this.getOrdIndex(i);
					}
			    });
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_DeleteOrd) {
			 int i=FrmMain.this.dataTableAdd.getSelectedRow();
			 if(i<0) {
					JOptionPane.showMessageDialog(null, "请选择购买记录！", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			 curOrd=allOrd.get(i);
			 OrdersManager p=new OrdersManager();
				try {
					p.DeleteOrd(curOrd);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_LookUserB) {
			 try {
				this.reloadTJUTable();
			} catch (BaseException e1) {
				e1.printStackTrace();
			}
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_LookTJC) {
			 try {
				this.reloadTJCTable();
			} catch (BaseException e1) {
				e1.printStackTrace();
			}
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_ChaAdd) {
			 int i=FrmMain.this.dataTableAdd.getSelectedRow();
			 if(i<0) {
					JOptionPane.showMessageDialog(null, "请选择地址！", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			 curAdd=allAdd.get(i);
			 /*if(this.curAdd.getUser_id()!=UsersManager.currentUser.getUser_id()) {
				 try {
					throw new BusinessException("不能修改别人的地址！");
				} catch (BusinessException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			 }*/
			 AddressManager.curAddress=curAdd;
			 FrmChaAdd dlg = new FrmChaAdd(this,"修改地址",true);
			 dlg.setVisible(true);
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_DeleteUser) {
			 int i=FrmMain.this.dataTableAdd.getSelectedRow();
			 if(i<0) {
					JOptionPane.showMessageDialog(null, "请选择删除用户！", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			 curUser=allUser.get(i);
			 UsersManager p=new UsersManager();
				try {
					p.DeleteUser(curUser);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_ChaCou) {
			 int i=FrmMain.this.dataTableAdd.getSelectedRow();
			 if(i<0) {
					JOptionPane.showMessageDialog(null, "请选择优惠券！", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			 curCou=allCou.get(i);
			 CouPonManager.curCoupon=curCou;
			 FrmChaCou dlg = new FrmChaCou(this,"修改优惠券",true);
			 dlg.setVisible(true);
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_ChaMz) {
			 int i=FrmMain.this.dataTableAdd.getSelectedRow();
			 if(i<0) {
					JOptionPane.showMessageDialog(null, "请选择满折活动！", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			 curMzinfo=allMzinfo.get(i);
			 MzinfoManager.curManzinfo = curMzinfo;
			 FrmChaMz dlg = new FrmChaMz(this,"修改满折活动",true);
			 dlg.setVisible(true);
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_ChaCx) {
			 int i=FrmMain.this.dataTableAdd.getSelectedRow();
			 if(i<0) {
					JOptionPane.showMessageDialog(null, "请选择促销活动！", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			 curXscx=allXscx.get(i);
			 XscxManager.curCx = curXscx;
			 FrmChaCx dlg = new FrmChaCx(this,"修改促销活动",true);
			 dlg.setVisible(true);
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_ChaCommo) {
			 int i=FrmMain.this.dataTableAdd.getSelectedRow();
			 if(i<0) {
					JOptionPane.showMessageDialog(null, "请选择商品！", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			 curCommo=allCommo.get(i);
			 CommodityManager.currentCommo = curCommo;
			
			 FrmChaCommo dlg = new FrmChaCommo(this,"修改商品信息",true);
			 dlg.setVisible(true);
			 
		 }
		 else if(e.getSource()==this.menuItem_DeleteCommo) {
			 int i=FrmMain.this.dataTableAdd.getSelectedRow();
			 if(i<0) {
					JOptionPane.showMessageDialog(null, "请选择删除商品！", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			 curCommo=allCommo.get(i);
			 CommodityManager p=new CommodityManager();
				try {
					p.DeleteCommo(curCommo);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, "有相关记录，不能删除！", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_ChaFre) {
			 int i=FrmMain.this.dataTableAdd.getSelectedRow();
			 if(i<0) {
					JOptionPane.showMessageDialog(null, "请选择生鲜！", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			 curFresh=allFresh.get(i);
			 FreshManager.currentFresh = curFresh;
			
			 FrmChaFre dlg = new FrmChaFre(this,"修改生鲜信息",true);
			 dlg.setVisible(true);
			 
		 }
		 
		 
		 else if(e.getSource()==this.menuItem_ChaMenu) {
			 int i=FrmMain.this.dataTableAdd.getSelectedRow();
			 if(i<0) {
					JOptionPane.showMessageDialog(null, "请选择菜谱！", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			 curMenu=allMenu.get(i);
			 MenuManager.currentMenu = curMenu;
			
			 FrmChaMenu dlg = new FrmChaMenu(this,"修改菜谱信息",true);
			 dlg.setVisible(true);
			 
		 }
		 
	}
	}
