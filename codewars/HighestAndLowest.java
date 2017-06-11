/**
  * In this little assignment you are given a string of space separated 
  * numbers, and have to return the highest and lowest number.
  *
  * Example:
  * HighAndLow("1 2 3 4 5") // return "5 1"
  * HighAndLow("1 2 -3 4 5") // return "5 -3"
  * HighAndLow("1 9 3 4 -5") // return "9 -5"
  * Notes:
  *
  * All numbers are valid Int32, no need to validate them.
  * There will always be at least one number in the input string.
  * Output string must be two numbers separated by a single space, 
  * and highest number is first.
  */

import java.util.Arrays;

public class HighestAndLowest {
  public static String HighAndLow(String numbers) {
    String[] sNums = numbers.split(" ");
    int[] iNums = new int[sNums.length];
    for (int i = 0; i < sNums.length; i++) {
      iNums[i] = Integer.parseInt(sNums[i]); 
    }
    Arrays.sort(iNums);
    return iNums[iNums.length - 1] + " " + iNums[0];
  }

  public static void main(String[] args) {
    String ans = HighAndLow("8 3 -5 42 -1 0 0 -9 4 7 4 -4");
    System.out.println(ans.equals("42 -9"));
  }
}