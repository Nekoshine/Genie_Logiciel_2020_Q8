package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DBUserTest {

    private static final String GOOD_LOGIN = "GoodLogin";
    private static final String WRONG_LOGIN = "WrongLogin";
        private static final String GOOD_PASSWORD = "GoodPassword";
    private static final String WRONG_PASSWORD = "WrongPassword";

    @Test
    final void testInsertUser() {
        boolean test = DBUser.insertUser(GOOD_LOGIN, GOOD_PASSWORD);
        Assertions.assertTrue(test, "Echec de l'ajout du couple login/password à la BDD");
    }

    @Test
    final void testConnectGoodUserGoodPassword() {
        boolean test = DBUser.connectUser(GOOD_LOGIN, GOOD_PASSWORD);
        Assertions.assertTrue(test, "Le couple login/password n'a pas été trouvé dans la BDD");
    }

    @Test
    final void testConnectGoodUserWrongPassword() {
        boolean test = DBUser.connectUser(GOOD_LOGIN, WRONG_PASSWORD);
        Assertions.assertFalse(test, "La connexion a été possible avec le mauvais password");
    }

    @Test
    final void testConnectWrongUserGoodPassword() {
        boolean test = DBUser.connectUser(WRONG_LOGIN, GOOD_PASSWORD);
        Assertions.assertFalse(test, "La connexion a été possible avec un password associé à personne");
    }

    @Test
    final void testConnectWrongUserWrongPassword() {
        boolean test = DBUser.connectUser(WRONG_LOGIN, WRONG_PASSWORD);
        Assertions.assertFalse(test, "La connexion a été possible avec un login et un password non present dans la BDD");
    }

    @Test
    final void testInjectionCode(){
        //fait par Yann
        String login = "GoodLogin' AND 1; -- ";
        String password ="*/GoodLogin";
        boolean test = DBUser.connectUser(login, password);
        Assertions.assertFalse(test, "L'injection a fonctionné");
    }

    @Test
    final void doubleInsertion(){
        String login="TestInsertionDouble";
        String password="passwordTestDoubleInsertion";
        DBUser.insertUser(login,password);
        boolean test = DBUser.insertUser(login,password);
        Assertions.assertFalse(test, "Un login a pu être utilisé 2 fois");
    }
}
