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
import superfresh.control.RecManager;
import superfresh.util.BaseException;

public class FrmAddRec extends JDialog implements ActionListener {

	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	
	private JLabel labelmen_id = new JLabel("菜谱编号：");
	private JLabel labelcom_id = new JLabel("商品编号：");
	private JLabel labelhow = new JLabel("推荐信息：");
	
	private JTextField edtmen_id = new JTextField(15);
	private JTextField edtcom_id = new JTextField(15);
	private JTextField edthow = new JTextField(15);
	
	
	public FrmAddRec(FrmMain frmMain, String s, boolean b) {
		super(frmMain, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelmen_id);
		workPane.add(edtmen_id);
		workPane.add(labelcom_id);
		workPane.add(edtcom_id);
		workPane.add(labelhow);
		workPane.add(edthow);
		
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(200, 250);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			int men_id = Integer.parseInt(this.edtmen_id.getText());
			int com_id = Integer.parseInt(this.edtcom_id.getText());
			String how = this.edthow.getText();
			RecManager a = new RecManager();
			try {
				a.AddRec(men_id, com_id, how);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
					
		}
	}
}
