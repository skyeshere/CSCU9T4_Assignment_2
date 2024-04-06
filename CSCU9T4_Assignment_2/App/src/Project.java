import java.time.LocalTime;

public class Project 
{

    //class variables
    int project_id;
    String project_name;
    String project_type;
    LocalTime project_date;
    String project_location;
    double project_cost;
    double project_customer_cost;
    String size_of_venue;
    int project_duration;
    String duration_units;
    String network;
    String play_wright;
    String genre;
    String format;


    /**
     * CLASS CONSTRUCTOR
     */
    public Project(int project_id, String project_name, String project_type, LocalTime project_date, String project_location, double project_cost, double project_customer_cost, 
                    String size_of_venue, int project_duration, String duration_units, String network, String play_wright, String genre, String format)
    {
        this.project_id = project_id;
        this.project_name = project_name;
        this.project_type = project_type;
        this.project_date = project_date;
        this.project_location = project_location;
        this.project_cost = project_cost;
        this.project_customer_cost = project_customer_cost;
        this.size_of_venue = size_of_venue;
        this.project_duration = project_duration;
        this.duration_units = duration_units;
        this.network = network;
        this.play_wright = play_wright;
        this.genre = genre;
        this.format = format;
    }

    /**
     * Getters
     */

     /**
      * Setters
      *
      */
    
    
    @Override 
    public String toString() {
        return "project details: \n" +
                "\tproject_id=" + project_id + "\n" +
                "\tproject_name=" + project_name + "\n" +
                "\tproject_type=" + project_type + "\n" +
                "\tproject_date=" + project_date + "\n" +
                "\tproject_location=" + project_location + "\n" +
                "\tproject_cost=" + project_cost + "\n" +
                "\tproject_customer_cost=" + project_customer_cost + "\n" +
                "\tsize_of_venue=" + size_of_venue + "\n" +
                "\tproject_duration=" + project_duration + " " + duration_units + "\n" +
                "\tnetwork=" + network + "\n" +
                "\tplay_wright=" + play_wright + "\n" +
                "\tgenre=" + genre + "\n" +
                "\tformat=" + format + "\n";
    }
}
