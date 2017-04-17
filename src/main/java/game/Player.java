package game;

import engine.display.AnimatedSprite;
import engine.display.DisplayObject;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EventListener;

/**
 * Created by Brigadoon on 4/16/2017.
 */


public class Player extends AnimatedSprite implements EventListener{

    boolean moving = false;

    ArrayList<Heart> lifeArray = new ArrayList<>();
    Heart life1 = new Heart("Heart", "heart.png");
    Heart life2 = new Heart("Heart", "heart.png");
    Heart life3 = new Heart("Heart", "heart.png");
    int lifeCount;
    Rectangle2D hitbox;

    public Player(String id, String fileName, String startState) {
        super(id, fileName, startState);
        lifeArray.add(life1);
        lifeArray.add(life2);
        lifeArray.add(life3);
        lifeCount = lifeArray.size();
        hitbox = new Rectangle2D.Double(this.getPositionX()+5,this.getPositionY()+5,this.getUnscaledWidth()-5,this.getUnscaledHeight()-5);
    }

    public Player(String id, String fileName) {
        super(id, fileName);
        lifeArray.add(life1);
        lifeArray.add(life2);
        lifeArray.add(life3);
        lifeCount = lifeArray.size();
        hitbox = new Rectangle2D.Double(this.getPositionX()+10,this.getPositionY()+10,this.getUnscaledWidth()-10,this.getUnscaledHeight()-10);
    }

    @Override
    public void update(ArrayList<String> pressedKeys) {
        super.update(pressedKeys);
        if (!moving) {
            if (this.getStateName().contains("right") && !this.getStateName().equals("idle_right")) {
                this.setAnimationState("idle_right", "");
                this.setDelay(100);
            } else if (this.getStateName().contains("left") && !this.getStateName().equals("idle_left")) {
                this.setAnimationState("idle_left", "");
                this.setDelay(100);
            }
        }


        if (pressedKeys.contains("W")) {
            this.setPositionY(this.getPositionY() - 5);
            hitbox = new Rectangle2D.Double(this.getPositionX()+10,this.getPositionY()+10,this.getUnscaledWidth()-10,this.getUnscaledHeight()-10);
            moving = true;
            if (this.getStateName().contains("right") && !this.getStateName().equals("run_back_right")) {
                this.setAnimationState("run_back_right", "");
                this.setDelay(50);
            } else if (this.getStateName().contains("left") && !this.getStateName().equals("run_back_left")) {
                this.setAnimationState("run_back_left", "");
                this.setDelay(50);
            }
        }

        if (pressedKeys.contains("S")) {
            this.setPositionY(this.getPositionY() + 5);
            hitbox = new Rectangle2D.Double(this.getPositionX()+10,this.getPositionY()+10,this.getUnscaledWidth()-10,this.getUnscaledHeight()-10);
            moving = true;
            if (this.getStateName().contains("right") && !this.getStateName().equals("run_front_right")) {
                this.setAnimationState("run_front_right", "");
                this.setDelay(50);
            } else if (this.getStateName().contains("left") && !this.getStateName().equals("run_front_left")) {
                this.setAnimationState("run_front_left", "");
                this.setDelay(50);
            }
        }
        if (pressedKeys.contains("D")) {
            this.setPositionX(this.getPositionX() + 5);
            hitbox = new Rectangle2D.Double(this.getPositionX()+10,this.getPositionY()+10,this.getUnscaledWidth()-10,this.getUnscaledHeight()-10);
            moving = true;
            if (!this.getStateName().equals("run_back_right") && !this.getStateName().equals("run_front_right")) {
                this.setAnimationState("run_front_right", "");
                this.setDelay(50);
            }
        }
        if (pressedKeys.contains("A")) {
            this.setPositionX(this.getPositionX() - 5);
            hitbox = new Rectangle2D.Double(this.getPositionX()+10,this.getPositionY()+10,this.getUnscaledWidth()-10,this.getUnscaledHeight()-10);
            moving = true;
            if (!this.getStateName().equals("run_back_left") && !this.getStateName().equals("run_front_left")) {
                this.setAnimationState("run_front_left", "");
                this.setDelay(50);
            }
        }
    }

    public boolean playerCollidesWith(DisplayObject object){
        Rectangle2D objRect = new Rectangle2D.Double(object.getPositionX(),object.getPositionY(),object.getUnscaledWidth(),object.getUnscaledHeight());
        if(this.hitbox.intersects(objRect)){
            return true;
        }
        return false;
    }

    public ArrayList<Heart> getLifeArray() {
        return lifeArray;
    }

    public void setLifeArray(ArrayList<Heart> lifeArray) {
        this.lifeArray = lifeArray;
    }

    public int getLifeCount() {
        return lifeCount;
    }

    public void setLifeCount(int lifeCount) {
        this.lifeCount = lifeCount;
    }


    public Rectangle2D getHitbox() {
        return hitbox;
    }
}
