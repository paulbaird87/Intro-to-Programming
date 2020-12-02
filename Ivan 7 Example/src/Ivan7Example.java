import java.awt.Color;
import java.util.Scanner;

public class Ivan7Example {
	
	private GTerm gt;
	private String[] names;
	private int[] ages;
	private int currentRecord;
	private int maxRecords;
	
	private Scanner input;
	
	public Ivan7Example(int userInterfaceMode) {		
		
		//if 0 use console only input
		if(userInterfaceMode == 0) {
			
			input = new Scanner(System.in);
			System.out.print("How many records to enter (int): ");
			this.maxRecords = input.nextInt();
			while(this.maxRecords <= 0) {
				System.out.print("ERROR: Invalid number of records. Re-enter (integer): ");
				this.maxRecords = input.nextInt();
			}
	        System.out.println("You entered integer " + this.maxRecords); 
	        
	     // initialize arrays			
			this.names = new String[this.maxRecords];
			this.ages = new int[this.maxRecords];
			
			// menu system
			int choice = 0;
			while(choice != -1) {
				System.out.println("Please choose from the following options:");
				System.out.print("1 - Add record | 2 - Display records | 3 - Edit record | 4 - Remove record | -1 - Exit");
				choice = input.nextInt();
				
				if(choice == 1) consoleAddRecord();
				else if (choice == 2) consoleDisplayRecords();
				else if (choice == 3) consoleEditRecord();
				else if (choice == 4) consoleRemoveRecord();
				else if (choice != -1) System.out.println("ERROR: Invalid choice made!");
			}
			System.out.println("Exiting...");
		}
		else {
			
			// GTerm window overall settings
			this.gt = new GTerm(600, 400);			
			this.gt.setXY(70, 50);
			this.gt.setFontSize(16);			
			this.gt.setBackgroundColor(Color.DARK_GRAY);
			
			// add table to GTerm			
			this.gt.setFontColor(Color.BLACK);
			this.gt.addTable(200, 200, "name\tage"); // use tab character \t to denote row column breaks
			
			// add text filed + labels to GTerm
			this.gt.println("");
			this.gt.setFontColor(Color.ORANGE);			
			this.gt.println("Record:");
			this.gt.print("[name,age]");
			this.gt.addTextField("", 100);
			
			// add buttons to GTerm
			this.gt.println("");
			this.gt.addButton("Add", this, "addRecord");
			this.gt.print(" ");
			this.gt.addButton("Edit", this, "editRecord");
			this.gt.print(" ");
			this.gt.addButton("Remove", this, "removeRecord");
			
			
			// get user to determine how many records will be stored
			String input = this.gt.getInputString("Number of records (integer): ");
			if(input != null) {			
				
				this.maxRecords = Integer.parseInt(input);
				while(this.maxRecords <= 0) {
					input = this.gt.getInputString("ERROR: Invalid number of records. Re-enter (integer): ");
					if(input != null) this.maxRecords = Integer.parseInt(input);
				}
				
				System.out.println("numRecords: " + this.maxRecords); // debug
				
				// initialize arrays			
				this.names = new String[this.maxRecords];
				this.ages = new int[this.maxRecords];
			} 
			else {
				
				this.gt.showWarningDialog("User cancelled...");
				this.gt.close();
			}
		}		
	}
	
	/// Console option methods
	
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
	
	/// GTerm button methods
	
	public void removeRecord() {
		
		int rowIndex = this.gt.getIndexOfSelectedRowFromTable(0);
		System.out.println(rowIndex); // debugging
		if(rowIndex >= 0) {
			
			removeRecordFromArrays(rowIndex);
			refreshTable();
		}
	}
	
	public void editRecord() {
		
		int rowIndex = this.gt.getIndexOfSelectedRowFromTable(0);
		if(rowIndex >= 0) {
			
			String input = this.gt.getTextFromEntry(0);
			if(input != null && !input.isBlank()) {
				
				String[] record = validateInput(input);
				this.names[rowIndex] = record[0];
				this.ages[rowIndex] = Integer.parseInt(record[1]);
				
				refreshTable();
				this.gt.setTextInEntry(0, "");
			}
			else this.gt.showWarningDialog("Enter new record details -- name,age -- into text field.");
		}
		else this.gt.showWarningDialog("Select record from table.");
	}
	
	public void addRecord() {
		
		if(this.currentRecord < this.maxRecords) {
			
			String input = this.gt.getTextFromEntry(0);
			if(input != null && !input.isBlank()) {
				
				String[] record = validateInput(input);
				this.names[this.currentRecord] = record[0];			
				this.ages[this.currentRecord] = Integer.parseInt(record[1]);
				
				System.out.println(this.names[this.currentRecord] + "," + this.ages[this.currentRecord]); // debug			
				
				this.currentRecord++;
				refreshTable();
				this.gt.setTextInEntry(0, "");
			}
			else this.gt.showWarningDialog("Enter record details -- name,age -- into text field.");
		}
		else {
			
			this.gt.showWarningDialog("Max (" + this.maxRecords + ") records reached.");
			String input = this.gt.getInputString("Expand records amount: ");
			if(input != null && !input.isBlank()) {
				
				int numRecords = Integer.parseInt(input);
				if(numRecords > 0) expandRecordArrays(numRecords);
				System.out.println(this.maxRecords);
			}
		}
	}
	
	public String[] validateInput(String input) {
		
		String[] record = input.split(",");
		
		// validate name
		while(record[0].equals("")) 
			record[0] = this.gt.getInputString("ERROR: Invalid NAME (string). Re-enter: ");
		// validate age
		while(record[1].equals("")) // OPTIONAL... can assume user is not a twit
			record[1] = this.gt.getInputString("ERROR: Invalid AGE (int). Re-enter: ");
		while(Integer.parseInt(record[1]) <= 0)
			record[1] = this.gt.getInputString("ERROR: AGE (int) must be positive number. Re-enter: ");
					
		return record;
	}
	
	/// Shared use methods
	
	public void refreshTable() {

		this.gt.clearRowsOfTable(0);
		int i = 0;
		while(i < this.currentRecord) {
			
			this.gt.addRowToTable(0, names[i] + "\t" + ages[i]);
			i++;
		}
	}
	
	public void expandRecordArrays(int numRecords) {
		
		String[] names = new String[this.names.length + numRecords];
		int[] ages = new int[this.ages.length + numRecords];
		
		int i = 0;
		while(i < this.names.length) {
			
			names[i] = this.names[i];
			ages[i] = this.ages[i];
			i++;
		}
		
		this.names = names;
		this.ages = ages;
		this.maxRecords += numRecords;
	}
	
	// this could be part of the removeRecord method, however, this will be shared via console ver...
	public void removeRecordFromArrays(int index) {
		
		String[] names = new String[this.names.length -1];
		int[] ages = new int[this.ages.length -1];
		
		boolean found = false;
		int i = 0, j = 0;
		while(i < this.names.length) {
			
			// if same index, skip the contents
	    	if(!found && i == index) {
	    		
	    		i++;
	            found = true;
	    	}
	    	
	    	if(j < names.length) {
	    		
	    		names[j] = this.names[i];
				ages[j] = this.ages[i];
	    	}
			
			i++;
	    	j++;
		}
		
		if(found) {
	    	
	    	this.names = names;
			this.ages = ages;
	    	this.currentRecord--; // remove 1 from current record
	    }
		
	}

	public static void main(String[] args) {

		Ivan7Example prmObj = new Ivan7Example(1); // you now must enter 0/1 to determine console or GTerm version
	}
}