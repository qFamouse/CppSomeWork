package vsu.by.task1;

import vsu.by.task1.JPanels.JPanelMain;

import javax.swing.*;
import java.awt.*;

public class JFrameMaster extends JFrame {
    public JFrameMaster(){
        // JFrame setting
        super("DataMaster");
        setBounds(700, 200, 250, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        //  Elements
        Container pane = getContentPane();
        setLayout(new FlowLayout());
        JPanel MainPanel = new JPanelMain();
        // Pane settings
        pane.add(MainPanel);
        //   pane.setLayout();
        // Add Pane
        // add(pane);
        setVisible(true);
        validate();
    }
}
