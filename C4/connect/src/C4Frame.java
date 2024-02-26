import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class C4Frame extends JFrame implements WindowListener, MouseListener {

    public C4Frame(){
        super("C4");
        setSize(600,560);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);


        setVisible(true);
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        //call drop method

    }

    @Override
    public void paint(Graphics g) {
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