import javax.swing.JOptionPane;

public class FriendlyGreeter {
	
	public static void main(String[] args) {
		
		String name = JOptionPane.showInputDialog("Enter your name");
		int age = Integer.parseInt(JOptionPane.showInputDialog("Enter your age"));
		double height = Double.parseDouble(JOptionPane.showInputDialog("Enter your height (m)"));
		double weight = Double.parseDouble(JOptionPane.showInputDialog("Enter your weight (Kg)"));
		double bmi = weight / (height * height);
		JOptionPane.showMessageDialog(null, "Hello " + name + "!");
		JOptionPane.showMessageDialog(null, "You are " + age + " and your body mass index is " + bmi + ".");
	}
}