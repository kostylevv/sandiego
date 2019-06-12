/**
https://hyperskill.org/learn/daily/3790
For a given string you need to find a length of the maximum sequence containing only same characters.
**/
import java.util.*;
class MaxSeq {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        if (word.length() == 0 || word.length() == 1) {
          System.out.println(word.length());
          return;
        }
        char cur = word.charAt(0);
        int curCharLen = 1;
        int maxCharLen = 1;

        for (int i = 1; i < word.length(); i++) {
            if (cur == word.charAt(i)) {
              curCharLen++;
            } else {
              cur = word.charAt(i);
              curCharLen = 1;
            }
            if (curCharLen > maxCharLen) {
              maxCharLen = curCharLen;
            }
        }
        System.out.println(maxCharLen);
    }
}
