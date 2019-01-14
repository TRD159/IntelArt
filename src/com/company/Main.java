package com.company;

import java.util.Scanner;

public class Main {

    static Game g;

    public static void main(String[] args) {
        Scanner k=new Scanner(System.in);
        //System.out.println("What is your name?");
        //String name=k.next();
        //HumanPlayer hp=new HumanPlayer(name, 'R');
        g=new Game();
        g.run();
    }

    public static Game getG() {
        return g;
    }
}
