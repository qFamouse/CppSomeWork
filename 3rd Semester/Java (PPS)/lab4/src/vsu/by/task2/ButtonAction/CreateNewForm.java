package vsu.by.task2.ButtonAction;

        import vsu.by.task2.ConvertToLayoutManager;
        import vsu.by.task2.NewForm;

        import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;

public class CreateNewForm implements ActionListener {
    private final JTextField SizeXField;
    private final JTextField SizeYField;
    private final JTextField BoundWidth;
    private final JTextField BoundHeight;
    private final JComboBox layoutbox;
    private final JTextField CountField1;
    private final JTextField CountField2;


    String buffer;

    public CreateNewForm(JTextField SizeXField, JTextField SizeYField, JTextField BoundWidth, JTextField BoundHeight, JComboBox layoutbox, JTextField CountField1, JTextField CountField2){
        this.SizeXField = SizeXField;
        this.SizeYField = SizeYField;
        this.BoundWidth = BoundWidth;
        this.BoundHeight = BoundHeight;
        this.layoutbox = layoutbox;
        this.CountField1 = CountField1;
        this.CountField2 = CountField2;
    }

    @Override
    public void actionPerformed(ActionEvent event){
        try{
            int SizeX = Integer.parseInt(SizeXField.getText());
//            JOptionPane.showMessageDialog(null, SizeXField.getText());
            int SizeY = Integer.parseInt(SizeYField.getText());
            int PositionW = Integer.parseInt(BoundWidth.getText());
            int PositionH = Integer.parseInt(BoundHeight.getText());
            LayoutManager placement = new ConvertToLayoutManager(layoutbox.getSelectedIndex()).GetLayoutManager();
            int CountTextBox = Integer.parseInt(CountField1.getText());
            int CountCheckBox = Integer.parseInt(CountField2.getText());

            new NewForm(SizeX, SizeY, PositionW, PositionH, placement, CountTextBox, CountCheckBox);

        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Вы ввели неверные данные");
        }
    }

}