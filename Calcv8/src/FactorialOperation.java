
public class FactorialOperation extends SingleOperations
{

	@Override
	public double calculate(double input)
	{
		// TODO Auto-generated method stub
		double result = 1;
		for(int i = 2; i < input + 1; i++)
		{
				
				result = result * i;
		}
		
		return result;
	}

}
