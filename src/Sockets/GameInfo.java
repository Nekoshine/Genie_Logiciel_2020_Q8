package Sockets;

import java.io.Serializable;
import java.util.Random;

public class Reponse implements Serializable {
  
  private Game game;
  
  public Reponse(Game game){
    this.game=game;
  }
  public Game getGameInfo(){
    return game;
  }
}
