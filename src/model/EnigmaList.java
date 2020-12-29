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

    public int getSize(){
        return List.size();
    }


}
