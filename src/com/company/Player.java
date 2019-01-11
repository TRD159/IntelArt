package com.company;

abstract class Player{
    private String name;
    private char letter;

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

    public abstract Move getMove(Board board);

    public abstract Player freshCopy();

    @Override
    public String toString() {
        return "("+name + " as " + letter + ")";
    }
}

