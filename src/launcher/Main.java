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

        Hint h1 = new Hint("c'est pas une durée",10);
        Hint h2 = new Hint("predateur",20);
        Hint h3 = new Hint("debrouille toi",30);
        Enigma e1 = new Enigma(1," Combien de temps peut vivre une souris ?","Cela dépend des chats !!!",h1,h2,h3);
        ListEnigma.addEnigma(e1);

        h1 = new Hint("homonyme",10);
        h2 = new Hint("suivre et être",20);
        h3 = new Hint("debrouille toi",30);
        e1 = new Enigma(1,"Je suis Sophie, mais je ne suis pas Sophie. Qui suis-je ?","Son chien",h1,h2,h3);
        ListEnigma.addEnigma(e1);

        /* Création de la vue */
            new GlobalFrame();

    }
}