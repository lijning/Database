package view;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import control.DBConnection;
import model.Course;
import model.Department;
import model.SC;
import model.Student;
import toolkit.Table;
import toolkit.Utility;

public final class Item24Act extends JPanel implements  ActionListener{
	
	private JPanel upper,top;
    private JButton buttonQuery;
    private JLabel labelYear,labelSemester,labelStudentID,labelCourse,labelScore,labelGpa,labelHeading,labelTo,labelTo1,labelTo2;
    private JComboBox comboBoxYear,comboBoxSemester,comboBoxCourse;
    private JTextField textIDMin, textIDMax, textScoreMin, textScoreMax, textGpaMin, textGpaMax;
    private JTable table;
    private ResultSet resultSet=null;
    
    public Item24Act (){
        super();
        labelYear=new JLabel("ѧ��");
        labelSemester=new JLabel("ѧ��");
        labelStudentID = new JLabel("ѧ��");
        labelCourse = new JLabel("�γ�����");
        labelScore = new JLabel("�ɼ�");
        labelGpa = new JLabel("����");
        labelTo = new JLabel("��");
        labelTo1 = new JLabel("��");
        labelTo2 = new JLabel("��");

        upper=new JPanel();
        buttonQuery= new JButton("��ѯ");
        comboBoxYear=new JComboBox(Utility.simpleUniqueQuery(SC.TABLE, SC.AYEAR));
        comboBoxSemester=new JComboBox(Utility.simpleUniqueQuery(SC.TABLE,SC.SEMESTER));
        comboBoxCourse=new JComboBox(Utility.simpleUniqueQuery(Course.TABLE,Course.NAME));
        textIDMin=new JTextField(30);
        textIDMax=new JTextField(30);
        textScoreMin=new JTextField(30);
        textScoreMax=new JTextField(30);
        textGpaMin=new JTextField(30);
        textGpaMax=new JTextField(30);
        this.upper.setLayout(createLayout());

        top=new JPanel();
        labelHeading=new JLabel("��������Ҫ��ѯ������");
        //labelHeading.setHorizontalAlignment(SwingConstants.LEFT);
        top.add(labelHeading);

        buttonQuery.addActionListener(this);

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.add(this.top);
        this.add(this.upper);
        table=new JTable(1,3);//todo What should be presented when no result has been acquired.
        this.add(this.table);

        this.setVisible(true);
        this.setFont(new Font("����",Font.ITALIC,30));//TODo �������⻹�ڣ���һ����ʾ����û�о��С�
        /*
        * ���Խ��GUI�������������⡣
        * */
    }
	
    private LayoutManager createLayout(){
        GroupLayout layout=new GroupLayout(upper);
        layout.setAutoCreateContainerGaps(true);

        GroupLayout.ParallelGroup hGroup1=layout.createParallelGroup().addComponent(labelYear).addComponent(labelSemester).addComponent(labelStudentID).addComponent(labelCourse).addComponent(labelScore).addComponent(labelGpa);
        GroupLayout.ParallelGroup hGroup2=layout.createParallelGroup().addComponent(comboBoxYear).addComponent(comboBoxSemester).addComponent(textIDMin).addComponent(comboBoxCourse).addComponent(textScoreMin).addComponent(textGpaMin);
        GroupLayout.ParallelGroup hGroup3=layout.createParallelGroup().addComponent(labelTo).addComponent(labelTo1).addComponent(labelTo2);
        GroupLayout.ParallelGroup hGroup4=layout.createParallelGroup().addComponent(textIDMax).addComponent(textScoreMax).addComponent(textGpaMax);
        GroupLayout.SequentialGroup hGroup=layout.createSequentialGroup().addGroup(hGroup1).addGroup(hGroup2).addGroup(hGroup3).addGroup(hGroup4).addComponent(buttonQuery);
        layout.setHorizontalGroup(hGroup);
        
        GroupLayout.ParallelGroup vGroup1=layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelYear).addComponent(comboBoxYear);
        GroupLayout.ParallelGroup vGroup2=layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelSemester).addComponent(comboBoxSemester);
        GroupLayout.ParallelGroup vGroup3=layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelStudentID).addComponent(textIDMin).addComponent(labelTo).addComponent(textIDMax);
        GroupLayout.ParallelGroup vGroup4=layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelCourse).addComponent(comboBoxCourse);
        GroupLayout.ParallelGroup vGroup5=layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelScore).addComponent(textScoreMin).addComponent(labelTo1).addComponent(textScoreMax);
        GroupLayout.ParallelGroup vGroup6=layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(labelGpa).addComponent(textGpaMin).addComponent(labelTo2).addComponent(textGpaMax).addComponent(buttonQuery);
        GroupLayout.SequentialGroup vGroup=layout.createSequentialGroup().addGroup(vGroup1).addGroup(vGroup2).addGroup(vGroup3).addGroup(vGroup4).addGroup(vGroup5).addGroup(vGroup6);
        layout.setVerticalGroup(vGroup);

        return layout;
    }
    
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		// TODO Auto-generated method stub
		String sqlString = "select * from " + SC.TABLE + " and " + Course.TABLE + " where " + SC.TABLE + "."+ SC.C_ID + " = " + Course.TABLE + "."+ Course.ID;
		System.out.print(sqlString);
		if(this.comboBoxYear != null)
			sqlString.concat(" and " + SC.AYEAR + " = " + comboBoxYear.getSelectedItem());
		if(this.comboBoxSemester != null)
			sqlString.concat(" and " + SC.SEMESTER + " = " + comboBoxSemester.getSelectedItem());
		if(this.textIDMin.getText() != null) {
			float idMin = Float.parseFloat(textIDMin.getText());
			sqlString.concat(" and " + SC.S_ID + " >= " + idMin);
		}
		if(this.textIDMax.getText() != null) {
			float idMax = Float.parseFloat(textIDMax.getText());
			sqlString.concat(" and" + SC.S_ID + " <= " + idMax);
		}
		if(this.comboBoxCourse != null)
			sqlString.concat(" and " + Course.NAME + " = " + comboBoxCourse.getSelectedItem());
		if(this.textScoreMin.getText() != null) {
			float scoreMin = Float.parseFloat(textScoreMin.getText());
			sqlString.concat(" and " + SC.SCORE + " >= " + scoreMin);
		}
		if(this.textScoreMax.getText() != null) {
			float scoreMax = Float.parseFloat(textScoreMax.getText());
			sqlString.concat(" and" + SC.SCORE + " <= " + scoreMax);
		}
		if(this.textGpaMin.getText() != null) {
			float gpaMin = Float.parseFloat(textScoreMin.getText());
			sqlString.concat(" and " + SC.GPA + " >= " + gpaMin);
		}
		if(this.textGpaMax.getText() != null) {
			float gpaMax = Float.parseFloat(textGpaMax.getText());
			sqlString.concat(" and" + SC.GPA+ " <= " + gpaMax);
		}
		System.out.println(sqlString);
        try {
            Statement statement = DBConnection.getConnection().createStatement();
            resultSet = statement.executeQuery(sqlString);
            table = (new Table(resultSet)).jt;
            this.updateUI();
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}

}