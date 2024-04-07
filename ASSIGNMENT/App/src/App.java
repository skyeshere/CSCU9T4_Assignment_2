import java.util.Scanner;
import java.io.*;

public class App {
    public static void main(String[] args)
    {
        parseCSV(args[0]);    
    }

    public static void parseCSV(String directory)
    {
        try
        {
            Scanner scanner = new Scanner(new File(directory));
            System.out.println(directory);
        } 
        catch (Exception e) 
        {
            System.err.println(e + "file not found, exiting");
            System.exit(0);
        }
    }
}
