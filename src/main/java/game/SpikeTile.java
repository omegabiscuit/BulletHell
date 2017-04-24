package game;

import engine.display.AnimatedSprite;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Tyler on 4/24/2017.
 */
public class SpikeTile extends AnimatedSprite {


    private int timerCap = 125;
    int timer;

    public SpikeTile(String id) {
        super(id, "", "");

        timer = 0;
        setDelay(45);
    }

    public SpikeTile(String id, String fileName) {
        super(id, fileName);

        timer = 0;
        setDelay(45);
    }

    public SpikeTile(String id, String fileName, String startState) {
        super(id, fileName, startState);

        timer = 0;
        setDelay(45);
    }


    public void update() {
        super.update();
        if(timer < timerCap && stateName != "up" && stateName != "down") {
            timer++;
        } else if(timer >= timerCap && stateName != "up" && stateName != "down") {
            if(stateName == "idle down") {
                setAnimationState("up", "idle up");
            } else if(stateName == "idle up") {
                setAnimationState("down", "idle down");
            }
            timer = 0;
        }
    }

}
