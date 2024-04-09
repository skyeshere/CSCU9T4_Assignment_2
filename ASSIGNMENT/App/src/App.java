import java.util.ArrayList;
import java.io.*;
import com.opencsv.*;
import com.opencsv.exceptions.CsvValidationException;

public class App {
    /**
     * 
     * @param args, args[0] = file directory to csv file
     */
    public static void main(String[] args)
    {
        if(args.length == 0 || args.length > 1)
        {
            System.err.println("you have either used too many or no command line arguments, make sure you're only passing the directory to the csv file.");
            System.exit(0);
        }

        ArrayList<Project> projects = parseCSV(args[0]);    
        System.out.println("Scotia Visual Productions has produced [" + (projects.size()-1)+"] projects and has generated £" + profit(projects) + " in profit");
        System.out.println(typeSearch(projects));
    }

    public static ArrayList<Project> parseCSV(String directory)
    {
        //create new arraylist to hold project data
        ArrayList<Project> project_list = new ArrayList<>();

        try 
        {
            //create new reader to read the csv file
            CSVReader reader = new CSVReader(new FileReader(directory));

            //holds a single record of the csv file
            String[] record = null;

            //loops until end of csv file
            while((record = reader.readNext()) != null)
            {
                //instantiate new Project object
                Project proj = new Project();

                //use setters to write data into object
                proj.setProjectId(record[0]);
                proj.setProjectName(record[1]);
                proj.setProjectType(record[2]);
                proj.setProjectDate(record[3]);
                proj.setProjectLocation(record[4]);
                proj.setProjectCost(record[5]);
                proj.setProjectCustomerCost(record[6]);
                proj.setSizeOfVenue(record[7]);
                proj.setProjectDuration(record[8]);
                proj.setDurationUnits(record[9]);
                proj.setNetwork(record[10]);
                proj.setPlayWright(record[11]);
                proj.setGenre(record[12]);
                proj.setFormat(record[13]);

                //add current object into arraylist
                project_list.add(proj);
            }
            
            System.out.println(project_list);
            reader.close();
        } 
        //catches exceptions that may occur above, i know its very messy
        catch (IOException e){ e.printStackTrace();} 
        catch (CsvValidationException e) {e.printStackTrace();}

        return project_list;
    }

    public static String profit(ArrayList<Project> project_list)
    {
        double profit = 0;
        
        //start from index 1 as first row is column headers
        for (int i = 1; i < project_list.size(); i++)
            profit = profit + ( Double.parseDouble(project_list.get(i).getProjectCustomerCost()) - Double.parseDouble(project_list.get(i).getProjectCost()));   
        
        return String.format("%.2f", profit); //String.format("%.2f") formats the string so only 2 characters after the decimal point are shown
    }


    /*
     * FILTERS
     */

    public static ArrayList<Project> typeSearch(ArrayList<Project> projects)
    {
        //new list to hold filtered list
        ArrayList<Project> filteredList = new ArrayList<>();
        //input string for search
        String inputString = "Music";

        //for every project in projects list
        for (Project project : projects) 
            //if project has type of inputString, add it to filtered list
            if(project.getProjectType().equals(inputString)) filteredList.add(project);
        
        //return filtered list
        return filteredList;
    }
    
}

    


    
