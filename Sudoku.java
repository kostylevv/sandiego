import java.util.*;

/**
https://hyperskill.org/learn/step/3785
5 8 9 6 7 4 2 1 3
7 4 3 1 8 2 9 5 6
1 2 6 9 5 3 8 7 4
9 3 5 4 2 1 7 6 8
4 1 2 8 6 7 3 9 5
6 7 8 3 9 5 1 4 2
8 6 4 2 1 9 5 3 7
3 9 7 5 4 8 6 2 1
2 5 1 7 3 6 4 8 9
**/

import java.util.*;

public class Sudoku {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        if (n <= 0 || n > 10) {
            System.out.println("NO");
            return;
        }
        int nSq = n*n;
        int[][] matrix = new int[nSq][nSq];
        for(int i = 0; i < nSq; i++) {
          String word = sc.nextLine();
          String[] words = word.split(" ");
          for (int j = 0; j < nSq; j++) {
            matrix[i][j] = Integer.parseInt(words[j]);
          }
        }
        boolean sudoku = true;
        for (int x = 0; x <= nSq-n; x+=n) {
          for (int y = 0; y <= nSq-n; y+=n) {
            Checker check = new Checker(nSq);
            for (int my = y; my <= y+n-1; my++) {
              for (int mx = x; mx <= x+n-1; mx++) {
                if (!check.checkMember(matrix[my][mx])) {
                  sudoku = false;
                  break;
                }
              }
            }
            if (!check.checkFinally()) {
              sudoku = false;
              break;
            }
          }
        }

        for (int x = 0; x < nSq; x++) {
          Checker check = new Checker(nSq);
          for (int y = 0; y < nSq; y++) {
            if (!check.checkMember(matrix[y][x])){
              sudoku = false;
              break;
            }
          }
          if (!check.checkFinally()) {
            sudoku = false;
            break;
          }
        }

        if (sudoku){
          System.out.println("YES");
        } else {
          System.out.println("NO");
        }
    }
}

class Checker {
  private boolean[] state;
  Checker(int i) {
    state = new boolean[i+1];
    state[0] = true;
    for (int k = 1; k <= i; k++) {
      state[k] = false;
    }
  }

  boolean checkMember(int i) {
    if (i > 0 && i < state.length && state[i] == false) {
      state[i] = true;
      return true;
    } else {
      return false;
    }
  }

  boolean checkFinally() {
    for (boolean b : state) {
      if (b == false) {
        return false;
      }
    }
    return true;
  }
}
