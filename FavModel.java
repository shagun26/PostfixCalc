import java.util.Stack;

public class FavModel {
	private Stack<String> currentPOST = new Stack<String>();
	private Stack<String> currentIN = new Stack<String>();
	private Stack<String> expression_storage = new Stack<String>();
	
	public void deleteExpr()
	{
		
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



	
}
