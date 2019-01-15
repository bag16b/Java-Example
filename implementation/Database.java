package implementation;

import java.util.Properties;
import java.sql.*;
import implementation.*;
import java.util.ArrayList;

public class Database
{

  private Connection conn;

  public Database()
  {
    conn = null;

    Settings config = new Settings();
    Properties dbProperties = config.readSettings();

    dbProperties.setProperty("useSSL", "false");

    try
    {
      conn = DriverManager.getConnection("jdbc:mysql://localhost/students?", dbProperties);
    }
    catch (SQLException ex)
    {
    // handle any errors
    ProgramIO io = new ProgramIO();
    io.output("Error: Database user information incorrect. Please reset settings in \"Startup Settings\" and try again.");
    //System.out.println("SQLException: " + ex.getMessage());
    //System.out.println("SQLState: " + ex.getSQLState());
    //System.out.println("VendorError: " + ex.getErrorCode());
    }
  }

  public boolean addStudent(DatabaseInputTemplate newStudent)
  {
    newCourse(newStudent);
    newStudent(newStudent);
    try
    {
      Statement st = conn.createStatement();

      String query = "insert into registered (student_ID, course_code, course_section, course_grade, term_code, enrolled) values (?, ?, ?, ?, ?, ?);";
      PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, newStudent.getId());
        pst.setString(2, newStudent.getCourseCode());
        pst.setString(3, newStudent.getCourseSection());
        pst.setString(4, newStudent.getCourseGrade());
        pst.setString(5, newStudent.getCourseTerm());
        pst.setString(6, newStudent.getEnrolled());

      pst.executeUpdate();
    }
    catch (SQLException ex)
    {
      System.out.println ("\nSQL Exception was caught");
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }

    return true;
  }

  private boolean newStudent(DatabaseInputTemplate newStudent)
  {

    try
    {
    Statement st = conn.createStatement();
    String query = "select * from student_list where Banner_ID = " + newStudent.getId() + ";";
    ResultSet result = st.executeQuery(query);
    result.beforeFirst();

    if (result.next())
    {
      //System.out.println("student exists.");
      String classStr = result.getString("class_code");
      if (classStr.contains(newStudent.getClassCode()))
      {
        query = "UPDATE student_list SET class_code = \"" + classStr + "\" where Banner_ID = " + newStudent.getId() + ";";

        st.executeUpdate(query);

        return true;
      }
      else
        return false;
    }

    query = "insert into student_list (Banner_ID, name_first, name_last, name_middle, name_preferred, email, class_code) values (?, ?, ?, ?, ?, ?, ?);";
    PreparedStatement pst = conn.prepareStatement(query);
      pst.setString(1, newStudent.getId());
      pst.setString(2, newStudent.getFirstName());
      pst.setString(3, newStudent.getLastName());
      pst.setString(4, newStudent.getMiddleName());
      pst.setString(5, newStudent.getPreferredName());
      pst.setString(6, newStudent.getEmail());
      pst.setString(7, newStudent.getClassCode());


    pst.executeUpdate();
    }
    catch (SQLException ex)
    {
      System.out.println ("\nSQL Exception was caught");
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }

    return true;
  }

  private boolean newCourse(DatabaseInputTemplate newCourse)
  {
    try
    {
    Statement st = conn.createStatement();
    String query = "select * from course_list where code_number = \"" + newCourse.getCourseCode() + "\" AND section_number = \"" + newCourse.getCourseSection() + "\";";
    ResultSet result = st.executeQuery(query);
    result.beforeFirst();

    if (result.next())
    {
      query = "select MAX(term_code) AS term from registered where course_code = \"" + newCourse.getCourseCode() + "\" AND course_section = \"" + newCourse.getCourseSection() + "\";";
      result = st.executeQuery(query);
      result.first();

      String incomingTermAsString = result.getString("term");
      int incomingTerm = Integer.parseInt(incomingTermAsString);
      int currentTerm = Integer.parseInt(newCourse.getCourseTerm());

      /*if (course_ID.contains("PSYC370"))
      {
        System.out.println("course already exists.");
        System.out.println(incomingTerm);
        System.out.println(currentTerm);
      }*/

      if (currentTerm > incomingTerm)
      {
        //System.out.println("updating course...");
        query = "UPDATE course_list SET title = ?, days = ?, time_begin = ?, time_end = ?, building = ?, room = ?, capacity = ? where code_number = ? AND section_number = ?;";
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setString(1, newCourse.getCourseTitle());
        pst.setString(2, newCourse.getCourseDays());
        pst.setInt(3, newCourse.getCourseTimeBegin());
        pst.setInt(4, newCourse.getCourseTimeEnd());
        pst.setString(5, newCourse.getBuilding());
        pst.setString(6, newCourse.getCourseRoom());
        pst.setInt(7, newCourse.getRoomCapacity());
        pst.setString(8, newCourse.getCourseCode());
        pst.setString(9, newCourse.getCourseSection());

        pst.executeUpdate();

        return true;
      }
      else
        return false;
    }

    //if (course_ID.contains("PSYC370"))
      //System.out.println("adding new course: ID = " + course_ID + ", section = " + section_ID + ", days = " + course_days);

    query = "insert into course_list (code_number, section_number, title, building, room, capacity, days, time_begin, time_end) values (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    PreparedStatement pst = conn.prepareStatement(query);
    pst.setString(1, newCourse.getCourseCode());
    pst.setString(2, newCourse.getCourseSection());
    pst.setString(3, newCourse.getCourseTitle());
    pst.setString(4, newCourse.getBuilding());
    pst.setString(5, newCourse.getCourseRoom());
    pst.setInt(6, newCourse.getRoomCapacity());
    pst.setString(7, newCourse.getCourseDays());
    pst.setInt(8, newCourse.getCourseTimeBegin());
    pst.setInt(9, newCourse.getCourseTimeEnd());

    pst.executeUpdate();
  }
    catch (SQLException ex)
    {
      System.out.println ("\nSQL Exception was caught");
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }

    return true;
  }

  public Student getStudent(String student_ID)
  {
    Student newStudent = new Student();
    try
    {
    Statement st = conn.createStatement();
    String query = "select * from student_list where Banner_ID = " + student_ID + ";";
    ResultSet result = st.executeQuery(query);
    result.beforeFirst();

    if (result.next())
    {
      newStudent.setId(result.getString("Banner_ID"));
      newStudent.setFirstName(result.getString("name_first"));
      newStudent.setLastName(result.getString("name_last"));
      newStudent.setMiddleName(result.getString("name_middle"));
      newStudent.setPreferredName(result.getString("name_preferred"));
      newStudent.setEmail(result.getString("email"));
      newStudent.setClassCode(result.getString("class_code"));
      newStudent.classesTaken = getStudentCourses(result.getString("Banner_ID"));
    }
  }
    catch (SQLException ex)
    {
      System.out.println ("\nSQL Exception was caught");
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }

    return newStudent;
  }

  public Student getStudent(String student_ID, String termCode)
  {
    Student newStudent = new Student();
    try
    {
    Statement st = conn.createStatement();
    String query = "select * from student_list where Banner_ID = " + student_ID + ";";
    ResultSet result = st.executeQuery(query);
    result.beforeFirst();

    if (result.next())
    {
      newStudent.setId(result.getString("Banner_ID"));
      newStudent.setFirstName(result.getString("name_first"));
      newStudent.setLastName(result.getString("name_last"));
      newStudent.setMiddleName(result.getString("name_middle"));
      newStudent.setPreferredName(result.getString("name_preferred"));
      newStudent.setEmail(result.getString("email"));
      newStudent.setClassCode(result.getString("class_code"));
      newStudent.classesTaken = getStudentCourses(result.getString("Banner_ID"), termCode);
    }
  }
    catch (SQLException ex)
    {
      System.out.println ("\nSQL Exception was caught");
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }

    return newStudent;
  }

  public ArrayList<String> getStudents(String class_code, String section_code, String term_code)
  {
    ArrayList<String> studentList = new ArrayList<String>();
    if (section_code.length () == 1)
    {
      section_code = "0" + section_code;
    }

    try
    {
    Statement st = conn.createStatement();
    String query = "select * from registered where course_code = " + "\"" + class_code + "\" AND course_section = \"" + section_code + "\" AND term_code = \"" + term_code + "\" AND enrolled = \"Y\";";
    ResultSet result = st.executeQuery(query);
    result.beforeFirst();

    while (result.next())
    {
      String resultID = result.getString("Student_ID");
      studentList.add(resultID);
    }
  }
    catch (SQLException ex)
    {
      System.out.println ("\nSQL Exception was caught");
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
    return studentList;
  }

  public ArrayList<Course> getStudentCourses(String student_ID)
  {
    ArrayList<Course> courseList = new ArrayList<Course>();

    try
    {
    Statement st = conn.createStatement();
    String query = "SELECT * FROM registered INNER JOIN (course_list) ON (registered.student_ID = \"" + student_ID + "\" AND registered.course_code = code_number AND registered.course_section = section_number);";
    ResultSet result = st.executeQuery(query);
    result.beforeFirst();

    while (result.next())
    {
      String resultCourse = result.getString("course_code");
      String resultGrade = result.getString("course_grade");
      String resultTerm = result.getString("term_code");
      String courseEnrolled = result.getString("enrolled");
      String building = result.getString("building");
      String room = result.getString("room");
      String days = result.getString("days");
      int timeBegin = result.getInt("time_begin");
      int timeEnd = result.getInt("time_end");
      Course newCourse = new Course(resultCourse, resultGrade, resultTerm, courseEnrolled, building, room, days, timeBegin, timeEnd);
      courseList.add(newCourse);
    }
  }
    catch (SQLException ex)
    {
      System.out.println ("\nSQL Exception was caught");
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }

    return courseList;
  }

  public ArrayList<Course> getStudentCourses(String student_ID, String termCode)
  {
    ArrayList<Course> courseList = new ArrayList<Course>();

    try
    {
    Statement st = conn.createStatement();
    String query = "SELECT * FROM registered INNER JOIN (course_list) ON (registered.student_ID = \"" + student_ID + "\" AND registered.course_code = code_number AND registered.course_section = section_number AND registered.term_code = \"" + termCode + "\");";
    ResultSet result = st.executeQuery(query);
    result.beforeFirst();

    while (result.next())
    {
      String resultCourse = result.getString("course_code");
      String resultGrade = result.getString("course_grade");
      String resultTerm = result.getString("term_code");
      String courseEnrolled = result.getString("enrolled");
      String building = result.getString("building");
      String room = result.getString("room");
      String days = result.getString("days");
      int timeBegin = result.getInt("time_begin");
      int timeEnd = result.getInt("time_end");
      Course newCourse = new Course(resultCourse, resultGrade, resultTerm, courseEnrolled, building, room, days, timeBegin, timeEnd);
      courseList.add(newCourse);
    }
  }
    catch (SQLException ex)
    {
      System.out.println ("\nSQL Exception was caught");
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }

    return courseList;
  }

  public boolean addStudentToTeam(String student_ID, String team)
  {
    try
    {
    Statement st = conn.createStatement();
    String query = "select * from student_list where Banner_ID = " + student_ID + ";";
    ResultSet result = st.executeQuery(query);
    result.beforeFirst();

    if (!result.next())
    {
      return false;
    }

    st = conn.createStatement();
    query = "UPDATE student_list SET team = \"" + team + "\" where Banner_ID = " + student_ID + ";";

    st.executeUpdate(query);
    }
    catch (SQLException ex)
    {
      System.out.println ("\nSQL Exception was caught");
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
    return true;
  }

  public boolean addStudentToTeam(Student student, String team)
  {
          try
          {
          Statement st = conn.createStatement();
          String query = "select * from student_list where Banner_ID = " + student.getId() + ";";
          ResultSet result = st.executeQuery(query);
          result.beforeFirst();

          if (result.next())
          {
            return false;
          }

          st = conn.createStatement();
          query = "UPDATE student_list SET team = \"" + team + "\" where Banner_ID = " + student.getId() + ";";

          st.executeUpdate(query);
          }
          catch (SQLException ex)
          {
            System.out.println ("\nSQL Exception was caught");
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
          }
        return true;
  }

  public Team getTeam(String teamName, String termCode)
  {
    Team newTeam = new Team(teamName);
    try
    {
      Statement st = conn.createStatement();
      String query = "select * from student_list where team = \"" + teamName + "\";";
      ResultSet result = st.executeQuery(query);
      result.beforeFirst();

      if (!result.next())
      {
        return newTeam;
      }

      newTeam.addStudent(getStudent(result.getString("Banner_ID"), termCode));

      while (result.next())
      {
        newTeam.addStudent(getStudent(result.getString("Banner_ID"), termCode));
      }
    }
    catch (SQLException ex)
    {
      System.out.println ("\nSQL Exception was caught");
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
    return newTeam;
  }

  public String getCurrentTerm()
  {
    String currentTerm = "";
    try
    {
      Statement st = conn.createStatement();
      String query = "select MAX(term_code) AS term from registered";
      ResultSet result = st.executeQuery(query);
      result.beforeFirst();

      if (!result.next())
      {
        return currentTerm;
      }

      currentTerm = result.getString("term");

    }
    catch (SQLException ex)
    {
      System.out.println ("\nSQL Exception was caught");
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
    return currentTerm;
  }

  public Room getRoom(String building, String roomName, String termCode)
  {
    Room newRoom = new Room(building, roomName);
    try
    {
    Statement st = conn.createStatement();
    String query = "select capacity, term_code from registered INNER JOIN course_list ON (course_code = code_number AND course_section = section_number) where building = \"" + building + "\" AND room = \"" + roomName + "\" order by term_code desc limit 1;";
    ResultSet result = st.executeQuery(query);
    result.beforeFirst();

    if (result.next())
    {
      newRoom.setCapacity(result.getInt("capacity"));
      newRoom.setCourses(getRoomCourses(building, roomName, termCode));
    }
  }
    catch (SQLException ex)
    {
      System.out.println ("\nSQL Exception was caught");
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }

    return newRoom;
  }

  public ArrayList<Room> getRooms(String termCode)
  {
    ArrayList<Room> roomList = new ArrayList<Room>();
    try
    {
    Statement st = conn.createStatement();
    String query = "select distinct building, room, capacity, term_code from registered INNER JOIN course_list ON (term_code = \"" + termCode + "\" AND course_code = code_number AND course_section = section_number) order by capacity desc;";
    ResultSet result = st.executeQuery(query);
    result.beforeFirst();


    while (result.next())
    {
      Room newRoom = new Room();
      newRoom.setBuilding(result.getString("building"));
      newRoom.setName(result.getString("room"));
      newRoom.setCapacity(result.getInt("capacity"));
      newRoom.setCourses(getRoomCourses(result.getString("building"), result.getString("room"), termCode));
      roomList.add(newRoom);
    }
  }
    catch (SQLException ex)
    {
      System.out.println ("\nSQL Exception was caught");
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }

    return roomList;
  }

  private ArrayList<Course> getRoomCourses(String buildingName, String roomName, String termCode)
  {
    ArrayList<Course> courseList = new ArrayList<Course>();

    try
    {
    Statement st = conn.createStatement();
    String query = "SELECT * FROM registered INNER JOIN (course_list) ON (course_list.building = \"" + buildingName + "\" AND course_list.room = \"" + roomName + "\" AND registered.course_code = course_list.code_number AND registered.course_section = course_list.section_number AND registered.term_code = \"" + termCode + "\");";
    ResultSet result = st.executeQuery(query);
    result.beforeFirst();

    while (result.next())
    {
      String resultCourse = result.getString("course_code");
      String resultGrade = result.getString("course_grade");
      String resultTerm = result.getString("term_code");
      String courseEnrolled = result.getString("enrolled");
      String building = result.getString("building");
      String room = result.getString("room");
      String days = result.getString("days");
      int timeBegin = result.getInt("time_begin");
      int timeEnd = result.getInt("time_end");
      Course newCourse = new Course(resultCourse, resultGrade, resultTerm, courseEnrolled, building, room, days, timeBegin, timeEnd);
      courseList.add(newCourse);
    }
  }
    catch (SQLException ex)
    {
      System.out.println ("\nSQL Exception was caught");
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }

  return courseList;
  }

  public void clearDB()
  {
    try
    {
      Statement st = conn.createStatement();
      String query = "DROP database IF EXISTS students;";
      st.executeUpdate(query);

      st = conn.createStatement();
      query = "SET NAMES utf8;";
      st.executeUpdate(query);

      st = conn.createStatement();
      query = "create database students;";
      st.executeUpdate(query);

      st = conn.createStatement();
      query = "use students;";
      st.executeUpdate(query);

      st = conn.createStatement();
      query = "create table student_list(";
      query += "Banner_ID varchar(10) not null,";
      query += "name_first varchar(31) not null,";
      query += "name_last varchar(31) not null,";
      query += "name_middle varchar(31),";
      query += "name_preferred varchar(31),";
      query += "email varchar(255),";
      query += "class_code varchar(3),";
      query += "team varchar(255),";
      query += "PRIMARY KEY (Banner_ID)";
      query += ");";
      st.executeUpdate(query);

      st = conn.createStatement();
      query = "create table course_list";
      query += "(";
      query += "code_number varchar(15) not null,";
      query += "section_number varchar(15) not null,";
      query += "title varchar(255) not null,";
      query += "building varchar(15),";
      query += "room varchar(15),";
      query += "capacity int,";
      query += "days varchar(7),";
      query += "time_begin int,";
      query += "time_end int,";
      query += "PRIMARY KEY (code_number, section_number)";
      query += ");";
      st.executeUpdate(query);

      st = conn.createStatement();
      query = "create table registered";
      query += "(";
      query += "student_ID varchar(10) not null,";
      query += "term_code varchar(15) not null,";
      query += "course_code varchar(15) not null,";
      query += "course_section varchar(15) not null,";
      query += "enrolled varchar(15) not null,";
      query += "course_grade varchar(2) not null,";
      query += "CONSTRAINT FOREIGN KEY (student_ID) REFERENCES student_list(Banner_ID),";
      query += "CONSTRAINT FOREIGN KEY (course_code, course_section) REFERENCES course_list(code_number, section_number)";
      query += ");";
      st.executeUpdate(query);
    }
    catch (SQLException ex)
    {
      System.out.println ("\nSQL Exception was caught");
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
  }
}
