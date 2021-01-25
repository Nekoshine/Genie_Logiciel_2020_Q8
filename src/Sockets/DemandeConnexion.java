package Sockets;

import model.Room;

import java.io.Serializable;

public class DemandeConnexion implements Serializable {
  
  private int idUser;
  private boolean firstConn;
  private Room salle;
  private String ip;
  
  
  public DemandeConnexion(int idUser,boolean firstConn, Room salle, String ip){
    this.idUser=idUser;
    this.firstConn=firstConn;
    this.salle=salle;
    if(ip!=null) {
      this.ip = ip;
    }
    
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

  public String getIp() {
    return ip;
  }
}
