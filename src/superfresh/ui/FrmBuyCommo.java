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

import superfresh.control.CommodityManager;
import superfresh.control.GmManager;
import superfresh.control.GmordManager;
import superfresh.util.BaseException;

public class FrmBuyCommo extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	
	private JLabel labelcnt = new JLabel("购买件数：");
	
	private JTextField edtcnt = new JTextField(15);
	
	public FrmBuyCommo(FrmMain frmMain, String s, boolean b) {
		super(frmMain, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelcnt);
		workPane.add(edtcnt);
		
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(200, 100);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			
			if(GmManager.currentGm!=null) {
			int cnt = Integer.parseInt(this.edtcnt.getText());
			GmordManager a = new GmordManager();
			try {
				a.AddGmord(CommodityManager.currentCommo.getCom_id(), cnt);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
					
		}else {
			
		}
			
			}
	}
}
