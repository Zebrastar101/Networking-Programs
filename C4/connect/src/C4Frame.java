import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.ObjectOutputStream;

public class C4Frame extends JFrame implements WindowListener, MouseListener {
    private String text = "";
    // the letter you are playing as
    private char player;
    // stores all the game data
    private GameData gameData = null;
    // output stream to the server
    ObjectOutputStream os;
    public C4Frame(GameData gameData, ObjectOutputStream os, char player){
        super("C4");
        this.gameData = gameData;
        this.os = os;
        this.player = player;
        addWindowListener(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Set initial frame message
        if(player == 'X')
            text = "Waiting for O to Connect";

        setSize(600,560);
        setResizable(false);
        setAlwaysOnTop(true);
        setVisible(true);
    }
    public void makeMove(int c, int r, char letter)
    {
        gameData.getGrid()[r][c] = letter;
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
    public void setText(String text) {
        this.text = text;
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        

    }
    public void setTurn(char turn) {
        if(turn==player)
            text = "Your turn";
        else
        {
            text = turn+"'s turn.";
        }
        repaint();
    }
    public char getTurn(char turn) {
        return turn;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.GRAY);
        g.setColor(Color.PINK);
        g.fillRect(0,0,getWidth(),getHeight());
        for(int c=0; c<6; c++){
            int y=40+(c*80);
            for(int i=0; i<7; i++){
                g.setColor(Color.WHITE);
                g.drawOval(20 + (i*80),y, 60, 60);
                g.fillOval(20 + (i*80),y,60,60);
            }
        }




    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

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
}