import java.awt.Color;
import java.util.Scanner;

public class Starter07 {
	
	private String[] names;
	private int[] ages;
	
	private int currentRecord;
	private int maxRecords;
	
	private GTerm gt;
	private Scanner input;
	
	public Starter07(int userInterfaceMode) {		
		
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
		
		System.out.println("BUTTON: Remove Record!");
	}
	
	public void editRecord() {
		
		System.out.println("BUTTON: Edit Record!");
	}
	
	// you may wish to move the input validation code into its own method so as to reuse for editRecord
	public void addRecord() {
		
		if(this.currentRecord < this.maxRecords) {
			
			String[] record = this.gt.getInputString("Enter record " + (this.currentRecord + 1) + ": name,age -- NO SPACES!").split(",");
			
			// validate name
			while(record[0].equals("")) 
				record[0] = this.gt.getInputString("ERROR: Invalid NAME (string). Re-enter: ");
			this.names[this.currentRecord] = record[0];
			
			// validate age
			while(record[1].equals("")) // OPTIONAL... can assume user is not a twit
				record[1] = this.gt.getInputString("ERROR: Invalid AGE (int). Re-enter: ");
			while(Integer.parseInt(record[1]) <= 0)
				record[1] = this.gt.getInputString("ERROR: AGE (int) must be positive number. Re-enter: ");
			this.ages[this.currentRecord] = Integer.parseInt(record[1]);
			
			System.out.println(this.names[this.currentRecord] + "," + this.ages[this.currentRecord]); // debug			
			
			this.currentRecord++;
			refreshTable();
		}
		else this.gt.showWarningDialog("Max (" + this.maxRecords + ") records reached.");
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
		
	}
	
	// this could be part of the removeRecord method, however, this will be reused by consoleRemoveRecord
	public void removeRecordFromArrays(int index) {
		
	}

	public static void main(String[] args) {

		Starter07 prmObj = new Starter07(0); // you now must enter 0/1 to determine console or GTerm version
	}
}