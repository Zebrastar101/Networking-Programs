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
    JLabel inventory=new JLabel("INVENTORY");
    int arrowHit=1;

    boolean cheat=false;
    public WumpusPanel(){
        setSize(500,800);
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
        inventory.setBounds(100, 600, 50, 50);
        inventory.setFont(new Font("Calibri", Font.BOLD, 23));
        inventory.setForeground(Color.red);
        add(inventory);
        inventory.setVisible(true);
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
        if(player.getDirection()==WumpusPlayer.SOUTH){
            g.drawImage(playerUp,player.getColPos()*50, player.getRowPos()*50,null);
        }
        if(player.getDirection()==WumpusPlayer.NORTH){
            g.drawImage(playerDown,player.getColPos()*50, player.getRowPos()*50,null);
        }
        if(player.getDirection()==WumpusPlayer.EAST){
            g.drawImage(playerRight,player.getColPos()*50, player.getRowPos()*50,null);
        }
        if(player.getDirection()==WumpusPlayer.WEST){
            g.drawImage(playerLeft,player.getColPos()*50, player.getRowPos()*50,null);
        }



        g.setColor(Color.black);
        g.fillRect(0,510,200,130);
        g.fillRect(210,510,300,130);
        g.setColor(Color.MAGENTA);
        g.setFont(new Font("Agency FB", Font.BOLD, 25));
        g.drawString("Inventory:",10,550);
        g.drawString("Messages:",220,550);
        if(player.isArrow()==true){
            g.drawImage(arrow,50,570,null);
        }
        if(player.isGold()==true){
            g.drawImage(gold, 80, 570, null);
        }
        g.setColor(Color.white);
        g.setFont(new Font("Agency FB", Font.BOLD, 15));
        if(map.getSquare(player.getRowPos(),player.getColPos()).isWumpus()==true){
            g.drawString("You are eaten by the Wumpus",230,570);
        }
        if(map.getSquare(player.getRowPos(),player.getColPos()).isLadder()==true&&map.getSquare(player.getRowPos(),player.getColPos()).isStench()!=true&&status==PLAYING){
            g.drawString("You bump into a ladder",230,570);
        }
        if(map.getSquare(player.getRowPos(),player.getColPos()).isBreeze()==true&&map.getSquare(player.getRowPos(),player.getColPos()).isLadder()!=true&&map.getSquare(player.getRowPos(),player.getColPos()).isStench()!=true){
            g.drawString("You feel a breeze",230,570);
        }
        if(map.getSquare(player.getRowPos(),player.getColPos()).isBreeze()==true&&map.getSquare(player.getRowPos(),player.getColPos()).isLadder()==true&&map.getSquare(player.getRowPos(),player.getColPos()).isStench()!=true){
            g.drawString("You bump into a ladder",230,570);
            g.drawString("You feel a breeze",230,590);
        }
        if(map.getSquare(player.getRowPos(),player.getColPos()).isBreeze()==true&&map.getSquare(player.getRowPos(),player.getColPos()).isLadder()==true&&map.getSquare(player.getRowPos(),player.getColPos()).isStench()==true){
            g.drawString("You bump into a ladder",230,570);
            g.drawString("You feel a breeze",230,590);
            g.drawString("You smell a stench ",230,610);
        }if(map.getSquare(player.getRowPos(),player.getColPos()).isBreeze()==true&&map.getSquare(player.getRowPos(),player.getColPos()).isLadder()!=true&&map.getSquare(player.getRowPos(),player.getColPos()).isStench()==true){

            g.drawString("You feel a breeze",230,590);
            g.drawString("You smell a stench ",230,610);
        }
        if((map.getSquare(player.getRowPos(),player.getColPos()).isStench()==true||map.getSquare(player.getRowPos(),player.getColPos()).isDeadWumpus()==true)&&map.getSquare(player.getRowPos(),player.getColPos()).isBreeze()!=true&&map.getSquare(player.getRowPos(),player.getColPos()).isLadder()!=true&&map.getSquare(player.getRowPos(),player.getColPos()).isStench()==true){
            g.drawString("You smell a stench ",230,570);
        }
        
        if(map.getSquare(player.getRowPos(),player.getColPos()).isGold()==true){
            g.drawString("You see a glimmer",230,570);
        }
        if(map.getSquare(player.getRowPos(),player.getColPos()).isPit()==true){
            g.drawString("You fell down a pit to your death",230,570);
        }
        if(arrowHit==0){
            g.drawString("You hear a scream ",230,570);
            arrowHit=1;
        }
        if(map.getSquare(player.getRowPos(),player.getColPos()).isLadder()==true&& player.isGold()==true&& status==WON){
            g.drawString("You Win. N for new game.",230,570);
        }






    }


    @Override
    public void keyTyped(KeyEvent e) {
        char event=e.getKeyChar();
        if ((event == 's') && player.getRowPos()<9 && status==PLAYING){
            player.setRowPos(player.getRowPos()+1);
            map.getSquare(player.getRowPos(),player.getColPos()).setVisited(true);
            player.setDirection(0);
            repaint();
        }
        if ((event == 'd') && player.getColPos()<9 && status==PLAYING){
            player.setColPos(player.getColPos()+1);
            map.getSquare(player.getRowPos(),player.getColPos()).setVisited(true);
            player.setDirection(WumpusPlayer.EAST);
            repaint();

        }
        if ((event == 'w') && player.getRowPos()>0 && status==PLAYING) {
            player.setRowPos(player.getRowPos()-1);
            map.getSquare(player.getRowPos(),player.getColPos()).setVisited(true);
            player.setDirection(WumpusPlayer.SOUTH);
            repaint();

        }
        if ((event == 'a') && player.getColPos()>0 && status==PLAYING) {
            player.setColPos(player.getColPos()-1);
            map.getSquare(player.getRowPos(),player.getColPos()).setVisited(true);
            player.setDirection(WumpusPlayer.WEST);
            repaint();
        }
        if ((event == 'i') && player.isArrow()==true && status==PLAYING) {
            //nio clue how to shoot arrow yet;
            player.setArrow(false);
            for(int x=player.getRowPos()+1; x<10;x++){
                if(map.getSquare(x,player.getColPos()).isWumpus()==true){
                    map.getSquare(x,player.getColPos()).setWumpus(false);
                    map.getSquare(x,player.getColPos()).setDeadWumpus(true);
                    arrowHit=0;
                }
            }
            repaint();

        }
        if ((event == 'l')&&player.isArrow()==true&&status==PLAYING) {
            //nio clue how to shoot arrow yet;
            player.setArrow(false);
            for(int x=player.getColPos()+1; x<10;x++){
                if(map.getSquare(player.getRowPos(),x).isWumpus()==true){
                    map.getSquare(player.getRowPos(),x).setWumpus(false);
                    map.getSquare(player.getRowPos(),x).setDeadWumpus(true);
                    arrowHit=0;
                    repaint();
                }
            }
            repaint();
        }
        if ((event == 'k')&&player.isArrow()==true&&status==PLAYING) {
            //nio clue how to shoot arrow yet;
            player.setArrow(false);
            for(int x=player.getRowPos()+1; x<10;x++){
                if(map.getSquare(x,player.getColPos()).isWumpus()==true){
                    map.getSquare(x,player.getColPos()).setWumpus(false);
                    map.getSquare(x,player.getColPos()).setDeadWumpus(true);
                    arrowHit=0;
                }
            }
            repaint();
        }
        if ((event == 'j')&&player.isArrow()==true&&status==PLAYING) {
            //nio clue how to shoot arrow yet;
            player.setArrow(false);
            for(int x=player.getColPos()-1; x>-1;x--){
                if(map.getSquare(player.getRowPos(),x).isWumpus()==true){
                    map.getSquare(player.getRowPos(),x).setWumpus(false);
                    map.getSquare(player.getRowPos(),x).setDeadWumpus(true);
                    arrowHit=0;
                }
            }
            repaint();
        }
        if ((event == 'c')&&player.isGold()==true&&player.getColPos()==map.getLadderColumn()&&player.getRowPos()==map.getLadderRow()&&status==PLAYING) {
            //nio clue how to shoot arrow yet;
            status=WON;
            repaint();
        }
        if(map.getSquare(player.getRowPos(),player.getColPos()).isPit()||map.getSquare(player.getRowPos(),player.getColPos()).isWumpus()&&status==PLAYING){
            status=DEAD;
            repaint();
        }
        if ((event == 'n')&&status!=PLAYING) {
            //nio clue how to shoot arrow yet;
            reset();

        }
        if ((event == 'p')&&status==PLAYING&&map.getSquare(player.getRowPos(),player.getColPos()).isGold()) {
           player.setGold(true);
            map.getSquare(player.getRowPos(),player.getColPos()).setGold(false);
            repaint();

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

