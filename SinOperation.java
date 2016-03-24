
public class SinOperation extends SingleOperations
{

	@Override
	public String execute(double input)
	{
		double result = Math.sin(input);
		if(isInt(result, (int) result))
		{
			return "" + (int) result;
		}
		else if(round(result))
		{
			return "" + (float) result;
		}
		return "" + result;
	}

}
