
public class MinusOperation extends BinaryOperations
{

	@Override
	public double calculate(double first, double second)
	{
		// TODO Auto-generated method stub
		return second - first;
	}

	@Override
	public String execute(double first, double second)
	{
		double result = second - first  ;
		
		if(isInt(result, (int) result))
		{
			return "" + (int) result;
		}
		
		return "" + result;
		
	}

}
