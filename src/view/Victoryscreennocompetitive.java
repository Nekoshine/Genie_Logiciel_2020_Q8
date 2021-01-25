package view;


import launcher.Main;
import model.Game;
import view.SwingWorkers.ImageLoaderVictoryNoCo;
import view.style.ColorPerso;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Victoryscreennocompetitive extends JPanel implements ActionListener {

    private JPanelImage victoryBackground;
    private JButton quit;
    private GlobalFrame frame;
    private JPanel quitcontainer;
    Dimension windowSize;

    private Image backgroundVictoire;

    public Victoryscreennocompetitive(GlobalFrame frame) {

        this.frame = frame;
        windowSize = frame.getSize();
        new ImageLoaderVictoryNoCo(this).execute();

        this.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundVictoire != null)
            g.drawImage(backgroundVictoire,0,0,null);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == quit) {
            frame.connectionMenuDisplay(frame);
        }
    }

    public void imageLoaded(Image backgroundVictoire) {
        this.backgroundVictoire = backgroundVictoire;
        repaint();
    }
}
