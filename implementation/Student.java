package implementation;

import java.util.ArrayList;

import implementation.Course;

public class Student
{
    private String id;              // 57 Banner ID
    private String firstName;       // 58 First Name
    private String middleName;      // 60 Last Name
    private String lastName;        // 59 Middle Name
    private String enrolled;        //  5 Enrolled Ind - do we still need this here?
    private String preferredName;   // 63 Preferred First Name
    private String email;           // 65 ACU Email
    private String classCode;
    public ArrayList<Course> classesTaken;

    //-----------Constructors-----------
    public Student ()
    {
        this.setId ("");
        this.setFirstName ("");
        this.setMiddleName ("");
        this.setLastName ("");
        this.setEnrolled ("");
        this.setPreferredName ("");
        this.setEmail ("");
        this.setClassCode ("");
    }

    public Student (String id)
    {
        this.setId (id);
        this.setFirstName ("");
        this.setMiddleName ("");
        this.setLastName ("");
        this.setEnrolled ("");
        this.setPreferredName ("");
        this.setEmail ("");
        this.setClassCode ("");
    }

    public Student (String id, String firstName)
    {
        this.setId (id);
        this.setFirstName (firstName);
        this.setMiddleName ("");
        this.setLastName ("");
        this.setEnrolled ("");
        this.setPreferredName ("");
        this.setEmail ("");
        this.setClassCode ("");
    }

    public Student (String id, String firstName, String middleName, String lastName, String preferredName, String newClass, String newEmail)
    {
        this.setId (id);
        this.setFirstName (firstName);
        this.setMiddleName (middleName);
        this.setLastName (lastName);
        this.setPreferredName (preferredName);
        this.setEmail (newEmail);
        this.setClassCode(newClass);
    }


    //-----------Getters-----------
    public String getId ()
    {
        return id;
    }

    public String getFirstName ()
    {
        return firstName;
    }

    public String getMiddleName ()
    {
        return middleName;
    }

    public String getLastName ()
    {
        return lastName;
    }

    public String getEnrolled ()
    {
        return enrolled;
    }

    public String getPreferredName ()
    {
        return preferredName;
    }

    public String getEmail ()
    {
        return email;
    }

    public String getClassCode ()
	{
		return this.classCode;
	}

    //-----------Setters-----------
    public void setId (String newValue)
    {
        id = newValue;
    }

    public void setFirstName (String newFirstName)
    {
        firstName = newFirstName;
    }

    public void setMiddleName (String newMiddleName)
    {
        middleName = newMiddleName;
    }

    public void setLastName (String newLastName)
    {
        lastName = newLastName;
    }

    public void setEnrolled (String newEnrolled)
    {
        enrolled = newEnrolled;
    }

    public void setPreferredName (String newPreferredName)
    {
        preferredName = newPreferredName;
    }

    public void setEmail (String newEmail)
    {
        email = newEmail;
    }

    public void setClassCode (String newClassCode)
	{
		this.classCode = newClassCode;
    }

    public void setCourseList (ArrayList <Course> newCourseList)
    {
        this.classesTaken = newCourseList;
    }

    public void addCourse (Course newCourse)
    {
        this.classesTaken.add(newCourse);
    }

    //-----------Misc Functions-----------
    public boolean hasDay(String dayString, String day)
    {
        boolean hasDay = false;
        int i = 0;
        while(i < dayString.length())
        {
            if(dayString.charAt(i) == day.charAt(0))
            {
                hasDay = true;
                break;
            }
            i++;
        }
        return hasDay;
    }

    public ArrayList<ArrayList<Integer>> getFullSchedule (int termCode)
    {
        ArrayList<ArrayList<Integer>> fullSchedule = new ArrayList <ArrayList<Integer>> ();
        fullSchedule.add (this.getSchedule ("U", termCode));
        fullSchedule.add (this.getSchedule ("M", termCode));
        fullSchedule.add (this.getSchedule ("T", termCode));
        fullSchedule.add (this.getSchedule ("W", termCode));
        fullSchedule.add (this.getSchedule ("R", termCode));
        fullSchedule.add (this.getSchedule ("F", termCode));
        fullSchedule.add (this.getSchedule ("S", termCode));
        return fullSchedule;
    }

    public ArrayList<Integer> getSchedule(String day, int termCode)
    {
        ArrayList<Integer> output = new ArrayList<Integer>();
        int begin;
        int index;
        int j;

        //Put all relevant courses into schedule
        for(int i = 0;i < this.classesTaken.size();i++)
        {
            //If class has right day and termcode
            if(hasDay(this.classesTaken.get(i).getCourseDays(), day) && this.classesTaken.get(i).getCourseTerm().equals(Integer.toString(termCode)) && !this.classesTaken.get(i).getCourseGrade().equals("W"))
            {
                //Sort them as they enter the schedule
                begin = this.classesTaken.get(i).getCourseTimeBegin();
                index = output.size();
                j = 0;

                while(j < output.size())
                {
                    if(output.get(j) > begin)
                    {
                        index = j;
                        break;
                    }

                    j+=2;//only look at beginning times
                }
                output.add(index,begin);//this.classesTaken.get(i).getCourseTimeBegin()
                output.add(index+1,this.classesTaken.get(i).getCourseTimeEnd());
            }
        }
        return output;
    }

    public String toString ()
    {
        return
            "\n╔══════════════════╗ " +
            "\n║Student ID        ║ " + this.getId() +
            "\n║First Name        ║ " + this.getFirstName() +
            "\n║Middle Name       ║ " + this.getMiddleName() +
            "\n║Last Name         ║ " + this.getLastName() +
            "\n║Preffered Name    ║ " + this.getPreferredName() +
            "\n║Student ACU Email ║ " + this.getEmail() +
            "\n╚══════════════════╝";
    }

    public String coursesToString ()
    {
        String output = "\n╔══════════════════╗\n";
        for (int i = 0; i < this.classesTaken.size (); i++)
        {
            output += "║Class             ║" + this.classesTaken.get(i).toString() + "\n";
        }
        output += "╚══════════════════╝";
        return output;
    }

    public String toFullString ()
    {
        String output =
            "\n╔══════════════════╗ " +
            "\n║Student ID        ║ " + this.getId() +
            "\n║First Name        ║ " + this.getFirstName() +
            "\n║Middle Name       ║ " + this.getMiddleName() +
            "\n║Last Name         ║ " + this.getLastName() +
            "\n║Preffered Name    ║ " + this.getPreferredName() +
            "\n║Student ACU Email ║ " + this.getEmail() + "\n";
        if (this.classesTaken == null || this.classesTaken.size () == 0)
        {
            output += "║Class         NONE║\n";
        }
        for (int i = 0; this.classesTaken != null && i < this.classesTaken.size (); i++)
        {
            output += "║Class             ║" + this.classesTaken.get(i).toString() + " | " + this.classesTaken.get(i).getCourseTimeBegin() + " | " + this.classesTaken.get(i).getCourseTimeEnd() + " | " + this.classesTaken.get(i).getCourseDays() + "\n";
        }

        output += "╚══════════════════╝";

        return output;
    }
}
