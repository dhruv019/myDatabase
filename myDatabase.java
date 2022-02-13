import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


public class myDatabase {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?","root","");
			
			Statement st = con.createStatement();
			int Result = st.executeUpdate("CREATE DATABASE myDatabase");
			
			
			Statement s = con.createStatement();
			
			s.execute("USE myDatabase");
			
            s.executeUpdate("create table Movies (Name varchar(50), Actor varchar(50), Actress varchar(50), Director varchar(50), `Year of release` YEAR)");
            
            s.executeUpdate("insert into Movies values('Inception' , 'Leonardo DiCaprio' , 'Marion Cotillard' , 'Christopher Nolan' , '2010')");  
            s.executeUpdate("insert into Movies values('Shutter Island' , 'Leonardo DiCaprio' , 'Michelle Williams' , 'Martin Scorsese' , '2010')");    
            s.executeUpdate("insert into Movies values('The Wolf of Wall Street' , 'Leonardo DiCaprio' , 'Margot Robbie' , 'Martin Scorsese' , '2013')");   
            s.executeUpdate("insert into Movies values('Interstellar' , 'Matthew McConaughey' , 'Anne Hathaway' , 'Christopher Nolan' , '2014')");
            s.executeUpdate("insert into Movies values('Predestination' , 'Ethan Hawke' , 'Sarah Snook' , 'Michael Spierig' , '2014')");
            
            ResultSet rs = s.executeQuery("select * from Movies");
            
            while (rs.next()) {
				String name = rs.getString(1);
				String actor = rs.getString(2);
				String actress = rs.getString(3);
				String director = rs.getString(4);
				int year = rs.getInt(5);
				
				System.out.println("\n "+name+" , "+actor+" , " +actress+ " , " +director+ " , " +year);
			}
            
            Scanner scan = new Scanner(System.in);

            System.out.println("\n Enter Actor's Name: ");
            String actorName = scan.nextLine();
            
            PreparedStatement pst = con.prepareStatement("select Name from Movies where Actor = ?");
            pst.setString(1, actorName);
            
            ResultSet rs1 = pst.executeQuery();
            
            while (rs1.next()) {
                String x = rs1.getString("Name");
                System.out.println(" " +x);
            }
            
            pst.close();
            s.close();
			st.close();
		}
		
		catch(Exception e) {
			System.out.println("Error: " +e.getMessage());
		}

	}

}
