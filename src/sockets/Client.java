package sockets;

import database.DBRoom;
import launcher.Main;
import model.Room;
import model.RoomList;

import javax.naming.ldap.SortKey;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.*;


public class Client {
  
  private static String host = Main.ipAdmin; //localhost
  
  /**
  * Fonction qui va permettre à un joueur de récupérer les salles et de se connecter à l'admin
  * @param idUser id du joueur
  * @param salle salle dans laquelle le joueur veut se connecter
  * @return Connexion établie ou non
  */
  public static boolean connexionRoom(int idUser, Room salle){
    boolean reponse = false;
    try{
      Socket socket = new Socket(host,8418);
      ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
      
      DemandeConnexion signal = new DemandeConnexion(idUser,false, salle,null);
      
      out.writeObject(signal);
      ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
      Object oserver =  in.readObject();
      
      if(oserver instanceof Boolean){
        reponse = (boolean) oserver;
      }
      
      socket.close();
    }catch(ClassNotFoundException e){
      System.out.println("connexionRoom ClassNotFoundException : "+ e.getMessage());
    } catch (UnknownHostException e) {
      System.out.println("connexionRoom UnknownHostException : "+ e.getMessage());
    } catch(IOException e){
      System.out.println("connexionRoom IOException : "+ e.getMessage());
    }
    return reponse;
  }
  
  
  /**
  * Fonction qui envoie de l'ip du joueur et recuperation de l'id de l'admin
  * @param idUser le joueur qui fait la demamnde
  * @return id de l'admin
  */
  public static int recepIpIdAdmin(int idUser){
    int idAdmin=0 ;
    try{
      
      Socket socket = new Socket(host,8418);
      System.out.println("Adresse ip de l'admin " + host);
      ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
      
      String ip = Inet4Address.getLocalHost().getHostAddress();
      DemandeConnexion signal = new DemandeConnexion(idUser,true, null, ip);
      
      out.writeObject(signal);
      
      ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
      Object oserver =  in.readObject();
      int user;
      
      if(oserver instanceof Integer){
        user = (int) oserver;
        System.out.println("RAI idUser : "+user);
        idAdmin=user;
      }
      
      socket.close();
    } catch (UnknownHostException e) {
      System.out.println("recepIpIdAdmin UnknownHostException : "+ e.getMessage());
    } catch(IOException e){
      System.out.println("recepIpIdAdmin IOException : "+ e.getMessage());
    }catch(ClassNotFoundException e){
      System.out.println("recepIpIdAdmin ClassNotFoundException : "+ e.getMessage());
    }
    System.out.println(idAdmin);
    return idAdmin;
  }
  
  /**
  * Fonction qui va permettre de recevoir l'aide fournie par l'administrateur
  * @param idUser user qui recoit l'aide
  * @return Le type d'aide fourni, soit un indice , soit une chaine de caractere
  */
  public static Object recepHelpGame(int idUser){
    
    Object oserver= null;
    try{
      System.out.println(idUser);
      ServerSocket serverSocket = new ServerSocket(idUser+8809);
      Socket socket = serverSocket.accept();
      
      ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
      oserver =  in.readObject();
      serverSocket.close();
      
    }catch(IOException e){
      System.out.println("recepHelpGame IOException : "+ e.getMessage());
    }catch(ClassNotFoundException e){
      System.out.println("recepHelpGame ClassNotFoundException : "+ e.getMessage());
    }
    return oserver;
  }
  
  /**
  * Fonction qui va recevoir la liste des salles à jour
  * @param idUser Joueur qui veut recevoir les salles
  * @return la liste des salles à jour
  */
  public static RoomList refreshRoomAccess(int idUser){
    Object obj= null;
    try{
      ServerSocket s = new ServerSocket(10163+idUser);
      Socket socket = s.accept();
      ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
      obj =  in.readObject();
      s.close();
    }catch(IOException e){
      System.out.println("refreshRoomAccess IOException : "+ e.getMessage());
    }catch(ClassNotFoundException e){
      System.out.println("refreshRoomAccess ClassNotFoundException : "+ e.getMessage());
    }
    if(obj instanceof Integer){
      return DBRoom.getRooms((int) obj);
    }
    return null;
  }
  
  /**
  * Fonction qui va signaler à l'administrateur que la partie est terminée
  * @param  login Login du joueur qui a terminé sa partie
  * @param  salle Salle dans laquelle le joueur a fini de jouer
  * @return  L'administrateur a accusé réception de la fin de la partie ou non
  */
  public static boolean envoieFinPartie(String login,Room salle) {
    
    boolean accept=false;
    if (login != null && salle != null) {
      try {
        
        Socket socket = new Socket(host, 11210);
        System.out.println("Adresse ip de l'admin " + host);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        
        FinPartie signal = new FinPartie(login, salle);
        
        out.writeObject(signal);
        
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        Object oserver =  in.readObject();
        
        if (oserver instanceof Boolean){
          accept = true;
        }
        
        socket.close();
        
      } catch (UnknownHostException e) {
        System.out.println("envoieFinPartie UnknownHostException : " + e.getMessage());
      } catch (IOException e) {
        System.out.println("envoieFinPartie IOException : " + e.getMessage());
      } catch (ClassNotFoundException e) {
        System.out.println("envoieFinPartie ClassNotFoundException : " + e.getMessage());
      }
      
    }
    return accept;
  }
  
  
  /**
  * Procédure qui va envoyer le numéro de l'énigme dans laquelle le joueur est.
  * @param idUser   Joueur dans la partie
  * @param riddleNb Numéro de l'énigme
  */
  public static void SendRiddleNb(int idUser, int riddleNb){
    ServerSocket serverSocket;
    try {
      System.out.println("Je suis a l'enigme : "+riddleNb);
      serverSocket = new ServerSocket(10288+idUser);
      Socket socket = serverSocket.accept();
      ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
      out.writeObject(riddleNb);
      
      serverSocket.close();
      
    }
    catch (ConnectException e){
      System.out.println("SendRiddleNb ConnectException : " + e.getMessage());
    } catch (UnknownHostException e) {
      System.out.println("SendRiddleNb UnknownHostException : " + e.getMessage());
    } catch (IOException e) {
      System.out.println("SendRiddleNb IOException : " + e.getMessage());
    }
    
  }
  
  /**
  * Procédure qui va envoyer la réponse à une énigme fournie par le joueur
  * @param answer Réponse à envoyer a l'administrateur
  * @param idUser Joueur qui envoie sa réponse
  */
  public static void sendMyAnswer(String answer, int idUser) {
    Socket socket;
    try {
      socket = new Socket(host,idUser+11176);
      
      ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
      out.writeObject(answer);
      
      socket.close();
    } catch (UnknownHostException e) {
      System.out.println("sendMyAnswer UnknownHostException : " + e.getMessage());
    } catch (IOException e) {
      System.out.println("sendMyAnswer IOException : " + e.getMessage());
    }
  }
  /**
  * Procédure qui va envoyer le timer de la partie dans laquelle le joueur est.
  * @param timer  Timer à envoyer à l'administrateur
  * @param idUser Joueur qui joue dans la partie
  */
  public static void sendTimer(int timer,int idUser){
    
    try {
      ServerSocket serverSocket = new ServerSocket(10811+idUser);
      Socket socket = serverSocket.accept();
      
      ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
      Object oserver = in.readObject();
      
      if (oserver instanceof Boolean){
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(timer);
      }
      
      socket.close();
      serverSocket.close();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
  
}
