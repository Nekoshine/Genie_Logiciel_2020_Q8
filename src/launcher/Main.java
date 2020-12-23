package launcher;

import database.DBGame;
import database.DBRoom;
import model.*;
import view.GlobalFrame;

import java.io.IOException;

public class Main {
    public static RoomList ListRoom = new RoomList();
    public static GameList ListGame = new GameList();
    public static void main(String[] args) throws IOException {

        /* Recuperation dans la BDD */
            ListGame= DBGame.getGames();
            ListRoom = DBRoom.getRooms(ListGame);

        /* Création de la vue */
            new GlobalFrame();

    }
}