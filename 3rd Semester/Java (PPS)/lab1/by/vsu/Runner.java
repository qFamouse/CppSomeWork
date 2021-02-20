package by.vsu;

public class Runner{
    public static void main(String []args){
        /* System.out.println("Ткач | Tkach"); */
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        int c = Integer.parseInt(args[2]);
        System.out.println(aquation(a,b,c));
    }

    public static double aquation(int a, int b,  int c){
        if (c == 0){
            if ((a+b) < 0){
                return Double.NEGATIVE_INFINITY;
            }
            else {
                return Double.POSITIVE_INFINITY;
            }
        }
        return ((a+b)/c)-4*c;
    }

}

