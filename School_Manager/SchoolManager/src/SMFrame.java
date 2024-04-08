import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SMFrame extends JFrame implements WindowListener {

    static Connection con;
    Statement stm;


    JLabel ViewLabel = new JLabel("View: ");

    JLabel FileLabel = new JLabel("File: ");


    JLabel aboutLabel = new JLabel("About:");

    JLabel creatorsLabel = new JLabel("creators- Zahraa F. & Chembian G. ");

    JLabel versionLabel = new JLabel("version number- 1 ");





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





    TeacherPanel teacherPan = null;
    StudentPanel studentPan = null;
    CoursePanel coursePan = null;
    SectionPanel sectionPan = null;


    public SMFrame(Connection c){
        super("School Manager");
        setSize(650,720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);


        con=c;
        //studentTable=new JTable();
        try{
            stm=con.createStatement();
        }catch(SQLException e){
            e.printStackTrace();

        }



        ViewLabel.setBounds(15,5,100,35);
        add(ViewLabel);
        ViewLabel.setFont(new Font("Calibri", Font.BOLD, 23));
        
        helpButton.setBounds(370,10,100,20);
        add(helpButton);
        helpButton.setFont(new Font("Calibri", Font.BOLD, 15));
        helpButton.addActionListener(e->about());

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
        dropDownFile.addActionListener(e-> {
            try {
                motion();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        teacherPan = new TeacherPanel();
        add(teacherPan);
        teacherPan.setVisible(false);

        studentPan = new StudentPanel();
        add(studentPan);
        studentPan.setVisible(false);

        coursePan = new CoursePanel();
        add(coursePan);
        coursePan.setVisible(false);

        sectionPan = new SectionPanel();
        add(sectionPan);
        sectionPan.setVisible(false);

        aboutLabel.setBounds(285,100,100,35);
        add(aboutLabel);
        aboutLabel.setFont(new Font("Calibri", Font.BOLD, 20));
        aboutLabel.setVisible(false);

        creatorsLabel.setBounds(210,130,300,20);
        add(creatorsLabel);
        creatorsLabel.setFont(new Font("Calibri", Font.BOLD, 15));
        creatorsLabel.setVisible(false);


        versionLabel.setBounds(255,150,200,20);
        add(versionLabel);
        versionLabel.setFont(new Font("Calibri", Font.BOLD, 15));
        versionLabel.setVisible(false);






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

    public void about(){
        teacherPan.setVisible(false);
        studentPan.setVisible(false);
        coursePan.setVisible(false);
        sectionPan.setVisible(false);

        aboutLabel.setVisible(true);
        creatorsLabel.setVisible(true);
        versionLabel.setVisible(true);
    }

    public void motion() throws SQLException {
        if (String.valueOf(dropDownFile.getSelectedItem())=="Purge") {
            studentPan.purge();
            try {
                Main.myConn.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.exit(0);
        }
        if (String.valueOf(dropDownFile.getSelectedItem())=="Exit") {
            try {
                Main.myConn.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.exit(0);
        }
    }




    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        try {
            Main.myConn.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
