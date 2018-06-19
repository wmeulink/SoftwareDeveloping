/****************************************************************************************	
*		The Godfather's Pizza Company Deliveries Database			*
*											*
*		This program maintains a database of information about pizzas		*
*		delivered for The Godfather's Pizza Company.				*
****************************************************************************************/	

import javax.swing.*;


public class Program3
{


	/********************************************************************************
	*				main method														*
	********************************************************************************/
	public static void main (String [] args)
	{
		int size = 5; 								//sets default size of PizzaRec orders array to 15.
		PizzaRec [] orders = new PizzaRec[size];  	//assigns memory for size of orders array
		size = loadDataBase (orders);				//returns actual used objects in orders array.
	
		selectionSort(orders, size);				//sorts orders array by order number.*/
		processTransactions (orders, size);			//processes orders*/

		System.exit(0);
	}

	/********************************************************************************
	*			selectionSort method												*
	*																				*
	*			accepts array of PizzaRec objects and an int						*
	*			parameter size that signifies size of used							*
	*			objects in array													*
	********************************************************************************/
	public static void selectionSort(PizzaRec[] orders, int size) 
	{
		int i, j, first, second, temp, temp2;
		String stringTemp;
	
		for (i = size -1; i >0; i--)
		{
			first = 0;
			second =0;
			for(j=1; j<=i; j++)
			{
				if (orders[j].getOrder() > orders[first].getOrder() )
				{
					first=j;
					second=j;
				
				}
			
				temp=orders[first].getOrder();
				orders[first].setOrder(orders[i].getOrder() );
				orders[i].setOrder(temp);

				stringTemp = orders[first].getName();
				orders[first].setName(orders[i].getName() );
				orders[i].setName(stringTemp);

				temp2=orders[second].getPizza();
				orders[second].setPizza(orders[i].getPizza());
				orders[i].setPizza(temp2);	
			}	
		}
	}

	/********************************************************************************
	*			loadDataBase method													*
	*																				*
	*			accepts array of PizzaRec objects and an int						*
	*			parameter size that signifies the maximum size 						*
	*			of objects that can be used in array.								*
	*																				*
	*			returns size of actual used objects in array						*
	********************************************************************************/
	public static int loadDataBase(PizzaRec[] orders)
	{

		/*************************************	*
		*	Default values for PizzaRec			*
		*	objects in orders array				*
		****************************************/
	
		for(int x = 0; x < orders.length; x++)
		{
			orders[x]=new PizzaRec();
		}
		/***************************************/
		
		String [] pizzaChoices = {"Cheese", "Pepperoni", 
		"Sausage and Mushroom", "Marlon's Special", "Custom"};

		String orderIn = JOptionPane.showInputDialog
		(null,"Please enter the next order number" +
		" (click 'Cancel' when finished)" );

			int i = 0;	// generic index
			int choiceNumber = 0;
			while (i < orders.length && orderIn != null)
			{
				String orderName = JOptionPane.showInputDialog
				(null,"Please enter the customer name for order " 
				+ orderIn);

				/****************************************************************
				*		simple data validation for input						*
				****************************************************************/			
				if(orderIn !=null && orderName !=null)
				{
					orders[i].setOrder(Integer.parseInt(orderIn));
					orders[i].setName(orderName);
					
					choiceNumber = JOptionPane.showOptionDialog
							(null, "What is the " +orders[i].getName() 
							+" family's preferred pizza topping?", "Pizza Toppings", 
							JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,
						 	null, pizzaChoices, pizzaChoices[1]);
					
					orders[i].setPizza(choiceNumber);
				}
				/***************************************************************/

				i++;	//increment i
				if (i < orders.length)
				{
					orderIn = JOptionPane.showInputDialog
					(null,"Please enter the next order number" +
					" (click 'Cancel' when finished)" );
				}

			}
			

		return i;	//return actual used objects in array
	}

	/********************************************************************************
	*			processTransactions method											*
	*																				*
	*			accepts array of PizzaRec objects and an int						*
	*			parameter size that signifies size of used							*
	*			objects in array													*
	********************************************************************************/
	public static void processTransactions(PizzaRec[] orders, int size)
	{
		String choice = JOptionPane.showInputDialog(null,
			"Please enter \n'L' to Look up an order number,\n\t" +
			"'S' to Show all orders, or\n\t'Q' to end");

		while ( !(choice.equalsIgnoreCase("Q") ) )
			{
				if (choice.equalsIgnoreCase("S") )
					{
						showAll( orders, size );
					}
				else if (choice.equalsIgnoreCase("L") )
					{
						lookUpOrder ( orders, size );
					} 
				choice = JOptionPane.showInputDialog(null,
				"Please enter \n'L' to Look up an order number\n\t" +
				"'S' to Show all orders, or\n\t'Q' to end");
			}
	
		 
	}

	/********************************************************
	*		showAll method				*
	*							*
	*		shows all orders			*
	********************************************************/
	public static void showAll(PizzaRec[] orders, int size) 
	{
		

		/****************************************************************************************
		*	heading for showAll method							*
		****************************************************************************************/
		System.out.println("\t\tThe Godfather's Pizza\n" 
				+ "**************************************************************\n"
				+ "\t\tOrder History\n\n"
				+ "\tOrder Number" 
				+ "\tCustomer" 
				+ "\tPizza\n"
				+ "\t-----------\t---------\t---------------------\n");


		for (int i = 0; i < size; i++) 
		{			
			System.out.println(orders[i].toString());

		}

		System.out.println("\n                     End of Report");
	}


	/********************************************************
	*		lookupOrder method			*
	*							*
	*		looks up particular order		*
	********************************************************/
	public static void lookUpOrder(PizzaRec[] orders, int size)
	{
		int searchOrder;		
		String customer, pizzaType;
		
		String [] pizzaChoices = {"Cheese", "Pepperoni", 
				"Sausage and Mushroom", "Marlon's Special", "Custom"};

		String inputValue = JOptionPane.showInputDialog ( null,
		"Enter the order you want to look up ('cancel' to skip)");
	
		if (inputValue != null)		// look for it
		{
			searchOrder = Integer.parseInt (inputValue);

			int position = binarySearch (size, orders, searchOrder);

			if (position >= 0)	// success
			{
				customer = orders[position].getName();
				pizzaType = pizzaChoices[orders[position].getPizza()];

				JOptionPane.showMessageDialog(null,"Order: "+searchOrder
					+ "\nCustomer: " + customer +"\nPizzaType: " +pizzaType);
			}
			else
			{
				
				JOptionPane.showMessageDialog(null,"Order " + inputValue +
					" is not listed");
				
			}
		}
	}




	/********************************************************
	*		binarySearch method			*
	*							*
	*		performs binary search			*
	********************************************************/
	public static int binarySearch (int size, PizzaRec[] orders, int searchOrder)
	{
		int start, mid, end; 	// indices for search space

		start = 0; end = size-1; mid = (start+end)/2;

		while(start<=end && orders[mid].getOrder() != searchOrder)
		{
			if (orders[mid].getOrder() < searchOrder)
			{
				start = mid + 1;	// search upper half
			}
			else
			{
				end = mid - 1;	// search lower half
			}
			mid = (start+end)/2;
		}
		if (start > end)			// search failed
		{
			mid = -1;
		}
		return mid;
	}
}


