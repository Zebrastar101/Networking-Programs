import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.ObjectOutputStream;
import java.util.*;
public class CSFrame extends JFrame implements WindowListener{

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


    private ObjectOutputStream os;
    private char user;


    public CSFrame(ObjectOutputStream os, char user){
        super("nuh uh");

        this.os = os;
        this.user = user;

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
        SendButton.addActionListener(e -> {SendMethod();});

        ExitButton.setBounds(550,490,100,40);
        add(ExitButton);
        ExitButton.setFont(new Font("Calibri", Font.BOLD, 20));
        ExitButton.addActionListener(e -> {ExitByButtonMethod();});

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

    public void newUser(String newUser){
        // add (newUser + " has connected") to textsArrayList
        textsArrayList.add(newUser + " has connected");
        String[] textsArray = new String[textsArrayList.size()];
        for (int i=0; i<textsArrayList.size(); i++){
            textsArray[i]=textsArrayList.get(i);
        }

        textsJList.setListData(textsArray);
        clearMethod();

        // add newUser to UsersArrayList
        UsersArrayList.add(newUser);
        String[] usersArray = new String[UsersArrayList.size()];
        for (int i=0; i<UsersArrayList.size(); i++){
            usersArray[i]=UsersArrayList.get(i);
        }

        UsersJList.setListData(textsArray);

    }

    public void SendMethod(){
        if (!textToSend.getText().equals("")){
            textsArrayList.add(textToSend.getText());
            String[] textsArray = new String[textsArrayList.size()];
            for (int i=0; i<textsArrayList.size(); i++){
                textsArray[i]=textsArrayList.get(i);
            }

            textsJList.setListData(textsArray);
            clearMethod();
        }
        else{
            int errorMessage = JOptionPane.showConfirmDialog(null, "you have not typed anything", "Error", JOptionPane.OK_CANCEL_OPTION);
        }
    }

    public void ExitByButtonMethod(){
        // add (newUser + " has connected") to textsArrayList
        textsArrayList.add(user + " has disconnected");
        String[] textsArray = new String[textsArrayList.size()];
        for (int i=0; i<textsArrayList.size(); i++){
            textsArray[i]=textsArrayList.get(i);
        }

        textsJList.setListData(textsArray);

        //remove user from UsersArrayList;
        UsersArrayList.remove(user);
        String[] usersArray = new String[UsersArrayList.size()];
        for (int i=0; i<UsersArrayList.size(); i++){
            usersArray[i]=UsersArrayList.get(i);
        }
        UsersJList.setListData(usersArray);
    }

    public void ExitByWindowClosingMethod(String UserThatLeft){
        // add (newUser + " has connected") to textsArrayList
        textsArrayList.add(UserThatLeft + " disconnected");
        String[] textsArray = new String[textsArrayList.size()];
        for (int i=0; i<textsArrayList.size(); i++){
            textsArray[i]=textsArrayList.get(i);
        }

        textsJList.setListData(textsArray);

        //remove user from UsersArrayList;
        UsersArrayList.remove(UserThatLeft);
        String[] usersArray = new String[UsersArrayList.size()];
        for (int i=0; i<UsersArrayList.size(); i++){
            usersArray[i]=UsersArrayList.get(i);
        }
        UsersJList.setListData(usersArray);
    }

    public void clearMethod(){
        textToSend.setText("");
    }


    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        //needs to indirectly call ExitByWindowClosingMethod
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

