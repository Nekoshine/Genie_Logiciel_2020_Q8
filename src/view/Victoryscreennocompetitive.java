package view;


import launcher.Main;
import model.Game;
import view.SwingWorkers.ImageLoaderVictoryNoCo;
import view.style.ColorPerso;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

public class Victoryscreennocompetitive extends JPanel implements ActionListener, WindowListener {

    private JPanelImage victoryBackground;
    private JButton quit;
    private GlobalFrame frame;
    private JPanel quitcontainer;
    Dimension windowSize;

    private Image backgroundVictoire;

    public Victoryscreennocompetitive(GlobalFrame frame,int score,int temps) {

        this.frame = frame;
        windowSize = frame.getSize();
        new ImageLoaderVictoryNoCo(this).execute();


        int seconde = (3600-temps) % 60;
        int minute = ((3600-temps) - seconde) / 60;
        String time;
        if (seconde < 10) {
            time = minute + ":0" + seconde;
        } else {
            time = minute + ":" + seconde;
        }
        String messageFin = "Temps : " + time;

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

        JPanel panelTime = new JPanel();
        JLabel labelTime = new JLabel(messageFin);
        panelTime.setLayout(new FlowLayout(1));
        labelTime.setForeground(Color.white);

        try {
            labelTime.setFont(Font.createFont(Font.TRUETYPE_FONT, Main.class.getResourceAsStream("/font/Lato.ttf")).deriveFont(Font.PLAIN,50));
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        panelTime.add(labelTime);
        panelTime.setOpaque(false);
        labelTime.setOpaque(false);


        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(Box.createRigidArea(new Dimension(0, 30)));
        this.add(panelScore);
        this.add(Box.createRigidArea(new Dimension(0,330)));
        this.add(panelTime);
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

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
