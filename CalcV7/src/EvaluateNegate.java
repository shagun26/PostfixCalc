import java.util.Stack;


public class EvaluateNegate extends ExpressionsParser 
{

	@Override
	public double[] evaluate(Stack<double[]> valuation, double[] y) 
	{
		// TODO Auto-generated method stub
		double[] num1 = valuation.pop();
		
		if(num1 == EXPR || num1.length > 1)
		{
			for(int i = 0; i < 10; i++)
			{
				y[i] = y[i] * -1;
			}
			valuation.push(y);
		}
		else
		{
			double[] result = {num1[0] * -1};
			valuation.push(result);
		}
		
		return y;
	}

	
}
