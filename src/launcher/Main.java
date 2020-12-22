package launcher;

import controller.RoomController;
import model.Game;
import model.GameModel;
import model.Room;
import model.RoomModel;
import view.GlobalFrame;

import java.io.IOException;

public class Main {
    public static RoomModel ListRoom = new RoomModel();
    public static GameModel ListGame = new GameModel();
    public static void main(String[] args) throws IOException {

        /* Création des models */
            Game jeu1 = new Game(1,"Titre du jeu 1",0,0,0,true);
            Game jeu2 = new Game(2,"Titre du jeu 2",0,0,0,true);
            Game jeu3 = new Game(3,"Titre du jeu 3",0,0,0,true);
            Game jeu4 = new Game(4,"Titre du jeu 4",0,0,0,true);

            Room salle1 = new Room(1,jeu1);
            Room salle2 = new Room(2,jeu2);
            Room salle3 = new Room(3,jeu3);
            Room salle4 = new Room(4,jeu4);

            ListRoom.addRoom(salle1);
            ListRoom.addRoom(salle2);
            ListRoom.addRoom(salle3);
            ListRoom.addRoom(salle4);

            ListGame.addGame(jeu1);
            ListGame.addGame(jeu2);
            ListGame.addGame(jeu3);
            ListGame.addGame(jeu4);

            //recuperation de la bdd

        /* Création des controleurs*/


        /* Création de la vue */
            new GlobalFrame();

    }
}