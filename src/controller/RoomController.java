package controller;

import model.RoomModel;


import java.util.ArrayList;

public class RoomController {
    public ArrayList<RoomModel> RoomList;

    public RoomController(){
        RoomList=new ArrayList<RoomModel>();
        RoomList.add(new RoomModel(1,"Titre jeu 1"));
        RoomList.add(new RoomModel(2,"Titre du jeu 2"));
        RoomList.add(new RoomModel(3,"Titre du jeu 3"));
        RoomList.add(new RoomModel(4,"Titre du jeu 4"));
        //devra recuperer les info du modele
    }

    public ArrayList<RoomModel> getRoomList(){
        return this.RoomList;
    }

    public void addRoom(int id, String Game){
        RoomList.add(new RoomModel(id,Game));
        //ajouter a la bdd
    }
}
