import java.util.ArrayList;
import java.util.Stack;


public class Model
{
	private int start = 0;
	/**
	 * Used to enter correct routine for binary operations
	 * (Section 11.2)
	 */
	private  BinaryOperations bin_code = null;
	
	/**
	 * Used to enter correct routine for single operations
	 * (Section 11.2)
	 */
	private  SingleOperations single_code = null;
	
	/**
	 * Stores a history of entered values and operations.
	 * (Section 3.3 in Design Document)
	 */
	private Stack<String> button_history = new Stack<String>();
	
	
	/**
	 * Stores the previous states of button_history
	 */
	protected Stack<Stack<String>> button_history_undo = new Stack<Stack<String>>();
	
	private static final String EQUALS = "=";
	
	/**
	 * Stores any entered values or results of operations.
	 * (Section 3.3 in Design Document)
	 */
	private Stack<Double> stored_values = new Stack<Double>();
	/**
	 * Stores the previous states of stored_values
	 */
	protected Stack<Stack<Double>> stored_values_undo = new Stack<Stack<Double>>();
	
	/**
	 * A like-for-like copy of button_history.
	 * Used to print the history.
	 * (Section 3.3 in Design Document)
	 */
	private ArrayList<String> running_history = new ArrayList<String>();
	/**
	 * Stores the previous states of running_history
	 */
	protected Stack<ArrayList<String>> running_history_undo = new Stack<ArrayList<String>>();
	
	
	/**
	 * Stores the operators that are responsible for values
	 * in stored_values (excluding Entered numbers)
	 * (Section 3.3 in Design Document)
	 */
	
	private Stack<String> precedence= new Stack<String>();
	/**
	 * Stores the previous states of precedence
	 */
	protected Stack<Stack<String>> precedence_undo = new Stack<Stack<String>>();

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
	 * A reference to high precedence operators
	 */
	private ArrayList<String> highest_precedence = new ArrayList<String>();
	
	/**
	 * Stores a list of expressions in
	 * InFix notation
	 */
	private Stack<String> expressionsInFix = new Stack<String>();
	/**
	 * Stores the previous states of expressionsInFix
	 */
	protected Stack<Stack<String>> expressionsInFix_undo = new Stack<Stack<String>>();
	/**
	 * Stores a list of expressions in
	 * PostFix notation
	 */
	private Stack<String> expressionsPostFix = new Stack<String>();
	/**
	 * Stores the previous states of expressionsPostFix
	 */
	protected Stack<Stack<String>> expressionsPostFix_undo = new Stack<Stack<String>>();
	
	/**
	 * Used to trace character presses.
	 * (Section 3.3 in Design Document)
	 */
	protected StringBuilder sb = new StringBuilder();
	
	/**
	 * Same functionality as sb except used for
	 * updates to history
	 * (Section 3.3 in Design Document)
	 */
	private StringBuilder sb_input_history = new StringBuilder();
	
	
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
	private boolean from_memory = true;
	/**
	 * Indicates whether pi was entered into the system
	 */
	private boolean pi = false;
	/**
	 * Indicates whether an error has occured
	 */
	private boolean error = false;
	
	/**
	 * Indicates whether an operation uses an expression
	 */
	private boolean opExpression = false;
	
	/**
	 * Indicates whether the expression list has already been
	 * updated
	 */
	private boolean exprUpdated = false;
	
	
	/**
	 * Instantiate a Model object in its default state
	 */
	public Model()
	{
		reset();
		
		high_precedence.add(Controller.MULT);
		high_precedence.add(Controller.DIV);
		
		lowest_precedence.add(Controller.PLUS);
		lowest_precedence.add(Controller.MINUS);
		
		
		highest_precedence.add(Controller.FACT);
		
	}
	
	/**
	 * Gets the expressions list in InFix notation
	 * @return - the expressions list in InFix notation
	 */
	public Stack<String> getInFixExpressionList()
	{
		return expressionsInFix;
	}
	/**
	 * Gets the expressions list in PostFix notation
	 * @return - the expressions list in PostFix notation
	 */
	public Stack<String> getPostExpressionList()
	{
		return expressionsPostFix;
	}
	/**
	 * Returns the String representation of running_history as 
	 * specified in the Requirements Document
	 * @return The string representation of running_history
	 */
	public String printHistory()
	{
		int size = running_history.size();
		if(size == 0)
			return "";
		
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < size - 1; i++)
		{
			result.append(running_history.get(i) + ", ");
		}
		//Add last element
		result.append(running_history.get(size - 1));
		return result.toString();
		
	}
	
	
	/**
	 * Enters the Java representation of PI into stored_values
	 * @return the Java value of Pi as a String
	 */
	@SuppressWarnings("unchecked")
	public String valuePi()
	{
		pi = true;
		from_memory = true;
		sb.delete(0, sb.length());
		sb_input_history.delete(0, sb_input_history.length());
		//Update previous state of stored_values
		if(!button_history.empty())
			stored_values_undo.push((Stack<Double>) stored_values.clone());
		stored_values.push(Math.PI);
		return "" + Math.PI;
	}
	
	/**
	 * Enters the symbol of PI into button_history and running_history
	 * @return The updated history String
	 */
	@SuppressWarnings("unchecked")
	public String historyPi()
	{
		if(!button_history.empty())
		{	//Update previous state of history lists and precedence
			button_history_undo.push((Stack<String>) button_history.clone());
			running_history_undo.push((ArrayList<String>) running_history.clone());
			precedence_undo.push((Stack<String>)precedence.clone());
		}
		button_history.push(Controller.PI);
		running_history.add(Controller.PI);
		return printHistory();
	}
	
	/**
	 * Returns the expression symbol to be displayed
	 * in the value field
	 * @return - the expression symbol
	 */
	@SuppressWarnings("unchecked")
	public String expressionVal()
	{
		sb.delete(0, sb.length());
		sb_input_history.delete(0, sb_input_history.length());
		//Update previous state of stored_values
		if(!button_history.empty())
			stored_values_undo.push((Stack<Double>) stored_values.clone());
		return Controller.EXPRESSION;
	}
	
	/**
	 * Adds the expression symbol to the history and expression list.
	 * Returns the updated history
	 * @return - the updated history
	 */
	@SuppressWarnings("unchecked")
	public String expressionHist()
	{
		if(!button_history.empty())
		{	//Update previous state of history lists and precedence
			button_history_undo.push((Stack<String>) button_history.clone());
			running_history_undo.push((ArrayList<String>) running_history.clone());
			precedence_undo.push((Stack<String>)precedence.clone());
		}
		if(!expressionsInFix.empty())
		{	//Update previous state of expression lists
			expressionsInFix_undo.push((Stack<String>) expressionsInFix.clone());
			expressionsPostFix_undo.push((Stack<String>) expressionsPostFix.clone());
		}
		
		if(expressionsInFix.empty() || !exprUpdated)
			button_history.push(Controller.EXPRESSION);
		else 
			button_history.push(expressionsInFix.peek());
			
		expressionsInFix.push(button_history.peek());
		running_history.add(button_history.peek());
		
		if(expressionsPostFix.empty() || expressionsInFix.peek().equals(Controller.EXPRESSION))
			expressionsPostFix.push(Controller.EXPRESSION);
		else
		{
			int size = expressionsPostFix.size();
			//Copy elements
			while(start < size)
				expressionsPostFix.push(expressionsPostFix.get(start++));
		
			if(isOp(expressionsInFix.peek()))
				precedence.push(precedence.peek());
			
		}System.out.println(expressionsPostFix);
		System.out.println(start);
		return printHistory();
	}
	
	/**
	 * Appends the next character to sb
	 * @param button - the character to be appended
	 */
	public void addToEntry(String button)
	{
		
		sb.append(button);
		sb_input_history.append(button);
		pi = false;
		from_memory = false;
		opExpression = false;
		
		
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
		stored_values_undo.clear();
		button_history.clear();
		button_history_undo.clear();
		precedence.clear();
		precedence_undo.clear();
		expressionsInFix.clear();
		expressionsInFix_undo.clear();
		expressionsPostFix.clear();
		expressionsPostFix_undo.clear();
		
		opExpression = false;
		exprUpdated = false;
		pi = false;
		error = false;
		
		sb_input_history.delete(0, sb_input_history.length());
		sb_completed_operations.delete(0, sb_completed_operations.length());
		running_history.clear();
		running_history_undo.clear();
		sb.delete(0, sb.length());
	
		start = 0;
	}
	
	/**
	 * Carries out the addition of two operands
	 * and returns the result
	 * (Section 3.4 in Design Document)
	 * @return The value of the addition as a String
	 */
	@SuppressWarnings("unchecked")
	public String sum()
	{
		//Determine whether an expression is involved
		opExpression = isExpressionBin();
		bin_code = new SumOperation();
		//Update previous state of stored_values
		if(!button_history.empty())
			stored_values_undo.push((Stack<Double>) stored_values.clone());
		System.out.println("GG " + stored_values_undo);
		if(from_memory)
		{	
			System.out.println(stored_values_undo);
			//If not expression, continue as normal
			if(!opExpression)
			{
				bin_code.zeroCheckBinary(stored_values, button_history);
				String value = bin_code.execute(stored_values.pop(), stored_values.pop());
				System.out.println(stored_values_undo);
				stored_values.push(Double.parseDouble(value));
				System.out.println(stored_values_undo);
				return value;	
			}
			//Otherwise update the history
			//and return top element (new expression)
			zeroCheckExpr();
			operandHistory(Controller.PLUS);
			return button_history.peek() ;
		}
		//If not expression, continue as normal
		if(!opExpression)
		{
			bin_code.zeroCheckSingle(stored_values, button_history);
			String value = bin_code.execute(Double.parseDouble(sb.toString()), stored_values.pop());
			sb.delete(0, sb.length());
			stored_values.push(Double.parseDouble(value));
			return "" + value;
		}
		//Otherwise update the history
		//and return top element (new expression)
		sb.delete(0, sb.length());
		operandHistory(Controller.PLUS);
		return button_history.peek();
	}
	
	
	/**
	 * Carries out the subtraction of two operands
	 * and returns the result
	 * (Section 3.4 in the Design Document)
	 * @return The value of the subtraction as a String
	 */
	@SuppressWarnings("unchecked")
	public String subtract()
	{
		//Determine whether an expression is involved
		opExpression = isExpressionBin();
		bin_code = new MinusOperation();
		//Update previous state of stored_values
		if(!button_history.empty())
			stored_values_undo.push((Stack<Double>) stored_values.clone());
		if(from_memory)
		{	
			//If not expression, continue as normal
			if(!opExpression)
			{
				bin_code.zeroCheckBinary(stored_values, button_history);
				String value = bin_code.execute(stored_values.pop(), stored_values.pop());
				stored_values.push(Double.parseDouble(value));
				return value;	
			}
			//Otherwise update the history
			//and return top element (new expression)
			zeroCheckExpr();
			operandHistory(Controller.MINUS);
			return button_history.peek() ;
		}
		//If not expression, continue as normal
		if(!opExpression)
		{
			bin_code.zeroCheckSingle(stored_values, button_history);
			String value = bin_code.execute(Double.parseDouble(sb.toString()), stored_values.pop());
			sb.delete(0, sb.length());
			stored_values.push(Double.parseDouble(value));
			return "" + value;
		}
		//Otherwise update the history
		//and return top element (new expression)
		sb.delete(0, sb.length());
		operandHistory(Controller.MINUS);
		return button_history.peek();
	}
	
	/**
	 * Carries out the product of two operands
	 * and returns the result
	 * (Section 3.4 in the Design Document)
	 * @return The value of the product as a String
	 */
	@SuppressWarnings("unchecked")
	public String multiply()
	{
		//Determine whether an expression is involved
		opExpression = isExpressionBin();
		bin_code = new MultOperation();
		//Update previous state of stored_values
		if(!button_history.empty())
			stored_values_undo.push((Stack<Double>) stored_values.clone());
		if(from_memory)
		{	
			//If not expression, continue as normal
			if(!opExpression)
			{
				bin_code.zeroCheckBinary(stored_values, button_history);
				String value = bin_code.execute(stored_values.pop(), stored_values.pop());
				stored_values.push(Double.parseDouble(value));
				return value;	
			}
			//Otherwise update the history
			//and return top element (new expression)
			zeroCheckExpr();
			operandHistory(Controller.MULT);
			return button_history.peek() ;
		}
			//If not expression, continue as normal
			if(!opExpression)
			{
				bin_code.zeroCheckSingle(stored_values, button_history);
				String value = bin_code.execute(Double.parseDouble(sb.toString()), stored_values.pop());
				sb.delete(0, sb.length());
				stored_values.push(Double.parseDouble(value));
				return "" + value;
			}
			//Otherwise update the history
			//and return top element (new expression)
			sb.delete(0, sb.length());
			operandHistory(Controller.MULT);
			return button_history.peek();
	}
	
	/**
	 * Carries out the division of two operands
	 * and returns the result
	 * (Section 3.4 in the Design Document)
	 * @return The value of the division as a String
	 */
	@SuppressWarnings("unchecked")
	public String divide()
	{
		//Determine whether an expression is involved
		opExpression = isExpressionBin();
		bin_code = new DivideOperation();
		//Update previous state of stored_values
		if(!button_history.empty())
			stored_values_undo.push((Stack<Double>) stored_values.clone());
		if(from_memory)
		{	
			//If not expression, continue as normal
			if(!opExpression)
			{
				bin_code.zeroCheckBinary(stored_values, button_history);
				String value = bin_code.execute(stored_values.pop(), stored_values.pop());
				stored_values.push(Double.parseDouble(value));
				return value;	
			}
			//Otherwise update the history
			//and return top element (new expression)
			zeroCheckExpr();
			operandHistory(Controller.DIV);
			return button_history.peek() ;
		}
			//If not expression, continue as normal
			if(!opExpression)
			{
				bin_code.zeroCheckSingle(stored_values, button_history);
				String value = bin_code.execute(Double.parseDouble(sb.toString()), stored_values.pop());
				sb.delete(0, sb.length());
				stored_values.push(Double.parseDouble(value));
				return "" + value;
			}
			//Otherwise update the history
			//and return top element (new expression)
			sb.delete(0, sb.length());
			operandHistory(Controller.DIV);
			return button_history.peek();
	}
	/**
	 * s either the top element of stored_values
	 * or the element currently constructed
	 * @return - A string representation of the negated element
	 */
	public String negate()
	{
		opExpression = isExpression();
		single_code = new NegateOperation();
		if(from_memory)
		{	
			stored_values_undo.push((Stack<Double>) stored_values.clone());
			if(!opExpression)
			{	
				single_code.zeroCheckSingle(stored_values, button_history);
				String value = single_code.execute(stored_values.pop());
				stored_values.push(Double.parseDouble(value));
				return value;
			}
			negateHistory();
			return button_history.peek();
		}
		else
		{
			//If negation during number entry,
			//the value shown to the user must be updated
			//from_memory = false;
			negateStoredWithHistory();
			return sb.toString();
		}

	}
	
	/**
	 * Carries out the factorial of one operand
	 * and returns the result
	 * (Section 3.4 in the Design Document)
	 * @precondition - The operand must be an integer only and must
	 * be smaller than 171
	 * @postcondition - The operation is carried out
	 * @return The value of the factorial as a String or
	 * NOT DEFINED if the precondition is not met
	 */
	@SuppressWarnings("unchecked")
	public String factorial()
	{
		single_code = new FactorialOperation();
		opExpression = isExpression();
		if(opExpression)
		{	//Precondition not met
			error = true;
			return "NOT DEFINED";
		}
		
		if(from_memory)
		{
			single_code.zeroCheckSingle(stored_values, button_history);
			double input = stored_values.peek();
			String value = single_code.execute(input);
			
			if(value.equals("NOT DEFINED"))
			{	//Precondition not met
				error = true;
			}
			else
			{	//Update previous state of stored_values
				stored_values_undo.push((Stack<Double>) stored_values.clone());
				stored_values.pop();
				stored_values.push(Double.parseDouble(value));
			}
			return value;
		}
		double input = Double.parseDouble(sb.toString());
		String value = single_code.execute(input);
		sb.delete(0, sb.length());
		
		if(value.equals("NOT DEFINED"))
		{
			sb_input_history.delete(0, sb_input_history.length());
			error = true;
		}
		else
		{	//Update previous state of stored_values
			if(!stored_values.empty())
				stored_values_undo.push((Stack<Double>) stored_values.clone());
			stored_values.push(Double.parseDouble(value));
		}
		return value;	
	}
	
	/**
	 * Carries out the sin of one operand
	 * and returns the result
	 * (Section 3.4 in the Design Document)
	 * @return The value of the sin as a String
	 */
	@SuppressWarnings("unchecked")
	public String sin()
	{
		//Determine whether an expression is involved
		opExpression = isExpression();
		single_code = new SinOperation();
	
		if(from_memory)
		{
			stored_values_undo.push((Stack<Double>) stored_values.clone());
			//If not expression, continue as normal
			if(!opExpression)
			{
				single_code.zeroCheckSingle(stored_values, button_history);
				String value = single_code.execute(stored_values.pop());
				stored_values.push(Double.parseDouble(value));
				return value;
			}
			//Otherwise update the history
			//and return top element (new expression)
			sb.delete(0, sb.length());
			trigHistory(Controller.SIN);
			return button_history.peek();
		}
		//Update previous state of stored_values
		if(!stored_values.empty())
			stored_values_undo.push((Stack<Double>) stored_values.clone());
		
		double history = Double.parseDouble(sb.toString());
		sb.delete(0, sb.length());
		String value = single_code.execute(history);
		stored_values.push(Double.parseDouble(value));
		return "" + value;
	}

	/**
	 * Carries out the cosine of one operand
	 * and returns the result
	 * (Section 3.4 in the Design Document)
	 * @return The value of the cosine as a String
	 */
	@SuppressWarnings("unchecked")
	public String cos()
	{
		//Determine whether an expression is involved
		opExpression = isExpression();
		single_code = new CosOperation();
				
		if(from_memory)
		{
			//If not expression, continue as normal
			if(!opExpression)
			{
				stored_values_undo.push((Stack<Double>) stored_values.clone());
				single_code.zeroCheckSingle(stored_values, button_history);
				String value = single_code.execute(stored_values.pop());
				stored_values.push(Double.parseDouble(value));
				return value;
			}
			//Otherwise update the history
			//and return top element (new expression)
			sb.delete(0, sb.length());
			trigHistory(Controller.COS);
			return button_history.peek();		
		}
		//Update previous state of stored_values
		if(!stored_values.empty())
			stored_values_undo.push((Stack<Double>) stored_values.clone());
		
		double history = Double.parseDouble(sb.toString());
		sb.delete(0, sb.length());
		String value = single_code.execute(history);
		stored_values.push(Double.parseDouble(value));
		return "" + value;
	}
	
	/**
	 * Updates button_history and running_history
	 * after a Binary Operation. Returns the updated
	 * history string
	 * @param operator - The operator of the binary operation
	 * @precondition - error is false
	 * @postcondition - The stacks are updated accordingly
	 * @return The updated history string or the unchanged history
	 * string if the precondition is not met
	 */
	@SuppressWarnings("unchecked")
	public String operandHistory(String operator)
	{
		String first;
		String second;
		//If call made with an expression
		//that was already updated, set both boolean
		//variables to false and return without any changes made
		if(opExpression && exprUpdated)
		{
			exprUpdated = false;
			return printHistory() + " " + EQUALS;
		}
		//Else if expression has not been updated,
		//set boolean variable true to indicate that it will be
		//after this call
		else if(opExpression && !exprUpdated)
		{
			exprUpdated = true;
			expressionsInFix_undo.push((Stack<String>) expressionsInFix.clone());
			expressionsPostFix_undo.push((Stack<String>) expressionsPostFix.clone());
		}
		
		if(!button_history.empty())
		{
			button_history_undo.push((Stack<String>) button_history.clone());
			running_history_undo.push((ArrayList<String>) running_history.clone());
			precedence_undo.push((Stack<String>)precedence.clone());
		}
		//If not expression, continue as normal
		second = button_history.pop();
		if(!from_memory)
		{
			//Necessary check for parentheses
			if(!checkBrackets(second, operator))
				sb_completed_operations.append(second + " " + operator + " " + sb_input_history.toString());
			else
				sb_completed_operations.append("(" + second + ")" + " " + operator + " " +  sb_input_history.toString());
			
			//Update precedence list
			precedence.push(operator);
			if(opExpression)
			{	//Update the PostFix expressions list
				expressionsPostFix.push(sb_input_history.toString());
				expressionsPostFix.push(operator);
			}
			updateHistDirect();
			from_memory = !from_memory;
			return printHistory() + " " + EQUALS;
		}
		//Else, use the last two entries in the stack and anything else as necessary
		first = button_history.pop();
		if(opExpression)
		{	//Update the PostFix expressions list
			if(!stored_values.empty())
				expressionsPostFix.push("" + stored_values.peek());
			else if(isOp(first) && isOp(second))
				start = 0;
			expressionsPostFix.push(operator);
		}
		formNewEntry(first, second, operator);
		//Update precedence list
		precedence.push(operator);
		updateHistMem();
		return printHistory() + " " +  EQUALS;
	}
	
	/**
	 * Updates button_history and running_history
	 * after a factorial. Returns the updated
	 * history string
	 * @param operator - The operator of the factorial operation
	 * @precondition - error is false
	 * @postcondition - The stacks are updated accordingly
	 * @return The updated history string or the unchanged history
	 * string if the precondition is not met
	 */
	@SuppressWarnings("unchecked")
	public String factHistory(String operator)
	{	//Preconditions for factorial not met
		if(error)
		{
			error = false;
			return printHistory();
		}
		//Update the previous state of the history lists and precedence
		if(!button_history.empty())
		{
			button_history_undo.push((Stack<String>) button_history.clone());
			running_history_undo.push((ArrayList<String>) running_history.clone());
			precedence_undo.push((Stack<String>)precedence.clone());
		}
		
		if(!from_memory)
		{
			//set the string
			sb_completed_operations.append(sb_input_history.toString() + operator);
			//Reset button-press string for next use
			sb_input_history.delete(0, sb_input_history.length());
			//Add updated history to the stack (First and only element)
			//running_history_undo.push((ArrayList<String>) running_history.clone());
			button_history.push(sb_completed_operations.toString());
			// Reset completed operations string_builder for next entry
			sb_completed_operations.delete(0, sb_completed_operations.length());
			//replace them with updated computation
			running_history.add(button_history.peek());
			//prev_history.push(operator);
			//Update precedence list
			precedence.push(operator);
			System.out.println(precedence.toString());
			from_memory = !from_memory;
			return printHistory() + " " + EQUALS;
		}
		
		String first = button_history.pop();
		if(!checkBrackets(first, operator))
			sb_completed_operations.append(first + operator);
		else
			sb_completed_operations.append("(" + first + ")" + operator);
			
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
		//print updated history
		return printHistory() + " " + EQUALS;
	}
	
	public String negateHistory()
	{
		if(!from_memory)
			return printHistory();
		
		//If call made with an expression
		//that was already updated, set both boolean
		//variables to false and return without any changes made
		if(opExpression && exprUpdated)
		{
			exprUpdated = false;
			return printHistory() + " " + EQUALS;
		}
		//Else if expression has not been updated,
		//set boolean variable true to indicate that it will be
		//after this call
		else if(opExpression && !exprUpdated)
		{
			exprUpdated = true;
			//Update the previous states of expressions list 
			expressionsInFix_undo.push((Stack<String>) expressionsInFix.clone());
			expressionsPostFix_undo.push((Stack<String>) expressionsPostFix.clone());
			expressionsPostFix.push(Controller.PLUSMINUS);
		}
				
		if(!button_history.empty())
		{	//Update the previous states of history lists and precedence 
			button_history_undo.push((Stack<String>) button_history.clone());
			running_history_undo.push((ArrayList<String>) running_history.clone());
			precedence_undo.push((Stack<String>)precedence.clone());
		}
		
		String first = button_history.pop();
		System.out.println(first);
		if(isOp(first))
		{	
			if(first.charAt(0) == '-' && first.charAt(1) == '(')
			{	sb_completed_operations.append(first);
				sb_completed_operations.deleteCharAt(0);
				sb_completed_operations.deleteCharAt(0);
				sb_completed_operations.deleteCharAt(sb_completed_operations.length() - 1);
			}
			else
				sb_completed_operations.append("-(" + first + ")");
		}	
		else
		{
			if(first.charAt(0) != '-')
				sb_completed_operations.append("-" + first);
			else
			{	
				sb_completed_operations.append(first);
				sb_completed_operations.deleteCharAt(0);
			}
				
		}
		
		button_history.push(sb_completed_operations.toString());
		running_history.remove(running_history.size() - 1);
		running_history.add(button_history.peek());
		if(opExpression)
			expressionsInFix.push(button_history.peek());
		sb_completed_operations.delete(0, sb_completed_operations.length());
		return printHistory() + " " + EQUALS;
	}
				
	/**
	 * Adds the 'Entered' number into running_history 
	 * and button_history. Returns the updated history string.
	 * @precondition - the entry is a number
	 * @postcondition - the entry is pushed onto the history stacks
	 * @return The updated history string or the unchanged history
	 * string if the precondition is not met
	 */
	@SuppressWarnings("unchecked")
	public String enterHistory()
	{	//Precondition not met
		if(error || exprUpdated)
		{
			exprUpdated = false;
			error = false;
			return printHistory();
		}
			
	
		//Update the previous states of the history lists and precedence
		if(!button_history.empty())
		{
			running_history_undo.push((ArrayList<String>)running_history.clone());
			button_history_undo.push((Stack<String>) button_history.clone());
			precedence_undo.push((Stack<String>)precedence.clone());
		}
		//Pushing the pi symbol	
		if(pi)
		{
			button_history.push(sb_input_history.toString());
			running_history.add(sb_input_history.toString());
		}
		else
		{	//pushing the decimal value
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
	@SuppressWarnings("unchecked")
	public String trigHistory(String funct)
	{
		//If call made with an expression
		//that was already updated, set both boolean
		//variables to false and return without any changes made
		if(opExpression && exprUpdated)
		{
			exprUpdated = false;
			return printHistory() + " " + EQUALS;
		}
		//Else if expression has not been updated,
		//set boolean variable true to indicate that it will be
		//after this call
		else if(opExpression && !exprUpdated)
		{
			exprUpdated = true;
			//Update the previous states of expressions list 
			expressionsInFix_undo.push((Stack<String>) expressionsInFix.clone());
			expressionsPostFix_undo.push((Stack<String>) expressionsPostFix.clone());
			expressionsPostFix.push(funct);
		}
		
		if(!button_history.empty())
		{	//Update the previous states of history lists and precedence 
			button_history_undo.push((Stack<String>) button_history.clone());
			running_history_undo.push((ArrayList<String>) running_history.clone());
			precedence_undo.push((Stack<String>)precedence.clone());
		}
		
		if(!from_memory)
		{
			//set the string
			sb_completed_operations.append(funct + sb_input_history.toString() + ")");
			//Reset button-press string for next use
			sb_input_history.delete(0, sb_input_history.length());
			//Add updated history to the stack (First and only element)
			button_history.push(sb_completed_operations.toString());
			// Reset completed operations string_builder for next entry
			sb_completed_operations.delete(0, sb_completed_operations.length());
			precedence.push(funct);
			//replace them with updated computation
			running_history.add(button_history.peek());
			from_memory = !from_memory;
			return printHistory() + " " + EQUALS;
			
		}
		String first = button_history.pop();
		sb_completed_operations.append(funct + first + ")");
		if(isOp(first))
			precedence.pop();
		precedence.push(funct);	
		sb_input_history.delete(0, sb_input_history.length());
		// Add updated history to the stack
		button_history.push(sb_completed_operations.toString());
		// Reset completed operations string_builder for next use.
		// This prevents duplication of previous entries
		sb_completed_operations.delete(0, sb_completed_operations.length());
		//remove last element in running_history
		if(!(running_history.isEmpty()))
			running_history.remove(running_history.size() - 1);
		
		if(!(expressionsInFix.isEmpty()))
			expressionsInFix.pop();
		
		if(opExpression)
			expressionsInFix.push(button_history.peek());
		//replace them with updated computation
		running_history.add(button_history.peek());
		System.out.println(expressionsInFix.toString());
		//print updated history
		return printHistory() + " " + EQUALS;
	}
	
	/**
	 * Enters a value into stored_values. Returns the entered value.
	 * @precondition - the entry is a parsable number
	 * @postcondition - the entry is pushed onto stored_values
	 * @return The entered value as a String or INVALID
	 * if the precondition is not met
	 */
	@SuppressWarnings("unchecked")
	public String enterValue()
	{
		from_memory = true;
		//Enter from value field
		if(sb.toString().equals(""))
		{	//Enter from default state
			if(stored_values.empty() && (!isExpression()))
			{
				stored_values.push((double) 0);
				sb.append(0);
				return "0";
			}
			//Entering an expression
			else if(!expressionsInFix.empty() && expressionsInFix.peek().equals(button_history.peek()))
			{
				exprUpdated = true;
				expressionVal();
				expressionHist();
				System.out.println("Haha");
				return button_history.peek();
			}
			//Entering from value field
			prepareForEnter();
		}
		//Error checking
		double pushed;
		try
		{
			pushed = Double.parseDouble(sb.toString());
		}
		catch (NumberFormatException e)
		{	//element not a parsable number
			error = true;
			System.out.println(sb.toString() + "Hi");
			sb.delete(0, sb.length());
			sb_input_history.delete(0, sb_input_history.length());
			return "INVALID";
		}
		
		error = false;
		//Update tracking of previous stored_value states
		if(!stored_values.empty() || isExpression())
			stored_values_undo.push((Stack<Double>) stored_values.clone());
		
		stored_values.push(pushed);
		if(isInt(Math.abs(pushed), Math.abs((int) pushed)))
		{
			sb.delete(0, sb.length());
			sb.append("" + (int) pushed);
		}	
		return sb.toString();
	}
	
	/**
	 * Sets sb and sb_input_history to the element in the value field.
	 * This allows for entering directly from value field
	 */
	private void prepareForEnter()
	{
		double push = stored_values.peek();
		//Set sb_input_history as int
		if(isInt(push, (int) push))
		{
			sb_input_history.append((int) push);
			//pi = false;
		}
		//Set sb_input_history as pi symbol for history
		if(Math.abs(push) == Math.PI)
		{
			sb_input_history.append(button_history.peek());
			pi = true;
		}
		//Set sb_input_history as double
		else
		{
			pi = false;
			sb_input_history.append(push);
		}	
		sb.append(push);	
	}
	/**
	 * Negates the char sequence being constructed
	 */
	private void negateStoredWithHistory()
	{
		double history = Double.parseDouble(sb.toString());
		double result = history * (-1);
		//Reset sb for update
		sb.delete(0, sb.length());
		sb_input_history.delete(0, sb_input_history.length());
		//If result is int, append the casted value 
		if(isInt(result, (int) result))
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
	}
	/**
	 * Replaces missing operand with 0 for expression operation
	 * 
	 */
	private void zeroCheckExpr()
	{
		//If no values in system
		if(stored_values.empty())
		{	//Replace missing operand with 0
			if(expressionsInFix.size() < 2)
				button_history.push("" +  0);
			
			System.out.println("Hi " + button_history.peek());
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
		//If input was pi or x
		//not an operation
		if(input.equals(Controller.PI) || input.equals(Controller.EXPRESSION) ||
				input.equals("-" + Controller.EXPRESSION) || input.equals("-" + Controller.PI))
			return false;
		//Otherwise try to parse it
		try
		{
			Double.parseDouble(input);
		}
		catch (NumberFormatException e)
		{	//Could not parse therefore op
			return true;
		}
		return false;
	}
	
	/**
	 * Checks whether parenthesis are required after an operation
	 * (Refer to section 6.3 in Design Document for more details)
	 * @param last_entry - An operation string or number string
	 * @param operator - The operator of the recent operation
	 * @return true if parentheses are required. False otherwise
	 */
	private boolean checkBrackets(String last_entry, String operator)
	{
		if(precedence.empty() || !isOp(last_entry))
		{
			return false;
		}
		
		//If last op was trig
		//No brackets needed
		if(precedence.peek().equals(Controller.COS) || precedence.peek().equals(Controller.SIN)
				|| precedence.peek().equals(operator))
		{
			String prev = precedence.pop();
			//If the two last operands are the same,
			//no need for brackets UNLESS it is DIV
			// via stored_values
			if(operator.equals(Controller.DIV) && prev.equals(Controller.DIV) &&  from_memory)
			{
				return true;
			}
		}
		//If Fact
		//Brackets needed
		else if(operator.equals(Controller.FACT))
		{
				precedence.pop();
				return true;
		}	
		//If both operands are high precedence
		//additional checks needed
		else if(high_precedence.contains(operator) || lowest_precedence.contains(operator))
		{
			//If multiplication was first,
			//brackets needed
			String prev = precedence.pop();
			if((operator.equals(Controller.DIV) && prev.equals(Controller.MULT)) || 
					lowest_precedence.contains(prev) )
				return true;		
		}		
		return false;
	}
	/**
	 * Constructs sb_completed_operations to the appropriate format
	 * for Binary Operations (Section 10.7 and 12.4 in Design Document)	
	 * @param first - the first operand
	 * @param second - the second operand
	 * @param operator - the operator
	 */
	private void formNewEntry(String first, String second, String operator)
	{
		//If the last element needs brackets
		if(checkBrackets(second, operator))	
		{	//And so does the one before
			//Put them
			if(checkBrackets(first, operator))
				sb_completed_operations.append("(" + first +  ") " + operator + " " + "(" + second + ")");
			//Otherwise just on the last element
			else
				sb_completed_operations.append(first +  " " + operator + " " + "(" + second + ")");			
		}
		//If only the one before needs brackets
		else if(checkBrackets(first, operator))
			sb_completed_operations.append("(" + first +  ") " + operator + " " + second);
		//No brackets at all
		else
			sb_completed_operations.append(first + " " + operator + " " /*+ sb_input_history.toString() + " "*/ + second);
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
			expressionsInFix.pop();
			expressionsInFix.push(button_history.peek());
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
			expressionsInFix.pop();
			if(!expressionsInFix.empty())
				expressionsInFix.pop();
			expressionsInFix.push(button_history.peek());
		}
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
	/**
	 * Checks if there is a discrepancy between an integer value and 
	 * double value
	 * @param double_val - the double-precision floating point representaion of the value
	 * @param int_val - the integer representation  of the value
	 * @return - true if there is no discrepancy. False otherwise
	 */
	private boolean isInt(double double_val, int int_val)
	{
		if(Math.abs(double_val - int_val) < Double.MIN_VALUE)
			return true;
		return false;
	}
	/**
	 * Checks if either of the last two elements in history are
	 * expressions are not
	 * @return - true if at least one of the elements is an expression. False otherwise.
	 */
	private boolean isExpression()
	{
		if(button_history.empty() || expressionsInFix.isEmpty())
			return false;
		else if(expressionsInFix.contains(button_history.peek())  && from_memory)
			return true;
		return false;
	}
	
	private boolean isExpressionBin()
	{
		if(button_history.empty() || expressionsInFix.isEmpty())
			return false;
		else if(expressionsInFix.contains(button_history.peek()) || 
				expressionsInFix.contains(running_history.get(running_history.size() - 2)) && from_memory)
			return true;
		return false;
	}
	
	/**
	 * Changes stored_values to its previous state. Returns
	 * the element that was in the value field during
	 * the previous state
	 * @return - the element that was in the value field during the 
	 * previous state
	 */
	public String undoValue()
	{
		//Case 1 : Undoing a character entry OR negate
		if(!sb.toString().equals(""))
		{
			sb.deleteCharAt(sb.length()-1);
			sb_input_history.deleteCharAt(sb_input_history.length() - 1);
			System.out.println(sb.length());
			if(sb.length() > 0)
				return updateValue();
			
			from_memory = true;
			if(button_history.empty())
				return "0";
			
			if(expressionsInFix.contains(button_history.peek()))
				return "" + button_history.peek();
			
			double result = stored_values.peek();
			if(isInt(result, (int) result))
				return (int) result + "";
			return  result + "";
			
		}
		//Case 2: Reached the default state by undos
		if(stored_values_undo.empty())
		{
			if(!stored_values.empty())
				stored_values.pop();
			return "" + 0;
		}
		Stack<Double> popped = stored_values_undo.pop();
		stored_values = popped;
		//Case 3 - Undoing something expression-related
		if(expressionsInFix.contains(button_history.peek()))
			return "" + button_history.peek();
		//Case 4 - Undoing with no expressions involved
		double result = stored_values.peek();
		if(isInt(result, (int) result))
			return (int) result + "";
		return  result + "";
	}
	/**
	 * Changes the history stacks, precedence stack and expressions stack
	 * to their previous state. Returns the history string of the previous
	 * state 
	 * @return - the history string of the previous state
	 */
	public String undoHistory()
	{	
		//If character undo, no changes needed
		if(sb.length() == 1)
		{
			if(button_history.empty())
				return "Start new Calculation";
			
			if(isOp(running_history.get(running_history.size() - 1)))
				return printHistory() + " " + EQUALS;
			return printHistory();
		}
		else if(sb.length() > 1)
			return printHistory();
		
		//Change precedence to its previous state
		if(!precedence_undo.empty())
			precedence = precedence_undo.pop();
		
		//Calculator has reached default state
		if(stored_values_undo.empty())
		{
			running_history.clear();
			button_history.clear();
			expressionsInFix.clear();
			expressionsPostFix.clear();	
			return "Start new Calculation";
		}
		//Undoing an expression
		if(expressionsInFix.contains(button_history.peek()))
		{
			if(!expressionsInFix_undo.empty())
			{
				expressionsInFix = expressionsInFix_undo.pop();
				expressionsPostFix = expressionsPostFix_undo.pop();
			}
			else
			{
				expressionsInFix.pop();
				expressionsPostFix.pop();	
			}
		}
		//Updates to previous states
		running_history = running_history_undo.pop();
		button_history = button_history_undo.pop();	
		//If the last element of previous state
		//was an op, '=' required
		if(isOp(running_history.get(running_history.size() - 1)))
			return printHistory() + " " + EQUALS;
		return printHistory();
	}
	
}


