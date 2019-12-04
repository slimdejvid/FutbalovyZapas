package sk.stuba.branny.futbal.teams;


import java.util.ArrayList;
import java.util.List;

import sk.stuba.branny.futbal.players.*;

public class DefaultTeam {
	private String teamName = "FC Bayern Munich";
	
	private List <Player> startingElevenBayern;
	
	public DefaultTeam() {
		startingElevenBayern = new ArrayList<>();
		
		initPlayers();
		
		printTeam();
	}
	
	private void initPlayers() {
		Player g1 = new Goalkeeper("Manuel","Neuer",1,70,90);
		startingElevenBayern.add(g1);
		
		Player d1 = new Defender("Niklas","S�le",4,84,50);
		Player d2 = new Defender("Lucas","Hern�ndez",21,80,70);
		Player d3 = new Defender("Joshua","Kimmich",32,77,86);
		Player d4 = new Defender("David","Alaba",27,78,81);
		startingElevenBayern.add(d1);
		startingElevenBayern.add(d2);
		startingElevenBayern.add(d3);
		startingElevenBayern.add(d4);
		
		Player m1 = new Midfielder("Thiago","Alc�ntara",6,85);
		Player m2 = new Midfielder("Ivan","Peri�i�",14,76);
		Player m3 = new Midfielder("Serge","Gnabry",22,72);
		startingElevenBayern.add(m1);
		startingElevenBayern.add(m2);
		startingElevenBayern.add(m3);



		Player f1 = new Forward("Phillipe","Coutinho",6,78,92);
		Player f2 = new Forward("Thomas","M�ller",25,83,84);
		Player f3 = new Forward("Robert","Lewandowski",9,93,85);
		startingElevenBayern.add(f1);
		startingElevenBayern.add(f2);
		startingElevenBayern.add(f3);
		
		
	}
	
	private void printTeam() 
	{
		System.out.println("\n"+teamName+"\n");
		for (Player p:startingElevenBayern) 
		{ 		
			System.out.println(p.getKitNumber()+". "+p.getFirstName()+" "+p.getSecondName());
	    } 
		System.out.println("\n");
	}
	
	
	
	public List <Player> getStartingElevenAwayTeam()
	{
        return this.startingElevenBayern;
	}
	
	public String getTeamName()
	{
		
		return this.teamName;
	}
	//prekon�vanie metody toString z triedy Object
	@Override
	public String toString()
	{
				
		return " \"We knew we can beat FCBarcelona\", they said";
	}
	
}