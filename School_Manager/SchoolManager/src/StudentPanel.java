import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class StudentPanel extends JPanel {

    JLabel panelTitleLabel = new JLabel("Students");
    JLabel studentFNLabel = new JLabel("Student's first name: ");
    JLabel studentLNLabel = new JLabel("Student's last name: ");

    JTextField studentFNTextField = new JTextField("");
    JTextField studentLNTextField = new JTextField("");

    JTable studentTable;

    JScrollPane jScrollPane;

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

        deleteButton.setBounds(300,140,70,20);
        deleteButton.setFont(new Font("Calibri", Font.BOLD, 10));
        add(deleteButton);

        scheduleButton.setBounds(400,140,90,20);
        scheduleButton.setFont(new Font("Calibri", Font.BOLD, 10));
        add(scheduleButton);



        //JTable

        s = new Student(Main.myConn);
        studentTable=s.getStudentTable();
        jScrollPane = new JScrollPane(studentTable);
        jScrollPane.setBounds(50,190,500, 400);
        add(jScrollPane);



    }

    public void newStudent(String fName, String lName) throws SQLException {
        studentTable=s.addStudent(fName, lName);
        jScrollPane.setViewportView(studentTable);
        studentFNTextField.setText("");
        studentLNTextField.setText("");
    }

    public void delStudent(String fName, String lName) throws SQLException {
        studentTable=s.deleteStudent(fName, lName);
        jScrollPane.setViewportView(studentTable);
    }

    public void purge() throws SQLException {
        s.purgeStudent();
    }



}
