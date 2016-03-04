import java.util.ArrayList;
import java.util.Stack;


public class Model
{
	/**
	 * Stores a history of entered values and opeations.
	 * (Section 3.3 in Design Document)
	 */
	private Stack<String> button_history = new Stack<String>();
	
	
	private Stack<String> prev_history = new Stack<String>();
	
	/**
	 * Stores any entered values or results of operations.
	 * (Section 3.3 in Design Document)
	 */
	private Stack<Double> stored_values = new Stack<Double>();
	
	/**
	 * Stores the operators that are responsible for values
	 * in stored_values (excluding Entered numbers)
	 * (Section 3.3 in Design Document)
	 */
	private Stack<String> precedence= new Stack<String>();
	
	/**
	 * String Constants that will be needed
	 */
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
	private static final String PI = "\u03C0";
	private static final String EXPRESSION = "" + 'x';
	
	/**
	 * A reference to high precedence operators not including 
	 * factorial, sin and cos.
	 */
	private ArrayList<String> high_precedence = new ArrayList<String>();
	
	/**
	 * A reference to low precedence operators
	 */
	private ArrayList<String> lowest_precedence = new ArrayList<String>();
	
	/**
	 * 
	 */
	private ArrayList<String> highest_precedence = new ArrayList<String>();
	
	/**
	 * Stores a list of expressions
	 */
	private ArrayList<String> expressions = new ArrayList<String>();
	
	
	/**
	 * Used to trace character presses.
	 * (Section 3.3 in Design Document)
	 */
	private StringBuilder sb = new StringBuilder();
	
	/**
	 * Same functionality as sb except used for
	 * updates to history
	 * (Section 3.3 in Design Document)
	 */
	private StringBuilder sb_input_history = new StringBuilder();
	
	/**
	 * A like-for-like copy of button_history.
	 * Used to print the history.
	 * (Section 3.3 in Design Document)
	 */
	private ArrayList<String> running_history = new ArrayList<String>();
	
	/**
	 * Used to construct next entry into button_history
	 * and running_history after an operation
	 * (Section 3.3 in Design Document)
	 */
	private StringBuilder sb_completed_operations = new StringBuilder();
	
	/**
	 * Indicates whether operands from only
	 * stored_values were used in an operation or otherwise
	 * (Section 3.3 in Design Document)
	 */
	private boolean from_memory = false;
	private boolean pi = false;
	private boolean opExpression = false;
	
	
	/**
	 * Instantstiate a Model object in its default state
	 */
	public Model()
	{
		reset();
		
		high_precedence.add(MULT);
		high_precedence.add(DIV);
		
		lowest_precedence.add(PLUS);
		lowest_precedence.add(MINUS);
		
		
		highest_precedence.add(FACT);
		
	}
	
	
	/**
	 * Returns the String representation of running_history as 
	 * specified in the Requirements Document
	 * @return The string representation of running_history
	 */
	private String printHistory()
	{
		int size = running_history.size();
		/*if(size == 1)
		{
			return arraylist.get(0);
		}
		return printHistory(arraylist, size - 1) + ", " + arraylist.get(size - 1);*/
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < size - 1; i++)
		{
			result.append(running_history.get(i) + ", ");
		}
		result.append(running_history.get(size - 1));
		return result.toString();
		
	}
	
	
	/**
	 * Enters the Java representation of PI into stored_values
	 * @return the Java value of Pi as a String
	 */
	public String valuePi()
	{
		pi = true;
		sb.delete(0, sb.length());
		sb_input_history.delete(0, sb_input_history.length());
		stored_values.push(Math.PI);
		return "" + Math.PI;
		
	}
	
	/**
	 * Enters the symbol of PI into button_history and running_history
	 * @return The updated history String
	 */
	public String historyPi()
	{
		button_history.push(PI);
		running_history.add(PI);
		return printHistory();
	}
	
	/**
	 * Returns the expression symbol to be displayed
	 * in the value field
	 * @return - the expression symbol
	 */
	public String expressionVal()
	{
		opExpression = true;
		return EXPRESSION;
	}
	
	/**
	 * Adds the expression symbol to the history and expression list.
	 * Returns the updated history
	 * @return - the updated history
	 */
	public String expressionHist()
	{
		button_history.push(EXPRESSION);
		running_history.add(EXPRESSION);
		expressions.add(EXPRESSION);
		return printHistory();
	}
	
	/**
	 * Appends the next character to sb
	 * @param button - the character to be appended
	 */
	public void addToEntry(String button)
	{
		/*if(button.equals("" + PI))
		{
			sb.delete(0, sb.length());
			
			//Set pi true for other functions
			pi = true;
			
		}
		else
		{
			//Set pi false for other functions
			pi = false;
		}*/
		sb.append(button);
		sb_input_history.append(button);
		pi = false;
	}
	
	/**
	 * Checks whether button_history is empty or not
	 * @return true when button_history is empty. False otherwise
	 */
	public boolean isHistoryEmpty()
	{
		return(button_history.empty());
	}
	
	/**
	 * Returns the character sequence sb
	 * @return The String representation of sb
	 */
	public String updateValue()
	{
		return sb.toString();
	}
	
	/**
	 * Sets the model to its default state
	 * (Section 3.4 in Design Document)
	 */
	public void reset()
	{
		stored_values.clear();
		button_history.clear();
		prev_history.clear();
		precedence.clear();
		expressions.clear();
		
		opExpression = false;
		pi = false;
		
		sb_input_history.delete(0, sb_input_history.length());
		sb_completed_operations.delete(0, sb_completed_operations.length());
		running_history.clear();
		sb.delete(0, sb.length());
	}
	
	/**
	 * Carries out the addition of two operands
	 * and returns the result
	 * (Section 3.4 in Design Document)
	 * @return The value of the addition as a String
	 */
	public String sum()
	{
		
		opExpression = isExpression();
		
		if(sb.toString().equals(""))
		{	
			from_memory = true;
			
			if(!opExpression)
			{
				
				double value = addStoredValues();
				sb.delete(0, sb.length());
				return "" + value;	
			}
			else
			{
				zeroCheckOther();
				return running_history.get(running_history.size() - 2) + " + " + button_history.peek() ;
			}
			
		}
		else
		{
			from_memory = false;
			if(!opExpression)
			{
				double value = addStoredWithHistory();
				sb.delete(0, sb.length());
				return "" + value;	
			}
			else
			{
				sb.delete(0, sb.length());
				return expressions.get(expressions.size() - 1) + " + " + sb_input_history.toString();
			}
			
		}
	}
	
	
	/**
	 * Carries out the subtraction of two operands
	 * and returns the result
	 * (Section 3.4 in the Design Document)
	 * @return The value of the subtraction as a String
	 */
	public String subtract()
	{
		opExpression = isExpression();
		
		if(sb.toString().equals(""))
		{	
			from_memory = true;
			
			if(!opExpression)
			{
				
				double value = subStoredValues();
				sb.delete(0, sb.length());
				return "" + value;	
			}
			else
			{
				zeroCheckOther();
				return running_history.get(running_history.size() - 2) + " - " + button_history.peek() ;
			}
			
		}
		else
		{
			from_memory = false;
			if(!opExpression)
			{
				double value = subStoredWithHistory();
				sb.delete(0, sb.length());
				return "" + value;	
			}
			else
			{
				sb.delete(0, sb.length());
				return expressions.get(expressions.size() - 1) + " - " + sb_input_history.toString();
			}
			
		}
	}
	
	/**
	 * Carries out the product of two operands
	 * and returns the result
	 * (Section 3.4 in the Design Document)
	 * @return The value of the product as a String
	 */
	public String multiply()
	{
		opExpression = isExpression();
		
		if(sb.toString().equals(""))
		{	
			from_memory = true;
			
			if(!opExpression)
			{
				
				double value = multStoredValues();
				sb.delete(0, sb.length());
				return "" + value;	
			}
			else
			{
				zeroCheckOther();
				return running_history.get(running_history.size() - 2) + " * " + button_history.peek() ;
			}
			
		}
		else
		{
			from_memory = false;
			if(!opExpression)
			{
				double value = multStoredWithHistory();
				sb.delete(0, sb.length());
				return "" + value;	
			}
			else
			{
				sb.delete(0, sb.length());
				return expressions.get(expressions.size() - 1) + " * " + sb_input_history.toString();
			}
			
		}
	}
	
	/**
	 * Carries out the division of two operands
	 * and returns the result
	 * (Section 3.4 in the Design Document)
	 * @return The value of the division as a String
	 */
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
			sb_input_history.delete(0, sb_input_history.length());
			
			int alt = (int) value;
			
			if(isInt(Math.abs(value), Math.abs(alt)))
			{
				sb.append("" + (int) value);
				sb_input_history.append("" + (int) value);
			}
			else
			{
				sb.append("" + value);
				sb_input_history.append("" + value);
				
			}
				
			
			return sb.toString();
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
	
	/**
	 * Carries out the factorial of one operand
	 * and returns the result
	 * (Section 3.4 in the Design Document)
	 * @return The value of the factorial as a String
	 */
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
	
	/**
	 * Carries out the sin of one operand
	 * and returns the result
	 * (Section 3.4 in the Design Document)
	 * @return The value of the sin as a String
	 */
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

	/**
	 * Carries out the cosine of one operand
	 * and returns the result
	 * (Section 3.4 in the Design Document)
	 * @return The value of the cosine as a String
	 */
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
	
	/**
	 * Updates button_history and running_history
	 * after a Binary Operation. Returns the updated
	 * history string
	 * @param operator - The operator of the binary operation
	 * @return The updated history string
	 */
	public String operandHistory(String operator)
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
			if(!checkBrackets(second, operator))
			{
				sb_completed_operations.append(second + " " + operator + " " + sb_input_history.toString() + " ");
			}
			else
			{
				sb_completed_operations.append("(" + second + ")" + " " + operator + " " +  sb_input_history.toString()  + " ");
			}
			
			//Update precedence list
			precedence.push(operator);
			
			System.out.println(precedence.toString());
			
			updateHistDirect();
			prev_history.push(operator);
			
			return printHistory() + EQUALS;
		}
		
		//Else, use the last two entries in the stack and anything else as necessary
		
		first = button_history.pop();
		
		//If the last element needs brackets
		if(checkBrackets(second, operator))	
		{
			System.out.println("second is true");
			//And so does the one before
			//Put them
			if(checkBrackets(first, operator))
				sb_completed_operations.append("(" + first +  ") " + operator + " " + "(" + second + ")" + " ");
			//Otherwise just on the last element
			else
			{
				sb_completed_operations.append(first +  " " + operator + " " + "(" + second + ")" + " ");
			}
				
		}
		//If only the one before needs brackets
		else if(checkBrackets(first, operator))
		{
			sb_completed_operations.append("(" + first +  ") " + operator + " " + second + " ");
		}
		//No brackets at all
		else
		{
			sb_completed_operations.append(first + " " + operator + " " /*+ sb_input_history.toString() + " "*/ + second + " ");
		}
		
		//Update precedence list
		precedence.push(operator);
		
		System.out.println(precedence.toString());
		updateHistMem();
		
		prev_history.push(operator);
		
		//print updated history
		return printHistory() + EQUALS;
	}
	
	/**
	 * Updates button_history and running_history
	 * after a factorial. Returns the updated
	 * history string
	 * @param operator - The operator of the factorial operation
	 * @return The updated history string
	 */
	public String factHistory(String operator)
	{
		//Add last action
		//prev_history.push(button_history.peek());
		
		if(!from_memory)
		{
			//set the string
			sb_completed_operations.append(sb_input_history.toString() + operator + " ");
			
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
			prev_history.push(operator);
			
			//Update precedence list
			precedence.push(operator);
			System.out.println(precedence.toString());
			
			return printHistory() + EQUALS;
			
		}
		else 
		{
			String first = button_history.pop();
			
			if(!checkBrackets(first, operator))
				sb_completed_operations.append(first + operator + " ");
			else
				sb_completed_operations.append("(" + first + ")" + operator + " ");
			
			//Update precedence list
			precedence.push(operator);
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
		
			prev_history.push(operator);
			
			//print updated history
			return printHistory() + EQUALS;
		}
				
		
	}
	
	/**
	 * Adds the 'Entered' number into running_history 
	 * and button_history. Returns the updated history string.
	 * @return The update history string
	 */
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
			
			
			if(pi)
			{
				button_history.push(sb_input_history.toString());
				running_history.add(sb_input_history.toString());
			}
			else
			{
				button_history.push(sb.toString());
				running_history.add(sb.toString());
			}
			
			sb_input_history.delete(0, sb_input_history.length());
			sb.delete(0, sb.length());
			return printHistory();
		
	}
	
	/**
	 * Updates button_history and running_history
	 * after a Trigonometric Operation. Returns the updated
	 * history string
	 * @param funct - The trigonometric function carried out
	 * @return The updated history string
	 */
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
			
			return printHistory() + EQUALS;
			
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
			return printHistory() + EQUALS;
		}
		
		
	}
	
	/**
	 * Enters a value into stored_values. Returns the entered value.
	 * @return The entered value as a String
	 */
	public String enterValue()
	{
		if(sb.toString().equals(""))
		{
			if(stored_values.empty() && (!opExpression))
			{
				System.out.println("Empty");
				sb.append(0);
				sb_input_history.append(0);
			}
			else if(opExpression)
			{
				sb.append(button_history.peek());
				return button_history.peek();
			}
			else
			{
				double push = stored_values.peek();
				if((Math.abs(push) - Math.abs((int) push)) < 0.00000000001)
				{
					sb.append((int) push);
					sb_input_history.append((int) push);
					pi = false;
				}
				else
				{	
					if(push == Math.PI)
						pi = true;
					else
						pi = false;
					
					sb.append(push);
					sb_input_history.append(push);
				}
					
			}
			
		}
		/*if(pi)
		{
			//If pi was pressed, push pi onto stack and return
			// its value
			stored_values.push(Math.PI);
			placeholder = "" + Math.PI;
		}*/
		//else
		//{
			//Otherwise parse the number
		stored_values.push(Double.parseDouble(sb.toString()));
		sb.delete(0, sb.length());
		double result = stored_values.peek();
		if(isInt(Math.abs(result), Math.abs((int) result)))
		{
			sb.append("" + (int) result);
			return "" + (int) result;
		}
			//}
		sb.append("" + result);
		//sb.delete(0, sb.length());
		return "" + result;
	}
	
	/**
	 * Case 1 in Section 5.1 of Design Document
	 * Adds two numbers from stored_values and returns the result.
	 * @return The result of the addition
	 */
	private double addStoredValues()
	{
		zeroCheckBinStored();
		double first_number = stored_values.pop();
		double second_number = stored_values.pop();
		double result = first_number + second_number;
		
		stored_values.push(result);
		
		return result;
	}
	
	/**
	 * Case 2 in section 5.1 of Design Document
	 * Adds one number from stored_values and one
	 * from the traced character sequence (sb).
	 * Returns the result of the addition
	 * @return The result of the addition
	 */
	private double addStoredWithHistory()
	{
		zeroCheckOther();
		double stored = stored_values.pop();
		double history = Double.parseDouble(sb.toString());
		
		if(isInt(history, (int) history))
		{
			sb_input_history.delete(0, sb_input_history.length());
			sb_input_history.append((int) history);
		}
		
		double result = stored + history;
		
		stored_values.push(result);
		
		return result;
		
	}
	
	/**
	 * Case 1 in Section 5.1 of Design Document
	 * Subtracts two numbers from stored_values and returns the result.
	 * @return The result of the addition
	 */
	private double subStoredValues()
	{
		zeroCheckBinStored();
		double first_number = stored_values.pop();
		double second_number = stored_values.pop();
		double result = second_number - first_number;
		
		stored_values.push(result);
		
		return result;
	}
	
	/**
	 * Case 2 in section 5.1 of Design Document
	 * Subtracts value of traced character sequence (sb)
	 * from the top element in stored_values..
	 * Returns the result of the subtraction
	 * @return The result of the subtraction
	 */
	private double subStoredWithHistory()
	{
		zeroCheckOther();
		double stored = stored_values.pop();
		double history = Double.parseDouble(sb.toString());
		double result = stored - history;
		
		
		if(isInt(history, (int) history))
		{
			sb_input_history.delete(0, sb_input_history.length());
			sb_input_history.append((int) history);
		}
		
		stored_values.push(result);
		
		return result;
		
	}
	
	/**
	 * Case 1 in Section 5.1 of Design Document
	 * Multiplies two numbers from stored_values and returns the result.
	 * @return The result of the multiplication
	 */
	private double multStoredValues()
	{
		zeroCheckBinStored();
		double first_number = stored_values.pop();
		double second_number = stored_values.pop();
		double result = second_number * first_number;
		
		stored_values.push(result);
		
		return result;
	}
	
	/**
	 * Case 2 in section 5.1 of Design Document
	 * Multiplies top element of stored_values by 
	 * value of traced character input (sb)
	 * Returns the result of the multiplication
	 * @return The result of the multiplication
	 */
	private double multStoredWithHistory()
	{
		zeroCheckOther();
		double stored = stored_values.pop();
		double history = Double.parseDouble(sb.toString());
		double result = stored * history;
		
		if(isInt(history, (int) history))
		{
			sb_input_history.delete(0, sb_input_history.length());
			sb_input_history.append((int) history);
		}
		
		stored_values.push(result);
		
		return result;
		
	}
	
	/**
	 * Case 1 in Section 5.1 of Design Document
	 * Divides two numbers from stored_values and returns the result.
	 * @return The result of the division
	 */
	private double divStoredValues()
	{
		zeroCheckBinStored();
		double first_number = stored_values.pop();
		double second_number = stored_values.pop();
		double result = second_number / first_number;
		
		stored_values.push(result);
		
		return result;
	}
	
	/**
	 * Case 2 in section 5.1 of Design Document
	 * Divides top element of stored_values by 
	 * value of traced character input (sb)
	 * Returns the result of the division
	 * @return The result of the division
	 */
	private double divStoredWithHistory()
	{
		zeroCheckOther();
		double stored = stored_values.pop();
		double history = Double.parseDouble(sb.toString());
		double result = stored / history;
		
		if(isInt(history, (int) history))
		{
			sb_input_history.delete(0, sb_input_history.length());
			sb_input_history.append((int) history);
		}
		
		stored_values.push(result);
		
		return result;
		
	}
	
	private double factStoredValues()
	{
		zeroCheckOther();
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
		zeroCheckOther();
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
		zeroCheckOther();
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
		double first_number = stored_values.peek();
		//System.out.println(second_number);
		double result = first_number * (-1);
		
		
		if(Math.abs(result) == Math.PI)
			pi = true;
		else
			pi = false;
		//stored_values.push(result);

		
		return result;
	}

	private void negateStoredWithHistory()
	{
		double history = Double.parseDouble(sb.toString());
		double result = history * (-1);
		
		//Reset sb for update
		sb.delete(0, sb.length());
		sb_input_history.delete(0, sb_input_history.length());
		
		//If result is int, append the casted value 
		if(result - (int) result == 0)
		{
			sb.append("" + (int) result);
			sb_input_history.append("" + (int) result);
		}
		//Otherwise append the double value
		else
		{
			sb.append("" + result);
			sb_input_history.append("" +  result);
		}
			

		//stored_values.push(result);
	}
	
	private void zeroCheckBinStored()
	{
		if(stored_values.empty())
		{
			stored_values.push((double) 0);
			stored_values.push((double) 0);
			button_history.push("" +  0);
			button_history.push("" +  0);
			
		}
		if(stored_values.size() == 1)
		{
			stored_values.push((double) 0);
			button_history.push("" +  0);
			
		}
	}
	
	private void zeroCheckOther()
	{
		//If no values in system
		if(stored_values.empty())
		{
			//If operation did not involve an expression
			//Continue as normal
			if(!opExpression)
			{
				stored_values.push((double) 0);
				button_history.push("" +  0);
				System.out.println("Not expr");
			}
			//Otherwise if only one expression
			// use 0
			else if(expressions.size() < 2)
			{
				button_history.push("" +  0);
				System.out.println("One expr");
			}
			
			//running_history.add("" + 0);
		}
		//Otherwise if last op used an expression
		//remove last value and place in expression list
		else if(opExpression)
		{
			expressions.add("" + stored_values.pop());
		}
		
		
	}
	
	/**
	 * Checks whether an inputed string is an operation
	 * or not
	 * @param input - the String to be checked
	 * @return true if input is an operation. False otherwise
	 */
	private boolean isOp(String input)
	{
		//If input was pi
		//not an operation
		if(pi || input.equals(EXPRESSION))
			return false;
		
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
	
	/**
	 * Checks whether parenthesis are required after an operation
	 * (Refer to section 6.3 in Design Document for more details)
	 * @param last_entry - An operation string or number string
	 * @param operator - The operator of the recent operation
	 * @return true if paranthesis are required. False otherwise
	 */
	private boolean checkBrackets(String last_entry, String operator)
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
			//If the two last operands are the same,
			//no need for brackets UNLESS it is DIV
			// via stored_values
			if(precedence.peek().equals(operator))
			{
				if(operator.equals(DIV) && from_memory)
				{
					precedence.pop();
					return true;
				}
					
				precedence.pop();
				return false;
			}
			//If Fact
			//Brackets needed
			if(highest_precedence.contains(operator))
			{
				precedence.pop();
				return true;
			}
			
			
			
			
			//If the last operand is low precedence,
			//no brackets needed
			if(lowest_precedence.contains(operator))
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
				if(operator == DIV && precedence.peek() == MULT)
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
	
	/**
	 * Makes updates to running_history and button_history 
	 * after Case 2 of a Binary Operation (Section 5.1 in Design Document)
	 */
	private void updateHistDirect()
	{
		//Reset button-press string for next use
		sb_input_history.delete(0, sb_input_history.length());
		
		//Add updated history to the stack (First and only element)
		button_history.push(sb_completed_operations.toString());
		
		//If op involved an expression
		//replace last element with new expression op
		if(opExpression)
		{
			expressions.remove(expressions.size() - 1);
			expressions.add(button_history.peek());
		}
		
		// Reset completed operations string_builder for next entry
		sb_completed_operations.delete(0, sb_completed_operations.length());
		
		if(!running_history.isEmpty())
		{
			running_history.remove(running_history.size() - 1);
		}
			
		
		running_history.add(button_history.peek());
		
	}
	
	/**
	 * Makes updates to running_history and button_history 
	 * after Case 1 of a Binary Operation(Section 5.1 in Design Document)
	 */
	private void updateHistMem()
	{
		
		// Add updated history to the stack
		button_history.push(sb_completed_operations.toString());
		
		//If op inolved an expression
		//replace last two in list with latest one
		if(opExpression)
		{
			for(int i = 0; i < 2; i++)
			{
				if(!(expressions.isEmpty()))
				{
					expressions.remove(expressions.size() - 1);
				}
				
			}
			
			expressions.add(button_history.peek());
		}
		System.out.println(expressions.toString());
		// Reset completed operations string_builder for next use.
		// This prevents duplication of previous entries
		sb_completed_operations.delete(0, sb_completed_operations.length());
		
		//remove last two elements in running_history
		for(int i = 0; i < 2 && i > -1; i++)
		{
			if(!(running_history.isEmpty()))
			{
				running_history.remove(running_history.size() - 1);
			}
			
		}
		//replace them with updated computation
		running_history.add(button_history.peek());
	}
	
	private boolean isInt(double double_val, int int_val)
	{
		if(double_val - int_val < Double.MIN_VALUE)
			return true;
		return false;
	}
	
	private boolean isExpression()
	{
		if(button_history.empty() || expressions.isEmpty())
			return false;
		else if(expressions.contains(button_history.peek()) || 
				expressions.contains(running_history.get(running_history.size() - 2)))
		{
			return true;
		}
		return false;
		
	}
	
	
	
}
