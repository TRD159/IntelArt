package com.company;

//import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class Game {
    public static Color redColor, blueColor=null;
    static Move AIMove=null;
    static Player pl1, pl2;
    //This class is going to be the main class for Connect 5 specifically, as I may use this same project to do a few things in my free time.
    Game(String p1, String p2) {
        pl1 = new HumanPlayer(p1, 'R');
        pl2 = new HumanPlayer(p1, 'B');
         
    }
    Game(String p1) {
        pl1 = new HumanPlayer(p1, 'R');
        pl2 = new RandomComputer("Random Com", 'B');
         
    }
    Game() {
        pl1 = new RandomComputer("Com 1", 'R');
        pl2 = new RandomComputer("Com 2", 'B');
    }

    public static Board b = new Board();

    public static Move getAIMove() {
        return AIMove;
    }

    public void moveMaker(int x, int z) {
        //System.out.println("Move made");
        Move m; char c;
        m = null;
        if(pl1.isPlaying()) {
            if(!(pl1 instanceof RandomComputer)) {
                c = 'R';
                m = pl1.getMove(b, x, z);
            }
            else if(Game.pl1 instanceof RandomComputer&&Game.pl2 instanceof RandomComputer) {
                c='R';
                m=pl1.getMove(b);
            }
            else {
                c='R';
                m=pl1.getMove(b,x,z);
                AIMove=m;
            }
        }
        else {
            if(!(pl2 instanceof RandomComputer)) {
                c = 'B';
                m = pl2.getMove(b, x, z);
            }
            else if(Game.pl1 instanceof RandomComputer&&Game.pl2 instanceof RandomComputer) {
                c='B';
                m=pl2.getMove(b);
            }
            else {
                    c='B';
                    m=pl2.getMove(b,x,z);
                    AIMove=m;
            }
        }
        if(m != null) {
            Location l = b.makeMove(m, c);

            Board.moves.add(l);

            pl1.setPlaying(!pl1.playing);
            pl2.setPlaying(!pl2.playing);
        }

        for(int zl = 0; zl < 4; zl++) {
            for(int yl = 0; yl < 3; yl++) {
                for(int xl = 0; xl < 4; xl++) {
                    
                }
            }
        }

        try {
            Thread.sleep(100);
        } catch (Exception e) {

        } finally {
        }
    }

    public void run() {
        Scanner k=new Scanner(System.in);
        System.out.println("Enter Player 1's Name or write AI to choose AI.");
        String p1=k.next();
        System.out.println("Enter Player 2's Name or write AI to choose AI.");
        String p2=k.next();
        if(p1.equals("AI")&&p2.equals("AI")) {
            pl1 = new RandomComputer("Com 1", 'R');
            pl2 = new RandomComputer("Com 2", 'B');
        }
        if(p1.equals("AI")&&!p2.equals("AI")) {
            pl1 = new RandomComputer("Com 1", 'R');
            pl2 = new HumanPlayer(p2, 'B');
        }
        if(!p1.equals("AI")&&p2.equals("AI")) {
            pl2 = new RandomComputer("Com 1", 'R');
            pl1 = new HumanPlayer(p2, 'B');
        }

        if(!p1.equals("AI")&&!p2.equals("AI")) {
            pl1 = new HumanPlayer(p1, 'R');
            pl2 = new HumanPlayer(p1, 'B');
        }
        pl1.setPlaying(true);
        Frame f = new Frame("Connect 5");
    }

    public Player getPl1() {
        return pl1;
    }

    public Player getPl2() {
        return pl2;
    }
}








