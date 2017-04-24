package game;

import engine.display.Sprite;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Brigadoon on 4/23/2017.
 */
public class BrighamLevel extends Room {

    Sprite map;
    Sprite startTile;



    Platform collider1;
    Platform collider2;
    Platform leftCollider;
    Platform collider3;
    Platform rightCollider;
    Platform collider6;
    Platform collider7;
    Platform collider8;

    LockedDoor door1;

    public BrighamLevel(String id) {
        super(id);
    }

    public void run() {
        //map stuff
        map = new Sprite("map", "BrighamLevelFinal.png");
        map.setPositionX(200);
        map.setPositionY(0);
        addChild(map);

        startTile = new Sprite("startTile", "tile.png");
        startTile.setPositionX(500);
        startTile.setPositionY(810);

        collider1 = new Platform("collider", "alpha_3x1.png");
        //collider4.setRotation(0.9);
        collider1.setPositionX(650);
        collider1.setPositionY(-20);
        addChild(collider1);

        collider2 = new Platform("collider", "alpha_2x1.png");

        collider2.setPositionX(230);
        collider2.setPositionY(-20);
        addChild(collider2);

        collider3 = new Platform("collider", "alpha_1x1.png");
        collider3.setPositionX(200);
        collider3.setPositionY(-20);
        addChild(collider3);

        leftCollider = new Platform("collider","alpha_1x6.png");
        leftCollider.setPositionX(102);
        leftCollider.setPositionY(-20);
        addChild(leftCollider);

        rightCollider = new Platform("collider","alpha_1x6.png");
        rightCollider.setPositionX(1006);
        rightCollider.setPositionY(-20);




        collisionArray.add(collider1);
        collisionArray.add(collider2);
        collisionArray.add(collider3);
        collisionArray.add(leftCollider);
        collisionArray.add(rightCollider);


    }


    public void draw(Graphics g) {
        super.draw(g);
        map.draw(g);
        startTile.draw(g);

//        collider1.draw(g);
//        collider2.draw(g);
//        collider3.draw(g);
//        collider4.draw(g);
//        collider5.draw(g);
//        collider6.draw(g);
//        collider7.draw(g);
//        collider8.draw(g);





    }

    public void update() {
        super.update();
//        door1.update();
    }
}
