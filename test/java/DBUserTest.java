import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    //@Test
    /*void testinsertUser() {
        DBUser DB = new DBUser();
        DB.getConnexion();
        String login="ydauvin";
        String password="AzertY.123";
        boolean test = DB.insertUser(login,password);
        assertTrue(test);
    }*/

    @Test
    void connectUser() {
        String login="ydauvin";
        String password="AzertY.123";
        boolean test = DB.connectUser(login,password);
        assertTrue(test);
    }

    //@Test
    /*void doubleInsertion(){
        DBUser DB = new DBUser();
        DB.getConnexion();
        String login="ydauvin";
        String password="AzertY.123";
        DB.insertUser(login,password);
        boolean test = DB.insertUser(login,password);
        assertFalse(test);
    }*/
}
