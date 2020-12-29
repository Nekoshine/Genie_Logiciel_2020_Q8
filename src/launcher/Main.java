package launcher;

import database.DBEnigma;
import model.*;
import view.GlobalFrame;

import java.io.IOException;

public class Main {
    public static EnigmaList ListEnigma = new EnigmaList();
    public static int idUser=3;
    public static void main(String[] args) throws IOException {

    ListEnigma= DBEnigma.getEnigmas(1);


        /* Création de la vue */
        new GlobalFrame();

    }
}