package model;

import java.util.ArrayList;

// Codé par Yann

public class RoomList {
  
  public ArrayList<Room> List;

  /**
   * Procedure qui va initaliser une liste vide
   */
  public RoomList(){
    List = new ArrayList<>();
  }

  /**
   * Procédure qui ajoute un jeu a la liste a l'aide de tout les champs
   * @param id du jeu
   * @param Game associé a la salle
   */
  public void addRoom(int id, Game Game,boolean competitive){
    List.add(new Room(id,Game,competitive));
  }

  /**
   * Fonction qui donne le nombre d'élement dans la liste
   * @return la taille de la liste
   */
  public int getSize(){
    return List.size();
  }

  /**
   * Procédure qui ajoute une salle deja existante a la liste
   * @param salle la salle en question
   */
  public void addRoom(Room salle) {
    List.add(salle);
  }

  /**
   * Donne la i-eme salle de la liste
   * @param i
   * @return le salle i
   */
  public Room getRoom(int i){
    return List.get(i);
  }

  public Room findByID(int i) {
    for (int j = 0; j < this.getSize(); j++) {
      if (this.getRoom(j).getId() == i) {
        return this.getRoom(j);
      }
    }
    return null;
  }
}
