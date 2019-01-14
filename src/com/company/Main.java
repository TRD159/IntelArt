package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner k=new Scanner(System.in);
        //System.out.println("What is your name?");
        //String name=k.next();
        //HumanPlayer hp=new HumanPlayer(name, 'R');
        Game g=new Game();
        g.run();
    }
}
