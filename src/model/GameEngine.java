package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class GameEngine {

	Scanner reader = new Scanner(System.in);

	Player p1;
	Player ai;

	/* current pokemon */
	Pokemon p1Pokemon;
	Pokemon aiPokemon;

	/* need to implement party functionality */
	private ArrayList<Pokemon> p1Party;
	private ArrayList<Pokemon> aiParty;

	/* pokemon are still battling boolean */
	private boolean battling;

	/* Default constructor */
	public GameEngine() {
		this.p1 = new Player();
		this.ai = new Player();

		this.p1Pokemon = p1.getPokeParty().get(0);
		this.aiPokemon = ai.getPokeParty().get(0);

		this.p1Party = p1.getPokeParty();
		this.aiParty = ai.getPokeParty();

		battling = true;
		startBattleLoop();
		// init();
	}

	/*
	 * this seems redundant as well?? moved initializations to constructor
	 */
	// private void init() {
	// this.p1 = new Player();
	// this.ai = new Player();
	//
	// this.p1Pokemon = p1.getPokeParty().get(0);
	// this.aiPokemon = ai.getPokeParty().get(0);
	//
	// this.p1Party = p1.getPokeParty();
	// this.aiParty = ai.getPokeParty();
	//
	// battling = true;
	// startBattleLoop();
	// }

	/* init game loop */
	private void startBattleLoop() {
		// beginning phase to init players/pokemon/party
		BeginningPhase();

		// counter to keep track of # of turns
		int turnCounter = 0;

		// keep looping while pokemon are still battling
		while (battling) {
			// while (p1Pokemon.getNextAttack() != -1) {
			++turnCounter;
			System.out.println("Turn " + turnCounter);
			SelectPhase();
			// if (p1Pokemon.getNextAttack() != -1) {
			AttackPhase();
			// }
			// }
		}
		System.out.println("oh my god the game is over");
	}

	/* SET NAMES // AND INIT PARTIES?! */
	private void BeginningPhase() {

		// System.out.print("Player 1 is currently named: " + p1.getPlayerName()
		// + "\nEnter new name: ");
		// p1.setPlayerName(reader.nextLine());
		p1.setPlayerName("Dragon");

		ai.setPlayerName("Red");

		// System.out.println("Player 2 is named: " + ai.getPlayerName() +
		// "\n");

		// System.out.println(p1.getPlayerName() + " throws out a " +
		// p1Pokemon.getName() + "\n" + ai.getPlayerName() + " throws out a " +
		// aiPokemon.getName());
	}

	/* Phase to select moves or another pokemon */
	private void SelectPhase() {

		p1.setMoveSelected(true);
		ai.setMoveSelected(true);
		// System.out.print("Enter A to select an attack or P to select a new
		// pokemon: ");
		System.out.print(p1.getPlayerName() + " select a move for " + p1Pokemon.getName() + "\n" + "\t1: "
				+ p1Pokemon.getMoves().get(0).getName() + "\n" + "\t2: " + p1Pokemon.getMoves().get(1).getName() + "\n"
				+ "\t3: " + p1Pokemon.getMoves().get(2).getName() + "\n" + "\t4: "
				+ p1Pokemon.getMoves().get(3).getName());
		System.out.print("\nSelect move number, P to select a new pokemon, or 0 to exit: ");

		// switch (reader.nextLine()) {
		// case "A":
		// case "a":
		// SelectAttack();
		// break;
		//
		// case "P":
		// case "p":
		// SelectPokemon();
		// break;
		//
		// default:
		// SelectPhase();
		// break;
		// }
		switch (reader.nextLine()) {
		case "0":
			System.out.println("Break");
			p1Pokemon.setNextAttack(-1);
			this.battling = false;
			break;
		case "1":
			// System.out.println("First attack");
			// SelectAttack(1);
			p1Pokemon.setNextAttack(0);
			break;
		case "2":
			// System.out.println("Second attack");
			p1Pokemon.setNextAttack(1);
			break;
		case "3":
			// System.out.println("Third attack");
			p1Pokemon.setNextAttack(2);
			break;
		case "4":
			// System.out.println("Fouth attack");
			p1Pokemon.setNextAttack(3);
			break;
		case "p":
		case "P":
			SelectPokemon();
			break;
		default:
			SelectPhase();
			break;
		}
	}

	/* attack calculations have been moved to game engine */
	private int AttackCalc(Pokemon attacker, Pokemon defender, Move attack) {
		double a = ((2 * attacker.getLevel()) / 5) + 2;
		double b = attack.getBaseDamage();
		double c = attacker.getAttack();
		double d = defender.getDefence();
		double g = c / d;
		double e = ((a * b * g) / 50) + 2;
		double mod = 1 * ThreadLocalRandom.current().nextDouble(0.85, 1);
		defender.setHp(defender.getHp() - (int) (e * mod));
		return (int) (e * mod);
	}

	/*
	 * attack phase Need to redo statements
	 */
	private void AttackPhase() {
		// temp formatting
		System.out.print("\n");
		// temp varibles to keep track of inflicted damage
		int playerAtk = 0;
		int aiAtk = 0;

		// randomly picks pokemon to attack if they have the same speed
		boolean isAIFirst = false;
		boolean isPlayerFirst = true;
		if (p1Pokemon.getSpeed() == aiPokemon.getSpeed()) {
			Random random = new Random();
			isAIFirst = random.nextBoolean();
		}
		/*
		 * this block of code has a lot of redundant print statements... I was
		 * kinda lazy and copied and pasted them around will work on cutting
		 * this down later... (also commented out a lot of code)
		 */
		
		// player's pokemon is slower than AI's pokemon
		if (p1Pokemon.getSpeed() < aiPokemon.getSpeed() || isAIFirst) {
			System.out.println(ai.getPlayerName() + "'s " + aiPokemon.getName() + " does a "
					+ aiPokemon.getMoves().get(aiPokemon.getNextAttack()).getName());
			// aiPokemon.attack(p1Pokemon,
			// aiPokemon.getMoves().get(aiPokemon.getNextAttack()));

			aiAtk = AttackCalc(aiPokemon, p1Pokemon, aiPokemon.getMoves().get(aiPokemon.getNextAttack()));
			isPlayerFirst = false;

			if (p1Pokemon.getHp() >= 0 && p1.isMoveSelected()) {
				System.out.println(p1.getPlayerName() + "'s " + p1Pokemon.getName() + " does a "
						+ p1Pokemon.getMoves().get(p1Pokemon.getNextAttack()).getName());
				// p1Pokemon.attack(aiPokemon,
				// p1Pokemon.getMoves().get(p1Pokemon.getNextAttack()));

				playerAtk = AttackCalc(p1Pokemon, aiPokemon, p1Pokemon.getMoves().get(p1Pokemon.getNextAttack()));
			}
			// player's pokemon is faster than AI's pokemon
		} else {
			if(p1.isMoveSelected()){
				System.out.println(p1.getPlayerName() + "'s " + p1Pokemon.getName() + " does a "
						+ p1Pokemon.getMoves().get(p1Pokemon.getNextAttack()).getName());
				// p1Pokemon.attack(aiPokemon,
				// p1Pokemon.getMoves().get(p1Pokemon.getNextAttack()));
	
				playerAtk = AttackCalc(p1Pokemon, aiPokemon, p1Pokemon.getMoves().get(p1Pokemon.getNextAttack()));
				isPlayerFirst = true;
			}
			if (aiPokemon.getHp() >= 0) {
				System.out.println(ai.getPlayerName() + "'s " + aiPokemon.getName() + " does a "
						+ aiPokemon.getMoves().get(aiPokemon.getNextAttack()).getName());
				// aiPokemon.attack(p1Pokemon,
				// aiPokemon.getMoves().get(aiPokemon.getNextAttack()));
				aiAtk = AttackCalc(aiPokemon, p1Pokemon, aiPokemon.getMoves().get(aiPokemon.getNextAttack()));
			}
		}

		// print damage calculations
		// if AI's pokemon faints
		if (aiPokemon.getHp() <= 0) {
			System.out.println(ai.getPlayerName() + "'s " + aiPokemon.getName() + " was hit for " + playerAtk
					+ " and now has 0 HP");
			
			
			for(int i = 0; i<6; i++){
				if(ai.getPokeParty().get(i).getHp() >= 0 && aiPokemon.getHp() <= 0){
					System.out.print(ai.getPlayerName() + " returned " + aiPokemon.getName());
					aiPokemon = ai.getPokeParty().get(i);
					System.out.println(" and sent out " + aiPokemon.getName());
				}
			}
			
			if (aiPokemon.getHp() <= 0) {
				System.out.println(ai.getPlayerName() + "'s " + aiPokemon.getName() + " has fainted!\n" + p1.getPlayerName()
						+ " WINS!!!");
				this.battling = false;
				p1Pokemon.setNextAttack(-1);
			}
			
			/*
			 * break out of loop when pokemon dies... need to change to
			 * accommodate party
			 */
			
			// if Player's pokemon faints
		} else if (p1Pokemon.getHp() <= 0) {
			System.out.println(
					p1.getPlayerName() + "'s " + p1Pokemon.getName() + " was hit for " + aiAtk + " and now has 0 HP");
			
			p1.getPokeParty().remove(p1.getPokeParty().indexOf(p1Pokemon));
			if(!p1.getPokeParty().isEmpty()){
				SelectPokemon();
			}else {
				System.out.println(p1.getPlayerName() + "'s " + p1Pokemon.getName() + " has fainted!\n" + ai.getPlayerName()
						+ " WINS!!!");
				this.battling = false;
				p1Pokemon.setNextAttack(-1);
			}
			/*
			 * break out of loop when pokemon dies... need to change to
			 * accommodate party
			 */
			
			// if both pokemon are still alive
		} else {
			// prints out based on who attacked first
			if (isPlayerFirst) {
				System.out.println(p1.getPlayerName() + "'s " + p1Pokemon.getName() + " was hit for " + aiAtk
						+ " and now has " + p1Pokemon.getHp() + " HP");

				System.out.println(ai.getPlayerName() + "'s " + aiPokemon.getName() + " was hit for " + playerAtk
						+ " and now has " + aiPokemon.getHp() + " HP");
			} else {
				System.out.println(ai.getPlayerName() + "'s " + aiPokemon.getName() + " was hit for " + playerAtk
						+ " and now has " + aiPokemon.getHp() + " HP");
				System.out.println(p1.getPlayerName() + "'s " + p1Pokemon.getName() + " was hit for " + aiAtk
						+ " and now has " + p1Pokemon.getHp() + " HP");
			}
			// }
		}
		// p1.setMoveSelected(false);
		// ai.setMoveSelected(false);
		System.out.print("\n");
	}

	/* need to implement */
	/* if moveSelected is false when it gets into the attack phase it means the person changed pokemon */
	private void SelectPokemon() {
		System.out.println("Select the number associated with the pokemon you want to switch out for ");
		int count = 0;
		for (Pokemon pokemon : p1.getPokeParty()) {
			if (pokemon != p1Pokemon && pokemon.getHp() > 0) {
				System.out.println(p1.getPokeParty().indexOf(pokemon)+1 + ": " + pokemon.getName() + " HP: " + pokemon.getHp());
				count++;
				
			}
		}
		int tempChosen = Integer.parseInt(reader.nextLine());

		if (tempChosen <= count && tempChosen > 0) {
			p1.setMoveSelected(false);
			System.out.print(p1.getPlayerName() + " returned " + p1Pokemon.getName());

			p1Pokemon = p1.getPokeParty().get(tempChosen-1);

			System.out.println(" and sent out " + p1Pokemon.getName() + p1Pokemon.getHp());
		} else {
			SelectPokemon();
		}
	}

	/* redundant / not needed */
	// private void SelectAttack(int atk) {
	// // System.out.print(p1.getPlayerName() + " select a move\n" + "\t1: " +
	// // p1Pokemon.getMoves().get(0).getName()
	// // + "\n" + "\t2: " + p1Pokemon.getMoves().get(1).getName() + "\n" +
	// // "\t3: "
	// // + p1Pokemon.getMoves().get(2).getName() + "\n" + "\t4: " +
	// // p1Pokemon.getMoves().get(3).getName()
	// // + "\n");
	//
	// // int tempTest = Integer.parseInt(reader.nextLine()) - 1;
	// // System.out.println(tempTest);
	// p1Pokemon.setNextAttack(atk);
	// if (p1Pokemon.getNextAttack() > -2 && p1Pokemon.getNextAttack() < 4) {
	// if (p1Pokemon.getNextAttack() == -1) {
	// ai.setMoveSelected(true);
	// // this.battling = false;
	// }
	// p1.setMoveSelected(true);
	// }
	// // else {
	// // SelectAttack();
	// // SelectPhase();
	// // }
	// }
}
