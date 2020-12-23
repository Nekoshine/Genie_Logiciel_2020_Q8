package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBConnexion {

    private static Connection connexion;

    public DBConnexion(){
        getConnexion();
    }
    /**
     * Fonction qui va initialiser la connexion avec la BDD
     * @return La connexion établie avec la bdd
     */
    public static Connection getConnexion(){
        if (connexion==null){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("Initialising connection to Q8 Database");
                DBConnexion.connexion = DriverManager.getConnection("jdbc:mysql://mysql-nekoshine.alwaysdata.net/nekoshine_gl2020q8",
                        "nekoshine_user", "Q82020GL");
                System.out.println("Connection to Q8 Database complete");
                return connexion;
            } catch ( SQLException e){
                System.out.println("Couldn't connect to Q8 DB :" + e.getMessage());
            }catch ( ClassNotFoundException e){
                System.out.println("Couldn't connect to Q8 DB :" + e.getMessage());
            }
        }
        System.out.println("Getting connexion to Q8 DB");
        return connexion;
    }
}
