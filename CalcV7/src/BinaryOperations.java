import java.util.Stack;


public abstract class BinaryOperations extends ArithmeticOperations
{

	//public abstract double calculate(double first, double second);
	public abstract String execute(double first, double second);
	
	public void zeroCheckBinary(Stack<Double> stored_values, Stack<String> button_history)
	{
		
		if(stored_values.empty())
		{
			stored_values.push((double) 0);
			stored_values.push((double) 0);
			button_history.push("" +  0);
			button_history.push("" +  0);
			
		}
		if(stored_values.size() == 1)
		{
			stored_values.push((double) 0);
			button_history.push("" +  0);
			
		}
		
	}
	
	
	
	
	
}
