import java.util.*;

/**
https://hyperskill.org/learn/step/1926
**/

class MatrixDiag {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter matrix dimension: ");
        int dim = sc.nextInt();
        if (dim > 100 || dim < 0) {
          System.out.println("Dim should be in range 1..n..100");
          return;
        }

        int[][] matrix = new int[dim][dim];
        for (int j = 0; j < dim; j++) {
          for (int k = 0; k < dim; k++) {
            matrix[j][k] = Math.abs(j-k);
          }
        }

        for (int j = 0; j < dim; j++) {
          for (int k = 0; k < dim; k++) {
            System.out.printf("%s ", matrix[j][k]);
          }
          System.out.println();
        }
    }
}
