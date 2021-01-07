import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


import static java.lang.Thread.sleep;

public class Admin {
  private static int port = 1095;
  
  public static void setServerAdmin(){
    try{
      ServerSocket s = new ServerSocket(port);
      Socket socket = s.accept();
      
      ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
      Object oserver =  in.readObject();
      DemandeConnexion user = new DemandeConnexion(0);
      if(oserver instanceof DemandeConnexion){
        user = (DemandeConnexion) oserver;
        System.out.println("idUser : "+user.getIdUser());
      }
      /* String login = DBUser.getUser(user.getId()); // On récupere le login de l'user qui demande à se connecter
      
      Normalement la on va faire un pop up qui va demander à l'admin si il veut accepter le joueur dans la salle ou non ,
      vous me renvoyez un booleen pour savoir
      
      */
      boolean reponse = true;
      
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
    socket.close();
  }catch(IOException e){
    System.out.println("IOException : "+ e.getMessage());
  }catch(ClassNotFoundException e){
    System.out.println("ClassNotFoundException : "+ e.getMessage());
  }
}

}
