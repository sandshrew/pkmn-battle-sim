package model;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class PokemonFactory {

	private static ArrayList<Pokemon> allPokemon = new ArrayList<Pokemon>();
	public static ArrayList<Pokemon> generatePokemon(){;
		parseFile();
		if (!allPokemon.isEmpty()){
			return allPokemon;
		} else{
			return null;
		}
		
	}
	
	public static void parseFile() {
		try (BufferedReader reader = Files.newBufferedReader((Paths.get("PokemonData.csv")))) {
			String line = reader.readLine();
			while (line != null) {
				// System.out.println(line);
				//String[] tokens = line.split(",");
				Pokemon pokemon = createPokemon(line.split(","));
				allPokemon.add(pokemon);
				line = reader.readLine();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Pokemon createPokemon(String[] tokens) {
		Pokemon test = new Pokemon(Integer.parseInt(tokens[0]), tokens[1], 50, Integer.parseInt(tokens[2]),
				Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]),
				Element.valueOf(tokens[6]), createMove(tokens));
		//test.printStats();
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
}
