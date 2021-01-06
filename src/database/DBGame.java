package database;
import model.Game;
import model.GameList;

import javax.xml.bind.DatatypeConverter;
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
  public static GameList getGames(int idUser){
    GameList gameList = new GameList();
    Boolean boolGame=false;
    try{
      PreparedStatement requete = DBGame.getConnexion().prepareStatement("Select * from Game WHERE idUser=? ORDER BY id ASC");
      requete.setString(1, String.valueOf(idUser));
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
      // for (int i=0;i<gameList.getSize() ;i++ ) {         System.out.println(gameList.getGame(i).getTitre());       }
      requete.close();
      resultat.close();
    } catch(SQLException e ){
      System.err.println("Erreur requete getGames: " + e.getMessage());
    }
    return gameList;
  }
  /**
  * V1 De l insertion de jeux, on insere un jeu donné en argument
  * @param  game Jeu à insérer
  * @return      insert correct ou non
  */
  public static boolean insertGame(Game game){
    boolean inserted = false;
    int valueReady=0;
    try{
      PreparedStatement requete = DBGame.getConnexion().prepareStatement("Insert into Game VALUES (?,?,?,?,?,?)");
      requete.setInt(1,game.getId());
      requete.setString(2,game.getTitre());
      requete.setInt(3,game.getScore());
      requete.setInt(4,game.getTimer());
      requete.setInt(5,game.getIdUser());
      if(game.getReady()){
        valueReady=1;
      }
      requete.setInt(6,valueReady);
      requete.executeUpdate();
      requete.close();
      PreparedStatement requeteVerif = DBUser.getConnexion().prepareStatement("Select * from Game where id=?");  // On regarde si l'user a bien été inséré
      requeteVerif.setString(1, String.valueOf(game.getId()));
      ResultSet resultatVerif = requeteVerif.executeQuery();
      if(resultatVerif.next() != false){ // Si il a été inséré
        inserted=true; // Alors on valide l insertion
      }
      resultatVerif.close();
      requeteVerif.close();
    } catch(SQLException e ){
      System.err.println("Erreur requete insertGame: " + e.getMessage());
    }
    return inserted;
  }
  
  /**
  * V2 De l insertion de jeux, on insere un jeu grace aux champs donnés en argument
  * @param  titreN a insérer
  * @return      insert correct ou non
  */
  public static boolean insertGame(String titreN,int scoreN,int idUserN,int timerN,Boolean readyN){
    boolean inserted = false;
    int valueReady=0;
    try{
      PreparedStatement requete = DBGame.getConnexion().prepareStatement("Insert into Game VALUES (default,?,?,?,?,?)");
      requete.setString(1,titreN);
      requete.setInt(2,scoreN);
      requete.setInt(3,idUserN);
      requete.setInt(4,timerN);
      if(readyN){
        valueReady=1;
      }
      requete.setInt(5,valueReady);
      requete.executeUpdate();
      requete.close();
      PreparedStatement requeteVerif = DBUser.getConnexion().prepareStatement("Select * from Game where titre=? and idUser=?");  // On regarde si l'user a bien été inséré
      requeteVerif.setString(1,titreN);
      requeteVerif.setInt(2,idUserN);
      ResultSet resultatVerif = requeteVerif.executeQuery();
      if(resultatVerif.next() != false){ // Si il a été inséré
        inserted=true; // Alors on valide l insertion
      }
      resultatVerif.close();
      requeteVerif.close();
    } catch(SQLException e ){
      System.err.println("Erreur requete insertGame: " + e.getMessage());
    }
    return inserted;
  }
}
