import java.util.Stack;


public  class EvaluateSine extends ExpressionsParser
{

	@Override
	public double[] evaluate(Stack<double[]> valuation, double[] y) 
	{
		double[] num1 = valuation.pop();
		
		if(num1 == EXPR || num1.length > 1)
		{
			for(int i = 0; i < 10; i++)
			{
				y[i] = Math.sin(y[i]);
			}
			valuation.push(y);
		}
		else
		{
			double[] result = {Math.sin(num1[0])};
			valuation.push(result);
			
		}
		
		return y;
	}
	
}
