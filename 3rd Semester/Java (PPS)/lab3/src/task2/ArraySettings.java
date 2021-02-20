package task2;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.IntSummaryStatistics;

public class ArraySettings {
    private int[][] matrix;
    private int length;
    private boolean MixMax = false;

    private int[] min = new int[2];
    private int[] max = new int[2];

    private void CreateMinMax(){

        // Search min
        for (int i = 1; i < length; i++){
            for (int k = 1; k < length; k++){
                if (matrix[i][k] < matrix[i-1][k-1]){
                    min[0] = i;
                    min[1] = k;
                }
            }
        }
        // Search max
        int maxVal = matrix[min[0]][min[1]];
        for (int i = 0; i < min[0]; i++){
            for (int k = 0; k < length; k++){
                if (maxVal < matrix[i][k] && k > min[1]){
                    max[0] = i;
                    max[1] = k;
                }
            }
        }
        MixMax = true;
    }

    ArraySettings(String[][] matrix){
    this.length = matrix.length;
    this.matrix = new int[length][length];
    for (int i = 0; i < length; i++){
        for (int k = 0; k < length; k++){
            this.matrix[i][k] = Integer.parseInt(matrix[i][k]);
        }
    }
        CreateMinMax();
    }

    public int GetMinElement(){
        return matrix[min[0]][min[1]];
    }

    public int GetMaxElement(){
        if (matrix[max[0]][max[1]] == matrix[min[0]][min[1]]){
            return -1;
        }

        return matrix[max[0]][max[1]];
    }

    public void ShowMinElementIndex(){
        System.out.print(min[0] + " " + min[1]);

    }
    public void ShowMaxElementIndex(){
        System.out.print(max[0] + " " + max[1]);
    }
}
