
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author placements2019
 */
public class Database extends JFrame implements ActionListener{
    static JTable table;
    JButton button=new JButton("Performance Table");
    JButton button2=new JButton("Judges Table");
    JPanel panel=new JPanel();
    JFrame frame1;
    
    public static void main(String args[])
    {
        Database db= new Database();
    }

    Database() {
        
        super("Showing Databases");
        setSize(640,480);
        setLocation(400,180);
        panel.setLayout (null);
        button.setBounds(50, 50, 200, 50);
        button2.setBounds(375, 50, 200, 50);
        button.addActionListener(this);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame1 = new JFrame("Judge Table");
        frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame1.setLayout(new BorderLayout());
        try{
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/FoundationDay","root","iiita123");
        Statement st=conn.createStatement();
        String[] columnNames = {"UID", "Judge_Name","Marks"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        table=new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        String uid;
        String name;
        String marks;
        String type;
        ResultSet rs=st.executeQuery("Select * from Judges");
        //while(rs.next())
        {
           // System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
        }
        while(rs.next())
        {
            uid=rs.getString("UID");
            name=rs.getString("Judge_Name");
            marks=rs.getString("Marks");
            model.addRow(new Object[]{uid,name,marks});
        }
        frame1.add(scroll);
        frame1.setVisible(true);
        frame1.setSize(400,300);
        frame1.setLocation(700, 350);
        conn.close();
        
        }
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex){
        System.err.println(ex);}
                
            }
        });

        panel.add(button);
        panel.add(button2);

        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    


    @Override
    public void actionPerformed(ActionEvent e) {
        //button=(JButton)e.getSource();
        frame1 = new JFrame("Performance Table");
        frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame1.setLayout(new BorderLayout());
        try{
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/FoundationDay","root","iiita123");
        Statement st=conn.createStatement();
        String[] columnNames = {"UID", "Batch Year", "No. of Members", "Type"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        table=new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
        String uid;
        String year;
        String no;
        String type;
        ResultSet rs=st.executeQuery("Select * from Performances");
        //while(rs.next())
        {
           // System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
        }
        while(rs.next())
        {
            uid=rs.getString("UID");
            year=rs.getString("Batch_Year");
            no=rs.getString("No_of_Members");
            type=rs.getString("Type");
            model.addRow(new Object[]{uid,year,no,type});
        }
        frame1.add(scroll);
        frame1.setVisible(true);
        frame1.setSize(400,300);
        frame1.setLocation(350, 350);
        
        conn.close();
        
        }
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex){
        System.err.println(ex);}
    }
    
    
}
