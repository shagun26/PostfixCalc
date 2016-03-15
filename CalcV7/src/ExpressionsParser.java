import java.util.Stack;


public abstract class ExpressionsParser 
{

	public abstract double[] evaluate(Stack<double[]> valuation, double[] y);
	public static final double[] EXPR = {'x'};
}

	