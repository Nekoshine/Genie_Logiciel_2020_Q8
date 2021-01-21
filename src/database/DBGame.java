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
  
    private DBGame(){
        super.getConnexion();
    }

    /**
    * Fonction qui va supprimer un jeu dans la BDD
    * @param idGame l'identifiant du jeu a supprimer
    * @return true si la suppression à reussi
    */
    public static boolean deleteGame(int idGame){
        boolean boolDelete=false;
        try{
            PreparedStatement reqRoom = DBConnexion.getConnexion().prepareStatement("SELECT * from Room WHERE idGame = ?");
            reqRoom.setInt(1,idGame);
            ResultSet resRoom = reqRoom.executeQuery();
            if(resRoom.next()){ // Si il est dans une salle
                return false; // Alors on arrete tout
            }

            PreparedStatement req = DBConnexion.getConnexion().prepareStatement("SELECT * from Enigma WHERE idGame = ?");
            req.setInt(1, idGame);
            ResultSet res = req.executeQuery();
            while (res.next() != false) { // On itère chaque résultat
                DBEnigma.deleteEnigma(res.getInt("id"));
            }

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
    * Fonction qui va récupérer les jeux d'un utilisateur dans la base de données
    * @param idUser l'identifiant de l'utilisateur
    * @return Liste de jeux
    */
    public static GameList getGames(int idUser){
        GameList gameList = new GameList();
        boolean boolGame;
        try{
            PreparedStatement requete = DBConnexion.getConnexion().prepareStatement("Select * from Game WHERE idUser=? ORDER BY id ASC");
            requete.setString(1, String.valueOf(idUser));
            ResultSet resultat = requete.executeQuery();
            while (resultat.next() != false) { // On itère chaque résultat
                if(resultat.getInt("ready")==1){ // On convertit le booleen car il est stocké comme un entier dans la base
                    boolGame=true;
                }
                else{
                    boolGame=false;
                }
                gameList.addGame(new Game(resultat.getInt("id"), resultat.getString("titre"),
                resultat.getInt("score"), resultat.getInt("idUser"),resultat.getInt("timer"), boolGame, resultat.getString("messageFin"))); // On crée l'objet model.Game et on l'ajoute dans la liste
            }
            requete.close();
            resultat.close();
        } catch(SQLException e ){
            System.err.println("Erreur requete getGames: " + e.getMessage());
        }
        return gameList;
    }

    /**
     * insertion d'un jeu, on insere un jeu grace aux champs donnés en argument
     * @param titreN titre du jeu
     * @param scoreN score initiale du jeu
     * @param idUserN identifiant de l'utilisateur qui possede le jeu
     * @param timerN
     * @param readyN
     * @return -1 si echec, l'id du jeu sinon
     */
    public static int insertGame(String titreN,int scoreN,int idUserN,int timerN,Boolean readyN,String message){
        int inserted = -1;
        int valueReady=0;
        try{
            PreparedStatement requete = DBConnexion.getConnexion().prepareStatement("Insert into Game VALUES (default,?,?,?,?,?,?)");
            requete.setInt(1,idUserN);
            requete.setString(2,titreN);
            requete.setInt(3,scoreN);
            requete.setInt(4,timerN);
            requete.setBoolean(5,readyN);
            requete.setString(6,message);
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
                inserted= resultatVerif.getInt("id"); // Alors on valide l insertion
            }
            resultatVerif.close();
            requeteVerif.close();
        } catch(SQLException e ){
            System.err.println("Erreur requete insertGame: " + e.getMessage());
        }
        return inserted;
    }

    /**
    * Fonction qui va verifier si un jeu est dans la BDD
    * @param id l'identifiant du jeu recherché
    * @return true si le jeu est dans la BDD
    */
    public static boolean isInDB(int id){
        boolean isHere = false;
        try {
            PreparedStatement requetePresence = DBConnexion.getConnexion().prepareStatement("SELECT * FROM Game WHERE id=?");
            requetePresence.setInt(1, id);
            ResultSet resultatPresence = requetePresence.executeQuery();
            if(resultatPresence.next() != false){ // Si il est deja dans la bdd
                isHere=true;
            }
            requetePresence.close();
            resultatPresence.close();
        }
        catch(SQLException e ){
            System.err.println("Erreur requete isInDB: " + e.getMessage());
        }
        return isHere;
    }

    /**
     * Fonction qui donne le titre d'un jeu a l'aide son identifiant
     * @param idGame l'idantifiant du jeu
     * @return le titre du jeu
     */
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


    /**
     * Fonction qui met a jour un jeu dans la bdd
     * @param idGame l'identifiant du jeu a modifier
     * @param titreN le nouveau titre
     * @param scoreN le nouveau score initial
     * @param timerN
     * @param readyN
     * @return
     */
    public static boolean majGame(int idGame, String titreN,int scoreN,int timerN,Boolean readyN, String message){
        boolean inserted = false;
        try{
            PreparedStatement requete = DBConnexion.getConnexion().prepareStatement("UPDATE Game SET titre=?, score=?, timer=?, ready=?, messageFin=? WHERE id=?");
            requete.setString(1, titreN);
            requete.setInt(2, scoreN);
            requete.setInt(3, timerN);
            requete.setBoolean(4, readyN);
            requete.setString(5, message);
            requete.setInt(6, idGame);
            requete.executeUpdate();
            requete.close();
            /*reste la verif a faire*/
            inserted = true;
        } catch(SQLException e ){
            System.err.println("Erreur requete majGame: " + e.getMessage());
        }
        return inserted;
    }

    /**
     * Fonction qui va recupérer un jeu dans la BDD
     * @param id l'identifiant du jeu
     * @return le jeu
     */
    public static Game getGame(int id){
        Game game = null;
        Boolean boolGame=false;
        try{
            PreparedStatement requete = DBConnexion.getConnexion().prepareStatement("Select * from Game WHERE id=?");
            requete.setString(1, String.valueOf(id));
            ResultSet resultat = requete.executeQuery();
            while (resultat.next() != false) { // On itère chaque résultat
                if(resultat.getInt("ready")==1){ // On convertit le booleen car il est stocké comme un entier dans la base
                    boolGame=true;
                }
                else{
                    boolGame=false;
                }
                game = new Game(resultat.getInt("id"), resultat.getString("titre"),
                resultat.getInt("score"), resultat.getInt("idUser"),resultat.getInt("timer"),boolGame, resultat.getString("messageFin")); // On crée l'objet model.Game et on l'ajoute dans la liste
            }
            // for (int i=0;i<gameList.getSize() ;i++ ) {         System.out.println(gameList.getGame(i).getTitre());       }
            requete.close();
            resultat.close();
        } catch(SQLException e ){
            System.err.println("Erreur requete getGames: " + e.getMessage());
        }
        return game;
    }


}
