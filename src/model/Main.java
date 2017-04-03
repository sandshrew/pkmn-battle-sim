package model;

public class Main {

	//MAIN METHOD
	public static void main(String[] args) {
		Pokemon test = new Pokemon();
		System.out.println(test.attack(test, test.getMoves().get(0)));
	}

}
