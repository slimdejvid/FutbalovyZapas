package sk.stuba.branny.futbal.players;

import java.util.Random;

public class Forward extends Player implements PlayerFunctions {

	private double shooting;
	private double dribling;
	private double form;

	public Forward(String firstName, String secondName, int kitNumber, double shooting, double dribling) {
		super(firstName, secondName, kitNumber);
		this.shooting = shooting;
		this.dribling = dribling;

		setForm();
	}

	@Override
	public void setForm() {

		Random random = new Random();
		form = (random.nextInt(3) + 9) / 10.0;

		this.shooting = shooting * form;
		this.dribling = dribling * form;
		if (this.shooting > 100) {
			this.shooting = 100;
		} 
		else if (this.shooting < 40) {
			this.shooting = 40;
		}

		if (this.dribling > 100) {
			this.dribling = 100;
		} 
		else if (this.dribling < 40) {
			this.dribling = 40;
		}

	}

	public double getShooting() {
		return shooting;
	}

	public double getDribling() {
		return dribling;
	}
	
	@Override
	public void printAttributes() {
		System.out.println(this.getKitNumber() + " " + this.getFirstName() + " "
				+ this.getSecondName() + " | Shooting: " + Math.round(this.getShooting())
				+ " | Dribling: " + Math.round(this.getDribling()));
	}
}
