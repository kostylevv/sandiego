import java.util.*;

/**
https://hyperskill.org
TTT stage 5
**/

class TicTacValidate {
  private static char[][] tt = new char[3][3];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter cells: ");
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
        //System.out.printf("[%d,%d] %n", x,y);

        tt[3-y][x-1] = 'X';

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

    private static boolean isOccupied(int x, int y) {
      return (tt[3-y][x-1] == 'X' || tt[3-y][x-1] == 'O');
    }


}
class CellOccupiedException extends Exception {}
