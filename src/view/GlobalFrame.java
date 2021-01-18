package view;

import database.DBRoom;
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
    private static volatile GlobalFrame INSTANCE;

    static {
        try {
            INSTANCE = new GlobalFrame();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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


    private GlobalFrame() throws IOException {
        /*Font*/
        UIManager.put("Label.font", FontPerso.Oxanimum);
        UIManager.put("Button.font",FontPerso.SirensDEMO);
        UIManager.put("Button.background", ColorPerso.grisOriginal);

        Toolkit.getDefaultToolkit().setDynamicLayout(true);


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
                    //GameCreation gameCreation = GameCreation.getInstance(frame,roomNumber,((GameCreation) getContentPane()).game);
                    //setContentPane(gameCreation);
                }

                else if (getContentPane() instanceof MainMenu){
                    new ImageLoaderMainMenu(mainmenu,frame.getSize()).execute();
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

    public final static GlobalFrame getInstance() throws IOException {
        //Le "Double-Checked Singleton"/"Singleton doublement vérifié" permet
        //d'éviter un appel coûteux à synchronized,
        //une fois que l'instanciation est faite.
        if (INSTANCE == null) {
            // Le mot-clé synchronized sur ce bloc empêche toute instanciation
            // multiple même par différents "threads".
            // Il est TRES important.
            synchronized(INSTANCE) {
                if (INSTANCE == null) {
                    INSTANCE = new GlobalFrame();
                }
            }
        }
        else {
            INSTANCE.frame=INSTANCE;
        }
        return INSTANCE;
    }

    public void roomManagementDisplay(GlobalFrame frame){

        roommanagement = RoomManagement.getInstance(frame);
        setContentPane(roommanagement);
        frame.setResizable(true);
        frame.revalidate();
        frame.repaint();

    }

    public void mainMenuDisplay(GlobalFrame frame){
        mainmenu = MainMenu.getInstance(frame);
        if (getContentPane() instanceof ConnectionMenu ){

            frame.setSize(1280,720);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }
        setMinimumSize(new Dimension(1280,720));
        setContentPane(mainmenu);
        frame.setResizable(true);
        frame.revalidate();
        frame.repaint();
    }

    public void connectionMenuDisplay(GlobalFrame frame){

        connectionmenu = ConnectionMenu.getInstance(frame);
        setContentPane(connectionmenu);
        frame.setMinimumSize(new Dimension(720,480));
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

        gamemanagement = GameManagement.getInstance(frame, roomNumber);
        setContentPane(gamemanagement);
        frame.setResizable(true);
        frame.revalidate();
        frame.repaint();
    }

    public void gameCreationDisplay(GlobalFrame frame, int roomNumber, Game game){

        gameCreation = GameCreation.getInstance(frame,roomNumber,game);
        setContentPane(gameCreation);
        frame.setResizable(true);
        frame.revalidate();
        frame.repaint();
    }

    public void currentGameDisplay(GlobalFrame frame,Game partie){

        currentGame = CurrentGame.getInstance(frame,partie);

        setContentPane(currentGame);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(false);
        frame.revalidate();
        frame.repaint();

    }

    public void roomAccessDisplay(GlobalFrame frame, RoomList roomList){

        roomAccess = RoomAccess.getInstance(frame,roomList);
        setContentPane(roomAccess);
        frame.setSize(1280,720);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.revalidate();
        frame.repaint();

    }

    public void playerManagementDisplay(GlobalFrame frame,int gameNb, int riddleNb, boolean boolHint1Revealed, boolean boolHint2Revealed,
                                 boolean boolHint3Revealed){
        playerManagement = PlayerManagement.getInstance(frame, gameNb, riddleNb, boolHint1Revealed, boolHint2Revealed, boolHint3Revealed);
        setContentPane(playerManagement);
        frame.setSize(1280,720);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(true);
        frame.revalidate();
        frame.repaint();

    }

    public boolean AccpetUser(String login) {
        //si je suis administrateur
        boolean admin = true;

        if (admin) {
            String[] options = {"Oui", "Non"};
            int reponse = JOptionPane.showOptionDialog
                    (null, login + " souhaites se connecter\nL'accepter ?",
                            "Nouveau Joueur",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null, // pas d'icone
                            options, // titres des boutons
                            null); // désactiver la touche ENTER
            if (reponse == JOptionPane.YES_OPTION) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

}
