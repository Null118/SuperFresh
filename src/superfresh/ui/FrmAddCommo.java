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

import superfresh.control.AddressManager;
import superfresh.control.CommodityManager;
import superfresh.util.BaseException;

public class FrmAddCommo extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	
	private JLabel labelname = new JLabel("商品名：");
	private JLabel labelprice = new JLabel("价格：");
	private JLabel labelvippri = new JLabel("vip价格：");
	private JLabel labelcount = new JLabel("库存：");
	private JLabel labelguige = new JLabel("规格：");
	private JLabel labeljuti = new JLabel("详情：");

	private JTextField edtname = new JTextField(15);
	private JTextField edtprice = new JTextField(15);
	private JTextField edtvippri = new JTextField(15);
	private JTextField edtcount = new JTextField(15);
	private JTextField edtguige = new JTextField(15);
	private JTextField edtjuti = new JTextField(15);

	public FrmAddCommo(FrmMain frmMain, String s, boolean b) {
		super(frmMain, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelname);
		workPane.add(edtname);
		workPane.add(labelprice);
		workPane.add(edtprice);
		workPane.add(labelvippri);
		workPane.add(edtvippri);
		workPane.add(labelcount);
		workPane.add(edtcount);
		workPane.add(labelguige);
		workPane.add(edtguige);
		workPane.add(labeljuti);
		workPane.add(edtjuti);
		
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(200, 400);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
	}

	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			String name = this.edtname.getText();
			Double price = Double.parseDouble(this.edtprice.getText());
			Double vippri = Double.parseDouble(this.edtvippri.getText());
			int count = Integer.parseInt(this.edtcount.getText());
			String guige = this.edtguige.getText();
			String juti = this.edtjuti.getText();
			CommodityManager a = new CommodityManager();
			try {
				a.AddCommodity(name, price, vippri, count, guige, juti);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
					
		}
	}
}
