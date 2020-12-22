package model;

import java.util.ArrayList;

/**
* * Codé par Esteban
*/
public class RoomModel {
  
  public ArrayList<Room> List;

  public RoomModel(){
    List = new ArrayList<>();
  }

  public void addRoom(int id, String Game){
    List.add(new Room(id,Game));
  }

  public int getSize(){
    return List.size();
  }

  public void addRoom(Room salle) {
    List.add(salle);
  }

  public Room getRoom(int i){
    return List.get(i);
  }

  public String toString(){
    for(int i=0;i<this.getSize();i++){
      System.out.println(this.getRoom(i).getId());
      System.out.println(this.getRoom(i).getGame());
    }
    return "-------";
  }
}
