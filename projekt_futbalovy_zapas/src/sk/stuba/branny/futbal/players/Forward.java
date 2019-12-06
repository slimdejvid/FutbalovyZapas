package sk.stuba.branny.futbal.players;

import java.util.Random;

public class Forward extends Player implements Form {
	
	private double shooting;
	private double dribling;
	private double forma;
	
	public Forward(String firstName,String secondName, int kitNumber,double shooting,double dribling)
	{
		super(firstName,secondName,kitNumber);
		this.shooting=shooting;
		this.dribling = dribling;
		
		setForm();
	}
	
	 @Override
		public void setForm(){
		
		Random random = new Random();
		forma = (random.nextInt(3)+9)/10.0;	
		
		this.shooting = shooting*forma;
		this.dribling = dribling*forma;
		if(this.shooting>100)
		{
			this.shooting = 100;
		}
		else if(this.shooting<40)
		{
			this.shooting=40;
		}
		
		if(this.dribling>100)
		{
			this.dribling = 100;
		}
		else if(this.dribling<40)
		{
			this.dribling=40;
		}	
		
		
	}
		
	public double getShooting() {
		return shooting;
	}
	
	public double getDribling(){
		return dribling;
	}
}
