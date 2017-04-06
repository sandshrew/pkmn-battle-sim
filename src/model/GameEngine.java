package model;

import java.util.Scanner;

public class GameEngine 
{
	
	Scanner reader = new Scanner(System.in);
	Player p1;
	Player p2;
	Pokemon p1CurrentPokemon;
	Pokemon p2CurrentPokemon;
	
	public GameEngine()
	{
		init();
	}
	
	private void init()
	{
		this.p1 = new Player();
		this.p2 = new Player();
		
		this.p1CurrentPokemon = p1.getPokeParty().get(0);
		this.p2CurrentPokemon = p2.getPokeParty().get(0);
		
		startBattleLoop();
	}
	
	private void startBattleLoop()
	{
		BeginningPhase();

		while(p1CurrentPokemon.getNextAttack() != -1)
		{
			SelectPhase();
			if(p1CurrentPokemon.getNextAttack() != -1)
			{
				AttackPhase();
			}
		}
	}
	
	private void BeginningPhase()
	{
		
		System.out.print("Player 1 is currently named: " + p1.getPlayerName()
		+ "\nEnter new name: ");
		p1.setPlayerName(reader.nextLine());
		
		p2.setPlayerName("Jebb");
		
		System.out.println("Player 2 is named: " + p2.getPlayerName() + "\n");
		
		System.out.println(p1.getPlayerName() + " throws out a " + p1CurrentPokemon.getName()
				+ "\n" + p2.getPlayerName() + " throws out a " + p2CurrentPokemon.getName());
	}
	
	private void SelectPhase()
	{
		System.out.println("\nTo exit loop enter 0 as your attack for p1\n");
		while(!p1.isMoveSelected() && !p2.isMoveSelected())
		{
			while(!p1.isMoveSelected()){
				System.out.print(p1.getPlayerName() + " select a move\n" 
						+ "\t1: " + p1CurrentPokemon.getMoves().get(0).getName() + "\n" 
						+ "\t2: "	+ p1CurrentPokemon.getMoves().get(1).getName() + "\n"
						+ "\t3: " + p1CurrentPokemon.getMoves().get(2).getName() + "\n"
						+ "\t4: " + p1CurrentPokemon.getMoves().get(3).getName() + "\n");
				
				p1CurrentPokemon.setNextAttack(reader.nextInt()-1);
				if(p1CurrentPokemon.getNextAttack() > -2 && p1CurrentPokemon.getNextAttack() < 4)
				{
					if(p1CurrentPokemon.getNextAttack() == -1)
					{
						p2.setMoveSelected(true);
					}
					p1.setMoveSelected(true);
				}
			}
			
			while(!p2.isMoveSelected())
			{
				System.out.print(p2.getPlayerName() + " select a move\n" 
						+ "\t1: " + p2CurrentPokemon.getMoves().get(0).getName() + "\n" 
						+ "\t2: " + p2CurrentPokemon.getMoves().get(1).getName() + "\n"
						+ "\t3: " + p2CurrentPokemon.getMoves().get(2).getName() + "\n"
						+ "\t4: " + p2CurrentPokemon.getMoves().get(3).getName() + "\n");
				
				p2CurrentPokemon.setNextAttack(reader.nextInt()-1);
				if(p2CurrentPokemon.getNextAttack() > -1 && p2CurrentPokemon.getNextAttack() < 4)
				{
					p2.setMoveSelected(true);
				}
			}
		}
	}
	
	private void AttackPhase()
	{
		if(p1CurrentPokemon.getSpeed() < p2CurrentPokemon.getSpeed())
		{
			System.out.println(p2.getPlayerName() + "'s " + p2CurrentPokemon.getName() + " does a " + p2CurrentPokemon.getMoves().get(p2CurrentPokemon.getNextAttack()).getName());
			p2CurrentPokemon.attack(p1CurrentPokemon, p2CurrentPokemon.getMoves().get(p2CurrentPokemon.getNextAttack()));
			
			if(p1CurrentPokemon.getHp() != 0)
			{			
				System.out.println(p1.getPlayerName() + "'s " + p1CurrentPokemon.getName() + " does a " + p1CurrentPokemon.getMoves().get(p1CurrentPokemon.getNextAttack()).getName());
				p1CurrentPokemon.attack(p2CurrentPokemon, p1CurrentPokemon.getMoves().get(p1CurrentPokemon.getNextAttack()));
			}
		} else 
		{
			System.out.println(p1.getPlayerName() + "'s " + p1CurrentPokemon.getName() + " does a " + p1CurrentPokemon.getMoves().get(p1CurrentPokemon.getNextAttack()).getName());
			p1CurrentPokemon.attack(p2CurrentPokemon, p1CurrentPokemon.getMoves().get(p1CurrentPokemon.getNextAttack()));
			
			if(p2CurrentPokemon.getHp() != 0)
			{
				System.out.println(p2.getPlayerName() + "'s " +p2CurrentPokemon.getName() + " does a " + p2CurrentPokemon.getMoves().get(p2CurrentPokemon.getNextAttack()).getName());
				p2CurrentPokemon.attack(p1CurrentPokemon, p2CurrentPokemon.getMoves().get(p2CurrentPokemon.getNextAttack()));
			}
		}
				
		if(p2CurrentPokemon.getHp() == 0)
		{
			System.out.println(p2.getPlayerName() + "'s " + p2CurrentPokemon.getName() + " was hit for 10 and now has " 
					+ p2CurrentPokemon.getHp() + " HP\n");
			System.out.println(p2.getPlayerName() + "'s " + p2CurrentPokemon.getName() + " has fainted!\n" + p1.getPlayerName() + " WINS!!!");
			p1CurrentPokemon.setNextAttack(-1);
		} else if(p1CurrentPokemon.getHp() == 0)
		{
			System.out.println(p1.getPlayerName() + "'s " + p1CurrentPokemon.getName() + " was hit for 10 and now has " 
					+ p1CurrentPokemon.getHp() + " HP");
			System.out.println(p1.getPlayerName() + "'s " + p1CurrentPokemon.getName() + " has fainted!\n" + p2.getPlayerName() + " WINS!!!");
			p1CurrentPokemon.setNextAttack(-1);
		} else{
			System.out.println(p1.getPlayerName() + "'s " + p1CurrentPokemon.getName() + " was hit for 10 and now has " 
					+ p1CurrentPokemon.getHp() + " HP\n");
			
			System.out.println(p2.getPlayerName() + "'s " + p2CurrentPokemon.getName() + " was hit for 10 and now has " 
					+ p2CurrentPokemon.getHp() + " HP");
		}
		
		p1.setMoveSelected(false);
		p2.setMoveSelected(false);
	}

}
