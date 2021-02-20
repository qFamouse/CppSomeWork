package task4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawFunc extends JFrame{
    private JTextField inputXStart = new JTextField("", 3);
    private JTextField inputXEnd = new JTextField("", 3);
    private JTextField inputA = new JTextField("", 3);
    private JTextField inputB = new JTextField("", 3);
    private JTextField inputC = new JTextField("", 3);
    private JTextField inputD = new JTextField("", 3);

    DrawFunc(){
        super("Task4");
        this.setBounds(680, 420, 250, 180);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton button = new JButton("Draw");
        button.addActionListener(new ButtonEventListener());

        Container container = this.getContentPane();
        GridLayout layout = new GridLayout(5,1);
        container.setLayout(layout);

        JLabel label = new JLabel("Input start and end X values:");
        container.add(label);

        JPanel panel = new JPanel();
        panel.add(inputXStart);
        panel.add(inputXEnd);
        container.add(panel);

        label = new JLabel("Input A, B, C and D values:");
        container.add(label);

        panel = new JPanel();
        panel.add(inputA);
        panel.add(inputB);
        panel.add(inputC);
        panel.add(inputD);
        container.add(panel);

        panel = new JPanel();
        panel.add(button);
        container.add(panel);
    }

    class ButtonEventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            double xStart = Integer.parseInt(inputXStart.getText());
            double xEnd = Integer.parseInt(inputXEnd.getText());
            double A = Double.parseDouble(inputA.getText());
            double B = Double.parseDouble(inputB.getText());
            double C = Double.parseDouble(inputC.getText());
            double D = Double.parseDouble(inputD.getText());
            FunctionDrawman app = new FunctionDrawman(xStart,xEnd,A,B,C,D);
            app.setVisible(true);
        }
    }

    public static void main(String[] args) {
        DrawFunc app = new DrawFunc();
        app.setVisible(true);
    }
}
