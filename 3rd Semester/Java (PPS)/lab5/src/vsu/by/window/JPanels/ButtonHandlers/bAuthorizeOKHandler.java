package vsu.by.window.JPanels.ButtonHandlers;

import vsu.by.window.JFrameMaster;
import vsu.by.window.JPanels.JPanelAnyUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class bAuthorizeOKHandler implements ActionListener {

    private JTextField username;
    private JTextField password;

    public bAuthorizeOKHandler(JTextField username, JTextField password){
        this.username = username;
        this.password = password;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (JFrameMaster.User.getDataStatus()){
            JFrameMaster.User.SetUsername(username.getText());
            JFrameMaster.User.SetPassword(password.getText());
            if (!(JFrameMaster.User.isUser())){
                new IncorrectInputData();
            }
            else {
                JFrameMaster.getFrame();
            }
        }
    }

    public static class IncorrectInputData extends JDialog {
        IncorrectInputData(){
            super(JFrameMaster.getFrame(), "Неверный логин или пароль", true);
            int x = JFrameMaster.getPositionX()/2;
            int y = JFrameMaster.getPositionY()/2;
            setLocation(x,y);
            getContentPane().add(createGUI());
            pack();
            setVisible(true);
        }
        public JPanel createGUI(){
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.CENTER));
            JLabel text = new JLabel("Вы вввели неверный логин или пароль");
            panel.add(text);
            return panel;
        }
    }

}
