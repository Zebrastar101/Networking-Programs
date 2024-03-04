import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class CSFrame extends JFrame implements WindowListener{
    private String text = "";
    // the letter you are playing as
    private String newJoiner;
    // stores all the game data

    // output stream to the server
    private ObjectOutputStream os;
    public CSFrame(ObjectOutputStream os, String newJoiner){
        super("Chat Server");
        this.os = os;
        this.newJoiner = newJoiner;
        addWindowListener(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Set initial frame message

        setSize(600,560);
        setResizable(false);
        setAlwaysOnTop(true);
        setVisible(true);
    }






    public void setText(String text) {
        this.text = text;
        repaint();
    }




    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        try {
            os.writeObject(new CommandFromClient(CommandFromClient.QUIT, ""+player));
        } catch (Exception z) {
            z.printStackTrace();
        }

    }

    public void closeIn5()
    {
        //copied from https://stackoverflow.com/questions/62539867/how-to-make-a-jframe-close-itself-after-10-seconds
        new Timer(5_000, (e) -> { setVisible(false); dispose(); System.exit(0);}).start();
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

//my code below






