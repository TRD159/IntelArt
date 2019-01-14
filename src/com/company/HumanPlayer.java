package com.company;

import java.util.Scanner;

class HumanPlayer extends Player {
    public HumanPlayer(String name, char letter) {
        super(name, letter);
    }

    @Override
    public Move getMove(Board board) {
        return null;
    }

    @Override
    public Move getMove(Board board, int x, int z) {
        return new Move(x, z);
    }

    @Override
    public Player freshCopy() {
        return new HumanPlayer(getName(), getLetter());
    }
}
