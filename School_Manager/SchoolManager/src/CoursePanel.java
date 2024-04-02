import javax.swing.*;
import java.awt.*;

public class CoursePanel extends JPanel {

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


    public CoursePanel(){
        setLayout(null);
        setBounds(15,40,600,630);
        setBorder(BorderFactory.createLineBorder(Color.black));

        panelTitleLabel.setBounds(15,5,100,35);
        panelTitleLabel.setFont(new Font("Calibri", Font.BOLD, 23));
        add(panelTitleLabel);

        studentFNLabel.setBounds(120,80,250,20);
        studentFNLabel.setFont(new Font("Calibri", Font.BOLD, 15));
        add(studentFNLabel);

        studentFNTextField.setBounds(260,80,230,20);
        studentFNTextField.setFont(new Font("Calibri", Font.BOLD, 15));
        add(studentFNTextField);

        studentLNLabel.setBounds(120,110,250,20);
        studentLNLabel.setFont(new Font("Calibri", Font.BOLD, 15));
        add(studentLNLabel);

        studentLNTextField.setBounds(260,110,230,20);
        studentLNTextField.setFont(new Font("Calibri", Font.BOLD, 15));
        add(studentLNTextField);


        //buttons

        newButton.setBounds(120,140,70,20);
        newButton.setFont(new Font("Calibri", Font.BOLD, 10));
        add(newButton);

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

        /*String[] columns = {"ID", "First Name", "Last Name"};

        Object[][] data = {{"1", "Chembian", "Ganeshan"}};

        studentTable = new JTable(data, columns);
        studentTable.setPreferredScrollableViewportSize(new Dimension(300,300));
        studentTable.setFillsViewportHeight(true);


        jScrollPane = new JScrollPane(studentTable);
        add(jScrollPane);

         */





    }
}

