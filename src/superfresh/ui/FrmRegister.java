package superfresh.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import superfresh.control.UsersManager;
import superfresh.util.BaseException;



public class FrmRegister extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("注册");
	private Button btnCancel = new Button("取消");
	
	private JLabel labelUser = new JLabel("用户：");
	private JLabel labelSex = new JLabel("性别：");
	private JLabel labelPwd = new JLabel("密码：");
	private JLabel labelTel = new JLabel("电话：");
	private JLabel labelMail = new JLabel("邮箱：");
	private JLabel labelCity = new JLabel("城市：");
	private JLabel labelVip = new JLabel("是否会员：");
	private JLabel labelEd = new JLabel("会员日期：");
	private JTextField edtUserId = new JTextField(20);
	private JTextField edtSex = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);
	private JTextField edtTel = new JTextField(20);
	private JTextField edtMail = new JTextField(20);
	private JTextField edtCity = new JTextField(20);
	private JTextField edtVip = new JTextField(20);
	private JTextField edtEd = new JTextField(20);


	public FrmRegister(Dialog f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelUser);
		workPane.add(edtUserId);
		workPane.add(labelSex);
		workPane.add(edtSex);
		workPane.add(labelPwd);
		workPane.add(edtPwd);
		workPane.add(labelTel);
		workPane.add(edtTel);
		workPane.add(labelMail);
		workPane.add(edtMail);
		workPane.add(labelCity);
		workPane.add(edtCity);
		workPane.add(labelVip);
		workPane.add(edtVip);
		workPane.add(labelEd);
		workPane.add(edtEd);
		
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(300, 400);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			String name=this.edtUserId.getText();
			String pwd=new String(this.edtPwd.getPassword());
			String sex=this.edtSex.getText();
			String tel=this.edtTel.getText();
			String mail=this.edtMail.getText();
			String city=this.edtCity.getText();
			String vip=this.edtVip.getText();
			String ed=this.edtEd.getText();
			try {
				//BeanUser user=PersonPlanUtil.userManager.reg(userid,pwd1,pwd2);
				UsersManager a = new UsersManager();
				a.AddUsers(name, sex, pwd, tel, mail, city, vip, ed);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
		}
			
		
	}


}
