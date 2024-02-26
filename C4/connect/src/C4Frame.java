import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.*;

public class C4Frame extends JFrame {


    public C4Frame(){
        super("C4");
        setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(Color.yellow);
        //create cicular buttons
        JButton b00 = new JButton();

        b00.setLocation(50,50);



        setVisible(true);
    }



}