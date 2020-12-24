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
    public static RoomList getRooms(GameList ListGame){
        RoomList roomList = new RoomList();
        try{
            PreparedStatement requete = DBGame.getConnexion().prepareStatement("Select * from Room");
            ResultSet resultat = requete.executeQuery();
            while (resultat.next() != false) { // On itère chaque résultat
                roomList.addRoom(new Room(resultat.getInt("id"),ListGame.findByID(resultat.getInt("idGame"))));
                         // On crée l'objet model.Game et on l'ajoute dans la liste
            }
            for (int i=0;i<roomList.getSize() ;i++ ) {         System.out.println(roomList.getRoom(i).getId()); System.out.println(roomList.getRoom(i).getGame());       }
            requete.close();
            resultat.close();
        } catch(SQLException e ){
            System.err.println("Erreur requete connectUser: " + e.getMessage());
        }
        return roomList;
    }
}