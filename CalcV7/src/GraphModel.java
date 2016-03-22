import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;


public class GraphModel 
{
	public static  double[] X = {-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5};
	private double[] y = new double[11];
	private Stack<double[]> valuation = new Stack<double[]>();
	private ExpressionsParser parser;
		
	public void getValues(Stack<String> expressionsPostFix)
	{
		System.out.println(expressionsPostFix);
		valuation.clear();
		for(String next : expressionsPostFix)
		{
			if(next.equals("x"))
				var();
			else if(next.equals("+"))
				sum();
			else if(next.equals("*"))
				mult();
			else if(next.equals("SIN("))
				sin();
			else if(next.equals(Controller.PLUSMINUS))
				negate();
			else
			{
				double[] pushed = {Double.parseDouble(next)};
				valuation.push(pushed);
			}
				
		}
		//valuation.clear();
		for(int i = 0; i < 11; i++)
		{
			System.out.print(y[i] + " ");
		}
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


	


	
	
}
