package game;

import engine.Tweens.Tween;
import engine.Tweens.TweenJuggler;
import engine.Tweens.TweenTransitions;
import engine.Tweens.TweenableParams;
import engine.display.AnimatedSprite;
import engine.events.Event;
import engine.events.IEventListener;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Tyler on 4/24/2017.
 */
public class TurtleBoss extends Enemy implements IEventListener {

    public int turtleTimer;
    public int health;
    private int turtleDamageBuffer;
    private int turtleDamageTimer;


    public TurtleBoss(String id) {
        super(id, "", "");
        setDelay(300);

    }

    public TurtleBoss(String id, String fileName) {
        super(id, fileName);
        setDelay(300);
    }

    public TurtleBoss(String id, String fileName, String startState) {
        super(id, fileName, startState);

        setDelay(300);
        setTransparency(0);
        health = 10;
        turtleDamageBuffer = 100;
        turtleDamageTimer = turtleDamageBuffer;
    }

    public void gotHurt() {
        turtleDamageTimer = 0;
    }

    public boolean canGetHurt() {
        if (turtleDamageTimer < turtleDamageBuffer) {
            return false;
        }
        return true;
    }




    public void update() {
        super.update();


        if (stateName == "idle")
            setDelay(300);

        turtleTimer++;
        if (turtleTimer % 300 == 0) {
            setAnimationState("flex", "idle");
            setDelay(150);
        }

        if (turtleDamageTimer < turtleDamageBuffer && dead == false) {
            turtleDamageTimer++;
            if (turtleDamageTimer % 5 == 0 && !(turtleDamageTimer % 10 == 0)) {
                setTransparency(0);
            } else if (turtleDamageTimer % 10 == 0) {
                setTransparency(1);
            }
        }
        if (health <= 0) {
            dead = true;

        }
        if (dead == true) {

        }
    }

    public void draw(Graphics g) {
        super.draw(g);

        g.setFont(new Font("ARIAL", Font.PLAIN, 48));
        if (dead == true) {
            g.drawString("Congrats! You Win!", 400, 40);
        }







    }
}
