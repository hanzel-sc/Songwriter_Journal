import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*;  
import java.sql.*; 
import java.io.*;
import javax.swing.border.BevelBorder;

//Songwriter Journal Database

class GUI extends JFrame
{
	JLabel l0, l1, l2, l3, l4;
	JTextField t1, t2, t3, t4;
	JButton b1,b2,b3,b4,b5,b6;
 	JTextArea displayArea;
    	JScrollPane scrollPane;
	String title,genre,date,progress;

public GUI()
{

setTitle("Songwriter Journal");
setSize(720,750);
setLayout(null);
getContentPane().setBackground(new Color(173, 216, 230));

ImageIcon icon = new ImageIcon("C:/Users/chris/Downloads/img2.png"); 
setIconImage(icon.getImage());

ImageIcon i1 = new ImageIcon("C:/Users/chris/Downloads/img1.png"); 
JLabel z1 = new JLabel(i1);
z1.setBounds(180, 125, 50, 50);

ImageIcon i2 = new ImageIcon("C:/Users/chris/Downloads/img1.png"); 
JLabel z2 = new JLabel(i2);
z2.setBounds(475, 125, 50, 50);

setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

add(z1); add(z2);

l0 = new JLabel("SONGWRITER DATABASE");
l0.setFont(new Font("Arial", Font.BOLD, 20 ));
l0.setBounds(230,130,470,40);

l1 = new JLabel("Title");
l1.setBounds(200,200,75,20);
l1.setFont(new Font("Arial", Font.BOLD, 14 ));

t1 = new JTextField();
t1.setBounds(300,200,200,20);

l2 = new JLabel("Genre");
l2.setBounds(200,250,75,20);
l2.setFont(new Font("Arial", Font.BOLD, 14 ));

t2 = new JTextField();
t2.setBounds(300,250,200,20);

l3 = new JLabel("Date");
l3.setBounds(200,300,75,20);
l3.setFont(new Font("Arial", Font.BOLD, 14 ));

t3 = new JTextField();
t3.setBounds(300,300,200,20);

l4 = new JLabel("Progress");
l4.setBounds(200,350,75,20);
l4.setFont(new Font("Arial", Font.BOLD, 14 ));

t4 = new JTextField();
t4.setBounds(300,350,200,20);

b1 = new JButton("Insert");
b1.setBounds(100,400,100,20);
b1.setBackground(new Color(84,175,203));
b1.setForeground(Color.BLACK);
b1.setFont(new Font("Arial", Font.BOLD,14));
b1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

b2 = new JButton("Update");
b2.setBounds(200, 400, 100, 20);
b2.setBackground(new Color(84, 175, 203));
b2.setForeground(Color.BLACK);
b2.setFont(new Font("Arial", Font.BOLD, 14));
b2.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

b3 = new JButton("Retrieve");
b3.setBounds(300, 400, 100, 20);
b3.setBackground(new Color(84, 175, 203));
b3.setForeground(Color.BLACK);
b3.setFont(new Font("Arial", Font.BOLD, 14));
b3.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

b4 = new JButton("Display");
b4.setBounds(400, 400, 100, 20);
b4.setBackground(new Color(84, 175, 203));
b4.setForeground(Color.BLACK);
b4.setFont(new Font("Arial", Font.BOLD, 14));
b4.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

b5 = new JButton("Delete");
b5.setBounds(500, 400, 100, 20);
b5.setBackground(new Color(84, 175, 203));
b5.setForeground(Color.BLACK);
b5.setFont(new Font("Arial", Font.BOLD, 14));
b5.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

b6 = new JButton("Clear");
b6.setBounds(500, 660, 100, 20);
b6.setBackground(new Color(84, 175, 203));
b6.setForeground(Color.BLACK);
b6.setFont(new Font("Arial", Font.BOLD, 14));
b6.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

displayArea = new JTextArea();
scrollPane = new JScrollPane(displayArea);
scrollPane.setBounds(100, 450, 500, 200);

add (l0); add (l1); add(l2); add(l3); add(l4); add(t1); add(t2); add(t3); add(t4); add(b1); add(b2); add(b3); add(b4); add(b5); add(b6); add(scrollPane);




try 
{UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");} 
catch (Exception e) 
{e.printStackTrace();}

setVisible(true);

}
}


class ConnectDB extends GUI implements ActionListener 
{
Connection con;
PreparedStatement st;

public ConnectDB()
{

super();
b1.addActionListener(this);
b2.addActionListener(this);
b3.addActionListener(this);
b4.addActionListener(this);
b5.addActionListener(this);
b6.addActionListener(this);
try
{
	Class.forName("com.mysql.cj.jdbc.Driver");
	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/songdb","hanzel_sc","Hans#7467884");
}
catch(Exception e)
{
	System.out.println(e);
}

}

public void insert()
{
try
{
String title = t1.getText();
String genre = t2.getText();
String date  = t3.getText();
String progress = t4.getText();

String query = "INSERT INTO songs (Title, Genre, Date, Progress) Values (?,?,?,?)";
st = con.prepareStatement(query);

st.setString(1, title);
st.setString(2, genre);
st.setString(3, date);
st.setString(4, progress);

st.executeUpdate();

t1.setText("");
t2.setText("");
t3.setText("");
t4.setText("");

JOptionPane.showMessageDialog(this,"Data inserted Successfully!");
}

catch(Exception ex)
{System.out.println(ex);}
}

public void update()
{
try
{

String oldTitle = JOptionPane.showInputDialog(this, "Enter the title of the song you want to update:");


String newTitle = t1.getText();
String genre = t2.getText();
String date  = t3.getText();
String progress = t4.getText();

String query = "UPDATE songs SET Title=?, Genre=?, Date=?, Progress=? WHERE Title=?";

st = con.prepareStatement(query);
st.setString(1, newTitle);
st.setString(2, genre);
st.setString(3, date);
st.setString(4, progress);
        
st.setString(5, oldTitle);

        
st.executeUpdate();

t1.setText("");
t2.setText("");
t3.setText("");
t4.setText("");

JOptionPane.showMessageDialog(this, "Updated successfully!");
}
catch (Exception ex) 
{System.out.println(ex);}
}

public void retrieve() {
try {
String searchTitle = JOptionPane.showInputDialog(this, "Enter the title of the song to retrieve:");

if (searchTitle == null) 
{JOptionPane.showMessageDialog(this, "No title entered.");
 return;}

String query = "SELECT * FROM songs WHERE Title = ?";
st = con.prepareStatement(query);
st.setString(1, searchTitle);

ResultSet rs = st.executeQuery();

displayArea.setText("");

if (rs.next()) 
{
String title = rs.getString("Title");
String genre = rs.getString("Genre");
String date = rs.getString("Date");
String progress = rs.getString("Progress");

displayArea.append("Title: " + title + ", Genre: " + genre + ", Date: " + date + ", Progress: " + progress + "\n");
} 
else {displayArea.setText("No song found with the title: " + searchTitle);}
} 
catch (Exception ex) 
{System.out.println(ex);}
}

public void display()
{
try {
String query = "SELECT * FROM songs";
st = con.prepareStatement(query);
ResultSet rs = st.executeQuery();

displayArea.setText("");

while (rs.next()) 
{
String title = rs.getString("Title");
String genre = rs.getString("Genre");
String date = rs.getString("Date");
String progress = rs.getString("Progress");

displayArea.append("Title: " + title + ", Genre: " + genre + ", Date: " + date + ", Progress: " + progress + "\n");
}
} 
catch (Exception ex) 
{System.out.println(ex);}

}

public void delete() 
{
try 
{
String deleteTitle = JOptionPane.showInputDialog(this, "Enter the title of the song to delete:");

if (deleteTitle == null) 
{
JOptionPane.showMessageDialog(this, "No title entered.");
return;
}

String query = "DELETE FROM songs WHERE Title = ?";
st = con.prepareStatement(query);
st.setString(1, deleteTitle);

int rowsDeleted = st.executeUpdate();

if (rowsDeleted > 0) 
{displayArea.setText("Song titled '" + deleteTitle + "' deleted successfully.");} 
else {displayArea.setText("No song found with the title: " + deleteTitle);}
} 
catch (Exception ex) 
{System.out.println(ex);}
}

public void actionPerformed(ActionEvent e)
{
if(e.getSource()==b1)
{insert();}
if(e.getSource()==b2)
{update();}
if(e.getSource()==b3)
{retrieve();}
if(e.getSource()==b4)
{display();}
if(e.getSource()==b5)
{delete();}
if(e.getSource()==b6)
{displayArea.setText("");}

}

}


public class SongDB
{
public static void main (String[] args)
{
ConnectDB db = new ConnectDB();
}
}
	
	