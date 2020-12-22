package model;

public class Room {

    private int id;
    private String Game;

    public Room(int id, String Game){
        this.id = id;
        this.Game = Game;
    }

    public int getId(){
        return this.id;
    }
    public String getGame(){
        return this.Game;
    }
}
