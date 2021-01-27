package database;
import model.Score;
import model.ScoreList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBScore extends DBConnexion {

    private DBScore(){
        super.getConnexion();
    }

    /**
    * Fonction qui va supprimé des scores
     */
    public static boolean deleteScore(int idGameN, int idUserN){
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
            System.err.println("Erreur requete deleteScore: " + e.getMessage());
        }
        return boolDelete;
    }


    /**
     * Fonction qui va récupérer les scores d'un jeu dans la base de données et le stocker dans un ArrayList
     * @return Liste de jeux
     */
    public static ScoreList getScoreFromGame(int idGame){
        ScoreList scoreList = new ScoreList();
        try{
            PreparedStatement requete = DBConnexion.getConnexion().prepareStatement("Select * from Score WHERE idGame=? ORDER BY score DESC");
            requete.setString(1, String.valueOf(idGame));
            ResultSet resultat = requete.executeQuery();
            while (resultat.next()) { // On itère chaque résultat
                scoreList.addScore(new Score(resultat.getInt("id"), resultat.getInt("idGame"),
                        resultat.getInt("idUser"), resultat.getInt("score"))); // On crée l'objet model.Score et on l'ajoute dans la liste
            }
            requete.close();
            resultat.close();
        } catch(SQLException e ){
            System.err.println("Erreur requete getScore: " + e.getMessage());
        }
        return scoreList;
    }

    /**
     * Fonction qui va récupérer les scores d'un jeu dans la base de données et le stocker dans un ArrayList
     * @return Liste de jeux
     */
    public static ScoreList getScoreFromUser(int idUser){
        ScoreList scoreList = new ScoreList();
        try{
            PreparedStatement requete = DBConnexion.getConnexion().prepareStatement("Select * from Score WHERE idUser=? ORDER BY score DESC");
            requete.setString(1, String.valueOf(idUser));
            ResultSet resultat = requete.executeQuery();
            while (resultat.next()) { // On itère chaque résultat
                scoreList.addScore(new Score(resultat.getInt("id"), resultat.getInt("idGame"),
                        resultat.getInt("idUser"), resultat.getInt("score"))); // On crée l'objet model.Score et on l'ajoute dans la liste
            }
            requete.close();
            resultat.close();
        } catch(SQLException e ){
            System.err.println("Erreur requete getScore: " + e.getMessage());
        }
        return scoreList;
    }

    public static boolean insertScore(Score score) {
        boolean inserted = false;
        try{
            PreparedStatement requete = DBConnexion.getConnexion().prepareStatement("Insert into Score VALUES (default,?,?,?)");
            requete.setInt(1,score.getIdUser());
            requete.setInt(2,score.getIdGame());
            requete.setInt(3,score.getScore());
            requete.executeUpdate();
            requete.close();
            PreparedStatement requeteVerif = DBConnexion.getConnexion().prepareStatement("Select * from Score where score=? and idUser=? and idGame=?");  // On regarde si le score à bien été inséré
            requeteVerif.setInt(1,score.getScore());
            requeteVerif.setInt(2,score.getIdUser());
            requeteVerif.setInt(3,score.getIdGame());
            ResultSet resultatVerif = requeteVerif.executeQuery();
            if(resultatVerif.next()){ // Si il a été inséré
                inserted=true; // Alors on valide l insertion
            }
            resultatVerif.close();
            requeteVerif.close();
        } catch(SQLException e ){
            System.err.println("Erreur requete insertScore: " + e.getMessage());
        }
        return inserted;
    }
}
