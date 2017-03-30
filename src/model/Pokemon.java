package model;



public class Pokemon {
	//pokemon attributes/stats
	private String name;
	private int baseHP;
	private int baseAttack;
	private int baseDefence;
	private int level;
	//maybe?
	//private int baseSpecialAttack;
	//private int baseSpecialDefence;
	private int speed;
	private Type type;
	//attacks
	//held item

	//maybe adding... EVs/IVs & nature


	public enum Type{
		Normal, Fire, Fighting, Water, Flying, Grass, Poison, Eletric, Ground, Psychic, Rock, Ice, Bug, Dragon, Ghost, Dark, Steel, Fairy, Questionable

	}

	//default constructor
	public Pokemon() {
		this.name = "missingno";
		this.baseHP = 25;
		this.baseAttack = 10;
		this.baseDefence = 10;
		this.speed = 10;
		this.level = 1;
		this.type = Type.Dragon;
	}

	//constructor
	public Pokemon(String name, int baseHP, int baseAttack,int level, int baseDefence, int speed, Type type) {
		this.name = name;
		this.baseHP = baseHP;
		this.baseAttack = baseAttack;
		this.baseDefence = baseDefence;
		this.speed = speed;
		this.type = type;
	}

	public int getAttackStat(){
		return (((2 * baseAttack + 0 + (0/4) * level)/100) + 5);
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

	public static void main(String[] args) {
		Pokemon test = new Pokemon();
		System.out.println(test.getAttackStat());
	}

}


