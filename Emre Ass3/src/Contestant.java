public class Contestant {
	
	private String name;
	private int age;
	private float height;
	private boolean employment;
	private char gender;
	
	public Contestant(String name, int age, float height, boolean employment, char gender) {
		this.name = name;
		this.age = age;
		this.height = height;
		this.employment = employment;
		this.gender = gender;
	}
	//Returns name of contestant.
	public String getName() {
		return this.name;
	}
	//Returns age of contestant.
	public int getAge() {
		return this.age;
	}
	//Returns height of contestant.
	public float getHeight() {
		return this.height;
		
	}
	//Returns employment of contestant.
	public boolean getEmployment() {
		return this.employment;
		
	}
	//Returns gender of contestant.
	public char getGender() {
		return this.gender;
		
	}	
	
	
}