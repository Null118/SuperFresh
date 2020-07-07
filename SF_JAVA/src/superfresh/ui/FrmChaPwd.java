package superfresh.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import superfresh.control.GmManager;
import superfresh.control.UsersManager;
import superfresh.util.BaseException;

public class FrmChaPwd extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	
	private JLabel labelopwd = new JLabel("原密码：");
	private JLabel labelnpwd = new JLabel("新密码：");
	private JLabel labelnpwd2 = new JLabel("确认新密码：");
	
	private JTextField edtopwd = new JTextField(15);
	private JTextField edtnpwd = new JTextField(15);
	private JTextField edtnpwd2 = new JTextField(15);
	
	public FrmChaPwd(FrmMain frmMain, String s, boolean b) {
		super(frmMain, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelopwd);
		workPane.add(edtopwd);
		workPane.add(labelnpwd);
		workPane.add(edtnpwd);
		workPane.add(labelnpwd2);
		workPane.add(edtnpwd2);
	
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(250, 250);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
	}
	
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			if(UsersManager.currentUser==null) {
				String oldpwd = this.edtopwd.getText();
				String newpwd = this.edtnpwd.getText();
				String newpwd2 = this.edtnpwd2.getText();
				GmManager g = new GmManager();
				try {
					g.changePwd(GmManager.currentGm.getGm_name(), oldpwd, newpwd, newpwd2);
					this.setVisible(false);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		}
		}
}
