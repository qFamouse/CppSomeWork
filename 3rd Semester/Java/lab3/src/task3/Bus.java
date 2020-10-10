package task3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class Bus implements Serializable {
     private String weekday;
     private String start_point;
     private String finish_point;
     private Calendar start_time;
     private Calendar finish_time;

     private String[] weeks = {"Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота", "Воскресенье"};
     Bus(int weekday, String start_point, String finish_point, Calendar start_time, Calendar finish_time){
         this.weekday = weeks[weekday];
         this.start_point = start_point;
         this.finish_point = finish_point;
         this.start_time = start_time;
         this.finish_time = finish_time;
     }

     public boolean isContainsStartPoint(String start_point){
         return this.start_point.equals(start_point);
    }

    public boolean isContainsFinishPoint(String finish_point){
        return this.finish_point.equals(finish_point);
    }

     // Get/Set Weekday
     public String GetWeekday(){
         return weekday;
     }
    public void SetWeekday(int weekday){
         this.weekday = weeks[weekday];
     }

    // Get/Set start_point
    public String GetStartPoint(){
        return start_point;
    }
    public void SetStartPoint(String start_point){
        this.start_point = start_point;
    }

    // Get/Set finish_point
    public String GetFinishPoint(){
         return finish_point;
    }
    public void SetFinishPoint(String finish_point){
        this.finish_point = finish_point;
    }

    // Get/Set start_time
    public int GetStartTimeHour(){
        return start_time.get(Calendar.HOUR);
    }
    public int GetStartTimeMin(){
        return start_time.get(Calendar.MINUTE);
    }
    public void SetStartTimeHour(int hour){
        start_time.set(Calendar.HOUR, hour);
    }
    public void SetStartTimeMin(int min){
        start_time.set(Calendar.MINUTE, min);
    }

    // Get/Set finish_time
    public int GetFinishTimeHour(){
        return finish_time.get(Calendar.HOUR);
    }
    public int GetFinishTimeMin(){
        return finish_time.get(Calendar.MINUTE);
    }
    public void SetFinishTimeHour(int hour){
        finish_time.set(Calendar.HOUR, hour);
    }
    public void SetFinishTimeMin(int min){
        finish_time.set(Calendar.MINUTE, min);
    }
}
