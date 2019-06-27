import java.util.*;

/**
https://hyperskill.org/projects/48/stages/257/implement
**/

class TicTacState {
    public static void main(String[] args) {
      char[][] tt = new char[3][3];
        Scanner sc = new Scanner(System.in);
        //System.out.println("Enter cells: ");
        String word = sc.nextLine();
        if (word.length() != 11) {
          System.out.println("Line should contain exactly 11 characters");
          return;
        }

        int i = 1;
        for (int j = 0; j < 3; j++) {
          for (int k = 0; k < 3; k++) {
            tt[j][k] = word.charAt(i);
            i++;
          }
        }

        System.out.println("---------");
        for (int j = 0; j < 3; j++) {
          System.out.printf("| ");
          for (int k = 0; k < 3; k++) {
            System.out.printf("%s ", tt[j][k]);
          }
          System.out.println('|');
        }
        System.out.println("---------");

        int xWins = 0;
        int oWins = 0;
        int empty = 0;
        int totX = 0;
        int tot0 = 0;

        //check cols
        for (int j = 0; j < 3; j++) {
            int xCols = 0;
            int oCols = 0;
            for (int k = 0; k < 3; k++) {
                if (tt[j][k] == 'O') {
                    oCols++;
                    tot0++;
                } else if (tt[j][k] == 'X') {
                    xCols++;
                    totX++;
                } else {
                    empty++;
                }
            }
            if (oCols == 3) oWins++;
            if (xCols == 3) xWins++;
        }

        if (Math.abs(totX-tot0) >= 2) {
            System.out.println("Impossible");
            return;
        }

        //check rows
        for (int j = 0; j < 3; j++) {
            int xCols = 0;
            int oCols = 0;
            for (int k = 0; k < 3; k++) {
                if (tt[k][j] == 'O') {
                    oCols++;
                } else if (tt[k][j] == 'X') {
                    xCols++;
                }
            }
            if (oCols == 3) oWins++;
            if (xCols == 3) xWins++;
        }

        //check diag
        int xDia = 0;
        int oDia = 0;
        for (int j = 0, k = 0; j < 3; j++, k++) {
                if (tt[k][j] == 'O') {
                    oDia++;
                } else if (tt[k][j] == 'X') {
                    xDia++;
                }

        }
        if (oDia == 3) oWins++;
        if (xDia == 3) xWins++;

        //check antiDiag
        xDia = 0;
        oDia = 0;
        for (int j = 0, k = 2; j < 3; j++, k--) {
                if (tt[j][k] == 'O') {
                    oDia++;
                } else if (tt[j][k] == 'X') {
                    xDia++;
                }

        }
        if (oDia == 3) oWins++;
        if (xDia == 3) xWins++;

        if (xWins > 0 && oWins > 0) {
            System.out.println("Impossible");
            return;
        }

        if (xWins > 0) {
            System.out.println("X wins");
            return;
        }

        if (oWins > 0) {
            System.out.println("O wins");
            return;
        }

        if (empty > 0) {
            System.out.println("Game not finished");
            return;
        } else {
            System.out.println("Draw");
            return;
        }

    }
}
