package model;
/**
* * Codé par Esteban
*/
public class User{
  private int id;
  private String login;
  private String pwd;
  private boolean isAdmin;
  public User(int id,String login,String pwd,boolean isAdmin){
    this.id = id;
    this.login = login;
    this.pwd = pwd;
    this.isAdmin= isAdmin;
  }
  public boolean getIsAdmin(){
    return this.isAdmin;
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
