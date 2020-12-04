package model;

import controller.DBGame;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class DBGameTest {

    private DBGame DB=null;

    @BeforeEach
    void initDB(){
        DB = new DBGame();
        DB.getConnexion();
    }

    @AfterEach
    void decoDB(){
        DB=null ;
    }


    @Test
    void testGetGames() {
//        public static ArrayList<Game> getGames()
///**
// * Fonction qui va récupérer les jeux dans la base de données et le stocker dans un ArrayList
// * @return Liste de jeux
// */
    }
}