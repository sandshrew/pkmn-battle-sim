package model;

public class Move {
	
	private String name;
	private Element type;
	private int baseDamage;
	
	//default constructor
	public Move(){
		this.name = "U-Turn";
		this.type = Element.Bug;
		this.baseDamage = 70;
	}
	
	//parameters constructor
	public Move(String name, Element type, int baseDamage) {
		this.name = name;
		this.type = type;
		this.baseDamage = baseDamage;
	}
	
	public String getName(){
		return name;
	}


	public Element getType() {
		return type;
	}

	public void setType(Element type) {
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
