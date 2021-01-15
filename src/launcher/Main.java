package launcher;

import model.*;
import view.GlobalFrame;

import java.io.IOException;

public class Main {

    // Variable global
    public static EnigmaList ListEnigma = new EnigmaList();
    public static RoomList ListRoom = new RoomList();

    // à supprimer
    public static int idUser=5;

    public static void main(String[] args) throws IOException {

        /* Création de la vue */
        new GlobalFrame();

    }

}