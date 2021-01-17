package launcher;

import database.DBEnigma;
import database.DBRoom;
import model.*;
import view.GameManagement;
import view.GlobalFrame;
import view.MainMenu;
import view.RoomManagement;

import java.io.IOException;

public class Main {

    // Variable global
    public static EnigmaList ListEnigma = new EnigmaList();
    public static RoomList ListRoom = new RoomList();
    public static int idAdmin;
    public static GlobalFrame frame = null;


    public static int idUser=5;

    public static void main(String[] args) throws IOException {

        /* Création de la vue */
        //System.setProperty("sun.java2d.opengl","True");
        frame = GlobalFrame.getInstance();

        /*Thread thread = new Thread(){
            public void run(){
                System.out.println("run");
                MainMenu.getInstance(frame);
                GameManagement.getInstance(frame,-1);
                RoomManagement.getInstance(frame);
                System.out.println("end");
            }
        };

        thread.start();*/


    }
}