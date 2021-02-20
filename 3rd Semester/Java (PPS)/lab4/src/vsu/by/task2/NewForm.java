package vsu.by.task2;

import javax.swing.*;
import java.awt.*;

public class NewForm extends JFrame {
    public NewForm(int SizeX, int SizeY, int PositionW, int PositionH, LayoutManager placement, int CountTextBox, int CountCheckBox){
        super("NewForm");
        setBounds(PositionW, PositionH, SizeX, SizeY);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        //
        JPanel mypanel = new JPanel(placement);

        for (int i = 0; i < CountTextBox; i++){
            JTextField buffer = new JTextField();
            buffer.setColumns(3);
            mypanel.add(buffer);
        }
        for (int i = 0; i < CountCheckBox; i++){
            JCheckBox buffer = new JCheckBox();
            buffer.setEnabled(false);
            mypanel.add(buffer);
        }
        setContentPane(mypanel);
        //

        setVisible(true);
    }
}
