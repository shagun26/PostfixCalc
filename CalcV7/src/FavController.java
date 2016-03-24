import java.util.Stack;


public class FavController {

	private static FavView fav_view = null;
	private static FavModel fav_model = null;
	//private Controller controller;
	private GraphController grph_controller;
	private Controller cntrl;
	
	public void open(GraphController cntrl, Stack<String> expressionsPostFix, Stack<String> expressionsInFix)
	{
		if(fav_model == null)
			fav_model = new FavModel();
//		System.out.println(expressionsPostFix);
//		System.out.println(expressionsInFix);
		System.out.println(cntrl + " , " + expressionsPostFix + " , " + expressionsInFix);
		
		fav_model.setValuePOST(expressionsPostFix);
		fav_model.setValueIN(expressionsInFix);
		
		grph_controller = cntrl;
//		this.cntrl = cntrl;
		
		if(fav_view == null)
			fav_view = new FavView(this);
		
		fav_view.setVisible(true);
	}

	public void changeToGraph() {
		
		fav_view.setVisible(false);
		System.out.println(cntrl + " , " + fav_model.getValuePOST() + " , " + fav_model.getValueIN());
		grph_controller.open(cntrl,fav_model.getValuePOST(), fav_model.getValueIN());
		
	}
	

	
	public void savetoFav()
	{
		System.out.println("Attempting to Save");
		
		
	}

	
	

}