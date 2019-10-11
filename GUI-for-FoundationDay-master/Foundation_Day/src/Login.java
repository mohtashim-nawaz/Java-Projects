import javax.swing.*;
import java.awt.*;
import java.sql.Statement;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login extends JFrame {



JButton blogin = new JButton("Login");
JPanel panel = new JPanel();
JLabel welcome=new JLabel("Foundation Day");
JLabel jid=new JLabel("User ID : ");
JLabel jpass=new JLabel("Password : ");
JTextField txuser = new JTextField(15);
JPasswordField pass = new JPasswordField(15);

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

Login() {
super("Judge Login Authentication");
try {

Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/FoundationDay","root","iiita123");
Statement st=conn.createStatement();
setSize(640,480);
setLocation(400,180);
panel.setLayout (null);


txuser.setBounds(250,200,150,20);
pass.setBounds(250,250,150,20);
blogin.setBounds(280,300,100,50);
welcome.setBounds(200, 10, 300, 200);
jid.setBounds(150, 200, 100, 20);
jpass.setBounds(150, 250, 100, 20);
fontsetter(welcome);
fontsetter(jid);
fontsetter(jpass);

panel.add(blogin);
panel.add(txuser);
panel.add(pass);
panel.add(welcome);
panel.add(jid);
panel.add(jpass);
getContentPane().add(panel);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
ResultSet rs;
rs = st.executeQuery("Select Email_ID,Password from Login");
int i=0;
String[] users=new String[3];
String[] passw=new String[3];
while(rs.next())
{
    users[i]=rs.getString(1);
    passw[i]=rs.getString(2);
    i++;
}
actionlogin(users,passw);
conn.close();
} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
    System.err.println(" "+ex);}

}

public void actionlogin(String[] users,String[] passw){
	blogin.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent ae) {
			String puname = txuser.getText();
			@SuppressWarnings("deprecation")
			String ppaswd = pass.getText();
                        int flag=1;
                        for(int i=0;i<3;i++)
                        {
                            if(users[i].equals(puname) && passw[i].equals(ppaswd)) flag=1;
                        }
			if(flag==1) {
				MainMenu regFace =new MainMenu();
                                regFace.setUser(puname);
				regFace.setVisible(true);
				dispose();
			} else {

				JOptionPane.showMessageDialog(null,"Wrong Email_ID or Password !");
				txuser.setText("");
				pass.setText("");
				txuser.requestFocus();
				}

			}
		});
	}
}