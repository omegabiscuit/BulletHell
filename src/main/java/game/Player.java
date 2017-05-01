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
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * Created by Brigadoon on 4/16/2017.
 */


public class Player extends AnimatedSprite implements IEventListener {

    SoundManagerClass soundEffects = new SoundManagerClass();
    ArrayList<String> knifeSounds = new ArrayList<>();
    ArrayList<String> hurtSounds = new ArrayList<>();
    public ArrayList<Bullet> playerBullets = new ArrayList<>();



    ArrayList<Heart> lifeArray = new ArrayList<>();
    public boolean isDead = false;
    Heart life1;
    Heart life2;
    Heart life3;
    int lifeCount;
    Rectangle2D hitbox;

    private int playerDamageBuffer;
    private int playerDamageTimer;

    Rectangle2D feetCollider;
    Random random;

    Sprite pressE;

    public Player(String id, String fileName, String startState) {
        super(id, fileName, startState);
        life1 = new Heart("Heart", "heart.png");
        life2 = new Heart("Heart", "heart.png");
        life3 = new Heart("Heart", "heart.png");
        lifeArray.add(life1);
        lifeArray.add(life2);
        lifeArray.add(life3);
//        lifeCount = lifeArray.size();
        lifeCount = 3;
        hitbox = new Rectangle2D.Double(this.getPositionX()+5,this.getPositionY()+5,this.getUnscaledWidth()-5,this.getUnscaledHeight()-5);
        knifeSounds.add("resources/knife1.mp3");
        knifeSounds.add("resources/knife2.mp3");
        hurtSounds.add("resources/hurt1.mp3");
        hurtSounds.add("resources/hurt2.mp3");
        hurtSounds.add("resources/hurt3.mp3");
        playerDamageBuffer = 100;
        playerDamageTimer = playerDamageBuffer;


        feetCollider = new Rectangle2D.Double(getPositionX(), getPositionY()+getUnscaledHeight()-10, getUnscaledWidth(), 10);
        random = new Random();
    }

    public Player(String id, String fileName) {
        super(id, fileName);
        lifeArray.add(life1);
        lifeArray.add(life2);
        lifeArray.add(life3);
        lifeCount = lifeArray.size()-1;
        hitbox = new Rectangle2D.Double(this.getPositionX()+10,this.getPositionY()+10,this.getUnscaledWidth()-10,this.getUnscaledHeight()-10);
        playerDamageBuffer = 100;
        playerDamageTimer = playerDamageBuffer;
        feetCollider = new Rectangle2D.Double(getPositionX(), getPositionY() + getUnscaledHeight() - 10, getUnscaledWidth(), 10);
    }

    @Override
    public void update(ArrayList<String> pressedKeys) {
        super.update(pressedKeys);

        if(playerDamageTimer < playerDamageBuffer) {
            playerDamageTimer++;

            if(playerDamageTimer % 5 == 0 && !(playerDamageTimer % 10 == 0)) {
                setTransparency(0);
            } else if(playerDamageTimer % 10 == 0) {
                setTransparency(1);
            }

        }

        boolean moving = false;
        if (pressedKeys.contains(KeyEvent.getKeyText(KeyEvent.VK_W))) {
            this.setPositionY(this.getPositionY() - 5);
            hitbox = new Rectangle2D.Double(this.getPositionX()+10,this.getPositionY()+10,this.getUnscaledWidth()-10,this.getUnscaledHeight()-10);
            feetCollider = new Rectangle2D.Double(getPositionX()+20, getPositionY()+getUnscaledHeight()-10, getUnscaledWidth()-20, 10);
            moving = true;
            if (this.getStateName().contains("right") && !this.getStateName().equals("run_back_right")) {
                this.setAnimationState("run_back_right", "");
                this.setDelay(50);
            } else if (this.getStateName().contains("left") && !this.getStateName().equals("run_back_left")) {
                this.setAnimationState("run_back_left", "");
                this.setDelay(50);
            }
        }

        if (pressedKeys.contains(KeyEvent.getKeyText(KeyEvent.VK_S))) {
            this.setPositionY(this.getPositionY() + 5);
            hitbox = new Rectangle2D.Double(this.getPositionX()+10,this.getPositionY()+10,this.getUnscaledWidth()-10,this.getUnscaledHeight()-10);
            feetCollider = new Rectangle2D.Double(getPositionX()+20, getPositionY()+getUnscaledHeight()-10, getUnscaledWidth()-20, 10);
            moving = true;
            if (this.getStateName().contains("right") && !this.getStateName().equals("run_front_right")) {
                this.setAnimationState("run_front_right", "");
                this.setDelay(50);
            } else if (this.getStateName().contains("left") && !this.getStateName().equals("run_front_left")) {
                this.setAnimationState("run_front_left", "");
                this.setDelay(50);
            }
        }
        if (pressedKeys.contains(KeyEvent.getKeyText(KeyEvent.VK_D))) {
            this.setPositionX(this.getPositionX() + 5);
            hitbox = new Rectangle2D.Double(this.getPositionX()+10,this.getPositionY()+10,this.getUnscaledWidth()-10,this.getUnscaledHeight()-10);
            feetCollider = new Rectangle2D.Double(getPositionX()+20, getPositionY()+getUnscaledHeight()-10, getUnscaledWidth()-20, 10);
            moving = true;
            if (!this.getStateName().equals("run_back_right") && !this.getStateName().equals("run_front_right")) {
                this.setAnimationState("run_front_right", "");
                this.setDelay(50);
            }
        }
        if (pressedKeys.contains(KeyEvent.getKeyText(KeyEvent.VK_A))) {
            this.setPositionX(this.getPositionX() - 5);
            hitbox = new Rectangle2D.Double(this.getPositionX()+10,this.getPositionY()+10,this.getUnscaledWidth()-10,this.getUnscaledHeight()-10);
            feetCollider = new Rectangle2D.Double(getPositionX()+20, getPositionY()+getUnscaledHeight()-10, getUnscaledWidth()-20, 10);
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

    public void gotHurt() {

        playerDamageTimer = 0;
    }

    public boolean canGetHurt() {
        if(playerDamageTimer < playerDamageBuffer) {
            return false;
        }
        return true;
    }

    public boolean feetCollideWith(DisplayObject object) {
        Rectangle2D objRect;
        if(object.getId()=="spikes"){
            objRect = new Rectangle2D.Double(object.getPositionX()+20,object.getPositionY()+20,object.getUnscaledWidth()-50,object.getUnscaledHeight()-35);
        }
        else {
            objRect = new Rectangle2D.Double(object.getPositionX(), object.getPositionY(), object.getUnscaledWidth(), object.getUnscaledHeight());
        }
        if(this.feetCollider.intersects(objRect)){
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
            soundEffects.playMusic(knifeSounds.get(random.nextInt(2)));//choose a random knife sound
        }
        if(event.getEventType() == "Collision"){
            lifeCount-=1;
            soundEffects.playMusic(hurtSounds.get(random.nextInt(3)));
        }

    }

    @Override
    public void handleEvent(Event event, Sprite sprite) {

    }
}
