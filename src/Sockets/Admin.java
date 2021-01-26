package Sockets;

import database.DBUser;
import launcher.Main;
import model.Game;
import model.Room;
import model.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;


import static java.lang.Thread.sleep;

public class Admin {
  private static HashMap<Integer,String> host = new HashMap<Integer, String>();

/*  Map<String, String> map = new HashMap<String, String>();
map.put("dog", "type of animal");
System.out.println(map.get("dog"));
  */

  /**
   * autoriser un joueur à jouer
   * @param idAdmin l'id de l'admin sur le reseau
   */
  public static void setServerAdmin(int idAdmin){
    try{
      ServerSocket s = new ServerSocket(1096);
      Socket socket = s.accept();

      ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
      Object oserver =  in.readObject();
      if(oserver instanceof DemandeConnexion){
        DemandeConnexion user = (DemandeConnexion) oserver;
        if(user.getFirstConn()){

          System.out.println("Je renvoie l'id de l'admin " + idAdmin);
          int idUser = user.getIdUser();
          String ip = user.getIp();
          host.put(idUser,ip);
          System.out.println("SSA idUser : "+user.getIdUser());
          System.out.println("adresse ip du client : " + host.get(user.getIdUser()));

          ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
          out.writeObject(idAdmin);
        }else{
          boolean reponse = false;


          System.out.println("Veux tu autoriser la connexion  de ? " + user.getIdUser());
          User logged = DBUser.getUser(user.getIdUser()); // On récupere le login de l'user qui demande à se connecter
          reponse = Main.frame.AcceptUser(logged.getLogin(),user.getSalle()); //pop up demande de connexion

          ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
          out.writeObject(reponse);

      }
    }
      Thread.sleep(4);
      socket.close();
      s.close();

  }catch(IOException e){
    //System.out.println("IOException : "+ e.getMessage());
  }catch(ClassNotFoundException e){
    System.out.println("ClassNotFoundException : "+ e.getMessage());
  }catch(InterruptedException  e){
    System.out.println("InterruptedException  : "+ e.getMessage());
  }
}

  /**
   * envoyer de l'aide au joueur
   * @param message helper
   * @param idIndice l'inde a deveroullé
   * @param idUser le joueur
   */
  public static void envoiAideJoueur(String message, int idIndice, int idUser){

    Socket socket = null;
  try{
    if(message != null ){

      socket = new Socket(host.get(idUser),idUser+5201);

      ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
      out.writeObject(message);
    }else{
      socket = new Socket(host.get(idUser),idUser+5201);
      ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
      out.writeObject(idIndice);
    }
    socket.close();
  } catch(IOException e){
    System.out.println("IOException :" + e.getMessage());
  }
}


public static String recepAnswerJoueur(int idUser) {
    ServerSocket s = null;
    String msg = null;
    try {
        s = new ServerSocket(49153+idUser);
        Socket socket = s.accept();

        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        Object oserver = in.readObject();

        if (oserver instanceof String) {
            msg = (String) oserver;
            System.out.println("Message : " + msg);
        }
        socket.close();
        s.close();
    }
    catch (IOException e) {
    e.printStackTrace();
    }
    catch (ClassNotFoundException e) {
    e.printStackTrace();
    }

    return msg;
}

  /**
   * ordre d'actualisé l'affichage de roomAccess
   * @param iAdmin sur le reseau
   */
  public static void refreshRoomAccess(int iAdmin){
    try{
      for (HashMap.Entry mapentry : host.entrySet()) {
        int idUser = (int)mapentry.getKey();
        Socket socket = new Socket((String) mapentry.getValue(), 1629+idUser);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(iAdmin);
        socket.close();
      }
    } catch(IOException e){
      System.out.println("IOException :" + e.getMessage());
    }
  }


  //mettre socket plutot que serveur
  public static int getRiddleNb(int idUser) {
    try {
      System.out.println("je recupère l'avancement du joueur : "+idUser);
      Socket socket =new Socket(host.get(idUser),49553+idUser);
      ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
      Object oserver =  in.readObject();
      socket.close();

      if(oserver instanceof Integer){
        System.out.println("il est "+(int)oserver);
        return (int) oserver;
      }

    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    System.out.println("je retourne enore et toujours 1 car je suis nul");
    return 1;
  }

  public static void acceptFin() {
    try {
      ServerSocket s = new ServerSocket(2530);
      Socket socket = s.accept();

      ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
      Object oserver = in.readObject();

      if (oserver instanceof FinPartie) {
        FinPartie fin = (FinPartie) oserver;

        Main.frame.endGame(fin.getLogin(), fin.getIdSalle()); //pop up demande de connexion
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(true);

      }
      Thread.sleep(4);
      socket.close();
      s.close();

    } catch (IOException e) {
      //System.out.println("IOException : "+ e.getMessage());
    } catch (ClassNotFoundException e) {
      System.out.println("ClassNotFoundException : " + e.getMessage());
    } catch (InterruptedException e) {
      System.out.println("InterruptedException  : " + e.getMessage());
    }
  }
}
