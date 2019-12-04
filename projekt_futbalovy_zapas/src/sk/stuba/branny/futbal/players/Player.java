package sk.stuba.branny.futbal.players;

public class Player {
	//enkapsulaciu, vo všetkych triedach.
	private String firstName;
	private String secondName;
	private int kitNumber;
	private boolean hasBall = false;
	
	public Player(String firstName,String secondName, int kitNumber)
	{
		this.kitNumber=kitNumber;
		this.firstName=firstName;
		this.secondName=secondName;
	}
	
	public String getFirstName() 
	{
		return this.firstName;
	}
	
	public String getSecondName() 
	{
		return this.secondName;
	}
	
	public int getKitNumber() 
	{
		return this.kitNumber;
	}
	
	public void setHasBall(boolean newValue) //ak hrac ziska loptu alebo strati, zmenim hodnotu
	{
		this.hasBall = newValue;
	}
	
	public boolean getHasBall() // stav hraca si ma loptu, volane prevazne v match classe
	{
		return this.hasBall;
	}
	
	
	
}
