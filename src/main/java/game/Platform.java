package game;

import engine.display.Sprite;
import engine.events.Event;
import engine.events.IEventListener;

public class Platform extends Sprite implements IEventListener {
	
	
	public Platform(String id, String imageFileName) {
        super(id, imageFileName);
    }


    @Override
    public void handleEvent(Event event) {
        if (event.getEventType() == "Collison") {
            this.setVisibleState(false);
        }
    }


	@Override
	public void handleEvent(Event event, Sprite sprite) {
		// TODO Auto-generated method stub
		
	}
   
    
    
    
}
