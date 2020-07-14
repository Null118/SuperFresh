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

import superfresh.control.CouPonManager;
import superfresh.util.BaseException;

public class FrmChaCou extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	
	private JLabel labeluser_id = new JLabel("用户编号：");
	private JLabel labelwhat = new JLabel("说明：");
	private JLabel labelsuit_jine = new JLabel("适用金额：");
	private JLabel labeldis_jine = new JLabel("折扣金额：");
	private JLabel labelstart_day = new JLabel("起始日期：");
	private JLabel labelend_day = new JLabel("结束日期：");
	
	private JTextField edtuser_id = new JTextField(15);
	private JTextField edtwhat = new JTextField(15);
	private JTextField edtsuit_jine = new JTextField(15);
	private JTextField edtdis_jine = new JTextField(15);
	private JTextField edtstart_day = new JTextField(15);
	private JTextField edtend_day = new JTextField(15);

	
	public FrmChaCou(FrmMain frmMain, String s, boolean b) {
		super(frmMain, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labeluser_id);
		workPane.add(edtuser_id);
		workPane.add(labelwhat);
		workPane.add(edtwhat);
		workPane.add(labelsuit_jine);
		workPane.add(edtsuit_jine);
		workPane.add(labeldis_jine);
		workPane.add(edtdis_jine);
		workPane.add(labelstart_day);
		workPane.add(edtstart_day);
		workPane.add(labelend_day);
		workPane.add(edtend_day);
		
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(210, 400);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			int user_id;
			double suit_jine,dis_jine;
			String start_day,end_day;
			if("".equals(this.edtuser_id.getText()))
				user_id=-1;
			else
			    user_id = Integer.parseInt(this.edtuser_id.getText());
			String what = this.edtwhat.getText();
			if("".equals(this.edtsuit_jine.getText()))
				suit_jine=-1;
			else
				suit_jine = Double.parseDouble(this.edtsuit_jine.getText());
			//double suit_jine = Double.parseDouble(this.edtsuit_jine.getText());
			//String suit_jine = this.edtsuit_jine.getText();
			//double dis_jine = Double.parseDouble(this.edtdis_jine.getText());
			if("".equals(this.edtdis_jine.getText()))
				dis_jine=-1;
			else
				dis_jine = Double.parseDouble(this.edtdis_jine.getText());
			if("".equals(this.edtstart_day.getText()))
				start_day = null;
			else
				start_day = this.edtstart_day.getText();
			if("".equals(this.edtend_day.getText()))
				end_day = null;
			else
				end_day = this.edtend_day.getText();
			CouPonManager a = new CouPonManager();
			try {
				a.ChaCou(CouPonManager.curCoupon, user_id, what, suit_jine, dis_jine, start_day, end_day);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
					
		}
	}
	
	
}
