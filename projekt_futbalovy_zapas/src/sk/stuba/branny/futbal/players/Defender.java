package sk.stuba.branny.futbal.players;

import java.util.Random;

public class Defender extends Player implements Form {
	
	private double defending;
	private double passing;
	private double forma;
	
	public Defender(String firstName,String secondName, int kitNumber,double defending,double passing)
	{
		super(firstName,secondName,kitNumber);
		
		this.passing=passing;
		this.defending=defending;
		
		setForm();
		
	}
		 @Override
		public void setForm(){
		
			Random random = new Random();
			forma = (random.nextInt(4)+8)/10.0;	
			this.defending = defending*forma;
			this.passing = passing*forma;
			if(this.passing>100)
			{
				this.passing = 100;
			}
			else if(this.passing<40)
			{
				this.passing=40;
			}
			
			if(this.defending>100)
			{
				this.defending=100;
			}
			else if(this.defending<40)
			{
				this.defending=40;
			}
			
		}
	
	
	public double getDefending() {
		return defending;
	}
	
	public double getPassing() {
		return passing;
	}

}	
