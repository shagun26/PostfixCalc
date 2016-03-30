import java.util.Stack;


public class GraphController 
{
	private static GraphView grph_view = null;
	private static GraphModel grph_model = null;
	private Controller cntrl;
	private static FavController favcntrl = null;
	private double[] Ys = new double[GraphModel.X.length];
	private Stack<String> expressionsList;
	private String prevExpr;
	
	/**
	 * Initialize the Graphing component of the calculator
	 * @param controller - the CalcController reference
	 * @param expressionsPostFix - the Postfix expressions list
	 * @param expressionsInFix - the Infix expressions list
	 */
	public void initialize(Controller controller, Stack<String> expressionsPostFix, Stack<String> expressionsInFix)
	{
		//Instantiate according to singleton pattern
		if(grph_model == null)
			grph_model = new GraphModel();
		
		if(favcntrl == null)
			favcntrl = new FavController();
		
		if(grph_view == null)
			grph_view = new GraphView(this);
		
		expressionsList = expressionsInFix;
	
		cntrl = controller;
		//If list is empty, reset to default
		if(expressionsList.empty())
		{
			Ys = null;
			grph_view.drawGraph(Ys, grph_model.getisSCGraph());
			grph_view.updateExpr("");
			prevExpr = "";
		}
		//If expression different, get y-values and
		//draw the graph
		else if(!expressionsList.peek().equals(prevExpr))
		{
			Ys = grph_model.getValues(expressionsPostFix);
			grph_view.updateExpr("y = " + expressionsList.peek());
			prevExpr = expressionsList.peek();
			grph_view.drawGraph(Ys, grph_model.getisSCGraph());
			System.out.println(grph_view.getExpr());
		}
		grph_view.setVisible(true);
	}
	/**
	 * Instructs the Graph View to open
	 */
	public void open()
	{
		grph_view.setVisible(true);
	}
	/**
	 * Instructs the Graph View to draw the Graph of the expression
	 * with the associated y values 
	 * @param expr - the expression to be graphed
	 * @param values - the expressions's y coordinates
	 */
	public void drawGraph(String expr, double[] values)
	{
		grph_view.updateExpr("y = " + expr);
		grph_view.drawGraph(values, grph_model.getisSCGraph());
		grph_view.setVisible(true);
	}
	/**
	 * Instructs the Graph View to close and passes control to the
	 * CalcController (which opens the CalcView)
	 */
	public void changeToCalc()
	{
		System.out.println("CALC PLS");
		grph_view.setVisible(false);
		cntrl.open();
	}
	/**
	 * Instructs the Graph View to close and passes control to the
	 * FavController (which opens the FavcView)
	 */
	public void changToFav() 
	{
		System.out.println("FAV PLS");
		grph_view.setVisible(false);
		favcntrl.open(this);
		
	}
	/**
	 * Passes control over to the favorites control
	 * to save the top expression in the favorites list
	 */
	public void addToFav()
	{
		if(!expressionsList.empty())
			favcntrl.savetoFav(expressionsList.peek(), Ys);
	}

	
}
