//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;


public class Controller
{
	private View window;
	private Model model;
	
	public Controller(View view)
	{
		model = new Model();
		window = view;
		
	}
	
	public void addToEntry(String button)
	{
		model.addToEntry(button);
		window.updateValue(model.updateValue());
		window.updateHistory("");
	}
	
	public void clear()
	{
		model.reset();
		window.clearDisplay();
	}
	
	public void enter()
	{
		window.updateValue(model.enterValue());
		window.updateHistory(model.enterHistory());
	}
	
	public void sum()
	{
		window.updateValue(model.sum());
	}
	
	public void subtract()
	{
		window.updateValue(model.subtract());
	}
	
	public void multiply()
	{
		window.updateValue(model.multiply());
	}
	
	public void divide()
	{
		window.updateValue(model.divide());
	}
	
	public void factorial()
	{
		window.updateValue(model.factorial());
		
	}
	
	public void sin()
	{
		window.updateValue(model.sin());
	}

	public void cos()
	{
		window.updateValue(model.cos());
	}

	public void negate()
	{
		window.updateValue(model.negate());
	}
	
	public void historyOperation(String operand)
	{
		window.updateHistory(model.operandHistory(operand));
	}
	
	public void factHistoryOperation(String operand)
	{
		window.updateHistory(model.factHistory(operand));
	}
	
	public void trigHistoryOperation(String funct)
	{
		window.updateHistory(model.trigHistory(funct));
	}
	
	
	

}
