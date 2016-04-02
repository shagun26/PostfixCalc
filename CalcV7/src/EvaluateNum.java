import java.util.Stack;


public class EvaluateNum extends ExpressionsParser 
{

	@Override
	public double[] evaluate(Stack<double[]> valuation) 
	{
		double[] pushed = new double[GraphModel.X.length];
		double[] popped = valuation.pop();
		for(int i = 0; i < pushed.length; i++)
		{
			pushed[i] = popped[0];
		}
		valuation.push(pushed);
		return pushed;
	}

}
