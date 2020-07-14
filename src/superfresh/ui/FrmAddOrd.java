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

import superfresh.control.MenuManager;
import superfresh.control.OrdersManager;
import superfresh.control.UsersManager;
import superfresh.util.BaseException;

public class FrmAddOrd extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	
	private JLabel labelflag = new JLabel("购买批次：");
	private JLabel labelcou_id = new JLabel("优惠券编号：");
	private JLabel labeladd_id = new JLabel("地址编号：");
	private JLabel labelttl = new JLabel("预计送达时间：");
	private JLabel labelsitu = new JLabel("订单状况：");

	private JTextField edtflag = new JTextField(15);
	private JTextField edtcou_id = new JTextField(15);
	private JTextField edtadd_id = new JTextField(15);
	private JTextField edtttl = new JTextField(15);
	private JTextField edtsitu = new JTextField(15);

	
	public FrmAddOrd(FrmMain frmMain, String s, boolean b) {
		super(frmMain, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelflag);
		workPane.add(edtflag);
		workPane.add(labelcou_id);
		workPane.add(edtcou_id);
		workPane.add(labeladd_id);
		workPane.add(edtadd_id);
		workPane.add(labelttl);
		workPane.add(edtttl);
		workPane.add(labelsitu);
		workPane.add(edtsitu);
		
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(220, 400);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			int cou_id;
			int flag = Integer.parseInt(this.edtflag.getText());
			int add_id = Integer.parseInt(this.edtadd_id.getText());
			if("".equals(this.edtcou_id.getText()))
				cou_id = -1;
			else
			    cou_id = Integer.parseInt(this.edtcou_id.getText());
			String situ = this.edtsitu.getText();
			String ttl = this.edtttl.getText();
			
			
			OrdersManager a = new OrdersManager();
			try {
				a.AddOrd(flag, cou_id, add_id, UsersManager.currentUser.getUser_id(), ttl, situ);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
					
		}
	}
}
