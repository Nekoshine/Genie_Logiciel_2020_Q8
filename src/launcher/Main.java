package launcher;

import database.DBEnigma;
import database.DBRoom;
import model.*;
import view.GlobalFrame;

import java.io.IOException;

public class Main {
    public static EnigmaList ListEnigma = new EnigmaList();
    public static int idUser=5;
    public static RoomList ListRoom = new RoomList();

    public static void main(String[] args) throws IOException {
        System.out.println(DBRoom.getMax());
        /* Création de la vue */
        new GlobalFrame();

    }
}