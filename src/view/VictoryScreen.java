package view;


import view.swingworkers.ImageLoaderVictoryNoCo;
import view.style.FontPerso;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class VictoryScreen extends JPanel implements WindowListener {

    private GlobalFrame frame;
    Dimension windowSize;

    private Image backgroundVictoire;

    public VictoryScreen(GlobalFrame frame, int score, int temps) {

        this.frame = frame;
        windowSize = frame.getSize();
        new ImageLoaderVictoryNoCo(this).execute();


        int seconde = (1200-temps) % 60;
        int minute = ((1200-temps) - seconde) / 60;
        String time;
        if (seconde < 10) {
            time = minute + ":0" + seconde;
        } else {
            time = minute + ":" + seconde;
        }
        String messageFin = "Temps : " + time;

        JPanel panelScore = new JPanel();
        JLabel labelScore = new JLabel("Votre score : " + score );
        panelScore.setLayout(new FlowLayout(FlowLayout.CENTER));
        labelScore.setForeground(Color.white);

        labelScore.setFont(FontPerso.lato.deriveFont(Font.PLAIN,50));
        panelScore.add(labelScore);
        panelScore.setOpaque(false);
        labelScore.setOpaque(false);

        JPanel panelTime = new JPanel();
        JLabel labelTime = new JLabel(messageFin);
        panelTime.setLayout(new FlowLayout(FlowLayout.CENTER));
        labelTime.setForeground(Color.white);

        labelTime.setFont(FontPerso.lato.deriveFont(Font.PLAIN,50));
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

    public void imageLoaded(Image backgroundVictoire) {
        this.backgroundVictoire = backgroundVictoire;
        repaint();
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        frame.connectionMenuDisplay(frame);
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
