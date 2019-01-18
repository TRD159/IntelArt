package com.company;

import java.awt.*;
import java.util.ArrayList;

class Board {
    public static final char EMPTY = '-', RED = 'R', BLUE = 'B', PLAYING = '-', TIE = 'T';
    public static final int X_SIZE=8,Y_SIZE=7, Z_SIZE = 8;
    private char[][][] board;
    private char winner=' ';
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
   public char getWinner(){
        for (int z = 0; z < Z_SIZE; z++) {
            for(int y = 0; y < Y_SIZE; y++) {
                for(int x = 0; x< X_SIZE; x++) {
                    if(board[z][y][x]==RED) {
                        if(checkXP(new Location(x,y,z),RED,0)||checkYP(new Location(x,y,z),RED,0)||checkZP(new Location(x,y,z),RED,0)||checkXM(new Location(x,y,z),RED,0)||checkYM(new Location(x,y,z),RED,0)||checkZM(new Location(x,y,z),RED,0)||checkYPZP(new Location(x,y,z),RED,0)||
                                checkYPZM(new Location(x,y,z),RED,0)||checkZPXP(new Location(x,y,z),RED,0)||checkZPXM(new Location(x,y,z),RED,0)||checkYPXP(new Location(x,y,z),RED,0)||checkYMXP(new Location(x,y,z),RED,0)||checkYMZP(new Location(x,y,z),RED,0)||checkYMZM(new Location(x,y,z),RED,0)
                        ||checkYMZPXP(new Location(x,y,z),RED,0)||checkYPZMXP(new Location(x,y,z),RED,0)||checkYPZPXM(new Location(x,y,z),RED,0)) {
                            winner=RED;
                            System.out.println("RED WINS");
                        }
                    }
                    if(board[z][y][x]==BLUE) {
                        if(checkXP(new Location(x,y,z),BLUE,0)||checkYP(new Location(x,y,z),BLUE,0)||checkZP(new Location(x,y,z),BLUE,0)||checkXM(new Location(x,y,z),BLUE,0)||checkYM(new Location(x,y,z),BLUE,0)||checkZM(new Location(x,y,z),BLUE,0)||checkYPZP(new Location(x,y,z),BLUE,0)||
                                checkYPZM(new Location(x,y,z),BLUE,0)||checkZPXP(new Location(x,y,z),BLUE,0)||checkZPXM(new Location(x,y,z),BLUE,0)||checkYPXP(new Location(x,y,z),BLUE,0)||checkYMXP(new Location(x,y,z),BLUE,0)||checkYMZP(new Location(x,y,z),BLUE,0)||checkYMZM(new Location(x,y,z),BLUE,0)
                        ||checkYMZPXP(new Location(x,y,z),BLUE,0)||checkYPZMXP(new Location(x,y,z),BLUE,0)||checkYPZPXM(new Location(x,y,z),BLUE,0)) {
                            winner=BLUE;
                            System.out.println("BLUE WINS");
                        }
                    }
                }
            }
        }
        return winner;

    }
    public boolean checkXP(Location l, char player, int x) { //x starts off being 0
        if(l.x==X_SIZE) {
            return false;
        }
        if(l.x < X_SIZE && x < 5) {
            if(board[l.z][l.y][l.x] == player) {
                return checkXP(cL(l,1, 0, 0), player, ++x);
            } else {
                return false;
            }
        }

        if(x==5) {
            return true;
        }
        return false;
    }
    public boolean checkXM(Location l, char player, int x) {
        if(l.x==0) {
            return false;
        }
        if(l.x >= 0 && x < 5) {
            if(board[l.z][l.y][l.x] == player) {
                return checkXM(cL(l,- 1, 0, 0), player, ++x);
            } else {
                return false;
            }
        }
        if(x==5) {
            return true;
        }
        return false;
    }
    public boolean checkYP(Location l, char player, int y) {
        if (l.y == Y_SIZE) {
            return false;
        }
        if (l.y < Y_SIZE && y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return checkYP(cL(l,0, 1, 0), player, ++y);
            } else {
                return false;
            }
        }
        if (y == 5) {
            return true;
        }
        return false;
    }

    public boolean checkYPZP(Location l, char player, int y) {
        if (l.y == Y_SIZE||l.z==Z_SIZE) {
            return false;
        }
        if (l.y < Y_SIZE && l.z <Z_SIZE&& y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return checkYPZP(cL(l,0, 1, 1), player, ++y);
            } else {
                return false;
            }
        }
        if (y == 5) {
            return true;
        }
        return false;
    }
    public boolean checkYMZP(Location l, char player, int y) {
        if (l.y == 0||l.z==Z_SIZE) {
            return false;
        }
        if (l.y > 0 && l.z <Z_SIZE&& y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return checkYMZP(cL(l,0, -1, 1), player, ++y);
            } else {
                return false;
            }
        }
        if (y == 5) {
            return true;
        }
        return false;
    }
    public boolean checkYMZM(Location l, char player, int y) {
        if (l.y == 0||l.z==0) {
            return false;
        }
        if (l.y >=0 && l.z>=0&& y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return checkYMZM(cL(l,0, -1, -1), player, ++y);
            } else {
                return false;
            }
        }
        if (y == 5) {
            return true;
        }
        return false;
    }


    public boolean checkYPZM(Location l, char player, int y) {
        if (l.y == Y_SIZE||l.z == 0) {
            return false;
        }
        if (l.y < Y_SIZE &&l.z>=0 &&y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return checkYPZM(cL(l,0, 1, -1), player, ++y);
            } else {
                return false;
            }
        }
        if (y == 5) {
            return true;
        }
        return false;
    }
    public boolean checkYMZPXP(Location l, char player, int y) {
        if (l.y == 0||l.z ==Z_SIZE||l.x==X_SIZE) {
            return false;
        }
        if (l.y >=0 &&l.z<Z_SIZE &&l.x<X_SIZE&&y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return checkYMZPXP(cL(l,1, -1, 1), player, ++y);
            } else {
                return false;
            }
        }
        if (y == 5) {
            return true;
        }
        return false;
    }
    public boolean checkYPZMXP(Location l, char player, int y) {
        if (l.y == Y_SIZE||l.z == 0||l.x==X_SIZE) {
            return false;
        }
        if (l.y < Y_SIZE &&l.z>=0 &&l.x<X_SIZE&&y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return checkYPZMXP(cL(l,1, 1, -1), player, ++y);
            } else {
                return false;
            }
        }
        if (y == 5) {
            return true;
        }
        return false;
    }
    public boolean checkYPZPXM(Location l, char player, int y) {
        if (l.y == Y_SIZE||l.z == Z_SIZE||l.x==0) {
            return false;
        }
        if (l.y < Y_SIZE &&l.z<Z_SIZE &&l.x>=0&&y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return checkYPZPXM(cL(l,-1, 1, 1), player, ++y);
            } else {
                return false;
            }
        }
        if (y == 5) {
            return true;
        }
        return false;
    }

    public boolean checkZPXP(Location l, char player, int y) {
        if (l.z == Z_SIZE||l.x==X_SIZE) {
            return false;
        }
        if (l.z < Z_SIZE &&l.x<X_SIZE&& y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return checkZPXP(cL(l,1, 0, 1), player, ++y);
            } else {
                return false;
            }
        }
        if (y == 5) {
            return true;
        }
        return false;
    }
    public boolean checkZPXM(Location l, char player, int y) {
        if (l.x ==0||l.z==Z_SIZE) {
            return false;
        }
        if (l.x >=0 &&l.z<Z_SIZE &&y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return checkZPXM(cL(l,-1, 0, 1), player, ++y);
            } else {
                return false;
            }
        }
        if (y == 5) {
            return true;
        }
        return false;
    }

    public boolean checkYPXP(Location l, char player, int y) {
        if (l.y == Y_SIZE||l.x==X_SIZE) {
            return false;
        }
        if (l.y < Y_SIZE &&l.x<X_SIZE &&y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return checkYPXP(cL(l,1, 1, 0), player, ++y);
            } else {
                return false;
            }
        }
        if (y == 5) {
            return true;
        }
        return false;
    }


    public boolean checkYMXP(Location l, char player, int y) {
        if (l.y == 0||l.x==X_SIZE) {
            return false;
        }
        if (l.y >=0 &&l.x<X_SIZE&& y < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return checkYMXP(cL(l,1, -1, 0), player, ++y);
            } else {
                return false;
            }
        }
        if (y == 5) {
            return true;
        }
        return false;
    }



    public boolean checkYM(Location l, char player, int y) {
        if(l.y==Y_SIZE) {
            return false;
        }
        if(l.y >= 0 && y < 5) {
            if(board[l.z][l.y][l.x] == player) {
                return checkYM(cL(l,0,-1,0), player, ++y);
            } else {
                return false;
            }
        }
        if(y==5) {
            return true;
        }

        return false;
    }
    public boolean checkZP(Location l, char player, int z) {
        if(l.z==Z_SIZE) {
            return false;
        }
        if(l.z < Z_SIZE && z < 5) {
            if(board[l.z][l.y][l.x] == player) {
                return checkZP(cL(l,0,0,1), player, ++z);
            } else {
                return false;
            }
        }

        if(z==5) {
            return true;
        }
        return false;
    }
    public boolean checkZM(Location l, char player, int z) {
        if (l.z == 0) {
            return false;
        }
        if (l.z >= 0 && z < 5) {
            if (board[l.z][l.y][l.x] == player) {
                return checkZM(cL(l, 0, 0, -1), player, ++z);
            } else {
                return false;
            }
        }

        if (z == 5) {
            return true;
        }
        return false;
    }
    Location cL(Location l, int x, int y, int z) {
        return new Location(l.x + x, l.y + y, l.z + z);
    }
    /*public boolean diagonalCheck(Location l) {
        return true;
    }

    public boolean verticalCheck(Location l) {
        return true;
    }

    public boolean horizontalCheck(Location l, int x, char player, int y) {
        if(x==X_SIZE) {
            if(y==4) {
                return true;
            }
            return false;
        }
        if(board[x++][l.y][l.z]==player) {
            horizontalCheck(l,x,player,++y);
        }
        if(y==4) {
            return true;
        }
        return false;
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
                    //Here was an infinite loop that caused the program to crash everytime. Thanks, Varun!
                }

            }
        }

    }
}
