import java.util.Stack;


public class EvaluateNegate extends ExpressionsParser 
{

	@Override
	public double[] evaluate(Stack<double[]> valuation) 
	{
		// TODO Auto-generated method stub
		double[] num1 = valuation.pop();
		double[] y = new double[GraphModel.X.length];
		
		for(int i = 0; i < y.length; i++)
		{
				y[i] = num1[i] * -1;
		}
		valuation.push(y);
		
		
		return y;
	}

	
}
