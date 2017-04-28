package game;

import engine.display.AnimatedSprite;
import engine.display.DisplayObjectContainer;
import engine.display.Sprite;
import engine.events.Event;
import engine.events.IEventListener;
import java.util.ArrayList;

import java.awt.image.BufferedImage;

/**
 * Created by Brigadoon on 3/31/2017.
 */
public class Bullet extends Sprite implements IEventListener {

    double endValX;
    double endValY;
    double startValX;
    double startValY;
    int velocityX;
    int velocityY;

    double shotTimer;
    double shotCap;

    double startTime;


    public Bullet(String id) {
        super(id);
    }

    public Bullet(String id, String imageFileName, double shot_cap, double startX, double startY, double endX, double endY) {

        super(id, imageFileName);

//        setStart(startX, startY);
//        setEnd(endX,endY);

        double angle = Math.atan2(endX - startX, endY-startY);
        velocityX = (int)Math.cos(angle)*1;
        velocityY = (int)Math.sin(angle)*1;



        shotTimer = 0.0;
        shotCap = shot_cap;

        startTime = (double) System.currentTimeMillis() / 1000;

        setPivotY(getUnscaledHeight()/2);
        setPivotX(getUnscaledWidth()/2);

        //1)take in mouse location
        //2)take in start position
        //3)
    }

    public Bullet(String id, String imageFileName, double shot_cap) {

        super(id, imageFileName);





        shotTimer = 0.0;
        shotCap = shot_cap;

        startTime = (double) System.currentTimeMillis() / 1000;

        setPivotY(getUnscaledHeight()/2);
        setPivotX(getUnscaledWidth()/2);

        //1)take in mouse location
        //2)take in start position
        //3)
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

    @Override
    public void update(ArrayList<String> pressedKeys) {
        super.update(pressedKeys);

        shotTimer = ( (double) System.currentTimeMillis() / 1000) - startTime;
        setPositionX(getPositionX()+velocityX);
        setPositionY(getPositionY()+velocityY);
        setRotation(getRotation() + 1);

        System.out.println(getPositionX());
    }

    public double getShotTimer() { return shotTimer; }

    public double getShotCap() { return shotCap; }


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
