public class Week5Example {
	public static void main(String[] args) {
		GTerm gt = new GTerm(600, 400);
		gt.setFontSize(16);
		int maxNumStudents = 1; // Integer.parseInt(gt.getInputString("Please enter number of students to

// process"));

// Declarations
		String[] firstNames;
// Creations (allocates memory)
		firstNames = new String[maxNumStudents];
		gt.addTable(500, 300, "First name\tLast name\tInitial\tYear\tGPA\tUnion?");
		int currentNumStudents = 0;
		String rawInput = gt.getInputString(

				"Student " + (currentNumStudents + 1)
						+ "\nEnter first name,last name,middle initial,year joined,GPA,is union?");

		while (rawInput != null) {
			String[] fieldsOfStudent = rawInput.split(",");
			String firstName = fieldsOfStudent[0];
			while (firstName.isBlank())
				firstName = gt.getInputString("Must enter something for first name");
// If we have reached the maximum capacity of the array
			if (currentNumStudents >= maxNumStudents) {
// Change numStudents to what we want
// Note: It does not change the original array's length
				maxNumStudents *= 2;
				gt.showMessageDialog("Expanding to " + maxNumStudents);
				String[] longerFirstNames = new String[maxNumStudents];
// String[] longerLastNames...
// double[] longerGPAs...
				int j = 0;
				while (j < currentNumStudents) { // Alternatively while(j<firstNames.length)
					longerFirstNames[j] = firstNames[j];
// longerLastNames..
// longerGPAs...
					j += 1;
				}
				firstNames = longerFirstNames;
// lastNames=longerLastNames;
// GPAs=longerGPAs;
			}
			firstNames[currentNumStudents] = firstName;
			String message = firstNames[currentNumStudents];
			gt.addRowToTable(0, message);
			currentNumStudents += 1;
			rawInput = gt.getInputString(

					"Student " + (currentNumStudents + 1)
							+ "\nEnter first name,last name,middle initial,year joined,GPA,is union?");

		}
// ----------------------------------
	}
}