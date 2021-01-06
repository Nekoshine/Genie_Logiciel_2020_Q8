package model;

public class Room {

    private int id;
    private Game Game;
    private Boolean occuped = false;

    public Room(int id, Game Game){
        this.id = id;
        this.Game = Game;
    }

    public Room(int id, int idGame,String titre,int score,int idUser,int timer,Boolean ready){
        this.id = id;
        this.Game = new Game(idGame,titre,score,idUser,timer,ready);
    }

    public int getId(){
        return this.id;
    }
    public Game getGame(){
        return this.Game;
    }

    public void setGame(model.Game game) {
        Game = game;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getOccuped() {
        return occuped;
    }
}
