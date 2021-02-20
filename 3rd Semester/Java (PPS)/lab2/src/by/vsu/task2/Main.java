package by.vsu.task2;

import java.util.Arrays;

// java D:\Programming\Java\lab2\src\by\vsu\task2\Main.java
public class Main {
    public static void main(String[] args) {
        if (CheckAP(args)){
            System.out.println("good");
        }else {
            System.out.println("no good");
        }
        // Крутой мув
//        StringBuilder FinishArgs = new StringBuilder();
//        FinishArgs.append(args);
//        String[] newArgs = FinishArgs.toString().split(" ");

// coolMUV(args)
        StringBuilder FinishArgs = new StringBuilder();
        Converting(coolMUV(args), FinishArgs);
        System.out.println(FinishArgs);


    }

    public static String[] coolMUV(String[] source){
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < source.length; i++){
            buf.append(source[i]).append(" ");
        }
//        buf.append(Arrays.toString(source));
        return (buf.toString().split(" "));
    }

    public static void Converting(String[] args, StringBuilder FinishArgs){
        for (String arg : args){
            if (SingleCheckArg(arg)) {
                FinishArgs.append("${").append(arg).append("} ");
            }
            else{
                FinishArgs.append(arg).append(" ");
            }
        }
    }

    public static boolean CheckAP(String[] args){
        for (String arg : args) {
            if (SingleCheckArg(arg)) {
                return true;
            }
        }
        return false;
    }

    public static boolean SingleCheckArg(String args){
        String[] counts = args.split("\\.");
        if (counts.length == 4){
            for (String count : counts){
                try {
                    int convert = Integer.parseInt(count);
                } catch (NumberFormatException e){
                    count = null;
                } finally {
                    if (count == null || Integer.parseInt(count) < 0){
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
}