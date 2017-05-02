package model;

public interface Listener {
	
	void updated();
	void attackPhase();
	void faintedPhase();
	void gameOver();
	void switchPhase();
	void updateAIImage();
	void quitIt();
	void returnMain();
	void switchPokemon();
}
