package sk.stuba.branny.futbal.teams;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;


import sk.stuba.branny.futbal.players.*;


public class MyTeam {
	private String teamName = "FC Barcelona";
	

	private List <Player> availableGoalkeepers; 
	private List <Player> availableDefenders; 
	private List <Player> availableMidfielders; 
	private List <Player> availableForwards; 
	
	private List <Player> startingElevenMyTeam;
	
	Scanner sc = new Scanner(System.in);
	
	public MyTeam(){
		availableGoalkeepers = new ArrayList<>();
		availableDefenders = new ArrayList<>();
		availableMidfielders = new ArrayList<>();
		availableForwards = new ArrayList<>();
		startingElevenMyTeam = new ArrayList<>();
		
		initPlayers();
		
		System.out.println(teamName+"\n");
		System.out.println("Let's choose your team : \n");
		
		
		chooseGoalkeeper();
		chooseDefenders();		
		chooseMidfielders();	
		chooseForwards();	 
		printStartingEleven();
	}
	
	private void initPlayers() {	//kompozicia
		
		Player g1 = new Goalkeeper("Marc-Andr�","Ter Stegen",1,70,85);
		Player g2 = new Goalkeeper("Norberto","Neto",13,65,80);
		
		Player d1 = new Defender("Gerard","Pique",3,84,72);
		Player d2 = new Defender("Nels�n","Semedo",2,75,69);
		Player d3 = new Defender("Samuel","Umtiti",23,83,69);
		Player d4 = new Defender("Jordi","Alba",18,77,81);
		Player d5 = new Defender("Cl�ment","Lenglet",15,83,65);
		Player d6 = new Defender("Sergio","Roberto",20,75,81);
		
		Player m1 = new Midfielder("Frenkie","De Jong",21,84);
		Player m2 = new Midfielder("Ivan","Rakiti�",4,82);
		Player m3 = new Midfielder("Sergio","Busquets",5,80);
		Player m4 = new Midfielder("Arthur","Melo",8,81);
		Player m5 = new Midfielder("Arturo","Vidal",22,80);
		
		Player f1 = new Forward("Lionel","Messi",10,94,98);
		Player f2 = new Forward("Luis","Suar�z",9,89,84);
		Player f3 = new Forward("Antoine","Griezmann",17,86,86);
		Player f4 = new Forward("Ousmane","Demb�l�",11,79,86);
		
		
		
		availableGoalkeepers.add(g1);
		availableGoalkeepers.add(g2);
		
		availableDefenders.add(d1);
		availableDefenders.add(d2);
		availableDefenders.add(d3);
		availableDefenders.add(d4);
		availableDefenders.add(d5);
		availableDefenders.add(d6);
		
		availableMidfielders.add(m1);
		availableMidfielders.add(m2);
		availableMidfielders.add(m3);
		availableMidfielders.add(m4);
		availableMidfielders.add(m5);
		
		availableForwards.add(f1);
		availableForwards.add(f2);
		availableForwards.add(f3);
		availableForwards.add(f4);

	}
	
	private void chooseGoalkeeper() 
	{
		
		System.out.println("List of goalkeepers: ");
		
			printPlayers(availableGoalkeepers, Goalkeeper.class);
		
			
		int poc=0;
			
		System.out.println("Choose your goalkeeper\n");
			
		int input = sc.nextInt();
		for(Player p:availableGoalkeepers)
		{
					
			if(input != p.getKitNumber())
			{
				poc++;
				if(poc==availableGoalkeepers.size())//ak som pre�iel v�etky prvky a stale som nena�iel �islo , tak volim nahodneho brankara
				{
					Random rnd = new Random();
					p = availableGoalkeepers.get(rnd.nextInt(availableGoalkeepers.size()));
					startingElevenMyTeam.add(p);
					
					
					System.out.println("There is no goalkeeper with this number, we chose "+p.getFirstName()+" "+p.getSecondName()+".");		
				}		
			}
			else
			{
				startingElevenMyTeam.add(p);
				System.out.println("Goalkeeper chosen\n");
				break;
			}

		}
		
	}
	
	
	private void chooseDefenders() {
		
		
		int numberOfValidInputs = 4;
		int input ;
		boolean find;
		
		System.out.println("List of defenders: ");
		
		printPlayers(availableDefenders,Defender.class);
		
		
		System.out.println("Choose your defenders\n");
		
		while(numberOfValidInputs>0)
		{
			find=false;
			input = sc.nextInt();
			for(Player p:availableDefenders)
			{
				if(input == p.getKitNumber())
				{
					find=true;
					--numberOfValidInputs;
					startingElevenMyTeam.add(p);
					System.out.println("Defender number "+ p.getKitNumber() +" chosen\n");
					availableDefenders.remove(p);
					if(numberOfValidInputs != 0)
					printPlayers(availableDefenders,Defender.class);
					break;
				}
				
			}
			if(find == false)
				System.out.println("Invalid kit number, try again");					
		}
	}

	private void chooseMidfielders() {
		
		int numberOfValidInputs = 3;
		int input ;
		boolean find;
		
		System.out.println("List of midfielders: ");
		
		printPlayers(availableMidfielders,Midfielder.class);
		
		System.out.println("Choose your midfielders\n");
		
		while(numberOfValidInputs>0)
		{
			find=false;
			input = sc.nextInt();
			for(Player p:availableMidfielders)
			{
				if(input == p.getKitNumber())
				{
					find=true;
					--numberOfValidInputs;
					startingElevenMyTeam.add(p);
					System.out.println("Midfielder number "+ p.getKitNumber() +" chosen\n");
					availableMidfielders.remove(p);
					if(numberOfValidInputs != 0)
						printPlayers(availableMidfielders,Midfielder.class);
					break;
				}
				
			}
			if(find == false)
				System.out.println("Invalid kit number, try again");					
		}	
	}
	
	private void chooseForwards() { 
		
		int numberOfValidInputs = 3;
		int input ;
		boolean find;
		
		System.out.println("List of forwards: ");
		
		printPlayers(availableForwards,Forward.class);
		
		System.out.println("Choose your forwards\n");
		
		while(numberOfValidInputs>0)
		{
			find=false;
			input = sc.nextInt();
			for(Player p:availableForwards)
			{
				if(input == p.getKitNumber())
				{
					find=true;
					--numberOfValidInputs;
					startingElevenMyTeam.add(p);
					System.out.println("Forward number "+ p.getKitNumber() +" chosen\n");
					availableForwards.remove(p);
					if(numberOfValidInputs != 0)
						printPlayers(availableForwards,Forward.class);
					break;
				}
				
			}
			if(find == false)
				System.out.println("Invalid kit number, try again");					
		}
	sc.close();	
	}
	
	private void printPlayers(List <Player> pole,Class c) //vypis hra�ov pri vyberan�
	{
		if(c == Goalkeeper.class)
		{
			for(Player p:pole)
				System.out.println("Kit number: "+p.getKitNumber()+" | "+p.getFirstName()+" "+p.getSecondName()+" | Goalkeeping: "+Math.round(((Goalkeeper)p).getGoalkeeping())+" | Passing: "+Math.round(((Goalkeeper)p).getPassing()));
		}
		else if(c == Defender.class)
		{
			for(Player p:pole)
			System.out.println("Kit number: "+p.getKitNumber()+" | "+p.getFirstName()+" "+p.getSecondName()+" | Defending: "+Math.round(((Defender)p).getDefending())+" | Passing: "+Math.round(((Defender)p).getPassing()));
		}
		else if(c == Midfielder.class)
		{
			for(Player p:pole)
				System.out.println("Kit number: "+p.getKitNumber()+" | "+p.getFirstName()+" "+p.getSecondName()+" | Passing: "+Math.round(((Midfielder)p).getPassing()));
		}
		else
		{
			for(Player p:pole)
				System.out.println("Kit number: "+p.getKitNumber()+" | "+p.getFirstName()+" "+p.getSecondName()+" | Shooting: "+Math.round(((Forward)p).getShooting())+" | Dribling: "+Math.round(((Forward)p).getDribling()));
		}		
	}
	
	
	private void printStartingEleven() //na vypis mojej jedenastky, pred zpaasom
	{
		System.out.println("\n"+teamName+"\n");
		for(Player p:startingElevenMyTeam)
		{
			System.out.println(p.getKitNumber()+". "+p.getFirstName()+" "+p.getSecondName());
		}
		System.out.println("\n");
	}
	
	public List <Player> getStartingElevenMyTeam()
	{
        return this.startingElevenMyTeam;
	}
	
	public String getTeamName()
	{
		
		return this.teamName;
		
	}
	
	

	
}