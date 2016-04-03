
import java.util.Stack;


public class GraphModel 
{
	/**
	 * X coordinates
	 */
	public static double[] X = new double[1001];
	/**
	 * The y coordinates
	 */
	private double[] y;
	/**
	 * The running value of the evaluated expression
	 */
	private Stack<double[]> valuation = new Stack<double[]>();
	//Polymorphic variable
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
	 * Evaluate an expression for its Y-coordinates
	 * (Section 21 in Design Document)
	 * @param expressionsPostFix - the expressions list in Posfix form
	 * @return - the Y-coordinates
	 */
	public double[] calculateValues(Stack<String> expressionsPostFix)
	{
		//Clear valuation for recomputation
		valuation.clear();
		//Evaluate each element
		for(String next : expressionsPostFix)
		{	//Category 2
			if(next.equals("x"))
				var();
			//Category 4
			else if(next.equals("+"))
				sum();
			//Category 4
			else if(next.equals("-"))
				sub();
			//Category 4
			else if(next.equals("*"))
				mult();
			//Category 4
			else if(next.equals("/"))
				div();
			//Category 3
			else if(next.equals("SIN("))
				sin();
			//Category 3
			else if(next.equals("COS("))
				cos();
			//Category 3
			else if(next.equals(Controller.PLUSMINUS))
				negate();
			//Category 1
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
	/**
	 * Returns the X-scaling factor for drawing the graph
	 * @return - the X-scaling factor
	 */
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
