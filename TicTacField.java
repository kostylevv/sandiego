import java.util.*;

/**
https://hyperskill.org/projects/48/stages/256/implement
**/

class TicTacField {
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
    }
}
