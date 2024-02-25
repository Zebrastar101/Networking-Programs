import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;

public class C4Frame extends JFrame{
    //create frame
    public C4Frame(){
        setTitle("Connect 4 Game");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        //create 42 cicular buttons
        JButton b1= new JButton("");
        b1.setBounds(0, 0, 100, 100);
        add(b1);
        setVisible(true);
    }
}
