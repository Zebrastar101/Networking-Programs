import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.*;
public class FrameEX extends JFrame implements WindowListener{

    //labels and texts

    JLabel FirstNameLabel = new JLabel("First Name: ");
    JTextField FirstNameText = new JTextField("");
    JLabel LastNameLabel = new JLabel("Last Name: ");
    JTextField LastNameText = new JTextField("");
    JLabel PhoneNumLabel = new JLabel("Phone Number: ");
    JTextField PhoneNumText = new JTextField("");
    JLabel AddressLabel = new JLabel("Address: ");
    JTextField AddressText = new JTextField("");

    //Save and New buttons

    JButton SaveButton = new JButton("Save");
    JButton NewButton = new JButton("New");

    //Save Changes && Delete Changes Buttons
    JButton SaveChangesButton = new JButton("Save Changes");
    JButton DeleteContactButton = new JButton("Delete Contact");


    //Scroll
    JScrollPane Scroller = null;

    //JList
    JList<Contact> ContactJList = new JList<>();

    ArrayList<Contact> ContactArrayList = new ArrayList<>();


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
        setSize(950,720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);




        //labels and buttons



        // ContactJList

        //ContactJList.setSelectedIndex(0);
        ContactJList.addListSelectionListener(e->alrExistingContact());
        ContactJList.setBorder(BorderFactory.createLineBorder(Color.black));
        add(ContactJList);

        Scroller = new JScrollPane(ContactJList);
        Scroller.setBounds(10,20,430,650);
        add(Scroller);




        setVisible(true);
    }











}

