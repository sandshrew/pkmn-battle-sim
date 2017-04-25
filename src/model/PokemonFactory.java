package model;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class PokemonFactory {

	public static void parseFile(){
		try(BufferedReader reader = Files.newBufferedReader((Paths.get("PokemonData.csv")))){
			String line = reader.readLine();
			while( line != null){
				//System.out.println(line);
				String[] tokens = line.split(",");
				Pokemon pokemon = createPokemon(tokens);
				line = reader.readLine();
			}
			

		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static Pokemon createPokemon(String[] tokens){
		for(String s: tokens){
			System.out.print(s + " ");
		}
		System.out.print('\n');
		return null;
	}
	
	public static ArrayList<Move> createMove(String[] Moves){
		return null;
	}

	public static void main(String[] args) {
		parseFile();
	}
}
