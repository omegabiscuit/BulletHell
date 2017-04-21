package game;

import engine.display.AnimatedSprite;
import engine.display.DisplayObject;
import engine.display.Sprite;
import engine.events.*;
import engine.events.Event;

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


public class Player extends AnimatedSprite implements IEventListener {

    SoundManagerClass soundEffects = new SoundManagerClass();
    ArrayList<String> knifeSounds = new ArrayList<>();



    ArrayList<Heart> lifeArray = new ArrayList<>();
    public boolean isDead = false;
    Heart life1;
    Heart life2;
    Heart life3;
    int lifeCount;
    Rectangle2D hitbox;

    public Player(String id, String fileName, String startState) {
        super(id, fileName, startState);
        life1 = new Heart("Heart", "heart.png");
        life2 = new Heart("Heart", "heart.png");
        life3 = new Heart("Heart", "heart.png");
        lifeArray.add(life1);
        lifeArray.add(life2);
        lifeArray.add(life3);
        lifeCount = lifeArray.size();
        hitbox = new Rectangle2D.Double(this.getPositionX()+5,this.getPositionY()+5,this.getUnscaledWidth()-5,this.getUnscaledHeight()-5);
        knifeSounds.add("resources/knife1.mp3");
        knifeSounds.add("resources/knife2.mp3");

    }

    public Player(String id, String fileName) {
        super(id, fileName);
        lifeArray.add(life1);
        lifeArray.add(life2);
        lifeArray.add(life3);
        lifeCount = lifeArray.size()-1;
        hitbox = new Rectangle2D.Double(this.getPositionX()+10,this.getPositionY()+10,this.getUnscaledWidth()-10,this.getUnscaledHeight()-10);
    }

    @Override
    public void update(ArrayList<String> pressedKeys) {
        super.update(pressedKeys);

        boolean moving = false;
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

        if (!moving) {
            if (this.getStateName().contains("right") && !this.getStateName().equals("idle_right")) {
                this.setAnimationState("idle_right", "");
                this.setDelay(100);
            } else if (this.getStateName().contains("left") && !this.getStateName().equals("idle_left")) {
                this.setAnimationState("idle_left", "");
                this.setDelay(100);
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

    @Override
    public void handleEvent(Event event) {
        if(event.getEventType() == "throwKnife"){
            int ran = (int)(Math.random()*2);

            soundEffects.playMusic(knifeSounds.get(ran));//choose a random knife sound
            System.out.println(ran);
        }
        if(event.getEventType() == "collision"){
            lifeCount-=1;
        }
    }

    @Override
    public void handleEvent(Event event, Sprite sprite) {

    }
}
