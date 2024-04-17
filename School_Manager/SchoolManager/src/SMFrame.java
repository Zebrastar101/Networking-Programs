import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Scanner;

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
    String exit="Exit";

    JButton helpButton = new JButton("Help");





    TeacherPanel teacherPan = null;
    StudentPanel studentPan = null;
    CoursePanel coursePan = null;
    SectionPanel sectionPan = null;


    public SMFrame(Connection c) throws SQLException {
        super("School Manager");
        setSize(1000,750);
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
        sectionPan.setVisible(true);

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
            sectionPan.reload();
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
        if (String.valueOf(dropDownFile.getSelectedItem())=="Exit") {
            try {
                Main.myConn.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.exit(0);
        }
        if(String.valueOf(dropDownFile.getSelectedItem())=="Export Data"){
            try{
                File ContactsFile = new File("ContactsFile.csv");
                if(!ContactsFile.exists()){
                    ContactsFile.createNewFile();
                }
                FileWriter fw = new FileWriter(ContactsFile, false);

                try{
                    stm=con.createStatement();
                    ResultSet teacherResultSet=stm.executeQuery("Select*from teacher WHERE teacher_id >=1");
                    fw.write("TEACHERS:\n");
                    while(teacherResultSet!=null && teacherResultSet.next()){
                        String teacher = teacherResultSet.getObject(1) + "," +teacherResultSet.getObject(2) + "," + teacherResultSet.getObject(3)+"\n";
                        fw.write(teacher);
                    }
                    ResultSet studentResultSet=stm.executeQuery("Select*from student WHERE student_id >=1");
                    fw.write("\nSTUDENTS:\n");
                    while(studentResultSet!=null && studentResultSet.next()){
                        String student = studentResultSet.getObject(1) + "," +studentResultSet.getObject(2) + "," + studentResultSet.getObject(3)+"\n";
                        fw.write(student);
                    }
                    ResultSet courseResultSet=stm.executeQuery("Select*from course WHERE course_id >=1");
                    fw.write("\nCOURSES:\n");
                    while(courseResultSet!=null && courseResultSet.next()){
                        String course = courseResultSet.getObject(1) + "," +courseResultSet.getObject(2) + "," + courseResultSet.getObject(3)+"\n";
                        fw.write(course);
                    }
                    ResultSet sectionResultSet=stm.executeQuery("Select*from section WHERE section_id >=1");
                    fw.write("\nSECTIONS:\n");
                    while(sectionResultSet!=null && sectionResultSet.next()){
                        String section = sectionResultSet.getObject(1) + "," +sectionResultSet.getObject(2) + "," + sectionResultSet.getObject(3)+"\n";
                        fw.write(section);
                    }




                }catch(SQLException e){
                    e.printStackTrace();

                }

                fw.close();
            }
            catch (Exception error){
                error.printStackTrace();
            }
        }

        if(String.valueOf(dropDownFile.getSelectedItem())=="Import Data"){
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("user.home"));
            int result = chooser.showOpenDialog(null);
            if(result!=JFileChooser.APPROVE_OPTION){
                return;
            }
            try{
                stm.execute("DROP TABLE IF EXISTS section;");
                stm.execute("DROP TABLE IF EXISTS course;");
                stm.execute("DROP TABLE IF EXISTS teacher;");
                stm.execute("DROP TABLE IF EXISTS student;");
                File f = chooser.getSelectedFile();
                if (f.exists()){
                    Scanner fromFile=new Scanner(f);
                    studentPan.fileImport(fromFile);
                    fromFile = new Scanner(f);
                    teacherPan.fileImport(fromFile);
                    fromFile = new Scanner(f);
                    coursePan.fileImport(fromFile);
                    fromFile = new Scanner(f);
                    sectionPan.fileImport(fromFile);
                }

            }
            catch (Exception error){
                error.printStackTrace();
            }

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
