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
    public WumpusPanel(){
        setSize(800,800);

        //picked a random one not sure if its right
        buffer=new BufferedImage(800,800,BufferedImage.TYPE_4BYTE_ABGR);

        //load images
        try{
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
        playerRight=ImageIO.read(new File("Wumpus World Images\\playerRight.gif"));}
        catch (IOException e) {
            System.out.print("failed to load");
        }
        //reset call
        reset();
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
        super.addNotify();
        requestFocus();
    }
    public void paint(Graphics g){
        for( int x=0; x<10; x++ ){
            for(int z=0; z<10; z++){
                WumpusSquare track=map.getSquare(x,z);

                if(track.isVisited()==true &&track!=null){
                    g.drawImage(floor,z*50, x*50,null);
                    track=map.getSquare(x,z);
                    if(track.isBreeze()==true){
                        g.drawImage(breeze,z*50,x*50,null);
                    }
                    if(track.isDeadWumpus()==true){
                        g.drawImage(deadWumpus,z*50,x*50,null);
                    }
                    if(track.isGold()==true){
                        g.drawImage(gold,z*50,x*50,null);
                    }
                    if(track.isLadder()==true){
                        g.drawImage(ladder,z*50,x*50,null);
                    }
                    if(track.isStench()==true){
                        g.drawImage(stench,z*50,x*50,null);
                    }
                    if(track.isWumpus()==true){
                        g.drawImage(wumpus,z*50,x*50,null);
                    }
                    if(track.isPit()==true){
                        g.drawImage(pit,z*50,x*50,null);
                    }
                }
                else if(track.isVisited()!=true){
                    g.drawImage(fog,z*50,x*50,null);
                }
            }
        }
        if(player.getDirection()==WumpusPlayer.NORTH){
            g.drawImage(playerUp,player.getColPos()*50, player.getRowPos()*50,null);
        }
        if(player.getDirection()==WumpusPlayer.SOUTH){
            g.drawImage(playerDown,player.getColPos()*50, player.getRowPos()*50,null);
        }
        if(player.getDirection()==WumpusPlayer.EAST){
            g.drawImage(playerRight,player.getColPos()*50, player.getRowPos()*50,null);
        }
        if(player.getDirection()==WumpusPlayer.WEST){
            g.drawImage(playerLeft,player.getColPos()*50, player.getRowPos()*50,null);
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {
        char event=e.getKeyChar();
        if (event == 'W' || event == 'w') {
            player.setRowPos(player.getRowPos()+1);
            map.getSquare(player.getRowPos(),player.getColPos()).setVisited(true);
            repaint();
        }
        if (event == 'D' || event == 'd') {
            player.setColPos(player.getColPos()+1);
            map.getSquare(player.getRowPos(),player.getColPos()).setVisited(true);
            repaint();

        }
        if ((event == 'S' || event == 's')&&player.getRowPos()>0) {
            player.setRowPos(player.getRowPos()-1);
            map.getSquare(player.getRowPos(),player.getColPos()).setVisited(true);
            repaint();

        }
        if ((event == 'a' || event == 'A')&&player.getColPos()>0) {
            player.setColPos(player.getColPos()-1);
            map.getSquare(player.getRowPos(),player.getColPos()).setVisited(true);
            repaint();
        }
        if ((event == 'i' || event == 'I')&&player.isArrow()==true) {
            //nio clue how to shoot arrow yet;
            player.setArrow(false);
        }
        if ((event == 'l' || event == 'L')&&player.isArrow()==true) {
            //nio clue how to shoot arrow yet;
            player.setArrow(false);
        }
        if ((event == 'k' || event == 'K')&&player.isArrow()==true) {
            //nio clue how to shoot arrow yet;
            player.setArrow(false);
        }
        if ((event == 'j' || event == 'J')&&player.isArrow()==true) {
            //nio clue how to shoot arrow yet;
            player.setArrow(false);
        }
        if()

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

