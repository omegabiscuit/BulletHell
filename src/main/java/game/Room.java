package game;


import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import engine.display.DisplayObjectContainer;

/**
 * Created by Tyler on 4/17/2017.
 */
public class Room extends DisplayObjectContainer{

    protected ArrayList<LockedDoor> doors;
   // protected ArrayList<Enemy> enemies;

    boolean fadingIn;
    boolean fadingOut;


    private static double fadeSpeed = 0.01;

    public Room(String id) {
        super(id);
        doors = new ArrayList<LockedDoor>();

        fadingIn = false;
        fadingOut = false;
    }

    public void draw(Graphics g) {

    }

    public void update() {

        if(fadingIn) {
            applyFadeIn();
        }

        if(fadingOut) {
            applyFadeOut();
        }

    }

    public void registerEnemy(Enemy enemy) {
    //    enemies.add(enemy);
        addChild(enemy);
    }

    public ArrayList<LockedDoor> getDoors() {
        return doors;
    }

    public void fadeOut() {
        fadingOut = true;
    }

    protected void applyFadeOut() {
        ArrayList<DisplayObjectContainer> children = getChildren();
        boolean fadeComplete = true;
        for (int i = 0; i < children.size(); i++) {
            DisplayObjectContainer child = children.get(i);
            if (child.getTransparency() > 0.0) {
                child.setTransparency((float) (child.getTransparency() - fadeSpeed));
                fadeComplete = false;
            } else {
                child.setTransparency(0);
            }
        }

        if(fadeComplete) {
            fadingOut = false;
        }
    }

    public void fadeIn() {
        fadingIn = true;
    }

    protected void applyFadeIn() {
        ArrayList<DisplayObjectContainer> children = getChildren();
        boolean fadeComplete = true;
        for (int i = 0; i < children.size(); i++) {
            DisplayObjectContainer child = children.get(i);
            if (child.getTransparency() < 1.0) {
                child.setTransparency((float) (child.getTransparency() + fadeSpeed));
                fadeComplete = false;
            } else {
                child.setTransparency(1);
            }
        }

        if(fadeComplete) {
            fadingIn = false;
        }
    }
}
