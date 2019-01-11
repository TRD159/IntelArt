package com.company;

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








