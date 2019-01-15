package com.company;


class RandomComputer extends Player {
    public RandomComputer(String name, char letter) {
        super(name, letter);
    }
    @Override
    public Move getMove(Board board) {
        return new Move((int)(Math.random()*8), (int)(Math.random()*8));
    }

    @Override
    public Move getMove(Board board, int x, int z) {
        return new Move((int)(Math.random()*8), z);
    }

    @Override
    public Player freshCopy() {
        return new RandomComputer(getName(), getLetter());
    }
}

