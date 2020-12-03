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
}
