package model;

public class User{
  private int id;
  private String login;
  private String pwd;
  public User(int id,String login,String pwd){
    this.id = id;
    this.login = login;
    this.pwd = pwd;
  }
  public int getId(){
    return this.id;
  }
  public String getLogin(){
    return this.login;
  }
  public String getPwd(){
    return this.pwd;
  }
}
