package launcher;

import database.DBConnexion;
import database.DBEnigma;
import database.DBGame;
import database.DBRoom;
import model.*;
import view.*;

import java.awt.*;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;

public class Main {

    // Variable global
    public static EnigmaList ListEnigma = new EnigmaList();
    public static RoomList ListRoom = new RoomList();
    public static GlobalFrame frame = null;

    public static int idAdmin;

    public static String ipAdmin;

    static {
        try {
            ipAdmin = Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    ;
    public static void main(String[] args) {

        if(args.length!=0){
            ipAdmin = args[0];
        }
        /* Création de la vue */
        //System.setProperty("sun.java2d.opengl","True");

        frame = GlobalFrame.getInstance();
        DBConnexion.getConnexion();

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