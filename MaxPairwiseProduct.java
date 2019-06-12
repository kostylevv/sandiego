/**
https://www.coursera.org/learn/algorithmic-toolbox/programming/Xscmz/programming-assignment-1-maximum-pairwise-product/submission
Maximum Pairwise Product Problem
Find the maximum product of two distinct numbers
in a sequence of non-negative integers.
Input: A sequence of non-negative
integers.
Output: The maximum value that
can be obtained by multiplying
two different elements from the sequence.
**/
import java.util.*;
import java.io.*;

public class MaxPairwiseProduct {
    static long getMaxPairwiseProduct(int[] numbers) {
        int max_product = 0;
        int maxIndexOne = 0;
        int maxOne = 0;
        int maxTwo = 0;

        for (int i = 0; i < numbers.length; i++) {
          if (numbers[i] > maxOne) {
            maxOne = numbers[i];
            maxIndexOne = i;
          }
        }

        for (int i = 0; i < numbers.length; i++) {
          if (i != maxIndexOne && numbers[i] > maxTwo) {
            maxTwo = numbers[i];
          }
        }

        return (long)maxOne*maxTwo;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }
        System.out.println(getMaxPairwiseProduct(numbers));
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new
                    InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

}
