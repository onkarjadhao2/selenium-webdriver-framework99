package stepdefination_UI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sqldbClass {

	public static void main(String[] args) throws SQLException {
		        
		        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/seleniumdb", "root", "root");
		            System.out.println("DB is connected");
		            
		            Statement smt = connection.createStatement();
		            
		            ResultSet rs = smt.executeQuery("select * from employee where empID = 2");
		            
		            while(rs.next())
		            {
		              String st = rs.getString("empName");
		              System.out.println("Name of Employee is: " + st);
		            }
		        
		    }
	}


