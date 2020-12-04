package model;

import controller.DBUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DBUserTest {

    private DBUser DB=null;

    @BeforeEach
    void initDB(){
        DB = new DBUser();
        DB.getConnexion();
    }

    @AfterEach
    void decoDB(){
        DB=null ;
    }

    @Test
    void testinsertUser() {
        DBUser DB = new DBUser();
        DB.getConnexion();
        String login="Jesuisuntest";
        String password="Jesuislemdpdutest";
        boolean test = DB.insertUser(login,password);
        Assertions.assertTrue(test);
    }

    @Test
    void connectUser() {
        String login="Jesuisuntest";
        String password="Jesuislemdpdutest";
        boolean test = DB.connectUser(login,password);
        Assertions.assertTrue(test);
    }

    @Test
    void doubleInsertion(){
        DBUser DB = new DBUser();
        DB.getConnexion();
        String login="testtest";
        String password="test";
        DB.insertUser(login,password);
        boolean test = DB.insertUser(login,password);
        Assertions.assertFalse(test);
    }
}
