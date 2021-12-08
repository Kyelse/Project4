
/*
 * FILE: BasicFunction.java
 * AUTHORS: QUANG VU, QUAN NGUYEN, MINH TRAN
 * CLASS: CSC 460 FALL 2021
 * ASSIGNMENT: Project 4
 * PURPOSE: This file will implement all the basic functionalities of the program
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class BasicFunction {

	/*
	 * This function will take in the scanner from the main menu that call it and
	 * use the scanner to work with JDBC
	 */
	public static void selection(Scanner input, Connection dbconn) {
		String option = "options";
		while (!option.toLowerCase().equals("back")) {
			if (!isOptionValid(option)) {
				System.out.println("Invalid input! Input can only be back, options"); // add more options here
				option = input.nextLine();
			} else if (option.equalsIgnoreCase("options")) {
				System.out.println("Basic Functionalities");
				System.out.println("Please select one option below");
				System.out.println("1.  Add				Client");
				System.out.println("2.  Update			Client");
				System.out.println("3.  Delete			Client");
				System.out.println("4.  Add				Employee");
				System.out.println("5.  Update			Employee");
				System.out.println("6.  Delete			Employee");
				System.out.println("7.  Add				Appointment");
				System.out.println("8.  Update			Appointment");
				System.out.println("9.  Delete			Appointment");
				System.out.println("10. Add				Service");
				System.out.println("11. Update			Service");
				System.out.println("12. Delete			Service");
				System.out.println("13. Add, Update			License");
				System.out.println("14. Add, Update			Permit");
				System.out.println("15. Add, Update			Registration");
				System.out.println("17. Add, Update			StateID");

				System.out.println("back to back to the main menu");
				option = input.nextLine();
				switch (option) {
				case "1":
					addClient(input, dbconn);
					break;
				case "2":
					updateClient(input, dbconn);
					break;
				case "3":
					deleteClient(input, dbconn);
					break;
				case "4":
					addEmployee(input, dbconn);
					break;
				case "5":
					updateEmployee(input, dbconn);
					break;
				case "6":
					deleteEmployee(input, dbconn);
					break;
				case "7":
					addAppointment(input, dbconn);
					break;
				case "8":
					updateAppointment(input, dbconn); 
					break;
				case "9":
					deleteAppointment(input, dbconn);
					break;
				case "10":
					addService(input, dbconn);
					break;
				case "11":
					addService(input, dbconn); 
					break;
				case "12":
					deleteService(input, dbconn); 
					break;
				case "13":
					changeLicense(input, dbconn);
					break;
				case "14":
					changePermit(input, dbconn);
					break;
				case "15":
					changeRegistration(input, dbconn); 
					break;
				case "16":
					changeStateID(input, dbconn); 
					break;
				}
			}
		}
	}
	/**
	 * Adding a new user base on user input. This will ask all of the neccesary
	 * information from the user in order to put in.
	 * 
	 * @param input
	 * @param dbconn
	 * @param dbconn the connection to the SQL server
	 */
	private static void changeLicense(Scanner input, Connection dbconn) {
		System.out.println("Please type in the username: ");
		String username = input.nextLine();
		if (!checkUserName(dbconn, username)) {
			System.out.println("The user does not exist in the database");
			return;
		}
		System.out.println("Please type in issued date: ");
		String time = input.nextLine();
		if (!isValidDate("dd/MM/yyyy", time)) {
			System.out.println("Wrong form.");
			return;
		}
		System.out.println("Please type in current/new license number"); 
		String license = input.nextLine();
		
		Statement stmt = null;
		try {
			stmt = dbconn.createStatement();
			stmt.executeUpdate("DELETE from license WHERE username = '" + username + "'");
			stmt.executeUpdate("INSERT INTO license value('" + username + "', '" + time + "', " + license + "'");
			System.out.println("Add successfully");
			stmt.close();

			// Shut down the connection to the DBMS.

		} catch (SQLException e) {
			System.err.println("*** SQLException:  " + "Could not fetch query results.");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);
		}
	}
	/**
	 * Adding a new user base on user input. This will ask all of the neccesary
	 * information from the user in order to put in.
	 * 
	 * @param input
	 * @param dbconn
	 * @param dbconn the connection to the SQL server
	 */
	private static void changeRegistration(Scanner input, Connection dbconn) {
		System.out.println("Please type in the username: ");
		String username = input.nextLine();
		if (!checkUserName(dbconn, username)) {
			System.out.println("The user does not exist in the database");
			return;
		}
		System.out.println("Please type in issued date: ");
		String time = input.nextLine();
		if (!isValidDate("dd/MM/yyyy", time)) {
			System.out.println("Wrong form.");
			return;
		}
		System.out.println("Please type in current new registration number"); 
		String rnum = input.nextLine();
		System.out.println("Please type in current new make"); 
		String make = input.nextLine();
		System.out.println("Please type in current new color"); 
		String color = input.nextLine();
		Statement stmt = null;
		try {
			stmt = dbconn.createStatement();
			stmt.executeUpdate("DELETE from vehicle WHERE registration number = '" + rnum + "'");
			stmt.executeUpdate("INSERT INTO vehicle value('" + username + "', '" + rnum+ "', '" + time + "', '" + make + "', '" + color + "'");
			System.out.println("Add successfully");
			stmt.close();

			// Shut down the connection to the DBMS.

		} catch (SQLException e) {
			System.err.println("*** SQLException:  " + "Could not fetch query results.");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);
		}
	}
	/**
	 * Adding a new user base on user input. This will ask all of the neccesary
	 * information from the user in order to put in.
	 * 
	 * @param input
	 * @param dbconn
	 * @param dbconn the connection to the SQL server
	 */
	private static void changePermit(Scanner input, Connection dbconn) {
		System.out.println("Please type in the username: ");
		String username = input.nextLine();
		if (!checkUserName(dbconn, username)) {
			System.out.println("The user does not exist in the database");
			return;
		}
		System.out.println("Please type in issued date: ");
		String time = input.nextLine();
		if (!isValidDate("dd/MM/yyyy", time)) {
			System.out.println("Wrong form.");
			return;
		}
		System.out.println("Please type in current/new permit number"); 
		String permit = input.nextLine();
		
		Statement stmt = null;
		try {
			stmt = dbconn.createStatement();
			stmt.executeUpdate("DELETE from permit WHERE username = '" + username + "'");
			stmt.executeUpdate("INSERT INTO permit value('" + username + "', '" + time + "', " + permit + "'");
			System.out.println("Add successfully");
			stmt.close();

			// Shut down the connection to the DBMS.

		} catch (SQLException e) {
			System.err.println("*** SQLException:  " + "Could not fetch query results.");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);
		}
	}
	/**
	 * Adding a new user base on user input. This will ask all of the neccesary
	 * information from the user in order to put in.
	 * 
	 * @param input
	 * @param dbconn
	 * @param dbconn the connection to the SQL server
	 */
	private static void changeStateID(Scanner input, Connection dbconn) {
		System.out.println("Please type in the username: ");
		String username = input.nextLine();
		if (!checkUserName(dbconn, username)) {
			System.out.println("The user does not exist in the database");
			return;
		}
		System.out.println("Please type in issued date: ");
		String time = input.nextLine();
		if (!isValidDate("dd/MM/yyyy", time)) {
			System.out.println("Wrong form.");
			return;
		}
		System.out.println("Please type in current/new stateID number"); 
		String stateID = input.nextLine();
		
		Statement stmt = null;
		try {
			stmt = dbconn.createStatement();
			stmt.executeUpdate("DELETE from permit WHERE username = '" + username + "'");
			stmt.executeUpdate("INSERT INTO permit value('" + username + "', '" + time + "', " + stateID + "'");
			System.out.println("Add successfully");
			stmt.close();

			// Shut down the connection to the DBMS.

		} catch (SQLException e) {
			System.err.println("*** SQLException:  " + "Could not fetch query results.");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);
		}
	}
	/**
	 * Adding a new user base on user input. This will ask all of the neccesary
	 * information from the user in order to put in.
	 * 
	 * @param input
	 * @param dbconn
	 * @param dbconn the connection to the SQL server
	 */
	private static void deleteClient(Scanner input, Connection dbconn) {
		System.out.println("Please type in the username: ");
		String username = input.nextLine();
		if (!checkUserName(dbconn, username)) {
			System.out.println("The user is does exist in the database");
			return;
		}
		Statement stmt = null;
		try {
			stmt = dbconn.createStatement();
			String query = "DELETE from client WHERE username = '" + username + "'";
			stmt.executeUpdate(query);
			System.out.println("Add successfully");
			stmt.close();

			// Shut down the connection to the DBMS.

		} catch (SQLException e) {

			System.err.println("*** SQLException:  " + "Could not fetch query results.");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);

		}

	}

	/**
	 * Adding a new user base on user input. This will ask all of the neccesary
	 * information from the user in order to put in.
	 * 
	 * @param input
	 * @param dbconn
	 * @param dbconn the connection to the SQL server
	 */
	private static void deleteEmployee(Scanner input, Connection dbconn) {
		System.out.print("Please type in EmployeeID: ");
		String eid = input.nextLine();
		if (!checkEID(dbconn, eid)) {
			System.out.println("I am sorry, this employee does not exists");
			return;
		}
		Statement stmt = null;
		try {
			stmt = dbconn.createStatement();
			String query = "DELETE from employee WHERE employeeID = '" + eid + "'" ;
			stmt.executeUpdate(query);
			System.out.println("Add successfully");

			// Shut down the connection to the DBMS.

			stmt.close();

		} catch (SQLException e) {

			System.err.println("*** SQLException:  " + "Could not fetch query results.");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);

		}

	}

	/**
	 * Adding a new user base on user input. This will ask all of the neccesary
	 * information from the user in order to put in.
	 * 
	 * @param input
	 * @param dbconn
	 * @param dbconn the connection to the SQL server
	 */
	private static void deleteAppointment(Scanner input, Connection dbconn) {
		System.out.println("Please type in the username: ");
		String username = input.nextLine();
		if (!checkUserName(dbconn, username)) {
			System.out.println("There is no user with that username exist");
			return;
		}
		System.out.println("Please type in the time(DD/MM/YYYY): ");
		String time = input.nextLine();
		if (!isValidDate("dd/MM/yyyy", time)) {
			System.out.println("Wrong form.");
			return;
		}
		if (!checkAppointment(dbconn, username, time)) {
			System.out.println(
					"I'm sorry, an appointment of '" + username + "' at " + time + " does not exist in the database");
			return;
		}
		Statement stmt = null;
		try {
			stmt = dbconn.createStatement();
			String query = "DELETE from appointment WHERE username = '" + username + "'";
			stmt.executeUpdate(query);
			System.out.println("Add successfully");

			// Shut down the connection to the DBMS.

			stmt.close();

		} catch (SQLException e) {

			System.err.println("***Something wrong here***");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);

		}
	}

	/**
	 * Adding a new user base on user input. This will ask all of the necessary
	 * information from the user in order to put in.
	 * 
	 * @param input
	 * @param dbconn
	 * @param dbconn the connection to the SQL server
	 */
	private static void deleteService(Scanner input, Connection dbconn) {
		System.out.println("Please type in the service type: ");
		String service = input.nextLine();
		if (checkService(dbconn, service)) {
			System.out.println("Service already exist");
			return;
		}
		Statement stmt = null;
		try {
			stmt = dbconn.createStatement();
			String query = "DELETE FROM service where service_type = '" + service + "'";
			stmt.executeUpdate(query);
			System.out.println("Add service successfully");
			// Shut down the connection to the DBMS.
			stmt.close();

		} catch (SQLException e) {

			System.err.println("*** SQLException:  " + "Could not fetch query results.");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);
		}
	}


	/**
	 * Adding a new user base on user input. This will ask all of the neccesary
	 * information from the user in order to put in.
	 * 
	 * @param input
	 * @param dbconn
	 * @param dbconn the connection to the SQL server
	 */
	private static void addClient(Scanner input, Connection dbconn) {
		System.out.println("Please type in the username: ");
		String username = input.nextLine();
		if (checkUserName(dbconn, username)) {
			System.out.println("The user is already exist in the database");
			return;
		}
		Statement stmt = null;
		try {
			stmt = dbconn.createStatement();
			System.out.print("Please type in the DOB(DD/MM/YYYY): ");
			String dob = input.nextLine();
			if (!isValidDate("dd/MM/yyyy", dob)) {
				System.out.println("Wrong form.");
				return;
			}
			System.out.print("Please type in the First Name: ");
			String fname = input.nextLine();
			System.out.print("Please type in the Last Name: ");
			String lname = input.nextLine();
			String query = "INSERT INTO client value ('" + username + "'," + "'" + fname + "'," + "'" + lname + "')";
			stmt.executeUpdate(query);
			System.out.println("Add successfully");
			stmt.close();

			// Shut down the connection to the DBMS.

		} catch (SQLException e) {

			System.err.println("*** SQLException:  " + "Could not fetch query results.");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);

		}

	}

	/**
	 * Adding a new user base on user input. This will ask all of the neccesary
	 * information from the user in order to put in.
	 * 
	 * @param input
	 * @param dbconn
	 * @param dbconn the connection to the SQL server
	 */
	private static void addEmployee(Scanner input, Connection dbconn) {
		System.out.print("Please type in EmployeeID: ");
		String eid = input.nextLine();
		if (checkEID(dbconn, eid)) {
			System.out.println("I am sorry, this employee already exists");
			return;
		}
		System.out.print("Please type in jobID: ");
		String jid = input.nextLine();
		if (!checkJob(dbconn, jid)) {
			System.out.println("I am sorry, this job does not exists");
			return;
		}
		System.out.print("Please type in Department Number: ");
		String dno = input.nextLine();
		if (!checkDepartment(dbconn, dno)) {
			System.out.println("I am sorry, this department does not exists");
			return;
		}
		Statement stmt = null;
		try {
			stmt = dbconn.createStatement();
			System.out.print("Please type in the First Name: ");
			String fname = input.nextLine();
			System.out.print("Please type in the Last Name: ");
			String lname = input.nextLine();
			String query = "INSERT INTO employee value ('" + eid + "'," + "'" + jid + "'," + "'" + dno + "'," + "'"
					+ fname + "'," + "'" + lname + "')";
			stmt.executeUpdate(query);
			System.out.println("Add successfully");

			// Shut down the connection to the DBMS.

			stmt.close();

		} catch (SQLException e) {

			System.err.println("*** SQLException:  " + "Could not fetch query results.");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);

		}

	}

	/**
	 * Adding a new user base on user input. This will ask all of the neccesary
	 * information from the user in order to put in.
	 * 
	 * @param input
	 * @param dbconn
	 * @param dbconn the connection to the SQL server
	 */
	private static void addAppointment(Scanner input, Connection dbconn) {
		System.out.println("Please type in the username: ");
		String username = input.nextLine();
		if (!checkUserName(dbconn, username)) {
			System.out.println("There is no user with that username exist");
			return;
		}
		System.out.println("Please type in the time(DD/MM/YYYY): ");
		String time = input.nextLine();
		if (!isValidDate("dd/MM/yyyy", time)) {
			System.out.println("Wrong form.");
			return;
		}
		if (checkAppointment(dbconn, username, time)) {
			System.out.println(
					"I'm sorry, an appointment of '" + username + "' at " + time + "is already exist in the database");
			return;
		}
		Statement stmt = null;
		try {
			stmt = dbconn.createStatement();
			System.out.print("Please type in EmployeeID: ");
			String eid = input.nextLine();
			if (!checkEID(dbconn, eid)) {
				System.out.println("I am sorry, this employee does not exist");
				return;
			}
			System.out.println("Please type in the service needed: ");
			String service = input.nextLine();
			if (!checkService(dbconn, service)) {
				System.out.println("The service does not exist");
				return;
			}
			String query = "INSERT INTO appointment value ('" + username + "'," + "'" + time + "'," + "'" + eid + "',"
					+ "'" + service + "'," + "'" + "0" + "'," + "0" + "')";
			stmt.executeUpdate(query);
			System.out.println("Add successfully");

			// Shut down the connection to the DBMS.

			stmt.close();

		} catch (SQLException e) {

			System.err.println("***Something wrong here***");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);

		}
	}

	/**
	 * Adding a new user base on user input. This will ask all of the necessary
	 * information from the user in order to put in.
	 * 
	 * @param input
	 * @param dbconn
	 * @param dbconn the connection to the SQL server
	 */
	private static void addService(Scanner input, Connection dbconn) {
		System.out.println("Please type in the service type: ");
		String service = input.nextLine();
		if (checkService(dbconn, service)) {
			System.out.println("Service already exist");
			return;
		}
		Statement stmt = null;
		try {
			stmt = dbconn.createStatement();
			System.out.print("Please type in the price: ");
			String price = input.nextLine();
			System.out.print("Please type in the expiry length in year: ");
			String expiry = input.nextLine();
			if (isInt(price) == false || isInt(expiry) == false) {
				System.out.println("Please type only whole number for the price and the expiry length");
			}
			String query = "INSERT INTO service value ('" + service + "'," + "'" + price + "'," + "'" + expiry + "')";
			stmt.executeUpdate(query);
			System.out.println("Add service successfully");
			// Shut down the connection to the DBMS.
			stmt.close();

		} catch (SQLException e) {

			System.err.println("*** SQLException:  " + "Could not fetch query results.");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);
		}
	}

	/**
	 * Adding a new user base on user input. This will ask all of the neccesary
	 * information from the user in order to put in.
	 * 
	 * @param input
	 * @param dbconn
	 * @param dbconn the connection to the SQL server
	 */
	private static void updateClient(Scanner input, Connection dbconn) {
		System.out.println("Please type in the username: ");
		String username = input.nextLine();
		if (!checkUserName(dbconn, username)) {
			System.out.println("There are no user like that");
			return;
		}
		Statement stmt = null;
		try {
			stmt = dbconn.createStatement();
			System.out.print("Please type in the DOB(DD/MM/YYYY): ");
			String dob = input.nextLine();
			if (!isValidDate("dd/MM/yyyy", dob)) {
				System.out.println("Wrong form.");
				return;
			}
			System.out.print("Please type in the First Name: ");
			String fname = input.nextLine();
			System.out.print("Please type in the Last Name: ");
			String lname = input.nextLine();
			String query = "UPDATE client set fname = '" + fname + "', lname = '" + lname + "' WHERE username = '"
					+ username + "'";
			stmt.executeUpdate(query);
			System.out.println("Update successfully");
			stmt.close();

			// Shut down the connection to the DBMS.

		} catch (SQLException e) {

			System.err.println("*** SQLException:  " + "Could not fetch query results.");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);

		}

	}

	/**
	 * Adding a new user base on user input. This will ask all of the neccesary
	 * information from the user in order to put in.
	 * 
	 * @param input
	 * @param dbconn
	 * @param dbconn the connection to the SQL server
	 */
	private static void updateEmployee(Scanner input, Connection dbconn) {
		System.out.print("Please type in EmployeeID: ");
		String eid = input.nextLine();
		if (!checkEID(dbconn, eid)) {
			System.out.println("I am sorry, this employee does not exist");
			return;
		}
		System.out.print("Please type in jobID: ");
		String jid = input.nextLine();
		if (!checkJob(dbconn, jid)) {
			System.out.println("I am sorry, this job does not exist");
			return;
		}
		System.out.print("Please type in Department Number: ");
		String dno = input.nextLine();
		if (!checkDepartment(dbconn, dno)) {
			System.out.println("I am sorry, this job does not exist");
			return;
		}
		Statement stmt = null;
		try {
			stmt = dbconn.createStatement();
			System.out.print("Please type in the First Name: ");
			String fname = input.nextLine();
			System.out.print("Please type in the Last Name: ");
			String lname = input.nextLine();
			String query = "UPDATE employee set job_id = '" + jid + "', departmentNumber = " + "'" + dno
					+ "', fname =  " + "'" + fname + "', lname = " + "'" + lname + "' WHERE employee.employeeID = '"
					+ eid + "'";
			stmt.executeUpdate(query);
			System.out.println("Add successfully");

			// Shut down the connection to the DBMS.

			stmt.close();

		} catch (SQLException e) {

			System.err.println("*** SQLException:  " + "Could not fetch query results.");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);

		}

	}

	/**
	 * Adding a new user base on user input. This will ask all of the neccesary
	 * information from the user in order to put in.
	 * 
	 * @param input
	 * @param dbconn
	 * @param dbconn the connection to the SQL server
	 */
	private static void updateAppointment(Scanner input, Connection dbconn) {
		System.out.println("Please type in the username: ");
		String username = input.nextLine();
		if (!checkUserName(dbconn, username)) {
			System.out.println("There is no user with that username exist");
			return;
		}
		System.out.println("Please type in the time(DD/MM/YYYY): ");
		String time = input.nextLine();
		if (!isValidDate("dd/MM/yyyy", time)) {
			System.out.println("Wrong form.");
			return;
		}
		if (!checkAppointment(dbconn, username, time)) {
			System.out.println("I'm sorry, an appointment of '" + username + "' at " + time + "does not exist.");
			return;
		}
		Statement stmt = null;
		ResultSet answer = null;
		try {
			stmt = dbconn.createStatement();
			System.out.println("Please type in the new time(DD/MM/YYYY): ");
			String newTime = input.nextLine();
			if (!isValidDate("dd/MM/yyyy", newTime)) {
				System.out.println("Wrong form.");
				return;
			}
			System.out.print("Please type in new EmployeeID: ");
			String eid = input.nextLine();
			if (!checkEID(dbconn, eid)) {
				System.out.println("I am sorry, this employee does not exist");
				return;
			}
			if (!checkAppointment(dbconn, username, newTime)) {
				System.out.println("I'm sorry, an appointment of '" + username + "' at " + newTime + "does not exist.");
				return;
			}
			System.out.println("Please type in the new service needed: ");
			String service = input.nextLine();
			if (!checkService(dbconn, service)) {
				System.out.println("The service does not exist");
				return;
			}
			answer = stmt.executeQuery("SELECT price FROM service WHERE service_type = '" + service + "'");
			answer.next();
			String price = String.valueOf(answer.getInt("price"));
			System.out.println("Please indicate if the appointment is success or not (1 or 0) ");
			String success = input.nextLine();
			if (!success.equals("0") || !success.equals("1")) {
				System.out.println("Only 1 for true and 0 for false");
				return;
			}
			System.out.println("Please indicate if the apppointment is processed or not (1 or 0)");
			String process = input.nextLine();
			if (!process.equals("0") || !process.equals("1")) {
				System.out.println("Only 1 for true and 0 for false");
				return;
			}
			if (success.equals("1") && process.equals("0")) {
				System.out.println("The appointment should be processed first");
				return;
			}
			if (success.equals("1") && process.equals("1")) {
				String query = "INSERT INTO logs value ('" + username + "', '" + newTime + "', '" + eid + "', '" + price+ "')";
				stmt.executeUpdate(query);
			}
			String query = "DELETE from appointment WHERE username = '" + username + "' AND '" + "time = '" + time + "'" ;
			stmt.executeUpdate(query);
			query = "INSERT INTO appointment value ('" + username + "'," + "'" + time + "'," + "'" + eid + "',"
					+ "'" + service + "'," + "'" + success + "'," + process + "')";
			stmt.executeUpdate(query);
			System.out.println("Add successfully");

			// Shut down the connection to the DBMS.

			stmt.close();

		} catch (SQLException e) {

			System.err.println("***Something wrong here***");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);

		}
	}

	/**
	 * Adding a new user base on user input. This will ask all of the necessary
	 * information from the user in order to put in.
	 * 
	 * @param input
	 * @param dbconn
	 * @param dbconn the connection to the SQL server
	 */
	private static void updateService(Scanner input, Connection dbconn) {
		System.out.println("Please type in the service type: ");
		String service = input.nextLine();
		if (checkService(dbconn, service)) {
			System.out.println("Service already exist");
			return;
		}
		Statement stmt = null;
		try {
			stmt = dbconn.createStatement();
			System.out.print("Please type in the price: ");
			String price = input.nextLine();
			System.out.print("Please type in the expiry length in year: ");
			String expiry = input.nextLine();
			if (isInt(price) == false || isInt(expiry) == false) {
				System.out.println("Please type only whole number for the price and the expiry length");
			}
			String query = "UPDATE service set price = '" + price + "', " + "expiry_length = '" + expiry
					+ "' WHERE service_type = '" + service + "'";
			stmt.executeUpdate(query);
			System.out.println("Add service successfully");
			// Shut down the connection to the DBMS.
			stmt.close();

		} catch (SQLException e) {

			System.err.println("*** SQLException:  " + "Could not fetch query results.");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);
		}
	}

	/**
	 * Check in the database if an employee with a certain ID number exist or not.
	 * 
	 * @param dbconn the connection to the SQL server
	 * @param eid    the id number of the employee
	 * @return indiciate if the employee exist
	 */
	private static boolean checkAppointment(Connection dbconn, String username, String time) {
		// Send the query to the DBMS, and get and display the results

		Statement stmt = null;
		ResultSet answer = null;
		String queryCheck = "SELECT count(*) as checking, username as 'UserName', time as 'Appointment Tame', EmployeeID, service_type as 'Service', Success, Process FROM appointment WHERE appointment.username = '"
				+ username + "' AND appointment.time = '" + time + "'";
		try {
			stmt = dbconn.createStatement();
			answer = stmt.executeQuery(queryCheck);
			if (answer != null) {
				answer.next();
				int count = answer.getInt("checking");
				if (count == 0) {
					return false;
				}
			}
			printQueryMetaData(answer);
			while (answer.next()) {
				System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s\n", answer.getInt("UserName"),
						answer.getDate("Appointment Time"), answer.getInt("EmployeeID"), answer.getString("Service"),
						answer.getInt("Success"), answer.getInt("Process"));
			}
			stmt.close();
		} catch (SQLException e) {
			System.err.println("*** SQLException:  " + "Could not fetch query results.");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);

		}
		return true;
	}

	/**
	 * Check in the database if an employee with a certain ID number exist or not.
	 * 
	 * @param dbconn the connection to the SQL server
	 * @param eid    the id number of the employee
	 * @return indiciate if the employee exist
	 */
	private static boolean checkEID(Connection dbconn, String eid) {
		// Send the query to the DBMS, and get and display the results

		Statement stmt = null;
		ResultSet answer = null;
		String queryCheck = "SELECT count(*) as checking, employeeID as 'EmployeeID', fname as 'First Name', lname as 'Last Name' FROM employee WHERE employee.EmployeeId = '"
				+ eid + "'";
		try {
			stmt = dbconn.createStatement();
			answer = stmt.executeQuery(queryCheck);
			if (answer != null) {
				answer.next();
				int count = answer.getInt("checking");
				if (count == 0) {
					return false;
				}
			}
			printQueryMetaData(answer);
			while (answer.next()) {
				System.out.printf("%-15s %-15s %-15s\n", answer.getInt("EmployeeID"), answer.getString("First Name"),
						answer.getString("Last Name"));
			}
			stmt.close();
		} catch (SQLException e) {
			System.err.println("*** SQLException:  " + "Could not fetch query results.");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);

		}
		return true;
	}

	/**
	 * Check in the database if a user with a certain username exist or not.
	 * 
	 * @param dbconn the connection to the SQL server
	 * @param eid    the id number of the employee
	 * @return indiciate if the employee exist
	 */
	private static boolean checkUserName(Connection dbconn, String username) {
		// Send the query to the DBMS, and get and display the results

		Statement stmt = null;
		ResultSet answer = null;
		String queryCheck = "SELECT count(*) as checking, username as 'UserName', fname as 'First Name', lname as 'Last Name' FROM client WHERE client.username = '"
				+ username + "'";
		try {
			stmt = dbconn.createStatement();
			answer = stmt.executeQuery(queryCheck);
			if (answer != null) {
				answer.next();
				int count = answer.getInt("checking");
				if (count == 0) {
					return false;
				}

			}
			printQueryMetaData(answer);
			while (answer.next()) {
				System.out.printf("%-15s %-15s %-15s\n", answer.getString("UserName"), answer.getString("First Name"),
						answer.getString("Last Name"));
			}
			stmt.close();
		} catch (SQLException e) {
			System.err.println("*** SQLException:  " + "Could not fetch query results.");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);

		}
		return true;
	}

	/**
	 * Check in the database if a user with a certain username exist or not.
	 * 
	 * @param dbconn the connection to the SQL server
	 * @param eid    the id number of the employee
	 * @return indiciate if the employee exist
	 */
	private static boolean checkJob(Connection dbconn, String job_ID) {
		// Send the query to the DBMS, and get and display the results

		Statement stmt = null;
		ResultSet answer = null;
		String queryCheck = "SELECT count(*) as checking, job_id as 'Job ID', job_type as 'Job Type', 'job_title' as 'Title', salary as 'Salary' FROM job WHERE job.job_id = '"
				+ job_ID + "'";
		try {
			stmt = dbconn.createStatement();
			answer = stmt.executeQuery(queryCheck);
			if (answer != null) {
				answer.next();
				int count = answer.getInt("checking");
				if (count == 0) {
					return false;
				}
			}
			printQueryMetaData(answer);
			while (answer.next()) {
				System.out.printf("%-15s %-15s %-15s %-15s\n", answer.getInt("Job ID"), answer.getString("Job Type"),
						answer.getString("Job Title"), answer.getInt("Salary"));

			}
			stmt.close();
		} catch (SQLException e) {
			System.err.println("*** SQLException:  " + "Could not fetch query results.");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);

		}
		return true;
	}

	/**
	 * Check in the database if a user with a certain username exist or not.
	 * 
	 * @param dbconn the connection to the SQL server
	 * @param eid    the id number of the employee
	 * @return indiciate if the employee exist
	 */
	private static boolean checkDepartment(Connection dbconn, String departmentNumber) {
		// Send the query to the DBMS, and get and display the results

		Statement stmt = null;
		ResultSet answer = null;
		String queryCheck = "SELECT count(*) as checking, departmentNumber as 'Department Number', name as 'Department Name' FROM department WHERE department.departmentNumber = '"
				+ departmentNumber + "'";
		try {
			stmt = dbconn.createStatement();
			answer = stmt.executeQuery(queryCheck);
			if (answer != null) {
				answer.next();
				int count = answer.getInt("checking");
				if (count == 0) {
					return false;
				}
			}
			printQueryMetaData(answer);
			while (answer.next()) {
				System.out.printf("%-15s %-15s\n", answer.getInt("Department Number"),
						answer.getString("Department Name"));
			}
			stmt.close();
		} catch (SQLException e) {
			System.err.println("*** SQLException:  " + "Could not fetch query results.");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);

		}
		return true;
	}

	/**
	 * Check in the database if a user with a certain username exist or not.
	 * 
	 * @param dbconn the connection to the SQL server
	 * @param eid    the id number of the employee
	 * @return indiciate if the employee exist
	 */
	private static boolean checkService(Connection dbconn, String service) {
		// Send the query to the DBMS, and get and display the results

		Statement stmt = null;
		ResultSet answer = null;
		String queryCheck = "SELECT count(*) as checking, service_type as 'Service', price as 'Price', expiry_length as 'Expiry Duration (year)' FROM service WHERE service.service_type = '"
				+ service + "'";
		try {
			stmt = dbconn.createStatement();
			answer = stmt.executeQuery(queryCheck);
			if (answer != null) {
				answer.next();
				int count = answer.getInt("checking");
				if (count == 0) {
					return false;
				}
			}
			while (answer.next()) {
				System.out.printf("%-15s %-15s %-15s\n", answer.getString("Service"), answer.getInt("Price"),
						answer.getInt("Expiry Duration (year)"));
			}
			stmt.close();
		} catch (SQLException e) {
			System.err.println("*** SQLException:  " + "Could not fetch query results.");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);

		}
		return true;
	}

	/**
	 * A wrapper to print out metadata of an answer. This piece of code is being
	 * used many times so it is good to put it here.
	 * 
	 * @param answer
	 */
	private static void printQueryMetaData(ResultSet answer) {
		// Using printf to format the answer
		try {
			ResultSetMetaData answermetadata = answer.getMetaData();
			for (int i = 2; i <= answermetadata.getColumnCount(); i++) {
				System.out.printf("%-15s", answermetadata.getColumnName(i));
			}
		} catch (SQLException e) {

			System.err.println("*** SQLException:  " + "Could not fetch query results.");
			System.err.println("\tMessage:   " + e.getMessage());
			System.err.println("\tSQLState:  " + e.getSQLState());
			System.err.println("\tErrorCode: " + e.getErrorCode());
			System.exit(-1);

		}
	}

	/**
	 * A helper function for validating the date time format. Format helped the
	 * program to identify which format to use.
	 * 
	 * @param format
	 * @param value
	 * @return
	 */
	public static boolean isValidDate(String format, String value) {
		SimpleDateFormat parsing = new SimpleDateFormat("dd/mm/yyyy");
		parsing.setLenient(false);
		try {
			parsing.parse(value);
		} catch (Exception e) {
			return false;
		}
		return true;

	}

	/**
	 * A little wrapper function to check if the string is an integer
	 * representation.
	 * 
	 * @param number
	 * @return true or false depends on the string representation
	 */
	public static boolean isInt(String number) {
		try {
			Integer.parseInt(number);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/*
	 * Check if useriput is valid
	 */
	private static boolean isOptionValid(String option) {
		String[] valid = new String[] { "back", "options" }; // add more options here
		for (String op : valid) {
			if (option.toLowerCase().equals(op))
				return true;
		}
		return false;
	}
}
