import java.awt.*;
import java.awt.image.BufferedImage;

public class WumpusPanel {
    public final int PLAYING=0;
    public final int DEAD=1;
    public final int WON=2;
    private int status;
    private WumpusPlayer player;
    private WumpusMap map;
    private BufferedImage buffer;
    private BufferedImage floor;
    private BufferedImage arrow;
    private BufferedImage fog;
    private BufferedImage gold;
    private BufferedImage ladder;
    private BufferedImage pit;
    private BufferedImage breeze;
    private BufferedImage wumpus;
    private BufferedImage deadWumpus;
    private BufferedImage stench;
    private BufferedImage playerUp;
    private BufferedImage playerDown;
    private BufferedImage playerLeft;
    private BufferedImage playerRight;


    public void reset(){
        status=PLAYING;
        map= new WumpusMap();
        //still need player at ladder
    }
    public void addNotify(){

    }
    public void paint(Graphics g){}

    




}

