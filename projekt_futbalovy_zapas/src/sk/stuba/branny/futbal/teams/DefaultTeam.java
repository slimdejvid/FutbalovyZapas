package sk.stuba.branny.futbal.teams;

import java.util.ArrayList;
import java.util.List;

import sk.stuba.branny.futbal.players.*;

public class DefaultTeam implements Teams {
	private String teamName = "FC Bayern Munich";

	private List<Player> startingElevenBayern;

	public DefaultTeam() {
		startingElevenBayern = new ArrayList<>();

		initPlayers();

		printTeam();
	}

	@Override
	public void initPlayers() {
		Player g1 = new Goalkeeper("Manuel", "Neuer", 1, 70, 90);

		Player d1 = new Defender("Niklas", "Süle", 4, 84, 50);
		Player d2 = new Defender("Lucas", "Hernández", 21, 80, 70);
		Player d3 = new Defender("Joshua", "Kimmich", 32, 77, 86);
		Player d4 = new Defender("David", "Alaba", 27, 78, 81);
		
		Player m1 = new Midfielder("Thiago", "Alcântara", 6, 85);
		Player m2 = new Midfielder("Ivan", "Perišiæ", 14, 76);
		Player m3 = new Midfielder("Serge", "Gnabry", 22, 72);

		Player f1 = new Forward("Phillipe", "Coutinho", 6, 78, 92);
		Player f2 = new Forward("Thomas", "Müller", 25, 83, 84);
		Player f3 = new Forward("Robert", "Lewandowski", 9, 93, 81);
		
		startingElevenBayern.add(g1);

		startingElevenBayern.add(d1);
		startingElevenBayern.add(d2);
		startingElevenBayern.add(d3);
		startingElevenBayern.add(d4);

		startingElevenBayern.add(m1);
		startingElevenBayern.add(m2);
		startingElevenBayern.add(m3);

		
		startingElevenBayern.add(f1);
		startingElevenBayern.add(f2);
		startingElevenBayern.add(f3);

	}

	@Override
	public void printTeam() {
		System.out.println("\n" + teamName + "\n");
		for (Player p : startingElevenBayern) {
			p.printAttributes();
		}
		System.out.println("\n");
	}

	public List<Player> getStartingElevenAwayTeam() {
		return this.startingElevenBayern;
	}

	public String getTeamName() {
		return this.teamName;
	}

	@Override
	public String toString() {

		return " \"We knew we can beat FCBarcelona\".";
	}

}
