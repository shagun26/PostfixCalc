import java.util.Stack;


public class EvaluateVar extends ExpressionsParser
{

	@Override
	public double[] evaluate(Stack<double[]> valuation) 
	{	double[] y = new double[21];
	
		for(int i = 0; i < y.length; i++)
			y[i] = GraphModel.X[i];
		
		valuation.push(y);
		return y;
	}
	

}
