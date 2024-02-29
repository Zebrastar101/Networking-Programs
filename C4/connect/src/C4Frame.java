import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class C4Frame extends JFrame implements WindowListener, MouseListener {
    private String text = "";
    // the letter you are playing as
    private char player;
    // stores all the game data
     private GameData gameData = null;
    // output stream to the server
    private ObjectOutputStream os;
    public C4Frame(GameData gameData, ObjectOutputStream os, char player){
        super("C4");
        this.gameData = gameData;
        this.os = os;
        this.player = player;
        addWindowListener(this);
        addMouseListener(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Set initial frame message
        if(player == 'X')
            text = "Waiting for R to Connect";

        setSize(600,560);
        setResizable(false);
        setAlwaysOnTop(true);
        setVisible(true);
    }
    public void restarting() {
        gameData.reset();
        repaint();
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        //Right Clicked
       if(e.getButton()==3){


           try {
               os.writeObject(new CommandFromClient(CommandFromClient.RESTART, "" + player));
           } catch (Exception z) {
               z.printStackTrace();
           }




           }
       }

    public void setText(String text) {
        this.text = text;
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("mouse pressed");
        int x = e.getX();
        // will indirectly call drop method which is similar to the makeMove method in the TTTFrame in tic tac toe
        int r=-1;
        int c=-1;
        //7 if statements for 7 columns
        //column 1
        if(x>=20 && x <=80){
            for(int i=5 ; i>=0 ; i--){
                if (gameData.getGrid()[i][0]==' '){
                    r=i;
                    c=0;
                    System.out.println("r: "+r+" c: "+c+" Mouse Clicked Worked");
                    break;
                }
            }
        }
        //column 2
        if(x>=100 && x <=160){
            for(int i=5 ; i>=0 ; i--){
                if (gameData.getGrid()[i][1]==' '){
                    r=i;
                    c=1;
                    System.out.println("r: "+r+" c: "+c+" Mouse Clicked Worked");
                    break;
                }
            }
        }
        //column 3
        if(x>=180 && x <=240){
            for(int i=5 ; i>=0 ; i--){
                if (gameData.getGrid()[i][2]==' '){
                    r=i;
                    c=2;
                    System.out.println("r: "+r+" c: "+c+" Mouse Clicked Worked");
                    break;
                }
            }
        }
        //column 4
        if(x>=260 && x <=320){
            for(int i=5 ; i>=0 ; i--){
                if (gameData.getGrid()[i][3]==' '){
                    r=i;
                    c=3;
                    System.out.println("r: "+r+" c: "+c+" Mouse Clicked Worked");
                    break;
                }
            }
        }
        //column 5
        if(x>=340 && x <=400){
            for(int i=5 ; i>=0 ; i--){
                if (gameData.getGrid()[i][4]==' '){
                    r=i;
                    c=4;
                    System.out.println("r: "+r+" c: "+c+" Mouse Clicked Worked");
                    break;
                }
            }
        }
        //column 6
        if(x>=420 && x <=480){
            for(int i=5 ; i>=0 ; i--){
                if (gameData.getGrid()[i][5]==' '){
                    r=i;
                    c=5;
                    System.out.println("r: "+r+" c: "+c+" Mouse Clicked Worked");
                    break;
                }
            }
        }
        //column 7
        if(x>=500 && x <=560){
            for(int i=5 ; i>=0 ; i--){
                if (gameData.getGrid()[i][6]==' '){
                    r=i;
                    c=6;
                    System.out.println("r: "+r+" c: "+c+" Mouse Clicked Worked");
                    break;
                }
            }
        }


        if (c!=-1) {
            try {
                os.writeObject(new CommandFromClient(CommandFromClient.MOVE, "" + c + r + player));
            } catch (Exception z) {
                z.printStackTrace();
            }
        }

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

    public void drop(int c, int r, char letter)
    {
        gameData.getGrid()[r][c] = letter;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.GRAY);
        g.setColor(Color.PINK);
        g.fillRect(0,0,getWidth(),getHeight());
        g.drawString(text, 300, 500);
        for(int c=0; c<6; c++){
            int y=40+(c*80);
            for(int i=0; i<7; i++){
                g.setColor(Color.WHITE);
                g.drawOval(20 + (i*80),y, 60, 60);
                g.fillOval(20 + (i*80),y,60,60);
            }
        }



        for(int c=0; c<6; c++){
            int y=40+(c*80);
            for(int i=0; i<7; i++){
                if(gameData.getGrid()[c][i] == 'R'){
                    g.setColor(Color.RED);
                    g.drawOval(20 + (i*80),y, 60, 60);
                    g.fillOval(20 + (i*80),y,60,60);
                }
                else if(gameData.getGrid()[c][i] == 'Y'){
                    g.setColor(Color.YELLOW);
                    g.drawOval(20 + (i*80),y, 60, 60);
                    g.fillOval(20 + (i*80),y,60,60);
                }
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

//my code below





