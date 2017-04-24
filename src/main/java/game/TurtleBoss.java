package game;

import engine.display.AnimatedSprite;

/**
 * Created by Tyler on 4/24/2017.
 */
public class TurtleBoss extends Enemy{

    public int turtleTimer;

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

    }


    public void update() {
        super.update();

        if(stateName == "idle")
            setDelay(300);

        turtleTimer++;
        if(turtleTimer % 300 == 0) {
            setAnimationState("flex", "idle");
            setDelay(150);
        }
    }
}
