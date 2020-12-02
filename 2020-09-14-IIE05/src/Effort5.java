public class Effort5 {
	public static void main(String[] args) {
		GTerm gt = new GTerm(720, 480);
		gt.setBackgroundColor(61, 255, 51);

		// Declarations of array names and data types
		// Each data type was chosen to most efficiently handle its relative data from
		// user input.
		// Array names are all plural as they represent multiple values of variable to
		// be input.
		String[] driverNames;
		int[] licNumbers;
		char[] licClasss;
		double[] vehicleWeights;
		int[] licExpirys;

		// Appropriate table size to display the amount of output data.
		// Used \t to separate the columns of the table
		gt.addTable(600, 100, "Driver Name\tLicense Number\tLicense Class\tVehicle Weight\tLicense Expiry?");

		// declaring variable to count the of records the user will be choosing to
		// enter.
		// I choose int as the data type for this as it will accept a large array of
		// values and numRecords as the variable name as this could represent anything
		// from 1+ values.
		int numRecords = 0;

		// The input string from the user will
		String input = gt
				.getInputString("Please enter the number of applications\n" + "you would like to apply for today:");

		// This line creates a prompt to the user if they have not entered any value.
		// The while loop is created to continue asking for a valid input being 1+, will
		// loop if nothing or 0 is entered.
		while (input.isEmpty() || Integer.parseInt(input) <= 0) {
			input = gt.getInputString("You have to enter at least 1 application to proceed");
		}
		// if the user enter a valid input this if statement will then run changing the
		// variable numRecords value to the number the user has chosen. Parsing the
		// string input
		// to integer for allocation to numRecords int variable.
		if (input != null) {
			numRecords = Integer.parseInt(input);
		}

		// Creations of arrays to allocate to memory
		// Assigning numRecords value from user input to create number of index in each
		// array.
		driverNames = new String[numRecords];
		licNumbers = new int[numRecords];
		licClasss = new char[numRecords];
		vehicleWeights = new double[numRecords];
		licExpirys = new int[numRecords];

		// This count integer is created to keep track and limit the number of entries
		// the user
		// performs inside the while loop below.
		int count = 0;

		// While loop that takes user input for table data and limits loops to
		// numRecords variable chosen above.
		while (count < numRecords) {
			String rawInput = gt.getInputString("Please enter these values using a comma between, no spaces:\n"
					+ "Driver Name, License Number, License Class (character displayed on license i.e. C), "
					+ "Vehicle Weight (in tonnes i.e. 1.3), License Expiry Year (i.e 2020)");

			// This section splits the user input string and allocates it to the appropriate
			// array.
			String[] fieldsofInput = rawInput.split(",");
			driverNames[count] = fieldsofInput[0];
			licNumbers[count] = Integer.parseInt(fieldsofInput[1]);
			licClasss[count] = fieldsofInput[2].charAt(0);
			vehicleWeights[count] = Double.parseDouble(fieldsofInput[3]);
			licExpirys[count] = Integer.parseInt(fieldsofInput[4]);

			// This line adds 1 to the value of count for each loop.
			count++;
		}

		// Re-declaring count to perform same loop cycle to add each entry to table.
		count = 0;

		while (count < numRecords) {
			// This takes the array data gathered from the user input and places it
			// in the appropriate column of the table.
			gt.addRowToTable(0, driverNames[count] + "\t" + licNumbers[count] + "\t" + licClasss[count] + "\t"
					+ vehicleWeights[count] + "\t" + licExpirys[count]);

			// this line adds 1 to the value of the count variable to limit the loops to the
			// user chosen in numRecords.
			count = count + 1;
		}

	}
}
