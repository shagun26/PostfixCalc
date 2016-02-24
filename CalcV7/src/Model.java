import java.util.ArrayList;
import java.util.Stack;


public class Model
{
	private Stack<String> button_history = new Stack<String>();
	private Stack<String> prev_history = new Stack<String>();
	private Stack<Double> stored_values = new Stack<Double>();
	private Stack<String> precedence= new Stack<String>();
	
	
	
	private static final String EQUALS = "=";
	private static final String START = "Start new Calculation";
	private static final String ENTER = "Enter";
	private static final String FACT = "!";
	private static final String PLUS = "+";
	private static final String MINUS = "-";
	private static final String MULT = "x";
	private static final String DIV = "/";
	private static final String SIN = "SIN(";
	private static final String COS = "COS(";
	
	
	private ArrayList<String> high_precedence = new ArrayList<String>();
	private ArrayList<String> lowest_precedence = new ArrayList<String>();
	private ArrayList<String> highest_precedence = new ArrayList<String>();
	
	private StringBuilder sb = new StringBuilder();
	private StringBuilder sb_input_history = new StringBuilder();
	private ArrayList<String> running_history = new ArrayList<String>();
	private StringBuilder sb_completed_operations = new StringBuilder();
	
	private boolean from_memory = false;
	//private boolean bin_op = false;
	
	public Model()
	{
		reset();
		
		high_precedence.add(MULT);
		high_precedence.add(DIV);
		
		lowest_precedence.add(PLUS);
		lowest_precedence.add(MINUS);
		
		
		highest_precedence.add(FACT);
		
	}
	
	
	
	private String printHistory(ArrayList<String> arraylist, int size)
	{
		if(size == 1)
		{
			return arraylist.get(0);
		}
		return printHistory(arraylist, size - 1) + ", " + arraylist.get(size - 1);
	}
	
	
	public void addToEntry(String button)
	{
		if(button == ("" + Math.PI))
		{
			sb.delete(0, sb.length());
		}
		sb.append(button);
		sb_input_history.append(button);
	}
	
	public String updateValue()
	{
		return sb.toString();
	}
	
	public void reset()
	{
		stored_values.clear();
		button_history.clear();
		prev_history.clear();
		precedence.clear();
		
		sb_input_history.delete(0, sb_input_history.length());
		sb_completed_operations.delete(0, sb_completed_operations.length());
		running_history.clear();
		sb.delete(0, sb.length());
	}
	
	public String sum()
	{
		if(sb.toString().equals(""))
		{
			from_memory = true;
			double value = addStoredValues();
			sb.delete(0, sb.length());
			return "" + value;
		}
		else
		{
			from_memory = false;
			double value = addStoredWithHistory();
			sb.delete(0, sb.length());
			return "" + value;
		}
	}
	
	public String subtract()
	{
		if(sb.toString().equals(""))
		{
			from_memory = true;
			double value = subStoredValues();
			sb.delete(0, sb.length());
			return "" + value;
		}
		else
		{
			from_memory = false;
			double value = subStoredWithHistory();
			sb.delete(0, sb.length());
			return "" + value;
		}
	}
	
	public String multiply()
	{
		if(sb.toString().equals(""))
		{
			from_memory = true;
			double value = multStoredValues();
			sb.delete(0, sb.length());
			return "" + value;
		}
		else
		{
			from_memory = false;
			double value = multStoredWithHistory();
			sb.delete(0, sb.length());
			//getNewHistory(button);
			return "" + value;
		}
	}
	
	public String divide()
	{
		if(sb.toString().equals(""))
		{
			from_memory = true;
			double value = divStoredValues();
			//sb.delete(0, sb.length());
			return "" + value;
		}
		else
		{
			from_memory = false;
			double value = divStoredWithHistory();
			sb.delete(0, sb.length());
			return "" + value;
		}
	}
	
	public String negate()
	{
		if(sb.toString().equals(""))
		{
			from_memory = true;
			double value = negateStoredValues();
			sb.delete(0, sb.length());
			return "" + value;
		}
		else
		{
			//If negation during number entry,
			//the value shown to the user must be updated
			from_memory = false;
			negateStoredWithHistory();
			return sb.toString();
		}

	}
	
	public String factorial()
	{
		if(sb.toString().equals(""))
		{
			from_memory = true;
			double value = factStoredValues();
			sb.delete(0, sb.length());
			return "" + value;
		}
		else
		{
			from_memory = false;
			double value = factStoredWithHistory();
			sb.delete(0, sb.length());
			return "" + value;
		}
		
	}
	
	public String sin()
	{
		if(sb.toString().equals(""))
		{
			from_memory = true;
			double value = sinStoredValues();
			sb.delete(0, sb.length());
			return "" + value;
		}
		else
		{
			from_memory = false;
			double value = sinStoredWithHistory();
			sb.delete(0, sb.length());
			return "" + value;
		}

	}

	public String cos()
	{
		if(sb.toString().equals(""))
		{
			from_memory = true;
			double value = cosStoredValues();
			sb.delete(0, sb.length());
			return "" + value;
		}
		else
		{
			from_memory = false;
			double value = cosStoredWithHistory();
			sb.delete(0, sb.length());
			return "" + value;
		}

	}
	
	public String operandHistory(String operand)
	{
		String first;
		String second;
		
		//Add last action
		prev_history.push(button_history.peek());
		
		
		second = button_history.pop();
		
		if(!from_memory)
		{
			//If the stack is empty or operation is completed using a button press, 
			//use the button press to update to the stack
			if(!checkBrackets(second, operand))
			{
				sb_completed_operations.append(second + " " + operand + " " + sb_input_history.toString() + " ");
			}
			else
			{
				sb_completed_operations.append("(" + second + ")" + " " + operand + " " +  sb_input_history.toString()  + " ");
			}
			
			//Update precedence list
			precedence.push(operand);
			
			System.out.println(precedence.toString());
			
			updateHistDirect();
			prev_history.push(operand);
			
			return printHistory(running_history, running_history.size()) + EQUALS;
		}
		
		//Else, use the last two entries in the stack and anything else as necessary
		
		first = button_history.pop();
		
		//If the last element needs brackets
		if(checkBrackets(second, operand))	
		{
			System.out.println("second is true");
			//And so does the one before
			//Put them
			if(checkBrackets(first, operand))
				sb_completed_operations.append("(" + first +  ") " + operand + " " + "(" + second + ")" + " ");
			//Otherwise just on the last element
			else
			{
				sb_completed_operations.append(first +  " " + operand + " " + "(" + second + ")" + " ");
			}
				
		}
		//If only the one before needs brackets
		else if(checkBrackets(first, operand))
		{
			sb_completed_operations.append("(" + first +  ") " + operand + " " + second + " ");
		}
		//No brackets at all
		else
		{
			sb_completed_operations.append(first + " " + operand + " " /*+ sb_input_history.toString() + " "*/ + second + " ");
		}
		
		//Update precedence list
		precedence.push(operand);
		
		System.out.println(precedence.toString());
		updateHistMem();
		
		prev_history.push(operand);
		
		//print updated history
		return printHistory(running_history, running_history.size()) + EQUALS;
	}
	
	public String factHistory(String operand)
	{
		//Add last action
		//prev_history.push(button_history.peek());
		
		if(!from_memory)
		{
			//set the string
			sb_completed_operations.append(sb_input_history.toString() + operand + " ");
			
			//Reset button-press string for next use
			sb_input_history.delete(0, sb_input_history.length());
			
			//Add updated history to the stack (First and only element)
			button_history.push(sb_completed_operations.toString());
			
			// Reset completed operations string_builder for next entry
			sb_completed_operations.delete(0, sb_completed_operations.length());
			
			
			/*if(!(running_history.isEmpty()))
			{
				running_history.remove(running_history.size()- 1);
			}*/
		
			//replace them with updated computation
			running_history.add(button_history.peek());
			prev_history.push(operand);
			
			//Update precedence list
			precedence.push(operand);
			System.out.println(precedence.toString());
			
			return printHistory(running_history, running_history.size()) + EQUALS;
			
		}
		else 
		{
			String first = button_history.pop();
			
			if(!checkBrackets(first, operand))
				sb_completed_operations.append(first + operand + " ");
			else
				sb_completed_operations.append("(" + first + ")" + operand + " ");
			
			//Update precedence list
			precedence.push(operand);
			System.out.println(precedence.toString());
			
			sb_input_history.delete(0, sb_input_history.length());
			// Add updated history to the stack
			button_history.push(sb_completed_operations.toString());
			
			// Reset completed operations string_builder for next use.
			// This prevents duplication of previous entries
			sb_completed_operations.delete(0, sb_completed_operations.length());
		
			//remove last two elements in running_history
			
			if(!(running_history.isEmpty()))
			{
					running_history.remove(running_history.size() - 1);
			}
			
			
			//replace them with updated computation
			running_history.add(button_history.peek());
		
			prev_history.push(operand);
			
			//print updated history
			return printHistory(running_history, running_history.size()) + EQUALS;
		}
				
		
	}
	
	public String enterHistory()
	{
			
			//Update prev_history
			if(button_history.empty())
			{
				//If machine started fresh, push default start message
				prev_history.push(START);
			}
			else
			{
				//Else, push last entry
				prev_history.push(button_history.peek());
				prev_history.push(ENTER);
			}
			
			//System.out.println(prev_history.toString());
			
			button_history.push(sb.toString());
			running_history.add(sb.toString());
			sb_input_history.delete(0, sb_input_history.length());
			sb.delete(0, sb.length());
			return printHistory(running_history, running_history.size());
		
	}
	
	
	public String trigHistory(String funct)
	{
		
		if(!from_memory)
		{
			//set the string
			sb_completed_operations.append(funct + sb_input_history.toString() + ") ");
			
			//Reset button-press string for next use
			sb_input_history.delete(0, sb_input_history.length());
			
			//Add updated history to the stack (First and only element)
			button_history.push(sb_completed_operations.toString());
			
			// Reset completed operations string_builder for next entry
			sb_completed_operations.delete(0, sb_completed_operations.length());
			precedence.push(funct);
			
			
			//replace them with updated computation
			running_history.add(button_history.peek());
			prev_history.push(funct);
			
			
			
			System.out.println(precedence.toString());
			
			return printHistory(running_history, running_history.size()) + EQUALS;
			
		}
		else 
		{
			String first = button_history.pop();
			
			
			sb_completed_operations.append(funct + first + ") ");
			
			
			if(isOp(first))
				precedence.pop();
			
			precedence.push(funct);
			
			System.out.println(precedence.toString());
			
			sb_input_history.delete(0, sb_input_history.length());
			// Add updated history to the stack
			button_history.push(sb_completed_operations.toString());
			
			// Reset completed operations string_builder for next use.
			// This prevents duplication of previous entries
			sb_completed_operations.delete(0, sb_completed_operations.length());
		
			//remove last element in running_history
			
			if(!(running_history.isEmpty()))
			{
				running_history.remove(running_history.size() - 1);
			}
			
			//replace them with updated computation
			running_history.add(button_history.peek());
		
			prev_history.push(funct);
			
			//print updated history
			return printHistory(running_history, running_history.size()) + EQUALS;
		}
		
		
	}
	
	
	public String enterValue()
	{
		if(sb.toString().equals(""))
		{
			sb.append(0);
			sb_input_history.append(0);
		}
		String placeholder = sb.toString();
		stored_values.push(Double.parseDouble(sb.toString()));
		//sb.delete(0, sb.length());
		return placeholder;
	}
	
	private double addStoredValues()
	{
		zeroCheck();
		double first_number = stored_values.pop();
		double second_number = stored_values.pop();
		double result = first_number + second_number;
		
		stored_values.push(result);
		
		return result;
	}
	
	private double addStoredWithHistory()
	{
		double stored = stored_values.pop();
		double history = Double.parseDouble(sb.toString());
		double result = stored + history;
		
		stored_values.push(result);
		
		return result;
		
	}
	
	private double subStoredValues()
	{
		zeroCheck();
		double first_number = stored_values.pop();
		double second_number = stored_values.pop();
		double result = second_number - first_number;
		
		stored_values.push(result);
		
		return result;
	}
	
	private double subStoredWithHistory()
	{
		double stored = stored_values.pop();
		double history = Double.parseDouble(sb.toString());
		double result = stored - history;
		
		stored_values.push(result);
		
		return result;
		
	}
	
	private double multStoredValues()
	{
		zeroCheck();
		double first_number = stored_values.pop();
		double second_number = stored_values.pop();
		double result = second_number * first_number;
		
		stored_values.push(result);
		
		return result;
	}
	
	private double multStoredWithHistory()
	{
		double stored = stored_values.pop();
		double history = Double.parseDouble(sb.toString());
		double result = stored * history;
		
		stored_values.push(result);
		
		return result;
		
	}
	
	private double divStoredValues()
	{
		zeroCheck();
		double first_number = stored_values.pop();
		double second_number = stored_values.pop();
		double result = second_number / first_number;
		
		stored_values.push(result);
		
		return result;
	}
	
	private double divStoredWithHistory()
	{
		double stored = stored_values.pop();
		double history = Double.parseDouble(sb.toString());
		double result = stored / history;
		
		stored_values.push(result);
		
		return result;
		
	}
	
	private double factStoredValues()
	{
		double first_number = stored_values.pop();
		double result = 1;
		
		for(int i = 2; first_number >= i; i++)
		{
			result = result * i;
		}
		
		
		stored_values.push(result);
		
		return result;
	}
	
	private double factStoredWithHistory()
	{
		double history = Double.parseDouble(sb.toString());
		double result = 1;
		
		for(int i = 1; history >= i; ++i)
		{
			result *= i;
		}
		
		stored_values.push(result);
		
		return result;
		
	}
	
	private double sinStoredValues()
	{
		//System.out.println("wee");
		double first_number = stored_values.pop();
		//System.out.println(second_number);

		double result = Math.sin(first_number);
		if(Math.abs(result) < (Math.pow(10, -15)))
		{
			result = 0;
		}
		else if ( (Math.abs(result - 0.5)) < (Math.pow(10, -15)))
		{
			if(result > 0) result = 0.5;
			if(result < 0) result = -0.5;
		}
		stored_values.push(result);

		return result;
	}

	private double sinStoredWithHistory()
	{
		double history = Double.parseDouble(sb.toString());

		double result = Math.sin(history);

		if(Math.abs(result) < (Math.pow(10, -15)))
		{
			result = 0;
		}
		else if ( (Math.abs(result - 0.5)) < (Math.pow(10, -15)))
		{
			if(result > 0) result = 0.5;
			if(result < 0) result = -0.5;
		}
		stored_values.push(result);

		return result;

	}

	private double cosStoredValues()
	{
		//System.out.println("wee");
		double first_number = stored_values.pop();
		//System.out.println(second_number);

		double result = Math.cos(first_number);
		if(Math.abs(result) < (Math.pow(10, -15)))
		{
			result = 0;
		}
		else if ( (Math.abs(result - 0.5)) < (Math.pow(10, -15)))
		{
			if(result > 0) result = 0.5;
			if(result < 0) result = -0.5;
		}

		stored_values.push(result);

		return result;
	}

	private double cosStoredWithHistory()
	{
		double history = Double.parseDouble(sb.toString());
		double result = Math.cos(history);
		if(Math.abs(result) < (Math.pow(10, -15)))
		{
			result = 0;
		}
		else if ( (Math.abs(result - 0.5)) < (Math.pow(10, -15)))
		{
			if(result > 0) result = 0.5;
			if(result < 0) result = -0.5;
		}

		stored_values.push(result);

		return result;

	}

	private double negateStoredValues()
	{
		//System.out.println("wee");
		double first_number = stored_values.pop();
		//System.out.println(second_number);
		double result = first_number * (-1);
		
		stored_values.push(result);

		return result;
	}

	private void negateStoredWithHistory()
	{
		double history = Double.parseDouble(sb.toString());
		double result = history * (-1);
		
		//Reset sb for update
		sb.delete(0, sb.length());
		
		
		//If result is int, append the casted value 
		if(result - (int) result == 0)
			sb.append("" + (int) result);
		//Otherwise append the double value
		else
			sb.append("" + result);

		//stored_values.push(result);
	}
	
	private void zeroCheck()
	{
		if(stored_values.empty())
		{
			stored_values.push((double) 0);
			stored_values.push((double) 0);
			button_history.push("" + 0);
			button_history.push("" + 0);
			
		}
		if(stored_values.size() == 1)
		{
			stored_values.push((double) 0);
			button_history.push("" + 0);
			
		}
	}
	
	private boolean isOp(String input)
	{
		try
		{
			Double.parseDouble(input);
		}
		catch (NumberFormatException e)
		{
			return true;
		}
		
		return false;
	}
	//Changes made here
	private boolean checkBrackets(String last_entry, String operand)
	{
		
		if(precedence.empty())
		{
			return false;
		}
		
		//If last op was trig
		//No brackets needed
		if(precedence.peek().equals(COS) || precedence.peek().equals(SIN))
		{
			precedence.pop();
			return false;
		}
		
		//Check if last_entry was an operation
		//or a number.
		if(!isOp(last_entry))
			return false;
		else
		{
			if(precedence.peek().equals(operand))
			{
				if(operand.equals(DIV))
				{
					precedence.pop();
					return true;
				}
					
				precedence.pop();
				return false;
			}
			//If Fact
			//Brackets needed
			if(highest_precedence.contains(operand))
			{
				precedence.pop();
				return true;
			}
			
			//If the two last operands are the same,
			//no need for brackets UNLESS it is DIV
			
			
			//If the last operand is low precedence,
			//no brackets needed
			if(lowest_precedence.contains(operand))
			{
				precedence.pop();
				return false;
			}
			
			//If both operands are high precedence, or one is highest
			//and other is high,
			//additional checks needed
			if(high_precedence.contains(precedence.peek()) || highest_precedence.contains(precedence.peek()))
			{
				//If multiplication was first,
				//brackets needed
				if(operand == DIV && precedence.peek() == MULT)
				{
					precedence.pop();
					return true;
				}
					
				
				//Otherwise, they are not
				precedence.pop();
				//System.out.println("Mult Second");
				
				return false;
				
			}
			
			
			//Low precedence followed by High.
			//Thus brackets needed.
			precedence.pop();
			System.out.println("Brackets");
			
			return true;
		
		
		}
		
	}
	
	
	private void updateHistDirect()
	{
		//Reset button-press string for next use
		sb_input_history.delete(0, sb_input_history.length());
		
		//Add updated history to the stack (First and only element)
		button_history.push(sb_completed_operations.toString());
		
		// Reset completed operations string_builder for next entry
		sb_completed_operations.delete(0, sb_completed_operations.length());
		
		
		running_history.remove(running_history.size() - 1);
		running_history.add(button_history.peek());
		
	}
	
	private void updateHistMem()
	{
		
		// Add updated history to the stack
		button_history.push(sb_completed_operations.toString());
		
		// Reset completed operations string_builder for next use.
		// This prevents duplication of previous entries
		sb_completed_operations.delete(0, sb_completed_operations.length());
		
		//remove last two elements in running_history
		for(int i = 0; i < 2; i++)
		{
			if(!(running_history.isEmpty()))
			{
				running_history.remove(running_history.size() - 1);
			}
			
		}
		//replace them with updated computation
		running_history.add(button_history.peek());
	}
	
	
	
}
