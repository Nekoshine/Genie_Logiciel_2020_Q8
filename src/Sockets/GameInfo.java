package Sockets;

import model.Game;

import java.io.Serializable;
import java.util.Random;

public class GameInfo implements Serializable {
  
  private Game game;
  
  public GameInfo(Game game){
    this.game=game;
  }
  public Game getGameInfo(){
    return game;
  }
}
