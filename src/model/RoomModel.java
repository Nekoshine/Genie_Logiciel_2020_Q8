package model;

import java.util.ArrayList;

/**
* * Codé par Yann
*/
public class RoomModel {
  
  public ArrayList<Room> List;

  public RoomModel(){
    List = new ArrayList<>();
  }

  public void addRoom(int id, Game Game){
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

}
