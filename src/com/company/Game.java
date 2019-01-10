package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

class Game {
    Player pl1, pl2;
    //This class is going to be the main class for Connect 5 specifically, as I may use this same project to do a few things in my free time.
    Game(String p1, String p2) {
        pl1 = new HumanPlayer(p1, 'R');
        pl2 = new HumanPlayer(p1, 'B');
        Frame f = new Frame("Connect 5");
    }
    Game(String p1) {
        pl1 = new HumanPlayer(p1, 'R');
        pl2 = new RandomComputer("Random Com", 'B');
        Frame f = new Frame("Connect 5");
    }
    Game() {
        pl1 = new RandomComputer("Com 1", 'R');
        pl2 = new RandomComputer("Com 2", 'B');
        Frame f = new Frame("Connect 5");
    }

    public static Board b = new Board();
}


class Frame extends JFrame {
    Frame(String title) throws HeadlessException {
        super(title);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setPreferredSize(new Dimension(500, 500));

        pack();

        Panel p = new Panel();
        add(p);

        Insets i = getInsets();

        setPreferredSize(new Dimension(500 + (i.left + i.right),500 + (i.top + i.bottom)));
        pack();

        setVisible(true);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Click");
            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                super.mouseWheelMoved(e);
                System.out.println(e.getWheelRotation());
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                System.out.println(e.getX());
                System.out.println(e.getY());
            }
        });
    }
}

class Panel extends JPanel {
    Panel() {
        setSize(500, 500);


    }

    public void paint(Graphics g) {
        /*g.setColor(Color.BLACK);
        g.fillRect(0, 0, 500, 500);

        g.setColor(Color.WHITE);

        for(int x = 50; x < 450; x += 50) {
            for(int y = 50;  y < 450; y += 50) {
                g.drawRect(x, y, 50, 50);
            }
        }*/
        Game.b.draw(g);
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
        return "("+name + " as " + letter + ")";
    }
}

class HumanPlayer extends Player {
    public HumanPlayer(String name, char letter) {
        super(name, letter);
    }

    @Override
    public Move getMove(Board board) {
        Scanner s = new Scanner(System.in);

        int z, x;
        while(true) {
            System.out.println("Where will you drop your piece?\nEnter the row and column of the space where you will move.");

            z = s.nextInt() - 1;
            x = s.nextInt() - 1;

            if((z >= 0 && z <= 7) && board.getBoard()[z][6][x] == '-')
                break;
            else
                System.out.println("That move isn't valid!");
        }

        return new Move(x, z);
    }

    @Override
    public Player freshCopy() {
        return new HumanPlayer(getName(), getLetter());
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
        return new RandomComputer(getName(), getLetter());
    }
}

class Location {
    int x, y, z;

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
        reset();

    }

    public char[][][] getBoard() {
        return board;
    }

    public void setBoard(char[][][] board) {
        this.board = board;
    }

    public boolean makeMove(Move move, char p) { //This returns the location of the piece, not a boolean. It'll make win checks easier later on. -RK
        int x = move.getCol();
        int y = 0;
        int z = move.getDepth();

        falseCheck :
        {
            for (; y < 7; y++) {
                if (board[z][y][x] == EMPTY) {
                    break falseCheck;
                }
            }

            return false;
        }

        Location l = new Location(x, y, z);
        setLocation(l, p);
        return true;
    }

    public void setLocation(Location l, char p) {
        board[l.getZ()][l.getY()][l.getX()]=p;
    }

    public char getLocation(Location l) {
        return board[l.z][l.y][l.x];
    }

    public char getLocation(int x, int y, int z) {
        return board[z][y][x];
    }


    public char getWinner(){
        return winner;
    }

    public boolean isFull(Move m) {
        /*Firstly, the way you access the array is wrong. You're going YXZ, but it's supposed to be ZYX.
        * Also, 0 is the topmost
        * */
        if(board[m.getDepth()][0][m.getCol()]==EMPTY) {//Not sure if row 6 is topmost or row 0-VK
            return false;
        }
        return true;
    }

    public void reset() {
        for (int x = 0; x < 8; x++) {
            for(int y = 0; y < 7; y++) {
                for(int z = 0; z < 8; z++) {
                    board[x][y][z] = EMPTY;
                }
            }
        }

        winner = PLAYING;
    }

    public void draw(Graphics g) {//have to use draw to draw the board-VK
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 500, 500);

        g.setColor(Color.WHITE);

        for(int x = 50; x < 450; x += 50) {
            for(int y = 50;  y < 450; y += 50) {
                g.drawRect(x, y, 50, 50);
            }
        }
        //This next part I am not sure if I got the for loop correct-VK;

        /*You didn't. I don't think it would have made a difference, but I fixed it anyways.
          Also, for the UI, we could have our program take in image files for the board and pieces and alter the opacity based on which layer the player wants to see. -RK
        */
        for(int x=0; x<board.length; x++) {
            for (int y=0;y<board[0].length;y++) {
                for(int z=0;z<board[0][0].length;z++) {
                    if (board[x][y][z] == RED) {
                        g.setColor(Color.RED);
                        g.fillRect(x * 50, y * 50, 50, 50);
                    }
                }
            }
        }
        //////
    }
}

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


