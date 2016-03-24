import java.util.Stack;


public class GraphController 
{
	private static GraphView grph_view = null;
	private static GraphModel grph_model = null;
	private Controller cntrl;
	private FavController favcntrl;

	
	public void open(Controller controller, Stack<String> expressionsPostFix, Stack<String> expressionsInFix)
	{
		if(grph_model == null)
		{
			grph_model = new GraphModel();
			
		}
		
		grph_model.setValuePOST(expressionsPostFix);
		grph_model.setValueIN(expressionsInFix);
		
		double[] y = grph_model.getValues(expressionsPostFix);
		
		if(grph_view == null)
			grph_view = new GraphView(this);
		
		cntrl = controller;
	
		grph_view.updateExpr("y = " + expressionsInFix.peek());
		grph_view.drawGraph(y);
		grph_view.setVisible(true);
	}
	
	public void open()
	{
		grph_view.setVisible(true);
	}
	
	public void changeToCalc()
	{
		System.out.println("CALC PLS");
		grph_view.setVisible(false);
		cntrl.open();
	}

	public void changToFav(GraphController controller) 
	{
		System.out.println("FAV PLS");
		grph_view.setVisible(false);
		favcntrl = new FavController();
		favcntrl.open(controller,grph_model.getValuePOST(),grph_model.getValueIN());
		
	}

//	public void addtoFav() {
//		System.out.println("Added");
//		grph_model.addFav();
//	}


	
}
