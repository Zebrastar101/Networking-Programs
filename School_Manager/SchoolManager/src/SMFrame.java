import javax.swing.*;
import java.awt.*;

public class SMFrame extends JFrame {

    JLabel MenuLabel = new JLabel("Menu: ");

    JComboBox<String> dropDownView = new JComboBox<String>();

    JComboBox<String> dropDownFile = new JComboBox<String>();

    JButton help = new JButton("Help");



    String teacher="Teacher";
    String student="Student";
    String course="Course";
    String section="Section";

    ViewPanels teacherPan = null;
    ViewPanels studentPan = null;
    ViewPanels coursePan = null;
    ViewPanels sectionPan = null;


    public SMFrame(){
        super("School Manager");
        setSize(650,720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        MenuLabel.setBounds(15,0,100,35);
        add(MenuLabel);
        MenuLabel.setFont(new Font("Calibri", Font.BOLD, 25));

        dropDownView.setBounds(90, 0, 250, 30);
        dropDownView.addItem(teacher);
        dropDownView.addItem(student);
        dropDownView.addItem(course);
        dropDownView.addItem(section);
        add(dropDownView);
        dropDownView.addActionListener(e->changePanel());

        teacherPan = new ViewPanels("teachh");
        teacherPan.setBounds(15,40,600,630);
        teacherPan.setBorder(BorderFactory.createLineBorder(Color.black));
        add(teacherPan);


        studentPan = new ViewPanels("studd");
        studentPan.setBounds(15,40,600,630);
        studentPan.setBorder(BorderFactory.createLineBorder(Color.black));
        add(studentPan);
        studentPan.setVisible(false);

        coursePan = new ViewPanels("coursess");
        coursePan.setBounds(15,40,600,630);
        coursePan.setBorder(BorderFactory.createLineBorder(Color.black));
        add(coursePan);
        coursePan.setVisible(false);

        sectionPan = new ViewPanels("sectionss");
        sectionPan.setBounds(15,40,600,630);
        sectionPan.setBorder(BorderFactory.createLineBorder(Color.black));
        add(sectionPan);
        sectionPan.setVisible(false);







        setVisible(true);

    }

    public void changePanel(){
        if (String.valueOf(dropDownView.getSelectedItem())=="Teacher") {
            teacherPan.setVisible(true);
            studentPan.setVisible(false);
            coursePan.setVisible(false);
            sectionPan.setVisible(false);
        }
        if (String.valueOf(dropDownView.getSelectedItem())=="Student") {
            teacherPan.setVisible(false);
            studentPan.setVisible(true);
            coursePan.setVisible(false);
            sectionPan.setVisible(false);
        }
        if (String.valueOf(dropDownView.getSelectedItem())=="Course") {
            teacherPan.setVisible(false);
            studentPan.setVisible(false);
            coursePan.setVisible(true);
            sectionPan.setVisible(false);
        }
        if (String.valueOf(dropDownView.getSelectedItem())=="Section") {
            teacherPan.setVisible(false);
            studentPan.setVisible(false);
            coursePan.setVisible(false);
            sectionPan.setVisible(true);
        }
    }




}
