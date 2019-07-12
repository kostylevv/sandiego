import java.util.*;

/**
https://hyperskill.org
TTT stage 4
**/

class TicTacStep {
    public static void main(String[] args) {
      char[][] tt = new char[3][3];
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

        System.out.println("Enter coordinates: ");
        String coordinates = sc.nextLine();

        String coords[] = coordinates.split(" ");
        int x = 0;
        int y = 0;
        try {
          x = Integer.parseInt(coords[0]);
          y = Integer.parseInt(coords[1]);
        } catch (NumberFormatException nfe) {
          System.out.println(nfe.toString());
        }

        if (x < 1 || x > 3 || y < 1 || y > 3) {
          System.out.println("coordinates should be in range 1..3");
          return;
        }

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
}
