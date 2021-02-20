// package by.vsu;
package quest_two;

// import static java.lang.Math.pow;
// import java.lang.FdLibm.Pow;

public class task_two{
    public static void main(String []args){
        double x = Double.parseDouble(args[0]);
        int h = Integer.parseInt(args[1]);

        if(!CheckX(x)){
            System.out.println("Error code 1");
        }
        else{
            System.out.println("Left Side: " + GetResultLeftSide(x) + "\n");
            System.out.println("Right Side: " + GetResultRitghSide(x, h) + "\n");
        }

        // System.out.println("0 deg " + Math.pow(-1, 0));
        // System.out.println("1 deg " + Math.pow(-1, 1));
        // System.out.println("2 deg " + Math.pow(-1, 2));
        // System.out.println("3 deg " + Math.pow(-1, 3));
        // System.out.println("4 deg " + Math.pow(-1, 4));
    }

    public static boolean CheckX(double x){
        if (x > -1 && x < 1){
            return true;
        }
        else{
            return false;
        }
    }

    public static double GetResultRitghSide(double x, int h){
        double slag = 0;
        double sum = 0;
        for(int i = 0; /*slag < h*/ i < h; i++){
            int ValueDegree = 2*i+1;
            slag = Math.pow(-1, i)*(Math.pow(x, ValueDegree) / ValueDegree);
            // System.out.print("[Right Side] " + sum + " + " + slag);
            sum += slag;
            // System.out.println(" = " + sum + '\n'); 




            // if (i % 2 == 0){
            //     slag = Math.pow(x, ValueDegree) / ValueDegree;
            // }
            // else {
            //     slag = (-(Math.pow(x, ValueDegree) / ValueDegree));
            // }

            // sum += slag;
        }

        return sum;
    }

    public static double GetResultLeftSide(double x){
        return Math.atan(x);
    }

}