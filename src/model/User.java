package model;

/**
 * Représentation d'un utilisateur
 */
public final class User{
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

    public int getId(){
      return this.id;
    }

    public String getLogin(){
      return this.login;
    }

    public void setId(int id) {
      this.id = id;
    }

}
