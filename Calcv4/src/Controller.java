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
		window.updateHistory("Start new Calculation");
		window.updateValue("0");
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
	
	public void historyOperation(String operand)
	{
		window.updateHistory(model.operandHistory(operand));
	}
	
	/*@Override
	public void actionPerformed(ActionEvent e)
	{
		String key = e.getActionCommand();
		window.updateValue(model.getNewValue(key));
		window.updateHistory(model.getHistory(key));
		
	}*/
	

}
