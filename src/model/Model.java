package model;

import java.util.ArrayList;
import java.util.List;


public class Model {

	private List<Listener> listeners = new ArrayList<Listener>();
    
	public void addListener(final Listener listener){
		listeners.add(listener);
	}
	    
	private void notifyListeners(){
		for(final Listener listener : listeners){
			listener.updated();
	    }
	}
}

