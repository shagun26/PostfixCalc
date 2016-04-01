import java.util.Stack;


public class EvaluateSub extends ExpressionsParser {

	@Override
	public double[] evaluate(Stack<double[]> valuation) 
	{
		double[] num1 = valuation.pop();
		double[] num2 = valuation.pop();
		double[] y = new double[GraphModel.X.length];
		
		
		for(int i = 0; i < y.length; i++)
		{
			y[i] = num2[i] - num1[i];
		}
		
		valuation.push(y);
		
		
		return y;
		
	}
}
