package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import model.Model;

public class Controller implements EventHandler{

private final Model model;
	
	public Controller(final Model model){
		this.model = model;
	}
	
	@Override
	public void handle(Event e) {		
		String eString = e.getSource().toString();
		if (eString.contains("Button")){
			String eSub = eString.substring(eString.indexOf("'",0)+1, eString.lastIndexOf("'",eString.length()));
			
			switch (eSub){
			case "Switch Pkmn":
				System.out.println("switch pokemon");
				break;
			case "Boy":
				System.out.println("boy");
				break;
			case "Girl":
				System.out.println("girl");
				break;
			case "Done":
				System.out.println("done");
			default:
				//System.out.println(eString);
				System.out.println(eSub);
				break;
			}
		} else if (eString.contains("AnchorPane")){
			System.out.println(eString);
		}
	}
}
