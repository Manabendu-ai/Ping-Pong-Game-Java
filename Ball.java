package PingPongGame;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Ball extends Rectangle{

    Random random;
    int xVel;
    int yVel;
    int speed = 1;


    Ball(int x, int y, int WIDTH, int HEIGHT){
        super(x,y,WIDTH, HEIGHT);
        random = new Random();
        int randX = random.nextInt(2);
        if(randX == 0) randX--;
        setX(randX*speed);

        int randY = random.nextInt(2);
        if(randY == 0) randY--;
        setY(randY*(int)speed);


    }
    public void setX(int randomX){
        xVel = randomX;
    }
    public void setY(int randomY){
        yVel = randomY;
    }
    public void move(){
        x += xVel;
        y += yVel;
    }
    public void draw(Graphics g){
        g.setColor(Color.yellow);
        g.fillOval(x,y, height, width);
    }
}
