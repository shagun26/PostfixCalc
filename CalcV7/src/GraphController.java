import java.util.Stack;


public class GraphController 
{
	private static GraphView grph_view = null;
	private static GraphModel grph_model = null;
	private Controller cntrl;
	private static FavController favcntrl = null;
	private double[] Ys = new double[GraphModel.X.length];
	
	public void open(Controller controller, Stack<String> expressionsPostFix, Stack<String> expressionsInFix)
	{
		if(grph_model == null)
			grph_model = new GraphModel();
		
		if(favcntrl == null)
			favcntrl = new FavController();
		
		grph_model.setValuePOST(expressionsPostFix);
		grph_model.setValueIN(expressionsInFix);
		
		Ys = grph_model.getValues(expressionsPostFix);
		
		if(grph_view == null)
			grph_view = new GraphView(this);
		
		cntrl = controller;
	
		grph_view.updateExpr("y = " + expressionsInFix.peek());
		grph_view.drawGraph(Ys);
		grph_view.setVisible(true);
	}
	
	public void open()
	{
		grph_view.setVisible(true);
	}
	
	public void drawGraph(String expr, double[] values)
	{
		grph_view.updateExpr("y = " + expr);
		grph_view.drawGraph(values);
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
		grph_view.setVisible(false);
		favcntrl.open(this, Ys , grph_model.getValueIN());
		
	}

	public void addToFav()
	{
		favcntrl.savetoFav(grph_model.getValueIN().peek(), Ys);
	}

	
}
