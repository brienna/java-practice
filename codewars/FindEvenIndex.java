/** 
  * You are going to be given an array of integers. Your job is to take that 
  * array and find an index N where the sum of the integers to the left of N 
  * is equal to the sum of the integers to the right of N. If there is no index
  * that would make this happen, return -1.
  *
  * Let's say you are given the array {1,100,50,-51,1,1}:
  * Your function will return the index 1, because at the 1st position of the array, 
  * the sum of left side of the index ({1}) and the sum of the right side of the 
  * index ({50,-51,1,1}) both equal 1.
  */

public class FindEvenIndex {
    public static int findEvenIndex(int[] arr) {
        // Loop through array
        for (int i = 0; i < arr.length; i++) {
            // Sum elements to the left of current index
            int leftSum = 0; 
            for (int l = 0; l < i; l++) {
                leftSum += arr[l];
            }
            // Sum elements to the right of current index
            int rightSum = 0; 
            for (int r = i + 1; r < arr.length; r++) {
                rightSum += arr[r];
            }
            // If left sum is equal to right sum, return current index
            if (leftSum == rightSum) {
                return i;
            }
        }

        // Otherwise return -1
        return -1;

    }

    public static void main(String[] args) {
        System.out.println(findEvenIndex(new int[] {2824, 1774, -1490, -9084, -9696, 23094}));
        System.out.println(findEvenIndex(new int[] {20,10,30,10,10,15,35}));
    }
}