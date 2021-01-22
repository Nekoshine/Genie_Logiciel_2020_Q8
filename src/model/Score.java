package model;
/**
* * Codé par Esteban
*/
public class Score{
  private int id;
  private int idUser;
  private int idGame;
  private int score;
  public Score(int id,int idGame,int idUser,int score){
    this.id = id;
    this.idGame = idGame;
    this.idUser=idUser;
    this.score=score;
  }
  public int getId(){
    return this.id;
  }

  public int getIdUser(){
    return this.idUser;
  }

  public int getIdGame(){
    return this.idGame;
  }

  public int getScore(){
    return this.score;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setIdUser(int idUser) {
    this.idUser = idUser;
  }

  public void setIdGame(int idGame) {
    this.idGame = idGame;
  }

  public void setScore(int score) {
    this.score = score;
  }

  /**
   * Fonction qui permet de calculé le score en fonction du temps
   * @param scoreInit le score initial d'un jeu
   * @param timer le temps qu'a mis le joueur
   * @param timerMax le temps maximal que peu mêtre un joueur
   */
  public void calculScore(int scoreInit, int timer, int timerMax){
    int deltaScore = scoreInit/timerMax;
    int newScore = scoreInit - deltaScore * timer;
    if(newScore < 0){
      newScore = 0;
    }
    this.score = newScore;
  }
}
