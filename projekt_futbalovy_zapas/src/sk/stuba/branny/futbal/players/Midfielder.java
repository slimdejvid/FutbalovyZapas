package sk.stuba.branny.futbal.players;

import java.util.Random;

public class Midfielder extends Player implements Form {
	
	private double passing;
	private double forma;
	
	public Midfielder(String firstName,String secondName, int kitNumber,double passing)
	{
		super(firstName,secondName,kitNumber);
		this.passing=passing;
		
		setForm();
	}
	
	 @Override
		public void setForm(){
		
		Random random = new Random();
		forma = (random.nextInt(3)+9)/10.0;	
		
		this.passing = passing*forma;
		if(this.passing>100)
		{
			this.passing = 100;
		}
		else if(this.passing<40)
		{
			this.passing=40;
		}
		
	}
	
	
	public double getPassing() {
		return passing;
	}
}
