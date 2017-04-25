package game;

import java.awt.*;
import engine.display.AnimatedSprite;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Created by Tyler on 4/17/2017.
 */
public class LockedDoor extends AnimatedSprite {

    Rectangle doorCollider;
    Room nextRoom;

    public LockedDoor(String id, String fileName, String startState) {

        super(id, fileName, startState);

        doorCollider = new Rectangle((int)getPositionX(), (int)getPositionY(), 128, 40);
    }

    public void update() {
        super.update();
    }

    public void draw(Graphics g) {
        super.draw(g);
//        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
//        g.setColor(Color.RED);
//        ((Graphics2D) g).draw(doorCollider);
    }

    public Rectangle2D getDoorCollider() {
        return doorCollider;
    }

    public void setNextRoom(Room next) {
        nextRoom = next;
    }

    public Room getNextRoom() {
        return nextRoom;
    }


    @Override
    public void setPositionX(double x) {
        super.setPositionX(x);
        doorCollider.setLocation((int)getPositionX(), (int)getPositionY());
    }

    @Override
    public void setPositionY(double y) {
        super.setPositionY(y);
        doorCollider.setLocation((int)getPositionX(), (int)getPositionY());
    }
}
