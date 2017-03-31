package Tweens;

import main.engine.display.Sprite;
import main.engine.events.Event;
import main.engine.events.IEventListener;

public class TweenEvent extends Event implements IEventListener{
	static boolean complete = false;
	public TweenEvent(String eventType){

	}
	public Tween getTween(){
		return null;
		
	}
	public static boolean tweenComplete(){
		return complete;
	}

	@Override
	public void handleEvent(Event event) {
		if (event.getEventType() == "FadeOut") {
			complete = true;
		}

	}

	@Override
	public void handleEvent(Event event, Sprite sprite) {

	}
}