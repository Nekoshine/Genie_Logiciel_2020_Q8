package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;

public class GlobalFrame extends JFrame {

    public static Dimension windowSize;
    private BorderLayout mainLayout;



    public GlobalFrame() throws IOException {

        File fichier = new File(".\\src\\view\\image\\logo.png");
        Image logo = ImageIO.read(fichier);
        this.setIconImage(logo);
        this.setTitle("E-Scape Game");
        this.setVisible(true);


        RoomManagement menu = new RoomManagement();
        this.add(menu,mainLayout.CENTER);

        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        windowSize = new Dimension(854,480);
        this.setSize(windowSize);
        this.setResizable(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        /*this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                windowSize = getSize();
            }
        });*/




    }

    public static void main(String[] args) throws InterruptedException, IOException {
        GlobalFrame test = new GlobalFrame();

    }

}
