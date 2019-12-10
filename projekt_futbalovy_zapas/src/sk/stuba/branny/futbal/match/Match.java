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
	private static final int maxNumberOfSubs = 3;

	private MyTeam homeTeam;
	private DefaultTeam awayTeam;

	Scanner scan = new Scanner(System.in);

	private String homeName;
	private String awayName;

	private List<Player> startingElevenMyTeam;
	private List<Player> startingElevenAwayTeam;

	private int skoreHome;
	private int skoreAway;

	private Match() {
		this.skoreHome = 0;
		this.skoreAway = 0;
	}

	public static Match getInstance() {
		if (instance == null) {
			instance = new Match();

		}
		return instance;
	}

	public void setHomeTeam(MyTeam homeTeam) {
		this.homeTeam = homeTeam;
		this.startingElevenMyTeam = homeTeam.getStartingElevenMyTeam();
		this.homeName = homeTeam.getTeamName();
	}

	public void setAwayTeam(DefaultTeam awayTeam) {
		this.awayTeam = awayTeam;
		this.startingElevenAwayTeam = awayTeam.getStartingElevenAwayTeam();
		this.awayName = awayTeam.getTeamName();
	}

	public void substitution(int numberOfSubs) {

		Player p1, p2;

		if (numberOfSubs > maxNumberOfSubs)
			numberOfSubs = 3;

		while (numberOfSubs > 0) {
			p1 = choosePlayerToBeSub();
			p2 = choosePlayerToSub(p1);
			if (p2 == null) {
				continue;
			} else if (p1 instanceof Goalkeeper) {
				substitute((Goalkeeper) p1, (Goalkeeper) p2);
			} else if (p1 instanceof Defender) {
				substitute((Defender) p1, (Defender) p2);
			} else if (p1 instanceof Midfielder) {
				substitute((Midfielder) p1, (Midfielder) p2);
			} else if (p1 instanceof Forward) {
				substitute((Forward) p1, (Forward) p2);
			}
			numberOfSubs--;
		}
	}

	public Player choosePlayerToBeSub() {

		System.out.println("Choose kit number of player to be substituted.");
		int input = scan.nextInt();

		for (Player p : startingElevenMyTeam) {
			if (p.getKitNumber() == input) {
				return p;
			}

		}
		System.out.println("There is no player with this number.");
		return null;

	}

	public Player choosePlayerToSub(Player chosen) {
		if (chosen == null)
			return null;

		System.out.println("Choose kit number of player to substitute the player.");

		List<Player> players;
		if (chosen instanceof Goalkeeper) {
			players = homeTeam.getAvailableGoalkeepers();
			for (Player p : players) {
				p.printAttributes();
			}
			int input = scan.nextInt();

			for (Player p : players) {
				if (input == p.getKitNumber()) {
					return p;
				}
			}
			System.out.println("Wrong kit number");
			return null;
		} 
		else if (chosen instanceof Defender) {
			players = homeTeam.getAvailableDefenders();
			for (Player p : players) {
				p.printAttributes();
			}

			int input = scan.nextInt();

			for (Player p : players) {
				if (input == p.getKitNumber()) {
					return p;
				}
			}
			System.out.println("Wrong kit number");
			return null;

		} 
		else if (chosen instanceof Midfielder) {
			players = homeTeam.getAvailableMidfielders();
			for (Player p : players) {
				p.printAttributes();
			}

			int input = scan.nextInt();

			for (Player p : players) {
				if (input == p.getKitNumber()) {

					return p;
				}
			}
			System.out.println("Wrong kit number");
			return null;
		} 
		else if (chosen instanceof Forward) {
			players = homeTeam.getAvailableForwards();
			for (Player p : players) {
				p.printAttributes();
			}

			int input = scan.nextInt();

			for (Player p : players) {
				if (input == p.getKitNumber()) {

					return p;
				}
			}
			System.out.println("Wrong kit number");
			return null;
		}

		return null;
	}

	public int returnPosition(Player p) {
		int numberOfPlayers = startingElevenMyTeam.size();

		for (int i = 0; i < numberOfPlayers; i++) {
			if (p.getKitNumber() == startingElevenMyTeam.get(i).getKitNumber()) {
				return i;
			}
		}
		return 0;
	}

	public void exchangePlayers(List<Player> players, Player player1, Player player2) {
		int pozicia =returnPosition(player1);
		startingElevenMyTeam.remove(pozicia);
		startingElevenMyTeam.add(pozicia, player2);
		players.remove(player2);
		players.add(player1);
		
	}
	
	public void substitute(Goalkeeper g1, Goalkeeper g2) {
		Player goalie = (Player) g1;
		List<Player> players = homeTeam.getAvailableGoalkeepers();
		startingElevenMyTeam.remove(goalie);
		startingElevenMyTeam.add(0, g2);
		players.remove(g2);
		players.add(goalie);

		System.out.println("SUBSTITUTION" + "\nON: " + g2.getFirstName() + " " + g2.getSecondName() + "\nOFF: "
				+ goalie.getFirstName() + " " + goalie.getSecondName());

	}

	public void substitute(Defender d1, Defender d2) {
		int pozicia = returnPosition(d1);

		List<Player> players = homeTeam.getAvailableDefenders();

		startingElevenMyTeam.remove(pozicia);
		startingElevenMyTeam.add(pozicia, d2);
		players.remove(d2);
		players.add(d1);

		System.out.println("SUBSTITUTION" + "\nON: " + d2.getFirstName() + " " + d2.getSecondName() + "\nOFF: "
				+ d1.getFirstName() + " " + d1.getSecondName());
	}

	public void substitute(Midfielder midfielder1, Midfielder midfielder2) {
		
		List<Player> players = homeTeam.getAvailableMidfielders();
		
		exchangePlayers(players, midfielder1, midfielder2);

		System.out.println("SUBSTITUTION" + "\nON: " + midfielder2.getFirstName() + " " + midfielder2.getSecondName() + "\nOFF: "
				+ midfielder1.getFirstName() + " " + midfielder1.getSecondName());

	}

	public void substitute(Forward forward1, Forward forward2) {

		

		List<Player> players = homeTeam.getAvailableForwards();

		exchangePlayers(players, forward1, forward2);

		System.out.println("SUBSTITUTION" + "\nON: " + forward2.getFirstName() + " " + forward2.getSecondName() + "\nOFF: "
				+ forward1.getFirstName() + " " + forward1.getSecondName());
	}

	public void pass(Player passing, Player recieving, Player defending) {
		int passValue;
		Random rnd = new Random();
		int random = (rnd.nextInt(60) + 40);
		
		passing.setHasBall(false);

		passValue=(int)passing.getPassing();
		
		if (random > passValue) {

			defending.setHasBall(true);
			System.out.println("- Missed pass on " + recieving.getFirstName() + " " + recieving.getSecondName()
					+ "  .The pass was caught by " + defending.getFirstName() + " " + defending.getSecondName());
		} else {
			recieving.setHasBall(true);
			System.out.println("- Accurate pass to " + recieving.getFirstName() + " " + recieving.getSecondName());
		}
	}

	
	
	
	public int forwardVsDefender(Player forward,Player defender) 
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
	
	public void startTheMatch() // metoda ziskavajuca priebeh zapasu
	{
		int numberOfSubs;
		int numberOfActions = 20; // poèet akcii zapasu
		Random rnd = new Random();

		int numberOfPlayers = startingElevenMyTeam.size();

		int drible;

		startingElevenMyTeam.get(5).setHasBall(true);// na zaèiatok ma loptu hraè z mojho teamu

		System.out.println("The match has started");

		while (numberOfActions > 0) {
			if (numberOfActions == 15) {
				System.out.println("Choose how many substitutions you want to make");

				numberOfSubs = scan.nextInt();
				substitution(numberOfSubs);
			}

			System.out.println("\n");
			for (int i = 0; i < numberOfPlayers; i++) {

				if (startingElevenMyTeam.get(i).getHasBall() == true
						&& startingElevenMyTeam.get(i) instanceof Goalkeeper) // ak má moj brankár loptu
				{
					System.out.println(startingElevenMyTeam.get(i).getFirstName() + " "
							+ startingElevenMyTeam.get(i).getSecondName() + " has the ball");

					int random = (rnd.nextInt(numberOfPlayers - 1 - i) + (i + 1));

					pass(startingElevenMyTeam.get(i), startingElevenMyTeam.get(random),
							startingElevenAwayTeam.get(numberOfPlayers - i - 1)); // tak prihráva
																									// náhodnému hráèovi
																									// pred ním

				} else if (startingElevenMyTeam.get(i).getHasBall() == true
						&& startingElevenMyTeam.get(i) instanceof Defender) // ak má moj obránca loptu
				{

					System.out.println(startingElevenMyTeam.get(i).getFirstName() + " "
							+ startingElevenMyTeam.get(i).getSecondName() + " has the ball");

					int random = (rnd.nextInt(numberOfPlayers - 1 - i) + (i + 1));

					pass(startingElevenMyTeam.get(i), startingElevenMyTeam.get(random),
							startingElevenAwayTeam.get(numberOfPlayers - i - 1)); // prihráva náhodnému
																									// hráèovi pred ním

				} else if (startingElevenMyTeam.get(i).getHasBall() == true
						&& startingElevenMyTeam.get(i) instanceof Midfielder)// podobne aj pre stredných záložníkov
				{

					System.out.println(startingElevenMyTeam.get(i).getFirstName() + " "
							+ startingElevenMyTeam.get(i).getSecondName() + " has the ball");

					int random = (rnd.nextInt(numberOfPlayers - 1 - i) + (i + 1));

					pass(startingElevenMyTeam.get(i), startingElevenMyTeam.get(random),
							startingElevenAwayTeam.get(numberOfPlayers - i - 1));

				} else if (startingElevenMyTeam.get(i).getHasBall() == true
						&& startingElevenMyTeam.get(i) instanceof Forward) {

					System.out.println(startingElevenMyTeam.get(i).getFirstName() + " "
							+ startingElevenMyTeam.get(i).getSecondName() + " has the ball");

					drible = forwardVsDefender(startingElevenMyTeam.get(i),
							startingElevenAwayTeam.get(numberOfPlayers - i)); // prechádza cez hráè ktorý je mu
																				// pridelený(prideleny je vzdy obranca)

					if (drible == 1)// ak prešiel
					{
						shot(startingElevenMyTeam.get(i), startingElevenAwayTeam.get(0));// vystrelí na branku
					}

				} else if (startingElevenAwayTeam.get(i).getHasBall() == true
						&& startingElevenAwayTeam.get(i) instanceof Goalkeeper) // ak nepriatelsky brankar ma loptu
				{

					System.out.println(startingElevenAwayTeam.get(i).getFirstName() + " "
							+ startingElevenAwayTeam.get(i).getSecondName() + " has the ball");

					int random = (rnd.nextInt(numberOfPlayers - 1 - i) + (i + 1));

					pass(startingElevenAwayTeam.get(i), startingElevenAwayTeam.get(random),
							startingElevenMyTeam.get(numberOfPlayers - i - 1));

				} else if (startingElevenAwayTeam.get(i).getHasBall() == true
						&& startingElevenAwayTeam.get(i) instanceof Defender) {

					System.out.println(startingElevenAwayTeam.get(i).getFirstName() + " "
							+ startingElevenAwayTeam.get(i).getSecondName() + " has the ball");

					int random = (rnd.nextInt(numberOfPlayers - 1 - i) + (i + 1));

					pass(startingElevenAwayTeam.get(i), startingElevenAwayTeam.get(random),
							startingElevenMyTeam.get(numberOfPlayers - i - 1));

				} else if (startingElevenAwayTeam.get(i).getHasBall() == true
						&& startingElevenAwayTeam.get(i) instanceof Midfielder) {
					System.out.println(startingElevenAwayTeam.get(i).getFirstName() + " "
							+ startingElevenAwayTeam.get(i).getSecondName() + " has the ball");

					int random = (rnd.nextInt(numberOfPlayers - 1 - i) + (i + 1));

					pass(startingElevenAwayTeam.get(i), startingElevenAwayTeam.get(random),
							startingElevenMyTeam.get(numberOfPlayers - i - 1));

				} else if (startingElevenAwayTeam.get(i).getHasBall() == true
						&& startingElevenAwayTeam.get(i) instanceof Forward) {

					System.out.println(startingElevenAwayTeam.get(i).getFirstName() + " "
							+ startingElevenAwayTeam.get(i).getSecondName() + " has the ball");

					drible = forwardVsDefender(startingElevenAwayTeam.get(i),
							startingElevenMyTeam.get(numberOfPlayers - i));

					if (drible == 1) {
						shot(startingElevenAwayTeam.get(i), startingElevenMyTeam.get(0));
					}

				}
			}
			--numberOfActions;
		}
		if (skoreHome < skoreAway) {
			System.out.println("\nFinal whistle!. The result is " + skoreHome + " : " + skoreAway + " The winner is "
					+ awayName + awayTeam.toString());

		}

		else if (skoreHome == skoreAway)
			System.out.println("\nFinal whistle!. The result is " + skoreHome + " : " + skoreAway);
		else
			System.out.println(
					"\nFinal whistle!. The result is " + skoreHome + " : " + skoreAway + " The winner is " + homeName);
	}
	
}
