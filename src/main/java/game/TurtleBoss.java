package game;

import engine.display.AnimatedSprite;
import engine.events.Event;
import engine.events.IEventListener;

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
        health = 5;
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

        if (turtleDamageTimer < turtleDamageBuffer) {
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
}
