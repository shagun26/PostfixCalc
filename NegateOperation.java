
public class NegateOperation extends SingleOperations 
{

	@Override
	public String execute(double input) 
	{
		double result = input * -1;
		if(isInt(result, (int) result))
			return (int) result + "";
		return result + "";
		
	}

}
