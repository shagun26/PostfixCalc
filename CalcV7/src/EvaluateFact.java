import java.util.Stack;


public class EvaluateFact extends ExpressionsParser {

	@Override
	public double[] evaluate(Stack<double[]> valuation) 
	{
		double[] num1 = valuation.pop();
		double[] y = new double[GraphModel.X.length];
		
		if(num1.length > 1)
		{
			for(int i = 0; i < y.length; i++)
			{
				;//TODO
			}
			valuation.push(y);
		}
		else
		{
			double[] result = {Math.cos(num1[0])};
			valuation.push(result);
		}
		
		return y;
	}

}

