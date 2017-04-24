package game;

import engine.display.AnimatedSprite;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Tyler on 4/24/2017.
 */
public class TreasureChest extends AnimatedSprite {

    private String item;

    public TreasureChest(String id) {
        super(id, "", "");
        setDelay(500);

    }

    public TreasureChest(String id, String fileName) {
        super(id, fileName);
        setDelay(500);
    }

    public TreasureChest(String id, String fileName, String startState) {
        super(id, fileName, startState);
        setDelay(500);

    }

    public void placeItemInChest(String itemToPlace) {
        item = itemToPlace;
    }

    public String getItem() {
        return item;
    }

    public void update() {
        super.update();

    }

}

