import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class CoursePanel extends JPanel {

    JLabel panelTitleLabel = new JLabel("Courses");
    JLabel courseLabel = new JLabel("Course Name: ");

    JTextField courseTextField = new JTextField("");


    JTable courseTable;

    JScrollPane jScrollPane;

    JButton newButton = new JButton("New");
    JButton saveButton = new JButton("Save");
    JButton deleteButton = new JButton("Delete");



    JRadioButton acaRadioButton = new JRadioButton("Academic");
    JRadioButton KAPRadioButton = new JRadioButton("KAP");
    JRadioButton APRadioButton = new JRadioButton("AP");

    ButtonGroup G = new ButtonGroup();

    Course c;






    public CoursePanel(){
        setLayout(null);
        setBounds(15,40,600,630);
        setBorder(BorderFactory.createLineBorder(Color.black));

        panelTitleLabel.setBounds(15,5,100,35);
        panelTitleLabel.setFont(new Font("Calibri", Font.BOLD, 23));
        add(panelTitleLabel);

        courseLabel.setBounds(140,110,100,20);
        courseLabel.setFont(new Font("Calibri", Font.BOLD, 15));
        add(courseLabel);

        courseTextField.setBounds(240,110,210,20);
        courseTextField.setFont(new Font("Calibri", Font.BOLD, 15));
        add(courseTextField);




        //buttons

        newButton.setBounds(140,140,70,20);
        newButton.setFont(new Font("Calibri", Font.BOLD, 10));
        add(newButton);
        newButton.addActionListener(e -> {
            try {
                newCourse(courseTextField.getText());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        saveButton.setBounds(260,140,70,20);
        saveButton.setFont(new Font("Calibri", Font.BOLD, 10));
        add(saveButton);

        deleteButton.setBounds(380,140,70,20);
        deleteButton.setFont(new Font("Calibri", Font.BOLD, 10));
        add(deleteButton);



        //radioButtons
        acaRadioButton.setBounds(210,80,80,20);
        acaRadioButton.setFont(new Font("Calibri", Font.BOLD, 12));
        add(acaRadioButton);

        KAPRadioButton.setBounds(290,80,50,20);
        KAPRadioButton.setFont(new Font("Calibri", Font.BOLD, 12));
        add(KAPRadioButton);

        APRadioButton.setBounds(340,80,50,20);
        APRadioButton.setFont(new Font("Calibri", Font.BOLD, 12));
        add(APRadioButton);

        G.add(acaRadioButton);
        G.add(KAPRadioButton);
        G.add(APRadioButton);


        c = new Course(Main.myConn);
        courseTable=c.getCourseTable();
        //below from https://www.tabnine.com/code/java/methods/javax.swing.JTable/getSelectedRow
        courseTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String courseName = (String) courseTable.getValueAt(courseTable.getSelectedRow() , 1);
                String type = (String) courseTable.getValueAt(courseTable.getSelectedRow() , 2);
                courseTextField.setText(courseName);
                if(type.equals("Academic")){
                    G.setSelected(acaRadioButton.getModel(), true);
                }
                if(type=="KAP"){
                    G.setSelected(KAPRadioButton.getModel(), true);
                }
                if(type=="AP"){
                    G.setSelected(APRadioButton.getModel(), true);
                }

            }
        });
        jScrollPane = new JScrollPane(courseTable);
        jScrollPane.setBounds(50,190,500, 400);
        add(jScrollPane);




    }

    public void newCourse(String course) throws SQLException {
        if(!courseTextField.getText().isEmpty() && (acaRadioButton.isSelected() || KAPRadioButton.isSelected() || APRadioButton.isSelected())){
            if(acaRadioButton.isSelected()){
                courseTable=c.addCourse(course, "Academic");
            }
            else if (KAPRadioButton.isSelected()) {
                courseTable=c.addCourse(course, "KAP");
            }
            else{
                courseTable=c.addCourse(course, "AP");
            }
            G.clearSelection();
            jScrollPane.setViewportView(courseTable);
            courseTextField.setText("");

        }
        else{
            int errorMessage = JOptionPane.showConfirmDialog(null, "Course name and type is needed", "Error", JOptionPane.OK_CANCEL_OPTION);
        }

    }


}

