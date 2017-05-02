package controller;

import model.GameEngine;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.Model;

public class Controller implements EventHandler {

	private final Model model;
	private final GameEngine ge;

	public Controller(final Model model) {
		this.model = model;
		this.ge = null;
	}

	public Controller(final GameEngine ge) {
		this.ge = ge;
		this.model = null;
	}

	public GameEngine getEngine() {
		return this.ge;
	}

	public void handle(MouseEvent e) {
		System.out.println("UH");
	}

	@Override
	public void handle(Event e) {
		String move1 = this.ge.getP1Pokemon().getMoves().get(0).getName();
		String eString = e.getSource().toString();
		if (eString.contains("Button")) {
			String eSub = eString.substring(eString.indexOf("'", 0) + 1, eString.lastIndexOf("'", eString.length()));
			switch (eSub) {
			case "Switch Pkmn":
				System.out.println("switch pokemon");
				this.ge.SelectPokemonView();
				break;
			case "Boy":
				System.out.println("boy");
				break;
			case "Girl":
				System.out.println("girl");
				break;
			case "Done":
				System.out.println("done");
				break;
			case "Forfeit":
				System.out.println("Forfeit");
				this.ge.setWinner("Red");
				break;
			case "Cancel":
				this.ge.CancelSwitch();
				break;
			default:
				// System.out.println(eSub);
				break;
			}

			if (eSub.equals(this.ge.getP1Pokemon().getMoves().get(0).getName())) {
				this.ge.SelectPhase("1");
			} else if (eSub.equals(this.ge.getP1Pokemon().getMoves().get(1).getName())) {
				this.ge.SelectPhase("2");
			} else if (eSub.equals(this.ge.getP1Pokemon().getMoves().get(2).getName())) {
				this.ge.SelectPhase("3");
			} else if (eSub.equals(this.ge.getP1Pokemon().getMoves().get(3).getName())) {
				this.ge.SelectPhase("4");
			}
			if (!eSub.isEmpty()) {
				switch (eSub.charAt(0)) {
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
					this.ge.SelectPokemon(eSub.charAt(0));
					break;
				default:
					break;
				}
			}

		} else if (eString.contains("AnchorPane")) {
			System.out.println(eString);
		}
	}
}
