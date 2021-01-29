package sockets;

import model.Room;

import java.io.Serializable;
/**
* Classe qui va permettre de créer un objet sérializable FinPartie qui sera envoyé a travers les sockets
*/
final class FinPartie implements Serializable {
  
  private String login;
  private Room salle;
  
  FinPartie(String login, Room salle){
    this.login = login;
    this.salle=salle;
  }
  
  Room getSalle() {
    return this.salle;
  }
  
  String getLogin() {
    return this.login;
  }
}
