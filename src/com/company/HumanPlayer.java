package com.company;

import java.util.Scanner;

class HumanPlayer extends Player {
    public HumanPlayer(String name, char letter) {
        super(name, letter);
    }

    @Override
    public Move getMove(Board board) {
        Scanner s = new Scanner(System.in);

        int z, x;
        while(true) {
            System.out.println("Where will you drop your piece?\nEnter the row and column of the space where you will move.");

            z = s.nextInt() - 1;
            x = s.nextInt() - 1;

            if((z >= 0 && z <= 7) && board.getBoard()[z][6][x] == '-')
                break;
            else
                System.out.println("That move isn't valid!");
        }

        return new Move(x, z);
    }

    @Override
    public Player freshCopy() {
        return new HumanPlayer(getName(), getLetter());
    }
}
