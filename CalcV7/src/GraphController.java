import java.util.Stack;


public class GraphController 
{
	private static GraphView grph_view = null;
	private static GraphModel grph_model = null;
	private Controller cntrl;
	
	public void open(Controller control, Stack<String> expressionsPostFix, Stack<String> expressionsInFix)
	{
		if(grph_model == null)
			grph_model = new GraphModel();
		
		double[] y = grph_model.getValues(expressionsPostFix);
		
		if(grph_view == null)
			grph_view = new GraphView(this);
		
		cntrl = control;
	
		grph_view.updateExpr("y = " + expressionsInFix.peek());
		grph_view.drawGraph(y);
		grph_view.setVisible(true);
	}
	
	public void changeToCalc()
	{
		System.out.println("CALC PLS");
		grph_view.setVisible(false);
		cntrl.open();
	}

	public void changToFav() 
	{
		System.out.println("FAV PLS");
		
	}
	
}
