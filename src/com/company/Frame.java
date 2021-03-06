package com.company;

import javax.swing.*;
import java.awt.*;

class Frame extends JFrame implements Runnable{
    Thread t;
    Frame(String title) throws HeadlessException {
        super(title);

        t = new Thread(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setPreferredSize(new Dimension(500, 500));

        pack();

        Panel p = new Panel();
        add(p);

        Insets i = getInsets();

        setPreferredSize(new Dimension(500 + (i.left + i.right),500 + (i.top + i.bottom)));
        pack();

        setVisible(true);

        t.start();
    }

    @Override
    public void run() {
        while (true) {
            repaint();
        }
    }
}

