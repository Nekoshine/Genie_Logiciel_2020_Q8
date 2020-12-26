package view;

import launcher.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;

public class GlobalFrame extends JFrame {

    public static Dimension windowSize;
    private BorderLayout mainLayout;
    GlobalFrame frame;
    RoomManagement roommanagement;
    MainMenu mainmenu;
    ConnectionMenu connectionmenu;
    SignupMenu signupmenu;
    GameManagement gamemanagement;
    GameCreation gameCreation;


    public GlobalFrame() throws IOException {
        /*Font*/
        UIManager.put("Label.font",FontPerso.Oxanimum);
        UIManager.put("Button.font",FontPerso.Arial);
        UIManager.put("Button.background",ColorPerso.grisOriginal);

        frame = this;

        File fichier = new File("./src/view/image/logo.png");
        Image logo = ImageIO.read(fichier);
        this.setIconImage(logo);
        this.setTitle("E-Scape Game");
        this.setVisible(true);
        windowSize = new Dimension(720,480);


        //menu = new RoomManagement();
        //this.setContentPane(menu);
        connectionMenuDisplay(this);



        //this.setSize(windowSize);
        this.setLocationRelativeTo(null);
        //this.setMinimumSize(new Dimension(720,480));
        //this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                windowSize = getSize();
                if (getContentPane() instanceof GameCreation) {
                    GameCreation gameCreation = new GameCreation(frame);
                    setContentPane(gameCreation);
                }
                revalidate();
                repaint();

            }
        });



    }

    public void roomManagementDisplay(GlobalFrame frame){

        roommanagement = new RoomManagement(frame, Main.ListRoom);
        setContentPane(roommanagement);
        frame.setSize(1280,720);
        frame.setResizable(true);
        this.setLocationRelativeTo(null);
        frame.revalidate();
        frame.repaint();

    }

    public void mainMenuDisplay(GlobalFrame frame){

        mainmenu = new MainMenu(frame);
        setContentPane(mainmenu);
        frame.setSize(1280,720);
        frame.setResizable(true);
        this.setLocationRelativeTo(null);
        frame.revalidate();
        frame.repaint();

    }

    public void connectionMenuDisplay(GlobalFrame frame){

        connectionmenu = new ConnectionMenu(frame);
        setContentPane(connectionmenu);
        frame.setSize(720,480);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.revalidate();
        frame.repaint();

    }

    public void signupMenuDisplay(GlobalFrame frame){

        signupmenu = new SignupMenu(frame);
        setContentPane(signupmenu);
        frame.setSize(720,480);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.revalidate();
        frame.repaint();
    }

    public void gameManagementDisplay(GlobalFrame frame, int roomNumber){

        gamemanagement = new GameManagement(frame, roomNumber,Main.ListGame);
        setContentPane(gamemanagement);
        frame.setSize(1280,720);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.revalidate();
        frame.repaint();
    }

    public void gameCreationDisplay(GlobalFrame frame){

        gameCreation = new GameCreation(frame);
        setContentPane(gameCreation);
        frame.setSize(1280,720);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.revalidate();
        frame.repaint();
    }
}
