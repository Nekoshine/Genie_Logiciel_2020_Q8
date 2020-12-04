package model;
/**
* * Codé par Esteban
*/
public class Room{
  
  private int id;
  private int idGame;
  
  public Room(int id,int idGame){
    this.id = id;
    this.idGame = idGame;
  }
  
  public int getId(){
    return this.id;
  }
  public int getIdGame(){
    return this.idGame;
  }
}
