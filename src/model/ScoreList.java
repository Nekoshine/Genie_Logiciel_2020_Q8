package model;

import java.util.ArrayList;

/**
 * Liste de score
 */
public final class ScoreList {

    public ArrayList<Score> List;

    /**
     * Initialisation d'une liste vide
     */
    public ScoreList (){
        this.List = new ArrayList<>();
    }

    /**
     * Fonction qui ajoute un score déja existant
     * @param score Le score déja existant
     */
    public void addScore(Score score){
        this.List.add(score);
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
     * @param i index de l'élement recherché
     * @return le jeu i
     */
    public Score getScore(int i){
        return this.List.get(i);
    }

}
