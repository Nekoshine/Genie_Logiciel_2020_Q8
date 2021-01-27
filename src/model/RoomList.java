package model;

import java.util.ArrayList;

/**
 * Liste de salles
 */
public final class RoomList {
  
      public ArrayList<Room> List;

      /**
       * Procedure qui va initaliser une liste vide
       */
      public RoomList(){
          this.List = new ArrayList<>();
      }

      /**
       * Procédure qui ajoute un jeu a la liste a l'aide de tout les champs
       * @param id du jeu
       * @param Game associé a la salle
       */
      public void addRoom(int id, Game Game,boolean competitive, int userInside){
          this.List.add(new Room(id,Game,competitive, userInside));
      }

      /**
       * Fonction qui donne le nombre d'élement dans la liste
       * @return la taille de la liste
       */
      public int getSize(){
          return this.List.size();
      }

      /**
       * Procédure qui ajoute une salle deja existante a la liste
       * @param salle la salle en question
       */
      public void addRoom(Room salle) {
        this.List.add(salle);
      }

      /**
       * Donne la i-eme salle de la liste
       * @param i
       * @return le salle i
       */
      public Room getRoom(int i){
          return this.List.get(i);
      }

      public Room findByID(int id){
          Room room = null;
          int size = this.getSize();
          for(int j = 0; j<size; j++){
              if (this.getRoom(j).getId()==id){
                  room=this.getRoom(j);
              }
          }
          return room;
      }
}
