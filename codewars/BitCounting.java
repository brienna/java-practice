/**
  * Write a function that takes an (unsigned) integer as input, and returns the 
  * number of bits that are equal to one in the binary representation of that number.
  * Example: The binary representation of 1234 is 10011010010, so the function 
  * should return 5 in this case
  */

public class BitCounting {
    public static int countBits(int n) {
        String binary = Integer.toBinaryString(n);
        
        int ones = 0;
        for (int i = 0; i < binary.length(); i++) {
          if (binary.charAt(i) == '1') {
            ones++;
          }
        }
        
        return ones;
    }

    public static int countBits2(int n) {
        return Integer.bitCount(n);
    }

    public static void main(String[] args) {
        System.out.println(countBits(1234) == 5);
        System.out.println(countBits2(1234) == 5);
        System.out.println(countBits(10) == 2);
        System.out.println(countBits2(10) == 2);
        System.out.println(countBits(9) == 2);
        System.out.println(countBits2(9) == 2);
    }
}