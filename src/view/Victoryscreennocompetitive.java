package view;

import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;
import com.sun.xml.internal.ws.api.addressing.OneWayFeature;
import launcher.Main;
import model.Game;
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

    public Victoryscreennocompetitive(GlobalFrame frame) {

        this.frame = frame;
        windowSize = frame.getSize();

        this.setLayout(new GridLayout(1, 1));

        victoryBackground = new JPanelImage(Main.class.getResourceAsStream("/image/victoire.jpg"),windowSize);
        victoryBackground.setLayout(new BorderLayout(10, 10));

        quit = new JButton("Quit");
        quit.setBackground(ColorPerso.rouge);

        quitcontainer = new JPanel();
        quitcontainer.setLayout(new FlowLayout(FlowLayout.LEFT));
        quitcontainer.add(quit);

        victoryBackground.add(quitcontainer, BorderLayout.PAGE_END);
        this.add(victoryBackground);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == quit) {
            frame.connectionMenuDisplay(frame);
        }
    }
}
