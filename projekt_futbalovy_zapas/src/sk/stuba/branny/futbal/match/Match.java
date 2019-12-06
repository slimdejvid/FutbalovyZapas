package sk.stuba.branny.futbal.match;

import java.util.List;
import java.util.Scanner;
import java.util.Random;

import sk.stuba.branny.futbal.players.Defender;
import sk.stuba.branny.futbal.players.Forward;
import sk.stuba.branny.futbal.players.Goalkeeper;
import sk.stuba.branny.futbal.players.Midfielder;
import sk.stuba.branny.futbal.players.Player;
import sk.stuba.branny.futbal.teams.DefaultTeam;
import sk.stuba.branny.futbal.teams.MyTeam;


public class Match {
	private static Match instance;
	private final int maxNumberOfSubs = 3; 
	
	private MyTeam homeTeam;//agregacia
	private DefaultTeam awayTeam;//agregacia
	
	Scanner scan = new Scanner(System.in);
	
	private String homeName;
	private String awayName;
	
	private List <Player> startingElevenMyTeam;
	private List <Player> startingElevenAwayTeam;
	
	private int skoreHome;
	private int skoreAway;
	
	private Match() {
		this.skoreHome=0;
		this.skoreAway=0;
	}
	
	public static  Match getInstance() //singleton na vytvorenie jednej in�tancie matchu 
	{
		if(instance == null)
		{
			instance = new Match();
			
		}
		return instance;
	}
	
	public void setHomeTeam(MyTeam homeTeam) //agregacia
	{
		this.homeTeam = homeTeam;
		this.startingElevenMyTeam = homeTeam.getStartingElevenMyTeam();
		this.homeName = homeTeam.getTeamName();
	}
	
	
	public void setAwayTeam(DefaultTeam awayTeam) //agregacia
	{
		this.awayTeam = awayTeam;
		this.startingElevenAwayTeam = awayTeam.getStartingElevenAwayTeam();
		this.awayName = awayTeam.getTeamName();
	}
	
	

	
	public void substitution(int numberOfSubs) 
	{
	
		Player p1,p2;
		
		if(numberOfSubs>maxNumberOfSubs)
			numberOfSubs=3;
		
		while(numberOfSubs>0)
		{
			p1=choosePlayerToBeSub();
			p2=choosePlayerToSub(p1);
			if(p2 == null)
				continue;
			else if(p1 instanceof Defender) 
				substitute((Defender) p1,(Defender) p2); //downcasting
			else if(p1 instanceof Midfielder)
				substitute((Midfielder) p1,(Midfielder) p2);
			else if(p1 instanceof Midfielder)
				substitute((Forward) p1,(Forward) p2);
			numberOfSubs--;			
		}
	
	}
	
	
	public Player choosePlayerToBeSub()
	{
		
		System.out.println("Choose kit number of player to be substituted.");
		
		
		int input = scan.nextInt();
		
		for(Player p : startingElevenMyTeam)
		{
			if(p.getKitNumber()==input)
			{
				return p;
			}
				
		}
		System.out.println("There is no player with this number.");
		return null;
		
	}
	
	
	public Player choosePlayerToSub(Player chosen)
	{
		
		
		if(chosen==null)
			return null;
		
		System.out.println("Choose kit number of player to substitute the player.");
		
		List <Player> players;
		if(chosen instanceof Goalkeeper)
		{
			players=homeTeam.getAvailableGoalkeepers();
			for(Player p : players)
			{
				System.out.println("Kit number: "+p.getKitNumber()+" | "+p.getFirstName()+" "+p.getSecondName()+" | Defending: "+Math.round(((Defender)p).getDefending())+" | Passing: "+Math.round(((Defender)p).getPassing()));
			}
			
			int input = scan.nextInt();
			
			for(Player p : players)
			{
				if(input == p.getKitNumber())
				{
					return p;
				}					
			}
			System.out.println("Wrong kit number");
			return null;
		}
		else if(chosen instanceof Defender)
		{
			players=homeTeam.getAvailableDefenders();
			for(Player p : players)
			{
				System.out.println("Kit number: "+p.getKitNumber()+" | "+p.getFirstName()+" "+p.getSecondName()+" | Defending: "+Math.round(((Defender)p).getDefending())+" | Passing: "+Math.round(((Defender)p).getPassing()));
			}
			
			int input = scan.nextInt();
			
			for(Player p : players)
			{
				if(input == p.getKitNumber())
				{
					return p;
				}					
			}
			System.out.println("Wrong kit number");
			return null;
			
			
		}
		else if(chosen instanceof Midfielder)
		{
			players=homeTeam.getAvailableMidfielders();
			for(Player p : players)
			{
				System.out.println("Kit number: "+p.getKitNumber()+" | "+p.getFirstName()+" "+p.getSecondName()+" | Passing: "+Math.round(((Midfielder)p).getPassing()));
			}
			
			int input = scan.nextInt();
			
			for(Player p : players)
			{
				if(input == p.getKitNumber())
				{
					
					return p;
				}					
			}
			System.out.println("Wrong kit number");
			return null;
		}
		else if(chosen instanceof Forward)
		{
			players=homeTeam.getAvailableForwards();
			for(Player p : players)
			{
				System.out.println("Kit number: "+p.getKitNumber()+" | "+p.getFirstName()+" "+p.getSecondName()+" | Shooting: "+Math.round(((Forward)p).getShooting())+" | Dribling: "+Math.round(((Forward)p).getDribling()));
			}
			
			int input = scan.nextInt();
			
			for(Player p : players)
			{
				if(input == p.getKitNumber())
				{
					
					return p;
				}					
			}
			System.out.println("Wrong kit number");
			return null;
		}
		
		return null;
		
		
	
	}
	
	
	public int returnPosition(Player p)
	{
		int numberOfPlayers=startingElevenMyTeam.size();
		
		for(int i = 0 ; i < numberOfPlayers ; i++)
		{
			if(p.getKitNumber()==startingElevenMyTeam.get(i).getKitNumber())
			{
				
				return i;
			}
		}
		return 0;
	}
	
	public void substitute(Goalkeeper g1,Goalkeeper g2)
	{
		Player goalie=(Player)g1; //upcasting
		
	}
	
	
	
	//overloading 
	public void substitute(Defender d1,Defender d2) 
	{
		int pozicia = returnPosition(d1);
		
		List <Player> players=homeTeam.getAvailableDefenders();
		
		startingElevenMyTeam.remove(pozicia);
		startingElevenMyTeam.add(pozicia, d2);
		players.remove(d2);
		players.add(d1);
		
	}
	//overloading
	public void substitute(Midfielder m1,Midfielder m2) 
	{
		int pozicia = returnPosition(m1);
		
		List <Player> players=homeTeam.getAvailableMidfielders();
		
		startingElevenMyTeam.remove(pozicia);
		startingElevenMyTeam.add(pozicia, m2);
		players.remove(m2);
		players.add(m1);
		
	}
	//overloading 
	public void substitute(Forward f1,Forward f2) 
	{
		
		int pozicia = returnPosition(f1);
		
		List <Player> players=homeTeam.getAvailableForwards();
		
		startingElevenMyTeam.remove(pozicia);
		startingElevenMyTeam.add(pozicia, f2);
		players.remove(f2);
		players.add(f1);
	}

	
	
	public void pass(Player passing,Player recieving,Player defending,Class c) // funkcia ktor� rie�i problematiku prihr�vania
	{
		int passValue; 
		Random rnd = new Random();
		int random = (rnd.nextInt(60)+40); 
		passing.setHasBall(false);	//hrac ktory prihrava, prichadza o loptu
		
		if(c==Goalkeeper.class)
		{	
			passValue = (int)((Goalkeeper)passing).getPassing();
		}
		else if(c == Defender.class)
		{
			passValue = (int)((Defender)passing).getPassing();
		}
		else
		{
			passValue = (int)((Midfielder)passing).getPassing();
		}	
		
		
		if(random>passValue) // if misspass , prihrava zle ak je ranomd va�ia hodnota ako passing daneho hra�a
		{
			
			defending.setHasBall(true);
			System.out.println("- Missed pass on "+recieving.getFirstName()+" "+recieving.getSecondName()+"  .The pass was caught by "+defending.getFirstName()+" "+defending.getSecondName());
		}
		else //if accurate pass
		{
			recieving.setHasBall(true);
			System.out.println("- Accurate pass to "+recieving.getFirstName()+" "+recieving.getSecondName());
		}	
	}

	
	
	
	public int forwardVsDefender(Player forward,Player defender) //problematika prech�dzania cez obrancov
	{
		int defValue,dribValue;
		
		defValue = (int)((Defender)defender).getDefending();
		
		dribValue = (int)((Forward)forward).getDribling();

		if(dribValue > defValue)
		{
			System.out.println("- "+forward.getFirstName()+" "+forward.getSecondName()+" has dribbled past "+defender.getFirstName()+" "+defender.getSecondName());
			return 1;
		}	
		else 
		{
			forward.setHasBall(false);
			defender.setHasBall(true);
			System.out.println("- "+defender.getFirstName()+" "+defender.getSecondName()+" has taken the ball from "+forward.getFirstName()+" "+forward.getSecondName());

			return 0;
		}
			
	}
	
	public void shot(Player shooter, Player goalkeeper) //strielanie na br�nu
	{
		int shootingValue,goalkeepingValue;
		boolean homePlayer;
		
		shootingValue = (int)((Forward)shooter).getShooting();
		goalkeepingValue = (int)((Goalkeeper)goalkeeper).getGoalkeeping();
		
		if(startingElevenMyTeam.contains(shooter)) //ak strila domaci uto�nik
			homePlayer=true;
		else
			homePlayer=false;
			
		
		Random rnd = new Random();
		int random = rnd.nextInt(60)+40;
		int random2 = rnd.nextInt(60)+40;
		
		shooter.setHasBall(false);
			if(random < shootingValue && homePlayer)//ak trafil branku domaci
			{
				
				if(random2 > goalkeepingValue) // ak brankar nechytil strelu domaceho
				{
					skoreHome++;
					
					startingElevenAwayTeam.get(5).setHasBall(true);
					System.out.println("- "+shooter.getFirstName()+" "+shooter.getSecondName()+" shoots and SCORES!!!!");
					System.out.println("- Score is "+skoreHome+" : "+skoreAway);
				}
				else //ak chyti
				{
					System.out.println("- "+shooter.getFirstName()+" "+shooter.getSecondName()+" shoots but "+goalkeeper.getFirstName()+" "+goalkeeper.getSecondName()+" has made great save!!!");
					goalkeeper.setHasBall(true);
				}		
			}
			else if(random > shootingValue && homePlayer)// ak netrafil branku domaci
			{
				goalkeeper.setHasBall(true);
				System.out.println("- "+shooter.getFirstName()+" "+shooter.getSecondName()+" shoots but misses the goal");
			}
			else if(random < shootingValue && !homePlayer)// ak trafi branku hostujuci hrac
			{
				if(random2 > goalkeepingValue) // ak brankar nechytil strelu hostujuceho 
				{
					skoreAway++;
					
					startingElevenMyTeam.get(5).setHasBall(true);
					System.out.println("- "+shooter.getFirstName()+" "+shooter.getSecondName()+" shoots and SCORES!!!!");
					System.out.println("- Score is "+skoreHome+" : "+skoreAway);
				}
				else //ak chyti
				{
					System.out.println("- "+shooter.getFirstName()+" "+shooter.getSecondName()+" shoots but "+goalkeeper.getFirstName()+" "+goalkeeper.getSecondName()+" has made great save!!!");
					goalkeeper.setHasBall(true);
					
				}		
			}
			else //ak hostujuci netrafi branku
			{
				goalkeeper.setHasBall(true);
				System.out.println("- "+shooter.getFirstName()+" "+shooter.getSecondName()+" shoots but misses the goal");
			}

		
	}
	
	public void startTheMatch() //metoda ziskavajuca priebeh zapasu
	{
		int numberOfSubs;
		int numberOfActions = 25; //po�et akcii zapasu
		Random rnd = new Random();
		
		int numberOfPlayers = startingElevenMyTeam.size();
		
		int drible;
		
		startingElevenMyTeam.get(5).setHasBall(true);//na za�iatok ma loptu hra� z mojho teamu
		
		System.out.println("The match has started");
		
		while(numberOfActions>0)
		{
			if(numberOfActions == 17)
			{
				System.out.println("Choose how many substitutions you want to make");
				
				numberOfSubs=scan.nextInt();
				substitution(numberOfSubs);
			}
				
			System.out.println("\n"); 
			for(int i = 0 ; i < numberOfPlayers ; i++)
			{
				
				if(startingElevenMyTeam.get(i).getHasBall() == true && startingElevenMyTeam.get(i) instanceof Goalkeeper) 		//ak m� moj brank�r loptu
				{
					System.out.println(startingElevenMyTeam.get(i).getFirstName()+" "+startingElevenMyTeam.get(i).getSecondName()+" has the ball");
					
					int random = (rnd.nextInt(numberOfPlayers-1-i)+(i+1));	
					
					pass(startingElevenMyTeam.get(i),startingElevenMyTeam.get(random),startingElevenAwayTeam.get(numberOfPlayers-i-1),Goalkeeper.class);	//tak prihr�va n�hodn�mu hr��ovi pred n�m
				
				}
				else if(startingElevenMyTeam.get(i).getHasBall()==true && startingElevenMyTeam.get(i) instanceof Defender)		// ak m� moj obr�nca loptu
				{
					
					System.out.println(startingElevenMyTeam.get(i).getFirstName()+" "+startingElevenMyTeam.get(i).getSecondName()+" has the ball");	
					
					int random = (rnd.nextInt(numberOfPlayers-1-i)+(i+1));	
					
					pass(startingElevenMyTeam.get(i),startingElevenMyTeam.get(random),startingElevenAwayTeam.get(numberOfPlayers-i-1),Defender.class); //prihr�va n�hodn�mu hr��ovi pred n�m
					
				}
				else if(startingElevenMyTeam.get(i).getHasBall()==true && startingElevenMyTeam.get(i) instanceof Midfielder)//podobne aj pre stredn�ch z�lo�n�kov
				{
					
					System.out.println(startingElevenMyTeam.get(i).getFirstName()+" "+startingElevenMyTeam.get(i).getSecondName()+" has the ball");	
					
					int random = (rnd.nextInt(numberOfPlayers-1-i)+(i+1));	
					
					pass(startingElevenMyTeam.get(i),startingElevenMyTeam.get(random),startingElevenAwayTeam.get(numberOfPlayers-i-1),Midfielder.class);
					
				}
				else if(startingElevenMyTeam.get(i).getHasBall()==true && startingElevenMyTeam.get(i) instanceof Forward)	
				{
					
					System.out.println(startingElevenMyTeam.get(i).getFirstName()+" "+startingElevenMyTeam.get(i).getSecondName()+" has the ball");	
					
					drible=forwardVsDefender(startingElevenMyTeam.get(i),startingElevenAwayTeam.get(numberOfPlayers-i));	//prech�dza cez hr�� ktor� je mu pridelen�(prideleny je vzdy obranca)
					
					if(drible == 1)//ak pre�iel
					{
						shot(startingElevenMyTeam.get(i),startingElevenAwayTeam.get(0));// vystrel� na branku
					}	
					
				}
				else if(startingElevenAwayTeam.get(i).getHasBall()==true && startingElevenAwayTeam.get(i) instanceof Goalkeeper) //ak nepriatelsky brankar ma loptu
				{
					
					System.out.println(startingElevenAwayTeam.get(i).getFirstName()+" "+startingElevenAwayTeam.get(i).getSecondName()+" has the ball");	
					
					int random = (rnd.nextInt(numberOfPlayers-1-i)+(i+1));	
					
					pass(startingElevenAwayTeam.get(i),startingElevenAwayTeam.get(random),startingElevenMyTeam.get(numberOfPlayers-i-1),Goalkeeper.class);	
					
				}
				else if(startingElevenAwayTeam.get(i).getHasBall()==true && startingElevenAwayTeam.get(i) instanceof Defender) 
				{
					
					System.out.println(startingElevenAwayTeam.get(i).getFirstName()+" "+startingElevenAwayTeam.get(i).getSecondName()+" has the ball");	
					
					int random = (rnd.nextInt(numberOfPlayers-1-i)+(i+1));
					
					pass(startingElevenAwayTeam.get(i),startingElevenAwayTeam.get(random),startingElevenMyTeam.get(numberOfPlayers-i-1),Defender.class);
					
				}
				else if(startingElevenAwayTeam.get(i).getHasBall()==true && startingElevenAwayTeam.get(i) instanceof Midfielder) 
				{
					System.out.println(startingElevenAwayTeam.get(i).getFirstName()+" "+startingElevenAwayTeam.get(i).getSecondName()+" has the ball");	
					
					int random = (rnd.nextInt(numberOfPlayers-1-i)+(i+1));	
					
					pass(startingElevenAwayTeam.get(i),startingElevenAwayTeam.get(random),startingElevenMyTeam.get(numberOfPlayers-i-1),Midfielder.class);	
					
				}
				else if(startingElevenAwayTeam.get(i).getHasBall()==true && startingElevenAwayTeam.get(i) instanceof Forward)
				{
					
					System.out.println(startingElevenAwayTeam.get(i).getFirstName()+" "+startingElevenAwayTeam.get(i).getSecondName()+" has the ball");	
					
					drible=forwardVsDefender(startingElevenAwayTeam.get(i),startingElevenMyTeam.get(numberOfPlayers-i));
					
					if(drible == 1)
					{
						shot(startingElevenAwayTeam.get(i),startingElevenMyTeam.get(0));
					}
					
				}	
			}	
			--numberOfActions;
		}
		if(skoreHome < skoreAway)
		{
			System.out.println("\nFinal whistle!. The result is "+skoreHome+" : "+skoreAway+" The winner is "+awayName+awayTeam.toString());
			
		}
	
		else if(skoreHome == skoreAway)
			System.out.println("\nFinal whistle!. The result is "+skoreHome+" : "+skoreAway);
		else
			System.out.println("\nFinal whistle!. The result is "+skoreHome+" : "+skoreAway+" The winner is "+homeName);
	}
	
}
