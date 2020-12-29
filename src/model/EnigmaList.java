package model;

// Codé par Yann

import java.util.ArrayList;

public class EnigmaList {

    public ArrayList<Enigma> List;

    public EnigmaList(){
        List = new ArrayList<>();
    }

    public void addEnigma(Enigma enigma){
        List.add(enigma);
    }

    public void addEnigma(int id,int idUser,String text,String answer,String clue1,int timer1,String clue2,int timer2,String clue3,int timer3){
        List.add(new Enigma(id,idUser,text,answer,clue1,timer1,clue2,timer2,clue3,timer3));
    }

    public Enigma getEnigma(int i){
        return List.get(i);
    }

    public void removeEnigma(int i){List.remove(i);}


    public int getSize(){
        return List.size();
    }


}
