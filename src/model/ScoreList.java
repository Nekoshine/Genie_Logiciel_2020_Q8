package model;

import java.util.ArrayList;

public class ScoreList {

    public ArrayList<Score> List;

    /**
     * Initialisation d'une liste vide
     */

    public ScoreList (){List = new ArrayList<>();}

    /**
     * Procédure qui ajoute un Score à la liste
     * @param id l'id du score
     * @param idGame l'id du jeu où le score a été marqué
     * @param idUser l'id de l'utilisateur qui a marqué ce score
     * @param score la valeur du score
     */
    public void addScore(int id,int idGame,int idUser,int score){
        List.add(new Score(id,idGame,idUser,score));
    }

    /**
     * Fonction qui ajoute un score déja existant
     * @param score Le score déja existant
     */
    public void addScore(Score score){
        List.add(score);
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
     * @param i index de l'élement recherché
     * @return le jeu i
     */
    public Score getScore(int i){
        return List.get(i);
    }

}
