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

class Board {
    public static final char EMPTY = '-', RED = 'R', BLUE = 'B', PLAYING = '-', TIE = 'T';

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
}


