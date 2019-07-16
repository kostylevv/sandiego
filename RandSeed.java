import java.util.*;

/**
Find the seed between A and B inclusively that produces
N pseudorandom numbers from 0 inclusive to K exclusive and
have the maximum of these N numbers be the minimum among
all maximums of other seeds in this range.

For example, we have N=4, A=7, B=9, K=100.
Suppose, for the seed 7 we have sequence 45, 99, 23, 67 - the maximum is 99.
For the seed 8, we have 64, 34, 23, 9 - the maximum is 64.
For the seed 9, we have 78, 34, 0, 11 - the maximum is 78.
The minimum among these maximums is 64 form the seed 8, therefore,
in this case, the program should output 8.

If there are some seeds with equal minimal maximums,
you should output the seed which is less than all other seeds.

The input contains numbers A, B, N, K in a single line.

You should output 2 numbers - a seed and it's maximum.

https://hyperskill.org/learn/step/4940

**/

public class RandSeed {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String params[] = line.split(" ");
        int a = Integer.parseInt(params[0]);
        int b = Integer.parseInt(params[1]);
        int n = Integer.parseInt(params[2]);
        int k = Integer.parseInt(params[3]);
        int seedsQty = b - a + 1;
        int[] maxSeedValues = new int[seedsQty];
        for (int i = a; i <= b; i++) {
          Random rand = new Random(i);
          int max = -1;
          for (int j = 1; j <= n; j++) {
            int r = rand.nextInt(k);
            if (r > max) {
              max = r;
            }
          }
          maxSeedValues[i-a] = max;
        }

        int seedOfInterest = 0;
        int minGeneratedValue = maxSeedValues[seedOfInterest];
        for (int i = 1; i < maxSeedValues.length; i++) {
          if (maxSeedValues[i] < minGeneratedValue){
            minGeneratedValue = maxSeedValues[i];
            seedOfInterest = i + a;
          }
        }
        System.out.println(seedOfInterest);
        System.out.println(minGeneratedValue);
        System.out.println(Arrays.toString(maxSeedValues));

    }

}
