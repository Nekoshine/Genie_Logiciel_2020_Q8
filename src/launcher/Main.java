package launcher;

import controller.RoomController;
import model.Game;
import model.RoomModel;
import view.GlobalFrame;

import java.io.IOException;

public class Main {
    public static RoomModel ListRoom = new RoomModel();
    public static void main(String[] args) throws IOException {

        /* Création des models */
            ListRoom.addRoom(1,"Titre du jeu 1");
            ListRoom.addRoom(2,"Titre du jeu 2");
            ListRoom.addRoom(3,"Titre du jeu 3");
            ListRoom.addRoom(4,"Titre du jeu 4");
            //recuperation de la bdd

        /* Création des controleurs*/


        /* Création de la vue */
            new GlobalFrame();

    }
}