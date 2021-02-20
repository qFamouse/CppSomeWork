package vsu.by.window;

import vsu.by.window.JPanels.JPanelAnyUser;
import vsu.by.window.User.user;

import javax.swing.*;
import java.awt.*;

public class JFrameMaster extends JFrame {
    // User
    public static user User = new user();
    // JFrame Data
    private static JFrameMaster jFrame;
    private static JPanel jPanelAnyUser;
    // JFrame setting
    private static int positionX;
    private static int positionY;

    public JFrameMaster(){
        super("Кафедра");
        jFrame = this;
        // Variables
        Dimension screenSize; // Для получения размеров окна


        // Settings JFrame
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        positionX = screenSize.width / 3;
        positionY = screenSize.height / 4;

        setBounds(positionX, positionY, 600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setResizable(false);
        setVisible(true);



        // JPanels
        jPanelAnyUser = AddPanel(new JPanelAnyUser());

        Container pane = getContentPane();
        pane.add(jPanelAnyUser, BorderLayout.NORTH);


        validate();
    }

    public JPanel AddPanel(JPanel panel){
        add(panel);
        return panel;
    }

    public static int getPositionX(){
        return positionX;
    }
    public static int getPositionY(){
        return positionY;
    }

    public static JFrame getFrame(){
        return jFrame;
    }

    public void repaintJPanelAnyUser(){
        System.out.println(User.getRank());
        jPanelAnyUser.removeAll();
        jPanelAnyUser = AddPanel(new JPanelAnyUser());
        jPanelAnyUser.revalidate();
    }
}
