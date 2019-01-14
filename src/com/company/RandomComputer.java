package com.company;


class RandomComputer extends Player {
    public RandomComputer(String name, char letter) {
        super(name, letter);
    }
    @Override
    public Move getMove(Board board) {
        return null;
    }

    @Override
    public Move getMove(Board board, int x, int z) {
        return null;
    }

    @Override
    public Player freshCopy() {
        return new RandomComputer(getName(), getLetter());
    }
}

