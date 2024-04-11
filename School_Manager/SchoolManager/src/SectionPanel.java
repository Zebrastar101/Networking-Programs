import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.*;
public class SectionPanel extends JPanel{

    JLabel panelTitleLabel = new JLabel("Sections");
    JLabel teacherLabel = new JLabel("Teacher: ");
    JLabel courseLabel = new JLabel("Course: ");

    JComboBox<String> teachersDropDown = new JComboBox<String>();
    JComboBox<String> coursesDropDown = new JComboBox<String>();
    JComboBox<String> studentsDropDown = new JComboBox<String>();

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
        setBounds(15, 40, 950, 630);
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

        JLabel studentLab= new JLabel();
        studentLab.setBounds(630, 330, 50,50);
        studentLab.setFont(new Font("Calibri", Font.BOLD, 15));
        add(studentLab);

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
        saveButton.addActionListener(e -> {
            try {
                if(!sectionTable.getSelectionModel().isSelectionEmpty()){
                    saveSectionChanges((String) teachersDropDown.getSelectedItem(), (String) coursesDropDown.getSelectedItem(), (Integer) sectionTable.getValueAt(sectionTable.getSelectedRow() , 0));
                }
                else{
                    int errorMessage = JOptionPane.showConfirmDialog(null, "No section was selected", "Error", JOptionPane.OK_CANCEL_OPTION);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        deleteButton.setBounds(300, 140, 70, 20);
        deleteButton.setFont(new Font("Calibri", Font.BOLD, 10));
        add(deleteButton);
        deleteButton.addActionListener(e-> {
            try {
                if(!sectionTable.getSelectionModel().isSelectionEmpty()){
                    delSection((Integer) sectionTable.getValueAt(sectionTable.getSelectedRow() , 0));
                }
                else{
                    int errorMessage = JOptionPane.showConfirmDialog(null, "No section was selected", "Error", JOptionPane.OK_CANCEL_OPTION);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });




        sec = new Section(Main.myConn);
        sectionTable=sec.getSectionTable();
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

        JTable enrollment=new JTable();
        JScrollPane jscrollEnroll= new JScrollPane(enrollment);
        jscrollEnroll.setBounds(630,80,250, 200);
        add(jscrollEnroll);


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
                String teacher = teacherResultSet.getObject(2) + " " + teacherResultSet.getObject(3)+ "("+teacherResultSet.getObject(1)+")";
                teachersDropDown.addItem(teacher);
            }
            courseResultSet=stm.executeQuery("Select*from courses WHERE id >=1");
            while(courseResultSet!=null && courseResultSet.next()){
                String course = String.valueOf(courseResultSet.getObject(2))+" ("+courseResultSet.getObject(1)+") ";
                coursesDropDown.addItem(course);
            }

        }catch(SQLException e){
            e.printStackTrace();

        }
    }
    public void reloadStudentDrop( JTable enrollment)
    {
        con = Main.myConn;

        try{
            stm=con.createStatement();
            studentsDropDown.removeAllItems();
            ResultSet studentRS=stm.executeQuery("Select*from students WHERE id >=1");
            ArrayList<String> studs=new ArrayList<>();
            while(studentRS!=null && studentRS.next()){
                studs.add(studentRS.getObject(2) + " " + studentRS.getObject(3)+ "("+studentRS.getObject(1)+")");

            }
            Collections.sort(studs);
            ArrayList tableList= getTableData(enrollment);
            Collections.sort(tableList);
            ArrayList<String> dropList=new ArrayList<>();
            int same=0;
            for(int x=0; x<studs.size();x++){
                String val= studs.get(x);
                for(int z=0; z<tableList.size(); z++){
                    if(val==tableList.get(z)){
                        same+=1;
                    }
                }
                if(same==0){
                    dropList.add(val);
                }

            }
            for(int c=0; c<dropList.size(); c++){
                String student = dropList.get(c);
                studentsDropDown.addItem(student);
            }





        }catch(SQLException e){
            e.printStackTrace();

        }
    }

    public void newSection(String teacher, String course) throws SQLException {
        sectionTable=sec.addSection(teacher, course);
        jScrollPane.setViewportView(sectionTable);
    }

    public void saveSectionChanges(String teacher, String course, int id) throws SQLException {
        sectionTable=sec.saveSection(teacher, course, id);
        jScrollPane.setViewportView(sectionTable);
    }

    public void delSection(int id) throws SQLException {
        sectionTable=sec.deleteSection(id);
        jScrollPane.setViewportView(sectionTable);
    }



    public void purge() throws SQLException {
        sec.purgeSection();
    }

    public ArrayList<String> getTableData (JTable table) {
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
        ArrayList<String> tableData=new ArrayList<>();
            for (int j = 0 ; j < nCol ; j++)
                tableData.add(String.valueOf(dtm.getValueAt(0,j)));
        return tableData;
    }
}

