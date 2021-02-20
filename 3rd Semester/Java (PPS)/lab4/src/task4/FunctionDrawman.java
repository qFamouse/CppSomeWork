package task4;

import javax.swing.*;
import java.awt.*;
import java.awt.desktop.SystemSleepEvent;

public class FunctionDrawman extends JFrame{
    double xStart;
    double xEnd;
    double A;
    double B;
    double C;
    double D;

    class Picture extends Canvas {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Rectangle r = g.getClipBounds();

            int x0 = r.x + 500;
            int y0 = r.height - 250;
            g.drawLine(x0,r.height, x0, 0);
            g.drawLine (0,y0, r.width, y0);
            g.setColor(Color.RED);
            for (double x = xStart; x<=xEnd; x+=0.001){
                double y = A*Math.sin(B*x) + C*Math.log(D*x*x + 1);
                int xCoord = (int) (x*40);
                int yCoord = (int) (y*40);
                g.drawOval (x0 + xCoord,y0 - yCoord,2,2);
            }

        }
    }

    FunctionDrawman (double xStart, double xEnd, double A, double B, double C, double D) {

        this.xStart = xStart;
        this.xEnd = xEnd;
        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(100, 50, 1000, 520);
        this.add(new FunctionDrawman.Picture());
        this.validate();
    }
}
