package database;

import model.Room;
import model.RoomList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBRoom extends DBConnexion{

    private DBRoom(){
        super.getConnexion();
    }

    /**
     * Fonction qui va récupérer les jeux d'un utilisateut dans la base de données
     * @return Liste des jeux
     */
    public static RoomList getRooms(int idUser){
        RoomList roomList = new RoomList();
        try{
            PreparedStatement requete = DBConnexion.getConnexion().prepareStatement("SELECT * FROM Room, Game WHERE Room.idGame=Game.id AND Game.idUser = ? ORDER BY Room.id ASC");
            requete.setString(1, String.valueOf(idUser));
            ResultSet resultat = requete.executeQuery();
            while (resultat.next() != false) { // On itère chaque résultat
                roomList.addRoom(new Room(resultat.getInt("Room.id"),resultat.getInt("Game.id"),resultat.getString("titre")
                        ,resultat.getInt("score")
                        ,idUser
                        ,resultat.getInt("timer")
                        , Boolean.valueOf(resultat.getString("ready")),resultat.getString("Game.messageFin")));

            }
            //for (int i=0;i<roomList.getSize() ;i++ ) {         System.out.println(roomList.getRoom(i).getId()); System.out.println(roomList.getRoom(i).getGame());       }
            requete.close();
            resultat.close();
        } catch(SQLException e ){
            System.err.println("Erreur requete getRooms: " + e.getMessage());
        }
        return roomList;
    }

    /**
     * Fonction qui va verifier si une salle est dans la BDD
     * @param id l'identifiant de la salle
     * @param idGame l'identifiant du jeu associé
     * @return true si la salle est dans la BDD
     */
    public static boolean isInDB(int id, int idGame){
        boolean isHere = false;
        try {
            PreparedStatement requetePresence = DBConnexion.getConnexion().prepareStatement("SELECT * FROM Room WHERE id=? AND idGame=?");
            requetePresence.setString(1, String.valueOf(id));
            requetePresence.setString(2, String.valueOf(idGame));
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

    /**
     * Fonction qui va inserer une salle dans la BDD
     * @param id l'identifiant de la salle
     * @param idGame le jeu associé
     * @return true si la salle a été inséré
     */
    public static boolean insertRoom(int id,int idGame) {
        boolean inserted=false;
        try{
            PreparedStatement requetePresence = DBConnexion.getConnexion().prepareStatement("SELECT * FROM Room WHERE id=? AND idGame=?"); // On regarde si la salle n'est pas deja dans la BDD
            requetePresence.setString(1, String.valueOf(id));
            requetePresence.setString(2, String.valueOf(idGame));
            ResultSet resultatPresence = requetePresence.executeQuery();
            if(resultatPresence.next() != false){ // Si il est deja dans la bdd
                inserted=false; //Alors on annule l'insertion
                requetePresence.close();
                resultatPresence.close();
            }else{
                requetePresence.close();
                resultatPresence.close();
                PreparedStatement requete = DBConnexion.getConnexion().prepareStatement("INSERT INTO Room VALUES (?,?)");
                requete.setString(1, String.valueOf(id));
                requete.setString(2, String.valueOf(idGame));
                requete.executeUpdate();
                requete.close();

                PreparedStatement requeteVerif = DBConnexion.getConnexion().prepareStatement("SELECT * FROM Room WHERE id=? AND idGame=?");  // On regarde si l'insertion a fonctionné
                requeteVerif.setString(1, String.valueOf(id));
                requeteVerif.setString(2, String.valueOf(idGame));
                ResultSet resultatVerif = requeteVerif.executeQuery();
                if(resultatVerif.next() != false){ // Si il a été inséré
                    inserted=true; // Alors on valide l insertion
                }
                resultatVerif.close();
                requeteVerif.close();
            }
        }catch(SQLException e ){
            System.err.println("Erreur requete insertRoom: " + e.getMessage());
        }
        return inserted;

    }

    /**
     * Fonction qui change le jeu d'une salle dans la BDD
     * @param id l'identifiant de la salle
     * @param idGame le nouveau jeu
     * @return true si la mise à jour a fonctionné
     */
    public static boolean majGame(int id, int idGame){
        boolean inserted = false;
        try{
            PreparedStatement requete = DBConnexion.getConnexion().prepareStatement("UPDATE Room SET idGame=? WHERE id=?");
            requete.setString(1, String.valueOf(idGame));
            requete.setString(2, String.valueOf(id));
            requete.executeUpdate();
            requete.close();

            PreparedStatement requeteVerif = DBConnexion.getConnexion().prepareStatement("SELECT * FROM Room WHERE id=? AND idGame=?");
            requeteVerif.setString(1, String.valueOf(id));
            requeteVerif.setString(2, String.valueOf(idGame));
            ResultSet resultatVerif = requeteVerif.executeQuery();
            if(resultatVerif.next() != false){ // Si il a été inséré
                inserted=true; // Alors on valide l insertion
            }
            resultatVerif.close();
            requeteVerif.close();

        } catch(SQLException e ){
            System.err.println("Erreur requete majGame: " + e.getMessage());
        }
        return inserted;
    }

    /**
     * Fonction qui renvoit l'id le plus grand dans la BDD
     * @return le plus grand id
     */
    public static int getMax() {
        int max=0;
        try {
            PreparedStatement requete = DBConnexion.getConnexion().prepareStatement("SELECT Max(id) FROM Room");
            ResultSet resultat = requete.executeQuery();
            if (resultat.next()!=false) {
                max = resultat.getInt("max(id)");
            }
            requete.close();
            resultat.close();
            return max;
        } catch (SQLException e) {
            System.err.println("Erreur requete getMax: " + e.getMessage());
        }
        return max;
    }

}
