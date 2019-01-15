package implementation;

import java.lang.*;
import java.io.*;
import java.util.Properties;
import java.util.Scanner;
import implementation.ProgramIO;
import implementation.Database;

public class Settings
{
  //values
  Properties config;

  //functions
  public Settings()
  {
    config = new Properties();
    String s = System.getProperty("user.dir");
    String configDir = "/fileInput/config.properties";
    //System.out.println(configDir);
    while (configDir.charAt(0) != '/')
    {
      configDir = configDir.substring(1);
    }
    //System.out.println(configDir);

    InputStream in = this.getClass().getResourceAsStream(configDir);

    if (in == null)
    {
      System.out.println("Checking file: " + configDir);
      System.out.println("No config file found");
      createConfig();
    }
    else
    {
      try
      {
        config.load(in);
        in.close();
      }
      catch (IOException ex)
      {
        ex.printStackTrace();
      }
    }
  }

  public String readUser()
  {
    return config.getProperty("user");
  }

  public String readPass()
  {
    return config.getProperty("password");
  }

  public String readCSV()
  {
    return config.getProperty("student_list", "");
  }

  public String readTeams()
  {
    return config.getProperty("team_list");
  }

  public Properties readSettings()
  {
    return config;
  }

  public void createConfig()
  {
    ProgramIO io = new ProgramIO();
    Scanner scanner = new Scanner(System.in);
    String input = "";
    String dir = System.getProperty("user.dir");
    /*while (dir.charAt(0) != '/' && dir.charAt(0) != '\\')
    {
      dir = dir.substring(1);
    }*/

    	try
      {
        //setting DB properties
        io.promptInput("This program requires SQL access. Please input your SQL username.");
        input = scanner.nextLine();
        config.setProperty("user", input);

        io.promptInput("Please input your SQL password.");
        input = scanner.nextLine();
    		config.setProperty("password", input);

        //setting input CSV properties
        io.promptInput("Would you like to change the file name of the input .CSV file containing student class registration? If so, type \"y\". If not, type \"n\"");
        input = scanner.nextLine();
        while (!(input.equals("y")) && !(input.equals("n")))
        {
          io.promptInput("Please input either \"y\" or \"n\".");
          input = scanner.nextLine();
        }

        if (input.equals("y"))
        {
          io.promptInput("Please input new student list file name, excluding file type (example: new_student_list).");
          input = scanner.nextLine();
          input = dir + "/fileInput/" + input;
          //System.out.println(input);
          config.setProperty("student_list", input + ".csv");
        }
        else
        {
          config.setProperty("student_list", dir + "/" + "fileInput/registration_anon.csv");
          //System.out.println("/" + "fileInput/registration_anon.csv");
        }

          //System.out.println(input);

        //setting input team list properties
        io.promptInput("Would you like to change the file name of the input .txt file containing the team list? If so, type \"y\". If not, type \"n\"");
        input = scanner.nextLine();
        while (!(input.equals("y")) && !(input.equals("n")))
        {
          io.promptInput("Please input either \"y\" or \"n\".");
          input = scanner.nextLine();
        }

        if (input.equals("y"))
        {
          io.promptInput("Please input new team list file name, excluding file type (example: new_team_list).");
          input = scanner.nextLine();
          input = dir + "/fileInput/" + input;
          //System.out.println(input);
          config.setProperty("team_list", input + ".txt");
        }
        else
        {
          config.setProperty("team_list", dir + "/" + "fileInput/studentTeams.txt");
          //System.out.println("/" + "fileInput/studentTeams.txt");
        }


    		//save properties to project root folder

    		config.store(new FileOutputStream(dir + "/fileInput/config.properties"), null);
    	}
      catch (IOException ex)
      {
    		ex.printStackTrace();
      }
  }
}
