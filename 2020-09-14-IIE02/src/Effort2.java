public class Effort2 {
	public static void main(String[] args) {
		GTerm gt = new GTerm(800, 420);
		gt.setBackgroundColor(61, 255, 51);
		gt.setXY(100, 100);
		gt.setFontSize(20);
		

		//
		//
		String driverName = gt.getInputString("Enter your full name: ");

		//
		//
		int licenseNumber = Integer.parseInt(gt.getInputString("Enter your drivers license number: "));

		//
		//
		char licenseClass = (gt
				.getInputString("Enter your lisence class" + "\n" + "c = Car" + "\n" + "r = Rider" + "\n")).charAt(0);

		//
		//
		double vehicleWeight = Double.parseDouble(gt.getInputString("Enter vehicle weight, tonnes (i.e. 1.3): "));

		//
		//
		int expiryYear = Integer.parseInt(gt.getInputString("Enter your year of expiry: "));
		boolean isCurrent = expiryYear >= 2020;
		boolean notCurrent = expiryYear < 2020;

		gt.println("Registered name: " + driverName);
		gt.println("License number: " + licenseNumber);

		if (licenseClass == 'c') {
			gt.println("License class: C-Car");
			gt.setFontSize(12);
			gt.println("    You can legally operate:" + "\n" + "    Vehicles up to 4.5 tonne Gross Vehicle Mass (GVM)"
					+ "\n" + "    Vehicles that seat up to 12 adults, including the driver");
		} else if (licenseClass == 'r') {
			gt.println("License class: R-Rider");
			gt.setFontSize(12);
			gt.println("    You can legally operate:" + "\n" + "    Motorcycles and scooters" + "\n"
					+ "    Motorcycles with 1 pillion passenger");
		} else {
			gt.println("Not a valid class, try using lower case");
		}

		gt.setFontSize(20);
		gt.println("Vehicle weight: " + vehicleWeight + "tonnes");

		if (isCurrent) {
			gt.println("Your drivers license is current");
		} else if (notCurrent) {
			gt.println("Your drivers license is out of date, please renew!");
		}

		// licenseNumber += 1;
		// vehicleWeight += 1;
		// expiryYear += 1;

		// String message = "";
		// message += "After assigning new values..." + "\n";
		// message += "Registered name: " + driverName + "\n";
		// message += "license Number: " + licenseNumber + "\n";
		// message += "License class: " + licenseClass + "\n";
		// message += "Vehicle weight: " + vehicleWeight + "tonnes" + "\n";
		// message += "License Expiry: " + expiryYear + "\n";

		// gt.showMessageDialog(message);

	}
}
