import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SectionPanel extends JPanel{

    JLabel panelTitleLabel = new JLabel("Sections");
    JLabel teacherLabel = new JLabel("Teacher: ");
    JLabel courseLabel = new JLabel("Course: ");

    JComboBox<String> teachersDropDown = new JComboBox<String>();
    JComboBox<String> coursesDropDown = new JComboBox<String>();

    JTable sectionTable;

    JScrollPane jScrollPane;

    JButton newButton = new JButton("New");
    JButton saveButton = new JButton("Save");
    JButton deleteButton = new JButton("Delete");
    JButton rosterButton = new JButton("Roster");

    Section sec;

    Connection con;
    Statement stm;
    ResultSet teacherResultSet;
    ResultSet courseResultSet;
    

    public SectionPanel() {


        setLayout(null);
        setBounds(15, 40, 600, 630);
        setBorder(BorderFactory.createLineBorder(Color.black));

        panelTitleLabel.setBounds(15, 5, 100, 35);
        panelTitleLabel.setFont(new Font("Calibri", Font.BOLD, 23));
        add(panelTitleLabel);

        teacherLabel.setBounds(120, 80, 250, 20);
        teacherLabel.setFont(new Font("Calibri", Font.BOLD, 15));
        add(teacherLabel);

        teachersDropDown.setBounds(260, 80, 230, 20);
        add(teachersDropDown);

        courseLabel.setBounds(120, 110, 250, 20);
        courseLabel.setFont(new Font("Calibri", Font.BOLD, 15));
        add(courseLabel);

        coursesDropDown.setBounds(260, 110, 230, 20);
        add(coursesDropDown);


        //buttons

        newButton.setBounds(120, 140, 70, 20);
        newButton.setFont(new Font("Calibri", Font.BOLD, 10));
        add(newButton);
        newButton.addActionListener(e -> {
            try {
                newSection((String) teachersDropDown.getSelectedItem(), (String) coursesDropDown.getSelectedItem());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        saveButton.setBounds(210, 140, 70, 20);
        saveButton.setFont(new Font("Calibri", Font.BOLD, 10));
        add(saveButton);

        deleteButton.setBounds(300, 140, 70, 20);
        deleteButton.setFont(new Font("Calibri", Font.BOLD, 10));
        add(deleteButton);

        rosterButton.setBounds(400, 140, 90, 20);
        rosterButton.setFont(new Font("Calibri", Font.BOLD, 10));
        add(rosterButton);


        sec = new Section(Main.myConn);
        sectionTable=sec.getStudentTable();
        //below from https://www.tabnine.com/code/java/methods/javax.swing.JTable/getSelectedRow
        sectionTable.addMouseListener(new MouseAdapter() {
            //Idk how to get the selected values to pop up for this one
            public void mouseClicked(MouseEvent e) {
                teachersDropDown.setSelectedItem((String)sectionTable.getValueAt(sectionTable.getSelectedRow() , 1));
                teachersDropDown.setSelectedItem((String)sectionTable.getValueAt(sectionTable.getSelectedRow() , 2));
            }
        });
        jScrollPane = new JScrollPane(sectionTable);
        jScrollPane.setBounds(50,190,500, 400);
        add(jScrollPane);


    }

    public void reload()
    {
        con = Main.myConn;
        try{
            stm=con.createStatement();
            teachersDropDown.removeAllItems();
            coursesDropDown.removeAllItems();
            teacherResultSet=stm.executeQuery("Select*from teachers WHERE id >=1");
            while(teacherResultSet!=null && teacherResultSet.next()){
                String teacher = teacherResultSet.getObject(2) + " " + teacherResultSet.getObject(3);
                teachersDropDown.addItem(teacher);
            }
            courseResultSet=stm.executeQuery("Select*from courses WHERE id >=1");
            while(courseResultSet!=null && courseResultSet.next()){
                String course = String.valueOf(courseResultSet.getObject(2));
                coursesDropDown.addItem(course);
            }

        }catch(SQLException e){
            e.printStackTrace();

        }
    }

    public void newSection(String teacher, String course) throws SQLException {
        sectionTable=sec.addSection(teacher, course);
        jScrollPane.setViewportView(sectionTable);
    }

}

