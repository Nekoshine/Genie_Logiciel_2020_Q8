package model;
/**
* * Codé par Esteban
*/

public class Enigma {
  private int id;
  private String question;
  private String answer;
  private Hint clue1;
  private Hint clue2;
  private Hint clue3;

  public Enigma(int id, String question, String answer, Hint clue1, Hint clue2, Hint clue3) {
    this.id = id;
    this.question = question;
    this.answer = answer;
    this.clue1 = clue1;
    this.clue2 = clue2;
    this.clue3 = clue3;
  }

}