package vsu.by.window.JPanels.ButtonHandlers;

import vsu.by.window.JFrameMaster;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class bAuthorizeHandler implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event){
       new AuthorizeDialog();
    }


    static class AuthorizeDialog extends JDialog {
        private JTextField tfLogin, tfPassword;
        private JButton    btnOk, btnCancel;
        AuthorizeDialog(){
            super(JFrameMaster.getFrame(), "Вход в систему", true);
            setLocation(700, 500);
            // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // добавляем расположение в центр окна
            getContentPane().add(createGUI());
            // задаем предпочтительный размер
            pack();
            // выводим окно на экран
            setVisible(true);
        }
        private JPanel createGUI(){
            // Создание панели для размещение компонентов
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            // Определение отступов от границ панели. Для этого используем пустую рамку
            panel.setBorder (BorderFactory.createEmptyBorder(12,12,12,12));
            // Создание панели для размещения метки и текстового поля логина
            JPanel name = new JPanel();
            name.setLayout(new BoxLayout(name, BoxLayout.X_AXIS)); // размещение по горизонтали
            JLabel nameLabel = new JLabel("Имя:");
            name.add(nameLabel);
            name.add(Box.createHorizontalStrut(12));
            tfLogin = new JTextField(15);
            name.add(tfLogin);
            // Создание панели для размещения метки и текстового поля пароля
            JPanel password = new JPanel();
            password.setLayout(new BoxLayout(password, BoxLayout.X_AXIS));
            JLabel passwordLabel = new JLabel("Пароль:");
            password.add(passwordLabel);
            password.add(Box.createHorizontalStrut(12));
            tfPassword = new JTextField(15);
            password.add(tfPassword);
            // Создание панели для размещения кнопок управления
            JPanel flow = new JPanel( new FlowLayout( FlowLayout.RIGHT, 0, 0) );
            JPanel grid = new JPanel( new GridLayout( 1,2,5,0) );
            btnOk = new JButton("OK");
            btnCancel = new JButton("Отмена");
            grid.add(btnOk    );
            grid.add(btnCancel);
            flow.add(grid);
            // Ивенты на кнопки
            btnOk.addActionListener(new bAuthorizeOKHandler(tfLogin, tfPassword));


            // Сборка интерфейса
            panel.add(name);
            panel.add(Box.createVerticalStrut(12));
            panel.add(password);
            panel.add(Box.createVerticalStrut(17));
            panel.add(flow);
            // готово
            return panel;
        }
    }
}