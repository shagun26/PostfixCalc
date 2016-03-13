import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;


public class GraphModel 
{
	private Stack<String> expressionsPostFix;
	public static final double[] X = {-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5};
	private double[] y = new double[11];
	private Stack<Double> valuation = new Stack<Double>();
	private ExpressionsParser parser;
	
	public GraphModel(Stack<String> listPostFix)
	{
		expressionsPostFix = listPostFix;
		
	}
	
	public void getValues()
	{
		Iterator<String> iter = expressionsPostFix.iterator();
		Arrays.fill(y, 0);
		while(iter.hasNext())
		{
			String next = iter.next();
			if(next.equals("x"))
				var();
			else if(next.equals("+"))
				sum();
			else if(next.equals("SIN("))
				sin();
			else
				valuation.push(Double.parseDouble(next));
		}
		
		for(int i = 0; i < 11; i++)
		{
			System.out.print(y[i] + " ");
		}
	}
	
	public void var()
	{
		parser = new EvaluateVar();
		parser.evaluate(valuation, y);
	}
	
	public void sum()
	{
		parser = new EvaluateSum();
		y = parser.evaluate(valuation, y);
	}
	
	public void sin()
	{
		parser = new EvaluateSine();
		y = parser.evaluate(valuation, y);
	}
	


	
	
}
