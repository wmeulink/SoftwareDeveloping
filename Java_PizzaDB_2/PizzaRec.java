public class PizzaRec
{


private int orderNum;
private String customerName;
private int pizza;
private Time timeOrdered;
private Time timeDelivered;

	public PizzaRec()
	{
		this.orderNum = 0;
		this.customerName = "Doe";
		this.pizza = 0;
		this.timeOrdered = new Time();
		this.timeDelivered = new Time();
	}
	
	public PizzaRec (int val1, String val2, int val3)
	{
		this.orderNum = val1;
		this.customerName = val2;
		this.pizza = val3;
		this.timeOrdered = new Time();
		this.timeDelivered = new Time();
		
	}

	public void setOrder(int val1) //accessors
	{
		this.orderNum = val1;
	}
	
	public void setName(String val2)
	{
		this.customerName = val2;
	}
	
	public void setPizza (int val3) 
	{
		this.pizza = val3;
	}

	public void setTimeOrdered(Time t)
	{
		this.timeOrdered.setTime(t.getHour(), t.getMinute());
	}

	public void setTimeDelivered(Time t)
	{
		this.timeDelivered.setTime(t.getHour(), t.getMinute());
	}
	
	public int getOrder() //mutators
	{
		return this.orderNum;
	
	}
	public String getName()
	{
		return this.customerName;
	}

	public int getPizza()
	{
		return this.pizza;
	}
	
	public Time getOrderTime()
	{
		return this.timeOrdered;
	}
	
	public Time getDeliverTime ()
	{
		return this.timeDelivered;
	}
	
	public String toString()
	{
		
		String [] pizzaString = {"Cheese","Pepperoni", "Sausage and Pepperoni", "Marlon's Special", "Custom"};
		
		String returnString = "\tOrder: "+this.getOrder() +"\n\tCustomer: " +this.getName() +"\n\tPizza: " 
		+pizzaString[this.getPizza()] + "\n\tOrdered:" +getOrderTime().ampmTime() +"\n\tDelivered: " 
		+ getDeliverTime().ampmTime() + "\n\tDelivery Time: " + timeOrdered.difference(timeDelivered) +"\n";
		
		return returnString;
	}
}
