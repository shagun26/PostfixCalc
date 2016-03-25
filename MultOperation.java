
public class MultOperation extends BinaryOperations
{

	@Override
	public String execute(double first, double second)
	{
		double result = first * second;
		
		if(isInt(result, (int) result))
		{
			return "" + (int) result;
		}
		
		return "" + result;
		
	}

}
