package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player 
{
	
	private String playerName;
	private String playerId;
	private List<Pokemon> pokeParty;
	private static final int MAX_POKEMON_COUNT = 6;
	private boolean moveSelected;
	
	public Player()
	{
	
		this.setPlayerName("Red");
		this.setPlayerId("red123");
		this.pokeParty = Arrays.asList(new Pokemon(), new Pokemon(), new Pokemon(), new Pokemon(), new Pokemon(), new Pokemon());
		this.setMoveSelected(false);
	
	}
	
	public List<Pokemon> getPokeParty(){
		return pokeParty;
	}
	
	public void setPokeParty(List<Pokemon> pokeParty){
		this.pokeParty = pokeParty;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String name) {
		this.playerName = name;
	}

	public String getPlayerId() {
		return playerId;
	}

	public void setPlayerId(String playerId) {
		this.playerId = playerId;
	}

	public static int getMaxPokemonCount() {
		return MAX_POKEMON_COUNT;
	}

	public boolean isMoveSelected() {
		return moveSelected;
	}

	public void setMoveSelected(boolean moveSelected) {
		this.moveSelected = moveSelected;
	}
	
}
