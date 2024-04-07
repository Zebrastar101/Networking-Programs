import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

        saveButton.setBounds(210, 140, 70, 20);
        saveButton.setFont(new Font("Calibri", Font.BOLD, 10));
        add(saveButton);

        deleteButton.setBounds(300, 140, 70, 20);
        deleteButton.setFont(new Font("Calibri", Font.BOLD, 10));
        add(deleteButton);

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
}
