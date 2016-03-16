import java.util.Stack;


public abstract class  ArithmeticOperations
{
	private static final double MIN_VALUE = 0.0000000000001;
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
	
	public boolean isInt(double double_val, int int_val)
	{
		if(Math.abs(double_val) - Math.abs(int_val) < MIN_VALUE )
			return true;
		System.out.println(Math.abs(double_val) - Math.abs(int_val));
		return false;
	}
	
	public boolean round(double input)
	{
		float round = (float) input;
		if(Math.abs(round - input)< MIN_VALUE )
			return true;
		return false;
	}
	
}
