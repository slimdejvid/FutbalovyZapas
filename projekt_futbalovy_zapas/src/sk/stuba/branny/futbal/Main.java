package sk.stuba.branny.futbal;

import java.util.Scanner;

import sk.stuba.branny.futbal.match.Match;
import sk.stuba.branny.futbal.teams.DefaultTeam;
import sk.stuba.branny.futbal.teams.MyTeam;

public class Main {
	public static void main(String[] args) {

		System.out.println("If you want to choose your starting team and play, click the \"C\" key");
		Scanner scan = new Scanner(System.in);
		char charInput = scan.next().charAt(0);
		while (charInput != 'c') {
			charInput = scan.next().charAt(0);
		}
		MyTeam team1 = new MyTeam();
		DefaultTeam team2 = new DefaultTeam();

		Match match = Match.getInstance();
		match.setHomeTeam(team1);
		match.setAwayTeam(team2);
		match.startTheMatch();

		scan.close();
	}

}
