package task1;

import java.util.Scanner;
import java.util.Random;

public class ContolPanel {
    private double[] array;
    private boolean arrayIsFull = false;
    private int length = 0;

    private Scanner scanner = new Scanner(System.in);

    // --------Методы--------------------------------
    private void FillingArrayByRandom(int length){
    Random random = new Random();
    this.length = length;
    array = new double[length];
    for (int i = 0; i < length; i++){
    array[i] = random.nextDouble();
        }
    System.out.println("Массив из был создан и заполнен случайными элементами. Количество элементов - " + length);
    arrayIsFull = true;
    }

    private void FillingArrayByKeyboard(int length){
        this.length = length;
        array = new double[length];
        for (int i = 0; i < length; i++){
            System.out.print("array[" + i +"] = ");
            array[i] = scanner.nextDouble();
        }
        System.out.println("Массив был создан и заполнен. Количество элементов - " + length);
        arrayIsFull = true;
    }

    private void ShowArray(){
        for (int i = 0; i < length; i++){
            System.out.println("array[" + i +"] = " + array[i]);
        }
    }

    private double ArrayProgressing(){
    double PI = Math.PI;
    double limit = 0.00001;
    double composition = 1;
    int shortLenght = length - 1;

    for (int i = 1; i < shortLenght; i++){
    if ((Math.abs(array[i]) - PI) <= limit){
        composition *= array[i];
    }
    }

    if (composition == 1){
        return 0;
    }

    return composition;
    }
    private boolean foundElement = false;
    private void ArraySettings(){
        double[] bufArray = array.clone();
        int counter = 0;
        // Поиск элементов уд условию
        for (int i = 0; i < length; i++){

            String current = Double.toString(Math.abs(bufArray[i]));
            int evenDigit = 0;
            int oddDigit = 0;
            for (int k = 0; k < current.length(); k++){
                if (current.charAt(k) == '.'){
                    break;
                }
                int add = Character.getNumericValue(current.charAt(k));
                if (k % 2 == 0){
                    evenDigit +=  add;
                }
                else{
                    oddDigit += add;
                }
            }
            if (evenDigit == oddDigit){
                bufArray[i] = Double.NaN;
                counter++;
                foundElement = true;
            }
        }
        if (foundElement){
        int oldLength = length;
        length = length - counter;
        array = new double[length];
        int arrayCounter = 0;
        for (int i = 0; i < oldLength; i++){
            if (!Double.isNaN(bufArray[i])){
                array[arrayCounter] = bufArray[i];
                arrayCounter++;
            }
        }
        }
    }
    // ------------------------------------------
    ContolPanel(){
        ShowText();
    }

    public void ShowText(){
    System.out.println("--- Array Menu ---");
    System.out.println("1. Заполнение массива случайным образом");
    System.out.println("2. Ввод элементов массива с клавиатуры");
    System.out.println("3. Вывод элементов массива на экран");
    System.out.println("4. Обработка массива");
    System.out.println("5. Изменение массива");
    System.out.println("6. Выход из программы");
    System.out.println("-> Выберите необходимый пункт");
    System.out.println("------------------");

    ActionPanel(scanner.nextInt());
    }

    public void ActionPanel(int select){
        if (select == 1){
            if (arrayIsFull == false){
                System.out.println("Введите желаемое количество элементов массива");
                FillingArrayByRandom(scanner.nextInt());
                ShowText();
            }
            else{
                System.out.println("Массив уже заполнен!");
                ShowText();
            }
        }
        else if (select == 2){
            if (arrayIsFull == false){
                System.out.println("Введите желаемое количество элементов массива");
                FillingArrayByKeyboard(scanner.nextInt());
                ShowText();
            }
            else {
                System.out.println("Массив уже заполнен!");
                ShowText();
            }
        }
        else if (select == 3){
            if (arrayIsFull == false){
                System.out.println("Массив не инициализирован! Создайте массив прежде чем его выводить!");
                ShowText();
            }
            else{
                ShowArray();
                ShowText();
            }
        }
        else if (select == 4){
            if (arrayIsFull == false){
                System.out.println("Массив не инициализирован! Создайте массив прежде чем его обрабатывать!");
                ShowText();
            }
            else {
                double Result = ArrayProgressing();
                if (Result != 0){
                    System.out.println("Обработка массива завершена. Произведение равно " + Result);
                    ShowText();
                }
                else {
                    System.out.println("Обработка массива не дала никаких результатов, ни один из элементов не удовлетворяет условию задачи");
                    ShowText();
                }
            }
        }
        else if (select == 5){
            if (arrayIsFull == false){
                System.out.println("Массив не инициализирован! Создайте массив прежде чем его изменять!");
                ShowText();
            }
            else {
                ArraySettings();
                if (foundElement){
                    System.out.println("Массив был изменен согласно условию задачи");
                    ShowText();
                }
                else{
                    System.out.println("После проверки массива не удалось обнаружить элементов удовлетворяющих условию \n Массив остался прежним");
                    ShowText();
                }
            }
        }
        else if (select == 6){

        }
        else {
            System.out.println("Неизвестная команда!");
            ShowText();
        }
    }




}
