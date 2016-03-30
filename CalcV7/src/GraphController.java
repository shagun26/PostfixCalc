import java.util.Stack;


public class GraphController 
{
	private static GraphView grph_view = null;
	private static GraphModel grph_model = null;
	private Controller cntrl;
	private static FavController favcntrl = null;
	private double[] Ys = new double[GraphModel.X.length];
	private Stack<String> expressionsList;
	private Stack<String> expressionsListPost;
	private double xScale;
	private String prevExpr;
	
	public void initialize(Controller controller, Stack<String> expressionsPostFix, Stack<String> expressionsInFix)
	{
		if(grph_model == null)
			grph_model = new GraphModel();
		
		if(favcntrl == null)
			favcntrl = new FavController();
		
		expressionsList = expressionsInFix;
		expressionsListPost = expressionsPostFix;
		xScale = grph_model.xScale();
		
		if(grph_view == null)
			grph_view = new GraphView(this);
		
		cntrl = controller;

		if(expressionsList.empty())
		{
			Ys = null;
			grph_view.drawGraph(Ys, xScale);
			grph_view.updateExpr("");
			prevExpr = "";
		}
		else if(!expressionsList.peek().equals(prevExpr))
		{
			Ys = grph_model.getValues(expressionsPostFix);
			grph_view.updateExpr("y = " + expressionsList.peek());
			prevExpr = expressionsList.peek();
			grph_view.drawGraph(Ys, xScale);
			System.out.println(grph_view.getExpr());
		}
			
			

		grph_view.setVisible(true);
	}
	
	public void open()
	{
		grph_view.setVisible(true);
	}
	
	public void drawGraph(String expr, double[] values)
	{
		grph_view.updateExpr("y = " + expr);
		grph_view.drawGraph(values, xScale);
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

	public void newGraphSet(double xScale2)
	{
		xScale = xScale2;
		grph_model.newGraph(2*xScale);
		Ys = grph_model.calculateValues(expressionsListPost);
		grph_view.drawGraph(Ys, xScale);
	}
	
}
