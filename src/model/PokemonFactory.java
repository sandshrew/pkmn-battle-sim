package model;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class PokemonFactory {

	public static void parseFile() {
		try (BufferedReader reader = Files.newBufferedReader((Paths.get("PokemonData.csv")))) {
			String line = reader.readLine();
			while (line != null) {
				// System.out.println(line);
				String[] tokens = line.split(",");
				Pokemon pokemon = createPokemon(tokens);
				line = reader.readLine();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Pokemon createPokemon(String[] tokens) {
		int tempID = Integer.parseInt(tokens[0]);
		String tempName = tokens[1];
		int tempHP = Integer.parseInt(tokens[2]);
		int tempAtk = Integer.parseInt(tokens[3]);
		int tempDef = Integer.parseInt(tokens[4]);
		int tempSpeed = Integer.parseInt(tokens[5]);

		Pokemon test = new Pokemon(tempID, tempName, 50, tempHP, tempAtk, tempDef, tempSpeed,
				Element.valueOf(tokens[6]), createMove(tokens));
		test.printStats();
		return test;
	}

	public static ArrayList<Move> createMove(String[] tokens) {
		ArrayList<Move> temp = new ArrayList<Move>();
		int next = 0;
		for (int i = 0; i < 4; i++) {
			Move move = new Move(tokens[7 + next], Element.valueOf(tokens[9 + next]),
					Integer.parseInt(tokens[8 + next]));
			temp.add(move);
			next += 3;
		}
		return temp;
	}

	public static void main(String[] args) {
		parseFile();
//		Pokemon test = new Pokemon();
//		test.printStats();
	}
}
