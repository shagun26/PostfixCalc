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
		
		if(model.isHistoryEmpty())
			window.updateHistory("");
		
	}
	
	public void clear()
	{
		model.reset();
		window.clearDisplay();
	}
	
	public void pi()
	{
		window.updateValue(model.valuePi());
		window.updateHistory(model.historyPi());
	}
	
	public void expression()
	{
		window.updateValue(model.expressionVal());
		window.updateHistory(model.expressionHist());
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
	
	public void historyOperation(String operator)
	{
		window.updateHistory(model.operandHistory(operator));
	}
	
	public void factHistoryOperation(String operator)
	{
		window.updateHistory(model.factHistory(operator));
	}
	
	public void trigHistoryOperation(String funct)
	{
		window.updateHistory(model.trigHistory(funct));
	}
	
	
	

}
