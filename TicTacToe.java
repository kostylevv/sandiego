import java.util.*;

/**
https://hyperskill.org
TTT stage 6
**/

public class TicTacToe {
    private char[][] tt;
    private String[] order;
    private int current;
    private boolean gameIsOver;
    public String state;

    public TicTacToe(String[] commands) {
      tt = new char[3][3];
      order = new String[2];
      order[0] = commands[1];
      order[1] = commands[2];
      current = 0;
      state = null;
      gameIsOver = false;

      for (int j = 0; j < 3; j++) {
        for (int k = 0; k < 3; k++) {
          tt[j][k] = ' ';
        }
      }
    }

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
      while(true) {
        System.out.println("Input command: ");
        String[] commands = sc.nextLine().split(" ");
        if (validateCommands(commands)) {
          TicTacToe ticTacToe = new TicTacToe(commands);
          ticTacToe.printField();
          boolean finished = false;
          while(!finished) {
              ticTacToe.nextMove();
              ticTacToe.printField();
              if (ticTacToe.gameIsOver()) {
                System.out.println("Game over. " + ticTacToe.state);
                finished = true;
                break;
              }
            }
        } else if (commands.length == 1 && commands[0].trim().toLowerCase().equals("exit")) {
          return;
        } else {
          System.out.println("Bad parameters!");
        }
      }
    }

    private static boolean validateCommands(String[] commands) {
      if (commands.length != 3) return false;
      if (!commands[0].trim().toLowerCase().equals("start")) return false;
      if (!(commands[1].trim().toLowerCase().equals("easy") || commands[1].trim().toLowerCase().equals("user"))) return false;
      if (!(commands[2].trim().toLowerCase().equals("easy") || commands[2].trim().toLowerCase().equals("user"))) return false;
      return true;
    }

    private CurrentMove getCurrent() {
      CurrentMove cm = new CurrentMove();
      cm.role = order[current];

      if (current == 1) {
        cm.symbol = 'O';
        current = 0;
      } else {
        cm.symbol = 'X';
        current = 1;
      }
      return cm;
    }

    private void nextMove() {
      CurrentMove cm = getCurrent();
      switch (cm.role) {
        case "easy":
          easyMove(cm.symbol);
          break;
        case "user":
          userMove(cm.symbol);
          break;
        default:
          System.out.println("Bad command " + cm.role);
      }
    }

    private void easyMove(char c) {
      System.out.println("Making move level \"easy\"");
      Random rand = new Random(c);
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
      tt[3-y][x-1] = c;
    }

    private void userMove(char c) {
      int x = 0;
      int y = 0;
      while (true) {
        try {
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
      tt[3-y][x-1] = c;
    }

    private int[] validateCoordinates(String[] coordinates) throws CellOccupiedException, IllegalArgumentException, NumberFormatException, IndexOutOfBoundsException {
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

    private void printField() {
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

    private boolean isOccupied(int x, int y) {
      return (tt[3-y][x-1] == 'X' || tt[3-y][x-1] == 'O');
    }

    private boolean gameIsOver(){
      String curState = checkState();
      switch (curState) {
        case "X wins":
        case "O wins":
        case "Impossible":
        case "Draw":
          state = curState;
          gameIsOver = true;
      }
      return gameIsOver;
    }

    private String checkState() {
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
class CurrentMove {
  String role;
  char symbol;
}
