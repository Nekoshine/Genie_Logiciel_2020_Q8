package model;
/**
* * Codé par Esteban
*/
public class Game{
  private int id;
  private String titre;
  private int score;
  private int idUser;
  private int timer;
  private Boolean ready;
  public Game(int id,String titre,int score,int idUser,int timer,Boolean ready){
    this.id = id;
    this.titre = titre;
    this.score = score;
    this.idUser = idUser;
    this.timer = timer;
    this.ready=ready;
  }
  public int getId(){
    return this.id;
  }
  public String getTitre(){
    return this.titre;
  }
  public int getIdUSer(){
    return this.idUser;
  }
  public int getScore(){
    return this.score;
  }
  public int getTimer(){
    return this.timer;
  }
  public Boolean getReady(){
    return this.ready;
  }

  public void setTitre(String titre) {
    this.titre = titre;
  }

  @Override
  public String toString() {
    return titre;
  }

    public int getIdUser() {
    return id;
    }
}
