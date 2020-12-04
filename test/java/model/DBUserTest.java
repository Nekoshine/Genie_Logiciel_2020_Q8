package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DBUserTest {

    private DBUser DB=null;

    @Test
    void testinsertUser() {
        DBUser DB = new DBUser();
        DB.getConnexion();
        String login="testInsertion";
        String password="password";
        boolean test = DB.insertUser(login,password);
        Assertions.assertTrue(test);
    }

    @Test
    void connectUser() {
        String login="testInsertion";
        String password="password";
        boolean test = DB.connectUser(login,password);
        Assertions.assertTrue(test);
    }

    @Test
    void doubleInsertion(){
        DBUser DB = new DBUser();
        DB.getConnexion();
        String login="testInsertionDouble";
        String password="password";
        DB.insertUser(login,password);
        boolean test = DB.insertUser(login,password);
        Assertions.assertFalse(test);
    }
}
