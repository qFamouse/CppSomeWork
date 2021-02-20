package vsu.by.window.JPanels;

import vsu.by.window.JFrameMaster;
import vsu.by.window.JPanels.ButtonHandlers.bAuthorizeHandler;
import vsu.by.window.User.user;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Flow;


public class JPanelAnyUser extends JPanel {
    public JPanelAnyUser(){
        setLayout(new BorderLayout());
        // Buttons
        JButton bAuthorize = new JButton();
        JButton bShowDisciplines = new JButton("Дисциплины");
        JButton bShowTopicList = new JButton("Список тем");

        if (JFrameMaster.User.isLogin()){
            bAuthorize.setText("Выйти");
        }
        else {
            bAuthorize.setText("Войти");
        }

        Container AnyAccessContainer = new Container();
        AnyAccessContainer.setLayout(new FlowLayout(FlowLayout.LEFT));
        AnyAccessContainer.add(bShowDisciplines);
        AnyAccessContainer.add(bShowTopicList);

        bAuthorize.addActionListener(new bAuthorizeHandler());

        add(bAuthorize, BorderLayout.EAST);
        add(AnyAccessContainer, BorderLayout.WEST);
        setVisible(true);
    }
}