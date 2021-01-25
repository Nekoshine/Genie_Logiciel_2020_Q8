package view;


import launcher.Main;
import model.Game;
import view.SwingWorkers.ImageLoaderDefeat;
import view.SwingWorkers.ImageLoaderMainMenu;
import view.style.ColorPerso;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class Defeatscreennocompetitive extends JPanel implements ActionListener {

    private Image defeatBackground;
    private JButton quit;
    private GlobalFrame frame;
    private JPanel quitcontainer;
    Dimension windowSize;

    public Defeatscreennocompetitive(GlobalFrame frame) {

        this.frame = frame;
        windowSize = frame.getSize();

        new ImageLoaderDefeat(this).execute();


        this.setLayout(new GridLayout(1, 1));
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == quit) {
            frame.connectionMenuDisplay(frame);
        }
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
