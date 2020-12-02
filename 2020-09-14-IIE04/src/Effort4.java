public class Effort4 {
	public static void main(String[] args) {
		GTerm gt = new GTerm(720, 480);
		gt.setBackgroundColor(61, 255, 51);
		
		//this creates the table columns an titles each column.
		gt.addTable(600, 100, "Driver Name\tLicense Number\tLicense Class\tVehicle Weight\tLicense Expiry?");
	
		
		//basic input data type for number with no decimal.
		//variable name chosen to easily identify. No other reference to appNumbers will be made. 
		int appNumber = Integer.parseInt(gt.getInputString(
				"How many times have you applied for a registration this year?\n" + "Enter a value and hit enter."));
		
		//if clause to determine if has exceeded max allowed applications. 
		if (appNumber > 10) {
			gt.clear();
			gt.showMessageDialog("You have exceeded the maximum number of vehicle registrations\n"
					+ "in the calander year. We are sorry for the inconvenience");
		}
		//this is the data type most easily used to count attempts/entries the user makes.
		int numEntries = 0;
		
		//this while loop is created to allow users up to three entries or attempts.  
		while (appNumber < 10 && numEntries < 3) {
			
			
			gt.setXY(0, 300);
			gt.println("You are eligible to apply for a new registration, Please fill out the required information.");
			
			//this input string is used to easily grab user data and later parsed to each data type and given
			//variable names.
			String inputRaw = gt.getInputString("Please enter these values using a comma between them:\n"
					+ "Driver Name, License Number, License Class (character displayed on license i.e. C), "
					+ "Vehicle Weight (in tonnes i.e. 1.3), License Expiry Year (i.e 2020)");
			
			//this separates the string input from the user by looking for commas
			String[] tableFields = inputRaw.split(",");
			
			//string input best for names
			//variable name chosen to best suit the field.
			String driverName = tableFields[0];
			
			//integer selected to take user data of multiple digits with no decimal. 
			//variable name chosen as its short and understandable. 
			int licNumber = Integer.parseInt(tableFields[1]);
			
			//simple single character input.
			//variable name chosen as its short and understandable.
			char licClass = tableFields[2].charAt(0);
			
			//inputs of 2 digits with a decimal place.
			//variable name chosen as its short and understandable.
			double vehicleWeight = Double.parseDouble(tableFields[3]);
			
			//integer selected to take user input of the year year.
			////variable name chosen as its short and understandable.
			int licExpiry = Integer.parseInt(tableFields[4]);
			
			
			//This line inputs the variable data gathered from the user input and places it in the appropriate column.
			gt.addRowToTable(0,
					driverName + "\t" + licNumber + "\t" + licClass + "\t" + vehicleWeight + "\t" + licExpiry);
			
			//this line adds 1 to the value of the pre-defined COUNTER to contribute to the max of 3.
			numEntries = numEntries + 1;
		}
	}
}
