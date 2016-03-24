import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;


public class GraphModel 
{
	public static final  double[] X = new double[202]; //= {-5, -4.5, -4, -3.5, -3, -2.5, -2, -1.5, -1, -0.5, 0, 0.5, 1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5, 5};
	private double[] y; //= new double[X.length];
	private Stack<String> currentPOST = new Stack<String>();
	private Stack<String> currentIN = new Stack<String>();
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
		//valuation.clear();
		for(int i = 0; i < (y.length); i++)
		{
			System.out.print("x y pair : " + X[i] + " " + y[i] + " ");
		}
		System.out.println("\n");
		System.out.println(y.length);
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

	public double[] returny()
	{
		return y;
	}
	
	public void setValuePOST(Stack<String> postExpr)
	{
		this.currentPOST = postExpr;
	}
	public void setValueIN(Stack<String> inExpr)
	{
		this.currentIN = inExpr;
	}
	public Stack<String> getValuePOST()
	{
		return currentPOST;
	}
	public Stack<String> getValueIN()
	{
		return currentIN;
	}
	
	public void addFav()
	{
		
	}

	
	
}
