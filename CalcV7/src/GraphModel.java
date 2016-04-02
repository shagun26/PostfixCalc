
import java.util.Stack;


public class GraphModel 
{
	/**
	 * X coordinates
	 */
	public static double[] X = new double[1001];
	
	private double[] y;
	private Stack<double[]> valuation = new Stack<double[]>();
	private ExpressionsParser parser;
	private double xScale = 10;
	
	/**
	 * Instantiate a new GraphModel with the default X-coordinates and XPi-coordinates
	 */
	public GraphModel()
	{
		newGraph(xScale);
	}
	/**
	 * Evaluate an expression for its Y-coordinates, it first checks if Sin or Cos exists within the equation, 
	 * in which it will calculate for a set of x values from -2pi to +2pi, otherwise it will go from -10 to +10
	 * @param expressionsPostFix - the expressions list in Posfix form
	 * @return - the Y-coordinates
	 */
	public double[] calculateValues(Stack<String> expressionsPostFix)
	{
		
		valuation.clear();
		
		for(String next : expressionsPostFix)
		{
			if(next.equals("x"))
				var();
			else if(next.equals("+"))
				sum();
			else if(next.equals("-"))
				sub();
			else if(next.equals("*"))
				mult();
			else if(next.equals("/"))
				div();
			else if(next.equals("SIN("))
				sin();
			else if(next.equals("COS("))
				cos();
			else if(next.equals(Controller.PLUSMINUS))
				negate();
			else
			{
				double[] pushed = {Double.parseDouble(next)};
				valuation.push(pushed);
				num();
			}
				
		}
		return y;
	}
	/**
	 * Adds a constant to the valuation stack
	 */
	public void num()
	{
		parser = new EvaluateNum();
		y = parser.evaluate(valuation);
	}
	
	/**
	 * Perform a division
	 */
	public void div() 
	{
		parser = new EvaluateDiv();
		y = parser.evaluate(valuation);
		
	}
	/**
	 * Perform a negation
	 */
	public void negate() 
	{
		parser = new EvaluateNegate();
		y = parser.evaluate(valuation);
	}
	/**
	 * Add X-coordinates to valuation stack
	 */
	public void var()
	{
		parser = new EvaluateVar();
		y = parser.evaluate(valuation);
	}
	/**
	 * Perform an addition
	 */
	public void sum()
	{
		parser = new EvaluateSum();
		y = parser.evaluate(valuation);
	}
	/**
	 * Perform an subtraction
	 */
	public void sub()
	{
		parser = new EvaluateSub();
		y = parser.evaluate(valuation);
	}
	/**
	 * Perform a multiplication
	 */
	public void mult()
	{
		parser = new EvaluateMult();
		y = parser.evaluate(valuation);
	}
	/**
	 * Perform a sine
	 */
	public void sin()
	{
		parser = new EvaluateSine();
		y = parser.evaluate(valuation);
	}
	/**
	 * Perform a cosine
	 */
	public void cos()
	{
		parser = new EvaluateCos();
		y = parser.evaluate(valuation);
	}
	
	public double getXScale()
	{
		return xScale/2;
	}
	
	private void newGraph(double XScale)
	{
		int j = 0;
		for(double i = -XScale; j < X.length;)
		{
			X[j++] = i;
			i = i + (XScale) / ((X.length-1)/2);
		}
	
	}

	
}
