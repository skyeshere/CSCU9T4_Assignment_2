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
        public int getProjectId() {
            return project_id;
        }
    
        public String getProjectName() {
            return project_name;
        }
    
        public String getProjectType() {
            return project_type;
        }
    
        public LocalTime getProjectDate() {
            return project_date;
        }
    
        public String getProjectLocation() {
            return project_location;
        }
    
        public double getProjectCost() {
            return project_cost;
        }
    
        public double getProjectCustomerCost() {
            return project_customer_cost;
        }
    
        public String getSizeOfVenue() {
            return size_of_venue;
        }
    
        public int getProjectDuration() {
            return project_duration;
        }
    
        public String getDurationUnits() {
            return duration_units;
        }
    
        public String getNetwork() {
            return network;
        }
    
        public String getPlayWright() {
            return play_wright;
        }
    
        public String getGenre() {
            return genre;
        }
    
        public String getFormat() {
            return format;
        }
    
        /**
         * Setters
         */

        public void setProjectId(int project_id) {
            this.project_id = project_id;
        }
    
        public void setProjectName(String project_name) {
            this.project_name = project_name;
        }
    
        public void setProjectType(String project_type) {
            this.project_type = project_type;
        }
    
        public void setProjectDate(LocalTime project_date) {
            this.project_date = project_date;
        }
    
        public void setProjectLocation(String project_location) {
            this.project_location = project_location;
        }
    
        public void setProjectCost(double project_cost) {
            this.project_cost = project_cost;
        }
    
        public void setProjectCustomerCost(double project_customer_cost) {
            this.project_customer_cost = project_customer_cost;
        }
    
        public void setSizeOfVenue(String size_of_venue) {
            this.size_of_venue = size_of_venue;
        }
    
        public void setProjectDuration(int project_duration) {
            this.project_duration = project_duration;
        }
    
        public void setDurationUnits(String duration_units) {
            this.duration_units = duration_units;
        }
    
        public void setNetwork(String network) {
            this.network = network;
        }
    
        public void setPlayWright(String play_wright) {
            this.play_wright = play_wright;
        }
    
        public void setGenre(String genre) {
            this.genre = genre;
        }
    
        public void setFormat(String format) {
            this.format = format;
        }
    
    
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
