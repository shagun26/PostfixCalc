import java.util.Stack;


public class EvaluateSum extends ExpressionsParser
{
	@Override
	public double[] evaluate(Stack<double[]> valuation, double[] y) 
	{
		double[] num1 = valuation.pop();
		double[] num2 = valuation.pop();
		
		
		if(num1 == EXPR)
		{
				for(int i = 0; i < 11; i++)
				{
					y[i] = GraphModel.X[i] + num2[0];
				}
		}
		else if(num2 == EXPR)
		{
				for(int i = 0; i < 11; i++)
				{
					y[i] = GraphModel.X[i] + num1[0];
				}
		}
		else if(num2.length < 2)
		{
			for(int i = 0; i < 11; i++)
			{
				y[i] = num2[0] + num1[i];
			}
		}
		else if(num1.length < 2)
		{
			for(int i = 0; i < 11; i++)
			{
				y[i] = num2[i] + num1[0];
			}
		}
		else
		{
			for(int i = 0; i < 11; i++)
			{
				y[i] = num2[i] + num1[i];
			}
		}
		valuation.push(y);
		
		
		return y;
		
	}
}
