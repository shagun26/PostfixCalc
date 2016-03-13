import java.util.Stack;


public  class EvaluateSine extends ExpressionsParser
{

	@Override
	public double[] evaluate(Stack<Double> valuation, double[] y) 
	{
		double num1 = valuation.pop();
		
		if(num1 == (double) 'x')
		{
			for(int i = 0; i < 10; i++)
			{
				y[i] = Math.sin(y[i]);
			}
			valuation.push((double) 'x');
		}
		else
		{
			valuation.push(Math.sin(num1));
		}
		
		return y;
	}
	
}
