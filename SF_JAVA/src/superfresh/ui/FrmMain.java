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
import superfresh.control.GmManager;
import superfresh.model.BeanAddress;
import superfresh.model.BeanGm;
import superfresh.util.BaseException;
import superfresh.ui.FrmAddAdd;;


public class FrmMain extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JMenuBar menubar=new JMenuBar(); ;
    private JMenu menu_users=new JMenu("用户");
    private JMenu menu_youhui=new JMenu("优惠");
    private JMenu menu_jilu=new JMenu("消费记录");
    private JMenu menu_commo=new JMenu("商品");
    private JMenu menu_add=new JMenu("地址 ");
	
    private JMenuItem  menuItem_AddAdd=new JMenuItem("添加地址");
    private JMenuItem  menuItem_DeleteAdd=new JMenuItem("删除地址");
    private JMenuItem  menuItem_LookAdd=new JMenuItem("查看地址");
    private JMenuItem  menuItem_Chaowd_user=new JMenuItem("修改密码");
    private JMenuItem  menuItem_AddGm=new JMenuItem("添加管理员");
    private JMenuItem  menuItem_DeleteGm=new JMenuItem("删除管理员");
    private JMenuItem  menuItem_LookGm=new JMenuItem("查看管理员");
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
	
	private void getAddIndex(int Idx){
		if(Idx<0) return;
		curAdd=allAdd.get(Idx);
	}
	
	
	private Object tblGmTitle[]=BeanGm.tableTitles;
	private Object tblGmData[][];
	//DefaultTableModel tabGmModel=new DefaultTableModel();
	//private JTable dataTableGm=new JTable(tabGmModel);
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
	
	private void getGmIndex(int Idx){
		if(Idx<0) return;
		curGm=allGm.get(Idx);
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
	    
	    menubar.add(menu_users);
	    menubar.add(menu_youhui);
	    menubar.add(menu_jilu);
	    menubar.add(menu_commo);
	    menubar.add(menu_add);
	    this.setJMenuBar(menubar);
	    
	    this.getContentPane().add(new JScrollPane(this.dataTableAdd), BorderLayout.WEST);
	    //this.getContentPane().add(new JScrollPane(this.dataTableGm), BorderLayout.WEST);
	    
	    /*this.dataTableAdd.addMouseListener(new MouseAdapter (){
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmMain.this.dataTableAdd.getSelectedRow();
				if(i<0) {
					return;
				}
				FrmMain.this.getAddIndex(i);
			}
	    	
	    });*/
	    
	    
	    /*this.dataTableAdd.addMouseListener(new MouseAdapter (){
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmMain.this.dataTableAdd.getSelectedRow();
				if(i<0) {
					return;
				}
				FrmMain.this.getGmIndex(i);
			}
	    	
	    });*/
	    //this.reloadAddTable();
	    
	    //状态栏
	    statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
	    JLabel label=new JLabel("Hello Baby!");//修改成   您好！+登陆用户名
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
			 if(this.curAdd==null) {
					JOptionPane.showMessageDialog(null, "请选择地址", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					AddressManager p=new AddressManager();
					p.DeleteAdd(this.curAdd);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
		 }
		 else if(e.getSource()==this.menuItem_LookAdd){
			 //this.getContentPane().add(new JScrollPane(this.dataTableAdd), BorderLayout.WEST);
			 this.reloadAddTable();
			 this.dataTableAdd.addMouseListener(new MouseAdapter (){
					@Override
					public void mouseClicked(MouseEvent e) {
						int i=FrmMain.this.dataTableAdd.getSelectedRow();
						if(i<0) {
							return;
						}
						FrmMain.this.getAddIndex(i);
					}
			    	
			    }); 
		 }
		 else if (e.getSource()==this.menuItem_AddAdd) {
			 FrmAddAdd dlg=new FrmAddAdd(this,"添加计划",true);
				dlg.setVisible(true);
		 }
		 else if(e.getSource()==this.menuItem_LookGm){
			 //this.getContentPane().add(new JScrollPane(this.dataTableGm), BorderLayout.WEST);
			 this.dataTableAdd.addMouseListener(new MouseAdapter (){
					@Override
					public void mouseClicked(MouseEvent e) {
						int i=FrmMain.this.dataTableAdd.getSelectedRow();
						if(i<0) {
							return;
						}
						FrmMain.this.getGmIndex(i);
					}
			    	
			    });
			 this.reloadGmTable();
		 }
		 else if(e.getSource()==this.menuItem_DeleteGm) {
			 if(this.curGm==null) {
					JOptionPane.showMessageDialog(null, "请选择管理员", "错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			 GmManager p=new GmManager();
				try {
					p.DeleteGm(this.curGm);
				} catch (BaseException e1) {
					e1.printStackTrace();
				}
		 }
		 else if(e.getSource()==this.menuItem_AddGm) {
			 FrmAddGm dlg = new FrmAddGm(this,"添加计划",true);
			 dlg.setVisible(true);
		 }
		 
		 
	}
	}
