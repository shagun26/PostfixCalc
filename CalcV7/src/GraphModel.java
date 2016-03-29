import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;


public class GraphModel 
{
	public static final  double[] X = new double[201]; 
	private double[] y;
	private Stack<double[]> valuation = new Stack<double[]>();
	private ExpressionsParser parser;
	
	public GraphModel()
	{
		int j = 0;
		for(double i = -10; j < X.length;)
		{
			X[j++] = i;
			i = i + 0.1;
		}
	}
	
	public double[] getValues(Stack<String> expressionsPostFix)
	{
		valuation.clear();
		System.out.println(expressionsPostFix);
		for(String next : expressionsPostFix)
		{
			if(next.equals("x"))
				var();
			else if(next.equals("+"))
				sum();
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
	
	public void div() 
	{
		parser = new EvaluateDiv();
		y = parser.evaluate(valuation);
		
	}

	public void negate() 
	{
		parser = new EvaluateNegate();
		y = parser.evaluate(valuation);
	}

	public void var()
	{
		parser = new EvaluateVar();
		y = parser.evaluate(valuation);
	}
	
	public void sum()
	{
		parser = new EvaluateSum();
		y = parser.evaluate(valuation);
	}
	
	public void mult()
	{
		parser = new EvaluateMult();
		y = parser.evaluate(valuation);
	}
	
	public void sin()
	{
		parser = new EvaluateSine();
		y = parser.evaluate(valuation);
	}
	
	public void cos()
	{
		parser = new EvaluateCos();
		y = parser.evaluate(valuation);
	}

	
	
}
