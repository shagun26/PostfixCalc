import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controller implements ActionListener
{
	private View window;
	private Model model;
	private StringBuilder sb = new StringBuilder();
	
	public Controller(View view)
	{
		model = new Model();
		window = view;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String key = e.getActionCommand();
		window.updateValue(model.getNewValue(key));
		window.updateHistory(model.getHistory(key));
		
	}
	

}
