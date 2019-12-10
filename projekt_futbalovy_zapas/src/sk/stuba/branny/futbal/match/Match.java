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

	
	public void substitute(Defender defender1, Defender defender2) {
		List<Player> players = homeTeam.getAvailableDefenders();

		exchangePlayers(players, defender1, defender2);

		System.out.println("SUBSTITUTION" + "\nON: " + defender2.getFirstName() + " " + defender2.getSecondName() + "\nOFF: "
				+ defender1.getFirstName() + " " + defender1.getSecondName());
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

	
	public boolean forwardVsDefender(Player forward, Player defender) {
		int defValue, dribValue;

		defValue = (int) ((Defender) defender).getDefending();

		dribValue = (int) ((Forward) forward).getDribling();

		if (dribValue > defValue) {
			System.out.println("- " + forward.getFirstName() + " " + forward.getSecondName() + " has dribbled past "
					+ defender.getFirstName() + " " + defender.getSecondName());
			return true;
		} 
		else {
			forward.setHasBall(false);
			defender.setHasBall(true);
			System.out.println("- " + defender.getFirstName() + " " + defender.getSecondName()
					+ " has taken the ball from " + forward.getFirstName() + " " + forward.getSecondName());

			return false;
		}
	}
	
	public void shot(Player shooter, Player goalkeeper) {
		int shootingValue, goalkeepingValue;
		boolean homePlayer;

		shootingValue = (int) ((Forward) shooter).getShooting();
		goalkeepingValue = (int) ((Goalkeeper) goalkeeper).getGoalkeeping();

		if (startingElevenMyTeam.contains(shooter)) { // If shooter is in my team
			homePlayer = true;
		} 
		else {
			homePlayer = false;
		}

		Random rnd = new Random();
		int random = rnd.nextInt(60) + 40;
		int random2 = rnd.nextInt(60) + 40;

		shooter.setHasBall(false);
		if (homePlayer) {
			if (random <= shootingValue){		// If my forward hits the post
			
				if (random2 > goalkeepingValue) {
					skoreHome++;

					startingElevenAwayTeam.get(5).setHasBall(true);
					System.out.println(
							"- " + shooter.getFirstName() + " " + shooter.getSecondName() + " shoots and SCORES!!!!");
					System.out.println("- Score is " + skoreHome + " : " + skoreAway);
				} 
				else {
					System.out.println("- " + shooter.getFirstName() + " " + shooter.getSecondName() + " shoots but "
							+ goalkeeper.getFirstName() + " " + goalkeeper.getSecondName() + " has made great save!!!");
					goalkeeper.setHasBall(true);
				}
			} 
			else {
				goalkeeper.setHasBall(true);
				System.out.println(
						"- " + shooter.getFirstName() + " " + shooter.getSecondName() + " shoots but misses the goal");
			}
		} 
		else if (!homePlayer) {
			if (random <= shootingValue){		// If enemy forward hits the post
			
				if (random2 > goalkeepingValue) {
					skoreAway++;

					startingElevenMyTeam.get(5).setHasBall(true);
					System.out.println(
							"- " + shooter.getFirstName() + " " + shooter.getSecondName() + " shoots and SCORES!!!!");
					System.out.println("- Score is " + skoreHome + " : " + skoreAway);
				}
				else {
					System.out.println("- " + shooter.getFirstName() + " " + shooter.getSecondName() + " shoots but "
							+ goalkeeper.getFirstName() + " " + goalkeeper.getSecondName() + " has made great save!!!");
					goalkeeper.setHasBall(true);

				}
			} 
			else {
				goalkeeper.setHasBall(true);
				System.out.println(
						"- " + shooter.getFirstName() + " " + shooter.getSecondName() + " shoots but misses the goal");
			}
		}
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
	
	public void passingSituation(int playerPosition, List<Player> team1, List<Player> team2) {
		
		System.out.println(team1.get(playerPosition).getFirstName() + " "
				+ team1.get(playerPosition).getSecondName() + " has the ball");
		
		Random rnd = new Random();
		int numberOfPlayers=team1.size();
		int random = (rnd.nextInt(numberOfPlayers - 1 - playerPosition) + (playerPosition + 1));
		
		pass(team1.get(playerPosition), team1.get(random), team2.get(numberOfPlayers - playerPosition - 1)); 
	}
	
	public void startTheMatch() {

		int numberOfSubs;
		int numberOfActions = 20;

		int numberOfPlayers = startingElevenMyTeam.size();
		boolean dribbled;

		startingElevenMyTeam.get(5).setHasBall(true);

		System.out.println("The match has started");

		while (numberOfActions > 0) {

			if (numberOfActions == 15) {
				System.out.println("Choose how many substitutions you want to make");

				numberOfSubs = scan.nextInt();
				substitution(numberOfSubs);
			}

			System.out.println("\n");
			for (int position = 0; position < numberOfPlayers; position++) {
				if (startingElevenMyTeam.get(position).getHasBall() == true) {
					
					if (startingElevenMyTeam.get(position) instanceof Forward) {

						System.out.println(startingElevenMyTeam.get(position).getFirstName() + " "
								+ startingElevenMyTeam.get(position).getSecondName() + " has the ball");

						dribbled = forwardVsDefender(startingElevenMyTeam.get(position),
								startingElevenAwayTeam.get(numberOfPlayers - position));

						if (dribbled) {
							shot(startingElevenMyTeam.get(position), startingElevenAwayTeam.get(0));
						}

					}
					else{

						passingSituation(position, startingElevenMyTeam, startingElevenAwayTeam);
					} 

				} 
				else if (startingElevenAwayTeam.get(position).getHasBall() == true) {

					if (startingElevenAwayTeam.get(position) instanceof Forward) {

						System.out.println(startingElevenAwayTeam.get(position).getFirstName() + " "
								+ startingElevenAwayTeam.get(position).getSecondName() + " has the ball");

						dribbled = forwardVsDefender(startingElevenAwayTeam.get(position),
								startingElevenMyTeam.get(numberOfPlayers - position));

						if (dribbled) {
							shot(startingElevenAwayTeam.get(position), startingElevenMyTeam.get(0));
						}

					}
					else{
					
						passingSituation(position, startingElevenAwayTeam, startingElevenMyTeam);
					}
				}

			}
			--numberOfActions;
		}

		if (skoreHome < skoreAway) {
			System.out.println("\nFinal whistle!. The result is " + skoreHome + " : " + skoreAway + " The winner is "
					+ awayName + awayTeam.toString());
		} else if (skoreHome == skoreAway) {
			System.out.println("\nFinal whistle!. The result is " + skoreHome + " : " + skoreAway);
		} else {
			System.out.println(
					"\nFinal whistle!. The result is " + skoreHome + " : " + skoreAway + " The winner is " + homeName);
		}

	}
	
}
