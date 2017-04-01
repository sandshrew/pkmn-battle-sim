package model;

public class Move {
	
	private int id;
	private String name;
	private Element type;
	private int baseDamage;
	
	//default constructor
	public Move(){
		this.id = -1;
		this.name = "boogy";
		this.type = Element.Questionable;
		this.baseDamage = 10;
	}
	
	//parameters constructor
	public Move(int id, String name, Element type, int baseDamage) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.baseDamage = baseDamage;
	}
	
	public String getName(){
		return name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
