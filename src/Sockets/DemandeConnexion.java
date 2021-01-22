package Sockets;

import model.Room;

import java.io.Serializable;

public class DemandeConnexion implements Serializable {
  
  private int idUser;
  private boolean firstConn;
  private Room salle;
  
  
  public DemandeConnexion(int idUser,boolean firstConn, Room salle){
    this.idUser=idUser;
    this.firstConn=firstConn;
    this.salle=salle;
    
  }

  public Room getSalle() {
    return salle;
  }

  public int getIdUser(){
    return idUser;
  }
  public boolean getFirstConn(){
    return firstConn;
  }
}
