package PingPongGame;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Score extends Rectangle{

    static int GameWidth;
    static int GameHeight;
    int player1;
    int player2;
    Score(int GameWidth, int GameHeight){
        Score.GameWidth = GameWidth;
        Score.GameHeight = GameHeight;
    }
    public void draw(Graphics g){
        g.setColor(Color.white);
        g.setFont(new Font("Consolas", Font.PLAIN, 60));

        g.drawLine(GameWidth/2, 0, GameWidth/2, GameHeight);
        g.drawString(String.valueOf(player1/10)+String.valueOf(player1%10), (GameWidth/2)-85, 50);
        g.drawString(String.valueOf(player2/10)+String.valueOf(player2%10), (GameWidth/2)+20, 50);
    }
}
