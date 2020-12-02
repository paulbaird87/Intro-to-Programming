public class Effort6 {

	// These are member variables that can be shared across methods within the
	// class.
	// Arrays and variables declared and stored outside of the public method.
	// Declarations of array names and data types.
	// Each data type was chosen to most efficiently handle its relative data from
	// user input.
	// Array names are all plural as they represent multiple values of variable to
	// be input.
	private GTerm gt;
	private String[] driverNames;
	private int[] licNumbers;
	private char[] licClasss;
	private double[] vehicleWeights;
	private int[] licExpirys;
	private int currentRecord;
	private int maxRecords;

	// This is a constructor used to create GTerm window, adds GTerm components
	// (i.e. buttons), prompts user to
	// enter a number of records required which and forces user to use correct
	// value, it then passes this value
	// to the addRecord method to then input the table data. 
	public Effort6() {

		// GTerm window styling and size
		this.gt = new GTerm(720, 480);
		this.gt.setFontSize(14);
		this.gt.setBackgroundColor(61, 255, 51);

		// Appropriate table size to display the amount of output data.
		// Used \t to separate the columns of the table
		// Add button to GTerm window.
		this.gt.setXY(60, 50);
		this.gt.addTable(600, 300, "Driver Name\tLicense Number\tLicense Class\tVehicle Weight\tLicense Expiry");
		this.gt.setXY(300, 410);
		this.gt.addButton("Add Record", this, "addRecord");

	

		// The input string from the user will limit the number of loops for number of records to input.
		String input = this.gt
				.getInputString("Please enter the number of applications\n" + "you would like to apply for today:");

		
		// This line creates a prompt to the user if they have not entered any value.
		// The while loop is created to continue asking for a valid input being 1+, will
		// loop if nothing or 0 is entered.
		while (input.isEmpty() || Integer.parseInt(input) <= 0) {
			input = this.gt.getInputString("You have to enter at least 1 application to proceed");
		}
		// if the user enter a valid input this if statement will then run changing the
		// variable numRecords value to the number the user has chosen. Parsing the
		// string input
		// to integer for allocation to numRecords integer variable.
		if (input != null) {
			this.maxRecords = Integer.parseInt(input);
		}

		System.out.println("numRecords: " + this.maxRecords); // debug

		// Initialize arrays to allocate to memory
		// Assigning maxRecords value from user input to create number of index in each
		// array.
		this.driverNames = new String[this.maxRecords];
		this.licNumbers = new int[this.maxRecords];
		this.licClasss = new char[this.maxRecords];
		this.vehicleWeights = new double[this.maxRecords];
		this.licExpirys = new int[this.maxRecords];

	}

	// This method adds records to arrays.
	public void addRecord() {
		// 1. Take comma separated inputs of a record
		// 2. Split the input
		// 3. Add the split input in to the array, increment currentNumRecords
		// 4. Call refreshTable

		// This loop contains the code to request the data from the user to later be
		// split and parsed into the arrays.
		if (this.currentRecord < this.maxRecords) {

			// This will bring up a text field to instruct user what and how to enter data.
			String[] record = this.gt.getInputString("Enter Record " + (this.currentRecord + 1) + " of "
					+ (this.maxRecords) + "\nPlease enter these values using a comma between and no spaces:\n"
					+ "Driver Name, License Number, License Class (character displayed on license i.e. C), "
					+ "Vehicle Weight (in tonnes i.e. 1.3), License Expiry Year (i.e 2020)").split(",");

			// This section splits the user input string and allocates it to the appropriate
			// array.

			this.driverNames[this.currentRecord] = record[0];
			this.licNumbers[this.currentRecord] = Integer.parseInt(record[1]);
			this.licClasss[this.currentRecord] = record[2].charAt(0);
			this.vehicleWeights[this.currentRecord] = Double.parseDouble(record[3]);
			this.licExpirys[this.currentRecord] = Integer.parseInt(record[4]);

			// This line adds 1 to the value of count for each loop.
			this.currentRecord++;

			// This is calling on a method named refreshTable and it will clear and add to
			// table from new array data.
			refreshTable();

		}

		// warning dialogue to inform user they have reached the maximum number of
		// applications.
		else
			this.gt.showWarningDialog("Maximum entries reached = " + (this.maxRecords));
	}

	public void refreshTable() {

		// Clear the rows of the table.
		// Loop through the arrays and add each record to the table.
		this.gt.clearRowsOfTable(0);
		int i = 0;
		while (i < this.currentRecord) {

			// This takes the array data gathered from the user input and places it
			// in the appropriate column of the table.
			this.gt.addRowToTable(0, driverNames[i] + "\t" + licNumbers[i] + "\t" + licClasss[i] + "\t"
					+ vehicleWeights[i] + "\t" + licExpirys[i]);

			i++;
		}

	}

	public static void main(String[] args) {
		// This "main" method now creates an object instance of the the class Effort6
		// and uses
		// the constructor Effort6() to setup the program
		Effort6 prmObj = new Effort6();
	}
}
 