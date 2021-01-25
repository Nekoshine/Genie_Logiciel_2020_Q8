package Sockets;

import database.DBRoom;
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

  private static String host = Main.ipAdmin; //localhost

  /**
   * demande d'acces a une salle
   * @param idUser celui qui veut renter
   * @param salle la salle
   * @return oui ou non
   */
  public static boolean connexionRoom(int idUser, Room salle){
    boolean accept = false;
    try{
      Socket socket = new Socket(host,1096);
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


  /**
   * envoie de l'ip du joueur + recuperation de l'id de l'admin
   * @param idUser le joueur qui fait la demamnde
   * @return id de l'admin
   */
  public static int recepIpIdAdmin(int idUser){
    int idAdmin=0 ;
    try{
      
      Socket socket = new Socket(host,1096);
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
        idAdmin=user.getIdUserAdmin();
      }
      socket.close();
    }catch(IOException e){
      System.out.println("IOException : "+ e.getMessage());
    }catch(ClassNotFoundException e){
      System.out.println("ClassNotFoundException : "+ e.getMessage());
    }
    System.out.println(idAdmin);
    return idAdmin;
  }

  /**
   * reception de l'aide
   * @param idUser user qui recoit l'aide
   * @return l'aide
   */
  public static Object recepHelpGame(int idUser){

    Object obj= null;
    try{
      ServerSocket s = new ServerSocket(idUser+5201);
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

  /**
   * ordre de rafraichir la salle
   * @param idUser qui recoit l'ordre
   * @return idadmin
   */
  public static int refreshRoomAccess(int idUser){
    System.out.println("idUser : " +idUser);
    if(idUser!=9){idUser=9;}
    Main.ListRoom= DBRoom.getRooms(Main.idAdmin);
    Object obj= null;
    try{
      ServerSocket s = new ServerSocket(1629+idUser);
      Socket socket = s.accept();
      ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
      Object oserver =  in.readObject();
      obj =  oserver;
      s.close();
    }catch(IOException e){
      System.out.println("IOException : "+ e.getMessage());
    }catch(ClassNotFoundException e){
      System.out.println("ClassNotFoundException : "+ e.getMessage());
    }
    if(obj instanceof Integer){
      return (int) obj;
    }
    return 0;
  }

}
