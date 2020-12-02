public class BackEnd {
	
	private Record[] records;	
	private int currentRecord;
	private int maxRecords;
	
	
	public BackEnd() {		
		
		this.records = new Record[3];
		this.currentRecord = 0;
		this.maxRecords = 3; 
		 		
	}
	
	public Record getRecord(int index) {
		
		if(index < this.records.length) {
			return records[index];
		}
		else return null;
	}
	
	
	public int getRecordCount() {
		return this.currentRecord;
	}
	
	
	public int getMaxRecords() {
		return this.maxRecords;
	}
	
	public void addRecord(Record record) {
		
		this.records[this.currentRecord] = record;
		this.currentRecord++;
		
	}
	
	public void editRecord(int index, Record record) {
		
		if(index < this.records.length)this.records[index] = record;
		
	}
	
	
	public void expandRecords(int numRecords) {
		
		Record[] records = new Record[this.records.length + numRecords];

		int i = 0;
		while(i < this.records .length) {
			
			records[i] = this.records[i];
			i++;
		}
		
		this.records = records;
		this.maxRecords += numRecords;
	}
	
	public void removeRecord(int index) {
		
		Record[] records = new Record[this.records.length -1];
		
		boolean found = false;
		int i = 0, j = 0;
		while(i < this.records.length) {
			
			// if same index, skip the contents
	    	if(!found && i == index) {
	    		
	    		i++;
	            found = true;
	    	}
	    	
	    	if(j < records.length) {
	    		
	    		records[j] = this.records[i];
	    	}
			
			i++;
	    	j++;
		}
		
		if(found) {
	    	
	    	this.records = records;
	    	this.currentRecord--; // remove 1 from current record
	    	this.maxRecords--; // Opps... forgot this last week...
	    }
		
	}
}