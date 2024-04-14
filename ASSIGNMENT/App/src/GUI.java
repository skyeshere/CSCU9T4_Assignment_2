import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class GUI extends JFrame
{
    //arraylist which will hold the whole list csv data
    public ArrayList<Project> fullList;
    //filter options for filter search
    public String filters[] = {"","Type", "Location", "Venue", "Before Date", "After Date"};

    //Column headers for table
    public String col[] = {"Project ID", "Project Name", "Project Type", "Project Date",
                            "Project Location", "Project Cost", "Project Cost To Customer",
                            "Size of Venue", "Project Duration", "Duration Units", "Network", 
                            "Play Wright", "Genre", "Format"};

    //constructor used to load the first state of the program
    GUI(String title, String directory)
    {
        JButton b = new JButton("Load CSV");
        b.setPreferredSize(new Dimension(100,100));
        add(b);
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                fullList = App.parseCSV(directory);
                getContentPane().removeAll();
                initialState();                
                reDraw();
            }
        });
        
        initWindow();
    }    

    //initialise the JFrame window
    public void initWindow()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Scotia Visual Productions Projet Viewer");
        this.setPreferredSize(new Dimension(1920,1080));
        this.setLayout(new FlowLayout());
        this.pack();
        this.setVisible(true);
    }
    
    //initial state after csv is loaded / no filters used
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

        JButton fb = submitButtonInit();
        add(fb);

        JTextPane tp = loadToolTip();
        add(tp);

        JButton sumb = SummaryButton();
        add(sumb);

        JButton addButton = new JButton("add record");
        add(addButton);

        cb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if(((String)cb.getSelectedItem()).equals("Before Date") || (((String)cb.getSelectedItem()).equals("After Date")))
                {
                    tp.setText("Date format MUST be: 'dd/MM/yyyy' \n e.g. 11/08/2004 (11th August 2004)");
                } 
                else if((((String)cb.getSelectedItem()).equals("Type")))
                {
                    tp.setText("will return an empty table if the input ISN'T exactly equal to a type\n e.g. 'Music' or 'Film'");
                }
                else if(((String)cb.getSelectedItem()).equals("Location"))
                {
                    tp.setText("will return an empty table if input ISN'T exactly equal to a location\n e.g. 'Los Angeles' or 'Glasgow'");
                }
                else if((((String)cb.getSelectedItem()).equals("Venue")))
                {
                    tp.setText("will return an empty table if input ISN'T exactly equal to a venue size\n e.g. 'Small' or 'Medium' or 'Large'");
                } else 
                {
                    tp.setText("no filter will return the full list");
                }
            }
        });
        
        fb.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                getContentPane().removeAll();
                
                Press((String)cb.getSelectedItem(), filterSearch.getText());
            }
        });

        sumb.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                remove(sumb);
                add(SummaryText());
                reDraw();
            }
        });

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                getContentPane().removeAll();
                inputRecordState();
                reDraw();
            }
        });
    }
    
    //redraws the JFrame for when changes to the components are made
    public void reDraw()
    {
        repaint();
        revalidate();
    }

    //use this state when filters are being used
    public void newState(String[][] data)
    {
        JTable table = loadTable(data);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1850,720));
        add(scrollPane);

        JComboBox<String> cb = filterBoxInit();
        JTextField searchb = searchBoxInit();
        JButton fb = submitButtonInit();
        JTextPane tt = loadToolTip();
        JButton sumb = SummaryButton();

        add(cb);
        add(searchb);
        add(fb);
        add(tt);
        add(sumb);

        cb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if(((String)cb.getSelectedItem()).equals("Before Date") || (((String)cb.getSelectedItem()).equals("After Date")))
                {
                    tt.setText("Date format MUST be: 'dd/MM/yyyy' \n e.g. 11/08/2004 (11th August 2004)");
                } 
                else if((((String)cb.getSelectedItem()).equals("Type")))
                {
                    tt.setText("will return an empty table if the input ISN'T exactly equal to a type\n e.g. 'Music' or 'Film'");
                }
                else if(((String)cb.getSelectedItem()).equals("Location"))
                {
                    tt.setText("will return an empty table if input ISN'T exactly equal to a location\n e.g. 'Los Angeles' or 'Glasgow'");
                }
                else if((((String)cb.getSelectedItem()).equals("Venue")))
                {
                    tt.setText("will return an empty table if input ISN'T exactly equal to a venue size\n e.g. 'Small' or 'Medium' or 'Large'");
                } else 
                {
                    tt.setText("no filter will return the full list");
                }
            }
        });

        fb.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                getContentPane().removeAll();
                
                Press((String)cb.getSelectedItem(), searchb.getText());
            }
        });

        sumb.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                remove(sumb);
                add(SummaryText());
                reDraw();
            }
        });
    }

    public void inputRecordState()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JButton subb = new JButton("insert record");
        JTextField id_in  = inputField();
        id_in.setText("Project ID");;
        JTextField name_in = inputField();
        name_in.setText("Project Name");
        JTextField type_in = inputField();
        type_in.setText("Project Type");
        JTextField date_in = inputField();
        date_in.setText("Date");
        JTextField location_in = inputField();
        location_in.setText("Project Location");
        JTextField  cost_in = inputField();
        cost_in.setText("Project Cost");
        JTextField customer_cost_in = inputField();
        customer_cost_in.setText("Customer Cost");
        JTextField venue_in = inputField();
        venue_in.setText("Venue Size");
        JTextField duration_in = inputField();
        duration_in.setText("Project Duration (number)");
        JTextField duration_units_in = inputField();
        duration_units_in.setText("Duration Units (Hours/Days)");
        JTextField network_in = inputField();
        network_in.setText("Network");
        JTextField play_wright_in = inputField();
        play_wright_in.setText("Play Wright");
        JTextField genre_in = inputField();
        genre_in.setText("Genre");
        JTextField format_in = inputField();
        format_in.setText("Format");

        JTextArea tt = new JTextArea();
        tt.setText("NOTE: PROGRAM WILL ASSUME THAT ANY INPUT IS VALID");
        tt.setEditable(false);

        panel.add(id_in);
        panel.add(name_in);
        panel.add(type_in);
        panel.add(date_in);
        panel.add(location_in);
        panel.add(cost_in);
        panel.add(customer_cost_in);
        panel.add(venue_in);
        panel.add(duration_in);
        panel.add(duration_units_in);
        panel.add(network_in);
        panel.add(play_wright_in);
        panel.add(genre_in);
        panel.add(format_in);
        panel.add(subb);
        panel.add(tt);
        add(panel);
        subb.addActionListener(new ActionListener() 
        {
            Project p = new Project();

            public void actionPerformed(ActionEvent e)
            {
                getContentPane().removeAll();
                p.setProjectId(id_in.getText());
                p.setProjectName(name_in.getText());
                p.setProjectType(type_in.getText());
                p.setProjectDate(date_in.getText());
                p.setProjectLocation(location_in.getText());
                p.setProjectCost(cost_in.getText());
                p.setProjectCustomerCost(customer_cost_in.getText());
                p.setSizeOfVenue(venue_in.getText());
                p.setProjectDuration(duration_in.getText());
                p.setDurationUnits(duration_units_in.getText());
                p.setNetwork(network_in.getText());
                p.setPlayWright(play_wright_in.getText());
                p.setGenre(genre_in.getText());
                p.setFormat(format_in.getText());
                fullList.add(p);
                initialState();
                reDraw();
            }
        });
    }

    public JTextField inputField()
    {
        JTextField tf = new JTextField();
        tf.setEditable(true);
        return tf;
    }
    //Summary
    public JTextArea SummaryText()
    {
        String message = App.profit(fullList);
        JTextArea ta = new JTextArea(message);
        ta.setEditable(false);
        ta.setPreferredSize(new Dimension(1600, 250));
        ta.setFont(getFont().deriveFont(15f));

        return ta;
    }

    public JButton SummaryButton()
    {
        JButton b = new JButton("Show summary!");
        b.setPreferredSize(new Dimension(100,50));
        return b;
    }

    //logic for when submit button is pressed
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
        } else if(filter.equals("Before Date"))
        {
            filteredList = App.dateFilter(input, true, fullList);
            newState(pullData(filteredList));
            reDraw();
        } else if(filter.equals("After Date"))
        {
            filteredList = App.dateFilter(input, false, fullList);
            newState(pullData(filteredList));
            reDraw();
        }
        else //case where null filter is selected
        {
            initialState();
            reDraw();
        }    
    }
    
    //initalises the submit button
    public JButton submitButtonInit()
    {
        JButton b = new JButton("Search!");
        b.setPreferredSize(new Dimension(100,50));
        return b;
    }

    //inialises the filter text input
    public JTextField searchBoxInit()
    {
        JTextField searchb = new JTextField();
        searchb.isEditable();
        searchb.setPreferredSize(new Dimension(300,50));
        return searchb;
    }

    //initalises the dropdown box for filters
    public JComboBox<String> filterBoxInit()
    {
        JComboBox<String> cb = new JComboBox<>(filters);
        cb.setPreferredSize(new Dimension(100,50));
        return cb;
    }

    //initialise tooltip text
    public JTextPane loadToolTip()
    {
        JTextPane tp = new JTextPane();
        tp.setEditable(false);
        tp.setText("no filter will return the full list");

        return tp;
    }

    //initialises a table component
    public JTable loadTable(String[][] data)
    {
        JTable t = new JTable(data, col);
        return t;
    }

    //creates a table for the full list
    public JTable loadInitialTable()
    {
        JTable t = new JTable(loadFullList(), col);
        return t;
    }

    //loads full list
    public String[][] loadFullList()
    {
            return pullData(fullList);
    }

    //pulls data from arraylist into something the tables can use (an array of arrays)
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