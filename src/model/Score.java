package model;

/**
 * Représentation du score
 */
public final class Score{
      private int id;
      private int idUser;
      private int idGame;
      private int score;

    /**
     * Création d'un objet Score
     * @param id L'id du Score dans la base de donnée
     * @param idGame L'id du jeu qui est lié à ce score
     * @param idUser L'id de l'utilisateur qui a marqué ce score
     * @param score  La valeur du score
     */
      public Score(int id,int idGame,int idUser,int score){
            this.id = id;
            this.idGame = idGame;
            this.idUser=idUser;
            this.score=score;
      }

    /**
     * Fonction qui permet d'obtenir l'id du score
     * @return L'id du score
     */
      public int getId(){
          return this.id;
      }

    /**
     * Fonction qui permet d'obtenir l'id de l'utilisateur qui a marqué ce score
     * @return  l'id de l'utilisateur qui a marqué ce score
     */
    public int getIdUser(){
          return this.idUser;
      }

    /**
     * Fonction qui permet d'obtenir l'id du jeu
     * @return  l'id du jeu
     */
      public int getIdGame(){
          return this.idGame;
      }

    /**
     * Fonction qui permet d'obtenir la valeur du score
     * @return  la valeur du score
     */
      public int getScore(){
          return this.score;
      }

    /**
     *  Permet de définir l'id d'un score
     * @param id  l'id du score
     */
      public void setId(int id) {
          this.id = id;
      }

    /**
     * Permet de définir l'id de l'user
     * @param idUser l'id de l'user
     */
      public void setIdUser(int idUser) {
          this.idUser = idUser;
      }

    /**
     * Permet de définir la valeur du score
     * @param score la valeur du score
     */
      public void setScore(int score) {
          this.score = score;
      }

      /**
       * Fonction qui permet de calculé le score en fonction du temps
       * @param scoreInit le score initial d'un jeu
       * @param timer le temps qu'a mis le joueur
       * @param nbErreur le nombre d'erreur du joueur
       */
      public void calculScore(int scoreInit, int timer, int nbErreur){
          int newScore = scoreInit - timer - 100*nbErreur;
          if(newScore < 0){
            newScore = 0;
          }
          this.score = newScore;
      }
}
