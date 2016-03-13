import java.util.Stack;


public abstract class  ArithmeticOperations
{

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
}
