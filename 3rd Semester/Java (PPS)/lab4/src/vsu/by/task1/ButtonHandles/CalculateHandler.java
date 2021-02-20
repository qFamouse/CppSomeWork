package vsu.by.task1.ButtonHandles;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class CalculateHandler implements ActionListener {
    private JTextField fieldDate;
    private JTextField fieldNumber;

    //private Date date = new Date();
    private Calendar date = new GregorianCalendar();
    private Calendar buffDate = new GregorianCalendar();
    private DateFormat df = new SimpleDateFormat("H:mm:ss");

    public CalculateHandler(JTextField fieldDate, JTextField fieldNumber){
        this.fieldDate = fieldDate;
        this.fieldNumber = fieldNumber;
    }

    private void DateSet(String[] inputDate){
        try{
            date.set(Calendar.HOUR_OF_DAY, Integer.parseInt(inputDate[0]));
            date.set(Calendar.MINUTE, Integer.parseInt(inputDate[1]));
            date.set(Calendar.SECOND, Integer.parseInt(inputDate[2]));
        }catch (Exception e) {
            ShowErrorDialog();
        }
    }

    private void SaveBufferDate(){
        buffDate = (Calendar) date.clone();
    }

    private void ShowErrorDialog(){
        JOptionPane.showMessageDialog(null, "Введено некорректное значение");
    }

    private void DecMinutesFromTime(int minutes){
        date.add(Calendar.MINUTE, -minutes);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String[] inputDate = fieldDate.getText().split(":");
        if (inputDate.length != 3){
            ShowErrorDialog();
        }
        else {
            int minutes;
            DateSet(inputDate);
            try {
                minutes = Integer.parseInt(fieldNumber.getText());
            }catch (Exception e) {
                ShowErrorDialog();
                return;
            }
            SaveBufferDate();
            DecMinutesFromTime(minutes);
            String infoMsg = date.after(buffDate) ? "Будущее" : "Прошлое";
            JOptionPane.showMessageDialog(null, df.format(date.getTime()) + " - " + infoMsg);
        }
    }
}
