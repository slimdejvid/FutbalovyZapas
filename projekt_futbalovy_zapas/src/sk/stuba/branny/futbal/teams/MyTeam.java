package sk.stuba.branny.futbal.teams;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

import sk.stuba.branny.futbal.players.*;

public class MyTeam implements Teams {
	private String teamName = "FC Barcelona";

	private List<Player> availableGoalkeepers;
	private List<Player> availableDefenders;
	private List<Player> availableMidfielders;
	private List<Player> availableForwards;

	private List<Player> startingElevenMyTeam;

	Scanner scanKitNumber = new Scanner(System.in);

	public MyTeam() {
		availableGoalkeepers = new ArrayList<>();
		availableDefenders = new ArrayList<>();
		availableMidfielders = new ArrayList<>();
		availableForwards = new ArrayList<>();
		startingElevenMyTeam = new ArrayList<>();

		initPlayers();

		System.out.println(teamName + "\n");
		System.out.println("Let's choose your team : \n");

		chooseGoalkeeper();
		chooseDefenders();
		chooseMidfielders();
		chooseForwards();
		printTeam();
	}

	@Override
	public void initPlayers() {

		Player g1 = new Goalkeeper("Marc-André", "Ter Stegen", 1, 70, 85);
		Player g2 = new Goalkeeper("Norberto", "Neto", 13, 65, 80);

		Player d1 = new Defender("Gerard", "Pique", 3, 84, 72);
		Player d2 = new Defender("Nelsón", "Semedo", 2, 75, 69);
		Player d3 = new Defender("Samuel", "Umtiti", 23, 83, 69);
		Player d4 = new Defender("Jordi", "Alba", 18, 77, 81);
		Player d5 = new Defender("Clément", "Lenglet", 15, 83, 65);
		Player d6 = new Defender("Sergio", "Roberto", 20, 75, 81);

		Player m1 = new Midfielder("Frenkie", "De Jong", 21, 84);
		Player m2 = new Midfielder("Ivan", "Rakitiæ", 4, 82);
		Player m3 = new Midfielder("Sergio", "Busquets", 5, 80);
		Player m4 = new Midfielder("Arthur", "Melo", 8, 81);
		Player m5 = new Midfielder("Arturo", "Vidal", 22, 80);

		Player f1 = new Forward("Lionel", "Messi", 10, 94, 98);
		Player f2 = new Forward("Luis", "Suaréz", 9, 89, 84);
		Player f3 = new Forward("Antoine", "Griezmann", 17, 86, 86);
		Player f4 = new Forward("Ousmane", "Dembélé", 11, 79, 86);

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

	private void chooseGoalkeeper() {

		System.out.println("List of goalkeepers: ");

		printPlayers(availableGoalkeepers);

		int count = 0;

		System.out.println("Choose your goalkeeper\n");

		int input = scanKitNumber.nextInt();
		for (Player p : availableGoalkeepers) {

			if (input != p.getKitNumber()) {
				count++;
				if (count == availableGoalkeepers.size()) {
					Random rnd = new Random();
					p = availableGoalkeepers.get(rnd.nextInt(availableGoalkeepers.size()));
					startingElevenMyTeam.add(p);
					availableGoalkeepers.remove(p);

					System.out.println("There is no goalkeeper with this number, we chose " + p.getFirstName() + " "
							+ p.getSecondName() + ".");
				}
			} else {
				startingElevenMyTeam.add(p);
				availableGoalkeepers.remove(p);
				System.out.println("Goalkeeper chosen\n");
				break;
			}

		}

	}

	private void chooseDefenders() {

		int numberOfValidInputs = 4;
		int input;
		boolean find;

		System.out.println("List of defenders: ");

		printPlayers(availableDefenders);

		System.out.println("Choose your defenders\n");

		while (numberOfValidInputs > 0) {
			find = false;
			input = scanKitNumber.nextInt();
			for (Player defender : availableDefenders) {
				if (input == defender.getKitNumber()) {
					find = true;
					numberOfValidInputs--;
					startingElevenMyTeam.add(defender);
					System.out.println("Defender number " + defender.getKitNumber() + " chosen\n");
					availableDefenders.remove(defender);
					if (numberOfValidInputs != 0)
						printPlayers(availableDefenders);
					break;
				}

			}
			if (find == false)
				System.out.println("Invalid kit number, try again");
		}
	}

	private void chooseMidfielders() {

		int numberOfValidInputs = 3;
		int input;
		boolean find;

		System.out.println("List of midfielders: ");

		printPlayers(availableMidfielders);

		System.out.println("Choose your midfielders\n");

		while (numberOfValidInputs > 0) {
			find = false;
			input = scanKitNumber.nextInt();
			for (Player p : availableMidfielders) {
				if (input == p.getKitNumber()) {
					find = true;
					--numberOfValidInputs;
					startingElevenMyTeam.add(p);
					System.out.println("Midfielder number " + p.getKitNumber() + " chosen\n");
					availableMidfielders.remove(p);
					if (numberOfValidInputs != 0)
						printPlayers(availableMidfielders);
					break;
				}

			}
			if (find == false)
				System.out.println("Invalid kit number, try again");
		}
	}

	private void chooseForwards() {

		int numberOfValidInputs = 3;
		int input;
		boolean find;

		System.out.println("List of forwards: ");

		printPlayers(availableForwards);

		System.out.println("Choose your forwards\n");

		while (numberOfValidInputs > 0) {
			find = false;
			input = scanKitNumber.nextInt();
			for (Player p : availableForwards) {
				if (input == p.getKitNumber()) {
					find = true;
					--numberOfValidInputs;
					startingElevenMyTeam.add(p);
					System.out.println("Forward number " + p.getKitNumber() + " chosen\n");
					availableForwards.remove(p);
					if (numberOfValidInputs != 0)
						printPlayers(availableForwards);
					break;
				}

			}
			if (find == false)
				System.out.println("Invalid kit number, try again");
		}

	}

	private void printPlayers(List<Player> pole) {
		for (Player p : pole)
			p.printAttributes();
	}

	@Override
	public void printTeam() {
		System.out.println("\n" + teamName + "\n");
		for (Player p : startingElevenMyTeam) {
			p.printAttributes();
		}
		System.out.println("\n");
	}

	public List<Player> getStartingElevenMyTeam() {
		return this.startingElevenMyTeam;
	}

	public String getTeamName() {
		return this.teamName;
	}

	public List<Player> getAvailableGoalkeepers() {
		return this.availableGoalkeepers;
	}

	public List<Player> getAvailableDefenders() {
		return this.availableDefenders;
	}

	public List<Player> getAvailableMidfielders() {
		return this.availableMidfielders;
	}

	public List<Player> getAvailableForwards() {
		return this.availableForwards;
	}

}
