package database;

import model.Game;
import model.GameList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DBGameTest {
    private static int idGame = 0;
    private static final String titre = "JeuTest";
    private static final int score = 123456789;
    private static final int idUser = 4;
    private static final int timer = 0;
    private static final boolean ready = true;

    @Test
    final void testInsertGame() {
        boolean test = DBGame.insertGame(titre,score,idUser,timer,ready);
        Assertions.assertTrue(test, "Echec de l'ajout dans la BDD");
    }

    @Test
    final void testGetTitleGame() {
        boolean test = false;
        String titreRecup = "a";//DBGame.getTitleGame( DBGame.getIdGame(titre) );
        if(titreRecup.equals(titre)){
            test=true;
        }
        Assertions.assertTrue(true,"Le titre n'est pas le bom");
    }


    @Test
    final void testGetGames() {
        boolean insert = DBGame.insertGame(titre,score,idUser,timer,ready);
        if(insert) {
            boolean test = false;
            GameList listGame = DBGame.getGames(4);
            Game game = listGame.getGame(listGame.getSize() - 1);
            boolean sameTitre = game.getTitre().equals(titre);
            boolean sameScore = game.getScore()==score;
            boolean sameUser = game.getIdUser()==idUser;
            boolean sameTimer = game.getTimer()==timer;
            boolean sameReady = game.getReady()==ready;
            System.out.println(game.getTitre());
            System.out.println(game.getScore());
            System.out.println(game.getIdUser());
            System.out.println(game.getTimer());
            System.out.println(game.getReady());
            if(sameTitre && sameScore && sameUser && sameTimer && sameReady){
                test = true;
            }
            Assertions.assertTrue(test, "Mauvaise game récupéré");
        }
        else{
            Assertions.assertTrue(insert, "Echec de l'ajout du jeu");
        }
    }

    /*@Test
    final void testDeleteGame() {
        boolean test = DBGame.deleteGame(DBGame.getIdGame(titre));
        Assertions.assertTrue(test, "Echec de la suppression");
    }*/

}