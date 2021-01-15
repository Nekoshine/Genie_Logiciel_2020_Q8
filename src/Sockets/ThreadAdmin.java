package Sockets;

public class ThreadAdmin extends Thread {

    private int idUserAdmin;

    public ThreadAdmin(int idUserAdmin){
        this.idUserAdmin= idUserAdmin;
    }

    public void run(){
        Admin.setServerAdmin(idUserAdmin);
    }

}
