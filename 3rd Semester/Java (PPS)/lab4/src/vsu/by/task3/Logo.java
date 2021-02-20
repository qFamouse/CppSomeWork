package vsu.by.task3;

import javax.swing.*;
import java.awt.*;

public class Logo extends JFrame {
    public Logo(){
        super("Rockstar Logotype");
        setBounds(700, 200, 500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        validate();
        setVisible(true);
    }

    public void paint(Graphics g){
        Font HighFont = new Font("Arial", Font.PLAIN | Font.ITALIC | Font.BOLD, 250);
        // Background
        g.setColor(new Color(254, 177, 0));
        g.fillRect(0, 0, 500, 500);
        // R
        g.setColor(new Color(0,0,0));
        g.setFont(HighFont);
        g.drawString("R", 125,325);
        // BlackStar
        int[] BlackStarX = {250, 275, 255, 300, 325, 330, 380, 340, 345, 300};
        int[] BlackStarY = {400, 350, 321, 320, 260, 325, 325, 350, 400, 370};
        g.fillPolygon(BlackStarX, BlackStarY, 10);
        // WhiteStar
        int[] WhiteStarX = {266, 285, 270, 300, 320, 325, 362, 330, 335, 305};
        int[] WhiteStarY = {385, 350, 328, 328, 285, 329, 329, 347, 387, 360};
        g.setColor(new Color(255, 255, 255));
        g.fillPolygon(WhiteStarX, WhiteStarY, 10);
    }
}