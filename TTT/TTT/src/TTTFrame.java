
import java.awt.*;
import java.awt.event.KeyListener;
import javax.swing.*;
public class TTTFrame extends JFrame implements KeyListener{
    //Display message
    private String text="";
    //the letter you are playing
    private char player;
    //store the letter of the active player
    private char turn='X';
    //store all game data
    private GameData gameData=null;
    //output stream to the server
    public TTTFrame(GameData gameData, char player) {
        super("TTT Game");
        //set attributes
        this.gameData = gameData;
        this.player = player;
        //add a key listener
        addKeyListener(this);
        //make closing the fram close the program
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setSize(400, 460);
        setAlwaysOnTop(true);
        setVisible(true);

    }
    public void paint(Graphics g) {
        //draws background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        //draws the display text to the screen
        g.setColor(Color.RED);
        g.setFont(new Font("Times New Roman", Font.BOLD, 30));
        g.drawString(text+"dfasdf asdfas", 20, 55);

        //draw the tic-tac-toe grid lines to the screen
        g.setColor(Color.RED);
        for(int y=0; y<=1; y++) {
            g.drawLine(0, (y+1)*133+60, getWidth(), (y+1)*133+60);
        }
        for(int x=0; x<=1; x++) {
            g.drawLine((x+1)*133, 60, (x+1)*133, getHeight());
        }
        g.setFont(new Font("Times New Roman", Font.BOLD,70));
        for(int r=0; r<gameData.getGrid().length; r++) {
            for(int c=0; c<gameData.getGrid().length; c++) {
                if(gameData.getGrid()[r][c] != ' ') {
                    g.setColor(Color.RED);
                    g.drawString(""+gameData.getGrid()[r][c], c*133+42, r*133+100);
                }
            }
        }

    }
    public void setText(String text) {
        this.text = text;
        repaint();
    }

    public char getPlayer() {
        return player;
    }
    public void setPlayer(char player) {
        this.player = player;
    }

    public char getTurn() {
        return turn;
    }
    public void setTurn(char turn) {
        this.turn = turn;
    }

}
