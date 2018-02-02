/* Your task is to sort a given string. Each word in the String will contain a single number. 
 * This number is the position the word should have in the result.
 * Note: Numbers can be from 1 to 9. So 1 will be the first word (not 0).
 * If the input String is empty, return an empty String. The words in the input String will 
 * only contain valid consecutive numbers.
 * For an input: "is2 Thi1s T4est 3a" the function should return "Thi1s is2 3a T4est"
 */

class YourOrderPlease {

  public static String order(String words) {
    if (words.length() == 0) {
      return "";
    } else {
      String[] grams = words.split(" ");
      for (int j = 0; j < grams.length; j++) {
        for (int i = 1; i < grams.length; i++) {
          int firstNum = Integer.parseInt(grams[i-1].replaceAll("[^0-9]", ""));
          int secondNum = Integer.parseInt(grams[i].replaceAll("[^0-9]", ""));
          // If first gram is larger than second gram, swap
          if (firstNum > secondNum) {
            String temp = grams[i-1];
            grams[i-1] = grams[i];
            grams[i] = temp;
          }
        }
      }

      return String.join(" ", grams);
    }
  }
  
  public static void main(String[] args) {
    System.out.println(order("is2 Thi1s T4est 3a"));
    System.out.println(order("4of Fo1r pe6ople g3ood th5e the2"));
  }
  
}
