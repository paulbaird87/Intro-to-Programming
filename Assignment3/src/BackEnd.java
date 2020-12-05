
// This import java.io gives the java program the ability to read and write
// from a local file. 
import java.io.*;

public class BackEnd {
	// array of record objects to store all maxRecords number of applications
	// and each of the variables indexed data to be collected from user.
	private Registration[] registrations;
	// currentRecord variable is declared here and will be used as a counter to
	// monitor the current number of entries the user has made.
	private int currentRecord;
	// maxRecords variable is declared here and is used to set a maximum amount of
	// entries the user would like to enter.
	private int maxRecords;

	public BackEnd() {

		// Initializing records array and local variables. maxRecords initially set at
		// 1 but user will have the option to expand later.
		this.registrations = new Registration[this.maxRecords];
		this.currentRecord = 0;
		this.maxRecords = 0;
	}

	// This is a get method for the records[] array. length of array is established
	// by the index variable.
	public Registration getRecord(int index) {

		// This if statement is used to set the length of the index array.
		if (index < this.registrations.length) {
			return registrations[index];
		} else
			return null;
	}

	// This is a get method for currentRecord variable.
	public int getRecordCount() {
		return this.currentRecord;
	}

	// This is a get method for the maxRecords variable. This sets the maximum
	// number of entries the user can enter.
	public int getMaxRecords() {
		return this.maxRecords;
	}

	// This is a get method used by the add record button.
	public void addRecord(Registration registration) {

		this.registrations[this.currentRecord] = registration;
		this.currentRecord++;

	}

	// This is a get method used by the edit record button.
	public void editRecord(int index, Registration registration) {

		if (index < this.registrations.length)
			this.registrations[index] = registration;

	}

	// This is a get method for expanding the number of records in the table. Also
	// changes the length of the Record[] array.
	public void expandRecords(int numRecords) {

		Registration[] records = new Registration[this.registrations.length + numRecords];

		int i = 0;
		while (i < this.registrations.length) {

			records[i] = this.registrations[i];
			i++;
		}

		this.registrations = records;
		this.maxRecords += numRecords;
	}

	// This is a get method used by the Remove record button.
	public void removeRecord(int index) {

		// This changes the length of the Record[] array.
		Registration[] records = new Registration[this.registrations.length - 1];

		boolean found = false;
		int i = 0, j = 0;
		while (i < this.registrations.length) {

			if (!found && i == index) {

				i++;
				found = true;
			}

			if (j < records.length) {

				records[j] = this.registrations[i];
			}

			i++; // adds one to records[] array
			j++; // adds one to this.records[] array.
		}

		if (found) {

			this.registrations = records;
			this.currentRecord--; // Removes one value form the currentRecords variable.
			this.maxRecords--; // Removes one value form the macRecords variable.
		}

	}

	// Updates the registration array details and counts.
	public void updateRegistrations(String[] arrayOfStrings) {
		this.registrations = new Registration[arrayOfStrings.length];
		this.maxRecords = arrayOfStrings.length;
		this.currentRecord = 0;

		int i = 0;
		while (i < arrayOfStrings.length) {

			this.registrations[i] = parseApplications(arrayOfStrings[i]);
			this.currentRecord++;
			i++;
		}

	}

	// This loads the the user chosen data file.
	public String[] loadFile(String filePath) {

		String fileText = "";
		BufferedReader inFile = null;

		try {

			inFile = new BufferedReader(new FileReader(filePath));
			String currLine = inFile.readLine();
			while (currLine != null) {

				fileText += currLine + "|";
				currLine = inFile.readLine();

			}
			inFile.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return fileText.split("\\|");
	}

	// This parses the data from the chosen load file to the table.
	public Registration parseApplications(String data) {

		String[] parsedData = data.split(",");
		Registration registrations = new Registration(parsedData[0], Integer.parseInt(parsedData[1]),
				parsedData[2].charAt(0), Double.parseDouble(parsedData[3]), Integer.parseInt(parsedData[4]));
		return registrations;

	}

	// This save the user input data to root directory SavedApplcations.txt
	public Boolean saveFile(String[] arrayOfStrings, String filePath) {
		boolean fileSaved = false;

		BufferedWriter outFile = null;
		try {
			outFile = new BufferedWriter(new FileWriter(filePath));
			int i = 0;
			while (i < arrayOfStrings.length) {
				outFile.write(arrayOfStrings[i] + "\n");
				i++;
			}
			outFile.close();
			fileSaved = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return fileSaved;
	}

	// This updates the Registration class arrays.
	public void updateDetails(int index, Registration registrations) {
		this.registrations[index] = registrations;
	}

}