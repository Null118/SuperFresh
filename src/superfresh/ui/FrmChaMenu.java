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
import superfresh.util.BaseException;

public class FrmChaMenu extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	
	private JLabel labelfre_id = new JLabel("生鲜编号：");
	private JLabel labelname = new JLabel("菜谱名称：");
	private JLabel labelstep = new JLabel("制作步骤：");

	private JTextField edtfre_id = new JTextField(15);
	private JTextField edtname = new JTextField(15);
	private JTextField edtstep = new JTextField(15);
	
	public FrmChaMenu(FrmMain frmMain, String s, boolean b) {
		super(frmMain, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelname);
		workPane.add(edtname);
		workPane.add(labelfre_id);
		workPane.add(edtfre_id);
		workPane.add(labelstep);
		workPane.add(edtstep);
		
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(220, 400);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			int fre_id;
			if("".equals(this.edtfre_id.getText()))
				fre_id = -1;
			else
				fre_id = Integer.parseInt(this.edtfre_id.getText());
			String name = this.edtname.getText();
			String step = this.edtstep.getText();
			MenuManager a = new MenuManager();
			try {
				a.ChaMenu(MenuManager.currentMenu, name, fre_id, step);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, "存在相关记录，不能修改！","错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
					
		}
	}
}
