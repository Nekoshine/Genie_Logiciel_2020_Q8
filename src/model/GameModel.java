package model;

import java.util.ArrayList;

/**
 * * Codé par Yann
 */
public class GameModel {

    public ArrayList<Game> List;

    public GameModel(){
        List = new ArrayList<>();
    }

    public void addGame(int id,String titre,int score,int idUser,int timer,Boolean ready){
        List.add(new Game(id,titre,score,idUser,timer,ready));
    }

    public int getSize(){
        return List.size();
    }

    public void addGame(Game game) {
        List.add(game);
    }

    public Game getGame(int i){
        return List.get(i);
    }

}
