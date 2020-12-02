import java.awt.Color;

public class FrontEndGTerm {

	private GTerm gt;
    private BackEnd backEnd;
    
	public FrontEndGTerm(BackEnd backEnd) {

		this.backEnd = backEnd;
		
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
		
	}
		
		/// GTerm button methods
		
		public void removeRecord() {
			
			int rowIndex = this.gt.getIndexOfSelectedRowFromTable(0);
			System.out.println(rowIndex); // debugging
			if(rowIndex >= 0) {
				
				this.backEnd.removeRecord(rowIndex);
				refreshTable();
			}
		}
		
		public void editRecord() {
			
			int rowIndex = this.gt.getIndexOfSelectedRowFromTable(0);
			if(rowIndex >= 0) {
				
				String input = this.gt.getTextFromEntry(0);
				if(input != null && !input.isBlank()) {
					
				this.backEnd.editRecord(rowIndex, validateInput(input));
					
					refreshTable();
					this.gt.setTextInEntry(0, "");
				}
				else this.gt.showWarningDialog("Enter new record details -- name,age -- into text field.");
			}
			else this.gt.showWarningDialog("Select record from table.");
		}
		
		public void addRecord() {
			
			if(this.backEnd.getRecordCount() < this.backEnd.getMaxRecords()) {
				
				String input = this.gt.getTextFromEntry(0);
				if(input != null && !input.isBlank()) {
					
					this.backEnd.addRecord(validateInput(input));
					
					refreshTable();
					this.gt.setTextInEntry(0, "");
				}
				else this.gt.showWarningDialog("Enter record details -- name,age -- into text field.");
			}
			else {
				
				this.gt.showWarningDialog("Max (" + this.backEnd.getMaxRecords() + ") records reached.");
				String input = this.gt.getInputString("Expand records amount: ");
				if(input != null && !input.isBlank()) {
					
					int numRecords = Integer.parseInt(input);
					if(numRecords > 0) this.backEnd.expandRecords(numRecords);
				}
			}
		}
		
		/// Shared use methods
		
		public void refreshTable() {

			this.gt.clearRowsOfTable(0);
			int i = 0;
			while(i < this.backEnd.getRecordCount()) {
				
				this.gt.addRowToTable(0, this.backEnd.getRecord(i).getName() + "\t" + this.backEnd.getRecord(i).getAge());
				i++;
			}
		}
		
		public Record validateInput(String input) {
			
			String[] record = input.split(",");
			
			// validate name
			while(record[0].equals("")) 
				record[0] = this.gt.getInputString("ERROR: Invalid NAME (string). Re-enter: ");
			// validate age
			while(record[1].equals("")) // OPTIONAL... can assume user is not a twit
				record[1] = this.gt.getInputString("ERROR: Invalid AGE (int). Re-enter: ");
			while(Integer.parseInt(record[1]) <= 0)
				record[1] = this.gt.getInputString("ERROR: AGE (int) must be positive number. Re-enter: ");
						
			return new Record(record[0], Integer.parseInt(record[1]));
		}
		
	}


