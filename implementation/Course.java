package implementation;

import implementation.ProgramIO;

public class Course
{
	private String courseCode;
	private String courseGrade;
	private String courseTerm;
	private String courseEnrolled;
	private String courseDays;
	private String classCode;
	private String abbreviatedBuildingName;
	private String courseRoom;
	private int courseTimeBegin;
	private int courseTimeEnd;

	//Complexity = 0
	public Course ()
	{
		this.setCourseCode("");
		this.setCourseGrade("");
		this.setCourseTerm("");
		this.setCourseEnrolled("");
		this.setCourseDays("");
		this.setCourseTimeBegin(-1);
		this.setCourseTimeEnd(-1);

		ProgramIO io = new ProgramIO ();
		io.output ("WARNING: Blank course created for student");
	}

	//Complexity = 0
	public Course (String code, String grade, String term, String enrolled, String building, String room, String days, int timeBegin, int timeEnd)
	{
		this.setCourseCode(code);
		this.setCourseGrade(grade);
		this.setCourseTerm(term);
		this.setCourseEnrolled(enrolled);
		this.setCourseDays(days);
		this.setCourseTimeBegin(timeBegin);
		this.setCourseTimeEnd(timeEnd);
		this.setAbbreviatedBuildingName(building);
		this.setCourseRoom(room);
	}
	//Complexity = 0
	public String getCourseCode ()
	{
		return this.courseCode;
	}
	//Complexity = 0
	public String getCourseGrade ()
	{
		return this.courseGrade;
	}
	//Complexity = 0
	public String getCourseTerm ()
	{
		return this.courseTerm;
	}
	//Complexity = 0
	public String getCourseEnrolled ()
	{
		return this.courseEnrolled;
	}
	//Complexity = 0
	public String getCourseDays ()
	{
		return this.courseDays;
	}
	//Complexity = 0
	public int getCourseTimeBegin ()
	{
		return this.courseTimeBegin;
	}
	//Complexity = 0
	public int getCourseTimeEnd ()
	{
		return this.courseTimeEnd;
	}

	public String getAbbreviatedBuildingName ()
    {
        return this.abbreviatedBuildingName;
	}

	public String getCourseRoom ()
	{
		return this.courseRoom;
	}

	//---------------setters-------
	//Complexity = 0
	public void setCourseCode (String newCourseCode)
	{
		this.courseCode = newCourseCode;
	}

	//Complexity = 0
	public void setCourseGrade (String newCourseGrade)
	{
		this.courseGrade = newCourseGrade;
	}

	//Complexity = 0
	public void setCourseTerm (String newCourseTerm)
	{
		this.courseTerm = newCourseTerm;
	}

	//Complexity = 0
	public void setCourseEnrolled (String newCourseEnrolled)
	{
		this.courseEnrolled = newCourseEnrolled;
	}

	//Complexity = 0
	public void setCourseDays (String newCourseDays)
	{
		this.courseDays = newCourseDays;
	}

	//Complexity = 0
	public void setCourseTimeBegin (int newCourseTimeBegin)
	{
		this.courseTimeBegin = newCourseTimeBegin;
	}

	//Complexity = 0
	public void setCourseTimeEnd (int newCourseTimeEnd)
	{
		this.courseTimeEnd = newCourseTimeEnd;
	}

	public void setAbbreviatedBuildingName (String newAbbreviatedBuildingName)
    {
        this.abbreviatedBuildingName = newAbbreviatedBuildingName;
	}

	public void setCourseRoom (String newRoom)
	{
		this.courseRoom = newRoom;
	}

	//Complexity = 0
	public String toString ()
	{
		return this.courseCode + "|" + this.courseTerm + "|" + this.courseEnrolled + ": " + this.courseGrade;
	}
}
