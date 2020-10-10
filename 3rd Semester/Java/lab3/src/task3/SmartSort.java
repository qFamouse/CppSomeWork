package task3;

import java.util.Comparator;

class SortByStartTime implements Comparator<Bus> {
    public int compare (Bus b1, Bus b2){
        int StartH1 = b1.GetStartTimeHour();
        int StartM1 = b1.GetStartTimeMin();

        int StartH2 = b2.GetStartTimeHour();
        int StartM2 = b2.GetFinishTimeMin();

        if (StartH1 == StartH2){
            return StartM1 - StartM2;
        }
        return StartH1 - StartH2;
    }
}

class SortByFinishTime implements Comparator<Bus> {
    public int compare (Bus b1, Bus b2){
        int StartH1 = b1.GetFinishTimeHour();
        int StartM1 = b1.GetFinishTimeMin();

        int StartH2 = b2.GetFinishTimeHour();
        int StartM2 = b2.GetFinishTimeMin();

        if (StartH1 == StartH2){
            return StartM1 - StartM2;
        }
        return StartH1 - StartH2;
    }
}