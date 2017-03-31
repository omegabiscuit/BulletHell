package main.java.game;


import main.java.engine.display.Sprite;
import main.java.engine.events.Event;
import main.java.engine.events.IEventListener;

/**
 * Created by Brigadoon on 2/17/2017.
 */
public class QuestManager implements IEventListener {
    @Override
    public void handleEvent(Event event) {
        if (event.getEventType() == "CoinPickedUp") {
            //System.out.println("Quest is Complete");
        }
        if(event.getEventType()== "CollidedEvent"){
        	
        }
        if (event.getEventType() == "playerDeath"){
            System.out.println("player is dead");
        }
    }

	@Override
	public void handleEvent(Event event, Sprite sprite) {
		// TODO Auto-generated method stub
		
	}



}
