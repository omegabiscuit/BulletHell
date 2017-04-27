package game;

import engine.Tweens.*;
import engine.display.Sprite;
import engine.events.Event;
import engine.events.IEventListener;


public class Coin extends Sprite implements IEventListener {

    public Coin(String id, String imageFileName) {
        super(id, imageFileName);
    }

    private boolean touched = false;

    public boolean isTouched() {
        return touched;
    }

    public void setTouched(boolean touched) {
        this.touched = touched;
    }


    @Override
    public void handleEvent(Event event) {

    }

    public void handleEvent(Event event, Sprite sprite) {
        if (event.getEventType() == "FadeOut") {
            Tween myTween = new Tween(sprite, new TweenTransitions("linearTransition"));
            myTween.animate(TweenableParams.ALPHA, sprite.getTransparency(), 0, 2);
            TweenJuggler.getInstance().add(myTween);
//            System.out.println("handled");
        }
        if (event.getEventType() == "CoinPickedUp"){
            if(touched == false){
                this.toggleVisibility();
                touched = true;
            }
        }
    }



}
