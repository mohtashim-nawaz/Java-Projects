
import com.sun.org.apache.xml.internal.security.c14n.helper.C14nHelper;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author placements2019
 */
public class Updation extends JFrame{
    
    private String user;
    public void setUser(String user) {this.user=user;}
    public String getUser() {return this.user;}
    
    JPanel panel=new JPanel();
    JButton b2=new JButton("Change Batch_Year");
    JButton b3=new JButton("Change Type of Performance");
    JButton b4=new JButton("Change No. of Performers");
    JButton b5=new JButton("Change Marks");
    
    
    JLabel j2=new JLabel("Click on this to change Batch_Year ->");
    JLabel j3=new JLabel("Click on this to change Type ->");
    JLabel j4=new JLabel("Click on this to change No_of_Members ->");
    JLabel j5=new JLabel("Click on this to change Marks ->");
    
    JFrame frame1;
    JFrame frame2;
    JFrame frame3;
    JFrame frame4;
    JFrame frame5;
    
    public void main(String args[])
    {
        Updation up=new Updation();
        
    }
    Updation()
    {
        super("Editing Information");
        setSize(640,480);
        setLocation(400,180);
        panel.setLayout (null);
        
        b2.setBounds(330, 50, 270, 40);
        b3.setBounds(330, 140,270, 40);
        b4.setBounds(330, 230, 270, 40);
        b5.setBounds(330, 320, 270, 40);
        
        
        j2.setBounds(30, 50, 300, 30);
        j3.setBounds(30, 140, 300, 30);
        j4.setBounds(30, 230, 300, 30);
        j5.setBounds(30, 320, 300, 30);
        
        panel.add(b2);
        panel.add(b3);
        panel.add(b4);
        panel.add(b5);

        panel.add(j2);
        panel.add(j3);
        panel.add(j4);
        panel.add(j5);
        
        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                        frame2=new JFrame("Updating Batch_Year");
                        frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame2.setLayout(null);
                        frame2.setVisible(true);
                        frame2.setSize(320,240);
                        frame2.setLocation(500, 300);
                        JTextField c1=new JTextField(15);
                        JLabel newc1=new JLabel("New Batch_Year : ");
                        JTextField c2=new JTextField(15);
                        JLabel newc2=new JLabel("Unique ID :");
                        c1.setBounds(150,40,150,20);
                        newc1.setBounds(10,35,170,25);
                        c2.setBounds(150,75,150,20);
                        newc2.setBounds(10,75,170,25);
                        JButton bu=new JButton("Apply Changes");
                        bu.setBounds(66, 150, 200, 30);
                        bu.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String newyear=c1.getText();
                                String uid=c2.getText();
                                try{
                                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                                Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/FoundationDay","root","iiita123");
                                Statement st=conn.createStatement();
                                 
                                int rv=st.executeUpdate("Update Performances set Batch_Year ='"+newyear+"' where UID ='"+uid+"'");
                                
                                if(rv<=0)
                                {
                                    JOptionPane.showMessageDialog(null,"Could not be Updated");
                                    c1.setText("");
                                    c2.setText("");
                                    c1.requestFocus();
                                }
                                 conn.close();
                                 frame2.dispose();
                                }
                                catch(ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex){
                                System.err.println(ex);}

                                }
                        });
                        frame2.add(c1);
                        frame2.add(newc1);
                        frame2.add(c2);
                        frame2.add(newc2);
                        frame2.add(bu);
            }
        });
        
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                        frame3=new JFrame("Updating Type of Performance");
                        frame3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame3.setLayout(null);
                        frame3.setVisible(true);
                        frame3.setSize(320,240);
                        frame3.setLocation(500, 300);
                        JTextField c1=new JTextField(15);
                        JLabel newc1=new JLabel("New Type : ");
                        JTextField c2=new JTextField(15);
                        JLabel newc2=new JLabel("Unique ID :");
                        c1.setBounds(150,40,150,20);
                        newc1.setBounds(10,35,170,25);
                        c2.setBounds(150,75,150,20);
                        newc2.setBounds(10,75,170,25);
                        JButton bu=new JButton("Apply Changes");
                        bu.setBounds(66, 150, 200, 30);
                        bu.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String newyear=c1.getText();
                                String uid=c2.getText();
                                try{
                                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                                Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/FoundationDay","root","iiita123");
                                Statement st=conn.createStatement();
                                 
                                int rv=st.executeUpdate("Update Performances set Type ='"+newyear+"' where UID ='"+uid+"'");
                                
                                if(rv<=0)
                                {
                                    JOptionPane.showMessageDialog(null,"Could not be Updated");
                                    c1.setText("");
                                    c2.setText("");
                                    c1.requestFocus();
                                }
                                 conn.close();
                                 frame3.dispose();
                                }
                                catch(ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex){
                                System.err.println(ex);}

                                }
                        });
                        frame3.add(c1);
                        frame3.add(newc1);
                        frame3.add(c2);
                        frame3.add(newc2);
                        frame3.add(bu);
            }
            
        });
        
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                        frame4=new JFrame("Updating Number of Performances");
                        frame4.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame4.setLayout(null);
                        frame4.setVisible(true);
                        frame4.setSize(320,240);
                        frame4.setLocation(500, 300);
                        JTextField c1=new JTextField(15);
                        JLabel newc1=new JLabel("New No. of members : ");
                        JTextField c2=new JTextField(15);
                        JLabel newc2=new JLabel("Unique ID :");
                        c1.setBounds(150,40,150,20);
                        newc1.setBounds(10,35,170,25);
                        c2.setBounds(150,75,150,20);
                        newc2.setBounds(10,75,170,25);
                        JButton bu=new JButton("Apply Changes");
                        bu.setBounds(66, 150, 200, 30);
                        bu.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String newyear=c1.getText();
                                String uid=c2.getText();
                                try{
                                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                                Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/FoundationDay","root","iiita123");
                                Statement st=conn.createStatement();
                                 
                                int rv=st.executeUpdate("Update Performances set No_of_Members ='"+newyear+"' where UID ='"+uid+"'");
                                
                                if(rv<=0)
                                {
                                    JOptionPane.showMessageDialog(null,"Could not be Updated");
                                    c1.setText("");
                                    c2.setText("");
                                    c1.requestFocus();
                                }
                                 conn.close();
                                 frame4.dispose();
                                }
                                catch(ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex){
                                System.err.println(ex);}

                                }
                        });
                        frame4.add(c1);
                        frame4.add(newc1);
                        frame4.add(c2);
                        frame4.add(newc2);
                        frame4.add(bu);
            }
        });
        
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                        frame5=new JFrame("Updating Marks by You");
                        frame5.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        frame5.setLayout(null);
                        frame5.setVisible(true);
                        frame5.setSize(320,240);
                        frame5.setLocation(500, 300);
                        JTextField c1=new JTextField(15);
                        JLabel newc1=new JLabel("New Marks : ");
                        JTextField c2=new JTextField(15);
                        JLabel newc2=new JLabel("Unique ID :");
                        c1.setBounds(150,40,150,20);
                        newc1.setBounds(10,35,170,25);
                        c2.setBounds(150,75,150,20);
                        newc2.setBounds(10,75,170,25);
                        JButton bu=new JButton("Apply Changes");
                        bu.setBounds(66, 150, 200, 30);
                        bu.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String newyear=c1.getText();
                                String uid=c2.getText();
                                try{
                                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                                Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/FoundationDay","root","iiita123");
                                Statement st=conn.createStatement();
                                String jname="";
                                ResultSet rs=st.executeQuery("Select Judge_Name from Login where Email_ID='"+user+"'");
                                while(rs.next())
                                {
                                    jname=rs.getString(1);
                                }
                                int rv=st.executeUpdate("Update Judges set Marks ='"+newyear+"' where UID ='"+uid+"' and Judge_Name='"+jname+"'");
                                
                                if(rv<=0)
                                {
                                    JOptionPane.showMessageDialog(null,"Could not be Updated");
                                    c1.setText("");
                                    c2.setText("");
                                    c1.requestFocus();
                                }
                                 conn.close();
                                 frame5.dispose();
                                }
                                catch(ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex){
                                System.err.println(ex);}

                                }
                        });
                        frame5.add(c1);
                        frame5.add(newc1);
                        frame5.add(c2);
                        frame5.add(newc2);
                        frame5.add(bu);
            }
        });
    }
    
}
