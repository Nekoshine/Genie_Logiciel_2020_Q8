package database;

import model.Room;
import model.RoomList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DBRoomTest {
    private final static int idGame = 2;

    @Test
    final void testInsertRoom() {
        boolean test = DBRoom.insertRoom(DBRoom.getMax()+1,idGame,true,-1);
        Assertions.assertTrue(test, "Echec de l'ajout de la room");
    }

    @Test
    final void testgetMax() {
        boolean test=false;
        int max = DBRoom.getMax();
        int maxBDD = 13; //à lire direct dans la bdd
        if(max==maxBDD){
            test = true;
        }
        Assertions.assertTrue(test, "Echec recuperation max");
    }

    @Test
    final void testGetRooms() {
        int id = DBRoom.getMax()+1;
        boolean test=false;
        boolean insert = DBRoom.insertRoom(id,idGame,true,-1);
        if(insert){
            RoomList listRoom = DBRoom.getRooms(3);
            Room room = listRoom.getRoom(listRoom.getSize()-1);
            if (id== room.getId() && idGame==room.getGame().getId()){
                test=true;
            }
            Assertions.assertTrue(test, "Echec de la lecture");

        }
        else {
            Assertions.assertTrue(insert, "Echec de l'ajout");
        }
    }

    @Test
    final void TestMajJeu(){
        int id = DBRoom.getMax()+1;
        boolean test=false;
        boolean insert = DBRoom.insertRoom(id,idGame,true,-1);
        if(insert) {
            test = DBRoom.majRoom(id, 3,false,-1);
            Assertions.assertTrue(test, "Echec de la mise a jour");
        }
        else {
            Assertions.assertTrue(insert, "Echec de l'ajout");
        }
    }

}