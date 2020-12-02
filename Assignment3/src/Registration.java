
// This class gathers and orders the Record method that holds the 5 arrays used for the program.
public class Registration {

	// Declaration of arrays to be used by the Records method and sent to frontEnd.
	private String driverNames;
	private int licNumbers;
	private char licClasss;
	private double vehicleWeights;
	private int licExpirys;

	// Sets the order or index number of the arrays.
	public Registration(String driverNames, int licNumbers, char licClasss, double vehicleWeights, int licExpirys) {
		this.driverNames = driverNames;
		this.licNumbers = licNumbers;
		this.licClasss = licClasss;
		this.vehicleWeights = vehicleWeights;
		this.licExpirys = licExpirys;
	}
    // get method that returns the drivers name
	public String getdriverNames() {
		return this.driverNames;
	}
	// get method that returns the drivers license number
	public int getlicNumbers() {
		return this.licNumbers;
	}
	// get method that returns the drivers license class
	public char getlicClasss() {
		return this.licClasss;
	}
	// get method that returns vehicle weight 
	public double getvehicleWeights() {
		return this.vehicleWeights;
	}
	// get method that returns the drivers license exipry date. 
	public int getlicExpirys() {
		return this.licExpirys;
	}

}
