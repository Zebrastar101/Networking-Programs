import javax.imageio.IIOException;
import javax.swing.*;

public class WumpusFrame extends JFrame {
    public WumpusFrame() throws IIOException {
        setSize(520,700);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        WumpusPanel p= new WumpusPanel();
        add(p);

        setVisible(true);
    }
}
