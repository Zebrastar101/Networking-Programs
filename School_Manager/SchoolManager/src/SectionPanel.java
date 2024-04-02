import javax.swing.*;
import java.awt.*;

public class SectionPanel extends JPanel{

    JLabel panelTitleLabel = new JLabel("Sections");
    JLabel courseLabel = new JLabel("Teacher's first name: ");
    JLabel teacherLabel = new JLabel("Teacher's last name: ");

    JTextField teacherFNTextField = new JTextField("");
    JTextField teacherLNTextField = new JTextField("");

    JTable teacherTable;

    JScrollPane jScrollPane;

    JButton newButton = new JButton("New");
    JButton saveButton = new JButton("Save");
    JButton deleteButton = new JButton("Delete");
    JButton rosterButton = new JButton("Sections");


    public TeacherPanel() {

        setLayout(null);
        setBounds(15, 40, 600, 630);
        setBorder(BorderFactory.createLineBorder(Color.black));

        panelTitleLabel.setBounds(15, 5, 100, 35);
        panelTitleLabel.setFont(new Font("Calibri", Font.BOLD, 23));
        add(panelTitleLabel);

        teacherFNLabel.setBounds(120, 80, 250, 20);
        teacherFNLabel.setFont(new Font("Calibri", Font.BOLD, 15));
        add(teacherFNLabel);

        teacherFNTextField.setBounds(260, 80, 230, 20);
        teacherFNTextField.setFont(new Font("Calibri", Font.BOLD, 15));
        add(teacherFNTextField);

        teacherLNLabel.setBounds(120, 110, 250, 20);
        teacherLNLabel.setFont(new Font("Calibri", Font.BOLD, 15));
        add(teacherLNLabel);

        teacherLNTextField.setBounds(260, 110, 230, 20);
        teacherLNTextField.setFont(new Font("Calibri", Font.BOLD, 15));
        add(teacherLNTextField);


        //buttons

        newButton.setBounds(120, 140, 70, 20);
        newButton.setFont(new Font("Calibri", Font.BOLD, 10));
        add(newButton);

        saveButton.setBounds(210, 140, 70, 20);
        saveButton.setFont(new Font("Calibri", Font.BOLD, 10));
        add(saveButton);

        deleteButton.setBounds(300, 140, 70, 20);
        deleteButton.setFont(new Font("Calibri", Font.BOLD, 10));
        add(deleteButton);

        rosterButton.setBounds(400, 140, 90, 20);
        rosterButton.setFont(new Font("Calibri", Font.BOLD, 10));
        add(rosterButton);
    }
}

