import java.io.FileReader;
import com.opencsv.*;

public class App {
    public static void main(String[] args) throws Exception {

        FileReader fileReader = new FileReader("C:/Users/skye/Documents/GitHub/CSCU9T4_Assignment_2/ASSIGNMENT/App/src/scotia_visual_productions_projects.csv");
        CSVReader csvReader = new CSVReader(fileReader);
    }
}