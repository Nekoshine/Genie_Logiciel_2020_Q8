import java.io.Serializable;
import java.util.Random;

public class Reponse implements Serializable {
  
  private String reponse;
  
  public Reponse(String reponse){
    this.reponse=reponse;
  }
  public String getReponse(){
    return reponse;
  }
}
