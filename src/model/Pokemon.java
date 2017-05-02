package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class Pokemon {
	//pokemon attributes/stats
	private int ID;
	private String name;
	private int level;
	private int baseHP;
	private int baseAttack;
	private int baseDefence;
	private int baseSpeed;
	
	//battle stats
	private int hp;
	private int totalHP;
	private int attack;
	private int defence;
	private int speed;
	
	private Type type;
	private ArrayList<Move> moves;
	//maybe?
	//private int baseSpecialAttack;
	//private int baseSpecialDefence;
	//attacks
	//held item
	//maybe adding... EVs/IVs & nature
	
	//default constructor
	public Pokemon() {
		//zoroark temp
		this.name = "Zoroark";
		this.level = 50;
		this.baseHP = 60;
		this.baseAttack = 105;
		this.baseDefence = 60;
		this.baseSpeed = 105;
		this.type = Type.Dark;
	
		this.calculateHP();
		this.attack = calculateStat(baseAttack);
		this.defence = calculateStat(baseDefence);
		this.speed = calculateStat(baseSpeed);
		
		ArrayList<Move> test = new ArrayList<Move>();
		test.add(new Move());
		test.add(new Move( "Scratch", Type.Normal, 20));
		test.add(new Move( "Dark Pulse", Type.Dark, 65));
		test.add(new Move( "Bite", Type.Dark, 120));
	
		this.moves = test;
		this.ID = -1;
	}

	//constructor without nextAttack 
	public Pokemon(int ID, String name, int level, int baseHP, int baseAttack, int baseDefence, int speed, Type type, ArrayList<Move> moves) {
		this.ID = ID;
		this.name = name;
		this.baseHP = baseHP;
		this.level = level;
		this.baseAttack = baseAttack;
		this.baseDefence = baseDefence;
		this.baseSpeed = speed;
		this.type = type;
		this.moves = moves;
		this.calculateHP();
		this.attack = calculateStat(baseAttack);
		this.defence = calculateStat(baseDefence);
		this.speed = calculateStat(speed);
	}
	
	public Pokemon(Pokemon pokemon){
		this.ID = pokemon.getID();
		this.name = pokemon.getName();
		this.baseHP = pokemon.getBaseHP();
		this.level = pokemon.getLevel();
		this.baseAttack = pokemon.getBaseAttack();
		this.baseDefence = pokemon.getBaseDefence();
		this.baseSpeed = pokemon.getBaseSpeed();
		this.type = pokemon.getType();
		this.moves = pokemon.getMoves();
		this.hp = pokemon.getHp();
		this.attack = pokemon.getAttack();
		this.defence = pokemon.getDefence();
		this.speed = pokemon.getSpeed();
		this.totalHP = pokemon.getHp();
	}

	public void calculateHP(){
		int IV = 0;
		int EV = 0;
		this.hp = (int) Math.floor(((2 * this.baseHP + IV + Math.floor(EV/4)) * this.level) / 100) + this.level + 10;
		this.totalHP = this.hp;
	}

	private int calculateStat(int base){
		int test = (int) Math.floor((Math.floor(((2 * base) * this.level)/100) + 5) * 1);
		return test;
	}
	
	public void printStats(){
		System.out.println("ID " + this.ID);
		System.out.println("Name " + this.name);
		System.out.println("HP " + this.hp);
		System.out.println("Attack " + this.attack);
		System.out.println("Defence " + this.defence);
		System.out.println("Speed " + this.speed);
		for(int i = 0; i < moves.size(); i++){
			System.out.print(moves.get(i).getName()+" "+ moves.get(i).getBaseDamage() + (( i == 3) ? " " : ", "));
		}
		System.out.print("\n\n");
	}

	//show moves
	public void printMoves(){
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBaseHP() {
		return baseHP;
	}

	public void setBaseHP(int baseHP) {
		this.baseHP = baseHP;
	}

	public int getBaseAttack() {
		return baseAttack;
	}

	public void setBaseAttack(int baseAttack) {
		this.baseAttack = baseAttack;
	}

	public int getBaseDefence() {
		return baseDefence;
	}

	public void setBaseDefence(int baseDefence) {
		this.baseDefence = baseDefence;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public ArrayList<Move> getMoves() {
		return moves;
	}

	public void setMoves(ArrayList<Move> moves) {
		this.moves = moves;
	}

	public int getBaseSpeed() {
		return baseSpeed;
	}

	public void setBaseSpeed(int baseSpeed) {
		this.baseSpeed = baseSpeed;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefence() {
		return defence;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getTotalHP() {
		return totalHP;
	}

	public void setTotalHP(int totalHP) {
		this.totalHP = totalHP;
	}
	
}


