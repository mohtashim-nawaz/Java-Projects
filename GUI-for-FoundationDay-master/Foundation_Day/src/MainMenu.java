import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class MainMenu extends JFrame {

    private String user;
    public void setUser(String user) {this.user=user;}
    public String getUser() {return this.user;}
    JButton ViewD = new JButton("View Database");
    JButton Edit = new JButton("Edit Information");
    JButton pass = new JButton("Change Password");
    
    private static void Action(Statement st,JButton view) {
        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Workingggg !");
                System.out.println("Workinggg");
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void fontsetter(JLabel label)
{
	Font labelFont = label.getFont();
	String labelText = label.getText();
	int stringWidth = label.getFontMetrics(labelFont).stringWidth(labelText);
	int componentWidth = label.getWidth();
	double widthRatio = (double)componentWidth / (double)stringWidth;
	int newFontSize = (int)(labelFont.getSize() * widthRatio);
	int componentHeight = label.getHeight();
	int fontSizeToUse = Math.min(newFontSize, componentHeight);
	label.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
}

public static void main(String[] args) throws ClassNotFoundException {
MainMenu frameTabel = new MainMenu();
}

JLabel welcome = new JLabel("Welcome Judge, You have the following options :");
JPanel panel = new JPanel();
JFrame frame1;

MainMenu(){
super("Main Menu");
try{    
    Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
    Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/FoundationDay","root","iiita123");
    Statement st=conn.createStatement();
    setSize(640,480);
    setLocation(400,180);
    panel.setLayout (null);

    welcome.setBounds(70,-50,600,260);
    ViewD.setBounds(200, 150, 200, 50);
    Edit.setBounds(200, 225, 200, 50);
    pass.setBounds(200, 300, 200, 50);
    fontsetter(welcome);

    panel.add(welcome);
    panel.add(ViewD);
    panel.add(Edit);
    panel.add(pass);
    getContentPane().add(panel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    ViewD.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
            Database db=new Database();
            db.setVisible(true);
            dispose();
        }
    });
    Edit.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Updation up=new Updation();
            up.setUser(user);
            up.setVisible(true);
            dispose();
        }
    });
    pass.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame1=new JFrame("Password Settings");
            frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame1.setLayout(null);
            frame1.setVisible(true);
            frame1.setSize(320,120);
            frame1.setLocation(500, 300);
            JPasswordField chng=new JPasswordField(15);
            JLabel newp=new JLabel("New Password:");
            newp.setBounds(10,20,150,50);
            chng.setBounds(130,35,170,25);
            JButton bu=new JButton("Change");
            bu.setBounds(130, 80, 100, 20);
            bu.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String check=chng.getText();
                    if(check.matches("^[a-zA-z]*$") && check.length()>=7 && check.matches("^[0-9]*$"))
                    {
                        try{
                            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/FoundationDay","root","iiita123");
                            Statement st=conn.createStatement();
                             st.executeUpdate("Update Login set Password = '"+check+"' where Email_ID ='"+user+"'");
                             conn.close();
                             frame1.dispose();
                            }
                            catch(ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex){
                            System.err.println(ex);}
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Password should be alphanumeric and of atleast 7 characters");
                        chng.setText("");
                        chng.requestFocus();
                    }
                }
            });
            frame1.add(chng);
            frame1.add(newp);
            frame1.add(bu);
            
        }
    });
conn.close();
}catch(ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex){
System.err.println(ex);}
}


}

