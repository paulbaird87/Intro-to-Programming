import java.util.Scanner;

public class FriebdlyGreeter {

	public static void main(String[] args) {
		Scanner scanner1 = new Scanner(System.in);
		System.out.print("Enter your name:");
		String name = scanner1.next();

		System.out.print("Enter your age (years): ");
		int age = scanner1.nextInt(); // Returns an int
		System.out.print("Enter your height (m): ");
		double height = scanner1.nextDouble(); // Returns a double
		System.out.print("Enter your weight (Kg): ");
		double weight = scanner1.nextDouble();
		double bmi = weight / (height * height);
		System.out.println("Well " + name + "!");
		System.out.println("You are " + age + " and your body mass index is " + bmi + ".");
		scanner1.close();
	}

}
