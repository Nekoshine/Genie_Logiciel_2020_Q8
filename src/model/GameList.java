package model;

import java.util.ArrayList;

/**
 * Liste de jeux
 */
public final class GameList {

    public ArrayList<Game> List;

    /**
     * Procédure qui va initialiser une liste vide
     */
    public GameList(){
        this.List = new ArrayList<>();
    }

    /**
     * Procédure qui ajoute un jeu deja existant a la liste
     * @param game le jeu en question
     */
    public void addGame(Game game) {
        this.List.add(game);
    }

    /**
     * Fonction qui donne le nombre d'élement dans la liste
     * @return la taille de la liste
     */
    public int getSize(){
        return this.List.size();
    }

    /**
     * Donne le i-eme jeu de la liste
     * @param i l'element recherché
     * @return le jeu i
     */
    public Game getGame(int i){
        return this.List.get(i);
    }

    /**
     * Donne le jeu qui possède un id
     * @param id l'id recherché
     * @return le jeu
     */
    public Game findByID(int id){
        Game game = null;
        int size = this.getSize();
        for(int j = 0; j<size; j++){
            if (this.getGame(j).getId()==id){
                game=this.getGame(j);
            }
        }
        return game;
    }
}
