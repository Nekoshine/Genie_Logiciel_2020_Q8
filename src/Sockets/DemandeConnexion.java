package Sockets;

import model.Room;

import java.io.Serializable;
import java.util.Random;

public class DemandeConnexion implements Serializable {
  
  private int idUser;
  private boolean firstConn;
  private String salle;
  
  
  public DemandeConnexion(int idUser,boolean firstConn, String salle){
    this.idUser=idUser;
    this.firstConn=firstConn;
    this.salle=salle;
    
  }

  public String getSalle() {
    return salle;
  }

  public int getIdUser(){
    return idUser;
  }
  public boolean getFirstConn(){
    return firstConn;
  }
}
