package view;

import view.SwingWorkers.ImageLoaderDefeat;
import javax.swing.*;
import java.awt.*;

public class Defeatscreennocompetitive extends JPanel {

    private Image defeatBackground;
    private GlobalFrame frame;
    Dimension windowSize;

    public Defeatscreennocompetitive(GlobalFrame frame) {

        this.frame = frame;
        windowSize = frame.getSize();

        new ImageLoaderDefeat(this).execute();


        this.setLayout(new GridLayout(1, 1));
        this.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (defeatBackground != null)
            g.drawImage(defeatBackground,0,0,null);
    }

    public void imageLoaded(Image backgroundDefaite) {
        this.defeatBackground = backgroundDefaite;
        repaint();
    }
}
