import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Scanner;

public class TeacherPanel extends JPanel{

    JLabel panelTitleLabel = new JLabel("Teachers");
    JLabel teacherFNLabel = new JLabel("Teacher's first name: ");
    JLabel teacherLNLabel = new JLabel("Teacher's last name: ");

    JTextField teacherFNTextField = new JTextField("");
    JTextField teacherLNTextField = new JTextField("");

    JTable teacherTable;

    JScrollPane jScrollPane;

    JButton newButton = new JButton("New");
    JButton saveButton = new JButton("Save");
    JButton deleteButton = new JButton("Delete");
    JButton sectionsButton = new JButton("Sections");

    Teacher t;


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
        newButton.addActionListener(e-> {
            try {
                newTeacher(teacherFNTextField.getText(), teacherLNTextField.getText());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        saveButton.setBounds(210, 140, 70, 20);
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

        deleteButton.setBounds(300, 140, 70, 20);
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

        sectionsButton.setBounds(400, 140, 90, 20);
        sectionsButton.setFont(new Font("Calibri", Font.BOLD, 10));
        add(sectionsButton);


        t = new Teacher(Main.myConn);
        teacherTable=t.getTeacherTable();
        //below from https://www.tabnine.com/code/java/methods/javax.swing.JTable/getSelectedRow
        teacherTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String firstName = (String) teacherTable.getValueAt(teacherTable.getSelectedRow() , 1);
                String lastName = (String) teacherTable.getValueAt(teacherTable.getSelectedRow() , 2);
                teacherFNTextField.setText(firstName);
                teacherLNTextField.setText(lastName);
            }
        });
        jScrollPane = new JScrollPane(teacherTable);
        jScrollPane.setBounds(50,190,500, 400);
        add(jScrollPane);
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
                }
            });
        }
        else{
            int errorMessage = JOptionPane.showConfirmDialog(null, "No teacher was selected", "Error", JOptionPane.OK_CANCEL_OPTION);
        }
    }
    public void purge() throws SQLException {
        t.purgeTeacher();
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
            }
        });
    }

}
