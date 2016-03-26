import java.util.Stack;


public class FavController {

	private  FavView fav_view;
	private  FavModel fav_model;
	//private Controller controller;
	private GraphController grph_controller = null;
	
	public FavController()
	{
		fav_model = new FavModel();
		fav_view = new FavView(this);
	}
	
	
	public void open(final GraphController cntrl)
	{
		if(grph_controller == null)
			grph_controller = cntrl;		
		
		fav_view.setVisible(true);
	}

	public void changeToGraph() 
	{
		
		fav_view.setVisible(false);
		grph_controller.open();
		
	}
	

	
	public void savetoFav(String exprInFix, double[] y)
	{
		boolean added = fav_model.addExpr(exprInFix, y);	
		if(added)
			fav_view.updateFav(exprInFix);
	}


	public void drawGraph(String expr) 
	{
		fav_view.setVisible(false);
		grph_controller.drawGraph(expr, fav_model.getValue(expr));
		
	}

	
	

}