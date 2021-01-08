package Sockets;

import java.io.Serializable;
import java.util.Random;

public class DemandeConnexion implements Serializable {
  
  private int idUser;
  private int idRoom;
  
  public DemandeConnexion(int idUser,int idRoom){
    this.idUser=idUser;
    this.idRoom=idRoom;
  }
  public int getIdUser(){
    return idUser;
  }
}
