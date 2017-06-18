/* 
 * Given an array of one's and zero's convert the equivalent binary value to an integer.
 * Eg: [0, 0, 0, 1] is treated as 0001 which is the binary representation of 1
 * 
 * Examples:
 * Testing: [0, 0, 0, 1] ==> 1
 * Testing: [0, 0, 1, 0] ==> 2
 * Testing: [0, 1, 0, 1] ==> 5
 * Testing: [1, 0, 0, 1] ==> 9
 * Testing: [0, 0, 1, 0] ==> 2
 * Testing: [0, 1, 1, 0] ==> 6
 * Testing: [1, 1, 1, 1] ==> 15
 * Testing: [1, 0, 1, 1] ==> 11
 *
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class OnesAndZeroes {
    public static int ConvertBinaryArrayToInt(List<Integer> binary) {
      String s = ""; 
      for (Integer num : binary) {
        s = s + num;
      }
      return Integer.parseInt(s, 2);
    }

    public static void main(String[] args) {
        System.out.println(ConvertBinaryArrayToInt(new ArrayList<>(Arrays.asList(0,0,0,1))) == 1);
        System.out.println(ConvertBinaryArrayToInt(new ArrayList<>(Arrays.asList(1,1,1,1))) == 15);
        System.out.println(ConvertBinaryArrayToInt(new ArrayList<>(Arrays.asList(1,0,0,1))) == 9);
    }
}