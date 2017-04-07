package model;

import java.util.Scanner;

public class GameEngine {

	Scanner reader = new Scanner(System.in);

	Player p1;
	Player ai;
	Pokemon p1Pokemon;
	Pokemon aiPokemon;

	public GameEngine() {
		init();
	}

	private void init() {
		this.p1 = new Player();
		this.ai = new Player();

		this.p1Pokemon = p1.getPokeParty().get(0);
		this.aiPokemon = ai.getPokeParty().get(0);

		startBattleLoop();
	}

	private void startBattleLoop() {
		BeginningPhase();

		while (p1Pokemon.getNextAttack() != -1) {
			SelectPhase();
			if (p1Pokemon.getNextAttack() != -1) {
				AttackPhase();
			}
		}
	}

	private void BeginningPhase() {

		System.out.print("Player 1 is currently named: " + p1.getPlayerName() + "\nEnter new name: ");
		p1.setPlayerName(reader.nextLine());

		ai.setPlayerName("Jebb");

		System.out.println("Player 2 is named: " + ai.getPlayerName() + "\n");

		System.out.println(p1.getPlayerName() + " throws out a " + p1Pokemon.getName() + "\n" + ai.getPlayerName()
				+ " throws out a " + aiPokemon.getName());
	}

	private void SelectPhase() {
		
		System.out.print("Enter A to select an attack or P to select a new pokemon: ");
		
		switch (reader.nextLine()) {
		case "A":
		case "a":
			SelectAttack();
			break;
			
		case "P":
		case "p":
			SelectPokemon();
			break;
			
		default:
			SelectPhase();
			break;
		}

	}

	private void SelectAttack() {
		
		System.out.println("\nTo exit loop enter 0 as your attack for p1\n");
		
		System.out.print(p1.getPlayerName() + " select a move\n" + "\t1: " + p1Pokemon.getMoves().get(0).getName()
				+ "\n" + "\t2: " + p1Pokemon.getMoves().get(1).getName() + "\n" + "\t3: "
				+ p1Pokemon.getMoves().get(2).getName() + "\n" + "\t4: " + p1Pokemon.getMoves().get(3).getName()
				+ "\n");

		p1Pokemon.setNextAttack(Integer.parseInt(reader.nextLine()) - 1);
		if (p1Pokemon.getNextAttack() > -2 && p1Pokemon.getNextAttack() < 4) {
			if (p1Pokemon.getNextAttack() == -1) {
				ai.setMoveSelected(true);
			}
			p1.setMoveSelected(true);
		} else {
			SelectAttack();
		}
	}

	private void SelectPokemon() {
		System.out.println("Select the number associated with the pokemon you want to switch out for ");
		int count = 1;
		for(Pokemon pokemon : p1.getPokeParty()){
			if(pokemon != p1Pokemon){
				System.out.println(count + ": " + pokemon.getName());
				count++;
			}
		}
		
		int tempChosen = Integer.parseInt(reader.nextLine());
		
		if(tempChosen < 5 || tempChosen > 1){			
			
			System.out.print(p1.getPlayerName() + " returned " + p1Pokemon.getName());
			
			p1Pokemon = p1.getPokeParty().get(tempChosen-1);
			
			System.out.println(" and sent out " + p1Pokemon.getName());
		} else {
			SelectPokemon();
		}
	}

	private void AttackPhase() {
		if (p1Pokemon.getSpeed() < aiPokemon.getSpeed()) {
			System.out.println(ai.getPlayerName() + "'s " + aiPokemon.getName() + " does a "
					+ aiPokemon.getMoves().get(aiPokemon.getNextAttack()).getName());
			aiPokemon.attack(p1Pokemon, aiPokemon.getMoves().get(aiPokemon.getNextAttack()));

			if (p1Pokemon.getHp() != 0) {
				System.out.println(p1.getPlayerName() + "'s " + p1Pokemon.getName() + " does a "
						+ p1Pokemon.getMoves().get(p1Pokemon.getNextAttack()).getName());
				p1Pokemon.attack(aiPokemon, p1Pokemon.getMoves().get(p1Pokemon.getNextAttack()));
			}
		} else {
			System.out.println(p1.getPlayerName() + "'s " + p1Pokemon.getName() + " does a "
					+ p1Pokemon.getMoves().get(p1Pokemon.getNextAttack()).getName());
			p1Pokemon.attack(aiPokemon, p1Pokemon.getMoves().get(p1Pokemon.getNextAttack()));

			if (aiPokemon.getHp() != 0) {
				System.out.println(ai.getPlayerName() + "'s " + aiPokemon.getName() + " does a "
						+ aiPokemon.getMoves().get(aiPokemon.getNextAttack()).getName());
				aiPokemon.attack(p1Pokemon, aiPokemon.getMoves().get(aiPokemon.getNextAttack()));
			}
		}

		if (aiPokemon.getHp() == 0) {
			System.out.println(ai.getPlayerName() + "'s " + aiPokemon.getName() + " was hit for 10 and now has "
					+ aiPokemon.getHp() + " HP\n");
			System.out.println(ai.getPlayerName() + "'s " + aiPokemon.getName() + " has fainted!\n" + p1.getPlayerName()
					+ " WINS!!!");
			p1Pokemon.setNextAttack(-1);
		} else if (p1Pokemon.getHp() == 0) {
			System.out.println(p1.getPlayerName() + "'s " + p1Pokemon.getName() + " was hit for 10 and now has "
					+ p1Pokemon.getHp() + " HP");
			System.out.println(p1.getPlayerName() + "'s " + p1Pokemon.getName() + " has fainted!\n" + ai.getPlayerName()
					+ " WINS!!!");
			p1Pokemon.setNextAttack(-1);
		} else {
			System.out.println(p1.getPlayerName() + "'s " + p1Pokemon.getName() + " was hit for 10 and now has "
					+ p1Pokemon.getHp() + " HP\n");

			System.out.println(ai.getPlayerName() + "'s " + aiPokemon.getName() + " was hit for 10 and now has "
					+ aiPokemon.getHp() + " HP");
		}

		p1.setMoveSelected(false);
		ai.setMoveSelected(false);
	}

}
