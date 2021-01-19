package Sockets;

import database.DBUser;
import launcher.Main;
import model.Game;
import model.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


import static java.lang.Thread.sleep;

public class Admin {
  private static int port = 1096;
  private static String host = "127.0.0.1"; //localhost
  
  public static void setServerAdmin(int idUserAdmin){
    try{
      ServerSocket s = new ServerSocket(port);
      Socket socket = s.accept();
      ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
      
      Object oserver =  in.readObject();
      if(oserver instanceof DemandeConnexion){
        DemandeConnexion user = (DemandeConnexion) oserver;
        System.out.println("SSA idUser : "+user.getIdUser());
        
        if(user.getFirstConn()){
          System.out.println("Je renvoie l'id de l'admin " + idUserAdmin);
          ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
          AdminInfo signal = new AdminInfo(idUserAdmin);
          out.writeObject(signal);
        }else{
          boolean reponse = false;
          
          
          System.out.println("Veux tu autoriser la connexion  de ? " + user.getIdUser());/*
          Scanner sc = new Scanner (System.in);
          int reponseVal = sc.nextInt();
          if (reponseVal==1){
          reponse=true;
        }*/
        User logged = DBUser.getUser(user.getIdUser()); // On récupere le login de l'user qui demande à se connecter
        reponse = Main.frame.AcceptUser(logged.getLogin(),user.getSalle()); //pop up demande de connexion
        
        
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        if (reponse){ // Si cest le bon user alors on lui dit quon accepte sa demande de jeu
          System.out.println("Je renvoie oui");
          Reponse signal = new Reponse("Oui");
          out.writeObject(signal);
        }else{ // Si c'est le mauvais on refuse
        System.out.println("Je renvoie non");
        Reponse signal = new Reponse("Non");
        out.writeObject(signal);
      }
    }
  }
  s.close();
}catch(IOException e){
  System.out.println("IOException : "+ e.getMessage());
  e.printStackTrace();
}catch(ClassNotFoundException e){
  System.out.println("ClassNotFoundException : "+ e.getMessage());
}

}

public void envoiGameInfo(Game game){
  try{
    Socket socket = new Socket(host,port);
    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
    GameInfo gameSend = new GameInfo(game);
    out.writeObject(gameSend);
    socket.close();
  } catch(IOException e){
    System.out.println("IOException :" + e.getMessage());
  }
}
}
