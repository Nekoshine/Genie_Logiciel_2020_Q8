package model;
/**
* * Codé par Esteban
* * Modifié par Yann
*/
public class RoomModel {
  
  private int id;
  private String Game;
  
  public RoomModel(int id, String Game){
    this.id = id;
    this.Game = Game;
  }
  
  public int getId(){
    return this.id;
  }
  public String getGame(){
    return this.Game;
  }
}
