
// This is what the user sees and interacts with. Contains the GTerm windows, text, 
// warning dialogues and input windows.
public class FrontEndGTerm {

	private GTerm gt;
	private BackEnd backEnd;

	// GTerm version of program.
	public FrontEndGTerm(BackEnd backEnd) {

		this.backEnd = backEnd;

		// GTerm window setting, style and size.
		this.gt = new GTerm(977, 850);
		this.gt.setFontSize(16);
		this.gt.setFontColor(0, 0, 0);
		this.gt.setBackgroundColor(204, 204, 204);

		// Loads image from root directory into GTerm window
		this.gt.addImageIcon("logo.png");

		// This adds table to GTerm, sets table XY position and size.
		// Using \t to separate column breaks.
		this.gt.setXY(180, 220);
		this.gt.addTable(600, 300, "Driver Name\tLicense Number\tLicense Class\tVehicle Weight" + "\tLicense Expiry");

		// These two buttons are added to the GTerm window directly below the table so the user understand 
		// that they are saving and loading the table information. 
		this.gt.println("");
		this.gt.addButton("Load", this, "loadContestant");
		this.gt.print(" ");
		this.gt.addButton("Save", this, "saveContestant");
		this.gt.println("");

		// This section adds the text to display to the user and adds spaces between
		// lines where necessary.
		// It also adds a text field with a given size.
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

		// This adds the three more buttons to the GTerm window.
		// addRecord is the last button in the sequence so that when the user hits enter
		// while in the GTerm window it will default to this button, handy for when the user finishes
		// typing there input data and can simply hit enter and start typing a new entry. 
		this.gt.println("");
		this.gt.addButton("Edit", this, "editRecord");
		this.gt.print(" ");
		this.gt.addButton("Remove", this, "removeRecord");
		this.gt.println("");
		this.gt.println("");
		this.gt.addButton("Add", this, "addRecord");
		this.gt.println("");
		
		// The first thing the user will see. A friendly welcome pop up, giving the user the options of loading from 
		// previously saved file or to enter the amount of applications they want processed. 
		String input = this.gt.getInputString("                                                            "
				+ "Welcome ( Í¡Â° ÍœÊ– Í¡Â°)"
				+ "\n\nIf you would like to load previously saved applications select 'Cancel' then select 'Load'"
				+ "\n\nIf this is your first time please type the number of applications you would like to apply for:"
				+ "\n ");

		while (input.isEmpty() || Integer.parseInt(input) <= 0) {
			input = this.gt.getInputString("     !............You must enter a valid number............!"
					+ "\nHow many registrations would you like to apply for?");
		}

		if (input != null) {
			int numRecords = Integer.parseInt(input);
			this.backEnd.expandRecords(numRecords);

		}
	}

//Loads saved applications details from file.
	public void loadContestant() {

		String[] arrayOfStrings = this.backEnd.loadFile(this.gt.getFilePath());
		this.backEnd.updateContestant(arrayOfStrings);
		refreshTable();
	}

	public void saveContestant() {

		String[] arrayofStrings = new String[this.backEnd.getRecordCount()];

		int i = 0;
		while (i < arrayofStrings.length) {
			arrayofStrings[i] = (this.backEnd.getRecord(i).getdriverNames() + ","
					+ this.backEnd.getRecord(i).getlicNumbers() + "," + this.backEnd.getRecord(i).getlicClasss() + ","
					+ this.backEnd.getRecord(i).getvehicleWeights() + "," + this.backEnd.getRecord(i).getlicExpirys());
			i++;

		}

		if (this.backEnd.saveFile(arrayofStrings, "./SavedApplcations.txt")) {
			this.gt.showMessageDialog("Applications Saved Successfuly ( Í¡Â° ÍœÊ– Í¡Â°)"
					+ "\nWhen returning select the 'Load' button to retrieve your saved applications.");
		} else
			this.gt.showMessageDialog("File failed to save, please try again");
	}

	// GTerm button method for removing a record. Calls and sends data to the
	// removeRecord()
	// public method in BackEnd.
	public void removeRecord() {

		int rowIndex = this.gt.getIndexOfSelectedRowFromTable(0);
		System.out.println(rowIndex); // debugging
		if (rowIndex >= 0) {

			this.backEnd.removeRecord(rowIndex);
			refreshTable();
		} else
			this.gt.showWarningDialog("Select record from table to remove, then select Remove");
	}

	// GTerm button method for editing a record. Calls and sends data to the
	// editRecord()
	// public method in BackEnd.
	public void editRecord() {

		int rowIndex = this.gt.getIndexOfSelectedRowFromTable(0);
		if (rowIndex >= 0) {

			String input = this.gt.getTextFromEntry(0);
			if (input != null && !input.isBlank()) {

				this.backEnd.editRecord(rowIndex, validateInput(input));

				refreshTable();
				this.gt.setTextInEntry(0, "");
			} else
				this.gt.showWarningDialog("Enter new record inforamtion:\n"
						+ "First Name,License Number,License Class,Vehicle Weight,License Expiry\n"
						+ "Then select Edit to make the changes.");
		} else
			this.gt.showWarningDialog("Select record from table to edit, then select Edit");
	}

	// GTerm button method for adding a record. Calls from data to the
	// addRecord() public method in BackEnd and sends changes made to variable
	// numRecords
	// which in turn expands the maxRecords variable and increases the array index.
	public void addRecord() {

		// if statement to check if the current record value is less than the max record
		// value, if so
		// then the table is refreshed with the new entry. If not the else statement
		// below with take affect.
		if (this.backEnd.getRecordCount() < this.backEnd.getMaxRecords()) {

			String input = this.gt.getTextFromEntry(0);
			if (input != null && !input.isBlank()) {

				this.backEnd.addRecord(validateInput(input));

				refreshTable();
				this.gt.setTextInEntry(0, "");

			}
		}

		// This tells the user they have entered the maximum number of records and asks
		// if they would like to
		// enter any more.
		else {
			this.gt.showWarningDialog("You have entered (" + (this.backEnd.getRecordCount()) + ") of the maximum ("
					+ (this.backEnd.getMaxRecords()) + ") applications selected!");

			String input = this.gt.getInputString("If you would you like to apply for more registrations; "
					+ "\nType: Yes" + "\nor" + "\nSelect cancel");

			// This while boolean is checking to see if the user typed Yes, if the user did
			// not they prompted again.
			while (!input.equalsIgnoreCase("yes")) {
				input = this.gt.getInputString("You must type yes to proceed or select cancel");
			}
			// If the user typed yes then the number of entries is asked and the numRecords
			// variable
			// is adjusted and in turn record array and maxRecords is adjusted.
			if (input.equalsIgnoreCase("yes")) {

				input = this.gt.getInputString("Enter new number of applications to enter:");

				if (input != null && !input.isBlank()) {
					int numRecords = Integer.parseInt(input);
					if (numRecords > 0)
						this.backEnd.expandRecords(numRecords);

				}
			}
		}
	}

	// This calls the refreshTable() method in BackEnd to refresh table after
	// adding, editing or
	// removing a record.
	public void refreshTable() {

		this.gt.clearRowsOfTable(0);
		int i = 0;
		while (i < this.backEnd.getRecordCount()) {

			this.gt.addRowToTable(0, this.backEnd.getRecord(i).getdriverNames() + "\t"
					+ this.backEnd.getRecord(i).getlicNumbers() + "\t" + this.backEnd.getRecord(i).getlicClasss() + "\t"
					+ this.backEnd.getRecord(i).getvehicleWeights() + "\t" + this.backEnd.getRecord(i).getlicExpirys());

			i++;
		}
	}

	// Validates the user input in the case that null value is input by user for
	// any of the array values in the string split.
	public Registration validateInput(String input) {

		// This .split() helps ignore any spaces that the user may use while entering
		// their record.
		String[] record = input.split("\\s*,\\s*");

		// Validation of driverName
		while (record[0].equals(""))
			record[0] = this.gt.getInputString("Enter a valid FIRST NAME, ie. Paul");
		// Validation of licNumber
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

		return new Registration(record[0], Integer.parseInt(record[1]), record[2].charAt(0),
				Double.parseDouble(record[3]), Integer.parseInt(record[4]));
	}

}
