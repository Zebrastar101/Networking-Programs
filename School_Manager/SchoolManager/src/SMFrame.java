import javax.swing.*;
import java.awt.*;

public class SMFrame extends JFrame {

    JLabel ViewLabel = new JLabel("View: ");

    JLabel FileLabel = new JLabel("File: ");


    JComboBox<String> dropDownView = new JComboBox<String>();


    String teacher="Teacher";
    String student="Student";
    String course="Course";
    String section="Section";

    JComboBox<String> dropDownFile = new JComboBox<String>();

    String expData="Export Data";
    String impData="Import Data";
    String purge="Purge";
    String exit="Exit";

    JButton helpButton = new JButton("Help");





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

        ViewLabel.setBounds(15,5,60,35);
        add(ViewLabel);
        ViewLabel.setFont(new Font("Calibri", Font.BOLD, 23));
        
        helpButton.setBounds(370,10,100,20);
        add(helpButton);
        helpButton.setFont(new Font("Calibri", Font.BOLD, 15));

        dropDownView.setBounds(75, 10, 100, 20);
        dropDownView.addItem(teacher);
        dropDownView.addItem(student);
        dropDownView.addItem(course);
        dropDownView.addItem(section);
        add(dropDownView);
        dropDownView.addActionListener(e->changePanel());

        FileLabel.setBounds(200,5,100,35);
        add(FileLabel);
        FileLabel.setFont(new Font("Calibri", Font.BOLD, 23));

        dropDownFile.setBounds(245, 10, 100, 20);
        dropDownFile.addItem(expData);
        dropDownFile.addItem(impData);
        dropDownFile.addItem(purge);
        dropDownFile.addItem(exit);
        add(dropDownFile);
        //dropDownFile.addActionListener(e->changePanel());

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
