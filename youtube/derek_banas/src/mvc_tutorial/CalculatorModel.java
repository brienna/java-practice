package mvc_tutorial;

/**
 * 
 * Holds data and performs calculations. Simplest part of the MVC. 
 * Doesn't know about the existence of the view.
 * 
 */
public class CalculatorModel {
	// Holds the sum of the numbers entered in the view
	private int calculationValue;
	
	// Perform a calculation
	public void addTwoNumbers(int firstNumber, int secondNumber) {
		calculationValue = firstNumber + secondNumber;
	}
	
	// Provide access to data
	public int getCalculation() {
		return calculationValue;
	}
}
