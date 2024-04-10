import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class GUI extends JFrame
{

    public String col[] = {"Project ID", "Project Name", "Project Type", "Project Date",
                            "Project Location", "Project Cost", "Project Cost To Customer",
                            "Size of Venue", "Project Duration", "Duration Units", "Network", 
                            "Play Wright", "Genre", "Format"};

    GUI(String title)
    {
        JButton b = new JButton("Load CSV");
        b.setPreferredSize(new Dimension(100,100));
        add(b);
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                b.setVisible(false);
                JTable table = loadInitialTable();
                
                JScrollPane scrollPane = new JScrollPane(table);
                scrollPane.setPreferredSize(new Dimension(1600,720));
                add(scrollPane);
            }
        });
        
        initWindow();
    }    

    public void initWindow()
    {
        this.setTitle("Scotia Visual Productions Projet Viewer");
        this.setPreferredSize(new Dimension(1920,1080));
        this.setLayout(new FlowLayout());
        this.setVisible(true);
    }

    public JTable loadInitialTable()
    {
        JTable t = new JTable(loadData(), col);
        return t;
    }

    public String[][] loadData()
    {
        ArrayList<Project> list = App.parseCSV("C:/Users/skye/Documents/GitHub/CSCU9T4_Assignment_2/ASSIGNMENT/App/src/scotia_visual_productions_projects.csv");

        String data[][] = new String[list.size()][14];
                for(int i = 1; i < list.size(); i++)
                {
                    String row[] =
                        {
                            list.get(i).project_id,
                            list.get(i).project_name,
                            list.get(i).project_type,
                            list.get(i).project_date,
                            list.get(i).project_location,
                            list.get(i).project_cost,
                            list.get(i).project_customer_cost,
                            list.get(i).size_of_venue,
                            list.get(i).project_duration,
                            list.get(i).duration_units,
                            list.get(i).network,
                            list.get(i).play_wright,
                            list.get(i).genre,
                            list.get(i).format   
                        };

                    for(int j = 0; j < 14; j++)
                    {
                        data[i-1][j] = row[j];
                    }
                }
        return data;
    }
}

