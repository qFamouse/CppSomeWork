package task3;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try{
            FileInputStream fileInputStream = new FileInputStream(WayToFile.GConstantFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            BusSchedule SaveSchedule = (BusSchedule) objectInputStream.readObject();
            fileInputStream.close();
            ControlPanel StartDance = new ControlPanel(SaveSchedule);
        }catch (FileNotFoundException e){
            System.out.println("Файл с сохранениями найти не удалось :(");
            System.out.println("Создаю пустой файл-сохранение :)");
            ControlPanel StartDance = new ControlPanel();
        }catch (IOException e){
            System.out.println("Ошибка ввода-вывода. Найденный файл поврежден и не может быть прочитан.");
            System.out.println("Через несколько секунд программа продолжит работу, а файл будет перезаписан.\nЗакройте программу для отмены!");
            try {
                Thread.sleep(5000);
            }catch (InterruptedException t){}
            ControlPanel StartDance = new ControlPanel();
        }catch (ClassNotFoundException e){
            System.out.println("Класс не найден или не может быть загружен. Обратитесь к разработчику");
        }
//        ArrayList<String> Kek = new ArrayList<>();
    }
}
