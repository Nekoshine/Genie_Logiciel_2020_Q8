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
        boolean test = DBUser.insertUser(DBUserTest.GOOD_LOGIN, DBUserTest.GOOD_PASSWORD);
        Assertions.assertTrue(test, "Echec de l'ajout du couple login/password à la BDD");
    }

    @Test
    final void testConnectGoodUserGoodPassword() {
        boolean test = DBUser.connectUser(DBUserTest.GOOD_LOGIN, DBUserTest.GOOD_PASSWORD);
        Assertions.assertTrue(test, "Le couple login/password n'a pas été trouvé dans la BDD");
    }

    @Test
    final void testConnectGoodUserWrongPassword() {
        boolean test = DBUser.connectUser(DBUserTest.GOOD_LOGIN, DBUserTest.WRONG_PASSWORD);
        Assertions.assertFalse(test, "La connexion a été possible avec le mauvais password");
    }

    @Test
    final void testConnectWrongUserGoodPassword() {
        boolean test = DBUser.connectUser(DBUserTest.WRONG_LOGIN, DBUserTest.GOOD_PASSWORD);
        Assertions.assertFalse(test, "La connexion a été possible avec un password associé à personne");
    }

    @Test
    final void testConnectWrongUserWrongPassword() {
        boolean test = DBUser.connectUser(DBUserTest.WRONG_LOGIN, DBUserTest.WRONG_PASSWORD);
        Assertions.assertFalse(test, "La connexion a été possible avec un login et un password non present dans la BDD");
    }

    @Test
    final void testInjectionCodeAveugle(){
        //fait par Yann
        String login = "GoodLogin\" AND 1; -- ";
        String password ="*/GoodLogin";
        boolean test = DBUser.connectUser(login, password);
        Assertions.assertFalse(test, "");
    }

    @Test
    final void testInjectionCodeAvecConnaissance(){
        //pas fait par Yann
        String login = "GoodLogin\" AND 1; -- ";
        String password ="*/GoodLogin";
        boolean test = DBUser.connectUser(login, password);
        Assertions.assertFalse(test, "");
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
