package com.company;

class Move {
    private int depth;
    private int col;

    public Move(int col, int depth) {
        this.col=col;
        this.depth=depth;
    }

    public int getDepth() {
        return depth;
    }

    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        return "(" + depth + ",  " + col + ")";
    }
}
