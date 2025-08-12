package PingPongGame;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {

    static final int WIDTH = 1280;
    static final int HEIGHT = (int) (WIDTH*0.5555);
    static final Dimension SCREEN_SIZE = new Dimension(WIDTH, HEIGHT);
    static final int BALL_DIA = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;

    Thread gameThread ;
    Image image, bgImage;
    Graphics graphics;
    Random random;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;

    GamePanel(){
        newPaddle();
        newBall();
        score = new Score(WIDTH,HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new Action());
        this.setPreferredSize(SCREEN_SIZE);
        bgImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("/PingPongGame/feild.png"))).getImage();

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void newBall(){
        random = new Random();
        ball = new Ball(((WIDTH/2)-(BALL_DIA)/2), random.nextInt(HEIGHT-BALL_DIA),BALL_DIA, BALL_DIA);
    }

    public void newPaddle(){
        paddle1 = new Paddle(0, ((HEIGHT/2)-(PADDLE_HEIGHT/2)), PADDLE_WIDTH, PADDLE_HEIGHT, 1);
        paddle2 = new Paddle(WIDTH-PADDLE_WIDTH, ((HEIGHT/2)-(PADDLE_HEIGHT/2)), PADDLE_WIDTH, PADDLE_HEIGHT, 2);
    }
    public void paint(Graphics g){
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();

        graphics.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);

        draw(graphics);
        g.drawImage(image, 0,0, this);
    }
    public void draw(Graphics g){
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
    }
    public void move(){
        paddle1.move();
        paddle2.move();
        ball.move();
    }
    public void checkCollision(){

        if(ball.y <= 0) ball.setY(-ball.yVel);
        if(ball.y >= HEIGHT-BALL_DIA) ball.setY(-ball.yVel);

        if(ball.intersects(paddle1)){
            ball.xVel = Math.abs(ball.xVel);
            ball.setX(ball.xVel);
            ball.setY(ball.yVel);
        }

        if(ball.intersects(paddle2)){
            ball.xVel = Math.abs(ball.xVel);
            ball.setX(-ball.xVel);
            ball.setY(ball.yVel);
        }

        if(paddle1.y <= 0) paddle1.y = 0;
        if(paddle1.y >= (HEIGHT-PADDLE_HEIGHT)) paddle1.y = HEIGHT-PADDLE_HEIGHT;
        if(paddle2.y <= 0) paddle2.y = 0;
        if(paddle2.y >= (HEIGHT-PADDLE_HEIGHT)) paddle2.y = HEIGHT-PADDLE_HEIGHT;

        if(ball.x <= 0) {
            score.player2++;
            newPaddle();
            newBall();

        }
        if(ball.x >= WIDTH-BALL_DIA) {
            score.player1++;
            newPaddle();
            newBall();

        }
    }
    public void run(){
        long lastTime = System.nanoTime();
        double fps = 60;
        double ns = 100_000_000/fps;
        double delta = 0;

        while(true){
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime = now;
            if(delta >= 1){
                move();
                checkCollision();
                repaint();
                delta--;
            }

        }
    }
    public class Action extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }
        public void keyReleased(KeyEvent e){
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}
