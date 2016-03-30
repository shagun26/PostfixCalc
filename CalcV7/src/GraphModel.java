
import java.util.Stack;


public class GraphModel 
{
	public static double[] X = new double[201];
	public static final double[] XOri = new double[201];
	public static final double[] XPi = new double[201];
	
	private double[] y;
	private Stack<double[]> valuation = new Stack<double[]>();
	private ExpressionsParser parser;
	private boolean isSCGraph = false;
	
	/**
	 * Instantiate a new GraphModel with the default X-coordinates and XPi-coordinates
	 */
	public GraphModel()
	{
		int j = 0;
		for(double i = -3.1415926535897932384626433*2; j < XPi.length;)
		{
			XPi[j++] = i;
			i = i + 0.031415927;
		}
		j = 0;
		for(double i = -10; j < XOri.length;)
		{
			XOri[j++] = i;
			i = i + 0.1;
		}
		X = XOri;
	}
	/**
	 * Evaluate an expression for its Y-coordinates
	 * @param expressionsPostFix - the expressions list in Posfix form
	 * @return - the Y-coordinates
	 */
	public double[] getValues(Stack<String> expressionsPostFix)
	{
		valuation.clear();
		System.out.println(expressionsPostFix);
		for(String next0 : expressionsPostFix)
		{
			if(next0.equals("SIN(") || next0.equals("COS("))
			{
				isSCGraph = true;
			//	X = XPi;
			//	break;
			}
			else
			{
				isSCGraph = false;
				//X = XOri;
			}
		}
				
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
			}
				
		}
		return y;
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
	public boolean getisSCGraph() {
		return isSCGraph;
	}

	
}
