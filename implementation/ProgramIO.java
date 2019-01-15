package implementation;

import implementation.Student;
import java.util.ArrayList;

public class ProgramIO
{
    private ArrayList<String> totalString = new ArrayList<String>();
    private Boolean holdingOutput;

    //Complexity = 0
    public ProgramIO ()
    {
        this.setHoldingOutput (false);
    }
    //Complexity = 0
    public Boolean getHoldingOutput ()
    {
        return holdingOutput;
    }
    //Complexity = 0
    public void setHoldingOutput (Boolean newValue)
    {
        holdingOutput = newValue;
    }

    //String Store
    //Complexity = 0
    public void storeOutput(String str)
    {
        totalString.add (str);
        this.setHoldingOutput(true);
    }

    //Student Store
    //Complexity = 0
    public void storeOutput(Student std)
    {
        totalString.add (std.toString());
        this.setHoldingOutput(true);
    }

    //Print all stored output and empty array
    //Complexity = 1
    public void emptyOutput()
    {
    	for(int i = 0; i < totalString.size(); i++){
    		System.out.println(totalString.get(i));
    	}
    	totalString.clear();
    }

    // Output String
    //Complexity = 1
    public void output (String str)
    {
        String courseBoxString = "";
        for(int i = 0;i < str.length();i++){
            courseBoxString += "═";
        }
        System.out.println ("\n\n╔═"+courseBoxString+"═╗");
        System.out.println ("║ " + str + " ║");
        System.out.println ("╚═"+courseBoxString+"═╝");
    }

    public void output (String str, Boolean withWhiteSpace)
    {
        String courseBoxString = "";
        for(int i = 0;i < str.length();i++){
            courseBoxString += "═";
        }
        if (withWhiteSpace)
        {
            System.out.print("\n\n");
        }
        System.out.println ("╔═"+courseBoxString+"═╗");
        System.out.println ("║ " + str + " ║");
        System.out.print   ("╚═"+courseBoxString+"═╝");
        if (withWhiteSpace)
        {
            System.out.print ("\n");
        }
    }

    public void promptInput (String str)
    {
        this.output (str);
        System.out.print (" ---» ");
    }

    public void promptInput (String str, Boolean withWhiteSpace)
    {
        this.output (str, withWhiteSpace);
        System.out.print ("\n ---» ");
    }

    // Full student output
    //Complexity = 0
    public void output (Student std)
    {
        System.out.println (std.toString());
    }

    // Full team output
    //Complexity = 0
    public void output (Team t)
    {
        System.out.println (t.toString());
    }
}
