package com.company;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class Game {
    public static Color redColor, blueColor=null;

    Player pl1, pl2;
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
        Frame f = new Frame("Connect 5");

        b.makeMove(pl1.getMove(b),pl1.getLetter());
        b.makeMove(pl2.getMove(b),pl1.getLetter());
    }
}








