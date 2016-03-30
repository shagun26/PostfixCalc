//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;


public class Controller
{
	private View window;
	private Model model;
	private GraphController grph_controller;
	
	//Constants used by both Model and View
	public static final String CLEAR = "Clear";
	public static final String ENTER = "Enter";
	public static final String PLUS = "+";
	public static final String MULT = "*";
	public static final String DIV = "/";
	public static final String MINUS = "-";
	public static final String FACT = "!";
	public static final String UNDO = "Undo";
	public static final String PI = "\u03C0";
	public static final String PLUSMINUS = "+/-";
	public static final String COS = "COS(";
	public static final String SIN = "SIN(";
	public static final String EXPRESSION = 'x' + "";
	
	/**
	 * Initializes a new Controller with an assigned View object
	 * @param view
	 */
	public Controller(View view)
	{
		model = new Model();
		window = view;
		grph_controller = new GraphController();
		
	}
	
	/**
	 * Switches from the Calculator View to
	 * the Graphing View
	 */
	public void changeToGraph()
	{
		window.setVisible(false);
		grph_controller.initialize(this, model.getPostExpressionList(), model.getInFixExpressionList());
	}
	
	/**
	 * opens the Calculator View
	 */
	public void open()
	{
		window.setVisible(true);
	}
	
	/**
	 * Notifies the Model to add a character to 
	 * the constructed string. Notifies View to
	 * update its displays accordingly
	 * @param button - the character to be appended
	 */
	public void addToEntry(String button)
	{
		model.addToEntry(button);
		window.updateValue(model.updateValue());
		
		if(model.isHistoryEmpty())
			window.updateHistory("");
		else
			window.updateHistory(model.printHistory());
	}
	
	/**
	 * Sets the Model and View to their default states
	 */
	public void clear()
	{
		model.reset();
		window.clearDisplay();
	}
	/**
	 * Notifies Model and View of pi
	 * button press
	 */
	public void pi()
	{
		window.updateValue(model.valuePi());
		window.updateHistory(model.historyPi());
	}
	/**
	 * Notifies Model and View of 'x'
	 * button press
	 */
	public void expression()
	{
		window.updateValue(model.expressionVal());
		window.updateHistory(model.expressionHist());
	}
	/**
	 * Notifies the Model of an Enter
	 * Notifies the View to update its history displays
	 */
	public void enter()
	{
		window.updateValue(model.enterValue());
		window.updateHistory(model.enterHistory());
	}
	/**
	 * Notifies Model  of an addition
	 * Notifies View to update its value field
	 */
	public void sum()
	{
		window.updateValue(model.sum());
	}
	/**
	 * Notifies Model  a subtraction
	 * Notifies View to update its value field
	 */
	public void subtract()
	{
		window.updateValue(model.subtract());
	}
	/**
	 * Notifies Model of a multiplication
	 * Notifies View to update its value field
	 */
	public void multiply()
	{
		window.updateValue(model.multiply());
	}
	/**
	 * Notifies Model of a division
	 * Notifies View to update its value field
	 */
	public void divide()
	{
		window.updateValue(model.divide());
	}
	/**
	 * Notifies Model of a factorial
	 * Notifies View to update its value field
	 */
	public void factorial()
	{
		window.updateValue(model.factorial());
		
	}
	/**
	 * Notifies Model of a sine
	 * Notifies View to update its value field
	 */
	public void sin()
	{
		window.updateValue(model.sin());
	}
	/**
	 * Notifies Model of a cosine
	 * Notifies View to update its value field
	 */
	public void cos()
	{
		window.updateValue(model.cos());
	}
	/**
	 * Notifies Model of a negation
	 * Notifies View to update its display
	 */
	public void negate()
	{
		window.updateValue(model.negate());
		window.updateHistory(model.negateHistory());
	}
	/**
	 * Notifies View to update its history field
	 * after a Binary Operation
	 * @param operator - the operator of the operation
	 */
	public void historyOperation(String operator)
	{
		window.updateHistory(model.operandHistory(operator));
	}
	/**
	 * Notifies View to update its history field
	 * after a factorial
	 * @param operator - the operator of the operation
	 */
	public void factHistoryOperation(String operator)
	{
		window.updateHistory(model.factHistory(operator));
	}
	/**
	 * Notifies View to update its history field
	 * after a Trigonometric Operation
	 * @param operator - the operator of the operation
	 */
	public void trigHistoryOperation(String funct)
	{
		window.updateHistory(model.trigHistory(funct));
	}
	/**
	 * Notifies the Model of an Undo
	 * Notifies the View to update its history displays
	 */
	public void undo()
	{
		window.updateHistory(model.undoHistory());
		window.updateValue(model.undoValue());
		System.out.println(model.expressionsInFix_undo);
		System.out.println(model.running_history_undo);
		System.out.println(model.button_history_undo);
		System.out.println(model.button_history);
		System.out.println(model.running_history);
	}
	

}
