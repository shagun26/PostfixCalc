
public class DivideOperation extends BinaryOperations
{

	@Override
	public String execute(double first, double second)
	{
		double result = second / first;
		
		if(isInt(result, (int) result))
		{
			return "" + (int) result;
		}
		
		return "" + result;
		
	}

}
