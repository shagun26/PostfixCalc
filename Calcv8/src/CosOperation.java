
public class CosOperation extends SingleOperations
{

	@Override
	public double calculate(double input)
	{
		// TODO Auto-generated method stub
		double result = Math.cos(input);
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
