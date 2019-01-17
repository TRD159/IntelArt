package com.company;


//import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.Key;

class Panel extends JPanel implements MouseListener, KeyListener {
    Panel() {
        setSize(500, 500);

        super.setFocusable(true);
        super.requestFocus();

        addMouseListener(this);
        addKeyListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getX() >= 475 && e.getX() <= 500) {
            for(double yi = 0; yi < 7; yi++) {
                if(e.getY() >= yi * (500.0/7) && e.getY() < (yi + 1) * (500.0/7)) {
                    PublicData.layerViewed = (int)yi;
                    break;
                }
            }
        }
        Game.redColor=new Color(255,0,35*PublicData.layerViewed);
        Game.blueColor=new Color(0,35*PublicData.layerViewed,255);
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            PublicData.downLayer();
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            PublicData.upLayer();
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            PublicData.left();
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            PublicData.right();
        }
        else if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            //System.out.println("Move sent");


            if(!(Main.getG().b.isFull(new Move(PublicData.columnViewed,PublicData.layerViewed)))) {
                Main.getG().moveMaker(PublicData.columnViewed, PublicData.layerViewed);
            }
            repaint();
            if(Main.getG().getPl2() instanceof RandomComputer||Main.getG().getPl1() instanceof RandomComputer ) {
                if(!(Main.getG().b.isFull(new Move(PublicData.columnViewed,PublicData.layerViewed)))) {
                    Main.getG().moveMaker(PublicData.columnViewed, PublicData.layerViewed);
                }
                System.out.println("AI Moved");
                repaint();
            }
            if(!(Main.getG().b.getWinner()==Main.getG().b.PLAYING)) {
                System.out.println("HEy someone WON");
                System.exit(0);
            }
        }
        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
        else if(e.getKeyChar()== 'F'||e.getKeyChar()=='F') {
            for(int x=0;x< 8;x++) {

            }
        }
        Game.redColor=new Color(255,0,35*PublicData.layerViewed);
        Game.blueColor=new Color(0,35*PublicData.layerViewed,255);
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

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

