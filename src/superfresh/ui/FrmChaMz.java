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

import superfresh.control.MzinfoManager;
import superfresh.util.BaseException;

public class FrmChaMz extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	
	private JLabel labelcom_id = new JLabel("商品编号：");
	private JLabel labelwhat = new JLabel("满折说明：");
	private JLabel labelcount = new JLabel("满折件数：");
	private JLabel labeldisc = new JLabel("折数：");
	private JLabel labelstart_day = new JLabel("起始日期：");
	private JLabel labelend_day = new JLabel("结束日期：");
	
	private JTextField edtcom_id = new JTextField(15);
	private JTextField edtwhat = new JTextField(15);
	private JTextField edtcount = new JTextField(15);
	private JTextField edtdisc = new JTextField(15);
	private JTextField edtstart_day = new JTextField(15);
	private JTextField edtend_day = new JTextField(15);

	
	public FrmChaMz(FrmMain frmMain, String s, boolean b) {
		super(frmMain, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelcom_id);
		workPane.add(edtcom_id);
		workPane.add(labelwhat);
		workPane.add(edtwhat);
		workPane.add(labelcount);
		workPane.add(edtcount);
		workPane.add(labeldisc);
		workPane.add(edtdisc);
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
			int com_id,count;
			double disc;
			String start_day,end_day;
			if("".equals(this.edtcom_id.getText()))
				com_id=-1;
			else
			    com_id = Integer.parseInt(this.edtcom_id.getText());
			String what = this.edtwhat.getText();
			if("".equals(this.edtcount.getText()))
				count=-1;
			else
			    count = Integer.parseInt(this.edtcount.getText());
			
			if("".equals(this.edtdisc.getText()))
				disc=-1;
			else
			    disc = Double.parseDouble(this.edtdisc.getText());
			if("".equals(this.edtstart_day.getText()))
				start_day = null;
			else
				start_day = this.edtstart_day.getText();
			if("".equals(this.edtend_day.getText()))
				end_day = null;
			else
				end_day = this.edtend_day.getText();
			MzinfoManager a = new MzinfoManager();
			try {
				a.ChaMzinfo(MzinfoManager.curManzinfo, com_id, what, count, disc, start_day, end_day);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
					
		}
	}
	
}