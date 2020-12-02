import java.awt.Color;

public class IvanIIE05 {	

	public static void main(String[] args) {

		GTerm gt = new GTerm(600, 400);
		gt.setXY(70, 50);
		gt.setFontSize(16);
		gt.setFontColor(Color.ORANGE);
		gt.setBackgroundColor(Color.DARK_GRAY);
		
	//  Ensure that your program first asks the user how many records (numRecords) they would like to process. 
			// Then declare an array reference of an appropriate data type for each field of your records.
			// If your code took inputs for 5 different pieces of information for each record, you must have 5 separate arrays
			// INITIALIZE:  create the actual arrays based on the numRecords specified by the user for each of the arrays
			// Modify your addRowToTable to add these same values to the table as you did in IIE04
			
			//  Investigative exercise: calc and show the average of one of the numerical fields/columns is shown...
			// find lowest stored value and display only that record in the table
		
		String[] names;
		int[] ages;
		int averageAge;
		
		gt.addTable(200, 200, "name\tage"); // use tab character \t to denote row column breaks
		
				
		int numRecords = 0;
		String input = gt.getInputString("Number of records (integer): ");
		if(input != null) {			
			
			numRecords = Integer.parseInt(input);
			while(numRecords <= 0) {
				input = gt.getInputString("ERROR: Invalid number of records. Re-enter (integer): ");
				if(input != null) numRecords = Integer.parseInt(input);
			}
			
			System.out.println("numRecords: " + numRecords); // debug
			
			// initialize arrays			
			names = new String[numRecords];
			ages = new int[numRecords];
			
			averageAge = 0;
			
			// get records from user			
			int currentRecord = 0;
			while(currentRecord < numRecords) {
				
				//you can assume that the user entered in something and of the correct type...
				String[] record = gt.getInputString("Enter record " + (currentRecord + 1) + ": name,age -- NO SPACES!").split(",");
				
				// validate name
				while(record[0].equals("")) 
					record[0] = gt.getInputString("ERROR: Invalid NAME (string). Re-enter: ");
				names[currentRecord] = record[0];
				
				// validate age
				while(record[1].equals("")) // OPTIONAL... can assume user is not a twit
					record[0] = gt.getInputString("ERROR: Invalid AGE (int). Re-enter: ");
				while(Integer.parseInt(record[1]) <= 0)
					record[1] = gt.getInputString("ERROR: AGE (int) must be positive number. Re-enter: ");
				ages[currentRecord] = Integer.parseInt(record[1]);
				
				System.out.println(names[currentRecord] + "," + ages[currentRecord]); // debug
				
				//gt.addRowToTable(0, names[currentRecord] + "\t" + ages[currentRecord]);
				
				averageAge += ages[currentRecord];
				currentRecord++;
			}
			
			// adding records to table
			int i = 0;
			while(i < numRecords) {
				
				gt.addRowToTable(0, names[i] + "\t" + ages[i]);
				i++;
			}
			
			// calc and show average
			averageAge /= numRecords;
			gt.println("averageAge: " + averageAge);
			
			
			// calc and show minValue, get index of record with min value
			int minValue = ages[0];
			int minValueIndex = 0; // this stores index of record with min value
			int y = 0;
			while(y < ages.length) {
				if(ages[y] < minValue) {
					minValue = ages[y];
					minValueIndex = y;
				}
				y++;
			}
			gt.println("minValue " + minValueIndex + ": " + minValue);
			
		} 
		else {
			
			gt.showWarningDialog("User cancelled...");
			gt.close();
		}
	}
}