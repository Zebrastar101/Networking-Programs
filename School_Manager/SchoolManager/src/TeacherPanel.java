import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TeacherPanel extends JPanel{

    JLabel panelTitleLabel = new JLabel("Teachers");
    JLabel teacherFNLabel = new JLabel("Teacher's first name: ");
    JLabel teacherLNLabel = new JLabel("Teacher's last name: ");

    JTextField teacherFNTextField = new JTextField("");
    JTextField teacherLNTextField = new JTextField("");

    JTable teacherTable;

    JTable sectionsTaughtTable;

    Connection con;



    JScrollPane jScrollPane;

    JScrollPane jScrollSection;

    JButton newButton = new JButton("New");
    JButton saveButton = new JButton("Save");
    JButton deleteButton = new JButton("Delete");

    Teacher t;
    JLabel secTaughtLab= new JLabel("Sections Taught");





    public TeacherPanel() {

        setLayout(null);
        setBounds(15, 40, 950, 630);
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

        secTaughtLab.setBounds(690, 40, 140,35);
        secTaughtLab.setFont(new Font("Calibri", Font.BOLD, 20));
        add(secTaughtLab);


        //buttons

        newButton.setBounds(120, 140, 70, 20);
        newButton.setFont(new Font("Calibri", Font.BOLD, 10));
        add(newButton);
        newButton.addActionListener(e-> {
            try {
                newTeacher(teacherFNTextField.getText(), teacherLNTextField.getText());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        saveButton.setBounds(270, 140, 70, 20);
        saveButton.setFont(new Font("Calibri", Font.BOLD, 10));
        add(saveButton);
        saveButton.addActionListener(e-> {
            try {
                if(!teacherTable.getSelectionModel().isSelectionEmpty()){
                    saveTeacherChanges(teacherFNTextField.getText(), teacherLNTextField.getText(), (Integer) teacherTable.getValueAt(teacherTable.getSelectedRow() , 0));
                }
                else{
                    int errorMessage = JOptionPane.showConfirmDialog(null, "No student was selected", "Error", JOptionPane.OK_CANCEL_OPTION);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        deleteButton.setBounds(420, 140, 70, 20);
        deleteButton.setFont(new Font("Calibri", Font.BOLD, 10));
        add(deleteButton);
        deleteButton.addActionListener(e-> {
            try {
                if(!teacherTable.getSelectionModel().isSelectionEmpty()){
                    delTeacher((Integer) teacherTable.getValueAt(teacherTable.getSelectedRow() , 0));
                }
                else{
                    int errorMessage = JOptionPane.showConfirmDialog(null, "No student was selected", "Error", JOptionPane.OK_CANCEL_OPTION);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });



        t = new Teacher();
        teacherTable=t.getTeacherTable();
        //below from https://www.tabnine.com/code/java/methods/javax.swing.JTable/getSelectedRow
        teacherTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String firstName = (String) teacherTable.getValueAt(teacherTable.getSelectedRow() , 1);
                String lastName = (String) teacherTable.getValueAt(teacherTable.getSelectedRow() , 2);
                teacherFNTextField.setText(firstName);
                teacherLNTextField.setText(lastName);

                sectionsTaughtTable=buildSectionTable((int)teacherTable.getValueAt(teacherTable.getSelectedRow() , 0));
                jScrollSection.setViewportView(sectionsTaughtTable);
            }
        });
        jScrollPane = new JScrollPane(teacherTable);
        jScrollPane.setBounds(50,190,500, 400);
        add(jScrollPane);

        sectionsTaughtTable=makeJTable(new Object[0][0]);
        jScrollSection = new JScrollPane(sectionsTaughtTable);
        jScrollSection.setBounds(630,80,250, 200);
        add(jScrollSection);

    }



    public void newTeacher(String fName, String lName) throws SQLException {
        if(!teacherFNTextField.getText().isEmpty() && !teacherLNTextField.getText().isEmpty()){
            teacherTable=t.addTeacher(fName, lName);
            jScrollPane.setViewportView(teacherTable);
            teacherFNTextField.setText("");
            teacherLNTextField.setText("");
            teacherTable.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    String firstName = (String) teacherTable.getValueAt(teacherTable.getSelectedRow() , 1);
                    String lastName = (String) teacherTable.getValueAt(teacherTable.getSelectedRow() , 2);
                    teacherFNTextField.setText(firstName);
                    teacherLNTextField.setText(lastName);

                    sectionsTaughtTable=buildSectionTable((int)teacherTable.getValueAt(teacherTable.getSelectedRow() , 0));
                    jScrollSection.setViewportView(sectionsTaughtTable);
                }
            });
        }
        else{
            int errorMessage = JOptionPane.showConfirmDialog(null, "Both first and last name are needed", "Error", JOptionPane.OK_CANCEL_OPTION);
        }

    }


    public void saveTeacherChanges(String fName, String lName, int id) throws SQLException {

        if(!teacherFNTextField.getText().isEmpty() && !teacherLNTextField.getText().isEmpty()){
            teacherTable=t.saveTeacher(fName, lName, id);
            jScrollPane.setViewportView(teacherTable);
            teacherFNTextField.setText("");
            teacherLNTextField.setText("");
            teacherTable.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    String firstName = (String) teacherTable.getValueAt(teacherTable.getSelectedRow() , 1);
                    String lastName = (String) teacherTable.getValueAt(teacherTable.getSelectedRow() , 2);
                    teacherFNTextField.setText(firstName);
                    teacherLNTextField.setText(lastName);

                    sectionsTaughtTable=buildSectionTable((int)teacherTable.getValueAt(teacherTable.getSelectedRow() , 0));
                    jScrollSection.setViewportView(sectionsTaughtTable);
                }
            });
        }
        else{
            int errorMessage = JOptionPane.showConfirmDialog(null, "Both first and last name are needed", "Error", JOptionPane.OK_CANCEL_OPTION);
        }
    }

    public void delTeacher(int id) throws SQLException {
        if(!teacherFNTextField.getText().isEmpty() && !teacherLNTextField.getText().isEmpty()){
            teacherTable=t.deleteTeacher(id);
            jScrollPane.setViewportView(teacherTable);
            teacherFNTextField.setText("");
            teacherLNTextField.setText("");
            teacherTable.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    String firstName = (String) teacherTable.getValueAt(teacherTable.getSelectedRow() , 1);
                    String lastName = (String) teacherTable.getValueAt(teacherTable.getSelectedRow() , 2);
                    teacherFNTextField.setText(firstName);
                    teacherLNTextField.setText(lastName);

                    sectionsTaughtTable=buildSectionTable((int)teacherTable.getValueAt(teacherTable.getSelectedRow() , 0));
                    jScrollSection.setViewportView(sectionsTaughtTable);
                }
            });
        }
        else{
            int errorMessage = JOptionPane.showConfirmDialog(null, "No teacher was selected", "Error", JOptionPane.OK_CANCEL_OPTION);
        }
    }

    public void fileImport(Scanner sc) throws SQLException {
        teacherTable=t.importFile(sc);
        jScrollPane.setViewportView(teacherTable);
        teacherFNTextField.setText("");
        teacherLNTextField.setText("");
        teacherTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String firstName = (String) teacherTable.getValueAt(teacherTable.getSelectedRow() , 1);
                String lastName = (String) teacherTable.getValueAt(teacherTable.getSelectedRow() , 2);
                teacherFNTextField.setText(firstName);
                teacherLNTextField.setText(lastName);

                sectionsTaughtTable=buildSectionTable((int)teacherTable.getValueAt(teacherTable.getSelectedRow() , 0));
                jScrollSection.setViewportView(sectionsTaughtTable);
            }
        });
    }

    public JTable buildSectionTable(int teacherID){
        con=Main.myConn;
        try{
            Statement stm1 = con.createStatement();
            Statement stm3 = con.createStatement();
            ResultSet secResultSet;

            ResultSet courseResultSet;
            secResultSet = stm1.executeQuery("Select*from section WHERE section_id >=1");

            ArrayList<Object> perRow = new ArrayList<>();
            ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();

            while(secResultSet!=null && secResultSet.next()){
                courseResultSet = stm3.executeQuery("Select*from course WHERE course_id >=1");
                if((int)secResultSet.getObject(3)==teacherID){
                    perRow.add(secResultSet.getObject(1));
                    int courseID= (int) secResultSet.getObject(2);
                    while(courseResultSet != null && courseResultSet.next()){
                        if((int)courseResultSet.getObject(1) == courseID){
                            String course = String.valueOf(courseResultSet.getObject(2))+" ("+courseResultSet.getObject(1)+") ";
                            //System.out.println(course);
                            perRow.add(course);
                            break;
                        }
                    }
                    data.add(perRow);
                    perRow = new ArrayList<>();
                }
            }

            if (data.size() != 0) {
                Object[][] dataArray = new Object[data.size()][data.get(0).size()];
                for (int r = 0; r < dataArray.length; r++) {
                    for (int c = 0; c < dataArray[0].length; c++) {
                        dataArray[r] = data.get(r).toArray();
                        //dataArray[r][c]=data.get(r).get(c);

                    }
                }
                System.out.println("data for SectionsTaught table"+ Arrays.deepToString(dataArray));

                return makeJTable(dataArray);
            }


            return makeJTable(new Object[0][0]);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public JTable makeJTable(Object[][] dataArray) {
        DefaultTableModel tableModel = new DefaultTableModel(dataArray, new String[]{"Section ID", "Course Name"}) {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        JTable table = new JTable();
        table.setModel(tableModel);
        table.getTableHeader().setReorderingAllowed(false);

        return table;
    }

}
