package Sockets;

import launcher.Main;
import model.Game;
import model.Room;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import static java.lang.Thread.sleep;

public class Client {
  
  private static int port = 1096;
  private static int portS = 1100;
  private static String host = Main.ipAdmin; //localhost
  
  public static boolean connectToServer(int idUser, Room salle){
    boolean accept = false;
    try{
      Socket socket = new Socket(host,port);
      ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
      
      DemandeConnexion signal = new DemandeConnexion(idUser,false, salle,null);
      
      out.writeObject(signal);
      ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
      Object oserver =  in.readObject();
      Reponse reponse = new Reponse("");
      if(oserver instanceof Reponse){
        reponse = (Reponse) oserver;
        if((reponse.getReponse()).equals("Oui")){
          System.out.println("C'est autorisé");
          accept=true;
        }else{
          accept=false;
          System.out.println("C'est refusé");
        }
      }
      socket.close();
    }catch(ClassNotFoundException e){
      System.out.println("ClassNotFoundException :"+ e.getMessage());
    }catch(IOException e){
      System.out.println("IOException :"+ e.getMessage());
    }
    return accept;
  }
  
  
  
  public static int recepAdminInfo(int idUser){
    int idUserAdmin=0 ;
    try{
      
      Socket socket = new Socket(host,port);
      System.out.println("Adresse ip de l'admin " + host);
      ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

      String ip = null;
      ip = Inet4Address.getLocalHost().getHostAddress();
      DemandeConnexion signal = new DemandeConnexion(idUser,true, null, ip);
      
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
  
  public static Object recepGameInfo(int portC){

    System.out.println(portC);

    Object obj= null;
    try{
      ServerSocket s = new ServerSocket(portC);
      Socket socket = s.accept();
      
      ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
      Object oserver =  in.readObject();
      if(oserver instanceof Message){
        Message  msg = (Message) oserver;
        System.out.println("Message : "+msg.getMessage());
        obj=msg;
      }
      if(oserver instanceof Indice){
        Indice  indice = (Indice) oserver;
        System.out.println("idIndice : "+ indice.getIdIndice() );
        obj=indice;
      }
      s.close();
    }catch(IOException e){
      System.out.println("IOException : "+ e.getMessage());
    }catch(ClassNotFoundException e){
      System.out.println("ClassNotFoundException : "+ e.getMessage());
    }
    return obj;
  }


}
