import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * FILE: CreateTables.java
 * AUTHORS: QUANG VU, QUAN NGUYEN, MINH TRAN
 * CLASS: CSC 460 FALL 2021
 * ASSIGNMENT: Project 4
 * PURPOSE: This file will create all the necessary table in oracle database 
 */

public class CreateTables {
	public static void main(String[] args) {
		String username = "puffvu",    // Oracle DBMS username
			   password = "a2137";     // Oracle DBMS password
			
			
			if (args.length == 2) {    // get username/password from cmd line args
			    username = args[0];
			    password = args[1];
			} else {
			    System.out.println("\nUsage:  java createTable <username> <password>\n"
			                     + "    where <username> is your Oracle DBMS"
			                     + " username,\n    and <password> is your Oracle"
			                     + " password (not your system password).\n");
			    System.exit(-1);
			}
			
			// create connection
			Connection dbconn = createConnection(username, password);
			
			// create tables
			userTable(dbconn);
			licenseTable(dbconn);
			vehicleTable(dbconn);
			employeeTable(dbconn);
			appointmentTable(dbconn);
			serviceTable(dbconn);
			stateIDTable(dbconn);
			permitTable(dbconn);
			jobTable(dbconn);
			departmentTable(dbconn);
			
			System.out.println("all tables were created");
		
	}
	
	
	/*
	 * createConnection(String userName, String password): This function will 
	 * use the given parameter to create connection to the appropriate server
	 * and later use that server to create table
	 */
	private static Connection createConnection(String username, String password) {
		// Access the oracle DB
				final String oracleURL =   // Magic lectura -> aloe access spell
		                "jdbc:oracle:thin:@aloe.cs.arizona.edu:1521:oracle";
				
				
				    // load the (Oracle) JDBC driver by initializing its base
				    // class, 'oracle.jdbc.OracleDriver'.
				
				try {
				
				        Class.forName("oracle.jdbc.OracleDriver");
				
				} catch (ClassNotFoundException e) {
				
				        System.err.println("*** ClassNotFoundException:  "
				            + "Error loading Oracle JDBC driver.  \n"
				            + "\tPerhaps the driver is not on the Classpath?");
				        System.exit(-1);
				
				}
				
				
				    // make and return a database connection to the user's
				    // Oracle database
				
				Connection dbconn = null;
				
				try {
				        dbconn = DriverManager.getConnection(oracleURL,username,password);
				
				} catch (SQLException e) {
				
				        System.err.println("*** SQLException:  "
				            + "Could not open JDBC connection.");
				        System.err.println("\tMessage:   " + e.getMessage());
				        System.err.println("\tSQLState:  " + e.getSQLState());
				        System.err.println("\tErrorCode: " + e.getErrorCode());
				        System.exit(-1);
				
				}
				
				return dbconn;
	}
	
	/*
	 *  This function will create user table in the Oracle database
	 *  
	 */
	private static void userTable(Connection dbconn) {
		Statement stmt = null;
		
		StringBuilder query = new StringBuilder();
		query.append("CREATE table USER ( ");
		query.append("username varchar2(64) UNIQUE, ");
		query.append("DOB DATE, ");
		query.append("fname varchar2(64), ");
		query.append("lname varchar2(64), ");
		query.append("primary key (username))");
		
		// Grant access to outside
		String special = "GRANT SELECT ON USER TO PUBLIC";
		try {
			stmt = dbconn.createStatement();
            stmt.executeQuery(query.toString());
            System.out.println("create USER table successfully");
		    stmt.executeQuery(special);
		} catch (SQLException e) {
            System.err.println("*** SQLException:  "
                + "Could not fetch query results. (Error happen when create USER table)");
            System.err.println("\tMessage:   " + e.getMessage());
            System.err.println("\tSQLState:  " + e.getSQLState());
            System.err.println("\tErrorCode: " + e.getErrorCode());
            System.exit(-1);
		}
	}
	
	/*
	 * This function will create license table
	 *
	 */
	private static void licenseTable(Connection dbconn) {
		Statement stmt = null;
		
		StringBuilder query = new StringBuilder();
		query.append("CREATE table LICENSE ( ");
		query.append("username varchar2(64), ");
		query.append("issuedDate DATE, ");
		query.append("LicenseID INTEGER, ");
		query.append("primary key (LicenseID) ");
		query.append("FOREIGN KEY (username) REFERENCES user UNIQUE"
				+ "ON DELETE CASCADE ON UPDATE CASCADE");
		
		
		
		// Grant access to outside
		String special = "GRANT SELECT ON LICENSE TO PUBLIC";
		try {
			stmt = dbconn.createStatement();
            stmt.executeQuery(query.toString());
            System.out.println("create LICENSE table successfully");
		    stmt.executeQuery(special);
		} catch (SQLException e) {
            System.err.println("*** SQLException:  "
                + "Could not fetch query results. (Error happen when create LICENSE table)");
            System.err.println("\tMessage:   " + e.getMessage());
            System.err.println("\tSQLState:  " + e.getSQLState());
            System.err.println("\tErrorCode: " + e.getErrorCode());
            System.exit(-1);
		}
	}
	
	/*
	 * This function will create vehicle table
	 */
	private static void vehicleTable(Connection dbconn) {
		Statement stmt = null;
		
		StringBuilder query = new StringBuilder();
		query.append("CREATE table VEHICLE ( ");
		query.append("username varchar2(64), ");
		query.append("registration_number varchar2(64), ");
		query.append("issuedDate DATE, ");
		query.append("make varchar2(64), ");
		query.append("color varchar2(64), ");
		query.append("primary key (registration_number) ");
		query.append("FOREIGN KEY (username) REFERENCES user "
				+ "ON DELETE CASCADE ON UPDATE CASCADE) ");
		
		// Grant access to outside
		String special = "GRANT SELECT ON VEHICLE TO PUBLIC";
		try {
			stmt = dbconn.createStatement();
            stmt.executeQuery(query.toString());
            System.out.println("create VEHICLE table successfully");
		    stmt.executeQuery(special);
		} catch (SQLException e) {
            System.err.println("*** SQLException:  "
                + "Could not fetch query results. (Error happen when create VEHICLE table)");
            System.err.println("\tMessage:   " + e.getMessage());
            System.err.println("\tSQLState:  " + e.getSQLState());
            System.err.println("\tErrorCode: " + e.getErrorCode());
            System.exit(-1);
		}
	}
	
	/*
	 * This function will create employee table
	 */
	private static void employeeTable(Connection dbconn) {
		Statement stmt = null;
		
		StringBuilder query = new StringBuilder();
		query.append("CREATE table employee ( ");
		query.append("job_id integer, ");
		query.append("departmentNumber integer, ");
		query.append("fname varchar2(64), ");
		query.append("lname varchar2(64), ");
		query.append("EmployeeID Integer, ");
		query.append("primary key (EmployeeID), ");
		query.append("FOREIGN KEY (jobID) REFERENCES job ON UPDATE CASCADE, "
				+ "FOREIGN KEY (departmentNumber) REFERENCES department ON UPDATE CASCADE )");
		
		
		// Grant access to outside
		String special = "GRANT SELECT ON EMPLOYEE TO PUBLIC";
		try {
			stmt = dbconn.createStatement();
            stmt.executeQuery(query.toString());
            System.out.println("create EMPLOYEE table successfully");
		    stmt.executeQuery(special);
		} catch (SQLException e) {
            System.err.println("*** SQLException:  "
                + "Could not fetch query results. (Error happen when create employee table)");
            System.err.println("\tMessage:   " + e.getMessage());
            System.err.println("\tSQLState:  " + e.getSQLState());
            System.err.println("\tErrorCode: " + e.getErrorCode());
            System.exit(-1);
		}
	}
	
	/*
	 * This method will create appointment table
	 */
	private static void appointmentTable(Connection dbconn) {
		Statement stmt = null;
		
		StringBuilder query = new StringBuilder();
		query.append("CREATE table appointment ( ");
		query.append("username varchar2(64), ");
		query.append("time DATE NOT NULL, ");
		query.append("EmployeeID Integer, ");
		query.append("service_type varchar2(64), ");
		query.append("success Integer, "); // 0 true, 1 false 
		query.append("primary key (username, time)), ");
		query.append("FOREIGN KEY (username) REFERENCES user "
				+ "ON DELETE CASCADE ON UPDATE CASCADE, ");
		query.append("FOREIGN KEY (EmployeeID) REFERENCES employee "
				+ "ON UPDATE CASCADE ON DELETE CASCADE, "); 
		query.append("FOREIGN KEY (service_type) REFERENCES service "
				+ "ON UPDATE CASCADE, "); // no reason to delete a service
		query.append("CONSTRAINT TimeConflict "
				+ "CHECK (NOT EXISTS ( SELECT * "
				+ "FROM appointment a "
				+ "WHERE a.timeFrom = appointment.timeFrom AND "
				+ "a.username = appointment.username)))");
		
		
		// Grant access to outside
		String special = "GRANT SELECT ON APPOINTMENT TO PUBLIC";
		try {
			stmt = dbconn.createStatement();
            stmt.executeQuery(query.toString());
            System.out.println("create APPOINTMENT table successfully");
		    stmt.executeQuery(special);
		} catch (SQLException e) {
            System.err.println("*** SQLException:  "
                + "Could not fetch query results. (Error happen when create APPOINTMENT table)");
            System.err.println("\tMessage:   " + e.getMessage());
            System.err.println("\tSQLState:  " + e.getSQLState());
            System.err.println("\tErrorCode: " + e.getErrorCode());
            System.exit(-1);
		}
	}
	
	/*
	 * This method will create service table
	 */
	private static void serviceTable(Connection dbconn) {
		Statement stmt = null;
		
		StringBuilder query = new StringBuilder();
		query.append("CREATE table service ( ");
		query.append("service_type varchar2(64) UNIQUE, "); 
		query.append("price integer, ");
		query.append("expiry_length integer, ");
		query.append("primary key (service_type))");
		
		// Grant access to outside
		String special = "GRANT SELECT ON SERVICE TO PUBLIC";
		try {
			stmt = dbconn.createStatement();
            stmt.executeQuery(query.toString());
            System.out.println("create SERVICE table successfully");
		    stmt.executeQuery(special);
		} catch (SQLException e) {
            System.err.println("*** SQLException:  "
                + "Could not fetch query results. (Error happen when create SERVICE table)");
            System.err.println("\tMessage:   " + e.getMessage());
            System.err.println("\tSQLState:  " + e.getSQLState());
            System.err.println("\tErrorCode: " + e.getErrorCode());
            System.exit(-1);
		}
	}
	
	/*
	 * This function will create stateID table
	 */
	private static void stateIDTable(Connection dbconn) {
		Statement stmt = null;
		
		StringBuilder query = new StringBuilder();
		query.append("CREATE table stateID ( ");
		query.append("username varchar2(64), ");
		query.append("issuedDate DATE, ");
		query.append("id INTEGER, ");
		query.append("primary key (id),");
		query.append("FOREIGN KEY (username) REFERENCES user UNIQUE "
				+ "ON DELETE CASCADE ON UPDATE CASCADE, ");
		
		// Grant access to outside
		String special = "GRANT SELECT ON stateID TO PUBLIC";
		try {
			stmt = dbconn.createStatement();
            stmt.executeQuery(query.toString());
            System.out.println("create stateID table successfully");
		    stmt.executeQuery(special);
		} catch (SQLException e) {
            System.err.println("*** SQLException:  "
                + "Could not fetch query results. (Error happen when create STATEID table)");
            System.err.println("\tMessage:   " + e.getMessage());
            System.err.println("\tSQLState:  " + e.getSQLState());
            System.err.println("\tErrorCode: " + e.getErrorCode());
            System.exit(-1);
		}
	}
	
	/*
	 * This function will create permit table
	 */
	private static void permitTable(Connection dbconn) {
		Statement stmt = null;
		
		StringBuilder query = new StringBuilder();
		query.append("CREATE table PERMIT ( ");
		query.append("username varchar2(64), ");
		query.append("issuedDate DATE, ");
		query.append("id INTEGER, ");
		query.append("primary key (id), ");
		query.append("FOREIGN KEY (username) REFERENCES user "
				+ "ON DELETE CASCADE ON UPDATE CASCADE, ");
		
		// Grant access to outside
		String special = "GRANT SELECT ON PERMIT TO PUBLIC";
		try {
			stmt = dbconn.createStatement();
            stmt.executeQuery(query.toString());
            System.out.println("create PERMIT table successfully");
		    stmt.executeQuery(special);
		} catch (SQLException e) {
            System.err.println("*** SQLException:  "
                + "Could not fetch query results. (Error happen when create PERMIT table)");
            System.err.println("\tMessage:   " + e.getMessage());
            System.err.println("\tSQLState:  " + e.getSQLState());
            System.err.println("\tErrorCode: " + e.getErrorCode());
            System.exit(-1);
		}
	}
	
	/*
	 * This function will create job table
	 */
	private static void jobTable(Connection dbconn) {
		Statement stmt = null;
		
		StringBuilder query = new StringBuilder();
		query.append("CREATE table JOB ( ");
		query.append("job_id integer, ");
		query.append("job_type VARCHAR2(64), ");
		query.append("salary NUMBER(8, 2), ");
		query.append("primary key (id))");
		
		// Grant access to outside
		String special = "GRANT SELECT ON job TO PUBLIC";
		try {
			stmt = dbconn.createStatement();
            stmt.executeQuery(query.toString());
            System.out.println("create job table successfully");
		    stmt.executeQuery(special);
		} catch (SQLException e) {
            System.err.println("*** SQLException:  "
                + "Could not fetch query results. (Error happen when create job table)");
            System.err.println("\tMessage:   " + e.getMessage());
            System.err.println("\tSQLState:  " + e.getSQLState());
            System.err.println("\tErrorCode: " + e.getErrorCode());
            System.exit(-1);
		}
	}
	
	/*
	 * create department table
	 */
	private static void departmentTable(Connection dbconn) {
		Statement stmt = null;
		
		StringBuilder query = new StringBuilder();
		query.append("CREATE table department ( ");
		query.append("departmentNumber integer, ");
		query.append("name VARCHAR2(64), ");
		query.append("primary key (number))");
		
		// Grant access to outside
		String special = "GRANT SELECT ON department TO PUBLIC";
		try {
			stmt = dbconn.createStatement();
            stmt.executeQuery(query.toString());
            System.out.println("create department table successfully");
		    stmt.executeQuery(special);
		} catch (SQLException e) {
            System.err.println("*** SQLException:  "
                + "Could not fetch query results. (Error happen when create department table)");
            System.err.println("\tMessage:   " + e.getMessage());
            System.err.println("\tSQLState:  " + e.getSQLState());
            System.err.println("\tErrorCode: " + e.getErrorCode());
            System.exit(-1);
		}
		
	}
	
	/*
	 * Create the log transaction table. The primary key are the foreign key to the appointment 
	 * table since we have to keep 1:1 relationship to each appointment. This is just in order 
	 * to match from what appointment has. 
	 */
	private static void logTable(Connection dbconn) {
		Statement stmt = null;
		
		StringBuilder query = new StringBuilder();
		query.append("CREATE table transLog ( ");
		query.append("username varchar2(64), ");
		query.append("time DATE NOT NULL, ");
		query.append("EmployeeID Integer, ");
		query.append("charge Integer, "); 
		query.append("primary key (username, time, employeeID)), ");
		query.append("FOREIGN KEY (username) REFERENCES user "
				+ "ON DELETE CASCADE ON UPDATE CASCADE, ");
		query.append("FOREIGN KEY (EmployeeID) REFERENCES employee "
				+ "ON UPDATE CASCADE ON DELETE CASCADE) "); 

		// Grant access to outside
		String special = "GRANT SELECT ON department TO PUBLIC";
		try {
			stmt = dbconn.createStatement();
            stmt.executeQuery(query.toString());
            System.out.println("create department table successfully");
		    stmt.executeQuery(special);
		} catch (SQLException e) {
            System.err.println("*** SQLException:  "
                + "Could not fetch query results. (Error happen when create department table)");
            System.err.println("\tMessage:   " + e.getMessage());
            System.err.println("\tSQLState:  " + e.getSQLState());
            System.err.println("\tErrorCode: " + e.getErrorCode());
            System.exit(-1);
		}
		
	}
	
	
}
