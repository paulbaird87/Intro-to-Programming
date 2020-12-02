
public class TheMagpie {

	public static void main(String[] args) {
		GTerm gt = new GTerm(720, 480);
		gt.setBackgroundColor(180, 184, 183);
		gt.setXY(0, 0);
		gt.setFontSize(20);

		// This is a simple starting message for the player. Requires a string input
		// that is not case sensitive.
		// String was chosen as the input can only be a word.
		String welcomeMessage = "Hello";
		// Variable name chosen as userHello to clearly display that it is to be used
		// for the hello section for user input.
		String userHello = gt.getInputString("Type: Hello");

		// While loop created if user miss spells hello.
		if (!welcomeMessage.equalsIgnoreCase(userHello)) {
			do {
				userHello = gt
						.getInputString("You will have to learn to spell to play this game.\nTry typing hello again.");
			} while (!welcomeMessage.equalsIgnoreCase(userHello));
		}
		gt.println("Welcome to the scary virtual magpie Java story!");

		// String was used here again as it is a basic yes or no input and I preferred
		// to have the children writing
		// words rather than selecting yes or no options with numbers, characters or
		// click inputs.
		// Variable name was chosen as startStory1 as it designates the start of the
		// story line and it also designates
		// the story line 1 as there are two choices for the player to take. Typing no
		// here would have the player come to
		// an end and the story will finish. If the player selects yes they will
		// continue the story with will have another split decision later.
		String startStory1 = gt.getInputString("Would you like to hear the story?\nType: Yes or No");
		if (startStory1.equalsIgnoreCase("Yes")) {
			gt.clear();
			gt.println("Are you sure?\nThis story can conjure horrifying\nemotions from your childhood.");

			// Typing no here will bring the player to an end. startStory2 is the variable
			// in which the player will proceed
			String startStory2 = gt
					.getInputString("If you are feeling brave type:\nYes to proceed\nor\nNo to cry to mummy, hahaha");

			if (startStory2.equalsIgnoreCase("yes")) {
				gt.clear();
				gt.println("Lets Begin");
			}
		}

		else {
			gt.clear();
			gt.println("Ok well I'll call your mummy to tuck you in");
		}
		// This is a synchronized block, it is put in place to block out other threads
		// outside of it until it has completed.
		synchronized (args) {
			// try is used to catch exceptions that may be thrown when the program executes
			// to so that it does
			// not crash is the exception occurs.
			try {
				// this .wait(3000) represents a 3000 millisecond wait time before the next line
				// is displayed.
				args.wait(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			gt.println("It was a hot summers day in Dubbo, NSW.");

			try {
				args.wait(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			gt.println("The school bell rings, you feel a sudden sense of horror.");

			try {
				args.wait(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			gt.println("You are the only kid in school that has to ride north!");

			try {
				args.wait(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			gt.println("All the way up Scarecrow lane and past the BIG GREY TREE!!!");

			try {
				args.wait(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			gt.println("You know what is waiting, you remember\nwhat happened last time you went that way home.");

			try {
				args.wait(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			gt.println("That dark object sitting on the branch,\nstaring.....waiting....watching!!!");

			try {
				args.wait(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			gt.clear();
			// m1.jpg was the file name chosen as it is quick and easy, there are not many
			// images, and the number represents the
			// pictures sequence in the story.
			gt.addImageIcon("m1.jpg");

			try {
				args.wait(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			gt.clear();
		}
		// int is used here as I have already asked the player to write words and this
		// time I decided to let the user select a simple integer option.
		int splitDecision1 = Integer.parseInt(
				gt.getInputString("Its HIM!      He has seen you!\nYou have two options\n1) The bravest option and"
						+ " the shortest way home, the road.\nor\n2) The scaredy-cat option, the long way, over the football field."));

		if (splitDecision1 == 1) {

			gt.addImageIcon("m2.jpg");

			synchronized (args) {
				try {
					args.wait(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				gt.clear();
				gt.println("You made it, but get inside quickly before\nyour parents notice your wet pants!");
			}
			// else if was used here to capture both decision paths in the same block.
		} else if (splitDecision1 == 2) {
			gt.addImageIcon("m3.jpg");
			synchronized (args) {
				try {
					args.wait(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				gt.clear();
				gt.println("Smart option, I applaud your efforts.\nBut he was too clever, you got swooped!");
			}

		}
	}
}
