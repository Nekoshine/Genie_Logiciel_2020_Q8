package Sockets;

import model.Room;

import java.io.Serializable;

public class FinPartie implements Serializable {

    private String login;
    private Room salle;

    public FinPartie(String login,Room salle){
        this.login = login;
        this.salle=salle;
    }

    public Room getIdSalle() {
        return salle;
    }

    public String getLogin() {
        return login;
    }
}
