package model;

public class Hint {
    private String clue;
    private int timer;

    public Hint(String clue,int timer){
        this.clue=clue;
        this.timer=timer;
    }

    public String getClue() {
        return this.clue;
    }

    public int getTimer() {
        return this.timer;
    }

    public void setClue(String clue) {
        this.clue=clue;
    }

    public void setTimer(int timer) {
        this.timer=timer;
    }
}
