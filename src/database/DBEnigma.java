package database;

import model.Enigma;
import model.EnigmaList;
import model.Room;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBEnigma extends DBConnexion{

    public DBEnigma(){
        super.getConnexion();
    }

    public  static EnigmaList getEnigmas(int idGame){
        EnigmaList enigmaList = new EnigmaList();
        try{
            PreparedStatement requete = DBGame.getConnexion().prepareStatement("Select * from Enigma WHERE idGame = ?");
            requete.setString(1, String.valueOf(idGame));
            ResultSet resultat = requete.executeQuery();
            while (resultat.next() != false) { // On itère chaque résultat
                enigmaList.addEnigma(
                        new Enigma(resultat.getInt("id"),resultat.getString("text"),resultat.getString("answer"),
                                resultat.getString("clue1"),resultat.getInt("timer1"),
                                resultat.getString("clue2"),resultat.getInt("timer2"),
                                resultat.getString("clue3"),resultat.getInt("timer3")));
                // On crée l'objet model.Game et on l'ajoute dans la liste
            }
            for (int i=0;i<enigmaList.getSize() ;i++ ) {
                System.out.println(enigmaList.getEnigma(i).getId());
                System.out.println(enigmaList.getEnigma(i).getText());
                System.out.println(enigmaList.getEnigma(i).getAnswer());
            }
            requete.close();
            resultat.close();
        } catch(SQLException e ){
            System.err.println("Erreur requete connectUser: " + e.getMessage());
        }
        return enigmaList;
    }
}

