
import java.util.HashMap;
import java.util.Stack;

public class FavModel {
	
	private HashMap<String, double[]> favs = new HashMap<String, double[]>();
	
	/**
	 * Remove an expression from the favorites list
	 * @param expr - the expression to be removed
	 */
	public void deleteExpr(String expr)
	{
		favs.remove(expr);
	}
	/**
	 * Get the y-coordinates of an expression in the favorites list
	 * @param key - the expression
	 * @return - the y coordinates
	 */
	public double[] getValue(String key)
	{
		return favs.get(key);
	}
	/**
	 * Add an expression and its y-coordinates to the favorites list
	 * @param expr - the expression
	 * @param y - the y coordinates
	 * @return - true if the expression has been added
	 * 			 false if it is already in the list
	 */
	public boolean addExpr(String expr, double[] y)
	{
		if(favs.containsKey(expr))
			return false;
		
		double[] values = new double[y.length];
		for(int i = 0; i < y.length; i++)
			values[i] = y[i];
		
		favs.put(expr, values);
		
		System.out.println("Hi + " + favs.toString());
		return true;
	}


	

	
}
