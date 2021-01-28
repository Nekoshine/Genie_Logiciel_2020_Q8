package view;

import database.DBRoom;
import database.DBUser;
import launcher.Main;
import model.Game;
import model.Room;
import model.RoomList;
import model.User;
import view.style.ColorPerso;
import view.style.FontPerso;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.io.InputStream;


public class GlobalFrame extends JFrame {

    public static Dimension windowSize;
    GlobalFrame frame;
    private static volatile GlobalFrame INSTANCE = new GlobalFrame();

    RoomManagement roommanagement;
    MainMenu mainmenu;
    ConnectionMenu connectionmenu;
    SignupMenu signupmenu;
    GameManagement gamemanagement;
    GameCreation gameCreation;
    CurrentGame currentGame;
    RoomAccess roomAccess;
    PlayerManagement playerManagement;
    DefeatScreen defeatscreen;
    VictoryScreen victory;


    private boolean fullScrren = false;



    public int roomNumber;
    public boolean insideRoom;


    /**
     * Constructeur de la fenêtre pricncipale
     */

    private GlobalFrame() {
        /*Font*/
        UIManager.put("Label.font", FontPerso.lato);
        UIManager.put("Button.font",FontPerso.SirensDEMO);
        UIManager.put("TextArea.font",FontPerso.lato);
        UIManager.put("TextField.font",FontPerso.lato);
        UIManager.put("Button.background", ColorPerso.grisOriginal);
        UIManager.put("CheckBox.font", FontPerso.lato);

        Toolkit.getDefaultToolkit().setDynamicLayout(true);


        frame = this;

        InputStream fichier = Main.class.getResourceAsStream("/image/logo.png");
        try {
            Image logo = ImageIO.read(fichier);
            this.setIconImage(logo);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        this.setTitle("E-Scape Game");
        this.setVisible(true);
        windowSize = new Dimension(720,480);

        connectionMenuDisplay(this);

        this.setLocationRelativeTo(null);
        this.setMinimumSize(new Dimension(720,480));

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


        this.addComponentListener(new ComponentAdapter() {
            @Override
            /**
             * Listener appelé lors d'un redimensionnement de la fenêtre
             */
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                windowSize = getSize();

                if (getContentPane() instanceof GameCreation) {
                    gameCreation = GameCreation.getInstance(frame,((GameCreation) getContentPane()).game);
                    setContentPane(gameCreation);
                }

                else if (getContentPane() instanceof MainMenu){
                    mainmenu = MainMenu.getInstance(frame);
                    setContentPane(mainmenu);
                }

                revalidate();
                repaint();

            }
        });



    }

    /**
     * Permet de recupérer l'instance de GlobalFrame
     * @return
     */
    public static GlobalFrame getInstance() {
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

    /**
     * Affiche le panel RoomManagement dans la GlobalFrame
     * @param frame
     */

    public void roomManagementDisplay(GlobalFrame frame){
        roommanagement = RoomManagement.getInstance(frame);
        setContentPane(roommanagement);
        frame.setResizable(true);
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Affiche le panel mainMenu dans la GlobalFrame
     * @param frame fenêtre
     */

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

    /**
     * Affiche le panel ConnectionMenu dans la GlobaleFrame
     * @param frame fenêtre
     */

    public void connectionMenuDisplay(GlobalFrame frame){

        connectionmenu = ConnectionMenu.getInstance(frame);

        if(fullScrren){
            removeFullScreen(connectionmenu);
        }
        else {
            setContentPane(connectionmenu);
        }
        setVisible(true);
        frame.setMinimumSize(new Dimension(720,480));
        frame.setSize(720,480);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.revalidate();
        frame.repaint();

    }

    /**
     * Affiche le panel SignupMenu dans la GlobaleFrame
     * @param frame fenêtre
     */

    public void signupMenuDisplay(GlobalFrame frame){

        signupmenu = SignupMenu.getInstance(frame);
        setContentPane(signupmenu);
        frame.setSize(720,480);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Affiche le panel GameManagement dans la GlobalFrame
     * @param frame fenêtre
     * @param roomNumber numéro de salle
     */

    public void gameManagementDisplay(GlobalFrame frame, int roomNumber){

        gamemanagement = GameManagement.getInstance(frame, roomNumber);
        setContentPane(gamemanagement);
        frame.setResizable(true);
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Affiche le panel GameCreation dans la GlobalFrame
     * @param frame
     * @param game
     */

    public void gameCreationDisplay(GlobalFrame frame, Game game){

        gameCreation = GameCreation.getInstance(frame,game);
        setContentPane(gameCreation);
        frame.setResizable(true);
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Affiche le panel CurrentGameDisplay dans la GlobalFrame
     * @param frame
     * @param partie
     * @param idRoom
     * @param idUser
     */

    public void currentGameDisplay(GlobalFrame frame,Game partie,int idRoom,int idUser) {
        currentGame = new CurrentGame(frame,partie,idRoom,idUser);

        if(!fullScrren) {
            setFullScreen(currentGame);
        }
        else {
            setContentPane(currentGame);
        }
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Affiche le panel roomAccessDisplay dans la GlobalFrame
     * @param frame
     * @param roomList
     * @param user
     */

    public void roomAccessDisplay(GlobalFrame frame, RoomList roomList, User user){

        roomAccess = new RoomAccess(frame,roomList,user);
        if(!fullScrren) {
            setFullScreen(roomAccess);
        }
        else {
            setContentPane(roomAccess);
        }
        frame.setLocationRelativeTo(null);
        frame.revalidate();
        frame.repaint();

    }

    /**
     * Affiche la fenêtre PlayerManagement dans la Global Frame
     * @param frame
     * @param room
     * @param gameNb
     * @param riddleNb
     * @param boolHint1Revealed
     * @param boolHint2Revealed
     * @param boolHint3Revealed
     */

    public void playerManagementDisplay(GlobalFrame frame,Room room,int gameNb, int riddleNb, boolean boolHint1Revealed, boolean boolHint2Revealed,
                                 boolean boolHint3Revealed){
        playerManagement = new PlayerManagement(frame, room,gameNb, riddleNb, boolHint1Revealed, boolHint2Revealed, boolHint3Revealed);
        setContentPane(playerManagement);
        frame.setExtendedState(MAXIMIZED_BOTH);
        frame.setResizable(false);
        frame.revalidate();
        frame.repaint();

    }

    /**
     * Affiche le panel DefeatScreen dans la GlobalFrame
     * @param frame
     */

    public void defeatscreenDisplay(GlobalFrame frame) {
        defeatscreen = new DefeatScreen(frame);

        if(fullScrren){
            removeFullScreen(defeatscreen);
        }
        else {
            setContentPane(defeatscreen);
        }
        frame.setSize(800,830);
        frame.setLocationRelativeTo(null);
        frame.revalidate();
        frame.repaint();
        setVisible(true);
    }

    /**
     * Affiche le panel VictoryNoCompetitionScreen dans la GlobalFrame
     * @param frame
     * @param score
     * @param time
     */

    public void victoryNoCompetitionScreenDisplay(GlobalFrame frame,int score,int time) {

        victory = new VictoryScreen(frame,score,time);

        if(fullScrren){
            removeFullScreen(victory);
        }
        else {
            setContentPane(victory);
        }
        frame.setSize(900,630);
        frame.setLocationRelativeTo(null);
        frame.revalidate();
        frame.repaint();
        setVisible(true);
    }

    /**
     * Fenêtre pop-up permettant à l'administrateur d'accepter la connexion d'un joueur à une salle
     * @param login
     * @param salle
     * @return Retourne un booléen pour savoir si le joueur a été accepté
     */

    public boolean AcceptUser(String login, Room salle) {
        String nomJeu = salle.getGame().getTitre();
        String[] options = {"Oui", "Non"};
        String message;
        if(salle.getCompetitive()){
            message = login + " souhaite se connecter a Competitif : "+ nomJeu +"\nL'accepter ?";
        }
        else {
            message = login + " souhaite se connecter a "+ nomJeu +"\nL'accepter ?";
        }
        int reponse = JOptionPane.showOptionDialog
                (null, message,
                        "Nouveau Joueur",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null, // pas d'icone
                        options, // titres des boutons
                        null); // désactiver la touche ENTER
        if (reponse == JOptionPane.YES_OPTION) {
            DBRoom.majUserRoom(salle.getId(), DBUser.getidUser(login));
            if (getContentPane() instanceof RoomManagement) {
                roommanagement = RoomManagement.getInstance(frame);
                setContentPane(roommanagement);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Fenêtre pop-up permettant à l'administrateur de valider la fin de partie d'un joueur
     * @param login Identifiant du joueur
     * @param salle Salle dans laquelle il vient de jouer
     */

    public void endGame(String login, Room salle) {
        String nomJeu = salle.getGame().getTitre();
        String message;
        if(salle.getCompetitive()){
            message = "Le joueur "+login + " a fini Competitif : "+ nomJeu;
        }
        else {
            message = "Le joueur "+login + " a fini "+ nomJeu;
        }
        JOptionPane.showMessageDialog(frame,message,"Information", JOptionPane.WARNING_MESSAGE);
        if (getContentPane() instanceof RoomManagement) {
            roommanagement = RoomManagement.getInstance(frame);
            setContentPane(roommanagement);
        }
    }

    /**
     * Affchage de la fenêtre joueur en plein écran
     * @param pane Panel affecté
     */

    public void setFullScreen(JPanel pane){
        fullScrren=true;
        setVisible(false);
        dispose();
        setContentPane(pane);
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);setVisible(true);
    }

    /**
     * Enlève le plein écran pour le joueur
     * @param pane Panel affecté
     */

    public void removeFullScreen(JPanel pane){
        fullScrren=false;
        setVisible(false);
        dispose();
        setContentPane(pane);
        setUndecorated(false);
    }
}
