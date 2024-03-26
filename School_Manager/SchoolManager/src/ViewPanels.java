import javax.swing.*;
import java.awt.*;

public class ViewPanels extends JPanel {
    public ViewPanels(int w, int h)
    {
        setSize(w,h);
    }

    public void paint(Graphics g)
    {
        g.fillRect(0,0,getWidth(),getHeight());
    }
}
