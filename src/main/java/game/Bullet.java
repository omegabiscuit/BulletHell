package game;

import engine.display.AnimatedSprite;
import engine.display.DisplayObjectContainer;
import engine.display.Sprite;
import engine.events.Event;
import engine.events.IEventListener;

import java.awt.image.BufferedImage;

/**
 * Created by Brigadoon on 3/31/2017.
 */
public class Bullet extends Sprite implements IEventListener {

    double endValX;
    double endValY;
    double startValX;
    double startValY;



    public Bullet(String id) {
        super(id);
    }

    public Bullet(String id, String imageFileName) {
        super(id, imageFileName);
    }

    @Override
    public void handleEvent(Event event) {

    }

    @Override
    public void handleEvent(Event event, Sprite sprite) {

    }
    public void setStart(double startValX, double startValY){
        this.startValX = startValX;
        this.startValY = startValY;
        this.setPositionX(startValX);
        this.setPositionY(startValY);
    }

    public void setEnd(double endValX, double endValY){
        this.endValX = endValX;
        this.endValY = endValY;
    }

    public double getEndValX() {
        return endValX;
    }

    public double getEndValY() {
        return endValY;
    }

    public double getStartValX() {
        return startValX;
    }

    public double getStartValY() {
        return startValY;
    }
}
