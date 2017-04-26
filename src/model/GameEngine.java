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
	private ArrayList<Pokemon> allPokemon;
	private ArrayList<Pokemon> p1Party;
	private ArrayList<Pokemon> aiParty;

	private String winner = "";

	/* pokemon are still battling boolean */
	private boolean battling;

	/* user selects an attack */
	private boolean moveSelected = false;
	private int selectedAttack = 0;

	/* Default constructor */
	public GameEngine() {
		allPokemon = PokemonFactory.generatePokemon();
		int[] test = { 0, 1, 2, 3, 4, 5 };
		this.p1 = new Player("Dragon", selectPokemon(test));
		// this.p1 = new Player();
		this.ai = new Player();

		this.p1Pokemon = p1.getPokeParty().get(0);
		this.aiPokemon = ai.getPokeParty().get(0);

		this.p1Party = p1.getPokeParty();
		this.aiParty = ai.getPokeParty();

		battling = true;
		startBattleLoop();
	}

	public ArrayList<Pokemon> selectPokemon(int[] test) {
		ArrayList<Pokemon> party = new ArrayList<Pokemon>();
		for (int i = 0; i < 6; i++) {
			party.add(this.allPokemon.get(test[i]));
		}
		return party;
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
			++turnCounter;
			System.out.println("Turn " + turnCounter);
			SelectPhase();
			if (battling) {
				AttackPhase();
			}
		}
		System.out.println("oh my god the game is over");
	}

	/* SET NAMES // AND INIT PARTIES?! */
	private void BeginningPhase() {

		ai.setPlayerName("Red");

	}

	/* Phase to select moves or another pokemon */
	private void SelectPhase() {

		System.out.print(p1.getPlayerName() + " select a move for " + p1Pokemon.getName() + " " + p1Pokemon.getHp()
				+ " " + p1Pokemon.getSpeed() + " \n" + "\t1: " + p1Pokemon.getMoves().get(0).getName() + "\n" + "\t2: "
				+ p1Pokemon.getMoves().get(1).getName() + "\n" + "\t3: " + p1Pokemon.getMoves().get(2).getName() + "\n"
				+ "\t4: " + p1Pokemon.getMoves().get(3).getName());
		System.out.print("\nSelect move number, P to select a new pokemon, or 0 to exit: ");

		switch (reader.nextLine()) {
		case "0":
			System.out.println("Break");
			this.battling = false;
			break;
		case "1":
			// System.out.println("First attack");
			// SelectAttack(1);
			p1Pokemon.setNextAttack(0);
			this.moveSelected = true;
			break;
		case "2":
			// System.out.println("Second attack");
			p1Pokemon.setNextAttack(1);
			this.moveSelected = true;
			break;
		case "3":
			// System.out.println("Third attack");
			p1Pokemon.setNextAttack(2);
			this.moveSelected = true;
			break;
		case "4":
			// System.out.println("Fouth attack");
			p1Pokemon.setNextAttack(3);
			this.moveSelected = true;
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
	 * attack phase
	 */
	private void AttackPhase() {
		// temp formatting
		System.out.print("\n");

		// randomly picks pokemon to attack if they have the same speed
		boolean random = false;
		if (p1Pokemon.getSpeed() == aiPokemon.getSpeed()) {
			Random r = new Random();
			random = r.nextBoolean();
		}

		//variables to keep track of which player/pokemon
		//is attacking first 
		Player first = this.ai;
		Pokemon firstPokemon = this.aiPokemon;
		Player next = this.p1;
		Pokemon nextPokemon = this.p1Pokemon;
		
		//variables to keep track of damage
		int firstAtk = 0;
		int nextAtk = 0;
		
		// player's pokemon is slower than AI's pokemon
		if (p1Pokemon.getSpeed() < aiPokemon.getSpeed() || random) {
			first = this.ai;
			firstPokemon = this.aiPokemon;
			next = this.p1;
			nextPokemon = this.p1Pokemon;
		} else {
			first = this.p1;
			firstPokemon = this.p1Pokemon;
			next = this.ai;
			nextPokemon = this.aiPokemon;
		}

		//calculate and apply first attack damage
		firstAtk = AttackCalc(firstPokemon, nextPokemon, firstPokemon.getMoves().get(firstPokemon.getNextAttack()));
		System.out.println(first.getPlayerName() + "'s " + firstPokemon.getName() + " used "
				+ firstPokemon.getMoves().get(firstPokemon.getNextAttack()).getName());
		System.out.println(next.getPlayerName() + "'s " + nextPokemon.getName() + " was hit for " + firstAtk
				+ " and now has " + ((nextPokemon.getHp() >= 0) ? nextPokemon.getHp() : "0") + " HP");

		//if attacked pokemon is still alive
		//AND an attack has been selected then
		//calculate and apply next attack damage
		if (nextPokemon.getHp() > 0 && this.moveSelected) {
			nextAtk = AttackCalc(nextPokemon, firstPokemon, nextPokemon.getMoves().get(nextPokemon.getNextAttack()));
			System.out.println(next.getPlayerName() + "'s " + nextPokemon.getName() + " used "
					+ nextPokemon.getMoves().get(nextPokemon.getNextAttack()).getName());
			System.out.println(first.getPlayerName() + "'s " + firstPokemon.getName() + " was hit for " + nextAtk
					+ " and now has " + ((firstPokemon.getHp() >= 0) ? firstPokemon.getHp() : "0") + " HP");
		}

		
		boolean pokemonFainted = false;
		Pokemon faintedPokemon = null;
		Player temp = null;
		if (nextPokemon.getHp() <= 0) {
			pokemonFainted = true;
			faintedPokemon = nextPokemon;
			temp = next;
		}
		
		if (firstPokemon.getHp() <= 0) {
			pokemonFainted = true;
			faintedPokemon = firstPokemon;
			temp = first;
		}
		
		if(pokemonFainted){
			pokemonFainted = false;
			System.out.println(temp.getPlayerName() + "'s " + faintedPokemon.getName() + " fainted!");
			if (!this.knockedOut(temp.getPokeParty())) {
				if (temp.getPlayerName().equals(ai.getPlayerName())) {
					aiSpawnNext();
				} else{
					p1.getPokeParty().remove(p1.getPokeParty().indexOf(p1Pokemon));
					if (!p1.getPokeParty().isEmpty()) {
						SelectPokemon();
					}
				}
			} else{
				System.out.println(temp.getPlayerName() + " loses!");
				battling = false;
			}
		}
		
		System.out.print("\n");
	}

	private void aiSpawnNext() {
		for (int i = 0; i < 6; i++) {
			if (ai.getPokeParty().get(i).getHp() >= 0 && aiPokemon.getHp() <= 0) {
				System.out.print(ai.getPlayerName() + " returned " + aiPokemon.getName());
				aiPokemon = ai.getPokeParty().get(i);
				System.out.println(" and sent out " + aiPokemon.getName());
			}
		}
	}

	private boolean knockedOut(ArrayList<Pokemon> party) {
		for (Pokemon p : party) {
			if (p.getHp() >= 0) {
				return false;
			}
		}
		return true;
	}

	/* need to implement */
	/*
	 * if moveSelected is false when it gets into the attack phase it means the
	 * person changed pokemon
	 */
	private void SelectPokemon() {
		System.out.println("Select the number associated with the pokemon you want to switch out for"
				+ ((this.p1Pokemon.getHp() <= 0) ? ": " : " (0) to go back to battle phase:"));
		int count = 0;
		for (Pokemon pokemon : p1.getPokeParty()) {
			if (pokemon.hashCode() != p1Pokemon.hashCode() && pokemon.getHp() > 0) {
				System.out.println(
						p1.getPokeParty().indexOf(pokemon) + 1 + ": " + pokemon.getName() + " HP: " + pokemon.getHp());
				count++;
			}
		}
		int tempChosen = Integer.parseInt(reader.nextLine());

		if (tempChosen <= count && tempChosen > 0) {
			// p1.setMoveSelected(false);
			this.moveSelected = false;
			System.out.print(p1.getPlayerName() + " returned " + p1Pokemon.getName());

			p1Pokemon = p1.getPokeParty().get(tempChosen - 1);

			System.out.println(" and sent out " + p1Pokemon.getName() + " " + p1Pokemon.getHp());
		} else if (tempChosen == 0) {
			SelectPhase();
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
