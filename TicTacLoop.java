import java.util.*;

/**
https://hyperskill.org
TTT stage 6
**/

public class TicTacLoop {
    private static char[][] tt = new char[3][3];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int j = 0; j < 3; j++) {
          for (int k = 0; k < 3; k++) {
            tt[j][k] = ' ';
          }
        }
        /*System.out.println("Enter cells: ");
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
        */

        printField();
        boolean finished = false;
        while(!finished) {
          userMove();
          printField();
          String state = checkState();
          switch (state) {
            case "X wins":
            case "O wins":
            case "Impossible":
            case "Draw":
              finished = true;
              System.out.println("Game over. " + state);
              break;
          }
          if (!finished) {
            easyMove();
            printField();
            state = checkState();
            switch (state) {
              case "X wins":
              case "O wins":
              case "Impossible":
              case "Draw":
                finished = true;
                System.out.println("Game over. " + state);
                break;
            }
          } else break;
        }
    }

    private static void easyMove() {
      System.out.println("Making move level \"easy\"");
      Random rand = new Random(123);
      int x = 0;
      int y = 0;
      boolean occupied = true;
      while(occupied) {
        x = rand.nextInt(3)+1;
        y = rand.nextInt(3)+1;
        if (!isOccupied(x,y)) {
          occupied = false;
        }
      }
      tt[3-y][x-1] = 'O';
    }



    private static void userMove() {
      int x = 0;
      int y = 0;
      while (true) {
        try {
          Scanner sc = new Scanner(System.in);
          System.out.println("Enter coordinates: ");
          String coordinates = sc.nextLine();
          String coords[] = coordinates.split(" ");
          int[] validCoordinates = validateCoordinates(coords);
          x = validCoordinates[0];
          y = validCoordinates[1];
          break;
        } catch (NumberFormatException e) {
          System.out.println("You should enter numbers!");
        } catch (IllegalArgumentException e) {
          System.out.println("Coordinates should be from 1 to 3!");
        } catch (CellOccupiedException e) {
          System.out.println("This cell is occupied! Choose another one!");
        } catch (IndexOutOfBoundsException e) {
          System.out.println("Two coordinates are required!");
        }
      }
      tt[3-y][x-1] = 'X';
    }

    private static int[] validateCoordinates(String[] coordinates) throws CellOccupiedException, IllegalArgumentException, NumberFormatException, IndexOutOfBoundsException {
      int x = Integer.parseInt(coordinates[0]);
      int y = Integer.parseInt(coordinates[1]);

      if (x < 1 || x > 3 || y < 1 || y > 3) {
        throw new IllegalArgumentException();
      }
      if (isOccupied(x,y)) throw new CellOccupiedException();
      int[] result = new int[2];
      result[0] = x;
      result[1] = y;
      return result;
    }

    private static void printField() {
      System.out.println("---------");
      for (int j = 0; j < 3; j++) {
        System.out.printf("| ");
        for (int k = 0; k < 3; k++) {
          System.out.printf("%s ", tt[j][k]);
        }
        System.out.println('|');
      }
      System.out.println("---------");
    }

    private static boolean isOccupied(int x, int y) {
      return (tt[3-y][x-1] == 'X' || tt[3-y][x-1] == 'O');
    }

    private static String checkState() {
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
          return "Impossible";
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
          return "Impossible";
      }

      if (xWins > 0) {
          return "X wins";
      }

      if (oWins > 0) {
          return "O wins";
      }

      if (empty > 0) {
          return "Game not finished";

      } else {
        return "Draw";
      }
    }


}
class CellOccupiedException extends Exception {}
