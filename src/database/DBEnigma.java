package database;

import launcher.Main;
import model.Enigma;
import model.EnigmaList;
import model.Room;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBEnigma extends DBConnexion{

    private DBEnigma(){
        super.getConnexion();
    }

    public  static EnigmaList getEnigmas(int idGame){
        EnigmaList enigmaList = new EnigmaList();
        try{
            PreparedStatement requete = DBConnexion.getConnexion().prepareStatement("Select * from Enigma,Game WHERE Enigma.idGame = Game.id AND Enigma.idGame = ? AND Game.idUser = ? ORDER BY Enigma.id ASC");
            requete.setString(1, String.valueOf(idGame));
            requete.setString(2, String.valueOf(Main.idUser));
            ResultSet resultat = requete.executeQuery();
            while (resultat.next() != false) { // On itère chaque résultat
                enigmaList.addEnigma(
                        new Enigma(resultat.getInt("id"),resultat.getString("text"),resultat.getString("answer"),
                                resultat.getString("clue1"),resultat.getInt("timer1"),
                                resultat.getString("clue2"),resultat.getInt("timer2"),
                                resultat.getString("clue3"),resultat.getInt("timer3")));
                // On crée l'objet model.Game et on l'ajoute dans la liste
            }
            /*for (int i=0;i<enigmaList.getSize() ;i++ ) {
                System.out.println(enigmaList.getEnigma(i).getId());
                System.out.println(enigmaList.getEnigma(i).getText());
                System.out.println(enigmaList.getEnigma(i).getAnswer());
            }*/
            requete.close();
            resultat.close();
        } catch(SQLException e ){
            System.err.println("Erreur requete getEnigmas: " + e.getMessage());
        }
        return enigmaList;
    }

    public static boolean insertEnigma(int idGame,String text,String answer, String clue1, int timer1,String clue2, int timer2,String clue3, int timer3){
        boolean inserted = false;
        try{
            PreparedStatement requete = DBConnexion.getConnexion().prepareStatement("Insert into Enigma VALUES (default,?,?,?,?,?,?,?,?,?)");
            requete.setInt(1,idGame);
            requete.setString(2,text);
            requete.setString(3,answer);
            requete.setString(4,clue1);
            requete.setInt(5,timer1);
            requete.setString(6,clue2);
            requete.setInt(7,timer2);
            requete.setString(8,clue3);
            requete.setInt(9,timer3);
            requete.executeUpdate();
            requete.close();
            PreparedStatement requeteVerif = DBConnexion.getConnexion().prepareStatement("Select * from Enigma where text=? and idGame=?");  // On regarde si l'user a bien été inséré
            requeteVerif.setString(1,text);
            requeteVerif.setInt(2,idGame);
            ResultSet resultatVerif = requeteVerif.executeQuery();
            if(resultatVerif.next() != false){ // Si il a été inséré
                inserted=true; // Alors on valide l insertion
            }
            resultatVerif.close();
            requeteVerif.close();
        } catch(SQLException e ){
            System.err.println("Erreur requete insertEnigma: " + e.getMessage());
        }
        return inserted;
    }
}

