package implementation;

import implementation.Course;

public class DatabaseInputTemplate
{
    private String id;              // 57 Banner ID
    private String firstName;       // 58 First Name
    private String middleName;      // 60 Last Name
    private String lastName;        // 59 Middle Name
    private String courseTitle;     // 45 Course Title
    private String courseCode;      // 41 + 43 Suject Code + Course Number
    private String courseSection;   // 44 Section Number
    private String courseGrade;     // 56 Grade Code
    private String courseTerm;      //  2 Term Code
    private String enrolled;        //  5 Enrolled Ind
    private String classCode;       // 34 Class Code
    private String preferredName;   // 63 Preferred First Name
    private String email;           // 65 ACU Email
    private String building;
    private String courseDays;
    private String courseRoom;
    private int courseTimeBegin;
    private int courseTimeEnd;
    private int roomCapacity;

    public DatabaseInputTemplate ()
    {
        this.setId ("");
        this.setFirstName ("");
        this.setMiddleName ("");
        this.setLastName ("");
        this.setCourseTitle ("");
        this.setCourseCode ("");
        this.setCourseSection ("");
        this.setCourseGrade ("");
        this.setCourseTerm ("");
        this.setClassCode ("");
        this.setPreferredName ("");
        this.setEmail ("");
        this.setCourseRoom ("");
        this.setBuilding ("");
        this.roomCapacity = 0;
    }

    public DatabaseInputTemplate (String id, String firstName, String middleName, String lastName, String courseTitle, String courseCode, String courseSection, String courseGrade, String courseTerm, String classCode, String preferredName, String email, String room, String newBuilding)
    {
        this.setId (id);
        this.setFirstName (firstName);
        this.setMiddleName (middleName);
        this.setLastName (lastName);
        this.setCourseTitle (courseTitle);
        this.setCourseCode (courseCode);
        this.setCourseSection (courseSection);
        this.setCourseGrade (courseGrade);
        this.setCourseTerm (courseTerm);
        this.setClassCode (classCode);
        this.setPreferredName (preferredName);
        this.setEmail (email);
        this.setCourseRoom (room);
        this.setBuilding(newBuilding);
        this.roomCapacity = 0;
    }

    public DatabaseInputTemplate (String id, String firstName, String middleName, String lastName, String courseTitle, String courseCode, String courseSection, String courseGrade, String courseTerm, String classCode, String preferredName, String email, String room, String newBuilding, int newSize)
    {
        this.setId (id);
        this.setFirstName (firstName);
        this.setMiddleName (middleName);
        this.setLastName (lastName);
        this.setCourseTitle (courseTitle);
        this.setCourseCode (courseCode);
        this.setCourseSection (courseSection);
        this.setCourseGrade (courseGrade);
        this.setCourseTerm (courseTerm);
        this.setClassCode (classCode);
        this.setPreferredName (preferredName);
        this.setEmail (email);
        this.setCourseRoom (room);
        this.setBuilding(newBuilding);
        this.roomCapacity = newSize;
    }

    public String getId ()
    {
        return this.id;
    }

    public String getFirstName ()
    {
        return this.firstName;
    }

    public String getMiddleName ()
    {
        return this.middleName;
    }

    public String getLastName ()
    {
        return this.lastName;
    }

    public String getCourseTitle ()
    {
        return this.courseTitle;
    }

    public String getCourseCode ()
    {
        return this.courseCode;
    }

    public String getCourseSection ()
    {
        return this.courseSection;
    }

    public String getCourseGrade ()
    {
        return this.courseGrade;
    }

    public String getCourseTerm ()
    {
        return this.courseTerm;
    }

    public String getCourseRoom ()
    {
        return this.courseRoom;
    }

    public String getEnrolled ()
    {
        return this.enrolled;
    }

    public String getClassCode ()
    {
        return this.classCode;
    }

    public String getPreferredName ()
    {
        return this.preferredName;
    }

    public String getEmail ()
    {
        return this.email;
    }

    public int getCourseTimeBegin ()
    {
        return this.courseTimeBegin;
    }

    public int getCourseTimeEnd ()
    {
        return this.courseTimeEnd;
    }

    public String getBuilding ()
    {
        return this.building;
    }

    public String getCourseDays ()
    {
        return this.courseDays;
    }

    public int getRoomCapacity()
    {
      return this.roomCapacity;
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

    public void setCourseTitle (String newCourseTitle)
    {
        courseTitle = newCourseTitle;
    }

    public void setCourseCode (String newCourseCode)
    {
        courseCode = newCourseCode;
    }

    public void setCourseSection (String newCourseSection)
    {
        courseSection = newCourseSection;
    }

    public void setCourseGrade (String newCourseGrade)
    {
        courseGrade = newCourseGrade;
    }

    public void setCourseTerm (String newCourseTerm)
    {
        courseTerm = newCourseTerm;
    }

    public void setCourseRoom (String newCourseRoom)
    {
        this.courseRoom = newCourseRoom;
    }

    public void setEnrolled (String newEnrolled)
    {
        enrolled = newEnrolled;
    }

    public void setClassCode (String newClassCode)
    {
        classCode = newClassCode;
    }

    public void setPreferredName (String newPreferredName)
    {
        preferredName = newPreferredName;
    }

    public void setEmail (String newEmail)
    {
        email = newEmail;
    }

    public void setCourseTimeBegin (int newTimeBegin)
    {
        this.courseTimeBegin = newTimeBegin;
    }

    public void setCourseTimeEnd (int newTimeEnd)
    {
        this.courseTimeEnd = newTimeEnd;
    }

    public void setBuilding (String newBuilding)
    {
        this.building = newBuilding;
    }

    public void setCourseDays (String newCourseDays)
    {
        this.courseDays = newCourseDays;
    }

    public void setRoomCapacity (int newSize)
    {
        this.roomCapacity = newSize;
    }
}
