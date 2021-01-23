package database;

import model.Enigma;
import model.EnigmaList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DBEnigmaTest {
    private static final int idGame = 9;
    private static final String text = "EnigmeTest";
    private static final String answer = "ReponseTest";
    private static final String clue1 = "Indice1";
    private static final int timer1 = 10;
    private static final String clue2 = "Indice 2";
    private static final int timer2 = 20;
    private static final String clue3 = null;
    private static final int timer3 = -1;


    @Test
    final void testInsertEnigma() {
        boolean test = DBEnigma.insertEnigma(idGame,text,answer, clue1, timer1,clue2, timer2,clue3, timer3);
        Assertions.assertTrue(test, "Echec de l'ajout de l'enigme");
    }

    @Test
    final void testGetEnigma() {
        boolean insert = DBEnigma.insertEnigma(idGame,text,answer, clue1, timer1,clue2, timer2,clue3, timer3);
        if(insert = true) {
            boolean test = false;
            EnigmaList listEnigma = DBEnigma.getEnigmas(idGame);
            Enigma enigma = listEnigma.getEnigma(listEnigma.getSize() - 1);

            boolean sameText = enigma.getText().equals(text);
            boolean sameAnswer = enigma.getAnswer().equals(answer);
            boolean sameClue1 = enigma.getClue1().equals(clue1);
            boolean sameTimer1 = enigma.getTimer1() == timer1;
            boolean sameClue2 = false;
            if (enigma.getClue2() == "") {
                if (clue2 == null) {
                    sameClue2 = true;
                }
            } else {
                sameClue2 = enigma.getClue2().equals(clue2);
            }
            boolean sameTimer2 = enigma.getTimer2() == timer2;
            boolean sameClue3 = false;
            if (enigma.getClue3() == "") {
                if (clue3 == null) {
                    sameClue3 = true;
                }
            } else {
                sameClue3 = enigma.getClue3().equals(clue3);
            }
            boolean sameTimer3 = enigma.getTimer3() == timer3;
            if (sameText && sameAnswer && sameClue1 && sameTimer1 && sameClue2 && sameTimer2 && sameClue3 && sameTimer3) {
                test = true;
            }
            Assertions.assertTrue(test, "L'enigme récupéré n'est pas la même");
        }
        else{
            Assertions.assertTrue(insert, "Echec de l'ajout de l'enigme");
        }
    }

    //public  static EnigmaList getEnigmas(int idGame){
    //      public static boolean {
}