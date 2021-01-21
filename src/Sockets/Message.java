package Sockets;


import java.io.Serializable;
import java.util.Random;

public class Message implements Serializable {
  
  private String message;
  
  public Message(String message){
    this.message=message;
  }
  public String getMessage(){
    return message;
  }
}
