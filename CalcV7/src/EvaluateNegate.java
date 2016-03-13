import java.util.Stack;


public class EvaluateNegate extends ExpressionsParser 
{

	@Override
	public double[] evaluate(Stack<Double> valuation, double[] y) 
	{
		// TODO Auto-generated method stub
		double num1 = valuation.pop();
		
		if(num1 == (double) 'x')
		{
			for(int i = 0; i < 10; i++)
			{
				y[i] =y[i] * -1;
			}
			valuation.push((double) 'x');
		}
		else
		{
			valuation.push(num1 * -1);
		}
		
		return y;
	}

	
}
