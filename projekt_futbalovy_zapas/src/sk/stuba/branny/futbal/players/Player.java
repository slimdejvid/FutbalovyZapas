package sk.stuba.branny.futbal.players;

public class Player implements PlayerFunctions {

	private String firstName;
	private String secondName;
	private int kitNumber;
	private boolean hasBall = false;

	public Player(String firstName, String secondName, int kitNumber) {
		this.kitNumber = kitNumber;
		this.firstName = firstName;
		this.secondName = secondName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getSecondName() {
		return this.secondName;
	}

	public int getKitNumber() {
		return this.kitNumber;
	}

	public void setHasBall(boolean newValue) {
		this.hasBall = newValue;
	}

	public boolean getHasBall() {
		return this.hasBall;
	}
	
	public double getPassing() {
		return 0.0;
	}
	
	public void printAttributes() {
		
	}
	
	public void setForm() {
		
	}

}
