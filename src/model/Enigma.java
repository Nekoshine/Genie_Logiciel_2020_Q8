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
  public Enigma(int id,int tampon, String question, String answer, String clue1, int timer1,String clue2, int timer2,String clue3, int timer3) {
    this.id = id;
    this.question = question;
    this.answer = answer;
    this.clue1 = new Hint(clue1,timer1);
    this.clue2 = new Hint(clue2,timer2);
    this.clue3 = new Hint(clue3,timer3);
  }

  public Enigma(int id, String question, String answer, String clue1, int timer1,String clue2, int timer2,String clue3, int timer3) {
    this.id = id;
    this.question = question;
    this.answer = answer;
    this.clue1 = new Hint(clue1,timer1);
    this.clue2 = new Hint(clue2,timer2);
    this.clue3 = new Hint(clue3,timer3);
  }

  public String getText(){
    return this.question;
  }

  public String getClue1(){
    return this.clue1.getClue();
  }

  public int getTimer1() {
    return this.clue1.getTimer();
  }

  public String getClue2(){
    return this.clue2.getClue();
  }

  public int getTimer2() {
    return this.clue2.getTimer();
  }

  public String getClue3(){
    return this.clue3.getClue();
  }

  public int getTimer3() {
    return this.clue3.getTimer();
  }

  public String getAnswer() {
    return this.answer;
  }

  public int getId() {
    return this.id;
  }

  public void setClue1(Hint clue1) {
    this.clue1 = clue1;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public void setClue2(Hint clue2) {
    this.clue2 = clue2;
  }

  public void setClue3(Hint clue3) {
    this.clue3 = clue3;
  }

  public void setId(int id) {
    this.id = id;
  }
}