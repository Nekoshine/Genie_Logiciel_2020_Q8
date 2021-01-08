package Sockets;

import java.io.Serializable;
import java.util.Random;

public class AdminInfo implements Serializable {
  
  private int idUserAdmin;
  
  public AdminInfo(int idUserAdmin){
    this.idUserAdmin=idUserAdmin;
  }
  public int getIdUserAdmin(){
    return idUserAdmin;
  }
}
