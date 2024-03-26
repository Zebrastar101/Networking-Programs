import javax.swing.*;
import java.awt.*;

public class SMFrame extends JFrame {

    JLabel NumberOfTermsLabel = new JLabel("Menu: ");

    JComboBox<String> dropDown = new JComboBox<String>();

    String teacher="Teacher";
    String student="Student";
    String course="Course";
    String section="Section";

    public SMFrame(){
        super("School Manager");
        setSize(650,720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        NumberOfTermsLabel.setBounds(15,0,100,35);
        add(NumberOfTermsLabel);
        NumberOfTermsLabel.setFont(new Font("Calibri", Font.BOLD, 25));

        dropDown.setBounds(90, 0, 250, 30);
        dropDown.addItem(teacher);
        dropDown.addItem(student);
        dropDown.addItem(course);
        dropDown.addItem(section);
        add(dropDown);
        dropDown.addActionListener(e->changePanel());





        setVisible(true);

    }

    public void changePanel(){

    }




}
