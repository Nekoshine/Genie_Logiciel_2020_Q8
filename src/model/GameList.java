package model;

import java.util.ArrayList;

// Codé par Yann

public class GameList {

    public ArrayList<Game> List;

    /**
     * Procédure qui va initialiser une liste vide
     */
    public GameList(){
        List = new ArrayList<>();
    }

    /**
     * Procédure qui ajoute un jeu a la liste a l'aide de tout les champs
     * @param id du jeu
     * @param titre du jeu
     * @param score initial
     * @param idUser du createur du jeu
     * @param timer initial
     * @param ready je sais pas il faut demander a Esteban
     */
    public void addGame(int id,String titre,int score,int idUser,int timer,Boolean ready){
        List.add(new Game(id,titre,score,idUser,timer,ready));
        //faire un ajout a la bdd
    }

    /**
     * Procédure qui ajoute un jeu deja existant a la liste
     * @param game le jeu en question
     */
    public void addGame(Game game) {
        List.add(game);
    }

    /**
     * Fonction qui donne le nombre d'élement dans la liste
     * @return la taille de la liste
     */
    public int getSize(){
        return List.size();
    }

    /**
     * Donne le i-eme jeu de la liste
     * @param i
     * @return le jeu i
     */
    public Game getGame(int i){
        return List.get(i);
    }

}
