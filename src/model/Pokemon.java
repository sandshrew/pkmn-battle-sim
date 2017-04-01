package model;

public class Pokemon {
	//pokemon attributes/stats
	private String name;
	private int level;
	private int baseHP;
	private int baseAttack;
	private int baseDefence;
	
	//maybe?
	//private int baseSpecialAttack;
	//private int baseSpecialDefence;
	private int speed;
	private Element type;
	//attacks
	//held item
	
	//maybe adding... EVs/IVs & nature
	
	
	//default constructor
	public Pokemon() {
		//zoroark temp
		this.name = "Zoroark";
		this.level = 50;
		this.baseHP = 120;
		this.baseAttack = 110;
		this.baseDefence = 65;
		this.speed = 110;
		this.type = Element.Dark;
	}
	
	//constructor
	public Pokemon(String name, int level, int baseHP, int baseAttack, int baseDefence, int speed, Element type) {
		this.name = name;
		this.baseHP = baseHP;
		this.baseAttack = baseAttack;
		this.baseDefence = baseDefence;
		this.speed = speed;
		this.type = type;
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
	
	public static void main(String[] args) {
		Pokemon test = new Pokemon();
		System.out.println(test.getName());
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
}


