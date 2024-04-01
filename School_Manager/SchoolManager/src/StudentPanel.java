import javax.swing.*;
import java.awt.*;

public class StudentPanel extends JPanel {

    JLabel panelTitleLabel = new JLabel("Students");
    JLabel studentFNLabel = new JLabel("Student's first name: ");
    JLabel studentLNLabel = new JLabel("Student's last name: ");

    JTextField studentFNTextField = new JTextField("");
    JTextField studentLNTextField = new JTextField("");

    //JTable ;

    JButton newButton = new JButton("New");
    JButton saveButton = new JButton("Save");
    JButton deleteButton = new JButton("Delete");
    JButton scheduleButton = new JButton("Schedule");


    public StudentPanel(){
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







    }
}
