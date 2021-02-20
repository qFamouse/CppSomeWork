package vsu.by.task1.JPanels;

import vsu.by.task1.ButtonHandles.CalculateHandler;

import javax.swing.*;
import java.awt.*;

public class JPanelMain extends JPanel {
    public JPanelMain(){
        // JPanel setting
        setLayout(new GridLayout(3, 0, 5, 5));
        setBackground(new Color(59, 210, 215));
        // Elements
            // FIRST CONTAINER //
        JTextField fieldDate;
        JLabel labelDate;
            // SECOND CONTAINER //
        JTextField fieldNumber;
        JLabel labelNumber;
            // BUTTON //
        JButton bCalculateDate;
            // CONTAINERS //
        Container dataContainer;
        Container numberContainer;
        // fieldDate settings
        fieldDate = new JTextField();
        fieldDate.setColumns(10);
        // fieldNumber settings
        fieldNumber = new JTextField();
        fieldNumber.setColumns(5);
        // labelDate settings
        labelDate = new JLabel("Введите время");
        // labelNumber settings
        labelNumber = new JLabel("Введите число");
        // dataContainer settings
        dataContainer = new Container();
        dataContainer.setLayout(new FlowLayout());
        dataContainer.add(labelDate);
        dataContainer.add(fieldDate);
        // bCalculateDate settings
        bCalculateDate = new JButton("Вычислить");
        bCalculateDate.addActionListener(new CalculateHandler(fieldDate, fieldNumber));
        // numberContainer settings
        numberContainer = new Container();
        numberContainer.setLayout(new FlowLayout());
        numberContainer.add(labelNumber);
        numberContainer.add(fieldNumber);
        // Add containers to Panel
        add(dataContainer);
        add(numberContainer);
        add(bCalculateDate);
    }
}
