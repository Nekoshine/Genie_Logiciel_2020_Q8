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

    public void set(int i, Score score){this.set(i,score);}

    /**
     * Donne le i-eme jeu de la liste
     * @param i index de l'élement recherché
     * @return le jeu i
     */
    public Score getScore(int i){
        return List.get(i);
    }

    /**
     * Propagande qui fait le tri d'une ScoreListe du meilleur score au pire score
     * Bubble sort optimized
     */
    public void sortScore(){
        int size = this.getSize();
        Score scoreTmp;
        for(int i = size - 1; i > 0; i--){
            for(int j = 0; j < i; i++){
                if(this.getScore(j+1).getScore() > this.getScore(j).getScore()){
                    scoreTmp = this.getScore(j+1);
                    this.set(j + 1, this.getScore(j));
                    this.set(j, scoreTmp);
                }
            }
        }
    }

}
