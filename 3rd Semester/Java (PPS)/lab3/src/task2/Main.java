package task2;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        String[][] maxtrix = new String[1][1];
        int MatrixLength;
        try{
            Reader reader = new FileReader("D:\\Programming\\Java\\lab3\\src\\task2\\matrix.csv");
            BufferedReader buffReader = new BufferedReader(reader);
            // Get matrix length
            String bufStr = buffReader.readLine();
            String[] bufArrayStr = bufStr.split(";");
            MatrixLength = bufArrayStr.length;
            // create matrix
            maxtrix = new String[MatrixLength][MatrixLength];

//            buffReader = new BufferedReader(reader);

            int counter = 0;
            for (int i = 0; bufStr != null; i++){
                maxtrix[i] = bufStr.split(";");
                bufStr = buffReader.readLine();
            }

            for (int i = 0; i < MatrixLength; i++){
                for (int k = 0; k < MatrixLength; k++){
                    System.out.print(maxtrix[i][k] + " ");
                }
                System.out.print("\n");
            }

            buffReader.close();

        }catch (FileNotFoundException e){
            System.out.println("Файл не найден");
        }catch (IOException e){
            System.out.println("Ошибка ввода-вывода");
        }

        ArraySettings FeSettings = new ArraySettings(maxtrix);
        System.out.print(FeSettings.GetMinElement());
        System.out.print(FeSettings.GetMaxElement());

    }
}
