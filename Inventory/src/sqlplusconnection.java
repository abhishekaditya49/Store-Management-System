import java.sql.*;
import javax.swing.*;

import net.proteanit.sql.DbUtils;
public class sqlplusconnection {
	Connection con= null;
		public static Connection dbconnector() {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","abhishek","12345678");
				//JOptionPane.showMessageDialog(null, "Connection Succesful");
				//Statement s = con.createStatement();
				return con;
				
				}
				
			catch(Exception e) {
				JOptionPane.showMessageDialog(null, e);
				System.exit(0);
				return null;
			}
		}
}
