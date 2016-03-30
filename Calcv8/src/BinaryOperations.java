import java.util.Stack;


public abstract class BinaryOperations extends ArithmeticOperations
{
	/**
	 * Performs the calculation based on the operation
	 * @param first - one operand
	 * @param second - the other operand
	 * @return - the result of the operation as a String
	 */
	public  abstract  String execute(double first, double second);
	
	/**
	 * Replaces missing operand(s) with a zero for Binary Operations
	 * @param stored_values - the stored_values stack
	 * @param button_history - the button_history stack
	 */
	public void zeroCheckBinary(Stack<Double> stored_values, Stack<String> button_history)
	{
		//If
		if(stored_values.empty())
		{
			stored_values.push((double) 0);
			stored_values.push((double) 0);
			button_history.push("" +  0);
			button_history.push("" +  0);
			
		}
		else if(stored_values.size() == 1)
		{
			stored_values.push((double) 0);
			button_history.push("" +  0);
		}
		
	}
	
	
	
	
	
}
