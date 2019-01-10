package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        new Graphic();
        Scanner k=new Scanner(System.in);
        Board b =new Board();
        System.out.println("What is your name?");
        String name=k.next();
        HumanPlayer hp=new HumanPlayer(name, 'R');
        hp.getMove(b);
    }
}
