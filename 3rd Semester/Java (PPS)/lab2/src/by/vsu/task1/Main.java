// javac -cp .;junit.jar;hamcrest-core.jar src\by\vsu\task1\AquationTestCase.java
// java -cp .;junit.jar;hamcrest-core.jar org.junit.runner.JUnitCore by.vsu.task1.AquationTestCase


package by.vsu.task1;

import java.util.Comparator;
import java.util.Arrays;

// psvm - create main object
public class Main {
    public static void main(String[] args) {
        if (!(CheckArray(args))){return;};
        String MergerArgs = MergerString(args);
        System.out.println(MergerArgs + "\n");
        String[] words = MergerArgs.split(" ");

        System.out.println("Source args");
        PrintArray(words);
        System.out.println();

        System.out.println("Sort#1 args");
        Arrays.sort(words, new SortByLength());
        PrintArray(words);
        System.out.println("------------");
        System.out.println("Action count " + SortByLength.count);
        System.out.println();

        System.out.println("Sort#2 args");
        Arrays.sort(words, new SortByLowerCase());
        PrintArray(words);
        System.out.println("------------");
        System.out.println("Action count " + SortByLowerCase.count);

    }






    public static boolean CheckArray(String[] arr) {
        System.out.println("Receiving data..");
        if (ArrayIsEmpty(arr)) { // Check on filled array
            System.out.println("I can't get the entered data ¯\\_(ツ)_/¯");
            return false;
        }
        return true;
    }

    public static String MergerString(String[] arr){
        StringBuilder newArgs = new StringBuilder();
        newArgs.append(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            newArgs.append(" ").append(arr[i]);
        }
        return newArgs.toString();
    }

    public static boolean ArrayIsEmpty(String[] arr){
         return arr.length == 0;

    }

    public static void PrintArray(String[] arr){
        System.out.println("Words:");
        for (String i : arr) {
            System.out.println(i);
        }
    }

}

class SortByLength implements Comparator<String> {
    static Integer count = 0;

    public int compare (String s1, String s2){
        count++;
        return s1.length() - s2.length();
    }
}

class SortByLowerCase implements Comparator<String> {
    static Integer count = 0;

    public int compare (String s1, String s2){
        count++;

        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();

        int low1 = 0;
        int low2 = 0;

        for (int i = 0; i < s1.length() || i < s2.length(); i++){
            if (Character.isLowerCase(c1[i])){
                low1++;
            }
        }

        for (int i = 0; i < s2.length(); i++){
            if (Character.isLowerCase(c2[i])){
                low2++;
            }
        }

        return low1 - low2;
    }
}