package sockets;

import database.DBUser;
import launcher.Main;
import model.User;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;


public class Admin {
  private static HashMap<Integer,String> host = new HashMap<>();
  
  /**
  * Procédure qui va mettre en place le server de sockets qui va servir lors de la première connexion et quand un joueur rejoint une salle.
  * @param idAdmin l'id de l'admin sur le reseau
  */
  public static void setServerAdmin(int idAdmin){
    try{
      ServerSocket serverSocket = new ServerSocket(8418);
      Socket socket = serverSocket.accept();
      
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
          
          System.out.println("Veux tu autoriser la connexion  de ? " + user.getIdUser());
          User logged = DBUser.getUser(user.getIdUser()); // On récupere le login de l'user qui demande à se connecter
          boolean reponse = Main.frame.AcceptUser(logged.getLogin(),user.getSalle()); //pop up demande de connexion
          
          ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
          out.writeObject(reponse);
          
        }
      }
      Thread.sleep(4);
      socket.close();
      serverSocket.close();
      
    }catch(IOException e){
      System.out.println("setServerAdmin IOException : "+ e.getMessage());
    }catch(ClassNotFoundException e){
      System.out.println("setServerAdmin ClassNotFoundException : "+ e.getMessage());
    }catch(InterruptedException  e){
      System.out.println("setServerAdmin InterruptedException  : "+ e.getMessage());
    }
  }
  
  /**
  * Procédure qui permet d'envoyer un message ou le numéro d'indice à débloquer pour le client
  * @param Message à envoyer
  * @param idIndice l'indice a deverouiller
  * @param idUser le joueur à qui envoyer les informations
  */
  public static void envoiAideJoueur(String message, int idIndice, int idUser){
    
    Socket socket = null;
    try{
      if(message != null ){
        
        socket = new Socket(host.get(idUser),idUser+8809);
        
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(message);
      }else{
        socket = new Socket(host.get(idUser),idUser+8809);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(idIndice);
      }
      socket.close();
    } catch (UnknownHostException e) {
      System.out.println("envoiAideJoueur UnknownHostException : " + e.getMessage());
    } catch(IOException e){
      System.out.println("envoiAideJoueur IOException : " + e.getMessage());
    }
  }
  
  /**
  * Fonction qui va récupérer la réponse que le joueur fourni.
  * @param  idUser id de l'utilisateur
  * @return        la réponse reçue
  */
  public static String recepAnswerJoueur(int idUser) {
    ServerSocket s = null;
    String msg = null;
    try {
      s = new ServerSocket(11176+idUser);
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
      System.out.println("recepAnswerJoueur IOException : " + e.getMessage());
    }
    catch (ClassNotFoundException e) {
      System.out.println("recepAnswerJoueur ClassNotFoundException : " + e.getMessage());
    }
    
    return msg;
  }
  
  /**
  * Procédure qui va permettre d'actualiser l'affichage du menu des salles
  * @param idAdmin sur le reseau
  */
  public static void refreshRoomAccess(int iAdmin){
    try{
      for (HashMap.Entry mapentry : host.entrySet()) {
        int idUser = (int)mapentry.getKey();
        Socket socket = new Socket((String) mapentry.getValue(), 10163+idUser);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(iAdmin);
        socket.close();
      }
    } catch(IOException e){
      System.out.println("refreshRoomAccess IOException : " + e.getMessage());
    }
  }
  
  /**
  * Fonction qui récupère le numéro de l'énigme en fonction de l'utilisateur qui est dans la room
  * @param  idUser Utilisateur qui est dans la salle
  * @return        Id de l'énigme
  */
  public static int getRiddleNb(int idUser) {
    int riddleNb=1;
    try {
      System.out.println("je recupère l'avancement du joueur : "+idUser);
      Socket socket =new Socket(host.get(idUser),10288+idUser);
      ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
      Object oserver =  in.readObject();
      socket.close();
      
      if(oserver instanceof Integer){
        System.out.println("il est "+(int)oserver);
        riddleNb = (int) oserver;
      }
      
    } catch (UnknownHostException e) {
      System.out.println("getRiddleNb UnknownHostException " +e.getMessage());
    } catch (IOException e) {
      System.out.println("getRiddleNb IOException " +e.getMessage());
    } catch (ClassNotFoundException e) {
      System.out.println("getRiddleNb ClassNotFoundException " +e.getMessage());
    }
    return riddleNb;
  }
  
  /**
  * Procédure qui va  permettre de gérer la fin de la partie quand un utilisateur à terminé.
  */
  public static void acceptFin() {
    try {
      ServerSocket serverSocket = new ServerSocket(11210);
      Socket socket = serverSocket.accept();
      
      ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
      Object oserver = in.readObject();
      
      if (oserver instanceof FinPartie) {
        FinPartie fin = (FinPartie) oserver;
        
        Main.frame.endGame(fin.getLogin(), fin.getSalle()); //pop up demande de connexion
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(true);
        
      }
      Thread.sleep(4);
      socket.close();
      serverSocket.close();
      
    } catch (IOException e) {
      System.out.println("acceptFin IOException : "+ e.getMessage());
    } catch (ClassNotFoundException e) {
      System.out.println("acceptFin ClassNotFoundException : " + e.getMessage());
    } catch (InterruptedException e) {
      System.out.println("acceptFin InterruptedException  : " + e.getMessage());
    }
  }
  
  /**
  * Fonction qui va récupérer le timer de la salle où le joueur dont on a spécifié l'ID est.
  * @param  idUser Joueur qui est dans la salle
  * @return        Temps restant
  */
  public static int recupTimer(int idUser){
    Socket socket;
    int timer =0;
    try {
      socket = new Socket(host.get(idUser),idUser+10811);
      
      ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
      out.writeObject(true);
      
      ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
      Object oserver =  in.readObject();
      
      if (oserver instanceof Integer){
        timer = (int) oserver;
      }
      
      socket.close();
      
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return timer;
  }
}
