package implementation;

import java.util.ArrayList;

public class Library
{
	public Library()
	{
	}

	public int getBlocks(int startTime, int endTime){
		return getStartingIndex(endTime) - getStartingIndex(startTime);
	}


	public int getStartingIndex(int startTime)
	{
		return((startTime / 100) * 6) + ((startTime % 100) / 10);
	}

    public boolean[][] chopper(ArrayList<ArrayList<String>> input){// 8:00-10:00 14:00-15:00 12:00-13:00
		int startTime;
		int endTime;
		int dayNum;
		int startingIndex;
		String fieldString = "";
		char day = 'Y';
		boolean[][] WeeklySchedule = new boolean[7][144];
		ArrayList <ArrayList <Integer>> allTimes = new ArrayList <ArrayList <Integer>>();

		for(int i = 0; i < 7; i++)
		{
			allTimes.add(new ArrayList <Integer>());
			for(int j = 0; j < 144; j++)
			{
				WeeklySchedule[i][j] = false;
			}
		}

		for(int j = 0;j < input.size();j++){

			for(int n = 0;n < input.get(j).size();n++){

				for(int i = 0;i <= input.get(j).get(n).length();i++)
				{
					if(n == 0){
						if(i == 0){

							day = input.get(j).get(n).charAt(i);
							i++;
						}
					}
					else{
						if(input.get(j).get(n).length() == i || input.get(j).get(n).charAt(i) == '-' || input.get(j).get(n).charAt(i) == ','){
							
							if(day == 'U'){
								dayNum = 0;
							}else if(day == 'M'){
								dayNum = 1;
							}else if(day == 'T'){
								dayNum = 2;
							}else if(day == 'W'){
								dayNum = 3;
							}else if(day == 'R'){
								dayNum = 4;
							}else if(day == 'F'){
								dayNum = 5;
							}else if(day == 'S'){
								dayNum = 6;
							}else{
								System.out.println("ERROR IN DAYNUM CALCULATION IN CHOPPER. BAD DAY CHARACTER. DEFAULTING TO SUNDAY. REPORT THIS TO TROY. FAULTY CHARACTER: "+day);
								dayNum = 0;
							}
							//System.out.println("ADDING: "+fieldString+" "+n+" "+i+" "+input.get(j).get(n).length());
							allTimes.get(dayNum).add(Integer.parseInt(fieldString));
							fieldString = "";
						}else if(input.get(j).get(n).charAt(i) != ':'){

							fieldString+=input.get(j).get(n).charAt(i);
						}
					}
				}
			}
		}

		for(int j = 0; j < allTimes.size(); j++){

			for(int k = 0; k < allTimes.get(j).size(); k += 2)
			{
				startTime = allTimes.get(j).get(k);
				endTime = allTimes.get(j).get(k+1);

				int blockCounter = getBlocks(startTime,endTime);

				startingIndex = getStartingIndex(startTime); // Total blocks to count from Midnight

				//System.out.println("New startTime: "+startTime+" New endTime: "+endTime+" New blockCounter: "+blockCounter+" New StartingIndex: "+startingIndex);
				for(int l = 0; l < blockCounter; l++){
					WeeklySchedule[j][(startingIndex+l)] = true;
				}
			}
		}
		return WeeklySchedule;
	}

	// public ArrayList<ArrayList<ArrayList<String>>> convert(String[][] nameSchedule)
	// {
	// 	ArrayList<ArrayList<ArrayList<String>>> returnValue = new ArrayList<ArrayList<ArrayList<String>>>();
	// 	int[] timeSlot = {0, 0};
	// 	String uniqueGroup = nameSchedule[timeSlot[0]][timeSlot[1]];
	// 	for (int i = 0; i < nameSchedule.length; i++)
	// 	{
	// 		for (int j = 0; j < nameSchedule[i].length; j++)
	// 		{
	// 			if (nameSchedule [timeSlot[0]][timeSlot[1]] != uniqueGroup)
	// 			{
	// 				uniqueGroup = nameSchedule[i][j];
	// 				returnValue.get()
	// 				timeSlot[0] = timeSlot[1];
	// 			}
	// 			else
	// 			{
	// 				timeSlot[1] = j;
	// 			}
	// 		}
	// 	}
	// 	return returnValue;
	// }

	public String toProperNumber(String num){
		if(num.length() >= 3){
			num = num.substring(0, num.length()-2) + ":" + num.substring(num.length()-2,num.length());
		}else if(num.length() == 2){
			num = "0:"+num;
		}else{
			num = "0:0"+num;
		}
		return num;
	}

	public ArrayList<ArrayList<ArrayList<String>>> convert(String[][] input)
	{
		//this.getTeamSize();
		String currentField; // initialize at first value
		int[] timeSlot = {0, 0};
		int sixtyCount;
		ArrayList<ArrayList<ArrayList<String>>> output = new ArrayList<ArrayList<ArrayList<String>>>();

		for(int j = 0;j < 2;j++){

			output.add(new ArrayList<ArrayList<String>>());
			for(int i = 0;i < 7;i++){

				output.get(j).add(new ArrayList<String>());
			}
		}

		for(int i = 0;i < 7;i++){

			currentField = input[i][0];//initialize the field
			timeSlot[0] = 0;//index 0
			for(int j = 0;j < 144;j++){
				
				if(j == 143 || !currentField.equals(input[i][j])){
					
					//System.out.println("|"+currentField + "|" + input[i][j]+"|");
					//save raw string at current index to second dimension
					output.get(1).get(i).add(input[i][timeSlot[0]]);

					
					//convert into times
					timeSlot[1] = j*10;
					timeSlot[0]*=10;
					
					for(int n = 0;n < 2;n++){
						
						sixtyCount = 0;
						while(timeSlot[n] >= 60){
							timeSlot[n]-=60;
							sixtyCount++;
						}
						timeSlot[n]+=100*sixtyCount;
					}

					
					//save difference of times to second dimension
					int hours = 10*getBlocks(timeSlot[0],timeSlot[1]);
					
					sixtyCount = 0;

					while(hours >= 60){
						hours-=60;
						sixtyCount++;
					}
					if(hours >= 30){ // round up for 
						sixtyCount++;
					}

					//System.out.println(timeSlot[0] + "-" + timeSlot[1] + " = " + sixtyCount + ":" + hours);

					output.get(1).get(i).add(Integer.toString(sixtyCount));

					
					//write time string to first dimension
					output.get(0).get(i).add(toProperNumber(Integer.toString(timeSlot[0]))+"-"+toProperNumber(Integer.toString(timeSlot[1])));
					
					
					//set values for next comparison
					timeSlot[0] = j;
					currentField = input[i][j];

					//System.out.println(i + "   |   " + output.get(0).get(i).get(output.get(0).get(i).size()-1) + "  |  " + output.get(1).get(i).get(output.get(1).get(i).size()-2) + "  |  " + output.get(1).get(i).get(output.get(1).get(i).size()-1));
				}
			}
		}

		return output;
	}
}