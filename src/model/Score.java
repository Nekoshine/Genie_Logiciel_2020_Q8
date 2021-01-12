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
    return this.id;
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
}
