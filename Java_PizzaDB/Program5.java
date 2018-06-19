/****************************************************************************************	
*		The Godfather's Pizza Company Deliveries Database					*
*																			*
*		This program maintains a database of information about pizzas		*
*		delivered for The Godfather's Pizza Company.						*
****************************************************************************************/	


import java.util.*;
import java.util.StringTokenizer;
import javax.swing.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Program5
{


	/********************************************************************************
	*				main method														*
	********************************************************************************/
	public static void main (String [] args)
	{
		int size = 100; 							//sets default size of PizzaRec orders array to 100.
		PizzaRec [] orders = new PizzaRec[size];  	//assigns memory for size of orders array
		String[] badOrders = new String[size];		
		
		int [] counts = new int[2];
		
		size = loadDataBase (orders, badOrders, counts);
		
		
		if (size!=-1)
		{
		
		selectionSort(orders, counts[0]);				//sorts orders array by order number.*/
		processTransactions (orders, badOrders, counts);			//processes orders*/
		
		}
		
	
		
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
		int i, j, first;
		PizzaRec temp;
		
	
		for (i = size -1; i >0; i--)
		{
			first = 0;
			
			for(j=1; j<=i; j++)
			{
				if (orders[j].getOrder() > orders[first].getOrder() )
				{
					first=j;
					
				
				}
			
				temp=orders[first];
				orders[first]=orders[i];
				orders[i]=temp;	
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
	public static int loadDataBase(PizzaRec[] orders, String[] badOrders, int[] counts)
	{

		/*************************************	*
		*	Default values for PizzaRec			*
		*	objects in orders array				*
		****************************************/
		String delimiters = "#";	
		String currentLine;
		counts[0] = 0;
		counts[1]=0;
		int lineNumber = 1;
		
		
		
		try
		{
			FileReader reader;
			
			try
			{	
				reader = new FileReader ( "PizzaDB.txt" );		
			}
			
			catch(Exception FileNotFound)
			{ 
				JOptionPane.showMessageDialog(null, "File Not Found");
				return -1;
			}
			
			BufferedReader in = new BufferedReader ( reader );
			Time tempTime = new Time();
			int hourTemp;
			int minTemp;
			boolean record = true;
			

			currentLine = in.readLine();
			if(currentLine==null)
			{
				record = false;
			}
			
			while(record)
			{
				StringTokenizer parser = new StringTokenizer(currentLine, delimiters);
				orders[counts[0]] = new PizzaRec();

				orders[counts[0]].setOrder(Integer.parseInt( parser.nextToken() ));
				orders[counts[0]].setName(parser.nextToken() );
				orders[counts[0]].setPizza(Integer.parseInt( parser.nextToken() ));
				
				hourTemp = Integer.parseInt( parser.nextToken() );
				minTemp = Integer.parseInt( parser.nextToken() );
				
				
				int validTime = Integer.parseInt(tempTime.setTime(hourTemp, minTemp));
				if(validTime!=0)
				{
					if(validTime == 1)
					{
						badOrders[counts[1]] = "ERROR: Line " + lineNumber + " has invalid order time. Hour out of range (0-23)";
						
						counts[1]++;
					}
					
					else
					{
						badOrders[counts[1]] = "ERROR: Line " +lineNumber + " has invalid order time. Minute out of range (0-59)";
						
						counts[1]++;
					}
				}
				
				else
				{
					orders[counts[0]].setTimeOrdered(tempTime);
					hourTemp = Integer.parseInt( parser.nextToken() );
					minTemp = Integer.parseInt( parser.nextToken() );
					validTime = Integer.parseInt(tempTime.setTime(hourTemp, minTemp));
					
					if(validTime!=0)
					{
						if(validTime == 1)
						{
							badOrders[counts[1]] = "ERROR: Line " +lineNumber + " has invalid delivery time. Hour out of range (0-23)";
							
							counts[1]++;
						}
						
						else
						{
							badOrders[counts[1]] = "ERROR: Line " +lineNumber + " has invalid delivery time. Minute out of range (0-59)";
							
							counts[1]++;
						}
					}
					
					else
					{
						orders[counts[0]].setTimeDelivered(tempTime);
						counts[0]++;
					}
					
					lineNumber++;
				}
				
				currentLine = in.readLine();
				if(currentLine==null)
				{
					record = false;					
				}
				
			}
			
			reader.close ( );
		}
			
		catch (Exception IO)
			{
				
			}
			
	
	return counts[0];
	}

	/********************************************************************************
	*			processTransactions method											*
	*																				*
	*			accepts array of PizzaRec objects and an int						*
	*			parameter size that signifies size of used							*
	*			objects in array													*
	********************************************************************************/
	public static void processTransactions(PizzaRec[] orders, String [] badArray, int [] counts)
	{
		String choice = JOptionPane.showInputDialog(null,
			"Please enter \n'L' to Look up an order number,\n\t" +
			"'S' to Show all orders, or\n\t'Q' to end");

		while ( !(choice.equalsIgnoreCase("Q") ) )
			{
				if (choice.equalsIgnoreCase("S") )
					{
						showAll( orders, badArray, counts);
					}
				else if (choice.equalsIgnoreCase("L") )
					{
						lookUpOrder ( orders, counts[0] );
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
	public static void showAll(PizzaRec[] orders, String [] badArray, int [] counts) 
	{
		

		/****************************************************************************************
		*	heading for showAll method							*
		****************************************************************************************/
		System.out.println ("\n\n\t\tException List\n");
		for(int h = 0; h<counts[1]; h++)
		{
			System.out.println (h+1 +"\t"+badArray[h]+"\n");
			
		}
		
		
		
		
		System.out.println("\n\n\tThe Godfather's Pizza\n" + "**************************************************************\n" + "\tOrder History\n\n");


		for (int i = 0; i < counts[0]; i++) 
		{			
			
			System.out.println(i+1 + "\n**************************************************************\n");
			System.out.print(orders[i].toString());

		}

		System.out.println("\n**************************************************************\n"+"\n                     End of Report");
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
		

		String inputValue = JOptionPane.showInputDialog ( null,
		"Enter the order you want to look up ('cancel' to skip)");
	
		if (inputValue != null)		// look for it
		{
			searchOrder = Integer.parseInt (inputValue);

			int position = binarySearch (size, orders, searchOrder);

			if (position >= 0)	// success
			{
				customer = orders[position].getName();
				
				JOptionPane.showMessageDialog(null, orders[position].toString());
			}
			else
			{
				
				JOptionPane.showMessageDialog(null,"Order " + inputValue +
					" is not listed");
				
			}
		}
	}




	/********************************************************
	*		binarySearch method				*
	*										*
	*		performs binary search			*
	********************************************************/
	public static int binarySearch (int size, PizzaRec[] orders, int searchOrder)
	{
		int start, mid, end; 	// indices for search space

		start = 0; 
		end = size-1; 
		mid = (start+end)/2;

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


