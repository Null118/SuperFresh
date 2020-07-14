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

import superfresh.control.XscxManager;
import superfresh.util.BaseException;

public class FrmChaCx extends JDialog implements ActionListener {

	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	
	private JLabel labelcom_id = new JLabel("商品编号：");
	private JLabel labelprice = new JLabel("促销价格：");
	private JLabel labelcount = new JLabel("促销件数：");
	private JLabel labelstart_day = new JLabel("起始日期：");
	private JLabel labelend_day = new JLabel("结束日期：");

	private JTextField edtcom_id = new JTextField(15);
	private JTextField edtprice = new JTextField(15);
	private JTextField edtcount = new JTextField(15);
	private JTextField edtstart_day = new JTextField(15);
	private JTextField edtend_day = new JTextField(15);

	
	public FrmChaCx(FrmMain frmMain, String s, boolean b) {
		super(frmMain, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelcom_id);
		workPane.add(edtcom_id);
		workPane.add(labelprice);
		workPane.add(edtprice);
		workPane.add(labelcount);
		workPane.add(edtcount);
		workPane.add(labelstart_day);
		workPane.add(edtstart_day);
		workPane.add(labelend_day);
		workPane.add(edtend_day);
		
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(220, 400);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			int com_id,count;
			double price;
			String start_day,end_day;
			
			if("".equals(this.edtcom_id.getText()))
				com_id = -1;
			else
				com_id = Integer.parseInt(this.edtcom_id.getText());
			if("".equals(this.edtprice.getText()))
				price = -1;
			else
				price = Double.parseDouble(this.edtprice.getText());
			if("".equals(this.edtcount.getText()))
				count = -1;
			else 
				count = Integer.parseInt(this.edtcount.getText());
			if("".equals(this.edtstart_day.getText()))
				start_day = null;
			else
				start_day = this.edtstart_day.getText();
			if("".equals(this.edtend_day.getText()))
				end_day = null;
			else
				end_day = this.edtend_day.getText();
			XscxManager a = new XscxManager();
			try {
				a.ChaCx(XscxManager.curCx, com_id, price, count, start_day, end_day);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
					
		}
	}
}