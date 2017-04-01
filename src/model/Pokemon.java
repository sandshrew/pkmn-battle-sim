package model;

import java.util.Arrays;
import java.util.List;

public class Pokemon {
	//pokemon attributes/stats
	private String name;
	private int level;
	private int baseHP;
	private int baseAttack;
	private int baseDefence;
	private int baseSpeed;
	private Element type;
	private List<Move> moves;
	//maybe?
	//private int baseSpecialAttack;
	//private int baseSpecialDefence;
	//attacks
	//held item

	//maybe adding... EVs/IVs & nature

	//battle stats
	private int hp;
	private int attack;
	private int defence;
	private int speed;


	//default constructor
	public Pokemon() {
		//zoroark temp
		this.name = "Zoroark";
		this.level = 50;
		this.baseHP = 60;
		this.baseAttack = 105;
		this.baseDefence = 60;
		this.baseSpeed = 105;
		this.type = Element.Dark;
	
		this.calculateHP();
		this.attack = calculateStat(baseAttack);
		this.defence = calculateStat(baseDefence);
		this.speed = calculateStat(baseSpeed);
		
		this.moves = Arrays.asList(new Move(),new Move(),new Move(),new Move());
	}

	//constructor
	public Pokemon(String name, int level, int baseHP, int baseAttack, int baseDefence, int speed, Element type, List<Move> moves) {
		this.name = name;
		this.baseHP = baseHP;
		this.baseAttack = baseAttack;
		this.baseDefence = baseDefence;
		this.baseSpeed = speed;
		this.type = type;
		this.moves = moves;
	}

	//attack method that calculates damage output 
	public int attack(Pokemon otherPokemon, Move attack){
		return 10;
	}

	public void calculateHP(){
		int IV = 0;
		int EV = 0;
		this.hp = (int) Math.floor(((2 * this.baseHP + IV + Math.floor(EV/4)) * this.level) / 100) + this.level + 10;
	}

	private int calculateStat(int base){
		int nature = 1;
		int IV = 0;
		int EV = 0;
		return (int) Math.floor((Math.floor(((2 * base + IV + EV) * this.level)/100) + 5) * nature);
	}
	
	public void printStats(){
		System.out.println("Name " + this.name);
		System.out.println("HP " + this.hp);
		System.out.println("Attack " + this.attack);
		System.out.println("Defence " + this.defence);
		System.out.println("Speed " + this.speed);
		for(int i = 0; i < moves.size(); i++){
			System.out.print(moves.get(i).getName() + " ");
		}
	}

	//show moves
	public void printMoves(){
	}

	//MAIN METHOD
	public static void main(String[] args) {
		Pokemon test = new Pokemon();
		test.printStats();
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

	public Element getType() {
		return type;
	}

	public void setType(Element type) {
		this.type = type;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<Move> getMoves() {
		return moves;
	}

	public void setMoves(List<Move> moves) {
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
}


