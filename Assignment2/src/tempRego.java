
// Imports the Scanner utility for Java to scan and add the user string for 
// the use of the console.
import java.util.Scanner;

public class tempRego {

	// These are member variables that can be shared across methods within the class.
	// Arrays and variables stored outside of the public methods to be accessed by any.
	// Declarations of array names and data types.
	// Each data type was chosen to most efficiently handle its relative data from user input.
	// Array names are all plural as they represent multiple values of that variable type to be input.
	private GTerm gt;
	private String[] driverNames;
	private int[] licNumbers;
	private char[] licClasss;
	private double[] vehicleWeights;
	private int[] licExpirys;
	private int currentRecord;
	private int maxRecords;

	// This adds the Scanner utility to the main class to be used for the console component.
	private Scanner input;

	// This is a constructor used to create GTerm window, adds GTerm components (i.e. buttons and table),
	// adds styling, text, and position of components in window.
	// Also takes inputs 1 or 0 from public static void main to output using GTerm window or 
	// or console.
	public tempRego(int userInterfaceMode) {

		// if 0 from public static void main this if statement forces the use of the console. 
		if (userInterfaceMode == 0) {

			input = new Scanner(System.in);
			System.out.print("Please enter the number of applications\n" + "you would like to apply for today:");
			this.maxRecords = input.nextInt();
			while (this.maxRecords <= 0) {
				System.out.print("You have to enter at least 1 application to proceed");
				this.maxRecords = input.nextInt();
			}
			System.out.println("You entered integer " + this.maxRecords);

			// Initialization of arrays.
            // Parsing each split data of the user entry and setting the index number of each array
			// using the maxRecords variable.
			this.driverNames = new String[this.maxRecords];
			this.licNumbers = new int[this.maxRecords];
			this.licClasss = new char[this.maxRecords];
			this.vehicleWeights = new double[this.maxRecords];
			this.licExpirys = new int[this.maxRecords];

			// Displays options to user in the console. 
			int choice = 0;
			while (choice != -1) {
				System.out.println("Select from below:");
				System.out.print(
						"1 - Add record | 2 - Display records | 3 - Edit record | 4 - Remove record | -1 - Exit");
				choice = input.nextInt();

				if (choice == 1)
					consoleAddRecord();
				else if (choice == 2)
					consoleDisplayRecords();
				else if (choice == 3)
					consoleEditRecord();
				else if (choice == 4)
					consoleRemoveRecord();
				else if (choice != -1)
					System.out.println("Invalid selection....!");
			}
			System.out.println("Exiting...");
			
	    //This else statement is if the public static void main input is 1 then run the script using GTerm.
		} else {

			// GTerm window setting, style and size.
			this.gt = new GTerm(977, 800);
			this.gt.setFontSize(14);
			this.gt.setFontColor(0, 0, 0);
			this.gt.setBackgroundColor(204, 204, 204);
			
			//
			this.gt.addImageIcon("logo.png");

			// This adds table to GTerm, sets table XY position and size.
			// Using \t to separate column breaks.
			this.gt.setXY(180, 220);
			this.gt.addTable(600, 300, "Driver Name\tLicense Number\tLicense Class\tVehicle Weight\tLicense Expiry");

			// This section adds the text to display to the user and adds spaces between lines where necessary.
			// It also adds a text field with a given size.
			this.gt.println("");
			this.gt.println("");
			this.gt.print("Please enter these values using a comma between each entry.\n"
					+ "First Name,License Number,License Class,Vehicle Weight,License Expiry");
			this.gt.println("");
			this.gt.setFontColor(204, 0, 0);
			this.gt.println("Example: Paul, 652431, C, 1.3, 2025");
			this.gt.setFontColor(0, 0, 0);
			this.gt.println("");
			this.gt.addTextField("", 500);
			this.gt.println("");

			
			// This adds the three buttons to the GTerm window.
			// addRecord is the last button in the sequence so that when the user hits enter while 
			// in the GTerm window it will default to this button. 
			this.gt.println("");
			this.gt.addButton("Edit", this, "editRecord");
			this.gt.print("");
			this.gt.addButton("Remove", this, "removeRecord");
			this.gt.println("");
			this.gt.println("");
			this.gt.addButton("Add", this, "addRecord");

			// This section asks the user to enter the number of desired records to then 
			// limit the loop to that number later. 
			String input = this.gt
					.getInputString("Please enter the number of applications\n" + "you would like to apply for today:");

			// The while loop is created to continue asking for a valid input being 1+.
			// Will loop if null or 0 is entered.
			while (input.isEmpty() || Integer.parseInt(input) <= 0) {
				input = this.gt.getInputString("You have to enter at least 1 application to proceed");
			}
			
			// If the user enters a valid input this if statement will then change the value of the
			// maxRecords variable to the number the user has chosen. Parsing the string input
			// to integer for allocation to maxRecords integer variable.
			if (input != null) {
				this.maxRecords = Integer.parseInt(input);
			}
            
			// Used to display debug info in the console. To check is the while loop and validation
			// has assigned an appropriate value to the maxRecords variable. 
			System.out.println("Number of records selected: " + this.maxRecords);

			// Initialize arrays to allocate to memory
			// Assigning maxRecords value from user input to create number of index in each array.
			this.driverNames = new String[this.maxRecords];
			this.licNumbers = new int[this.maxRecords];
			this.licClasss = new char[this.maxRecords];
			this.vehicleWeights = new double[this.maxRecords];
			this.licExpirys = new int[this.maxRecords];

		}
	}

	// Displays options in console.
	public void consoleAddRecord() {
		System.out.println("1: Add Record selected!");
	}
	public void consoleDisplayRecords() {
		System.out.println("2: Display Records selected!");
	}

	public void consoleEditRecord() {
		System.out.println("3: Edit Records selected!");
	}
	public void consoleRemoveRecord() {
		System.out.println("4: Remove Records selected!");
	}

	// These are the three methods for the buttons added to the GTerm window earlier in the constructor. 
	// This is the removeRecord button, Used to prompt user to first select a record then click Remove. 
	public void removeRecord() {

		int rowIndex = this.gt.getIndexOfSelectedRowFromTable(0);
		System.out.println(rowIndex); // debugging
		if (rowIndex >= 0) {

			removeRecordFromArrays(rowIndex);
			refreshTable();
		}

		else
			this.gt.showWarningDialog("Select record from table to remove, then select Remove");
	}

	
 
	// This is the editRecord button, Used to prompt user to first select a record, 
	// write in the text field then click edit. 
	public void editRecord() {

		int rowIndex = this.gt.getIndexOfSelectedRowFromTable(0);
		if (rowIndex >= 0) {

			String input = this.gt.getTextFromEntry(0);
			if (input != null && !input.isBlank()) {

				String[] record = validateInput(input);
				this.driverNames[rowIndex] = record[0];
				this.licNumbers[rowIndex] = Integer.parseInt(record[1]);
				this.licClasss[rowIndex] = record[2].charAt(0);
				this.vehicleWeights[rowIndex] = Double.parseDouble(record[3]);
				this.licExpirys[rowIndex] = Integer.parseInt(record[4]);

				refreshTable();
				this.gt.setTextInEntry(0, "");
			} else
				this.gt.showWarningDialog("Enter new record inforamtion:\n"
						+ "First Name,License Number,License Class,Vehicle Weight,License Expiry\n"
						+ "Then select Edit to make the changes.");
		} else
			this.gt.showWarningDialog("Select record from table to edit, then select Edit");
	}

	// This is the addRecord button.
	// When user clicks this they are prompted to write in the text field. 
	// Each time user clicks on Add they are given a prompt as to what number record they are
	// entering and of how many records they have chosen. 
	public void addRecord() {
		
		// This if-else statement contains the code to request the data from the user to later be split and 
		// parsed into the arrays.
		if (this.currentRecord < this.maxRecords) {

			String input = this.gt.getTextFromEntry(0);
			if (input != null && !input.isBlank()) {
				
				// Declaring each "this.variable" a value based from the String[] arrays index 
				// taken from the split user input.
				String[] record = validateInput(input);
				this.driverNames[this.currentRecord] = record[0];
				this.licNumbers[this.currentRecord] = Integer.parseInt(record[1]);
				this.licClasss[this.currentRecord] = record[2].charAt(0);
				this.vehicleWeights[this.currentRecord] = Double.parseDouble(record[3]);
				this.licExpirys[this.currentRecord] = Integer.parseInt(record[4]);

				// Used to display info in the console. 
				// To check if the user values have been entered or split correctly. 
				System.out.println(this.driverNames[this.currentRecord] + "," + this.licNumbers[this.currentRecord]
						+ "," + this.licClasss[this.currentRecord] + "," + this.vehicleWeights[this.currentRecord] + ","
						+ this.licExpirys[this.currentRecord]);
				this.currentRecord++;
				refreshTable();
				this.gt.setTextInEntry(0, "");
				
			// Brings up a window asking the user to ender the current record number and also shows them 
			// the total number of records chosen.
 			} else
				this.gt.showWarningDialog("Enter Record " + (this.currentRecord + 1) + " of " + (this.maxRecords));
		
		// This else tells the user they have entered the maximum number of records and asks 
	    // if they would like to enter any more. 
		} else {

			this.gt.showWarningDialog("You have reached your selcted number of records = " + (this.maxRecords));
			String input = this.gt.getInputString("Would you like to add more entries?\n" + "Yes/No");
			if (input.equalsIgnoreCase("yes"));
			input = this.gt.getInputString("Enter new number of records:");
			if (input != null && !input.isBlank()) {
				int numRecords = Integer.parseInt(input);
				if (numRecords > 0)
					expandRecordArrays(numRecords);
				
				// Used to display info in the console. 
				// To check if the max records value is correct.
				System.out.println(this.maxRecords);
			}
		}
	}

	public String[] validateInput(String input) {

		String[] record = input.split("\\s*,\\s*");

		// Validation of driverName
		while (record[0].equals(""))
			record[0] = this.gt.getInputString("Enter a valid FIRST NAME, ie. Paul");
		// Validation of  licNumber
		while (record[1].equals(""))
			record[1] = this.gt.getInputString("Enter a valid LICENSE NUMBER, i.e.276635");
		// Validation of licClass
		while (record[2].equals(""))
			record[2] = this.gt.getInputString("Enter a valid LICENSE CLASS, i.e C");
		// Validation of vehicleWeight
		while (record[3].equals(""))
			record[3] = this.gt.getInputString("Enter a valid VEHICLE WEIGHT in tonnes," + " i.e 1.3");
		// Validation of licExpiry
		while (record[4].equals(""))
			record[4] = this.gt.getInputString("Enter a valid LICENSE EXpiry, i.e 2020");

		return record;
	}

	// This method simply refreshes the table to add, remove or edit entries.
	// This method is called and used by the three button methods, Add/Edit/Remove
	public void refreshTable() {

		this.gt.clearRowsOfTable(0);
		int i = 0;
		while (i < this.currentRecord) {

			this.gt.addRowToTable(0, driverNames[i] + "\t" + licNumbers[i] + "\t" + licClasss[i] + "\t"
					+ vehicleWeights[i] + "\t" + licExpirys[i]);
			i++;
		}
	}

	// This method is called by the addRecord button method when more entries are required to be entered
	// beyond the value the user originally requested.  
	public void expandRecordArrays(int numRecords) {

		String[] driverNames = new String[this.driverNames.length + numRecords];
		int[] licNumbers = new int[this.licNumbers.length + numRecords];
		char[] licClasss = new char[this.licClasss.length + numRecords];
		double[] vehicleWeights = new double[this.vehicleWeights.length + numRecords];
		int[] licExpirys = new int[this.licExpirys.length + numRecords];

		int i = 0;
		while (i < this.driverNames.length) {

			driverNames[i] = this.driverNames[i];
			licNumbers[i] = this.licNumbers[i];
			licClasss[i] = this.licClasss[i];
			vehicleWeights[i] = this.vehicleWeights[i];
			licExpirys[i] = this.licExpirys[i];

			i++;
		}

		this.driverNames = driverNames;
		this.licNumbers = licNumbers;
		this.licClasss = licClasss;
		this.vehicleWeights = vehicleWeights;
		this.licExpirys = licExpirys;
		this.maxRecords += numRecords;
	}

	// This method is used called by the removeRecord button to delete a particular index number 
	// from each array, representing a row. 
	public void removeRecordFromArrays(int index) {

		String[] driverNames = new String[this.driverNames.length - 1];
		int[] licNumbers = new int[this.licNumbers.length - 1];
		char[] licClasss = new char[this.licClasss.length - 1];
		double[] vehicleWeights = new double[this.vehicleWeights.length - 1];
		int[] licExpirys = new int[this.licExpirys.length - 1];

		boolean found = false;
		int i = 0, j = 0;
		while (i < this.driverNames.length) {

			if (!found && i == index) {

				i++;
				found = true;
			}

			if (j < driverNames.length) {

				driverNames[j] = this.driverNames[i];
				driverNames[j] = this.driverNames[i];
			}

			i++;
			j++;
		}

		if (found) {

			this.driverNames = driverNames;
			this.licNumbers = licNumbers;
			this.licClasss = licClasss;
			this.vehicleWeights = vehicleWeights;
			this.licExpirys = licExpirys;
			
			// This line removes 1 from the current record count.
			this.currentRecord--; 
		}

	}
	
    // This "main" method creates an object instance of the the class tempRego and uses the 
	// constructor Effort6() to setup the program with 1/0 being chosen to run either console 
	// or GTerm version of the program. 
	public static void main(String[] args) {
		
		tempRego prmObj = new tempRego(1);
	}
}