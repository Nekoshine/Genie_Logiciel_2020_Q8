import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnexionBDD{
  public static void main(String[] args) {
    Connection connexion;
    try {
      Class.forName("com.mysql.jdbc.Driver");
      System.out.println("Initialising connection to Q8 Database");
      connexion = DriverManager.getConnection("jdbc:mysql://mysql-nekoshine.alwaysdata.net/nekoshine_gl2020q8",
      "nekoshine_user", "Q82020GL");
      System.out.println("Connection to Q8 Database complete");
      
      //Inserer une valeur
      /*PreparedStatement requete = connexion.prepareStatement("INSERT INTO Test VALUES (?,?)");
      requete.setInt(1, 666);
      requete.setString(2, "Satan");
      requete.executeUpdate();*/
      
      //Recuperer la BDD
      PreparedStatement requete = connexion.prepareStatement("SELECT * FROM Test");
      ResultSet rs = requete.executeQuery();
      while (rs.next()) {
        System.out.print(rs.getString(1));
        System.out.print(" | ");
        System.out.println(rs.getString(2));
      }
      
      requete.close();
    } catch (Exception e) {
      System.err.println("Couldn't connect to DB : " + e.getMessage());
    }
    
    System.out.println("Getting connexion to Q8 database..");
  }
}
