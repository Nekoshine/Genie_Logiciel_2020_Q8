import java.io.Serializable;
import java.util.Random;

public class DemandeConnexion implements Serializable {
  
  private int idUser;
  
  public DemandeConnexion(int idUser){
    this.idUser=idUser;
  }
  public int getIdUser(){
    return idUser;
  }
}
