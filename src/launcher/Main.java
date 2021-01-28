package launcher;

import database.DBConnexion;
import model.*;
import view.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    // Variable global
    public static EnigmaList ListEnigma = new EnigmaList();
    public static RoomList ListRoom = new RoomList();
    public static GameList ListGame = new GameList();
    public static GlobalFrame frame = null;

    public static HashMap<Integer, ArrayList<String>> answers = new HashMap<>();

    public static int idAdmin=0;

    public static String ipAdmin="127.0.0.1";

    public static void main(String[] args) {

        if(args.length!=0){
            ipAdmin = args[0];
        }

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