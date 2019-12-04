package sk.stuba.branny.futbal;

import java.util.Scanner;

import sk.stuba.branny.futbal.match.Match;
import sk.stuba.branny.futbal.teams.DefaultTeam;
import sk.stuba.branny.futbal.teams.MyTeam;



public class Main {
	public static void main(String[] args) {
		
		System.out.println("If u want to choose your starting team and play, click the \"C\" key");
		Scanner sc = new Scanner(System.in);
		char ipt=sc.next().charAt(0);
		while(ipt != 'c')
		{
			ipt=sc.next().charAt(0);
		}
		
			MyTeam t1 = new MyTeam();
			DefaultTeam t2 = new DefaultTeam();
			Match match = Match.getInstance(); //vytvorenie inštancie matchu cez singleton
			match.setHomeTeam(t1);
			match.setAwayTeam(t2);
			match.startTheMatch();
		
		sc.close();	
	}

}
