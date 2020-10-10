package task3;


import java.io.*;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.InputMismatchException;

public class ControlPanel {
    private Scanner scanner = new Scanner(System.in);

    private BusSchedule CrazySchedule;

    private void SaveChanges(){
        try {
            FileOutputStream outputStream = new FileOutputStream(WayToFile.GConstantFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(CrazySchedule);
            objectOutputStream.close();
            outputStream.close();
        }catch (FileNotFoundException e){
            System.out.println("Ошибка в классе ControlPanel. Не может найти файл!");
        }catch (IOException e){
            System.out.println("Ошибка в классе ControlPanel. Ошибка ввода-вывода!");
        }
        System.out.println("[DevInfo] Сохранено!");
    }

    ControlPanel(BusSchedule CrazySchedule){
        System.out.println("hi");
        if (CrazySchedule.busesIsNull()){
            this.CrazySchedule = new BusSchedule();
        }
        else{
            this.CrazySchedule = CrazySchedule;
        }
        PanelMenu();
    }
    ControlPanel(){
        CrazySchedule = new BusSchedule();
        PanelMenu();
    }

    private void PanelMenu(){
        System.out.println("• ══─━━── ⫷Панель редактора расписания⫸ ──══─━━ •");
        System.out.println("1. Показать расписание");
        System.out.println("2. Добавить новый маршрут");
        System.out.println("3. Отредактировать существующий маршрут");
        System.out.println("4. Удалить маршрут");
        System.out.println("5. Вывод по критерию");
        System.out.println("6. Выход из программы");
        System.out.println("• ══─━━── ⫷──══─━━──══─━━──══─━━──══─━━──═⫸ ──══─━━ •");
        System.out.print(":");

        if (scanner.hasNextInt()){
            Action(scanner.nextInt());
        }
        else {
            System.out.println("Вводите исключительно цифры");
            scanner.nextLine();
            PanelMenu();
        }
    }

    private void Action(int index){
        if (index == 1){
            CrazySchedule.ShowBuses();
            SaveChanges();
            PanelMenu();
        }
        else if (index == 2){
            System.out.println("Для отмены отправьте '0'");
            // Data for add
            int weekday;
            String start_point;
            String finish_point;
            Calendar start_time = new GregorianCalendar(2020, Calendar.JANUARY, 25);
            Calendar finish_time = new GregorianCalendar(2020, Calendar.JANUARY, 25);
            // Weekday
            System.out.println("Выберите день недели");
            System.out.println("1.Понедельник 2.Вторник 3.Среда 4.Четверг 5.Пятница 6.Суббота 7.Воскресенье");
            weekday = scanner.nextInt() - 1;
            if (weekday == -1){
                PanelMenu();
            }
            else if (weekday > 6 || weekday < 0){
                System.out.println("Ошибка ввода! Попробуйте снова");
                Action(2);
            }
            // start_point
            System.out.println("Укажите пункт отправления");
            start_point = scanner.next();
            // finish_point
            System.out.println("Укажите пункт назначения");
            finish_point = scanner.next();
            // TIME
            System.out.println("Укажите время отправления");
            System.out.print("Часов: "); start_time.set(Calendar.HOUR, scanner.nextInt());
            System.out.print("Минут: "); start_time.set(Calendar.MINUTE, scanner.nextInt());
            System.out.println("Укажите время прибытия");
            System.out.print("Часов: "); finish_time.set(Calendar.HOUR, scanner.nextInt());
            System.out.print("Минут: "); finish_time.set(Calendar.MINUTE, scanner.nextInt());
            if (start_time.get(Calendar.HOUR) > finish_time.get(Calendar.HOUR)){
                System.out.println("Время отправления больше чем время прибытия");
                System.out.println("Попробуйте снова");
                Action(2);
            }
            else if (start_time.get(Calendar.HOUR) == finish_time.get(Calendar.HOUR)){
                if (start_time.get(Calendar.MINUTE) > finish_time.get(Calendar.MINUTE)){
                    System.out.println("Время отправления больше чем время прибытия");
                }
            }
            /////////////////////////////
            CrazySchedule.AddBus(weekday, start_point, finish_point, start_time, finish_time);
            SaveChanges();
            PanelMenu();
        }
        else if (index == 3){
            // GetCurrentBus
            System.out.println("Какой маршрут будем редактировать? 0 - Отмена");
            int way = scanner.nextInt() - 1;
            if (way == -1){
                PanelMenu();
            }
            else if (CrazySchedule.GetLengthBuses() == 0){
                System.out.println("Не могу найти маршруты");
            }
            else if (way > CrazySchedule.GetLengthBuses() || way < 0){
                System.out.println("Ошибка ввода! Попробуйте снова");
                Action(3);
            }
            // Edit
            System.out.println("Какие данные будем редактировать?");
            System.out.println("1. День недели\n2. Пункт отправления\n3. Пункт назначения\n4. Время отправления\n5. Время прибытия");
            int edit = scanner.nextInt();
            if (edit < 1 || edit > 5){
                System.out.println("Ошибка ввода! Попробуйте снова");
                Action(3);
            }
            if (edit == 1){
                System.out.println("Выберите день недели");
                System.out.println("1.Понедельник 2.Вторник 3.Среда 4.Четверг 5.Пятница 6.Суббота 7.Воскресенье");
                CrazySchedule.SetWeekday(way, scanner.nextInt() - 1);
            }
            else if (edit == 2){
                System.out.println("Укажите пункт отправления");
                CrazySchedule.SetStartPoint(way, scanner.next());
            }
            else if (edit == 3){
                System.out.println("Укажите пункт назначения");
                CrazySchedule.SetFinishPoint(way, scanner.next());
            }
            else if (edit == 4){
                System.out.println("Укажите время отправления");
                System.out.print("Часов: ");
                int hour = scanner.nextInt();
                if (hour < 0 || hour > 23){
                    System.out.println("Ошибка ввода! Попробуйте снова");
                    Action(3);
                }
                System.out.print("Минут: ");
                int min = scanner.nextInt();
                if (min < 0 || min > 59){
                    System.out.println("Ошибка ввода! Попробуйте снова");
                    Action(3);
                }
                CrazySchedule.SetStartTimeHour(way, hour);
                CrazySchedule.SetStartTimeMin(way, min);
            }
            else {
                System.out.println("Укажите время прибытия");
                int hour = scanner.nextInt();
                if (hour < 0 || hour > 23){
                    System.out.println("Ошибка ввода! Попробуйте снова");
                    Action(3);
                }
                System.out.print("Минут: ");
                int min = scanner.nextInt();
                if (min < 0 || min > 59){
                    System.out.println("Ошибка ввода! Попробуйте снова");
                    Action(3);
                }
                CrazySchedule.SetFinishTimeHour(way, hour);
                CrazySchedule.SetFinishTimeMin(way, min);
            }
            SaveChanges();
            PanelMenu();
        }
        else if (index == 4){
            System.out.println("Введите номер маршрута согласно списку | 0 - Отмена");
            int way = scanner.nextInt() - 1;
            if (way == -1){
                PanelMenu();
            }
            else if (way < 0 || way > CrazySchedule.GetLengthBuses()){
                System.out.println("Ошибка ввода! Попробуйте снова");
                Action(4);
            }
            CrazySchedule.RemoveBus(way);
            SaveChanges();
            PanelMenu();
        }
        else if (index == 5){
            System.out.println("Выберите критерий вывода");
            System.out.println("1. По времени отправления");
            System.out.println("2. По времени прибытия");
            System.out.println("3. Вывод автобуса, который отправляется из выбранного пункта");
            System.out.println("4. Вывод автобуса, который приезжает выбранный пункт");

            int cin = scanner.nextInt();
            if (cin < 1 || cin > 4){
                System.out.println("Ошибка ввода! Попробуйте снова");
                Action(5);
            }

            if (cin == 1){
                CrazySchedule.SortBusesForStartTime();
                SaveChanges();
                PanelMenu();
            }
            else if (cin == 2){
                CrazySchedule.SortBusesForFinishTime();
                SaveChanges();
                PanelMenu();
            }
            else if (cin == 3){
                System.out.println("Какой пункт будем искать?");
                CrazySchedule.ShowBusesForStartPoint(scanner.next());
                SaveChanges();
                PanelMenu();
            }
            else if (cin == 4){
                System.out.println("Какой пункт будем искать?");
                CrazySchedule.ShowBusesForFinishPoint(scanner.next());
                SaveChanges();
                PanelMenu();
            }
        }
        else if (index == 6){

        }
        else {
            System.out.println("Неизвестная команда: " + index);
            PanelMenu();
        }
    }
}
