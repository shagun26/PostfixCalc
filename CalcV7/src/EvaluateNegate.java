import java.util.Stack;


public class EvaluateNegate extends ExpressionsParser 
{

	@Override
	public double[] evaluate(Stack<double[]> valuation) 
	{
		// TODO Auto-generated method stub
		double[] num1 = valuation.pop();
		double[] y = new double[11];
		
		for(int i = 0; i < 11; i++)
		{
				y[i] = num1[i] * -1;
		}
		valuation.push(y);
		
		//else
		//{
			//double[] result = {num1[0] * -1};
			//valuation.push(result);
		//}
		
		return y;
	}

	
}
