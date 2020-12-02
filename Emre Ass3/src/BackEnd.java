import java.io.*;

public class BackEnd {

	private Contestant[] contestants;
	private int currentContestant;
	private int maxContestant;

	public BackEnd() {
		//Allows the user to set number of contestants prior to initialisation.
		this.contestants = new Contestant[0];
		this.currentContestant = 0;
		this.maxContestant = 0;
			
	}
	
	public Contestant getContestant(int index) {
		
		if(index < this.contestants.length) {
			return contestants[index];
		}
		else return null;
	}
	
	public int getContestantCount() {
		
		return this.currentContestant;		
	}
	
	public int getMaxContestant() {
		
		return this.maxContestant;
	}
	
	public void addContestant(Contestant contestants) {
		
		this.contestants[this.currentContestant] = contestants;
		this.currentContestant++;
	}
	
	public void editContestant(int index, Contestant contestants) {
		
		if(index < this.contestants.length) this.contestants[index] = contestants;
		
	}
	//Allows to increase contestant count.
	public void expandContestants(int numContestant) {
		
		Contestant[] contestants = new Contestant[this.contestants.length + numContestant];
		
		
		int i = 0;
		while(i < this.contestants.length) {
			contestants[i] = this.contestants[i];
			i++;
		}
		
		this.contestants = contestants;
		this.maxContestant += numContestant;
		
	}
	//Allows to remove contestant from table.
	public void removeContestant(int index) {
		
		Contestant[] contestants = new Contestant[this.contestants.length -1];
		
		boolean found = false;
		int i = 0, j = 0;
		while(i < this.contestants.length) {
			
			//If same index, skip the contents
			if(!found && i == index) {
								
				i++;
				found = true;
			}
			
			if(j< contestants.length) {
				
				contestants[j] = this.contestants[i];
			}
			
			i++;
			j++;
		}
		
		if(found) {
			
			this.contestants = contestants;		
			this.currentContestant--;
			this.maxContestant--;
		}
		
	}
	//Allows to update contestant information.
	public void updateContestant(String[] arrayOfStrings) {
		this.contestants = new Contestant[arrayOfStrings.length];
		this.maxContestant = arrayOfStrings.length;
		this.currentContestant = 0;
		
		int i = 0;
		while(i < arrayOfStrings.length) {
			
			this.contestants[i] = parseContestant(arrayOfStrings[i]);
			this.currentContestant++;
			i++;
		}
			
	}
	//Allows to load file from .data.
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
	//Translates details from .data to table.
	public Contestant parseContestant(String data) {
		
		String[] parsedData = data.split(",");
		Contestant contestants = new Contestant(parsedData[0], Integer.parseInt(parsedData[1]), Float.parseFloat(parsedData[2]), Boolean.parseBoolean(parsedData[3]), parsedData[4].charAt(0));
		return contestants;		
		
	}
	
	public void saveDetails() {
		
	}
	//Allows to save details to .data.
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
	//Allows to update contestant details.
	public void updateDetails(int index, Contestant contestants) {
		this.contestants[index] = contestants;
	}
}