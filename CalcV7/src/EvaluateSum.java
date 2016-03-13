import java.util.Stack;


public class EvaluateSum extends ExpressionsParser
{
	@Override
	public double[] evaluate(Stack<Double> valuation, double[] y) 
	{
		double num1 = valuation.pop();
		double num2 = valuation.pop();
		
		if(num1 == (double) 'x' && num2 == (double) 'x')
			return y;
		
		if(num1 == (double) 'x')
		{
				for(int i = 0; i < 11; i++)
				{
					y[i] = y[i] + GraphModel.X[i] + num2;
				}
		}
		else
		{
				for(int i = 0; i < 11; i++)
				{
					y[i] = y[i] + GraphModel.X[i] + num1;
				}
		}
			
		valuation.push((double) 'x');
		
		
		return y;
		
	}
}
