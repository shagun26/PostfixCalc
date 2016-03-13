
public class SinOperation extends SingleOperations
{

	@Override
	public double calculate(double input)
	{
		
	
		//System.out.println(second_number);

		double result = Math.sin(input);
		if(Math.abs(result) < Math.pow(10, -6))
		{
			result = 0;
		}
		else if ( (Math.abs(result - 0.5)) < Math.pow(10, -6))
		{
			if(result > 0) result = 0.5;
			if(result < 0) result = -0.5;
		}
		
		return result;
		
		
	}

}
