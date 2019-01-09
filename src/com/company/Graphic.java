package com.company;

import javax.swing.*;
import java.awt.*;

class Graphic {
    Frame f = new Frame("Connect 5");
}


class Frame extends JFrame {
    Frame(String title) throws HeadlessException {
        super(title);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));

        pack();

        Panel p = new Panel();
        add(p);

        Insets i = getInsets();

        setPreferredSize(new Dimension(500 + (i.left + i.right),500 + (i.top + i.bottom)));
        pack();

        setVisible(true);
    }
}

class Panel extends JPanel {
    Panel() {
        setSize(500, 500);
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 500, 500);

        g.setColor(Color.WHITE);

        for(int x = 50; x < 450; x += 50) {
            for(int y = 50;  y < 450; y += 50) {
                g.drawRect(x, y, 50, 50);
            }
        }
    }
}

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
        return "("+name + " as " + letter + " )";
    }
}

class HumanPlayer extends Player {
    public HumanPlayer(String name, char letter) {
        super(name, letter);
    }

    @Override
    public Move getMove(Board board) {
        return null;
    }

    @Override
    public Player freshCopy() {
        return null;
    }
}

class RandomComputer extends Player {
    public RandomComputer(String name, char letter) {
        super(name, letter);
    }
    @Override
    public Move getMove(Board board) {
        return null;
    }

    @Override
    public Player freshCopy() {
        return null;
    }
}

class Location {
    int x, y , z;

    public Location(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
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
}

class Board {
    public static final char EMPTY = '-', RED = 'R', BLUE = 'B', PLAYING = '-', TIE = 'T';
    public static final int X_SIZE=8,Y_SIZE=7, Z_SIZE = 8;
    private char[][][] board;
    private char winner;

    public Board() {
        board = new char[8][7][8];
        for (int x = 0; x < 8; x++) {
            for(int y = 0; y < 7; y++) {
                for(int z = 0; z < 8; z++) {
                    board[z][y][x] = EMPTY;
                }
            }
        }

        winner = PLAYING;
    }

    public char[][][] getBoard() {
        return board;
    }

    public void setBoard(char[][][] board) {
        this.board = board;
    }

    public boolean makeMove(Move move, char p) {
        return true;
    }

    public void setLocation(Location l, char p) {
        board[l.getZ()][l.getY()][l.getX()]=p;
    }

    public char getLocation() {
        return ' ' ;
    }

    public char getWinner(){
        return winner;
    }

    public boolean isFull(Move m) {
        return false;
    }

    public void reset() {

    }

    public void draw() {

    }
}

class Move {
    private int row;
    private int col;

    public Move(int col, int row) {
        this.col=col;
        this.row=row;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public String toString() {
        return "(" + row + ",  " + col + ")";
    }
}


