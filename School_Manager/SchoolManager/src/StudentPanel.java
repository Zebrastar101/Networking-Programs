import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentPanel extends JPanel {
    Connection con;
    Statement stm;
    JLabel panelTitleLabel = new JLabel("Students");
    JLabel studentFNLabel = new JLabel("Student's first name: ");
    JLabel studentLNLabel = new JLabel("Student's last name: ");

    JTextField studentFNTextField = new JTextField("");
    JTextField studentLNTextField = new JTextField("");

    JTable studentTable;

    JScrollPane jScrollPane;
    JTable schedule=new JTable();
    JScrollPane jScrollEnrollment;
    JButton newButton = new JButton("New");
    JButton saveButton = new JButton("Save");
    JButton deleteButton = new JButton("Delete");
    JButton scheduleButton = new JButton("Schedule");

    Student s;


    public StudentPanel(){
        setLayout(null);
        setBounds(15,40,600,630);
        setBorder(BorderFactory.createLineBorder(Color.black));


        panelTitleLabel.setBounds(15,5,100,35);
        panelTitleLabel.setFont(new Font("Calibri", Font.BOLD, 23));
        add(panelTitleLabel);

        studentFNLabel.setBounds(120,80,140,20);
        studentFNLabel.setFont(new Font("Calibri", Font.BOLD, 15));
        add(studentFNLabel);

        studentFNTextField.setBounds(260,80,230,20);
        studentFNTextField.setFont(new Font("Calibri", Font.BOLD, 15));
        add(studentFNTextField);

        studentLNLabel.setBounds(120,110,140,20);
        studentLNLabel.setFont(new Font("Calibri", Font.BOLD, 15));
        add(studentLNLabel);

        studentLNTextField.setBounds(260,110,230,20);
        studentLNTextField.setFont(new Font("Calibri", Font.BOLD, 15));
        add(studentLNTextField);

        schedule=new JTable();
        schedule=makeJTable(new Object[0][0]);
        jScrollEnrollment = new JScrollPane(schedule);
        jScrollEnrollment.setBounds(630,80,250, 200);
        add(jScrollEnrollment);
        //buttons

        newButton.setBounds(120,140,70,20);
        newButton.setFont(new Font("Calibri", Font.BOLD, 10));
        add(newButton);
        newButton.addActionListener(e-> {
            try {
                newStudent(studentFNTextField.getText(), studentLNTextField.getText());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        saveButton.setBounds(210,140,70,20);
        saveButton.setFont(new Font("Calibri", Font.BOLD, 10));
        add(saveButton);
        saveButton.addActionListener(e-> {
            try {
                if(!studentTable.getSelectionModel().isSelectionEmpty()){
                    saveStudentChanges(studentFNTextField.getText(), studentLNTextField.getText(), (Integer) studentTable.getValueAt(studentTable.getSelectedRow() , 0));
                }
                else{
                    int errorMessage = JOptionPane.showConfirmDialog(null, "No student was selected", "Error", JOptionPane.OK_CANCEL_OPTION);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        deleteButton.setBounds(300,140,70,20);
        deleteButton.setFont(new Font("Calibri", Font.BOLD, 10));
        add(deleteButton);
        deleteButton.addActionListener(e-> {
            try {
                if(!studentTable.getSelectionModel().isSelectionEmpty()){
                    delStudent((Integer) studentTable.getValueAt(studentTable.getSelectedRow() , 0));
                }
                else{
                    int errorMessage = JOptionPane.showConfirmDialog(null, "No student was selected", "Error", JOptionPane.OK_CANCEL_OPTION);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });




        //JTable

        s = new Student();
        studentTable=s.getStudentTable();
        //below from https://www.tabnine.com/code/java/methods/javax.swing.JTable/getSelectedRow
        studentTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String firstName = (String) studentTable.getValueAt(studentTable.getSelectedRow() , 1);
                String lastName = (String) studentTable.getValueAt(studentTable.getSelectedRow() , 2);
                studentFNTextField.setText(firstName);
                studentLNTextField.setText(lastName);

                try {
                    schedule=makeJTable(scheduleMaker((int)studentTable.getValueAt(studentTable.getSelectedRow() , 0)));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                jScrollEnrollment = new JScrollPane(schedule);
                jScrollEnrollment.setBounds(630,80,250, 200);
                add(jScrollEnrollment);
            }
        });
        jScrollPane = new JScrollPane(studentTable);
        jScrollPane.setBounds(50,190,500, 400);
        add(jScrollPane);



    }

    public void newStudent(String fName, String lName) throws SQLException {
        if(!studentFNTextField.getText().isEmpty() && !studentLNTextField.getText().isEmpty()){
            studentTable=s.addStudent(fName, lName);
            jScrollPane.setViewportView(studentTable);
            studentFNTextField.setText("");
            studentLNTextField.setText("");
            studentTable.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    String firstName = (String) studentTable.getValueAt(studentTable.getSelectedRow() , 1);
                    String lastName = (String) studentTable.getValueAt(studentTable.getSelectedRow() , 2);
                    studentFNTextField.setText(firstName);
                    studentLNTextField.setText(lastName);

                    try {
                        schedule=makeJTable(scheduleMaker((int)studentTable.getValueAt(studentTable.getSelectedRow() , 0)));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    jScrollEnrollment = new JScrollPane(schedule);
                    jScrollEnrollment.setBounds(630,80,250, 200);
                    add(jScrollEnrollment);
                }
            });
        }
        else{
            int errorMessage = JOptionPane.showConfirmDialog(null, "Both first and last name are needed", "Error", JOptionPane.OK_CANCEL_OPTION);
        }
    }

    public void saveStudentChanges(String fName, String lName, int id) throws SQLException {
        if(!studentFNTextField.getText().isEmpty() && !studentLNTextField.getText().isEmpty()){
            studentTable=s.saveStudent(fName, lName, id);
            jScrollPane.setViewportView(studentTable);
            studentFNTextField.setText("");
            studentLNTextField.setText("");
            studentTable.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    String firstName = (String) studentTable.getValueAt(studentTable.getSelectedRow() , 1);
                    String lastName = (String) studentTable.getValueAt(studentTable.getSelectedRow() , 2);
                    studentFNTextField.setText(firstName);
                    studentLNTextField.setText(lastName);

                    try {
                        schedule=makeJTable(scheduleMaker((int)studentTable.getValueAt(studentTable.getSelectedRow() , 0)));
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    jScrollEnrollment = new JScrollPane(schedule);
                    jScrollEnrollment.setBounds(630,80,250, 200);
                    add(jScrollEnrollment);
                }
            });
        }
        else{
            int errorMessage = JOptionPane.showConfirmDialog(null, "Both first and last name are needed", "Error", JOptionPane.OK_CANCEL_OPTION);
        }
    }

    public void delStudent(int id) throws SQLException {
        studentTable=s.deleteStudent(id);
        jScrollPane.setViewportView(studentTable);
        studentFNTextField.setText("");
        studentLNTextField.setText("");
        studentTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String firstName = (String) studentTable.getValueAt(studentTable.getSelectedRow() , 1);
                String lastName = (String) studentTable.getValueAt(studentTable.getSelectedRow() , 2);
                studentFNTextField.setText(firstName);
                studentLNTextField.setText(lastName);

                try {
                    schedule=makeJTable(scheduleMaker((int)studentTable.getValueAt(studentTable.getSelectedRow() , 0)));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                jScrollEnrollment = new JScrollPane(schedule);
                jScrollEnrollment.setBounds(630,80,250, 200);
                add(jScrollEnrollment);
            }
        });
    }


    public void fileImport(Scanner sc) throws SQLException {
        studentTable=s.importFile(sc);
        jScrollPane.setViewportView(studentTable);
        studentFNTextField.setText("");
        studentLNTextField.setText("");
        studentTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String firstName = (String) studentTable.getValueAt(studentTable.getSelectedRow() , 1);
                String lastName = (String) studentTable.getValueAt(studentTable.getSelectedRow() , 2);
                studentFNTextField.setText(firstName);
                studentLNTextField.setText(lastName);

                try {
                    schedule=makeJTable(scheduleMaker((int)studentTable.getValueAt(studentTable.getSelectedRow() , 0)));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                jScrollEnrollment = new JScrollPane(schedule);
                jScrollEnrollment.setBounds(630,80,250, 200);
                add(jScrollEnrollment);
            }
        });
    }

    public JTable makeJTable(Object[][] dataArray) {
        System.out.println("found make Jtable");
        DefaultTableModel tableModel = new DefaultTableModel(dataArray, new String[]{"Section","Course","Teacher"}) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        JTable table = new JTable();
        table.setModel(tableModel);
        table.getTableHeader().setReorderingAllowed(false);
        System.out.print(table);

        return table;
    }
    public Object[][] scheduleMaker(int sID) throws SQLException {
        con = Main.myConn;
        stm=con.createStatement();
        ResultSet sectionRS=stm.executeQuery("Select*from enrollment WHERE student_id >=1");
        ArrayList<Object> sectionList = new ArrayList<>();
        while(sectionRS!=null && sectionRS.next()){
            if(sID==(int)sectionRS.getObject(2)){
                sectionList.add(sectionRS.getObject(1));
            }
        }
        ResultSet sRS=stm.executeQuery("Select*from section WHERE section_id >=1");
        Object[][] dataArray= new Object[sectionList.size()][3];
        for(int x=0; x< dataArray.length; x++){
            dataArray[x][0]=sectionList.get(x);
            dataArray[x][2]=findTeacher((int)findTeacherID((int)sectionList.get(x)));
            dataArray[x][1]=findCourse((int)findCourseID((int)sectionList.get(x)));

        }
        return dataArray;
    }

    public Object findTeacher(int id) throws SQLException {
        stm=con.createStatement();
        ResultSet studentRS=stm.executeQuery("Select*from teacher WHERE teacher_id >=1");
        while (studentRS!=null && studentRS.next()){
            if(id==(int)studentRS.getObject(1)){
                return studentRS.getObject(2) + " " + studentRS.getObject(3);
            }
        }
        return null;
    }
    public Object findCourse(int id) throws SQLException {
        stm=con.createStatement();
        ResultSet studentRS=stm.executeQuery("Select*from course WHERE course_id >=1");
        while (studentRS!=null && studentRS.next()){
            if(id==(int)studentRS.getObject(1)){
                return studentRS.getObject(2);
            }
        }
        return null;
    }
    public Object findTeacherID(int id) throws  SQLException{
        stm=con.createStatement();
        ResultSet studentRS=stm.executeQuery("Select*from section WHERE section_id >=1");
        while (studentRS!=null && studentRS.next()){
            if(id==(int)studentRS.getObject(1)){
                return studentRS.getObject(3);
            }
        }
        return null;
    }
    public Object findCourseID(int id) throws  SQLException{
        stm=con.createStatement();
        ResultSet studentRS=stm.executeQuery("Select*from section WHERE section_id >=1");
        while (studentRS!=null && studentRS.next()){
            if(id==(int)studentRS.getObject(1)){
                return studentRS.getObject(2);
            }
        }
        return null;
    }
}
