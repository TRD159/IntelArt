package com.company;

import java.awt.*;

class Board {
    public static final char EMPTY = '-', RED = 'R', BLUE = 'B', PLAYING = '-', TIE = 'T';
    public static final int X_SIZE=8,Y_SIZE=7, Z_SIZE = 8;
    private char[][][] board;
    private char winner;

    public Board() {
        board = new char[8][7][8];
        reset();
        board[4][4][4] = 'R';
        board[6][6][6]='B';

        for (char[][] a: board
             ) {
            for (char[] r: a
                 ) {
                for(char c: r
                ) {
                    System.out.print(c);
                }
                System.out.println();
            }
            System.out.println("\n");
        }
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
        //Would that not be extremely complicated? Do you think we could do that in a week? -VK


        for(int x=0; x<board.length; x++) {

            Color gr = Color.white;
            Color rg = Color.black;

            for (int y=0;y<board[0].length;y++) {

                for(int z=0;z<board[0][0].length;z++) {

                    int xa = PublicData.getLayerViewed();

                    if (board[xa][y][z] == RED) {
                        g.setColor(Game.redColor);
                        g.fillRect(z * 50, y * 50, 50, 50);
                    }
                    if (board[xa][y][z] == BLUE) {
                        g.setColor(Game.blueColor);
                        g.fillRect(z * 50, y * 50, 50, 50);
                    }
                }
                g.setColor(gr);
                g.fillRect(474, (int)(y * (500.0/7) - 1), 27, 74);
                if(PublicData.layerViewed != y)
                    g.setColor(rg);
                g.fillRect(475, (int)(y * (500.0/7)), 25, 72);

            }
        }
    }
}
