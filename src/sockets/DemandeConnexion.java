package sockets;

import model.Room;

import java.io.Serializable;

final class DemandeConnexion implements Serializable {
  
  private int idUser;
  private boolean firstConn;
  private Room salle;
  private String ip;
  
  
  DemandeConnexion(int idUser,boolean firstConn, Room salle, String ip){
    this.idUser=idUser;
    this.firstConn=firstConn;
    this.salle=salle;
    if(ip!=null) {
      this.ip = ip;
    }
    
  }

  Room getSalle() {
    return this.salle;
  }

  int getIdUser(){
    return this.idUser;
  }
  boolean getFirstConn(){
    return this.firstConn;
  }

  String getIp() {
    return this.ip;
  }
}
