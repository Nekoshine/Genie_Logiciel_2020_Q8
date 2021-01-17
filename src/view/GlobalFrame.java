package view;

import launcher.Main;
import model.Game;
import model.RoomList;
import view.SwingWorkers.ImageLoaderMainMenu;
import view.style.ColorPerso;
import view.style.FontPerso;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
    CurrentGame currentGame;
    RoomAccess roomAccess;
    PlayerManagement playerManagement;



    public int roomNumber;
    public boolean insideRoom;


    public GlobalFrame() throws IOException {
        /*Font*/
        UIManager.put("Label.font", FontPerso.Oxanimum);
        UIManager.put("Button.font",FontPerso.SirensDEMO);
        UIManager.put("Button.background", ColorPerso.grisOriginal);


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
        this.setMinimumSize(new Dimension(720,480));

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                windowSize = getSize();
                //frame.setSize(frame.getWidth(),(16/9)*frame.getWidth());

                if (getContentPane() instanceof GameCreation) {
                    GameCreation gameCreation = new GameCreation(frame,roomNumber,((GameCreation) getContentPane()).game);
                    setContentPane(gameCreation);
                }

                if (getContentPane() instanceof MainMenu){
                    remove(getContentPane());
                    MainMenu mainMenu = new MainMenu(frame);
                    setContentPane(mainMenu);
                }


                /*if (getContentPane() instanceof CurrentGame) {
                    CurrentGame currentGame = new CurrentGame(frame);
                    setContentPane(currentGame);
                }*/

                revalidate();
                repaint();

            }
        });



    }

    public void roomManagementDisplay(GlobalFrame frame){

        roommanagement = RoomManagement.getInstance(frame);
        setContentPane(roommanagement);
        frame.setResizable(true);
        frame.revalidate();
        frame.repaint();

    }

    public void mainMenuDisplay(GlobalFrame frame){

        if (getContentPane() instanceof ConnectionMenu ){
            mainmenu = new MainMenu(frame);
            frame.setSize(1280,720);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
        else {
            mainmenu = new MainMenu(frame);
        }
        setContentPane(mainmenu);
        frame.setResizable(true);
        frame.revalidate();
        frame.repaint();
    }

    public void connectionMenuDisplay(GlobalFrame frame){

        connectionmenu = ConnectionMenu.getInstance(frame);
        setContentPane(connectionmenu);
        frame.setSize(720,480);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.revalidate();
        frame.repaint();

    }

    public void signupMenuDisplay(GlobalFrame frame){

        signupmenu = SignupMenu.getInstance(frame);
        setContentPane(signupmenu);
        frame.setSize(720,480);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.revalidate();
        frame.repaint();
    }

    public void gameManagementDisplay(GlobalFrame frame, int roomNumber){

        gamemanagement = new GameManagement(frame, roomNumber);
        setContentPane(gamemanagement);
        frame.setResizable(true);
        frame.revalidate();
        frame.repaint();
    }

    public void gameCreationDisplay(GlobalFrame frame, int roomNumber, Game game){

        gameCreation = new GameCreation(frame,roomNumber,game);
        setContentPane(gameCreation);
        frame.setResizable(true);
        frame.revalidate();
        frame.repaint();
    }

    public void currentGameDisplay(GlobalFrame frame,Game partie){

        currentGame = new CurrentGame(frame,partie);
        setContentPane(currentGame);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(true);
        this.setLocationRelativeTo(null);
        frame.revalidate();
        frame.repaint();

    }

    public void roomAccessDisplay(GlobalFrame frame, RoomList roomList){

        roomAccess = new RoomAccess(frame,roomList);
        setContentPane(roomAccess);
        frame.setSize(1280,720);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.revalidate();
        frame.repaint();

    }

    public void playerManagement(GlobalFrame frame,int gameNb, int riddleNb, boolean boolHint1Revealed, boolean boolHint2Revealed,
                                 boolean boolHint3Revealed){
        playerManagement = new PlayerManagement(frame, gameNb, riddleNb, boolHint1Revealed, boolHint2Revealed, boolHint3Revealed);
        setContentPane(playerManagement);
        frame.setSize(1280,720);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(true);
        frame.revalidate();
        frame.repaint();

    }


}
