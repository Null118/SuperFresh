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

import superfresh.control.BuyManager;
import superfresh.control.CommodityManager;
import superfresh.control.GmordManager;
import superfresh.util.BaseException;

public class FrmBuyU extends JDialog implements ActionListener 
{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	
	//private JLabel labelcom_id= new JLabel("购买件数：");
	private JLabel labelmz_id = new JLabel("满折编号：");
	private JLabel labelcx_id = new JLabel("促销编号：");
	private JLabel labelcount = new JLabel("购买件数：");
	private JLabel labelsitu = new JLabel("购买状态：");
	private JLabel labelflag = new JLabel("购买批次：");
	
	private JTextField edtmz_id = new JTextField(15);
	private JTextField edtcx_id = new JTextField(15);
	private JTextField edtcount = new JTextField(15);
	private JTextField edtsitu = new JTextField(15);
	private JTextField edtflag = new JTextField(15);
	
	public FrmBuyU(FrmMain frmMain, String s, boolean b) {
		super(frmMain, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelmz_id);
		workPane.add(edtmz_id);
		workPane.add(labelcx_id);
		workPane.add(edtcx_id);
		workPane.add(labelcount);
		workPane.add(edtcount);
		workPane.add(labelsitu);
		workPane.add(edtsitu);
		workPane.add(labelflag);
		workPane.add(edtflag);
		
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(200, 400);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			int mz_id,cx_id;
			
			if("".equals(this.edtmz_id.getText())) {
				mz_id = -1;
			}else 
			mz_id = Integer.parseInt(this.edtmz_id.getText());
			
			if("".equals(this.edtcx_id.getText())) {
				cx_id = -1;
			}else 
			cx_id = Integer.parseInt(this.edtcx_id.getText());
			
			int count = Integer.parseInt(this.edtcount.getText());
			String situ = this.edtsitu.getText();
			int flag = Integer.parseInt(this.edtflag.getText());
			BuyManager a = new BuyManager();
			try {
				a.AddBuy(CommodityManager.currentCommo.getCom_id(), mz_id, cx_id, count, situ, flag);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
	}
}
}
