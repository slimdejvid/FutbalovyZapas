package sk.stuba.branny.futbal.players;

import java.util.Random;

public class Goalkeeper extends Player implements Form  { //dedenie
	
	private double goalkeeping;
	private double passing;
	private double forma;
	
	
	
	public Goalkeeper(String firstName,String secondName, int kitNumber,double goalkeeping,double passing)
	{
		super(firstName,secondName,kitNumber);
		
		
		this.goalkeeping=goalkeeping;
		this.passing=passing;
		
		setForm();	
	}
	
		@Override
		
		public void setForm(){ //podobne ako tu tak aj v dalšich klasach aplikujem nahodnu formu od 0.9 po 1.1 na atributy hraèov podla typu 
		
		Random random = new Random();
		forma = (random.nextInt(3)+9)/10.0;	
		this.goalkeeping = goalkeeping*forma;
		this.passing=passing*forma;
		
		if(this.passing>100)
		{
			this.passing = 100;
		}
		else if(this.passing<40)
		{
			this.passing=40;
		}
		
		if(this.goalkeeping>70)
		{
			this.goalkeeping=70;
		}
		else if(this.goalkeeping<40)
		{
			this.goalkeeping=40;
		}
			
	}
	
	
	public double getGoalkeeping() {
		return goalkeeping;
	}
	
	public double getPassing() {
		return passing;
	}

}
