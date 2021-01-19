package Sockets;

import model.Game;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import static java.lang.Thread.sleep;

public class Client {
  
  private static int port = 1096;
  private static String host = "127.0.0.1"; //localhost
  
  public static void connectToServer(int idUser){
    try{
      Socket socket = new Socket(host,port);
      ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
      
      DemandeConnexion signal = new DemandeConnexion(idUser,false);
      
      out.writeObject(signal);
      ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
      Object oserver =  in.readObject();
      Reponse reponse = new Reponse("");
      if(oserver instanceof Reponse){
        reponse = (Reponse) oserver;
        if((reponse.getReponse()).equals("Oui")){
          System.out.println("C'est autorisé");
        }else{
          System.out.println("C'est refusé");
        }
      }
      socket.close();
    }catch(ClassNotFoundException e){
      System.out.println("ClassNotFoundException :"+ e.getMessage());
    }catch(IOException e){
      System.out.println("IOException :" + e.getMessage());
    }
  }
  
  
  public static int recepAdminInfo(int idUser){
    int idUserAdmin=0 ;
    try{
      
      Socket socket = new Socket(host,port);
      ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
      
      DemandeConnexion signal = new DemandeConnexion(idUser,true);
      
      out.writeObject(signal);
      
      ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
      Object oserver =  in.readObject();
      AdminInfo user = new AdminInfo(0);
      if(oserver instanceof AdminInfo){
        user = (AdminInfo) oserver;
        System.out.println("RAI idUser : "+user.getIdUserAdmin());
        idUserAdmin=user.getIdUserAdmin();
      }
      socket.close();
    }catch(IOException e){
      System.out.println("IOException : "+ e.getMessage());
    }catch(ClassNotFoundException e){
      System.out.println("ClassNotFoundException : "+ e.getMessage());
    }
    System.out.println(idUserAdmin);
    return idUserAdmin;
  }
  
  public static Game recepGameInfo(){
    Game gameRCV = null;
    try{
      
      ServerSocket s = new ServerSocket(port);
      Socket socket = s.accept();
      
      ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
      Object oserver =  in.readObject();
      if(oserver instanceof GameInfo){
        GameInfo  game = (GameInfo) oserver;
        System.out.println("Titre du jeu recu : "+game.getGameInfo().getTitre());
        gameRCV=game.getGameInfo();
      }
    }catch(IOException e){
      System.out.println("IOException : "+ e.getMessage());
    }catch(ClassNotFoundException e){
      System.out.println("ClassNotFoundException : "+ e.getMessage());
    }
    return gameRCV;
  }
}
