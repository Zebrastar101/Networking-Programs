import javax.swing.*;
import java.awt.*;

public class SMFrame extends JFrame {

    JLabel MenuLabel = new JLabel("Menu: ");

    JComboBox<String> dropDown = new JComboBox<String>();

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

        dropDown.setBounds(90, 0, 250, 30);
        dropDown.addItem(teacher);
        dropDown.addItem(student);
        dropDown.addItem(course);
        dropDown.addItem(section);
        add(dropDown);
        dropDown.addActionListener(e->changePanel());

        teacherPan = new ViewPanels("teachh");
        teacherPan.setBounds(15,40,600,630);
        teacherPan.setBorder(BorderFactory.createLineBorder(Color.black));
        add(teacherPan);
        //teacherPan.setVisible(false);

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
        if (dropDown.getSelectedItem()==teacherPan) {
            teacherPan.setVisible(true);
            studentPan.setVisible(false);
            coursePan.setVisible(false);
            sectionPan.setVisible(false);
        }
        if (dropDown.getSelectedItem()==studentPan) {
            teacherPan.setVisible(false);
            studentPan.setVisible(true);
            coursePan.setVisible(false);
            sectionPan.setVisible(false);
        }
        if (dropDown.getSelectedItem()==coursePan) {
            teacherPan.setVisible(false);
            studentPan.setVisible(false);
            coursePan.setVisible(true);
            sectionPan.setVisible(false);
        }
        if (dropDown.getSelectedItem()==sectionPan) {
            teacherPan.setVisible(false);
            studentPan.setVisible(false);
            coursePan.setVisible(false);
            sectionPan.setVisible(true);
        }
    }




}
