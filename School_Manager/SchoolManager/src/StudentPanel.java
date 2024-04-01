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

        panelTitleLabel.setBounds(15,40,60,35);
        panelTitleLabel.setFont(new Font("Calibri", Font.BOLD, 23));
        add(panelTitleLabel);

    }
}
