package com.company;

abstract class Player{
    private String name;
    private char letter;
    boolean playing = false;

    public Player(String name, char letter) {
        this.name = name;
        this.letter = letter;
    }

    public String getName() {
        return name;
    }

    public char getLetter() {
        return letter;
    }

    public abstract Move getMove(Board board, int x, int z);

    public abstract Move getMove(Board board);

    public abstract Player freshCopy();

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    @Override
    public String toString() {
        return "("+name + " as " + letter + ")";
    }
}

