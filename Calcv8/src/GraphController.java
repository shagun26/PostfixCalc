import java.util.Stack;


public class GraphController 
{
	private static GraphView grph_view = null;
	private static GraphModel grph_model = null;
	private Controller cntrl;
	private static FavController favcntrl = null;
	private double[] Ys = new double[GraphModel.X.length];
	private Stack<String> expressionsList;
	
	public void initialize(Controller controller, Stack<String> expressionsPostFix, Stack<String> expressionsInFix)
	{
		if(grph_model == null)
			grph_model = new GraphModel();
		
		if(favcntrl == null)
			favcntrl = new FavController();
		
		//grph_model.setValuePOST(expressionsPostFix);
		//grph_model.setValueIN(expressionsInFix);
		expressionsList = expressionsInFix;
		Ys = grph_model.getValues(expressionsPostFix);
		
		if(grph_view == null)
			grph_view = new GraphView(this);
		
		cntrl = controller;
		if(!expressionsList.empty())
			grph_view.updateExpr("y = " + expressionsList.peek());
		
		if(!expressionsPostFix.empty())
			grph_view.drawGraph(Ys, grph_model.getisSCGraph());
		
		grph_view.setVisible(true);
	}
	
	public void open()
	{
		grph_view.setVisible(true);
	}
	
	
	public void drawGraph(String expr, double[] values)
	{
		grph_view.updateExpr("y = " + expr);
		grph_view.drawGraph(values, grph_model.getisSCGraph());
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
		favcntrl.open(this);
		
	}

	public void addToFav()
	{
		if(!expressionsList.empty())
			favcntrl.savetoFav(expressionsList.peek(), Ys);
	}

	
}
