package model;

public class Move {
	
	private String name;
	private Type type;
	private int baseDamage;
	
	//default constructor
	public Move(){
		this.name = "U-Turn";
		this.type = Type.Bug;
		this.baseDamage = 70;
	}
	
	//parameters constructor
	public Move(String name, Type type, int baseDamage) {
		this.name = name;
		this.type = type;
		this.baseDamage = baseDamage;
	}
	
	public String getName(){
		return name;
	}


	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public int getBaseDamage() {
		return baseDamage;
	}

	public void setBaseDamage(int baseDamage) {
		this.baseDamage = baseDamage;
	}

	public void setName(String name) {
		this.name = name;
	}
}
