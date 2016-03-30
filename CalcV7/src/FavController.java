import java.util.Stack;


public class FavController {

	private  FavView fav_view;
	private  FavModel fav_model;
	private GraphController grph_controller;
	
	/**
	 * Instantiate a new FavController with its model and view
	 */
	public FavController()
	{
		fav_model = new FavModel();
		fav_view = new FavView(this);
	}
	/**
	 * Opens the favorites view
	 * @param cntrl - the GraphController that transfered control
	 */
	public void open(final GraphController cntrl)
	{
		grph_controller = cntrl;		
		fav_view.setVisible(true);
	}
	/**
	 * Closes the Favorites view and transfers control
	 * to the GraphController (opens the Graph View)
	 */
	public void changeToGraph() 
	{
		
		fav_view.setVisible(false);
		grph_controller.open();
		
	}
	/**
	 * Notify the Favorites model that the passed expression must be removed
	 * from the list
	 * @param expr - the expression to be removed
	 */
	public void remove(String expr)
	{
		fav_model.deleteExpr(expr);
	}
	/**
	 * Notifies the favorites model to add the currently drawn expression
	 * to the favorites list. Notifies the Favorites View to update accordingly
	 * @param exprInFix - the expression
	 * @param y - the expressions y coordinates
	 */
	public void savetoFav(String exprInFix, double[] y)
	{
		boolean added = fav_model.addExpr(exprInFix, y);	
		if(added)
			fav_view.updateFav(exprInFix);
	}

	/**
	 * Closes the favorites view and passes control to the GraphController,
	 * who draws the graph of the expression selected in the 
	 * favorites view
	 * @param expr - the expression of the graph
	 */
	public void drawGraph(String expr) 
	{
		fav_view.setVisible(false);
		grph_controller.drawGraph(expr, fav_model.getValue(expr));
		
	}

	
	

}