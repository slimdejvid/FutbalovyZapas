package sk.stuba.branny.futbal.players;

import java.util.Random;

public class Goalkeeper extends Player implements Form {

	private double goalkeeping;
	private double passing;
	private double form;

	public Goalkeeper(String firstName, String secondName, int kitNumber, double goalkeeping, double passing) {
		super(firstName, secondName, kitNumber);

		this.goalkeeping = goalkeeping;
		this.passing = passing;

		setForm();
	}

	@Override

	public void setForm() {
		Random random = new Random();
		form = (random.nextInt(3) + 9) / 10.0;

		this.goalkeeping = goalkeeping * form;
		this.passing = passing * form;

		if (this.passing > 100) {
			this.passing = 100;
		} 
		else if (this.passing < 40) {
			this.passing = 40;
		}

		if (this.goalkeeping > 70) {
			this.goalkeeping = 70;
		} 
		else if (this.goalkeeping < 40) {
			this.goalkeeping = 40;
		}

	}

	public double getGoalkeeping() {
		return goalkeeping;
	}

	public double getPassing() {
		return passing;
	}

}
