
import java.awt.*;
import java.awt.event.KeyListener;
import javax.swing.*;
public class TTTFrame extends JFrame implements KeyListener {
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
        this.gameData=gameData;
        this.player=player;
        //add a key listener
        addKeyListener(this);
        //make closing the fram close the program
        



    }

}
