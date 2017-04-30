package game;


import engine.display.Sprite;
import engine.events.Event;
import engine.events.IEventListener;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Brigadoon on 2/17/2017.
 */
public class QuestManager implements IEventListener {
//    Sprite findKey = new Sprite("findKey", "resources/find_key.png");
//    Sprite getToRoom = new Sprite("getToRoom", "resources/get_to_room.png");
//    Sprite CurrentObjective = findKey;

    @Override
    public void handleEvent(Event event) {
        if (event.getEventType() == "KeyPickedUp") {

        }
        else if(event.getEventType()== "DoorOpened"){
//            CurrentObjective = findKey;
//            CurrentObjective.setPositionX(800);
//            CurrentObjective.setPositionY(300);
        	
        }
        else if (event.getEventType() == "FinalBoss"){

        }

    }

	@Override
	public void handleEvent(Event event, Sprite sprite) {
		// TODO Auto-generated method stub
		
	}


}


