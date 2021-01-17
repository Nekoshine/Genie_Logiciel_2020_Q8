package launcher;

import database.DBEnigma;
import database.DBRoom;
import model.*;
import view.GlobalFrame;

import java.io.IOException;

public class Main {

    // Variable global
    public static EnigmaList ListEnigma = new EnigmaList();
    public static RoomList ListRoom = new RoomList();
    public static int idAdmin;

    // à supprimer
    public static int idUser=5;

    public static void main(String[] args) throws IOException {

        /* Création de la vue */
        //System.setProperty("sun.java2d.opengl","True");
        new GlobalFrame();

    }
}