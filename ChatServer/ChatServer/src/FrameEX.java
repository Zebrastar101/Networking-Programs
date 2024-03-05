import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.*;
public class FrameEX extends JFrame implements WindowListener{

    //labels and texts

    JLabel ChatLabel = new JLabel("Chat");
    JTextField textToSend = new JTextField("teeeheee");
    JLabel Userslabel = new JLabel("Users");


    //Send and Exit buttons

    JButton SendButton = new JButton("Send");
    JButton ExitButton = new JButton("Exit");



    //Scroll
    JScrollPane Scroller = null;

    //JList
    JList<String> UsersJList = new JList<>();

    JList<String> textsJList = new JList<>();

    //arrayLists that will convert to JList
    ArrayList<String> UsersArrayList = new ArrayList<>();

    ArrayList<String> textsArrayList = new ArrayList<>();


    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

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



    public FrameEX(){




        super("nuh uh");
        setSize(700,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        getContentPane().setBackground(new Color(45, 45, 45));


        //labels and buttons
        ChatLabel.setBounds(10,20,40,15);
        add(ChatLabel);
        ChatLabel.setFont(new Font("Calibri", Font.BOLD, 15));
        ChatLabel.setForeground(Color.white);

        Userslabel.setBounds(525,20,40,15);
        add(Userslabel);
        Userslabel.setFont(new Font("Calibri", Font.BOLD, 15));
        Userslabel.setForeground(Color.white);

        SendButton.setBounds(550,445,100,40);
        add(SendButton);
        SendButton.setFont(new Font("Calibri", Font.BOLD, 20));

        ExitButton.setBounds(550,490,100,40);
        add(ExitButton);
        ExitButton.setFont(new Font("Calibri", Font.BOLD, 20));

        //textbox
        textToSend.setBounds(10,440,500,100);
        add(textToSend);
        textToSend.setFont(new Font("Calibri", Font.BOLD, 15));



        // JList and ScrollPane


        add(textsJList);

        Scroller = new JScrollPane(textsJList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        Scroller.setBounds(10,30,500,400);
        add(Scroller);


        add(UsersJList);

        Scroller = new JScrollPane(UsersJList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        Scroller.setBounds(525,30,150,400);
        add(Scroller);






        setVisible(true);
    }











}

