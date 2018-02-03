/* Once upon a time, on a way through the old wild west,…
 * … a man was given directions to go from one point to another. 
 * The directions were "NORTH", "SOUTH", "WEST", "EAST". 
 * Clearly "NORTH" and "SOUTH" are opposite, "WEST" and "EAST" too. 
 * Going to one direction and coming back the opposite direction is a needless effort. 
 * Since this is the wild west, with dreadfull weather and not much water, 
 * it's important to save yourself some energy, otherwise you might die of thirst!
 * 
 * How I crossed the desert the smart way.
 * The directions given to the man are, for example, the following:
 * { "NORTH", "SOUTH", "SOUTH", "EAST", "WEST", "NORTH", "WEST" };
 * You can immediatly see that going "NORTH" and then "SOUTH" is not reasonable, 
 * better stay to the same place! So the task is to give to the man a simplified version of the plan. 
 * A better plan in this case is simply:
 * { "WEST" }
 *
 * Other examples:
 * In ["NORTH", "SOUTH", "EAST", "WEST"], the direction "NORTH" + "SOUTH" is going north 
 * and coming back right away. What a waste of time! Better to do nothing.
 * The path becomes ["EAST", "WEST"], now "EAST" and "WEST" annihilate each other, 
 * therefore, the final result is []
 * In ["NORTH", "EAST", "WEST", "SOUTH", "WEST", "WEST"], "NORTH" and "SOUTH" are 
 * not directly opposite but they become directly opposite after the reduction of "EAST" 
 * and "WEST" so the whole path is reducible to ["WEST", "WEST"].
 *
 * Task:
 * Write a function dirReduc which will take an array of strings and returns an 
 * array of strings with the needless directions removed (W<->E or S<->N side by side).
 */
 
import java.util.ArrayList;
import java.util.Arrays;

class DirectionsReduction {
  public static String[] dirReduc(String[] arr) {
    ArrayList<String> flexibleArr = new ArrayList<String>(Arrays.asList(arr));
    ArrayList<String> ans = flexibleArr;
  
    boolean end = false;
    ArrayList<Boolean> checks = new ArrayList<Boolean>();
    while (!end) {
      for (int i = 1; i < flexibleArr.size(); i++) {
        String firstDir = flexibleArr.get(i-1);
        String secondDir = flexibleArr.get(i);

        if (firstDir.equals("NORTH") && secondDir.equals("SOUTH")) {
          ans.remove(i);
          ans.remove(i-1);
          i = i - 1;
          checks.add(false);
        } else if (firstDir.equals("SOUTH") && secondDir.equals("NORTH")) {
          ans.remove(i);
          ans.remove(i-1);
          i = i - 1;
          checks.add(false);
        } else if (firstDir.equals("WEST") && secondDir.equals("EAST")) {
          ans.remove(i);
          ans.remove(i-1);
          i = i - 1;
          checks.add(false);
        } else if (firstDir.equals("EAST") && secondDir.equals("WEST")) {
          ans.remove(i);
          ans.remove(i-1);
          i = i - 1;
          checks.add(false);
        } else {
          checks.add(true);
        } 
      }
    
      if (!checks.contains(false)) {
        end = true;
      } 
      
      checks.clear();
    }
    
    return ans.toArray(new String[ans.size()]);
  }
  
  public static void main(String[] args) {
    String[] ans = dirReduc(new String[] {"NORTH", "SOUTH", "SOUTH", "EAST", "WEST", "NORTH", "WEST"});
    System.out.println("Answer: " + String.join(" ", ans));
    String[] ans2 = dirReduc(new String[]{"NORTH", "WEST", "SOUTH", "EAST"});
    System.out.println("Answer: " + String.join(" ", ans2));
  }
  
}
