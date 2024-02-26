import javax.swing.*;
import java.awt.*;

public class C4Frame extends JFrame implements WindowListener, MouseListener{
    
    public C4Frame(){
        super("C4");
        setSize(600,660);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(Color.BLUE);


        setVisible(true);
    }



}