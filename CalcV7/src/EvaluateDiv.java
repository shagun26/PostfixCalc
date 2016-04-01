import java.util.Stack;


public class EvaluateDiv extends ExpressionsParser 
{

	@Override
	public double[] evaluate(Stack<double[]> valuation) {
		double[] y = new double[GraphModel.X.length];
		double[] num1 = valuation.pop();
		double[] num2 = valuation.pop();
		
		
		
		for(int i = 0; i < y.length; i++)
		{
				y[i] = num2[i] / num1[i];
				System.out.println(num2[i] + " " + num1[i]);
		}
		System.out.println("Array mult");
		
		valuation.push(y);
		
		
		return y;
	}

}
