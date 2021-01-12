package launcher;

import database.DBEnigma;
import database.DBRoom;
import model.*;
import view.GlobalFrame;


import javax.swing.*;
import java.io.IOException;

public class Main {
    public static EnigmaList ListEnigma = new EnigmaList();
    public static int idUser=0;
    public static RoomList ListRoom = new RoomList();
    public static int idAdmin;

    public static void main(String[] args) throws IOException {

        /* Création de la vue */
        new GlobalFrame();

    }
}