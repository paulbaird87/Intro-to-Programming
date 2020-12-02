
// This class gathers and orders the Record method that holds the 5 arrays used for the program.
public class Record {

	// Declaration of arrays to be used by the Records method and sent to frontEnd. 
	private String driverNames;
	private int licNumbers;
	private char licClasss;
	private double vehicleWeights;
	private int licExpirys;

	// Sets the order or index number of the arrays.
	public Record(String driverNames, int licNumbers, char licClasss, double vehicleWeights, 
	              int licExpirys) {
		this.driverNames = driverNames;
		this.licNumbers = licNumbers;
		this.licClasss = licClasss;
		this.vehicleWeights = vehicleWeights;
		this.licExpirys = licExpirys;
	}

	public String getdriverNames() {
		return this.driverNames;
	}

	public int getlicNumbers() {
		return this.licNumbers;
	}

	public char getlicClasss() {
		return this.licClasss;
	}

	public double getvehicleWeights() {
		return this.vehicleWeights;
	}

	public int getlicExpirys() {
		return this.licExpirys;
	}

}
