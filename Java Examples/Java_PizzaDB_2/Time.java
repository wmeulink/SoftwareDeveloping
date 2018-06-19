/*
Time Class
Created by: Whitney Meulink
Created on: 02/23/2015

*/

public class Time {

private int hour;
private int minute;


public Time()
{
	
hour = 0;
minute = 0;

}




/*
The following method is a setTime mutator Method. 
This method returns the hours and minutes in a String.
Has parameters that have been sent from TestOfTime for mutation.
This method also makes sure that the data that is being entered in TestOfTime
are valid. 
*/

public String setTime(int hr, int min)
{
	String goodValue = "0";
	
	if ((hr < 24 && hr >= 0) && (min < 60 && min >= 0) )
			{
				this.hour = hr;
				this.minute = min;
			}
		
		else if (hr >= 24 || hr < 0)
			{
				goodValue = "1";
			}
	
		else 
			{
				goodValue = "2";
			}
			
	return goodValue;
}

/*
The following method is getHour access Method. 
This method returns the hours and carries 0 parameters. 
*/

public int getHour()
{
return this.hour;
}


/*
The following method is getMinute access Method. 
This method returns the minutes and carries 0 parameters. 
*/

public int getMinute()
{
return this.minute;
}

/*
The following method is ampmTime formatting method. 
This method returns a String that represents the hours and minutes in 
ampmTime. This carries 0 parameters. 
*/
public String ampmTime()
{
	String theTime = null;
	if ( hour == 12 && minute == 0 )
	{
		theTime = " Noon";
	}
	else if (hour == 0 && minute == 0 )
	{
		theTime = "Midnight";
	}
	else if ( hour < 12 )
	{
			
		if ( minute < 10 )
		{
			if(hour ==0)
			{
				theTime = " " + "12" + ":" + "0" + minute + " AM";
				
			}
			else if (hour<12 && minute <10)
			{
				theTime = " " + hour + ":" + "0" + minute + " AM";
			}
			
		
			else
			{
				theTime = " " + hour + ":" + minute + " AM";
			}
		}	
	
		
		else if ( minute > 10)
		{
			if(hour == 0)
			{
				theTime = " " + "12" + ":" + minute + " AM";
			}
			
			else
			{
				
				theTime = " " + hour + ":" +  minute + " AM";
			}
		}
	}
	else if ( hour >= 12 )
	{
		if ( minute < 10 )
		{
			if(hour ==12)
			{
				theTime = " " + "12" + ":" + "0" + minute + " PM";
			}
			else
			{
			theTime = " " + (hour - 12) + ":" + "0" + minute + " PM";
			}
		}
		else
		{
			if(hour == 12)
			{
				theTime = " " + "12" + ":" + minute + " PM";
			
			}
			else
			{
			theTime = " " + (hour - 12) + ":" + minute + " PM";
			}
		}
	
	
	}
	
return theTime;
}

/*
The following method is milTime formatting method. 
This method returns a String that represents the hours and minutes in 
military time. This carries 0 parameters. 
*/

public String milTime ()
{
	String milTime = null;
	if ( hour < 10 && minute < 10 )
	{
		milTime = " 0" + hour + "0" + minute + " hours";
	}
	else if ( hour < 10 && minute >= 10 )
	{
		milTime = " 0" + hour + minute + " hours";
	}
	else if ( hour > 9 && minute < 10 )
	{
		milTime = " " + hour + "0" + minute + " hours";
	}
	else
	{
		milTime = " " + hour + "" + minute + " hours";
	}
	return milTime;

}
/*
The following method is the increment method. 
This method adds one to the hour and one to the minute.  
*/

public void increment()
{

minute++;


if (hour >=23 && minute > 59)
	{
	hour = 0;
	minute = 0;
	}
else if(minute > 59)
	{
		minute = 0; 
		hour++;
	}


}

/*
The following method is the difference method. 
This method carries one parameter that is entered for comparison.
This and that have both get converted to minutes to easily compute 
the difference between the two. This returns a Time object that carries 
the difference amount. 
*/


public String difference(Time that)
{


		int thatMinuteHour= (60*that.getHour()) +that.getMinute();
		

		int thisMinuteHour = (60*this.hour) + minute;
		int differenceMinuteHour = thatMinuteHour - thisMinuteHour;

	if(differenceMinuteHour<0)
		{
			differenceMinuteHour= differenceMinuteHour + 1440;
			
		}
	/*
	int differenceMinute = differenceMinuteHour % 60;
	int differenceHour = differenceMinuteHour / 60;

	Time differenceTime = new Time(differenceHour, differenceMinute);

	return differenceTime;
	*/
	
	
	String theDifference = "Hour: " +differenceMinuteHour/60 +" Minute: " +differenceMinuteHour%60;
	return theDifference;
	
}


/*
The following method is the later method. 
This carries a Time object that is entered after the "this"
time has been entered. The two variables get compared. If this is greater
than that, the boolean variable gets returned "true", otherwise, it is false. 
*/



public boolean later(Time that)
{
	int thisTime = this.minute + (this.minute*60);
	int thatTime = that.getMinute() + (that.getHour()*60);
	boolean test = false;
	
	if(thisTime > thatTime)
	{
	test = true;
	}
	
return test;	
}

/*
The following method is the equals method. 
This compares the this.hour versus the that.hour and 
sends back a boolean true/false whether or not those two
variables equal each other. 

*/

public boolean equals (Time that)
{
	boolean test = false;
	
	if(this.minute == that.minute && this.hour == that.hour)
	{
	
	test = true;
	}
	
return test;
}

/*
The following method is the copy method. 
This takes a copy of the hour and minute and sets it to a different location... "copyTime".
*/

public Time copy()
{
	Time copyTime = new Time();
	
	return copyTime;

}



} // end of class
