import java.util.Stack;


public abstract class  ArithmeticOperations
{
	//Rounding figure
	private static final double MIN_VALUE = 0.0000000000001;
	/**
	 * Replaces one missing operand with a zero for
	 * Single Operations
	 * @param stored_values - the stored_values stack
	 * @param button_history -  the button_history stack
	 */
	public void zeroCheckSingle(Stack<Double> stored_values, Stack<String> button_history)
	{
		//If no values in system
		if(stored_values.empty())
		{		
			stored_values.push((double) 0);
			button_history.push("" +  0);
			System.out.println("Not expr");	
		}
				
	}
	/**
	 * Determines whether the double and integer
	 * representation of a value are the same
	 * @param double_val - the the double representation of the value
	 * @param int_val - the integer representation of the value
	 * @return - true if the representations have no significant difference.
	 * 			 false otherwise
	 */
	public static boolean isInt(double double_val, int int_val)
	{
		if(Math.abs(double_val) - Math.abs(int_val) < MIN_VALUE )
			return true;
		System.out.println(Math.abs(double_val) - Math.abs(int_val));
		return false;
	}
	/**
	 * Determines whether the rounded value of an input
	 * is significantly different from the actual value
	 * @param input - the value
	 * @return - true if there is no significant difference
	 * 			 false otherwise
	 */
	public boolean round(double input)
	{
		float round = (float) input;
		if(Math.abs(round - input)< MIN_VALUE )
			return true;
		return false;
	}
	
}
