package implementation;

import java.util.ArrayList;

import javax.lang.model.util.ElementScanner6;

import implementation.Student;
import implementation.ProgramIO;

import java.util.PriorityQueue;
import java.util.Comparator;

public class Team
{
	private ArrayList<Student> teamList;
	private String teamName;
	private int teamSize;
	private int[][] constraints;
	private PriorityQueue<ArrayList<String>> pointPQ;

	public Team ()
	{
		this.setTeamName("Empty Team");
		this.setTeamSize(0);
		teamList = new ArrayList<Student>();
		constraints = new int [7][2];
		pointPQ = new PriorityQueue<ArrayList<String>>(3, PointComparator);
		for(int i = 0;i < 7;i++){
			constraints[i][0] = 800;
			constraints[i][1] = 1800;
		}
	}
	public Team (ArrayList<Student> newStudents)
	{
		this.setStudents(newStudents);
		this.setTeamName("");
		this.setTeamSize(this.getStudents().size());
		constraints = new int [7][2];
		pointPQ = new PriorityQueue<ArrayList<String>>(3, PointComparator);
		for(int i = 0;i < 7;i++){
			constraints[i][0] = 800;
			constraints[i][1] = 1800;
		}
	}
	public Team (ArrayList<Student> newStudents, String newTeamName)
	{
		this.setStudents(newStudents);
		this.setTeamName(newTeamName);
		this.setTeamSize(this.getStudents().size());
		constraints = new int [7][2];
		pointPQ = new PriorityQueue<ArrayList<String>>(3, PointComparator);
		for(int i = 0;i < 7;i++){
			constraints[i][0] = 800;
			constraints[i][1] = 1800;
		}
	}
	public Team (String newTeamName)
	{
		this.setTeamName (newTeamName);
		this.setTeamSize (0);
		teamList = new ArrayList<Student>();
		constraints = new int [7][2];
		pointPQ = new PriorityQueue<ArrayList<String>>(3, PointComparator);
		for(int i = 0;i < 7;i++){
			constraints[i][0] = 800;
			constraints[i][1] = 1800;
		}
	}

	public int getTeamSize ()
	{
		return teamSize;
	}
	public String getTeamName ()
	{
		return teamName;
	}

	public ArrayList<Student> getStudents ()
	{
		return teamList;
	}

	public String getStudentNames()
	{
		String temp = "";
		for(int i = 0; i < teamSize; i++)
		{
			if(i != teamSize - 1){
				temp = temp + teamList.get(i).getFirstName() + ",";
			}
			else{
				temp = temp + teamList.get(i).getFirstName();
			}
			
		}
		return temp;
	}
	public ArrayList<ArrayList<String>> getFirst3Values() {
		ArrayList<ArrayList<String>> returnValue = new ArrayList<ArrayList<String>>();
		String day;

		for(int i = 0; i < 3; i++){
			returnValue.add(new ArrayList<String>());
			ArrayList<String> boi = new ArrayList<String>();
			boi = pointPQ.peek();
			if (boi != null && boi.size() >= 3){
				for(int j = 0; j < boi.size(); j++){
					if(j == 2 && boi.get(j).equals("")){
						returnValue.get(i).add("Everyone can make it!");
					}else if(j == 5){
						day = "ERROR";
						if(boi.get(j).equals("0")){
							day = "Sunday";
						}else if(boi.get(j).equals("1")){
							day = "Monday";
						}else if(boi.get(j).equals("2")){
							day = "Tuesday";
						}else if(boi.get(j).equals("3")){
							day = "Wednesday";
						}else if(boi.get(j).equals("4")){
							day = "Thursday";
						}else if(boi.get(j).equals("5")){
							day = "Friday";
						}else if(boi.get(j).equals("6")){
							day = "Saturday";
						}
						returnValue.get(i).add(day);
					}else{
						returnValue.get(i).add(boi.get(j));
					}
				}
			}
			pointPQ.remove(boi);
		}
		return returnValue;
	}
	public int[][] getConstraint ()
	{
		return constraints;
	}

	public void setConstraint (String input)
	{
		String parsel = "";
		char day = ' ';
		String start = "";
		int index = 0;

		for(int i = 0;i < input.length();i++)
		{
			if(i == 0){
				day = input.charAt(i);
			}else if(i > 1){
				if(input.charAt(i) == '-'){
					start = parsel;
					parsel = "";
				}else{
					parsel+=input.charAt(i);
				}
			}
		}// at this point, parsel is the end string
		if (String.valueOf(day) != ""){
			if(day == 'U'){index = 0;}
			else if(day == 'M'){index = 1;}
			else if(day == 'T'){index = 2;}
			else if(day == 'W'){index = 3;}
			else if(day == 'R'){index = 4;}
			else if(day == 'F'){index = 5;}
			else if(day == 'S'){index = 6;}
		}
		if(Integer.parseInt(start) < 2358 && Integer.parseInt(parsel) < 2359){
			constraints[index][0] = Integer.parseInt(start);
			constraints[index][1] = Integer.parseInt(parsel);//parsel is end here
		}
		else{
			//Tell the user they are bad
		}
		
	}
	public void setTeamSize (int newTeamSize)
	{
		teamSize = newTeamSize;
	}
	public void setTeamName (String newTeamName)
	{
		teamName = newTeamName;
	}
	public void setStudents (ArrayList<Student> newTeam)
	{
		teamList = newTeam;
	}

	public void addStudent (Student newStudent)
	{
		this.getStudents().add(newStudent);
		this.setTeamSize(this.getTeamSize()+1);
	}
	public void removeStudent (String bannerID)
	{
		for (int i = 0; i < this.getTeamSize(); i++)
		{
			if(this.getStudents().get(i).getId().equals(bannerID)){
				this.getStudents().remove(i);
				this.setTeamSize(getTeamSize()-1);
			}
		}
	}
	public String toString ()
	{
		String output = "";
		for (int i = 0; i < this.getTeamSize(); i++)
		{
			output += this.getStudents().get(i).toString();
			if (i + 1 <= this.getTeamSize ())
			{
				output += "\n";
			}
		}
		return output;
	}

	public static Comparator<ArrayList<String>> PointComparator = new Comparator<ArrayList<String>>(){
             
		// Overriding compare()method of Comparator 
					// for descending order of cgpa
		@Override
		public int compare(ArrayList<String> s1, ArrayList<String> s2) {
			return Integer.parseInt(s2.get(3)) - Integer.parseInt(s1.get(3));
			}
	};

	public int getBlocks(int startTime, int endTime){
		return getStartingIndex(endTime) - getStartingIndex(startTime);
	}
	
	public int convertHours(int timeToConvert){
		int hourCounter = 0;
		while(timeToConvert >= 60){
			hourCounter++;
			timeToConvert -= 60;
		}
		return (hourCounter*100 + timeToConvert);
	}

	public int getStartingIndex(int startTime)
	{
		return((startTime / 100) * 6) + ((startTime % 100) / 10);
	}
	//Split team times into 10 minute intervals
	public String[][] compareTimes(String termCode)
	{
		int startTime;
		int endTime;
		ArrayList <Integer> allTimes = new ArrayList <Integer>();
		ArrayList <String> DaysOfWeek = new ArrayList <String>();
		DaysOfWeek.add("U");
		DaysOfWeek.add("M");
		DaysOfWeek.add("T");
		DaysOfWeek.add("W");
		DaysOfWeek.add("R");
		DaysOfWeek.add("F");
		DaysOfWeek.add("S");
		String[][] WeeklyTeamSchedule = new String[7][144];
		int startingIndex;

		for(int i = 0; i < 7; i++)
		{
			for(int j = 0; j < 144; j++)
			{
				WeeklyTeamSchedule[i][j] = "";
			}
		}

		for(int i = 0; i < this.getTeamSize(); i++){

			for(int j = 0; j < 7; j++){

				allTimes = teamList.get(i).getSchedule(DaysOfWeek.get(j), Integer.parseInt(termCode));
				

				for(int k = 0; k < allTimes.size(); k += 2)
				{
					startTime = allTimes.get(k);
					endTime = allTimes.get(k+1);

					int blockCounter = getBlocks(startTime,endTime);

					startingIndex = getStartingIndex(startTime); // Total blocks to count from Midnight

					for(int l = 0; l < blockCounter; l++){
						if(WeeklyTeamSchedule[j][(startingIndex+l)].equals("")){
							WeeklyTeamSchedule[j][(startingIndex+l)] += teamList.get(i).getFirstName(); //If index is empty just add name
						}
						else{
							WeeklyTeamSchedule[j][(startingIndex+l)] += ("," + teamList.get(i).getFirstName()); //else add a comma before the name
						}
					}
				}
			}
		}
		return WeeklyTeamSchedule;
	}

	public void TeamCanMeet(String[][] WeeklyTeamSchedule){
		boolean toggle = true;
		int startTime = 0;
		int endTime = 0;
		ArrayList <String> DaysOfWeek = new ArrayList <String>();
		DaysOfWeek.add("U");
		DaysOfWeek.add("M");
		DaysOfWeek.add("T");
		DaysOfWeek.add("W");
		DaysOfWeek.add("R");
		DaysOfWeek.add("F");
		DaysOfWeek.add("S");

		for(int i = 0; i < 7; i++)
		{
			ProgramIO io = new ProgramIO();
			int startTimeConstraint = getStartingIndex(constraints[i][0]);
			int endTimeConstraint = constraints[i][1];
			toggle = true;
			for(int j = startTimeConstraint; j < startTimeConstraint + getBlocks(constraints[i][0], endTimeConstraint); j++)
			{
				if(WeeklyTeamSchedule[i][j].equals("") && toggle){
					toggle = false;
					startTime = (j*10);
				}
				else if(!WeeklyTeamSchedule[i][j].equals("") && !toggle){
					toggle = true;
					endTime = (j-1)*10;
					if(startTime != endTime) {
						startTime = convertHours(startTime);
						endTime  = convertHours(endTime);
						//io.output(DaysOfWeek.get(i) + "    Students can meet at: " + startTime + " until: " + endTime, true);
					}
				}
			}
			if(!toggle && (convertHours(startTime) != endTimeConstraint)){
				startTime = convertHours(startTime);
				//io.output(DaysOfWeek.get(i) + "    Students can meet at: " + startTime + " until: " + endTimeConstraint, false);
			}
			//System.out.println ("\n");
		}
	}

	public String[][] compare(String[][] teamTimes, boolean[][] canMeet, String teamNames)
	{
		
		String[][] teamSchedule1 = new String[7][144];
		for(int i = 0; i < 7; i++) {
			for(int q = 0; q < 144; q++) {
				teamSchedule1[i][q] = teamTimes[i][q];
			}
		} 
		for(int j = 0; j < 7; j++)
		{
			int startTimeConstraint = getStartingIndex(constraints[j][0]);
			int endTimeConstraint = constraints[j][1];
			//System.out.println("\n\n" + j + "\n");
			for(int k = 0; k < 144; k++)
			{
				if(canMeet[j][k] == true || k < startTimeConstraint || k >= startTimeConstraint + getBlocks(constraints[j][0], endTimeConstraint)){ // if true
					teamSchedule1[j][k] = teamNames;
				}	
				if(k % 6 == 0){
					//System.out.print("\n");
				}
				if(teamSchedule1[j][k] == teamNames){
					//System.out.print("ALL" + "  ");
				}	
				else if(teamSchedule1[j][k] != ""){
					//System.out.print(teamSchedule1[j][k] + "  ");
				}
				else {
					//System.out.print("N" + " ");
				}
				
			}
			//System.out.print("\n\n\n");
		}

		return teamSchedule1;
	}

	

	public void pointCalculator(ArrayList<ArrayList<ArrayList<String>>> convertResult, String Building, String Name) {
		
		for(int i = 0; i < convertResult.get(0).size(); i++){
			for(int j = 0; j < convertResult.get(0).get(i).size(); j++){
				ArrayList<String> BuildingNamePeopleTimePoints = new ArrayList<String>();
				
				//System.out.print(convertResult.get(0).get(i).get(j) + "   ");
				//System.out.print(convertResult.get(1).get(i).get(j*2) + "   ");
				int commaCounter = 0;
				for(int k = 0; k < convertResult.get(1).get(i).get(j*2).length(); k++){
					if(convertResult.get(1).get(i).get(j*2).charAt(k) == ',')
					commaCounter++;
				}
				if(convertResult.get(1).get(i).get(j*2).equals("")){
					//System.out.print(commaCounter + "  TEST  ");
				}
				else{
					//System.out.print((commaCounter + 1) + "    ");
					commaCounter++;
				}
				int peopleThatCanAttend = getTeamSize() - commaCounter;
				int blockCounter = Integer.parseInt(convertResult.get(1).get(i).get(j*2+1));
				int pointValue = (((peopleThatCanAttend/teamSize)*4) + blockCounter);
				if(peopleThatCanAttend > 0) {
					BuildingNamePeopleTimePoints.add(Building);//buildingName
					BuildingNamePeopleTimePoints.add(Name);//buildingNum
					BuildingNamePeopleTimePoints.add(convertResult.get(1).get(i).get(j*2));//people
					BuildingNamePeopleTimePoints.add(Integer.toString(pointValue));//points
					BuildingNamePeopleTimePoints.add(convertResult.get(0).get(i).get(j));//times
					BuildingNamePeopleTimePoints.add(Integer.toString(i));//day
					pointPQ.add(BuildingNamePeopleTimePoints);
					//System.out.print("People who can" + peopleThatCanAttend + "   ");
					//System.out.print("Team Size" + teamSize + "    ");
					//System.out.print(blockCounter + "    ");
					//System.out.println(pointValue);
				}
				else {
					//System.out.println("");
				}
				
			}
		}
	}
}
