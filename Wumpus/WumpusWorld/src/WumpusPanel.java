import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WumpusPanel extends JPanel implements KeyListener{
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
    public WumpusPanel() throws IOException {
        setSize(800,800);

        //picked a random one not sure if its right
        buffer=new BufferedImage(800,800,BufferedImage.TYPE_4BYTE_ABGR);

        //load images
        floor= ImageIO.read(new File("Wumpus World Images\\Floor.gif"));
        arrow=ImageIO.read(new File("Wumpus World Images\\arrow.gif"));
        fog=ImageIO.read(new File("Wumpus World Images\\fog.gif"));
        gold=ImageIO.read(new File("Wumpus World Images\\gold.gif"));
        ladder=ImageIO.read(new File("Wumpus World Images\\ladder.gif"));
        pit=ImageIO.read(new File("Wumpus World Images\\pit.gif"));
        breeze=ImageIO.read(new File("Wumpus World Images\\breeze.gif"));
        wumpus=ImageIO.read(new File("Wumpus World Images\\wumpus.gif"));
        deadWumpus=ImageIO.read(new File("Wumpus World Images\\deadWumpus.gif"));
        stench=ImageIO.read(new File("Wumpus World Images\\stench.gif"));
        playerUp=ImageIO.read(new File("Wumpus World Images\\playerUp.gif"));
        playerDown=ImageIO.read(new File("Wumpus World Images\\playerDown.gif"));
        playerLeft=ImageIO.read(new File("Wumpus World Images\\playerLeft.gif"));
        playerRight=ImageIO.read(new File("Wumpus World Images\\playerRight.gif"));
        


    }


    public void reset(){
        status=PLAYING;
        map= new WumpusMap();
        //still need player at ladder
        player=new WumpusPlayer();
        player.setRowPos(map.getLadderRow());
        player.setColPos(map.getLadderColumn());
    }
    public void addNotify(){

    }
    public void paint(Graphics g){

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

