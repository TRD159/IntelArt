package com.company;


import java.awt.*;

class Location {
    int x, y, z;
    Color b, r;

    public Location(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;

        try {
            r = new Color(255, 0, 35 * z);
            b = new Color(0, 35 * z, 255);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "(" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ")";
    }

    public Color getB() {
        return b;
    }

    public Color getR() {
        return r;
    }
}
