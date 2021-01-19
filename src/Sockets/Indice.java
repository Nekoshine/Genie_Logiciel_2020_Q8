package Sockets;


import java.io.Serializable;
import java.util.Random;

public class Indice implements Serializable {
  
  private int idIndice;
  
  public Indice(int idIndice){
    this.idIndice=idIndice;
  }
  public int getIdIndice(){
    return idIndice;
  }
}
