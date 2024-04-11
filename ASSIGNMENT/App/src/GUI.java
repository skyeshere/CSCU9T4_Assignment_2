import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class GUI extends JFrame
{
    public ArrayList<Project> fullList = App.parseCSV("C:/Users/skye/Documents/GitHub/CSCU9T4_Assignment_2/ASSIGNMENT/App/src/scotia_visual_productions_projects.csv");
    public String filters[] = {"","Type", "Location", "Venue", "Date"};

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
                remove(b);
                initialState();
                revalidate();
                repaint();
            }
        });
        
        initWindow();
    }    

    public void initWindow()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Scotia Visual Productions Projet Viewer");
        this.setPreferredSize(new Dimension(1920,1080));
        this.setLayout(new FlowLayout());
        this.pack();
        this.setVisible(true);
    }
    
    public void initialState()
    {
        JTable table = loadInitialTable();
                
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1800,720));
        add(scrollPane);

        JComboBox<String> cb = filterBoxInit();
        add(cb);
        
        JTextField filterSearch = searchBoxInit();
        add(filterSearch);

        JButton filterSubmit = submitButtonInit();
        add(filterSubmit);
        filterSubmit.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                remove(cb);
                remove(filterSearch);
                remove(filterSubmit);
                remove(scrollPane); 
                
                Press((String)cb.getSelectedItem(), filterSearch.getText());
            }
        });
    }

    public void reDraw()
    {
        repaint();
        revalidate();
    }

    public void newState(String[][] data)
    {
        JTable table = loadTable(data);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1850,720));
        add(scrollPane);

        JComboBox<String> cb = filterBoxInit();
        JTextField sb = searchBoxInit();
        JButton b = submitButtonInit();

        add(cb);
        add(sb);
        add(b);
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                remove(cb);
                remove(sb);
                remove(b);
                remove(scrollPane);
                
                Press((String)cb.getSelectedItem(), sb.getText());
            }
        });

    }

    public void Press(String filter, String input)
    {
        ArrayList<Project> filteredList = new ArrayList<>();
        if(filter.equals("Type"))
        {
            filteredList = App.typeSearch(fullList, input);
            newState(pullData(filteredList));
            reDraw();
        }
        else if(filter.equals("Location"))
        {
            filteredList = App.locationSearch(fullList, input);
            newState(pullData(filteredList));
            reDraw();
        }
        else if(filter.equals("Venue"))
        {
            filteredList = App.venueSizeSearch(fullList, input);
            newState(pullData(filteredList));
            reDraw();
        } else if(filter.equals("Date"))
        {
            filteredList = App.dateFilter(null, null, filteredList);
            newState(pullData(filteredList));
            reDraw();
        }
        else
        {
            initialState();
            reDraw();
        }    
    }

    public JButton submitButtonInit()
    {
        JButton b = new JButton("Search!");
        b.setPreferredSize(new Dimension(100,50));
        return b;
    }

    public JTextField searchBoxInit()
    {
        JTextField sb = new JTextField();
        sb.isEditable();
        sb.setPreferredSize(new Dimension(300,50));
        return sb;
    }

    public JComboBox<String> filterBoxInit()
    {
        JComboBox<String> cb = new JComboBox<>(filters);
        cb.setPreferredSize(new Dimension(100,50));
        return cb;
    }

    public JTable loadTable(String[][] data)
    {
        JTable t = new JTable(data, col);
        return t;
    }

    public JTable loadInitialTable()
    {
        JTable t = new JTable(loadFullList(), col);
        return t;
    }

    public String[][] loadFullList()
    {
            return pullData(fullList);
    }

    public String[][] pullData(ArrayList<Project> list)
    {
        String[][] data = new String[list.size()][14];
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

