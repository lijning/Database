package view;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import control.DBConnection;
import toolkit.Table;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Thread.State;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTable;
public class Item312Act extends JPanel implements ActionListener{
	JPanel jp1,jp2;
	JLabel jl1;
	JButton Jb1;
	JTable jt1;
	public Item312Act() {
		jl1 = new JLabel("��ѯ�����ڿγ̵����̵ȼ�ȫ�ŵĽ�ʦ���������ڿγ�");
		Jb1 = new JButton("��ѯ");
		jt1 = new JTable(1,3);
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp1.add(jl1);
		jp2.add(Jb1);
		Jb1.addActionListener(this);
		this.add(jp1);
		this.add(jp2);
		this.add(jt1);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		ResultSet res;
		Statement statement = null;
		String sql = "";
		System.out.println(sql);
		try {
			statement = DBConnection.getConnection().createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			res = statement.executeQuery(sql);
			jt1 = new Table(res).jt;
			statement.close();
			res.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}