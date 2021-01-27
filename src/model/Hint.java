package model;

/**
 * Représentation d'un indice
 */
public final class Hint {
    private String clue;
    private int timer;

    public Hint(String clue,int timer){
        this.clue=clue;
        this.timer=timer;
    }

    String getClue() {
        return this.clue;
    }

    int getTimer() {
        return this.timer;
    }

}
