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

import superfresh.control.FreshManager;
import superfresh.util.BaseException;

public class FrmChaFre extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	
	private JLabel labelname = new JLabel("生鲜名 ：");
	private JLabel labelwhat = new JLabel("详情：");
	
	private JTextField edtname = new JTextField(15);
	private JTextField edtwhat = new JTextField(15);
	
	
	public FrmChaFre(FrmMain frmMain, String s, boolean b) {
		super(frmMain, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelname);
		workPane.add(edtname);
		workPane.add(labelwhat);
		workPane.add(edtwhat);
		
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(230, 200);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			String name = this.edtname.getText();
			String what = this.edtwhat.getText();
			FreshManager a = new FreshManager();
			try {
				a.ChaFre(FreshManager.currentFresh, name, what);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
					
		}
	}
}
