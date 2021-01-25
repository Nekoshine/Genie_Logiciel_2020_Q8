package view;


import launcher.Main;
import model.Game;
import view.SwingWorkers.ImageLoaderVictoryNoCo;
import view.style.ColorPerso;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Victoryscreennocompetitive extends JPanel implements ActionListener {

    private JPanelImage victoryBackground;
    private JButton quit;
    private GlobalFrame frame;
    private JPanel quitcontainer;
    Dimension windowSize;

    private Image backgroundVictoire;

    public Victoryscreennocompetitive(GlobalFrame frame,int score) {

        this.frame = frame;
        windowSize = frame.getSize();
        new ImageLoaderVictoryNoCo(this).execute();

        JPanel panelScore = new JPanel();
        JLabel labelScore = new JLabel("Votre score : " + score );
        panelScore.setLayout(new FlowLayout(1));
        labelScore.setForeground(Color.white);

        try {
            labelScore.setFont(Font.createFont(Font.TRUETYPE_FONT, Main.class.getResourceAsStream("/font/Lato.ttf")).deriveFont(Font.PLAIN,50));
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        panelScore.add(labelScore);
        panelScore.setOpaque(false);
        labelScore.setOpaque(false);

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(Box.createRigidArea(new Dimension(0, 480)));
        this.add(panelScore);
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
