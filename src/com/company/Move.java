package com.company;

class Move {
    private int z;
    private int x;

    public Move(int col, int depth) {
        this.x =col;
        this.z =depth;
    }

    public int getZ() {
        return z;
    }

    public int getX() {
        return x;
    }

    @Override
    public String toString() {
        return "(" + z + ",  " + x + ")";
    }
}
