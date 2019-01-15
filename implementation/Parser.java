package implementation;

import implementation.DatabaseInputTemplate;
import implementation.Database;
import implementation.ProgramIO;
import java.io.*;
import java.util.ArrayList;

public class Parser
{
	//public String newestTerm = "";
	private int previousProgress = 0;

	private void printLoadingBar(int prog, int highIndex)
	{
		String loadingBarHighlight = "▓▒▒▒░░▒▒▒▓";
		int loadingProgress = prog;
		int highlightIndex = highIndex;

		// delete old loadingBar
		for (int i = 0; i < previousProgress + 3; i++)
		{
			System.out.print('\b');
		}
		if (previousProgress >= 5)
		{
			System.out.print('\b');
		}
		previousProgress = loadingProgress;

		// print loading bar
		for (int i = 0; i < loadingProgress; i++)
		{
			// if printing index within highlight index range
			if (i >= highlightIndex && i <= highlightIndex + 9)
			{
					System.out.print(loadingBarHighlight.charAt(i - highlightIndex));
			}
			else
			{
				System.out.print('█');
			}
		}
		System.out.print(" " + loadingProgress * 2 + "%");
	}

    public void parseCSV() {
		Database db = new Database ();
		Settings config = new Settings();

	    String fileName = config.readCSV();
	    String row = null;
	    String fieldString = "";
	    int rowNum = 0;
	    int quotationCheck = 0;
	    int fieldNum = 0;
		int collumMax = 26;
		Boolean calcLength = true;
		int CSVLength = 0;
		int loadingProgress = 0;
		int highlightIndex = 0;
		int barCounter = 0;
	    ArrayList<Integer> collumSet = new ArrayList<Integer>(collumMax);
      for (int count = 0; count < collumMax; count++)
        collumSet.add(0);

	    int fieldsDone = 0;
	    int highestCollum = -1;
	    int neededFields = 0;

	    String courseNum;
	    String sectionNum;
	    String mon;
	    String tue;
	    String wed;
	    String thu;
	    String fri;
	    String sat;
	    String sun;

	    DatabaseInputTemplate rowRegister = new DatabaseInputTemplate ();
	    ProgramIO io = new ProgramIO ();

	    try {
	        FileReader fileReader = new FileReader(fileName);
	        BufferedReader bufferedReader = new BufferedReader(fileReader);

			System.out.println ("╔══════════════════════════════════════════════════╗");
			System.out.print ("║ 0%");


	        while((row = bufferedReader.readLine()) != null) {
	        	rowNum++;
	        	quotationCheck = 0;
	        	fieldNum = 0;
				courseNum = "";
			    sectionNum = "";
			    mon = "";
			    tue = "";
			    wed = "";
			    thu = "";
			    fri = "";
			    sat = "";
			    sun = "";

				if(rowNum == 1)// parse legend
				{
	        		for(int i = 0;i < row.length() && highestCollum == -1;i++){

	        			if(row.charAt(i) == ','){
	        				fieldNum+=1;
	        				fieldsDone++;

	        				if(fieldString.equals("Banner ID")){
	        					collumSet.set(0, fieldNum);
	        				}else if(fieldString.equals("First Name")){
	        					collumSet.set(1, fieldNum);
	        				}else if(fieldString.equals("Last Name")){
	        					collumSet.set(2, fieldNum);
	        				}else if(fieldString.equals("Middle Name")){
	        					collumSet.set(3, fieldNum);
	        				}else if(fieldString.equals("Subject Code")){
	        					collumSet.set(4, fieldNum);
	        				}else if(fieldString.equals("Course Number")){
	        					collumSet.set(5, fieldNum);
	        				}else if(fieldString.equals("Section Number")){
	        					collumSet.set(6, fieldNum);
	        				}else if(fieldString.equals("Course Title")){
	        					collumSet.set(7, fieldNum);
	        				}else if(fieldString.equals("Grade Code")){
	        					collumSet.set(8, fieldNum);
	        				}else if(fieldString.equals("Class Code")){
	        					collumSet.set(9, fieldNum);
	        				}else if(fieldString.equals("Term Code")){
	        					collumSet.set(10, fieldNum);
	        				}else if(fieldString.equals("Enrolled Ind")){
	        					collumSet.set(11, fieldNum);
	        				}else if(fieldString.equals("Preferred First Name")){
	        					collumSet.set(12, fieldNum);
	        				}else if(fieldString.equals("ACU Email Address")){
	        					collumSet.set(13, fieldNum);
	        				}else if(fieldString.equals("Begin Time 1")){
	        					collumSet.set(14, fieldNum);
	        				}else if(fieldString.equals("End Time1")){
	        					collumSet.set(15, fieldNum);
	        				}else if(fieldString.equals("Bldg Code1")){
	        					collumSet.set(16, fieldNum);
	        				}else if(fieldString.equals("Room Code1")){
	        					collumSet.set(17, fieldNum);
	        				}else if(fieldString.equals("Monday Ind1")){
	        					collumSet.set(18, fieldNum);
	        				}else if(fieldString.equals("Tuesday Ind1")){
	        					collumSet.set(19, fieldNum);
	        				}else if(fieldString.equals("Wednesday Ind1")){
	        					collumSet.set(20, fieldNum);
	        				}else if(fieldString.equals("Thursday Ind1")){
	        					collumSet.set(21, fieldNum);
	        				}else if(fieldString.equals("Friday Ind1")){
	        					collumSet.set(22, fieldNum);
	        				}else if(fieldString.equals("Saturday Ind1")){
	        					collumSet.set(23, fieldNum);
	        				}else if(fieldString.equals("Sunday Ind1")){
	        					collumSet.set(24, fieldNum);
	        				}else if(fieldString.equals("Section Max Enrollment")){
	        					collumSet.set(25, fieldNum);
	        				}else{
	        					fieldsDone--; // AKA don't increment
	        				}


	        				if(fieldsDone == collumMax){
	        					highestCollum = fieldNum;
	        				}

        					fieldString = "";

	        			}else{
	        				fieldString+=row.charAt(i);
	        			}
        			}

	        	}
	        	else
	        	{ // ignore first row, which is legend

	        		for(int i = 0;i < row.length() && fieldNum < highestCollum;i++){

	        			if(row.charAt(i) == '"'){
	        				quotationCheck+=1;
	        			}
	        			if(row.charAt(i) == ',' && quotationCheck % 2 == 0){
	        				fieldNum+=1;

	        				if(fieldNum == collumSet.get(0)){
	        					rowRegister.setId(fieldString);
	        				}else if(fieldNum == collumSet.get(1)){
	        					rowRegister.setFirstName(fieldString);
	        				}else if(fieldNum == collumSet.get(2)){
	        					rowRegister.setLastName(fieldString);
	        				}else if(fieldNum == collumSet.get(3)){
	        					rowRegister.setMiddleName(fieldString);
	        				}else if(fieldNum == collumSet.get(4)){
	        					courseNum = fieldString;
	        				}else if(fieldNum == collumSet.get(5)){
	        					sectionNum = fieldString;
	        				}else if(fieldNum == collumSet.get(6)){
	        					rowRegister.setCourseSection(fieldString);
	        				}else if(fieldNum == collumSet.get(7)){
	        					rowRegister.setCourseTitle(fieldString);
	        				}else if(fieldNum == collumSet.get(8)){
	        					if(fieldString == ""){
	        						fieldString = "A";
	        					}
	        					rowRegister.setCourseGrade(fieldString);
	        				}else if(fieldNum == collumSet.get(9)){
	        					rowRegister.setClassCode(fieldString);
	        				}else if(fieldNum == collumSet.get(10)){
	        					rowRegister.setCourseTerm(fieldString);
	        				}else if(fieldNum == collumSet.get(11)){
	        					rowRegister.setEnrolled(fieldString);
	        				}else if(fieldNum == collumSet.get(12)){
	        					rowRegister.setPreferredName(fieldString);
	        				}else if(fieldNum == collumSet.get(13)){
	        					rowRegister.setEmail(fieldString);
	        				}else if(fieldNum == collumSet.get(14) && !fieldString.equals("")){
								rowRegister.setCourseTimeBegin(Integer.parseInt(fieldString));
							}else if(fieldNum == collumSet.get(15) && !fieldString.equals("")){
	        					rowRegister.setCourseTimeEnd(Integer.parseInt(fieldString));
	        				}else if(fieldNum == collumSet.get(16)){
	        					rowRegister.setBuilding(fieldString);
	        				}else if(fieldNum == collumSet.get(17)){
	        					rowRegister.setCourseRoom(fieldString);
	        				}else if(fieldNum == collumSet.get(18)){
	        					mon = fieldString;
	        				}else if(fieldNum == collumSet.get(19)){
	        					tue = fieldString;
	        				}else if(fieldNum == collumSet.get(20)){
	        					wed = fieldString;
	        				}else if(fieldNum == collumSet.get(21)){
	        					thu = fieldString;
	        				}else if(fieldNum == collumSet.get(22)){
	        					fri = fieldString;
	        				}else if(fieldNum == collumSet.get(23)){
	        					sat = fieldString;
	        				}else if(fieldNum == collumSet.get(24)){
	        					sun = fieldString;
	        				}else if(fieldNum == collumSet.get(25)){
	        					rowRegister.setRoomCapacity(Integer.parseInt(fieldString));
	        				}

        					if(fieldNum == highestCollum-1){
        						//finish up setting values
        						String temp = "";
        						temp+=mon;
        						temp+=tue;
        						temp+=wed;
        						temp+=thu;
        						temp+=fri;
        						temp+=sat;
        						temp+=sun;

        						rowRegister.setCourseCode(courseNum+sectionNum);
        						rowRegister.setCourseDays(temp);


	        					//make parsing look nice NOTE: this is only for a parser of size 180000!
	        					//
	        					//I could totally make a pass through the CSV to see how many rows there are
	        					//it wouldn't take much time out of parsing
								//then the loading bar could be correct with any size of CSV!

								BufferedReader bufferedReaderTemp = new BufferedReader(new FileReader(fileName));
								if (calcLength){
									while (bufferedReaderTemp.readLine() != null)
									{
										CSVLength++;
									}
								}
								calcLength = false;
								if (rowNum % 10 == 0)
								{
									highlightIndex++;
									if (highlightIndex > loadingProgress)
									{
										highlightIndex = -5;
									}
									printLoadingBar(loadingProgress, highlightIndex);
								}
								if (CSVLength >= 50){
									if (rowNum % (CSVLength / 50) == 0)
									{
										loadingProgress++;
										barCounter++;
									}
									else if (rowNum == CSVLength)
									{
										System.out.print ("\b\b\b\b║");
										if (barCounter != 50)
										{
											System.out.print('█');
										}
										System.out.println (" 100%");
										System.out.println ("╚══════════════════════════════════════════════════╝");
									}
								}
								/*else
								{
									if (CSVLength % (int) Math.ceil((50 / CSVLength)) == 0)
									{
										loadingProgress++;
										barCounter++;
									}
									else if (rowNum == CSVLength)
									{
										System.out.print ("\b\b\b\b║");
										for (int w = 0; w < 50; w++)
										{
											System.out.print('\b');
										}
										for (int w = 0; w < 50; w++)
										{
											System.out.print('█');
										}
										System.out.println (" 100%");
										System.out.println ("╚══════════════════════════════════════════════════╝");
									}
								}*/

	        					//output to database
								String course = rowRegister.getCourseCode();
								String section = rowRegister.getCourseSection();
								String days = rowRegister.getCourseDays();
								String term = rowRegister.getCourseTerm();

								db.addStudent (rowRegister);
	        				}

	        				fieldString = "";

	        			}else{
	        				fieldString+=row.charAt(i);
	        			}
	        		}
	        	}
	        }
			//newestTerm = rowRegister.getCourseTerm(); // give this to checkPrereqs later
			if (CSVLength < 50)
			{
				System.out.print ("\b\b\b\b║");
				for (int w = 0; w < 50; w++)
				{
					System.out.print('\b');
				}
				System.out.print("║");
				for (int w = 0; w < 50; w++)
				{
					System.out.print('█');
				}
				System.out.println ("║100%");
				System.out.println ("╚══════════════════════════════════════════════════╝");
			}
			else
			{
				highlightIndex = 999;
				printLoadingBar(loadingProgress, highlightIndex);
				System.out.println ("║100%");
				System.out.println ("╚══════════════════════════════════════════════════╝");
			}

	        io.output ("Done Parsing");
	        bufferedReader.close();
	    }
	    catch(FileNotFoundException ex) {
	        System.out.println("Unable to open file '" + fileName + "'");
	    }
	    catch(IOException ex) {
	        System.out.println("Error reading file '" + fileName + "'");
	    }
	}
}
