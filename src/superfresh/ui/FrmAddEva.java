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

import superfresh.control.EvaluateManager;
import superfresh.control.RecManager;
import superfresh.util.BaseException;

public class FrmAddEva extends JDialog implements ActionListener {

	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	
	private JLabel labelcom_id = new JLabel("商品编号：");
	private JLabel labelwhat = new JLabel("评价：");
	private JLabel labelday = new JLabel("评价日期：");
	private JLabel labelstar = new JLabel("星级：");
	
	private JTextField edtcom_id = new JTextField(15);
	private JTextField edtwhat = new JTextField(15);
	private JTextField edtday = new JTextField(15);
	private JTextField edtstar = new JTextField(15);
	
	public FrmAddEva(FrmMain frmMain, String s, boolean b) {
		super(frmMain, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelcom_id);
		workPane.add(edtcom_id);
		workPane.add(labelwhat);
		workPane.add(edtwhat);
		workPane.add(labelday);
		workPane.add(edtday);
		workPane.add(labelstar);
		workPane.add(edtstar);
		
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(200, 350);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			int com_id = Integer.parseInt(this.edtcom_id.getText());
			String what = this.edtwhat.getText();
			String day = this.edtday.getText();
			int star = Integer.parseInt(this.edtstar.getText());
			EvaluateManager a = new EvaluateManager();
			try {
				a.AddEva(com_id, what, day, star);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
					
		}
	}
}
