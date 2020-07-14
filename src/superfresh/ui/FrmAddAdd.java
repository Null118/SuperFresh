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

import superfresh.control.AddressManager;
import superfresh.util.BaseException;

public class FrmAddAdd  extends JDialog implements ActionListener {
	
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	
	private JLabel labelSheng = new JLabel("省：");
	private JLabel labelShi = new JLabel("市：");
	private JLabel labelQu = new JLabel("区：");
	private JLabel labelJuti = new JLabel("具体：");
	private JLabel labelName = new JLabel("姓名：");
	private JLabel labelTel = new JLabel("电话：");

	private JTextField edtSheng = new JTextField(15);
	private JTextField edtShi = new JTextField(15);
	private JTextField edtQu = new JTextField(15);
	private JTextField edtJuti = new JTextField(15);
	private JTextField edtName = new JTextField(15);
	private JTextField edtTel = new JTextField(15);


	
	public FrmAddAdd(FrmMain frmMain, String s, boolean b) {
		super(frmMain, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelSheng);
		workPane.add(edtSheng);
		workPane.add(labelShi);
		workPane.add(edtShi);
		workPane.add(labelQu);
		workPane.add(edtQu);
		workPane.add(labelJuti);
		workPane.add(edtJuti);
		workPane.add(labelName);
		workPane.add(edtName);
		workPane.add(labelTel);
		workPane.add(edtTel);
		
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(230, 400);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
	}
	

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			String sheng = this.edtSheng.getText();
			String shi = this.edtShi.getText();
			String qu = this.edtQu.getText();
			String juti = this.edtJuti.getText();
			String name= this.edtName.getText();
			String tel = this.edtTel.getText();
			AddressManager a = new AddressManager();
			try {
				a.AddAddress(sheng, shi, qu, juti, name, tel);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
					
		}
	}
}
