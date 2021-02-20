package vsu.by.task2;


import vsu.by.task2.ButtonAction.CreateNewForm;

import javax.swing.*;
import java.awt.*;


public class FeForm extends JFrame {
    public FeForm(){
        // Parent Window settings
        super("FE"); // super - текущий экземпляр родительского класса
        setBounds(700, 150, 200, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);

        // Font
        Font TextFont = new Font("Arial Black", Font.PLAIN, 15);
        // Labels
        JLabel SizeLabel = new JLabel("Размер (x/y)");
        SizeLabel.setFont(TextFont);


        JLabel LayoutLabel = new JLabel("Положение (w/h)");
        LayoutLabel.setFont(TextFont);

        JLabel ManagerLayoutLabel = new JLabel("Размещение");
        ManagerLayoutLabel.setFont(TextFont);

        JLabel CountLabel = new JLabel("Количество");
        CountLabel.setFont(TextFont);

        // JTextFields
        JTextField SizeXField = new JTextField();
        SizeXField.setColumns(3);

        JTextField SizeYField = new JTextField();
        SizeYField.setColumns(3);

        JTextField BoundWidth = new JTextField();
        BoundWidth.setColumns(3);

        JTextField BoundHeight = new JTextField();
        BoundHeight.setColumns(3);

        JTextField CountField1 = new JTextField();
        CountField1.setColumns(3);

        JTextField CountField2 = new JTextField();
        CountField2.setColumns(3);
        //
        String[] ItemsForLayoutbox = {"Left", "Right", "Center", "Leading", "Trailing"};

        




        JComboBox layoutbox = new JComboBox(ItemsForLayoutbox);
        //
        JButton button1 = new JButton("Click");

        button1.addActionListener(new CreateNewForm(SizeXField, SizeYField, BoundWidth, BoundHeight, layoutbox, CountField1, CountField2));
        // ADD
        JPanel contents = new JPanel(new VerticalLayout());

        contents.add(SizeLabel);
        contents.add(SizeXField);
        contents.add(SizeYField);

        contents.add(LayoutLabel);
        contents.add(BoundWidth);
        contents.add(BoundHeight);

        contents.add(ManagerLayoutLabel);
        contents.add(layoutbox);

        contents.add(CountLabel);
        contents.add(new JLabel("Текстовые поля"));
        contents.add(CountField1);
        contents.add(new JLabel("Заблокированные метки"));
        contents.add(CountField2);

        contents.add(button1);



        setContentPane(contents);
        ///////////////////////////////////////////////////////////////////

        validate();
        setVisible(true);
    }
}