/** 
  * Write a function that accepts an array of 10 integers (between 0 and 9), 
  * that returns a string of those numbers in the form of a phone number.  
  *
  * Kata.createPhoneNumber(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}); 
  * returns "(123) 456-7890"
  */

public class CreatePhoneNumber {
  public static String createPhoneNumber(int[] numbers) {
    String phoneNum = "";
    for (int i = 0; i < numbers.length; i++) {
      phoneNum += numbers[i];
    }
    return phoneNum.replaceAll("(\\d{3})(\\d{3})(\\d{4})", "($1) $2-$3");
  }

  public static void main(String[] args) {
    System.out.println(createPhoneNumber(
      new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}).equals("(123) 456-7890"));
  }
}


