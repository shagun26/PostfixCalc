import java.util.Stack;


public class EvaluateDiv extends ExpressionsParser 
{

	@Override
	public double[] evaluate(Stack<double[]> valuation) {
		// TODO Auto-generated method stub
		double[] y = new double[GraphModel.X.length];
		double[] num1 = valuation.pop();
		double[] num2 = valuation.pop();
		
		
		if(num2.length < 2 && num1.length > 2)
		{
			for(int i = 0; i < y.length; i++)
			{
				y[i] = num2[0] / num1[i];
			}
		}
		else if(num1.length < 2 && num2.length > 2)
		{
			for(int i = 0; i < y.length; i++)
			{
				y[i] = num2[i] / num1[0];
			}
		}
		else
		{
			for(int i = 0; i < y.length; i++)
			{
				y[i] = num2[i] / num1[i];
				System.out.println(num2[i] + " " + num1[i]);
			}
			System.out.println("Array mult");
		}
		valuation.push(y);
		
		
		return y;
	}

}
