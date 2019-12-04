package sk.stuba.branny.futbal.match;

import java.util.List;
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
	private final int pocStriedani = 3; // 
	private MyTeam homeTeam;//agregacia
	private DefaultTeam awayTeam;//agregacia
	
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
	
	public static  Match getInstance() //singleton na vytvorenie jednej inštancie matchu 
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
	
	public void pass(Player passing,Player recieving,Player defending,Class c) // funkcia ktorá rieši problematiku prihrávania
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
		
		
		if(random>passValue) // if misspass , prihrava zle ak je ranomd vaèšia hodnota ako passing daneho hraèa
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

	
	
	
	public int forwardVsDefender(Player forward,Player defender) //problematika prechádzania cez obrancov
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
	
	public void shot(Player shooter, Player goalkeeper) //strielanie na bránu
	{
		int shootingValue,goalkeepingValue;
		boolean homePlayer;
		
		shootingValue = (int)((Forward)shooter).getShooting();
		goalkeepingValue = (int)((Goalkeeper)goalkeeper).getGoalkeeping();
		
		if(startingElevenMyTeam.contains(shooter)) //ak strila domaci utoènik
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
		int numberOfActions = 25; //poèet akcii zapasu
		Random rnd = new Random();
		int numberOfPlayers = startingElevenMyTeam.size();
		int drible;
		startingElevenMyTeam.get(5).setHasBall(true);//na zaèiatok ma loptu hraè z mojho teamu
		System.out.println("The match has started");
		while(numberOfActions>0)
		{
			System.out.println("\n"); 
			for(int i = 0 ; i < numberOfPlayers ; i++)
			{
				
				if(startingElevenMyTeam.get(i).getHasBall() == true && startingElevenMyTeam.get(i) instanceof Goalkeeper) 		//ak má moj brankár loptu
				{
					System.out.println(startingElevenMyTeam.get(i).getFirstName()+" "+startingElevenMyTeam.get(i).getSecondName()+" has the ball");
					
					int random = (rnd.nextInt(numberOfPlayers-1-i)+(i+1));	
					
					pass(startingElevenMyTeam.get(i),startingElevenMyTeam.get(random),startingElevenAwayTeam.get(numberOfPlayers-i-1),Goalkeeper.class);	//tak prihráva náhodnému hráèovi pred ním
				
				}
				else if(startingElevenMyTeam.get(i).getHasBall()==true && startingElevenMyTeam.get(i) instanceof Defender)		// ak má moj obránca loptu
				{
					
					System.out.println(startingElevenMyTeam.get(i).getFirstName()+" "+startingElevenMyTeam.get(i).getSecondName()+" has the ball");	
					
					int random = (rnd.nextInt(numberOfPlayers-1-i)+(i+1));	
					
					pass(startingElevenMyTeam.get(i),startingElevenMyTeam.get(random),startingElevenAwayTeam.get(numberOfPlayers-i-1),Defender.class); //prihráva náhodnému hráèovi pred ním
					
				}
				else if(startingElevenMyTeam.get(i).getHasBall()==true && startingElevenMyTeam.get(i) instanceof Midfielder)//podobne aj pre stredných záložníkov
				{
					
					System.out.println(startingElevenMyTeam.get(i).getFirstName()+" "+startingElevenMyTeam.get(i).getSecondName()+" has the ball");	
					
					int random = (rnd.nextInt(numberOfPlayers-1-i)+(i+1));	
					
					pass(startingElevenMyTeam.get(i),startingElevenMyTeam.get(random),startingElevenAwayTeam.get(numberOfPlayers-i-1),Midfielder.class);
					
				}
				else if(startingElevenMyTeam.get(i).getHasBall()==true && startingElevenMyTeam.get(i) instanceof Forward)	
				{
					
					System.out.println(startingElevenMyTeam.get(i).getFirstName()+" "+startingElevenMyTeam.get(i).getSecondName()+" has the ball");	
					
					drible=forwardVsDefender(startingElevenMyTeam.get(i),startingElevenAwayTeam.get(numberOfPlayers-i));	//prechádza cez hráè ktorý je mu pridelený(prideleny je vzdy obranca)
					
					if(drible == 1)//ak prešiel
					{
						shot(startingElevenMyTeam.get(i),startingElevenAwayTeam.get(0));// vystrelí na branku
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
