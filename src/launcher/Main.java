package launcher;

import database.DBGame;
import database.DBRoom;
import model.*;
import view.GlobalFrame;

import java.io.IOException;

public class Main {
    public static RoomList ListRoom = new RoomList();
    public static GameList ListGame = new GameList();
    public static EnigmaList ListEnigma = new EnigmaList();
    public static void main(String[] args) throws IOException {

        /* Recuperation dans la BDD */
            ListGame= DBGame.getGames();
            ListRoom = DBRoom.getRooms(ListGame);
            ListEnigma=new EnigmaList();

            ListEnigma.addEnigma(new Enigma(1,1,"Enigme1","","c'est un l'indice 1",0,"c'est un l'indice 2",0,"c'est un l'indice 3",0));
            ListEnigma.addEnigma(new Enigma(2,1,"Enigme2","","indice n°1",0,"indice n°2",0,"indice n°3",0));

        /* Création de la vue */
            new GlobalFrame();

    }
}