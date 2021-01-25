package view;

import launcher.Main;
import model.Game;
import view.style.ColorPerso;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Victoryscreencompetitive extends JPanel implements ActionListener {

    private JPanelImage defeatBackground;
    private JButton quit;
    private GlobalFrame frame;
    private JPanel quitcontainer;
    Dimension windowSize;

    public Victoryscreencompetitive(GlobalFrame frame,int timer,int erreur) {

        this.frame = frame;
        windowSize = frame.getSize();

        this.setLayout(new GridLayout(1, 1));

        defeatBackground = new JPanelImage(Main.class.getResourceAsStream("/image/defaite.jpg"),windowSize);
        defeatBackground.setLayout(new BorderLayout(10, 10));

        quit = new JButton("Quit");
        quit.setBackground(ColorPerso.rouge);

        quitcontainer = new JPanel();
        quitcontainer.setLayout(new FlowLayout(FlowLayout.LEFT));
        quitcontainer.add(quit);

        defeatBackground.add(quitcontainer, BorderLayout.PAGE_END);
        this.add(defeatBackground);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == quit) {
            frame.connectionMenuDisplay(frame);
        }
    }
}

