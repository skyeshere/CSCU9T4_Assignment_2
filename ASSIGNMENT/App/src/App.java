import java.util.ArrayList;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.opencsv.*;
import com.opencsv.exceptions.CsvValidationException;

public class App {

    //date formatter with date pattern
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * 
     * @param args, args[0] = file directory to csv file
     */
    public static void main(String[] args)
    {
        //init gui
        new GUI("meow", args[0]);
    }

    /*
     * CSV Parse method
     */
    public static ArrayList<Project> parseCSV(String directory)
    {
        //create new ArrayList to hold project data
        ArrayList<Project> project_list = new ArrayList<Project>();
        try
        {
            //create new reader to read the csv file
            CSVReader reader = new CSVReader(new FileReader(directory));

            //holds a single record of the csv file
            String[] record = null;

            //loops until end of csv file
            while((record = reader.readNext()) != null) //while record isn't null, record gets set to the next line every iteration
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

                //add current object into ArrayList
                project_list.add(proj);
            }
            reader.close();
        } 
        //catches exceptions that may occur above, i know its very messy
        catch (IOException e){ e.printStackTrace();} 
        catch (CsvValidationException e) {e.printStackTrace();}

        return project_list;
    }

    /*
     * SUMMARY FUNCTIONS
     */
    
    public static double totalCost(ArrayList<Project> project_list)
    {  
        double total = 0;
        for(int i = 1; i < project_list.size(); i++)
        {
            total = total + Double.parseDouble(project_list.get(i).getProjectCost());
        }
        return total;
    }

    public static int totalLocations(ArrayList<Project> project_list)
    {
        ArrayList<String> seen = new ArrayList<>();
        for(int i = 1; i < project_list.size(); i++)
        {
            if(!seen.contains(project_list.get(i).getProjectLocation())) seen.add(project_list.get(i).getProjectLocation());
        }

        return seen.size();
    }

    public static String profit(ArrayList<Project> project_list)
    {
        double profit = 0.00;
        
        //start from index 1 as first row is column headers
        for (int i = 1; i < project_list.size(); i++)
            profit = profit + ( Double.parseDouble(project_list.get(i).getProjectCustomerCost()) -
             Double.parseDouble(project_list.get(i).getProjectCost()));   
        
        return "Scotia Visual Productions has generated £" + profit + " in profit!" + 
                "Other statistics below: \n" + 
                "\t- Total Different Locations: " + totalLocations(project_list) + 
                "\n\t- Total Cost Spent: " + String.format("%.2f", totalCost(project_list)) + 
                "\n\t- Total Profit: £" + String.format("%.2f",profit);
    }

    /*
     * FILTERS
     */

    public static ArrayList<Project> typeSearch(ArrayList<Project> projects, String inputString)
    {
        //new list to hold filtered list
        ArrayList<Project> filteredList = new ArrayList<>();

        //for every project in projects list
        for(int i = 1; i < projects.size(); i++)
        {
            if (projects.get(i).getProjectType().toLowerCase().equals(inputString.toLowerCase()))
                filteredList.add(projects.get(i));
        }
        //return filtered list
        return filteredList;
    }

    public static ArrayList<Project> locationSearch(ArrayList<Project> projects, String inputString)
    {
        ArrayList<Project> filteredList = new ArrayList<>();
        for(int i = 1; i < projects.size(); i++)
        {
            if(projects.get(i).getProjectLocation().toLowerCase().equals(inputString.toLowerCase()));
                filteredList.add(projects.get(i));
        }
        return filteredList;
    }
    
    public static ArrayList<Project> venueSizeSearch(ArrayList<Project> projects, String inputString)
    {
        ArrayList<Project> filteredList = new ArrayList<>();

        for(int i = 1; i < projects.size(); i++)
        {
            if(projects.get(i).getSizeOfVenue().toLowerCase().equals(inputString.toLowerCase()))
                filteredList.add(projects.get(i));
        }

        return filteredList;
    }

    public static ArrayList<Project> dateFilter(String date, Boolean before, ArrayList<Project> projects)
    {
        ArrayList<Project> filteredList = new ArrayList<>();

        for(int i = 1; i < projects.size(); i++)
        {
            if(before) // if before == true
            {
                //bool class function for LocalDate to compare two dates, returns true if it is before
                if(LocalDate.parse(projects.get(i).getProjectDate(), formatter).isBefore(LocalDate.parse(date, formatter))) 
                    filteredList.add(projects.get(i));
            }
            else // if before == false
            {      
                //returns true if it is after 
                if(LocalDate.parse(projects.get(i).getProjectDate(), formatter).isAfter(LocalDate.parse(date, formatter))) 
                    filteredList.add(projects.get(i));
            }
        }
        return filteredList;
    }
}