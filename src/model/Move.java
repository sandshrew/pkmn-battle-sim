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
	

}
