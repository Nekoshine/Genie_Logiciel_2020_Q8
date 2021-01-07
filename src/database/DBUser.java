package database;

import launcher.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.security.*;
import java.io.*;
import javax.xml.bind.DatatypeConverter;
/**
* Classe qui va contenir toutes les méthodes liées a la DB pour le user
* Codé par Esteban
*/
public class DBUser extends DBConnexion {
  
  public DBUser(){
    super.getConnexion();
  }
  
  /**
  * Fonction qui vérifie que les identifiants fournis dans la base correspondent bien a un user inscrit
  * @param  login    Login a tester
  * @param  password Password a tester
  * @return           0 si c'est un utilisateur normal, 1 si cest un admin , 3 si la connexion échoue
  */
  public static int connectUser(String login, String password){
    int isAdmin = 0;
    String pwd;
    int idUser = 0;
    try{
      MessageDigest md = MessageDigest.getInstance("MD5"); // Création de la classe qui va hash en MD5
      byte[] byteChaine = password.getBytes("UTF-8"); // On convertit la chaine en octets
      byte[] hash = md.digest(byteChaine); // On hash notre chaine en MD5
      String hashString = DatatypeConverter.printHexBinary(hash); // On convertit le tableau d'octets en string
      PreparedStatement requete = DBConnexion.getConnexion().prepareStatement("Select * from User where login=?");
      requete.setString(1,login);
      ResultSet resultat = requete.executeQuery();
      resultat.next();
      pwd = resultat.getString("pwd");
      idUser = resultat.getInt("id");
      isAdmin = resultat.getInt("isAdmin");
      requete.close();
      resultat.close();
      System.out.println();
      if(!hashString.equals(pwd)){ //On vérifie que le pwd et le hash stocké correspondent
        return 3;
      }
    } catch(SQLException e ){
      System.err.println("Erreur requete connectUser: " + e.getMessage());
    }catch(UnsupportedEncodingException e ){
      System.err.println("Erreur Encoding: " + e.getMessage());
    }catch(NoSuchAlgorithmException e ){
      System.err.println("Erreur Algorithme: " + e.getMessage());
    }
    Main.idUser=idUser;
    return isAdmin;
  }
  /**
  * Procédure qui insere un nouvel user dans la base en hachant son mdp
  * @param login Login a insérer dans la base
  * @param pwd   Mot de passe a hasher et insérer dans la base
  * @param isAdmin Booleen qui indique quel type d'utilisateur on insère
  * @return      Booleen qui indique si l insert cest correctement déroulé
  */
  public static boolean insertUser(String login,String pwd,boolean isAdmin){
    boolean inserted=false;
    int valueAdmin =0;
    if(isAdmin){
      valueAdmin=1;
    }
    try{
      PreparedStatement requetePresence = DBConnexion.getConnexion().prepareStatement("Select * from User where login=?"); // On regarde si l'user n'est pas déja dans la BDD
      requetePresence.setString(1,login);
      ResultSet resultatPresence = requetePresence.executeQuery();
      if(resultatPresence.next() != false){ // Si il est deja dans la bdd
        inserted=false; //Alors on annule l'insertion
        requetePresence.close();
        resultatPresence.close();
        return inserted;
      }else{
        requetePresence.close();
        resultatPresence.close();
        MessageDigest md = MessageDigest.getInstance("MD5"); // Création de la classe qui va hash en MD5
        byte[] byteChaine = pwd.getBytes("UTF-8"); // On convertit la chaine en octets
        byte[] hash = md.digest(byteChaine); // On hash notre chaine en MD
        PreparedStatement requete = DBUser.getConnexion().prepareStatement("INSERT INTO User VALUES (?,?,?,default)");
        String hashString = DatatypeConverter.printHexBinary(hash); // On convertit le tableau d'octets en string
        requete.setString(1,login);
        requete.setString(2,hashString);
        requete.setInt(3,valueAdmin);
        requete.executeUpdate();
        requete.close();
        PreparedStatement requeteVerif = DBUser.getConnexion().prepareStatement("Select * from User where login=?");  // On regarde si l'user a bien été inséré
        requeteVerif.setString(1,login);
        ResultSet resultatVerif = requeteVerif.executeQuery();
        if(resultatVerif.next() != false){ // Si il a été inséré
          inserted=true; // Alors on valide l insertion
        }
        resultatVerif.close();
        requeteVerif.close();
      }
    }catch(SQLException e ){
      System.err.println("Erreur requete insertUser: " + e.getMessage());
    }catch(UnsupportedEncodingException e ){
      System.err.println("Erreur Encoding: " + e.getMessage());
    }catch(NoSuchAlgorithmException e ){
      System.err.println("Erreur Algorithme: " + e.getMessage());
    }
    return inserted;
  }
  
  public static User getUser(int idUSer){
    User user = null;
    try{
      PreparedStatement requete = DBConnexion.getConnexion().prepareStatement("Select * from User where id=?"); // On regarde si l'user n'est pas déja dans la BDD
      requete.setInt(1,idUSer);
      ResultSet resultat = requete.executeQuery();
      resultat.next();
      user.setLogin(resultat.getString("login"));
      user.setPwd(resultat.getString("pwd"));
      user.setId(resultat.getInt("id"));
      user.setIsAdmin(resultat.getInt("isAdmin"));
      return user;
    }catch(NoSuchAlgorithmException e ){
      System.err.println("Erreur Algorithme: " + e.getMessage());
    }
  }
}
