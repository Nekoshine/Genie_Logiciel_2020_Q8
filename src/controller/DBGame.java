package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
* Classe qui va contenir toutes les méthodes liées a la DB pour un jeu
*/
public class DBGame {
  private static Connection connexion;
  
  public DBGame(){
    getConnexion();
  }
  /**
  * Fonction qui va initialiser la connexion avec la BDD
  * @return La connexion établie avec la bdd
  */
  public static Connection getConnexion(){
    if (connexion==null){
      try{
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Initialising connection to Q8 Database");
        DBGame.connexion = DriverManager.getConnection("jdbc:mysql://mysql-nekoshine.alwaysdata.net/nekoshine_gl2020q8",
        "nekoshine_user", "Q82020GL");
        System.out.println("Connection to Q8 Database complete");
        return connexion;
      } catch ( SQLException e){
        System.out.println("Couldn't connect to Q8 DB :" + e.getMessage());
      }catch ( ClassNotFoundException e){
        System.out.println("Couldn't connect to Q8 DB :" + e.getMessage());
      }
    }
    System.out.println("Getting connexion to Q8 DB");
    return connexion;
  }
  /**
  * Fonction qui va récupérer les jeux dans la base de données et le stocker dans un ArrayList
  * @return Liste de jeux
  */
  public static ArrayList<Game> getGames(){
    ArrayList<Game> gameList = new ArrayList<Game>();
    Boolean boolGame=false;
    try{
      PreparedStatement requete = DBUser.getConnexion().prepareStatement("Select * from model.Game");
      ResultSet resultat = requete.executeQuery();
      if (resultat.next()) { // On itère chaque résultat
        if(resultat.getInt("ready")==1){ // On convertit le booleen car il est stocké comme un entier dans la base
          boolGame=true;
        }else{
          boolGame=false;
        }
        gameList.add(new Game(resultat.getInt("id"), resultat.getString("titre"),
        resultat.getInt("score"), resultat.getInt("idUser"),resultat.getInt("timer"),boolGame)); // On crée l'objet model.Game et on l'ajoute dans la liste
      }
      requete.close();
      resultat.close();
    } catch(SQLException e ){
      System.err.println("Erreur requete connectUser: " + e.getMessage());
    }
    return gameList;
  }  
}
