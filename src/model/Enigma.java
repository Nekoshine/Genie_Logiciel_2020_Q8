package model;

public class Enigma{
  private int id;
  private int idUser;
  private String text;
  private String answer;
  private String clue1;
  private int timer1;
  private String clue2;
  private int timer2;
  private String clue3;
  private int timer3;
  public Enigma(int id,int idUser,String text,String answer,String clue1,int timer1,String clue2,int timer2,String clue3,int timer3){
    this.id = id;
    this.idUser = idUser;
    this.text = text;
    this.answer =answer;
    this.clue1 = clue1;
    this.timer1 = timer1;
    this.clue2 = clue2;
    this.timer2 = timer2;
    this.clue3 = clue3;
    this.timer3 = timer3;
  }
  public int getId(){
    return this.id;
  }
  public int getIdUSer(){
    return this.idUser;
  }
  public String getText(){
    return this.text;
  }
  public String getAnswer(){
    return this.answer;
  }
  public String getClue1(){
    return this.clue1;
  }
  public int getTimer1(){
    return this.timer1;
  }
  public String getClue2(){
    return this.clue2;
  }
  public int getTimer2(){
    return this.timer2;
  }
  public String getClue3(){
    return this.clue3;
  }
  public int getTimer3(){
    return this.timer3;
  }
}
