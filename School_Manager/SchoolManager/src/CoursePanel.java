import javax.swing.*;
import java.awt.*;

public class CoursePanel extends JPanel {

    JLabel panelTitleLabel = new JLabel("Courses");
    JLabel courseLabel = new JLabel("Course Name: ");

    JTextField courseTextField = new JTextField("");


    JTable studentTable;

    JScrollPane jScrollPane;

    JButton newButton = new JButton("New");
    JButton saveButton = new JButton("Save");
    JButton deleteButton = new JButton("Delete");



    JRadioButton acaRadioButton = new JRadioButton("Academic");
    JRadioButton KAPRadioButton = new JRadioButton("KAP");
    JRadioButton APRadioButton = new JRadioButton("AP");

    ButtonGroup G = new ButtonGroup();






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








    }
}

