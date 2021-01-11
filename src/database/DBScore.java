package database;
import model.Score;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBScore extends DBConnexion {

    public DBScore(){
        super.getConnexion();
    }

    /**
    * Fonction qui va supprimé des scores
     */
    public static boolean deleteGame(int idGameN, int idUserN){
        boolean boolDelete=false;
        try{
            PreparedStatement requete = DBConnexion.getConnexion().prepareStatement("Delete from Score WHERE idGame=? and idUser=? ");
            requete.setInt(1, idGameN);
            requete.setInt(2, idUserN);
            requete.executeUpdate();
            requete.close();
            PreparedStatement requeteVerif = DBConnexion.getConnexion().prepareStatement("Select * from Score where idGame=? and idUser=? ");  // On regarde si le score a bien été supprimé
            requeteVerif.setInt(1, idGameN);
            requeteVerif.setInt(2, idUserN);
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
     * On insert le score d'un utilisateur
     * @param  scoreN a insérer
     * @return      insert correct ou non
     */
    public static boolean insertScore(int scoreN ,int idUserN,int idGameN,Boolean readyN){
        boolean inserted = false;
        int valueReady=0;
        try{
            PreparedStatement requete = DBConnexion.getConnexion().prepareStatement("Insert into Score VALUES (default,?,?,?)");
            requete.setInt(1,idUserN);
            requete.setInt(2,idGameN);
            requete.setInt(3,scoreN);
            if(readyN){
                valueReady=1;
            }
            requete.setInt(5,valueReady);
            requete.executeUpdate();
            requete.close();
            PreparedStatement requeteVerif = DBConnexion.getConnexion().prepareStatement("Select * from Score where score=? and idUser=? and idGame=?");  // On regarde si le score à bien été inséré
            requeteVerif.setInt(1,scoreN);
            requeteVerif.setInt(2,idUserN);
            requeteVerif.setInt(3,idGameN);
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
