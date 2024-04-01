import javax.swing.*;

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
        
    }
}
