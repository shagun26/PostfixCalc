
public class FactorialOperation extends SingleOperations
{

	
	@Override
	public String execute(double input) 
	{
		if(!isInt(input, (int)(input) )||  input < 0)
		{
			return "NOT DEFINED";
		}
		
		double result = 1;
		
		for(int i = 2; i < input + 1; i++)
		{
				result = result * i;
		}
		
		if(Double.isInfinite(result))
		{
			return "NOT DEFINED";
		}
		
		if(isInt(result, (int)(result) ))
		{
			return "" + (int) result;
		}
		return "" +  result;
	}

}
