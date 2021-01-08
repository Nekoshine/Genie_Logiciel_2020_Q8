package Sockets;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import static java.lang.Thread.sleep;

public class Client {
  
  private static int port = 1095;
  private static String host = "127.0.0.1"; //localhost
  
  public static void connectToServer(int idUser){
    try{
      Socket socket = new Socket(host,port);
      ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
      DemandeConnexion signal = new DemandeConnexion(idUser);
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
  
  public static int recepAdminInfo(){
    int idUserAdmin;
    try{
      ServerSocket s = new ServerSocket(port);
      Socket socket = s.accept();
      
      ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
      Object oserver =  in.readObject();
      AdminInfo user = new AdminInfo(0);
      if(oserver instanceof AdminInfo){
        user = (AdminInfo) oserver;
        System.out.println("idUser : "+user.getIdUser());
        idUserAdmin=user.getIdUserAdmin();
      }
    }catch(IOException e){
      System.out.println("IOException : "+ e.getMessage());
    }catch(ClassNotFoundException e){
      System.out.println("ClassNotFoundException : "+ e.getMessage());
    }
  }
}
