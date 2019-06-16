import java.util.*;

/**
https://hyperskill.org/learn/step/3800
**/

class PrettyMatrix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[][] matrix = new char[4][4];
        int i = 0;
        while (i < 4) {
          String word = sc.nextLine();
          for (int j = 0; j < 4; j++) {
            matrix[i][j] = word.charAt(j);
          }
          i++;
        }

        for (int j = 0; j < 3; j++) {
          int curcount = 0;
          char prev = '$';
          for (int k = 0; k < 4; k++) {
            char cur = matrix[j][k];
            if (prev != '$') {
              if (prev == cur) {
                curcount++;
              } else {
                curcount = 0;
              }
            }
            if (matrix[j+1][k] == cur) {
              curcount++;
            } else {
              curcount = 0;
            }
            if (curcount == 3) {
              System.out.println("NO");
              return;
            }
            prev = cur;
          }
        }
        System.out.println("YES");
    }
}
