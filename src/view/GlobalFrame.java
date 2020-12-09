package view;

import sun.applet.Main;

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
    RoomManagement roommanagement;
    MainMenu mainmenu;
    ConnectionMenu connectionmenu;
    SignupMenu signupmenu;
    GameManagement gamemanagement;


    public GlobalFrame() throws IOException {

        File fichier = new File(".\\src\\view\\image\\logo.png");
        Image logo = ImageIO.read(fichier);
        this.setIconImage(logo);
        this.setTitle("E-Scape Game");
        this.setVisible(true);
        windowSize = new Dimension(1280,720);


        //menu = new RoomManagement();
        //this.setContentPane(menu);
        MainMenuDisplay(this);



        this.setSize(windowSize);
        this.setLocationRelativeTo(null);
        this.setMaximumSize(new Dimension(720,480));
        this.setResizable(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


        /*this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                windowSize = getSize();
                menu = new RoomManagement();
                setContentPane(menu);
                revalidate();
                repaint();

            }
        });*/



    }

    public static void main(String[] args) throws IOException{
        GlobalFrame test = new GlobalFrame();

    }


    public void roomManagementDisplay(GlobalFrame frame){

        roommanagement = new RoomManagement(frame);
        setContentPane(roommanagement);
        frame.revalidate();
        frame.repaint();

    }

    public void MainMenuDisplay(GlobalFrame frame){

        mainmenu = new MainMenu(frame);
        setContentPane(mainmenu);
        frame.revalidate();
        frame.repaint();

    }

    public void ConnectionMenuDisplay(GlobalFrame frame){

        connectionmenu = new ConnectionMenu(frame);
        setContentPane(connectionmenu);
        frame.revalidate();
        frame.repaint();

    }

    public void SignupMenuDisplay(GlobalFrame frame){

        signupmenu = new SignupMenu(frame);
        setContentPane(signupmenu);
        frame.revalidate();
        frame.repaint();
    }

    public void GameManagementDisplay(GlobalFrame frame){

        gamemanagement = new GameManagement(frame);
        setContentPane(gamemanagement);
        frame.revalidate();
        frame.repaint();
    }
}
