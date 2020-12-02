import java.awt.Color;

public class FrontEndGTerm {

	private GTerm gt;
	private BackEnd backEnd;

	public FrontEndGTerm(BackEnd backEnd) {
		
		this.backEnd = backEnd;

		// Sets GTerm window style
		this.gt = new GTerm(800, 600);
		this.gt.setXY(70, 50);
		this.gt.setFontSize(16);
		this.gt.setFontColor(Color.BLACK);
		this.gt.setBackgroundColor(Color.WHITE);

		// Adds GTerm components to window
		this.gt.setFontColor(Color.BLACK);
		this.gt.addTable(500, 300, "Name\tAge\tHeight\tEmployed\tGender");

		//Adds text field and labers to new GTerm window
		this.gt.println("");
		this.gt.setFontColor(Color.RED);
		this.gt.println("Contestant:");
		this.gt.print("Name,Age,Height,Employed,Gender ");
		this.gt.addTextField("", 250);

		//Adds buttons to new GTerm window
		this.gt.println("");
		this.gt.addButton("Add", this, "addContestant");
		this.gt.print(" ");
		this.gt.addButton("Edit", this, "editContestant");
		this.gt.print(" ");
		this.gt.addButton("Remove", this, "removeContestant");
		this.gt.println("");
		this.gt.addButton("Load Contestant", this, "loadContestant");
		this.gt.println("");
		this.gt.addButton("Save Contestant", this, "saveContestant");

	}
	//Loads contestant details from file.
	public void loadContestant() {
		
		String[] arrayOfStrings = this.backEnd.loadFile(this.gt.getFilePath());
		this.backEnd.updateContestant(arrayOfStrings);
		refreshTable();
	}
	
	public void saveContestant() {
		
		String[] arrayofStrings = new String[this.backEnd.getContestantCount()];
		
		int i = 0;
		while (i < arrayofStrings.length) {
			arrayofStrings[i] = (this.backEnd.getContestant(i).getName() + "," + this.backEnd.getContestant(i).getAge() + "," +  this.backEnd.getContestant(i).getHeight() + "," +  this.backEnd.getContestant(i).getEmployment() + "," +  this.backEnd.getContestant(i).getGender());
			i++;
		}
		if (this.backEnd.saveFile(arrayofStrings, "./contestantDetails.data")) {
			this.gt.showMessageDialog("File written successfully");
			} else
				this.gt.showMessageDialog("File failed to save, please try again");
	}
	//Allows to remove contestant from table.
	public void removeContestant() {

		int rowIndex = this.gt.getIndexOfSelectedRowFromTable(0);
		System.out.println(rowIndex); // debugging
		if(rowIndex >= 0) {

			this.backEnd.removeContestant(rowIndex);
			refreshTable();
		}
	}
	//Allows to edit contestant details.
	public void editContestant() {

		int rowIndex = this.gt.getIndexOfSelectedRowFromTable(0);
		if(rowIndex >= 0) {

			String input = this.gt.getTextFromEntry(0);
			if(input != null && !input.isBlank()) {

				this.backEnd.editContestant(rowIndex, validateInput(input));

				refreshTable();
				this.gt.setTextInEntry(0, "");
			}
			else this.gt.showWarningDialog("Enter new contestant details -- Name,Age,Height,Employed,Gender -- into text field.");
		}
		else this.gt.showWarningDialog("Select contestant from table.");
	}

	//Allows to add contestant to table.
	public void addContestant() {

		// Enter and process each contestant details.
		if(this.backEnd.getContestantCount() < this.backEnd.getMaxContestant()) {

			// Ask user to enter details for current contestant.
			String input = this.gt.getTextFromEntry(0);
			if(input != null && !input.isBlank()) {

				this.backEnd.addContestant(validateInput(input));	
				
				refreshTable();
				this.gt.setTextInEntry(0, "");
			}
			else this.gt.showWarningDialog("Enter contestant details: Name,Age,Height,Employed,Gender - into the text field.");

		}
		else {

			this.gt.showWarningDialog("Max number of contestants (" + this.backEnd.getMaxContestant() + ") reached.");
			String input = this.gt.getInputString("Increase contestant amount: ");
			if(input != null && !input.isBlank()) {

				int numContestant = Integer.parseInt(input);
				if(numContestant > 0) this.backEnd.expandContestants(numContestant);
			}
		}
	}

	//Clears and repopulates table based on data in arrays.
	public void refreshTable() {

		this.gt.clearRowsOfTable(0);
		int i = 0;
		while(i < this.backEnd.getContestantCount()) {

			this.gt.addRowToTable(0, this.backEnd.getContestant(i).getName() + "\t" + this.backEnd.getContestant(i).getAge() + "\t" + this.backEnd.getContestant(i).getHeight() + "\t" + this.backEnd.getContestant(i).getEmployment() + "\t" + this.backEnd.getContestant(i).getGender());
			i++;
		}
	}
	//Validates contestant details to match the format.
	public Contestant validateInput(String input) {

		String[] contestants = input.split(",");

		// Checks for name, if blank then need to enter name.
		while (contestants[0].isEmpty()) 
			contestants[0] = this.gt.getInputString("ERROR: Invalid entry for name. Please re-enter:");
		
		// Checks for age, needs to be whole and positive number. If not, doesn't accept.
		while (Integer.parseInt(contestants[1]) <= 0) 
			contestants[1] = this.gt.getInputString("ERROR: Invalid entry for age. Please enter a whole positive number:");
		
		// Checks for height, needs to be more than 0. If not, doesn't accept.
		while (Float.parseFloat(contestants[2]) <= 0.0) 
			contestants[2] = this.gt.getInputString("ERROR: Invalid entry for height. Please enter a positive number:");
		
		// Checks for true or false answer. If anything else is entered, doesn't accept.
		while (!contestants[3].equalsIgnoreCase("true") && !contestants[3].equalsIgnoreCase("false")) 
			contestants[3] = this.gt.getInputString("ERROR: Invalid entry for employment. Please re-enter (True/False):");
		
		// Checks for M or F as gender input. If anything else, doesn't accept.
		while (!contestants[4].equalsIgnoreCase("M") && !contestants[4].equalsIgnoreCase("F")) 
			contestants[4] = this.gt.getInputString("ERROR: Invalid entry for gender. Please re-enter (M/F):");
					
		return new Contestant(contestants[0], Integer.parseInt(contestants[1]), Float.parseFloat(contestants[2]), Boolean.parseBoolean(contestants[3]), contestants[4].charAt(0));
		
			
	}
}