package game;

import engine.display.Sprite;
import engine.events.Event;
import engine.events.IEventListener;

/**
 * Created by Ahmed on 4/9/2017.
 */
public class Heart extends Sprite implements IEventListener {

    private boolean alive = false;
    public Heart(String id, String imageFileName)
    {
        super(id, imageFileName);
    }

    @Override
    public void handleEvent(Event event) {

        if (event.getEventType()=="Collision") {
            this.toggleVisibility();
           // System.out.println("this is being called");
        }
    }

    @Override
    public void handleEvent(Event event, Sprite sprite) {
        // TODO Auto-generated method stub

    }


}
