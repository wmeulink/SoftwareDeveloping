output = "\n\n\t\t\tName\t\tTime (ms)\t\tSpeed (mph)\n"
fastestPitcher="Nobody"
slowestPitcher="Nobody"
fastestSpeed = 0
slowestSpeed = 10000
fastestTime = 0
slowestTime = 0
pitcherName=input("Enter the name of the next contestant, or nothing to exit: ")


if pitcherName != "":
	fastestPitcher = pitcherName
	slowestPitcher = pitcherName
        
	while pitcherName!="":
    
		pitcherTime=float(input("Enter the time for " +str(pitcherName) +" in milliseconds: "))

                #60 feet is distance from pitcher mount to home plate    
		pitcherSpeed=float(round((40908/pitcherTime),2))
		pitcherTime = round(pitcherTime,2)

		if pitcherSpeed > fastestSpeed:

			fastestPitcher = pitcherName
			fastestSpeed=pitcherSpeed
			fastestTime = pitcherTime
                        
		if pitcherSpeed < slowestSpeed:

			slowestSpeed = pitcherSpeed
			slowestPitcher = pitcherName
			slowestTime = pitcherTime
		output=(output +"\t\t\t" +str(pitcherName)+ "\t\t" +str(pitcherTime) + "\t\t\t" +str(pitcherSpeed) +"\n")
		pitcherName=input("Enter the name of the next contestant, or nothing to exit: ")




if slowestSpeed == 10000:
        slowestSpeed = fastestSpeed
        slowestTime = fastestTime
        
print(output)
print("Slowest Pitcher:\t" +str(slowestPitcher) +"\t\t" +str(slowestTime) +"\t\t\t" +str(slowestSpeed))
print("Fastest Pitcher:\t" +str(fastestPitcher) +"\t\t" +str(fastestTime) +"\t\t\t" +str(fastestSpeed))