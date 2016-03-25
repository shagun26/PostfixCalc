import java.util.Stack;


public class FavController {

	private static FavView fav_view = null;
	private static FavModel fav_model = null;
	//private Controller controller;
	private GraphController grph_controller;
	
	public FavController()
	{
		if(fav_model == null)
			fav_model = new FavModel();
		
		if(fav_view == null)
			fav_view = new FavView(this);
	}
	
	
	public void open(GraphController cntrl, double[] y)
	{
//		System.out.println(expressionsPostFix);
//		System.out.println(expressionsInFix);
//		System.out.println(cntrl + " , " + y + " , " + expressionsInFix);
		
		//fav_model.setValue(expressionsPostFix);
		//fav_model.setValueIN(expressionsInFix);
		
		grph_controller = cntrl;
//		this.cntrl = cntrl;
		
		fav_view.setVisible(true);
	}

	public void changeToGraph() 
	{
		
		fav_view.setVisible(false);
		//System.out.println( fav_model.getValuePOST() + " , " + fav_model.getValueIN());
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