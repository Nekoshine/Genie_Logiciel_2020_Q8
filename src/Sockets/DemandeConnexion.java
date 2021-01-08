package Sockets;

import java.io.Serializable;
import java.util.Random;

public class DemandeConnexion implements Serializable {
  
  private int idUser;
  private int firstConn;
  public DemandeConnexion(int idUser,boolean firstConn){
    this.idUser=idUser;
    this.firstConn=firstConn;
  }
  public int getIdUser(){
    return idUser;
  }
  public boolean getFirstConn(){
    return firstConn;
  }
}
