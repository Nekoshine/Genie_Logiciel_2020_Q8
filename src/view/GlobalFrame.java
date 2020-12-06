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
    RoomManagement menu;



    public GlobalFrame() throws IOException {

        File fichier = new File(".\\src\\view\\image\\logo.png");
        Image logo = ImageIO.read(fichier);
        this.setIconImage(logo);
        this.setTitle("E-Scape Game");
        this.setVisible(true);
        windowSize = new Dimension(1280,720);





        menu = new RoomManagement();
        this.setContentPane(menu);


        this.setSize(windowSize);
        this.setMinimumSize(new Dimension(720,480));
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                windowSize = getSize();
                menu = new RoomManagement();
                setContentPane(menu);
                revalidate();
                repaint();

            }
        });




    }

    public static void main(String[] args) throws IOException{
        GlobalFrame test = new GlobalFrame();

    }

}
