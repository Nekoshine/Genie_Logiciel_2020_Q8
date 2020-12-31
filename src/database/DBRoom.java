package database;

import model.Game;
import model.GameList;
import model.Room;
import model.RoomList;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/*Codé par Yann, c'était pour tester le bon fonctionnement des visuels avec les données de la BDD
* Code sujet à modification
* */

public class DBRoom extends DBConnexion{

    public DBRoom(){
        super.getConnexion();
    }

    /**
     * Fonction qui va récupérer les jeux dans la base de données et le stocker dans un ArrayList
     * @return Liste de jeux
     */
    public static RoomList getRooms(int idUser){
        RoomList roomList = new RoomList();
        try{
            PreparedStatement requete = DBGame.getConnexion().prepareStatement("SELECT * FROM Room, Game WHERE Room.idGame=Game.id AND Game.idUser = ? ORDER BY Room.id ASC");
            requete.setString(1, String.valueOf(idUser));
            ResultSet resultat = requete.executeQuery();
            while (resultat.next() != false) { // On itère chaque résultat
                roomList.addRoom(new Room(resultat.getInt("Room.id"),resultat.getInt("Game.id"),resultat.getString("titre")
                        ,resultat.getInt("score")
                        ,idUser
                        ,resultat.getInt("timer")
                        , Boolean.valueOf(resultat.getString("ready"))));

            }
            //for (int i=0;i<roomList.getSize() ;i++ ) {         System.out.println(roomList.getRoom(i).getId()); System.out.println(roomList.getRoom(i).getGame());       }
            requete.close();
            resultat.close();
        } catch(SQLException e ){
            System.err.println("Erreur requete connectUser: " + e.getMessage());
        }
        return roomList;
    }

    public static boolean insertRoom(int id,int idGame) {
        boolean inserted=false;
        try{
            PreparedStatement requetePresence = DBUser.getConnexion().prepareStatement("SELECT * FROM Room WHERE id=? AND idGame=?"); // On regarde si la salle n'est pas deja dans la BDD
            requetePresence.setString(1, String.valueOf(id));
            requetePresence.setString(2, String.valueOf(idGame));
            ResultSet resultatPresence = requetePresence.executeQuery();
            if(resultatPresence.next() != false){ // Si il est deja dans la bdd
                inserted=false; //Alors on annule l'insertion
                requetePresence.close();
                resultatPresence.close();
                return inserted;
            }else{
                requetePresence.close();
                resultatPresence.close();
                PreparedStatement requete = DBUser.getConnexion().prepareStatement("INSERT INTO Room VALUES (?,?)");
                requete.setString(1, String.valueOf(id));
                requete.setString(2, String.valueOf(idGame));
                requete.executeUpdate();
                requete.close();
                PreparedStatement requeteVerif = DBUser.getConnexion().prepareStatement("SELECT * FROM Room WHERE id=? AND idGame=?");  // On regarde si l'insertion a fonctionné
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
}
