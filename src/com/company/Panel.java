package com.company;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.Key;

class Panel extends JPanel {
    Panel() {
        setSize(500, 500);

        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getX() >= 475 && e.getX() <= 500) {
                    for(double yi = 0; yi < 7; yi++) {
                        if(e.getY() >= yi * (500.0/7) && e.getY() < (yi + 1) * (500.0/7)) {
                            PublicData.layerViewed = (int)yi;
                            break;
                        }
                    }
                }
                repaint();
            }


            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                super.mouseWheelMoved(e);
                System.out.println(e.getWheelRotation());
            }
        });
    }

    public void paint(Graphics g) {
        /*g.setColor(Color.BLACK);
        g.fillRect(0, 0, 500, 500);

        g.setColor(Color.WHITE);

        for(int x = 50; x < 450; x += 50) {
            for(int y = 50;  y < 450; y += 50) {
                g.drawRect(x, y, 50, 50);
            }
        }*/
        Game.b.draw(g);
    }
}

