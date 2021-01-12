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
  public static boolean deleteGame(int idGame){
    boolean boolDelete=false;
    try{
      PreparedStatement requete = DBConnexion.getConnexion().prepareStatement("Delete from Game WHERE id=? ");
      requete.setInt(1, idGame);
      requete.executeUpdate();
      requete.close();
      PreparedStatement requeteVerif = DBConnexion.getConnexion().prepareStatement("Select * from Game where id=?");  // On regarde si l'user a bien été supprimé
      requeteVerif.setInt(1, idGame);
      ResultSet resultatVerif = requeteVerif.executeQuery();
      if(!resultatVerif.next()){ // Si il a été supprimé
        boolDelete=true; // Alors on valide la suppression
      }
      resultatVerif.close();
      requeteVerif.close();
    } catch(SQLException e ){
      System.err.println("Erreur requete deleteGame: " + e.getMessage());
    }
    return boolDelete;
  }
  
  /**
  * Fonction qui va récupérer les jeux dans la base de données et le stocker dans un ArrayList
  * @return Liste de jeux
  */
  public static GameList getGames(int idUser){
    GameList gameList = new GameList();
    Boolean boolGame=false;
    try{
      PreparedStatement requete = DBConnexion.getConnexion().prepareStatement("Select * from Game WHERE idUser=? ORDER BY id ASC");
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
  * V2 De l insertion de jeux, on insere un jeu grace aux champs donnés en argument
  * @param  titreN a insérer
  * @return      insert correct ou non
  */
  public static boolean insertGame(String titreN,int scoreN,int idUserN,int timerN,Boolean readyN){
    boolean inserted = false;
    int valueReady=0;
    try{
      PreparedStatement requete = DBConnexion.getConnexion().prepareStatement("Insert into Game VALUES (default,?,?,?,?,?)");
      requete.setInt(1,idUserN);
      requete.setString(2,titreN);
      requete.setInt(3,scoreN);
      requete.setInt(4,timerN);
      if(readyN){
        valueReady=1;
      }
      requete.setInt(5,valueReady);
      requete.executeUpdate();
      requete.close();
      PreparedStatement requeteVerif = DBConnexion.getConnexion().prepareStatement("Select * from Game where titre=? and idUser=?");  // On regarde si l'user a bien été inséré
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
  public static boolean isInDB(int id){
    boolean isHere = false;
    try {
      PreparedStatement requetePresence = DBConnexion.getConnexion().prepareStatement("SELECT * FROM Game WHERE id=?");
      requetePresence.setInt(1, id);
      ResultSet resultatPresence = requetePresence.executeQuery();
      if(resultatPresence.next() != false){ // Si il est deja dans la bdd
        isHere=true; //Alors on annule l'insertion
        requetePresence.close();
        resultatPresence.close();
      }
    }
    catch(SQLException e ){
      System.err.println("Erreur requete isInDB: " + e.getMessage());
    }
    return isHere;
  }

  public static int getIdGame(String name) {
    int idGame=0;
    try {
      PreparedStatement requete = DBConnexion.getConnexion().prepareStatement("SELECT id FROM Game WHERE titre=?");
      requete.setString(1, name);
      ResultSet resultat = requete.executeQuery();
      if (resultat.next()!=false) {
        idGame = resultat.getInt("id");
      }
      requete.close();
      resultat.close();
      return idGame;
    } catch (SQLException e) {
      System.err.println("Erreur requete getIdGame: " + e.getMessage());
    }
    return idGame;
  }
  
  public static String getTitleGame(int idGame) {
    String titre = "Titre";
    try {
      PreparedStatement requete = DBConnexion.getConnexion().prepareStatement("Select titre from Game where id=?");
      requete.setInt(1, idGame);
      ResultSet resultat = requete.executeQuery();
      if (resultat.next()!=false) {
        titre = resultat.getString("titre");
      }
      requete.close();
      resultat.close();
      return titre;
    } catch (SQLException e) {
      System.err.println("Erreur requete getIdGame: " + e.getMessage());
    }
    return titre;
  }

  public static boolean majGame(int idGame, String titreN,int scoreN,int timerN,Boolean readyN){
    boolean inserted = false;
    try{
      PreparedStatement requete = DBConnexion.getConnexion().prepareStatement("UPDATE Game SET titre=?, score=?, timer=?, ready=? WHERE id=?");
      requete.setString(1, titreN);
      requete.setInt(2, scoreN);
      requete.setInt(3, timerN);
      requete.setBoolean(4, readyN);
      requete.setInt(5, idGame);
      requete.executeUpdate();
      requete.close();
      /*reste la verif a faire*/
    } catch(SQLException e ){
      System.err.println("Erreur requete majGame: " + e.getMessage());
    }
    return inserted;
  }

}
