package game;


import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Created by Tyler on 4/17/2017.
 */
public class Room {

    protected ArrayList<LockedDoor> doors;

    public Room() {
        doors = new ArrayList<LockedDoor>();
    }

    public void draw(Graphics g) {

    }

    public void update() {


    }

    public ArrayList<LockedDoor> getDoors() {
        return doors;
    }
}
