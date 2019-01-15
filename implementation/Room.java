package implementation;

import java.util.ArrayList;

import implementation.Course;

public class Room
{
  private String building;
  private String name;
  private int capacity;
  private ArrayList<Course> classes;

  //-----------Constructors-----------
  public Room ()
  {
    this.setBuilding("");
    this.setName("");
    this.setCapacity(0);
    this.classes = new ArrayList<Course>();
  }

  public Room (String buildingName)
  {
    this.setBuilding(buildingName);
    this.setName("");
    this.setCapacity(0);
    this.classes = new ArrayList<Course>();
  }

  public Room (String buildingName, String roomName)
  {
    this.setBuilding(buildingName);
    this.setName(roomName);
    this.setCapacity(0);
    this.classes = new ArrayList<Course>();
  }

  public Room (String roomName, int max)
  {
    this.setBuilding("");
    this.setName(roomName);
    this.setCapacity(max);
    this.classes = new ArrayList<Course>();
  }

  public Room (String buildingName, String roomName, int max)
  {
    this.setBuilding(buildingName);
    this.setName(roomName);
    this.setCapacity(max);
    this.classes = new ArrayList<Course>();
  }

  //-----------Getters-----------
  public String getBuilding()
  {
    return this.building;
  }

  public String getName()
  {
    return this.name;
  }

  public int getCapacity()
  {
    return this.capacity;
  }

  public ArrayList<Course> getCourses()
  {
    return classes;
  }

  //returns the times taken up by classes in an list of String lists.
  //The first value in each string list is the day. The following values are times of courses on that day.
  //Times are formatted timeBegin-timeEnd  -  Example: 9:30-10:50
  public ArrayList<ArrayList<String>> getCourseTimes()
  {
    ArrayList<ArrayList<String>> timeList = new ArrayList<ArrayList<String>>();
    
    //creating the timeList, which will contain a String list for each day.
    for (int a = 0; a < 7; a++)
        timeList.add(new ArrayList<String>());
      timeList.get(0).add("U");
      timeList.get(1).add("M");
      timeList.get(2).add("T");
      timeList.get(3).add("W");
      timeList.get(4).add("R");
      timeList.get(5).add("F");
      timeList.get(6).add("S");
    
    for (int count = 0; count < classes.size(); count++)
    {
      //confirming which day the course is associated with, then finding it in timeList.
      String classDay = classes.get(count).getCourseDays();
      for (int b = 0; b < 7; b++)
      {
        if (classDay.contains(timeList.get(b).get(0)))
        {
          //adding the new time as a new value to that day's String list.
          String newTime = "";
          newTime = newTime + classes.get(count).getCourseTimeBegin();
          newTime = newTime + "-";
          newTime = newTime + classes.get(count).getCourseTimeEnd();

          timeList.get(b).add(newTime);
        }
      }
    }

    return timeList;
  }
  //-------------------------------------------------------

  //-----------Setters-----------
  public void setBuilding(String newBuilding)
  {
    this.building = newBuilding;
  }

  public void setName(String newName)
  {
    this.name = newName;
  }

  public void setCapacity(int newSize)
  {
    this.capacity = newSize;
  }

  public void setCourses(ArrayList<Course> courseList)
  {
    this.classes = courseList;
  }

  public void addCourse(Course newCourse)
  {
    this.classes.add(newCourse);
  }
}
