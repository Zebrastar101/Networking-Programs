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
    int start=0;
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
    boolean cheat=false;
    public WumpusPanel(){
        setSize(800,800);
        addKeyListener(this);
        //picked a random one not sure if its right
        buffer=new BufferedImage(800,800,BufferedImage.TYPE_4BYTE_ABGR);

        //load images
        try{
        floor= ImageIO.read(new File("Wumpus World Images\\Floor.gif"));
        arrow=ImageIO.read(new File("Wumpus World Images\\arrow.gif"));
        fog=ImageIO.read(new File("Wumpus World Images\\black.gif"));
        gold=ImageIO.read(new File("Wumpus World Images\\gold.gif"));
        ladder=ImageIO.read(new File("Wumpus World Images\\ladder.gif"));
        pit=ImageIO.read(new File("Wumpus World Images\\pit.gif"));
        breeze=ImageIO.read(new File("Wumpus World Images\\breeze.gif"));
        wumpus=ImageIO.read(new File("Wumpus World Images\\wumpus.gif"));
        deadWumpus=ImageIO.read(new File("Wumpus World Images\\deadWumpus.gif"));
        stench=ImageIO.read(new File("Wumpus World Images\\stench.gif"));
        playerUp=ImageIO.read(new File("Wumpus World Images\\playerUp.png"));
        playerDown=ImageIO.read(new File("Wumpus World Images\\playerDown.png"));
        playerLeft=ImageIO.read(new File("Wumpus World Images\\playerLeft.png"));
        playerRight=ImageIO.read(new File("Wumpus World Images\\playerRight.png"));}
        catch (IOException e) {
            System.out.print("failed to load");
            e.printStackTrace();
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
        start=0;
        System.out.print(start);
        repaint();
    }
    public void addNotify(){
        super.addNotify();
        requestFocus();
    }
    public void paint(Graphics g){
        System.out.print("paint");
        if(start==0){
            player.setColPos(map.getLadderColumn());
            player.setRowPos(map.getLadderRow());
            start=1;
        }

        map.getSquare(player.getRowPos(),player.getColPos()).setVisited(true);
        for( int x=0; x<10; x++ ){
            for(int z=0; z<10; z++){
                WumpusSquare track=map.getSquare(x,z);

                if(track.isVisited()==true ||cheat==true){
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
                else if(track.isVisited()!=true&&cheat==false){
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
        if ((event == 'S' || event == 's') && player.getRowPos()<=9 && status==PLAYING){
            player.setRowPos(player.getRowPos()+1);
            map.getSquare(player.getRowPos(),player.getColPos()).setVisited(true);
            player.setDirection(0);
            repaint();
        }
        if ((event == 'D' || event == 'd') && player.getColPos()<=9 && status==PLAYING){
            player.setColPos(player.getColPos()+1);
            map.getSquare(player.getRowPos(),player.getColPos()).setVisited(true);
            player.setDirection(WumpusPlayer.EAST);
            repaint();

        }
        if ((event == 'W' || event == 'w') && player.getRowPos()>0 && status==PLAYING) {
            player.setRowPos(player.getRowPos()-1);
            map.getSquare(player.getRowPos(),player.getColPos()).setVisited(true);
            player.setDirection(WumpusPlayer.SOUTH);
            repaint();

        }
        if ((event == 'a' || event == 'A') && player.getColPos()>0 && status==PLAYING) {
            player.setColPos(player.getColPos()-1);
            map.getSquare(player.getRowPos(),player.getColPos()).setVisited(true);
            player.setDirection(WumpusPlayer.WEST);
            repaint();
        }
        if ((event == 'i' || event == 'I') && player.isArrow()==true && status==PLAYING) {
            //nio clue how to shoot arrow yet;
            player.setArrow(false);
            for(int x=player.getRowPos()+1; x<10;x++){
                if(map.getSquare(x,player.getColPos()).isWumpus()==true){
                    map.getSquare(x,player.getColPos()).setWumpus(false);
                    map.getSquare(x,player.getColPos()).setDeadWumpus(true);

                }
            }
            repaint();

        }
        if ((event == 'l' || event == 'L')&&player.isArrow()==true&&status==PLAYING) {
            //nio clue how to shoot arrow yet;
            player.setArrow(false);
            for(int x=player.getColPos()+1; x<10;x++){
                if(map.getSquare(player.getRowPos(),x).isWumpus()==true){
                    map.getSquare(player.getRowPos(),x).setWumpus(false);
                    map.getSquare(player.getRowPos(),x).setDeadWumpus(true);
                    repaint();
                }
            }
            repaint();
        }
        if ((event == 'k' || event == 'K')&&player.isArrow()==true&&status==PLAYING) {
            //nio clue how to shoot arrow yet;
            player.setArrow(false);
            for(int x=player.getRowPos()+1; x<10;x++){
                if(map.getSquare(x,player.getColPos()).isWumpus()==true){
                    map.getSquare(x,player.getColPos()).setWumpus(false);
                    map.getSquare(x,player.getColPos()).setDeadWumpus(true);

                }
            }
            repaint();
        }
        if ((event == 'j' || event == 'J')&&player.isArrow()==true&&status==PLAYING) {
            //nio clue how to shoot arrow yet;
            player.setArrow(false);
            for(int x=player.getColPos()-1; x>-1;x--){
                if(map.getSquare(player.getRowPos(),x).isWumpus()==true){
                    map.getSquare(player.getRowPos(),x).setWumpus(false);
                    map.getSquare(player.getRowPos(),x).setDeadWumpus(true);

                }
            }
            repaint();
        }
        if ((event == 'c' || event == 'C')&&player.isGold()==true&&player.getColPos()==map.getLadderColumn()&&player.getRowPos()==map.getLadderRow()&&status==PLAYING) {
            //nio clue how to shoot arrow yet;
            status=WON;
            repaint();
        }
        if(map.getSquare(player.getRowPos(),player.getColPos()).isPit()||map.getSquare(player.getRowPos(),player.getColPos()).isWumpus()&&status==PLAYING){
            status=DEAD;
            repaint();
        }
        if ((event == 'n' || event == 'N')&&status!=PLAYING) {
            //nio clue how to shoot arrow yet;
            reset();

        }
        if (event=='*'&&status==PLAYING) {
            if(cheat==true){
                cheat=false;
            }else{
                cheat=true;
            }
            repaint();
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

