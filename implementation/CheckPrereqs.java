package implementation;

import implementation.Course;
import implementation.ProgramIO;
import java.util.ArrayList;
import java.io.*;

public class CheckPrereqs
{
	public ProgramIO io = new ProgramIO ();
	public int value;
	public boolean haveCourse;
	public boolean outputReason;
	public String courseInQuestion;
	public String coursePrereqs;
	public String fileName;
	public String row;
	public String fieldString;
	public int rowNum;
	public int rowNext;
	public int fieldNum;
	public boolean canParse;
	public boolean foundCourse;
	public boolean isEligible;
	public String keepCourse1;
	public String keepCourse2;
	public String prereqGrade;
	public String prereqClassCode;
	public int prereqMode;
	public String ioOutput;
	public String finalCourseTerm;

	public void setPrereqs(String classToCheck, String newCourseTerm)
	{
		finalCourseTerm = newCourseTerm;
		fileName = "fileInput/classPrerequisites.txt";
		courseInQuestion = classToCheck;
	    row = null;
	    rowNum = 0;
	    rowNext = 0;
	    canParse = false;
	    foundCourse = false;

	    try {
	        FileReader fileReader = new FileReader(fileName);
	        BufferedReader bufferedReader = new BufferedReader(fileReader);

	        while((row = bufferedReader.readLine()) != null) {

	        	rowNum++;
	        	rowNext = 0;
	        	fieldString = "";

	        	for(int i = 0;i < row.length() && rowNext == 0;i++){

        			if(canParse){
        				if(row.charAt(i) == ' '){
        					if(fieldString.equals(classToCheck)){
        						coursePrereqs = row;
        						foundCourse = true;
        					}
        					rowNext = 1;
        				}else{
        					fieldString+=row.charAt(i);
        				}
        			}else{
        				if(row.charAt(i) == '-'){
        					canParse = true;
        				}else{
        					rowNext = 1;
        				}
        			}
        		}
			}
			bufferedReader.close();
	    }
	    catch(FileNotFoundException ex) {
	        System.out.println("Unable to open file '" + fileName + "'");
	    }
	    catch(IOException ex) {
	        System.out.println("Error reading file '" + fileName + "'");
	    }

		//If course wasn't found, and course wan't one of the exceptions: CS120 CS315 CS316 IT225 IT325 IT410 IT490 ITC499 IS324 IS499 FIN310 FIN311 BUSA419 MGMT331 MGMT439 MGMT447 MKTG333
	    if(!foundCourse && !classToCheck.equals("FIN310") && !classToCheck.equals("FIN311") && !classToCheck.equals("BUSA419") && !classToCheck.equals("MGMT331") && !classToCheck.equals("MGMT439") && !classToCheck.equals("MGMT447") && !classToCheck.equals("MKTG333") && !classToCheck.equals("CS120") && !classToCheck.equals("CS315") && !classToCheck.equals("CS316") && !classToCheck.equals("IT225") && !classToCheck.equals("IT325") && !classToCheck.equals("IT410") && !classToCheck.equals("IT490") && !classToCheck.equals("ITC499") && !classToCheck.equals("IS324") && !classToCheck.equals("IS499")){
	    	System.out.println("ERROR: Course '"+classToCheck+"' not found in directory.");
	    }

	}

	public String hasPrereqs(ArrayList<Course> courses, String studentName, String classCode)
	{
		ioOutput = "";
		isEligible = false;
		keepCourse1 = "";
		keepCourse2 = "";
		prereqClassCode = "";
		prereqGrade = "";
		fieldNum = 0;
		fieldString = "";

		//BELOW ARE SYSTEM EXCEPTIONS HANDLED MANUALLY:

		//CS120 None MATH109/MATH124 N / MATH185 D / CS115 D
		//CS315 None IT220&CS116 D / CS120 C
		//CS316 None IT220 D & CS230/CS315 C
		//IT225 None IT220 F & CS116/CS120 D
		//IT325 None IT220 D & CS130/IT225 D
		//IT410 None CS115&CS116&CS120&CS130&CS220&CS230&IT105&IT220&IT221&IT225 D
		//IT490 None IT220 D & IT221 D & CS116/CS120 D
		//ITC499 None IT105&IT220&IT221&IT225&DET210&DET220&DET230&CS115&CS116&CS120&CS130&CS220&CS230 D
		//ASSUMMED JR    IS324 (48 earned hours) BUSA120 D
		//NO THANKS   IS499 (this is an internship) IS324 C + 2.75 GPA in Business Courses + 2.5 GPA overall, approval from director of internships

		//FIN310 None ACCT210&ECON260&ECON261 C &MATH130 D
		//FIN311 None ACCT210&ECON260&ECON261 C &MATH130 D
		//BUSA419 None FIN310&MGMT330&MKTG320 C
		//MGMT331 None ACCT210&MGMT330&IS322 C
		//MGMT439 None FIN310&(IS324/ACCT324)&MGMT330&MKTG320 C
		//MGMT447 None BLAW363&FIN310&MGMT330 C
		//MKTG333 None BUSA120&(MGMT330/MKTG320) C

		if(courseInQuestion.equals("CS120")){
			isEligible = (hasCourseOr(courses,"MATH109","MATH124","D") | hasCourseOr(courses,"MATH185","CS115","D"));
		}else if(courseInQuestion.equals("CS315")){
			isEligible = (hasCourseAnd(courses,"IT220","CS116", "D") | hasCourse(courses,"CS120","C"));
		}else if(courseInQuestion.equals("CS316")){
			isEligible = (hasCourseOr(courses,"CS230","CS315", "C") & hasCourse(courses,"IT220","D"));
		}else if(courseInQuestion.equals("IT225")){
			isEligible = (hasCourseOr(courses,"CS116","CS120", "D") & hasCourse(courses,"IT220","D"));
		}else if(courseInQuestion.equals("IT325")){
			isEligible = (hasCourseOr(courses,"CS130","IT225", "D") & hasCourse(courses,"IT220","D"));
		}else if(courseInQuestion.equals("IT410")){
			isEligible = (hasCourseAnd(courses,"CS115","CS116", "D") & hasCourseAnd(courses,"CS120","CS130", "D") & hasCourseAnd(courses,"CS220","CS230", "D") & hasCourseAnd(courses,"IT105","IT220", "D") & hasCourseAnd(courses,"IT221","IT225", "D"));
		}else if(courseInQuestion.equals("IT490")){
			isEligible = (hasCourseAnd(courses,"IT220","IT221", "D") & hasCourseOr(courses,"CS116","CS120","D"));
		}else if(courseInQuestion.equals("ITC499")){
			isEligible = (hasCourseAnd(courses,"IT105","IT220", "D") & hasCourseAnd(courses,"IT221","IT225", "D") & hasCourseAnd(courses,"DET210","DET220", "D") & hasCourseAnd(courses,"DET230","CS115", "D") & hasCourseAnd(courses,"CS116","CS120", "D") & hasCourseAnd(courses,"CS130","CS220", "D") & hasCourse(courses,"CS230", "D"));
		}else if(courseInQuestion.equals("IS324")){
			isEligible = (hasCourse(courses,"BUSA120","D") & classCheck(classCode,"JR"));
		}else if(courseInQuestion.equals("IS499")){
			isEligible = hasCourse(courses,"IS324","C");
		}else if(courseInQuestion.equals("FIN310") || courseInQuestion.equals("FIN311")){
			isEligible = hasCourseAnd(courses,"ACCT210","ECON260","C") & hasCourse(courses, "ECON261", "C") & hasCourse(courses, "MATH130", "D");
		}else if(courseInQuestion.equals("BUSA419")){
			isEligible = hasCourseAnd(courses,"FIN310","MGMT330","C") & hasCourse(courses, "MATH320", "C");
		}else if(courseInQuestion.equals("MGMT331")){
			isEligible = hasCourseAnd(courses,"ACCT210","MGMT330","C") & hasCourse(courses, "IS322", "C");
		}else if(courseInQuestion.equals("MGMT439")){
			isEligible = hasCourse(courses, "FIN310", "C") & hasCourseOr(courses,"IS324","ACCT324","C") & hasCourseAnd(courses,"MGMT330","MKTG320","C");
		}else if(courseInQuestion.equals("MGMT447")){
			isEligible = hasCourse(courses, "BLAW363", "C") & hasCourseAnd(courses,"FIN310","MGMT330","C");
		}else if(courseInQuestion.equals("MKTG333")){
			isEligible = hasCourse(courses, "BUSA120", "C") & hasCourseOr(courses,"MGMT330","MKTG320","C");
		}else{
			for(int i = 0;i < coursePrereqs.length();i++)
			{
				if(coursePrereqs.charAt(i) == '/'){
					keepCourse2 = fieldString;
					fieldString = "";
					prereqMode = 1;
				}else if(coursePrereqs.charAt(i) == '&'){
					keepCourse2 = fieldString;
					prereqMode = 2;
					fieldString = "";
				}else if(coursePrereqs.charAt(i) == ' '){
					if(fieldNum == 1){
						prereqClassCode = fieldString;
					}else if(fieldNum == 2){
						keepCourse1 = fieldString;
					}
					fieldString = "";
					fieldNum++;
				}else{
					fieldString += coursePrereqs.charAt(i);
				}
			}

			prereqGrade = fieldString;

			if(prereqMode == 0){
				if(keepCourse1.equals("None") & classCheck(classCode,prereqClassCode)){
					isEligible = true;
				}else{
					isEligible = hasCourse(courses, keepCourse1, prereqGrade);
				}
			}else if(prereqMode == 1){
				isEligible = (hasCourseOr(courses, keepCourse1, keepCourse2, prereqGrade) & classCheck(classCode,prereqClassCode));
			}else{
				isEligible = (hasCourseAnd(courses, keepCourse1, keepCourse2, prereqGrade) & classCheck(classCode,prereqClassCode));
			}

			//System.out.println("isEligible: "+isEligible+"\nkeepCourse1: "+keepCourse1+"\nkeepCourse2: "+keepCourse2+"\nprereqClassCode: "+prereqClassCode+"\nclassCode: "+classCode+"\nprereqGrade: "+prereqGrade+"\nfieldNum: "+fieldNum+"\nprereqMode: "+prereqMode+"\ncourseInQuestion: "+courseInQuestion);
		}
		if(isEligible){
			ioOutput = "";
		}else{
			ioOutput = studentName+" can't take "+courseInQuestion+" because "+ioOutput;
		}

		return ioOutput;
	}

	public int gradeValue(String str)
	{
		if(str.equals("A")){
			value = 4;
		}else if(str.equals("B")){
			value = 3;
		}else if(str.equals("C")){
			value = 2;
		}else if(str.equals("D")){
			value = 1;
		}else{
			value = 0;
		}
		return value;
	}
	public int classValue(String str)
	{
		if(str.equals("GR")){
			value = 5;
		}else if(str.equals("SR")){
			value = 4;
		}else if(str.equals("JR")){
			value = 3;
		}else if(str.equals("SO")){
			value = 2;
		}else if(str.equals("FR")){
			value = 1;
		}else{
			value = 0;
		}
		return value;
	}

	public boolean classCheck(String classInput, String classNeeded)
	{
		if(classValue(classInput) >= classValue(classNeeded)){
			return true;
		}else{
			if(!ioOutput.equals("")){ioOutput+=", ";}
			ioOutput += (classNeeded +" needed standing of "+classNeeded+" but has "+classInput);
			return false;
		}
	}

	public boolean hasCourse(ArrayList<Course> courses, String classToCheck, String gradeNeeded)
	{
		haveCourse = false;
		outputReason = false;

		for(int i = 0;i < courses.size();i++){
				if(courses.get(i).getCourseCode().equals(classToCheck) && gradeValue(courses.get(i).getCourseGrade()) >= gradeValue(gradeNeeded)){
					haveCourse = true;
					//System.out.println(courses.get(i).courseCode + " " + gradeValue(courses.get(i).courseGrade) + " "+ gradeValue(gradeNeeded));
				}else if(courses.get(i).getCourseCode().equals(classToCheck)){
					if(!ioOutput.equals("")){ioOutput+=", ";}
					ioOutput += (classToCheck +" needed grade of "+gradeNeeded+" but has "+courses.get(i).getCourseGrade());
					outputReason = true;
				}
			}
		if(!classToCheck.equals("None") && !haveCourse && !outputReason){
			if(!ioOutput.equals("")){ioOutput+=", ";}
			ioOutput += classToCheck +" not taken";
		}
		return haveCourse;
	}

	public boolean hasCourseAnd(ArrayList<Course> courses, String class1, String class2, String gradeNeeded)
	{
		return (hasCourse(courses,class1,gradeNeeded) & hasCourse(courses,class2,gradeNeeded));
	}

	public boolean hasCourseOr(ArrayList<Course> courses, String class1, String class2, String gradeNeeded)
	{
		return (hasCourse(courses,class1,gradeNeeded) | hasCourse(courses,class2,gradeNeeded));
	}
}
