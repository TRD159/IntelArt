package com.company;

import java.awt.*;
import java.util.ArrayList;

class Board {
    public static final char EMPTY = '-', RED = 'R', BLUE = 'B', PLAYING = '-', TIE = 'T';
    public static final int X_SIZE=8,Y_SIZE=7, Z_SIZE = 8;
    private char[][][] board;
    private char winner;
    long noOneHasWon=0;
    static boolean fullView=false;
    static ArrayList<Location> moves = new ArrayList<>();

    public Board() {
        board = new char[8][7][8];
        reset() ;
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

    public Location makeMove(Move move, char p) { //This returns the location of the piece, not a boolean. It'll make win checks easier later on. -RK
        int x = move.getX();
        int y = 6;
        int z = move.getZ();

        Location l = new Location(x, y, z);

        falseCheck :
        {
            for (; y >= 0; y--) {
                //System.out.println(y);
                if (board[z][y][x] == EMPTY) {
                    break falseCheck;
                }
            }

            return null;
        }



        l = new Location(x, y, z);
        setLocation(l, p);
        //System.out.println(z + " " + y + " " + x);
        return l;
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


//????????????????????????????????????????????????????????HELP MEEEEEEEEE
   /* public char getWinner(){
        for (int x = 0; x < X_SIZE; x++) {
            for(int y = 0; y < Y_SIZE; y++) {
                for(int z = 0; z < Z_SIZE; z++) {
                    if(board[x][y][z]==RED) {
                        if((x<X_SIZE-5){
                            if(board[x++][y][z]==RED) {
                                // NOT SURE WHICH METHOD GOES HERE
                                horizontalCheck(new Location(x,y,z));
                            }
                        }
                    }
                }
            }
        }
        //return winner;

    }
    public boolean diagonalCheck(Location l) {

    }

    public boolean verticalCheck(Location l) {

    }

    public boolean horizontalCheck(Location l) {

    }*/

    public boolean isFull(Move m) {
        /*Firstly, the way you access the array is wrong. You're going YXZ, but it's supposed to be ZYX.
        * Also, 0 is the topmost
        * */
        if(board[m.getZ()][0][m.getX()]==EMPTY) {//Not sure if row 6 is topmost or row 0-VK
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
            for(int y = 100;  y < 450; y += 50) {
                if(x/50 - 1 == PublicData.columnViewed)
                    g.fillRect(x, y, 50, 50);
                g.drawRect(x, y, 50, 50);
            }
        }
        //This next part I am not sure if I got the for loop correct-VK;

        /*You didn't. I don't think it would have made a difference, but I fixed it anyways.
          Also, for the UI, we could have our program take in image files for the board and pieces and alter the opacity based on which layer the player wants to see. -RK
        */
        //Would that not be extremely complicated? Do you think we could do that in a week? -VK


        for(int z=0; z<board.length; z++) {



            for (int y=0;y<board[0].length;y++) {

                //System.out.println(y);

                Color gr = Color.white;
                Color rg = Color.black;

                for(int x=0;x<board[0][0].length;x++) {

                    int za = PublicData.getLayerViewed();

                    if (board[za][y][x] == RED) {
                        g.setColor(Game.redColor);
                        g.fillRect((x + 1)* 50 + 1, (y + 2)* 50 + 1, 49, 49);
                    }
                    if (board[za][y][x] == BLUE) {
                        g.setColor(Game.blueColor);
                        g.fillRect((x + 1)* 50  + 1, (y + 2)* 50 + 1, 49, 49);
                    }
                    g.setColor(gr);
                    //g.fillRect(474, (int)(z * (500.0/8) - 1), 27, 65);
                    g.fillRect((int)(z * (500.0/8) - 1), 0, 65, 27);
                    if(PublicData.layerViewed != z)
                        g.setColor(rg);
                    //g.fillRect(475, (int)(z * (500.0/8)), 25, 63);
                    g.fillRect((int)(z * (500.0/8)), 0, 63, 25);
                }
                if(Game.pl1 instanceof RandomComputer&&Game.pl2 instanceof RandomComputer) {
                    Main.getG().moveMaker(PublicData.columnViewed, PublicData.layerViewed);
                    draw(g);

                }

            }
        }

    }
}
