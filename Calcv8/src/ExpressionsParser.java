import java.util.Stack;


public abstract class ExpressionsParser 
{
	/**
	 * Evaluates the expression based on the character symbol
	 * @param valuation - the valuation stack
	 * @return - the y-coordinates after the evaluation
	 */
	public abstract double[] evaluate(Stack<double[]> valuation);
}

	