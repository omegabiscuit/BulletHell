package game;


import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import engine.display.DisplayObjectContainer;
import engine.display.Sprite;

/**
 * Created by Tyler on 4/17/2017.
 */
public class Room extends DisplayObjectContainer{



    protected ArrayList<LockedDoor> doors;
    protected ArrayList<Enemy> enemies = new ArrayList<>();

    boolean fadingIn;
    boolean fadingOut;

    boolean doneFading;

    ArrayList<Rectangle2D> coverList;
    ArrayList<Platform> collisionArray = new ArrayList<>();


    private static double fadeSpeed = 0.01;

    public Room(String id) {
        super(id);
        doors = new ArrayList<>();
        fadingIn = false;
        fadingOut = false;
        doneFading = false;
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
            if (child.getTransparency() > 0.00001) {
                child.setTransparency((float) (child.getTransparency() - fadeSpeed));
                fadeComplete = false;
            } else {
                child.setTransparency(0);
            }
        }

        if(fadeComplete) {
            fadingOut = false;
            doneFading = fadeComplete;
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
            if (child.getTransparency() < 0.9999 ) {
                child.setTransparency((float) (child.getTransparency() + fadeSpeed));
                fadeComplete = false;
            } else {
                child.setTransparency(1);
            }
        }

        if(fadeComplete) {
            fadingIn = false;
            doneFading = fadeComplete;
        }
    }

    public Rectangle2D convertToCover(Sprite sprite){
        return new Rectangle2D.Double(sprite.getPositionX(),sprite.getPositionY(),sprite.getUnscaledWidth(),sprite.getUnscaledHeight());
    }


    public void disableRoom() {

    }

    public void moveRoomX(double movex) {

        ArrayList<DisplayObjectContainer> children = getChildren();
        for (int i = 0; i < children.size(); i++) {

            DisplayObjectContainer child = children.get(i);


            child.setPositionX(child.getPositionX() + movex);


        }
    }


    public void moveRoomY(double movey) {

        ArrayList<DisplayObjectContainer> children = getChildren();
        for (int i = 0; i < children.size(); i++) {

            DisplayObjectContainer child = children.get(i);
            child.setPositionY(child.getPositionY() + movey);

        }
    }

    public void mapDoorToRoom(int doorIndx, Room nextRoom) {
        doors.get(doorIndx).setNextRoom(nextRoom);
    }


    public void hide() {
        ArrayList<DisplayObjectContainer> children = getChildren();
        for (int i = 0; i < children.size(); i++) {
            DisplayObjectContainer child = children.get(i);
            child.setTransparency((float)0.0);
        }
    }

    public boolean getDoneFading() {
        return doneFading;
    }

    public void setDoneFading(boolean done) {

    }

//    public void createCover(Platform top, Sprite bottom, Sprite shadow, double posX, double posY) {
//        top = new Platform("cover", "cover_top_only_horizontal.png");
//        top.setPositionX(posX);
//        top.setPositionY(posY);
//
//        coverList.add(convertToCover(top));
//        addChild(top);
//        bottom = new Sprite("corner", "cover_bottom.png");
//        bottom.setPositionX(top.getPositionX());
//        bottom.setPositionY(top.getPositionY() + top.getUnscaledHeight());
//        addChild(bottom);
//        shadow = new Sprite("corner", "cover_shadow.png");
//        shadow.setPositionX(top.getPositionX() - 16);
//        shadow.setPositionY(top.getPositionY());
//        addChild(shadow);
//
//        collisionArray.add(top);
//    }

}
