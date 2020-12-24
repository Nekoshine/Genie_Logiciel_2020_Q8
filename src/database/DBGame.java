package database;
import model.Game;
import model.GameList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
* Classe qui va contenir toutes les méthodes liées a la DB pour un jeu
* Codé par Esteban
*/
public class DBGame extends DBConnexion {

  public DBGame(){
    super.getConnexion();
  }

  /**
  * Fonction qui va récupérer les jeux dans la base de données et le stocker dans un ArrayList
  * @return Liste de jeux
  */
  public static GameList getGames(){
    GameList gameList = new GameList();
    Boolean boolGame=false;
    try{
      PreparedStatement requete = DBGame.getConnexion().prepareStatement("Select * from Game");
      ResultSet resultat = requete.executeQuery();
      while (resultat.next() != false) { // On itère chaque résultat
        if(resultat.getInt("ready")==1){ // On convertit le booleen car il est stocké comme un entier dans la base
          boolGame=true;
        }else{
          boolGame=false;
        }
        gameList.addGame(new Game(resultat.getInt("id"), resultat.getString("titre"),
        resultat.getInt("score"), resultat.getInt("idUser"),resultat.getInt("timer"),boolGame)); // On crée l'objet model.Game et on l'ajoute dans la liste
      }
      for (int i=0;i<gameList.getSize() ;i++ ) {         System.out.println(gameList.getGame(i).getTitre());       }
      requete.close();
      resultat.close();
    } catch(SQLException e ){
      System.err.println("Erreur requete connectUser: " + e.getMessage());
    }
    return gameList;
  }
}
