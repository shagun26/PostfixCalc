
public class SinOperation extends SingleOperations
{

	@Override
	public double calculate(double input)
	{
		
	
		//System.out.println(second_number);

		double result = Math.sin(input);
		if(Math.abs(result) < Double.MIN_VALUE)
		{
			result = 0;
		}
		else if ( (Math.abs(result - 0.5)) < Double.MIN_VALUE)
		{
			if(result > 0) result = 0.5;
			if(result < 0) result = -0.5;
		}
		
		return result;
		
		
	}

}
