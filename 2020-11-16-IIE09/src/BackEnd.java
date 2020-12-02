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
	// number
	// of entries the user can enter.
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
	// changes
	// the length of the Record[] array.
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
}