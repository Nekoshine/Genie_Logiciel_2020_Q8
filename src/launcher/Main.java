package launcher;

import database.DBEnigma;
import database.DBGame;
import database.DBRoom;
import model.*;
import view.*;

import java.awt.*;
import java.io.IOException;

public class Main {

    // Variable global
    public static EnigmaList ListEnigma = new EnigmaList();
    public static RoomList ListRoom = new RoomList();
    public static GlobalFrame frame = null;


    public static int idAdmin;

    public static void main(String[] args) {

        /* Création de la vue */
        //System.setProperty("sun.java2d.opengl","True");

        frame = GlobalFrame.getInstance();

        GameList gameList = DBGame.getGames(2);
        Game partie = gameList.getGame(0);
        try {
            frame.currentGameDisplay(frame,partie,1);
        } catch (IOException e) {
            e.printStackTrace();
        }

       /* Thread thread = new Thread(){
            public void run(){
                MainMenu.getInstance(frame);
                GameManagement.getInstance(frame,-1);
                RoomManagement.getInstance(frame);
                GameCreation.getInstance(frame,0,null);
                RoomAccess.getInstance(frame,ListRoom,new User(1,"","",false));
            }
        };

        thread.start();*/


    }
}