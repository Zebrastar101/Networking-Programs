
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
                    g.drawString(""+gameData.getGrid()[r][c], c*133+42, r*133+150);
                }
            }
        }

    }
    public void setText(String text) {
        this.text = text;
        repaint();
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

    public void makeMove(int c, int r, char letter)
    {
        gameData.getGrid()[r][c] = letter;
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent event) {
        char key = event.getKeyChar();
        int r;
        int c;

        // sets the row and column, based on the entered key
        switch(key)
        {
            case '1':
                r=0;
                c=0;
                break;
            case '2':
                r=0;
                c=1;
                break;
            case '3':
                r=0;
                c=2;
                break;
            case '4':
                r=1;
                c=0;
                break;
            case '5':
                r=1;
                c=1;
                break;
            case '6':
                r=1;
                c=2;
                break;
            case '7':
                r=2;
                c=0;
                break;
            case '8':
                r=2;
                c=1;
                break;
            case '9':
                r=2;
                c=2;
                break;
            default:
                r=c=-1;
        }
        // if a valid enter was entered, send the move to the server
        if(c!=-1) {
            try {
                os.writeObject(new CommandFromClient(CommandFromClient.MOVE, "" + c + r + player));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}


}
