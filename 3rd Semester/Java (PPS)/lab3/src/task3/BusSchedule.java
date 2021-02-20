package task3;

import java.io.*;
import java.util.*;

public class BusSchedule implements Serializable {
    private static final long serialVersionUID = 1L;

    private ArrayList<Bus> buses;

    public boolean busesIsNull(){
        return buses == null;
    }

    BusSchedule(){
        buses = new ArrayList<>();
    }

    public int GetLengthBuses(){
        return buses.size();
    }

    public void ShowBuses(){
        if (buses != null){
            int length = GetLengthBuses();
            for (int i = 0; i < length; i++){
                ShowCurrentBus(i);
            }
        }
        else {
            System.out.println("Автобусы null");
        }
    }

    public void SortBusesForStartTime(){
        Collections.sort(buses, new SortByStartTime());
    }

    public void SortBusesForFinishTime(){
        Collections.sort(buses, new SortByFinishTime());
    }

    public void ShowCurrentBus(int i){
        System.out.print("Маршрут №" + (i+1) + '\t');
        System.out.print("Д/н: " + buses.get(i).GetWeekday() + " || ");
        System.out.print("П/о: " + buses.get(i).GetStartPoint() + " || ");
        System.out.print("П/н: " + buses.get(i).GetFinishPoint() + " || ");
        System.out.print("В/о: " + buses.get(i).GetStartTimeHour() + " h. ");
        System.out.print(buses.get(i).GetStartTimeMin() + " m. || ");
        System.out.print("В/п: " + buses.get(i).GetFinishTimeHour() + " h. ");
        System.out.print(buses.get(i).GetFinishTimeMin() + " m.\n");
    }

    public void ShowBusesForStartPoint(String start_point){
        int length = GetLengthBuses();
        boolean isCout = false;
        for (int i = 0; i < length; i++){
            if (buses.get(i).isContainsStartPoint(start_point)){
                ShowCurrentBus(i);
                isCout = true;
            }
        }
        if (!isCout){
            System.out.println("Таких остановок не найдено :(");
        }
    }

    public void ShowBusesForFinishPoint(String finish_point){
        int length = GetLengthBuses();
        boolean isCout = false;
        for (int i = 0; i < length; i++){
            if (buses.get(i).isContainsFinishPoint(finish_point)){
                ShowCurrentBus(i);
                isCout = true;
            }
        }
        if (!isCout){
            System.out.println("Таких остановок не найдено :(");
        }
    }

    public void AddBus(int weekday, String start_point, String finish_point, Calendar start_time, Calendar finish_time){
        buses.add(new Bus(weekday, start_point, finish_point, start_time, finish_time));
    }

    public void RemoveBus(int index){
        buses.remove(index);
    }

    public void SetWeekday(int bus, int weekday){
        buses.get(bus).SetWeekday(weekday);
    }

    public void SetStartPoint(int bus, String start_point){
        buses.get(bus).SetStartPoint(start_point);
    }

    public void SetFinishPoint(int bus, String finish_point){
        buses.get(bus).SetFinishPoint(finish_point);
    }

    public void SetStartTimeHour(int bus, int hour){
        buses.get(bus).SetStartTimeHour(hour);
    }
    public void SetStartTimeMin(int bus, int min){
        buses.get(bus).SetStartTimeMin(min);
    }

    public void SetFinishTimeHour(int bus, int hour){
        buses.get(bus).SetFinishTimeHour(hour);
    }
    public void SetFinishTimeMin(int bus, int min){
        buses.get(bus).SetFinishTimeMin(min);
    }
}