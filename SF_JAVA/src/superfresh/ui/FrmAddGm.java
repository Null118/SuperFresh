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
import superfresh.util.BaseException;

public class FrmAddGm extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	
	private JLabel labelname = new JLabel("管理员名：");
	private JLabel labelpwd = new JLabel("密码：");
	
	private JTextField edtname = new JTextField(15);
	private JTextField edtpwd = new JTextField(15);
	
	public FrmAddGm(FrmMain frmMain, String s, boolean b) {
		super(frmMain, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelname);
		workPane.add(edtname);
		workPane.add(labelpwd);
		workPane.add(edtpwd);
	
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(200, 200);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			String name = this.edtname.getText();
			String pwd = this.edtpwd.getText();
			GmManager g = new GmManager();
			try {
				g.AddGm(name, pwd);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}

}
