import java.util.Stack;


public class EvaluateVar extends ExpressionsParser
{

	@Override
	public double[] evaluate(Stack<double[]> valuation, double[] y) 
	{
		valuation.push(GraphModel.X);
		return GraphModel.X;
	}
	

}
