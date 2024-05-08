import javax.imageio.IIOException;
import javax.swing.*;

public class WumpusFrame extends JFrame {
    public WumpusFrame() throws IIOException {
        setSize(800,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocale(null);
        WumpusPanel p= new WumpusPanel();
        add(p);
        setVisible(true);
    }
}
