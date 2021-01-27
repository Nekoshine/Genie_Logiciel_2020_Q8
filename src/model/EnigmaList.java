package model;

import java.util.ArrayList;

/**
 * Liste d'enigmes
 */
public final class EnigmaList {

    public ArrayList<Enigma> List;

    public EnigmaList() {
        this.List = new ArrayList<>();
    }

    public void addEnigma(Enigma enigma) {
        this.List.add(enigma);
    }

    public void addEnigma(int id, String text, String answer, String clue1, int timer1, String clue2, int timer2, String clue3, int timer3) {
        this.List.add(new Enigma(id, text, answer, clue1, timer1, clue2, timer2, clue3, timer3));
    }

    public Enigma getEnigma(int i) {
        return this.List.get(i);
    }

    public void removeEnigma(int i) {
        this.List.remove(i);
    }

    public int getSize() {
        return this.List.size();
    }

    public Enigma findByID(int i){
        Enigma enigma = null;
        int size = this.getSize();
        for(int j = 0; j<size; j++){
            if (this.getEnigma(j).getId()==i){
                enigma=this.getEnigma(j);
            }
        }
        return enigma;
    }

    public Enigma getLastEnigma() {
        int nb = this.getSize();

        return this.getEnigma(nb-1);
    }
}
