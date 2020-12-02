public class HelloWorld {
	public static void main(String[] args) {
		GTerm gt = new GTerm(650, 760);
		gt.setBackgroundColor(61, 255, 51 );
		gt.setXY(0, 0);
		gt.setFontSize(20);
		String enteredString=gt.getInputString("Type your name to see my first Java app");
        // if
		gt.print("Hello ");
		gt.print(enteredString);
		gt.println(" my name is Paul Baird.");
		gt.println("I am new to coding but already enjoy it.");
		gt.println("I only expect to learn the basics.");
		gt.println("Do not have any ambitions of being a full time coder.");
		
		String enteredString1=gt.getInputString("Would you like to see a pic of my PC?");
		// if
		gt.print("Here it is:");
		gt.println("");
		gt.setXY(100, 150);
		gt.addImageIcon("MyPC.jpg");
		gt.setXY(0, 700);
		gt.println("");
		gt.println("Sooooo Pretty!!!");
	
	}
}
