package model;

public class Room {

    private int id;
    private Game Game;

    public Room(int id, Game Game){
        this.id = id;
        this.Game = Game;
    }

    public int getId(){
        return this.id;
    }
    public Game getGame(){
        return this.Game;
    }
}
